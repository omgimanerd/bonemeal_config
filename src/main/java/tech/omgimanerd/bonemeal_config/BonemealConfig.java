package tech.omgimanerd.bonemeal_config;

import com.mojang.logging.LogUtils;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

import org.slf4j.Logger;

@Mod(BonemealConfig.MODID)
public class BonemealConfig {

  // Define mod id in a common place for everything to reference
  public static final String MODID = "bonemeal_config";

  public static final Logger LOGGER = LogUtils.getLogger();

  public BonemealConfig() {
    // Register ourselves for server and other game events we are interested in
    MinecraftForge.EVENT_BUS.register(this);

    // Register our mod's ForgeConfigSpec so that Forge can create and load the
    // config file for us
    ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
  }
}
