package tech.omgimanerd.bonemeal_config.mixin;

import javax.annotation.Nonnull;

import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.NetherWartBlock;
import net.minecraft.world.level.block.state.BlockState;
import tech.omgimanerd.bonemeal_config.Config;

@Mixin(NetherWartBlock.class)
public class NetherWartBlockMixin implements BonemealableBlock {

  public boolean isBonemealSuccess(@Nonnull Level level, @Nonnull RandomSource random, @Nonnull BlockPos pos,
      @Nonnull BlockState blockState) {
    return Config.NETHER_WART_ENABLED;
  }

  public boolean isValidBonemealTarget(@Nonnull LevelReader reader, @Nonnull BlockPos pos,
      @Nonnull BlockState blockState, boolean isClient) {
    return blockState.getValue(NetherWartBlock.AGE) < 3;
  }

  public void performBonemeal(@Nonnull ServerLevel level, @Nonnull RandomSource random, @Nonnull BlockPos pos,
      @Nonnull BlockState blockState) {
    // Advances it one growth stage.
    int newAge = Math.min(3, blockState.getValue(NetherWartBlock.AGE) + 1);
    level.setBlockAndUpdate(pos, blockState.setValue(NetherWartBlock.AGE, newAge));
  }
}
