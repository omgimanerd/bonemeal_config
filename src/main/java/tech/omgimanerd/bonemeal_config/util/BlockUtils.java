package tech.omgimanerd.bonemeal_config.util;

import javax.annotation.Nonnull;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;

public class BlockUtils {

  /**
   * Gets the topmost position of the crop block given any position along its
   * height and the crop block instance.
   *
   * @param level
   * @param pos
   * @param instance The crop block class instance
   * @return The position of the topmost crop block
   */
  public static BlockPos getTopCropBlock(@Nonnull LevelReader level, @Nonnull BlockPos pos,
      @Nonnull Class<?> instance) {
    while (instance.isInstance(level.getBlockState(pos.above()).getBlock())) {
      pos = pos.above();
    }
    return pos;
  }

  /**
   * Gets the height of a crop block like sugarcane or cactus given any position
   * along its height and the crop block instance.
   *
   * @param level
   * @param pos
   * @param instance The crop block class instance
   * @return The total height of the crop block
   */
  public static int getCropHeight(@Nonnull LevelReader level, @Nonnull BlockPos pos, @Nonnull Class<?> instance) {
    if (!instance.isInstance(level.getBlockState(pos).getBlock())) {
      return 0;
    }
    int height = 1;
    BlockPos above = pos.above();
    while (instance.isInstance(level.getBlockState(above).getBlock())) {
      height++;
      above = above.above();
    }
    BlockPos below = pos.below();
    while (instance.isInstance(level.getBlockState(below).getBlock())) {
      height++;
      below = below.below();
    }
    return height;
  }

}
