package tech.omgimanerd.bonemeal_config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber(modid = BonemealConfig.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {
  private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

  private static final ForgeConfigSpec.BooleanValue CACTUS_CONFIG = BUILDER
      .comment("Can cactus be bonemealed?")
      .define("cactus", true);
  public static boolean CACTUS_ENABLED;
  private static final ForgeConfigSpec.IntValue CACTUS_GROWTH_CONFIG = BUILDER
      .comment("Maximum amount that cactus can grow per bonemeal.")
      .defineInRange("cactus_growth", 3, 1, 10);
  public static int CACTUS_GROWTH;
  private static final ForgeConfigSpec.IntValue CACTUS_HEIGHT_CONFIG = BUILDER
      .comment("Maximum height that cactus can grow when bonemealed.")
      .defineInRange("cactus_height", 7, 3, 256);
  public static int CACTUS_HEIGHT;

  private static final ForgeConfigSpec.BooleanValue NETHER_WART_CONFIG = BUILDER
      .comment("Can nether wart be bonemealed?")
      .define("nether_wart", true);
  public static boolean NETHER_WART_ENABLED;

  private static final ForgeConfigSpec.BooleanValue SUGAR_CANE_CONFIG = BUILDER
      .comment("Can sugar cane be bonemealed?")
      .define("sugar_cane", true);
  public static boolean SUGAR_CANE_ENABLED;
  private static final ForgeConfigSpec.IntValue SUGAR_CANE_GROWTH_CONFIG = BUILDER
      .comment("Maximum amount that sugar cane can grow per bonemeal.")
      .defineInRange("sugar_cane_growth", 3, 1, 10);
  public static int SUGAR_CANE_GROWTH;
  private static final ForgeConfigSpec.IntValue SUGAR_CANE_HEIGHT_CONFIG = BUILDER
      .comment("Maximum height that sugar cane can grow when bonemealed.")
      .defineInRange("sugar_cane_height", 7, 3, 256);
  public static int SUGAR_CANE_HEIGHT;

  static final ForgeConfigSpec SPEC = BUILDER.build();

  @SubscribeEvent
  static void onLoad(final ModConfigEvent event) {
    CACTUS_ENABLED = CACTUS_CONFIG.get();
    CACTUS_GROWTH = CACTUS_GROWTH_CONFIG.get();
    CACTUS_HEIGHT = CACTUS_HEIGHT_CONFIG.get();
    NETHER_WART_ENABLED = NETHER_WART_CONFIG.get();
    SUGAR_CANE_ENABLED = SUGAR_CANE_CONFIG.get();
    SUGAR_CANE_GROWTH = SUGAR_CANE_GROWTH_CONFIG.get();
    SUGAR_CANE_HEIGHT = SUGAR_CANE_HEIGHT_CONFIG.get();
  }
}
