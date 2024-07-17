package tech.omgimanerd.bonemeal_config.mixin;

import javax.annotation.Nonnull;

import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.NetherSproutsBlock;
import net.minecraft.world.level.block.state.BlockState;
import tech.omgimanerd.bonemeal_config.Config;

@Mixin(NetherSproutsBlock.class)
public class NetherSproutsBlockMixin extends Block implements BonemealableBlock {

  public NetherSproutsBlockMixin(Properties p) {
    super(p);
  }

  public boolean isValidBonemealTarget(@Nonnull LevelReader reader, @Nonnull BlockPos pos,
      @Nonnull BlockState blockState, boolean isClient) {
    return Config.NETHER_SPROUTS_ENABLED;
  }

  public boolean isBonemealSuccess(@Nonnull Level level, @Nonnull RandomSource random,
      @Nonnull BlockPos pos, @Nonnull BlockState blockState) {
    return true;
  }

  public void performBonemeal(@Nonnull ServerLevel level, @Nonnull RandomSource random,
      @Nonnull BlockPos pos, @Nonnull BlockState blockState) {
    popResource(level, pos, new ItemStack(this));
  }
}
