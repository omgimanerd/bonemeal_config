package tech.omgimanerd.bonemeal_config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber(modid = BonemealConfig.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {
  private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

  private static final ForgeConfigSpec.BooleanValue NETHER_WART_CONFIG = BUILDER
      .comment("Can nether wart be bonemealed?")
      .define("nether_wart", true);
  public static boolean NETHER_WART_ENABLED;

  private static final ForgeConfigSpec.BooleanValue SUGARCANE_CONFIG = BUILDER
      .comment("Can sugarcane be bonemealed?")
      .define("sugarcane", true);
  public static boolean SUGARCANE_ENABLED;
  private static final ForgeConfigSpec.IntValue SUGARCANE_HEIGHT_CONFIG = BUILDER
      .comment("Maximum height that sugarcane can grow.")
      .defineInRange("sugarcane_height", 7, 3, 256);
  public static int SUGARCANE_HEIGHT;

  static final ForgeConfigSpec SPEC = BUILDER.build();

  @SubscribeEvent
  static void onLoad(final ModConfigEvent event) {
    NETHER_WART_ENABLED = NETHER_WART_CONFIG.get();
    SUGARCANE_ENABLED = SUGARCANE_CONFIG.get();
    SUGARCANE_HEIGHT = SUGARCANE_HEIGHT_CONFIG.get();
  }
}
