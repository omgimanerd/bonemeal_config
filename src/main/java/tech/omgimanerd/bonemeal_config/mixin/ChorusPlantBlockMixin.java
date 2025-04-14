package tech.omgimanerd.bonemeal_config.mixin;

import javax.annotation.Nonnull;

import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.ChorusFlowerBlock;
import net.minecraft.world.level.block.ChorusPlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import tech.omgimanerd.bonemeal_config.Config;
import tech.omgimanerd.bonemeal_config.util.ChorusPlantTraverser;

@Mixin(ChorusPlantBlock.class)
public class ChorusPlantBlockMixin implements BonemealableBlock {

  public boolean isValidBonemealTarget(@Nonnull LevelReader level, @Nonnull BlockPos pos,
      @Nonnull BlockState blockState, boolean isClient) {
    // Not bonemealable if there are no flowers to grow.
    ChorusPlantTraverser t = new ChorusPlantTraverser(level, pos).traverse();
    return Config.CHORUS_ENABLED && t.flowerLocations.size() > 0;
  }

  public boolean isBonemealSuccess(@Nonnull Level level, @Nonnull RandomSource random,
      @Nonnull BlockPos pos, @Nonnull BlockState blockState) {
    // If the chorus plant has reached maximum size, it is still a valid bonemeal
    // target, but no growth will happen.
    ChorusPlantTraverser t = new ChorusPlantTraverser(level, pos).traverse();
    return t.plantSize < Config.CHORUS_SIZE && random.nextDouble() < Config.CHORUS_CHANCE;
  }

  public void performBonemeal(@Nonnull ServerLevel level, @Nonnull RandomSource random,
      @Nonnull BlockPos pos, @Nonnull BlockState blockState) {
    ChorusPlantTraverser t = new ChorusPlantTraverser(level, pos).traverse();
    if (t.flowerLocations.size() == 0) {
      return;
    }
    int index = random.nextIntBetweenInclusive(0, t.flowerLocations.size() - 1);
    ChorusFlowerBlock.generatePlant(
        level, t.flowerLocations.get(index), random, /* maxHorizontalDistance */ 5);
  }

}
