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
import net.minecraft.world.level.block.ChorusFlowerBlock;
import net.minecraft.world.level.block.ChorusPlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import tech.omgimanerd.bonemeal_config.Config;
import tech.omgimanerd.bonemeal_config.util.ChorusPlantTraverser;

@Mixin(ChorusPlantBlock.class)
@Implements(@Interface(iface = BonemealableBlock.class, prefix = "bonemealable$"))
public class ChorusPlantBlockMixin implements BonemealableBlock {

  public boolean isBonemealSuccess(@Nonnull Level level, @Nonnull RandomSource random, @Nonnull BlockPos pos,
      @Nonnull BlockState blockState) {
    return Config.CHORUS_ENABLED;
  }

  public boolean isValidBonemealTarget(@Nonnull LevelReader level, @Nonnull BlockPos pos,
      @Nonnull BlockState blockState, boolean isClient) {
    ChorusPlantTraverser t = new ChorusPlantTraverser(level);
    int plantSize = BlockPos.breadthFirstTraversal(
        pos, /* depth */ Integer.MAX_VALUE, /* visitLimit */ Integer.MAX_VALUE, t::consumer, t::predicate);
    return plantSize < Config.CHORUS_SIZE;
  }

  public void performBonemeal(@Nonnull ServerLevel level, @Nonnull RandomSource random, @Nonnull BlockPos pos,
      @Nonnull BlockState blockState) {
    if (random.nextDouble() >= Config.CHORUS_CHANCE) {
      return;
    }
    ChorusPlantTraverser t = new ChorusPlantTraverser(level);
    BlockPos.breadthFirstTraversal(
        pos, /* depth */ Integer.MAX_VALUE, /* visitLimit */ Integer.MAX_VALUE, t::consumer, t::predicate);
    int index = random.nextIntBetweenInclusive(0, t.flowerLocations.size() - 1);
    ChorusFlowerBlock.generatePlant(
        level, t.flowerLocations.get(index), random, 5);
  }

}
