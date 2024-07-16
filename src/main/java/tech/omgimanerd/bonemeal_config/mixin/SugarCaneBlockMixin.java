package tech.omgimanerd.bonemeal_config.mixin;

import javax.annotation.Nonnull;

import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.SugarCaneBlock;
import net.minecraft.world.level.block.state.BlockState;
import tech.omgimanerd.bonemeal_config.BonemealConfig;
import tech.omgimanerd.bonemeal_config.Config;

@Mixin(SugarCaneBlock.class)
@Implements(@Interface(iface = BonemealableBlock.class, prefix = "bonemealable$"))
public class SugarCaneBlockMixin implements BonemealableBlock {

  public boolean isBonemealSuccess(@Nonnull Level level, @Nonnull RandomSource random, @Nonnull BlockPos pos,
      @Nonnull BlockState blockState) {
    return Config.SUGAR_CANE_ENABLED;
  }

  public boolean isValidBonemealTarget(@Nonnull LevelReader level, @Nonnull BlockPos pos,
      @Nonnull BlockState blockState, boolean isClient) {
    BonemealConfig.LOGGER.debug("{}", pos);
    return true;
  }

  public void performBonemeal(@Nonnull ServerLevel level, @Nonnull RandomSource random, @Nonnull BlockPos pos,
      @Nonnull BlockState blockState) {
    // TODO Auto-generated method stub
  }

  private int getSugarcaneHeight(@Nonnull BlockPos pos) {
    return 0;
  }

}
