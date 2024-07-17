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
import net.minecraft.world.level.block.CactusBlock;
import net.minecraft.world.level.block.state.BlockState;
import tech.omgimanerd.bonemeal_config.Config;
import tech.omgimanerd.bonemeal_config.util.BlockUtils;

@Mixin(CactusBlock.class)
public class CactusBlockMixin implements BonemealableBlock {

  public boolean isBonemealSuccess(@Nonnull Level level, @Nonnull RandomSource random, @Nonnull BlockPos pos,
      @Nonnull BlockState blockState) {
    return Config.CACTUS_ENABLED;
  }

  public boolean isValidBonemealTarget(@Nonnull LevelReader level, @Nonnull BlockPos pos,
      @Nonnull BlockState blockState, boolean isClient) {
    return BlockUtils.getCropHeight(level, pos, CactusBlock.class) < Config.CACTUS_HEIGHT;
  }

  public void performBonemeal(@Nonnull ServerLevel level, @Nonnull RandomSource random, @Nonnull BlockPos pos,
      @Nonnull BlockState blockState) {
    int height = BlockUtils.getCropHeight(level, pos, CactusBlock.class);
    int growth = Math.min(Config.CACTUS_HEIGHT - height,
        random.nextIntBetweenInclusive(1, Config.CACTUS_GROWTH));
    BlockPos top = BlockUtils.getTopCropBlock(level, pos, CactusBlock.class);
    for (int i = 0; i < growth; ++i, top = top.above()) {
      level.setBlockAndUpdate(top.above(), Blocks.CACTUS.defaultBlockState());
    }
  }
}
