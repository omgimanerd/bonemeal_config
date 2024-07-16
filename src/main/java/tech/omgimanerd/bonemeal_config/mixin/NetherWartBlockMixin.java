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

@Mixin(NetherWartBlock.class)
@Implements(@Interface(iface = BonemealableBlock.class, prefix = "bonemealable$"))
public class NetherWartBlockMixin {

  boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState blockState) {
    return true;
  }

  boolean isValidBonemealTarget(LevelReader reader, BlockPos pos, BlockState blockState, boolean isClient) {

    return true;
    // return blockState.getValue(NetherWartBlock.AGE) < 3;
  }

  void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState blockState) {
    blockState.setValue(NetherWartBlock.AGE, 3);
  }
}
