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
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.SugarCaneBlock;
import net.minecraft.world.level.block.state.BlockState;
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
    return getSugarcaneHeight(level, pos) < Config.SUGAR_CANE_HEIGHT;
  }

  public void performBonemeal(@Nonnull ServerLevel level, @Nonnull RandomSource random, @Nonnull BlockPos pos,
      @Nonnull BlockState blockState) {
    int height = getSugarcaneHeight(level, pos);
    int growth = Math.min(Config.SUGAR_CANE_HEIGHT - height,
        random.nextIntBetweenInclusive(1, Config.SUGAR_CANE_GROWTH));
    BlockPos top = getTopSugarCane(level, pos);
    for (int i = 0; i < growth; ++i, top = top.above()) {
      level.setBlockAndUpdate(top.above(), Blocks.SUGAR_CANE.defaultBlockState());
    }
  }

  private BlockPos getTopSugarCane(@Nonnull LevelReader level, @Nonnull BlockPos pos) {
    while (level.getBlockState(pos.above()).getBlock() instanceof SugarCaneBlock) {
      pos = pos.above();
    }
    return pos;
  }

  private int getSugarcaneHeight(@Nonnull LevelReader level, @Nonnull BlockPos pos) {
    if (!(level.getBlockState(pos).getBlock() instanceof SugarCaneBlock)) {
      return 0;
    }
    int height = 1;
    BlockPos above = pos.above();
    while (level.getBlockState(above).getBlock() instanceof SugarCaneBlock) {
      height++;
      above = above.above();
    }
    BlockPos below = pos.below();
    while (level.getBlockState(below).getBlock() instanceof SugarCaneBlock) {
      height++;
      below = below.below();
    }
    return height;
  }

}
