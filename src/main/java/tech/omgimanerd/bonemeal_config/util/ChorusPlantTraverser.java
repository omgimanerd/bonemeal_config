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

  public ArrayList<BlockPos> flowerLocations;

  private LevelReader level;

  public ChorusPlantTraverser(LevelReader level) {
    this.flowerLocations = new ArrayList<>();
    this.level = level;
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

}