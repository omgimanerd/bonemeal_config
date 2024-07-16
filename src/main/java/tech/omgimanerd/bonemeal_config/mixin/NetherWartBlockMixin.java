package tech.omgimanerd.bonemeal_config.mixin;

import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
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
@Implements(@Interface(iface = BonemealableBlock.class, prefix = "bonemealable$"))
public class NetherWartBlockMixin implements BonemealableBlock {

  public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState blockState) {
    return Config.NETHER_WART_ENABLED;
  }

  public boolean isValidBonemealTarget(LevelReader reader, BlockPos pos, BlockState blockState, boolean isClient) {
    return blockState.getValue(NetherWartBlock.AGE) < 3;
  }

  public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState blockState) {
    // Advances it one growth stage.
    int newAge = Math.min(3, blockState.getValue(NetherWartBlock.AGE) + 1);
    level.setBlockAndUpdate(pos, blockState.setValue(NetherWartBlock.AGE, newAge));
  }
}
