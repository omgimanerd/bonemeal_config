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
import net.minecraft.world.level.block.state.BlockState;
import tech.omgimanerd.bonemeal_config.Config;
import tech.omgimanerd.bonemeal_config.util.ChorusPlantTraverser;

@Mixin(ChorusFlowerBlock.class)
public class ChorusFlowerBlockMixin implements BonemealableBlock {

  public boolean isValidBonemealTarget(@Nonnull LevelReader level, @Nonnull BlockPos pos,
      @Nonnull BlockState blockState, boolean isClient) {
    return Config.CHORUS_ENABLED;
  }

  public boolean isBonemealSuccess(@Nonnull Level level, @Nonnull RandomSource random,
      @Nonnull BlockPos pos, @Nonnull BlockState blockState) {
    ChorusPlantTraverser t = new ChorusPlantTraverser(level, pos).traverse();
    // If the chorus plant has reached maximum size, it is still a valid bonemeal
    // target, but no growth will happen.
    return t.plantSize < Config.CHORUS_SIZE && random.nextDouble() < Config.CHORUS_CHANCE;
  }

  public void performBonemeal(@Nonnull ServerLevel level, @Nonnull RandomSource random,
      @Nonnull BlockPos pos, @Nonnull BlockState blockState) {
    ChorusFlowerBlock.generatePlant(level, pos, random, /* maxHorizontalDistance */5);
  }

}
