package tech.omgimanerd.bonemeal_config.util;

import java.util.ArrayList;
import java.util.function.Consumer;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChorusFlowerBlock;
import net.minecraft.world.level.block.ChorusPlantBlock;

public class ChorusPlantTraverser {

  private static int MAX_DEPTH = 256;

  public ArrayList<BlockPos> flowerLocations;
  public int plantSize;

  private LevelReader level;
  private BlockPos startPos;

  public ChorusPlantTraverser(LevelReader level, BlockPos startPos) {
    this.flowerLocations = new ArrayList<>();
    this.level = level;
    this.startPos = startPos;
  }

  public boolean predicate(BlockPos pos) {
    if (level.getBlockState(pos).getBlock() instanceof ChorusFlowerBlock) {
      flowerLocations.add(pos);
    }
    return true;
  }

  public void consumer(BlockPos pos, Consumer<BlockPos> enqueue) {
    for (Direction dir : Direction.values()) {
      BlockPos testPos = pos.relative(dir);
      Block block = level.getBlockState(testPos).getBlock();
      if (block instanceof ChorusPlantBlock || block instanceof ChorusFlowerBlock) {
        enqueue.accept(testPos);
      }
    }
  }

  public ChorusPlantTraverser traverse() {
    plantSize = BlockPos.breadthFirstTraversal(startPos, /* depth */MAX_DEPTH, /* visitLimit */MAX_DEPTH,
        this::consumer, this::predicate);
    return this;
  }
}