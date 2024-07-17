package tech.omgimanerd.bonemeal_config.mixin;

import javax.annotation.Nonnull;

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
import tech.omgimanerd.bonemeal_config.util.BlockUtils;

@Mixin(SugarCaneBlock.class)
public class SugarCaneBlockMixin implements BonemealableBlock {

  public boolean isValidBonemealTarget(@Nonnull LevelReader level, @Nonnull BlockPos pos,
      @Nonnull BlockState blockState, boolean isClient) {
    return Config.SUGAR_CANE_ENABLED;
  }

  public boolean isBonemealSuccess(@Nonnull Level level, @Nonnull RandomSource random,
      @Nonnull BlockPos pos, @Nonnull BlockState blockState) {
    return BlockUtils.getCropHeight(level, pos, SugarCaneBlock.class) < Config.SUGAR_CANE_HEIGHT;
  }

  public void performBonemeal(@Nonnull ServerLevel level, @Nonnull RandomSource random,
      @Nonnull BlockPos pos, @Nonnull BlockState blockState) {
    int height = BlockUtils.getCropHeight(level, pos, SugarCaneBlock.class);
    int growth = Math.min(Config.SUGAR_CANE_HEIGHT - height,
        random.nextIntBetweenInclusive(1, Config.SUGAR_CANE_GROWTH));
    BlockPos top = BlockUtils.getTopCropBlock(level, pos, SugarCaneBlock.class);
    for (int i = 0; i < growth; ++i, top = top.above()) {
      level.setBlockAndUpdate(top.above(), Blocks.SUGAR_CANE.defaultBlockState());
    }
  }

}
