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

  private static final ForgeConfigSpec.BooleanValue CHORUS_CONFIG = BUILDER
      .comment("Can chorus flowers and plants be bonemealed?")
      .define("chorus", true);
  public static boolean CHORUS_ENABLED;
  private static final ForgeConfigSpec.DoubleValue CHORUS_CHANCE_CONFIG = BUILDER
      .comment("Chance to grow a flower on each bonemeal.")
      .defineInRange("chorus_chance", 0.4, 0, 1);
  public static double CHORUS_CHANCE;
  private static final ForgeConfigSpec.IntValue CHORUS_SIZE_CONFIG = BUILDER
      .comment("Maximum size in blocks that the chorus plant can grow to with bonemeal.")
      .defineInRange("chorus_size", 64, 0, 256);
  public static int CHORUS_SIZE;

  private static final ForgeConfigSpec.BooleanValue CORAL_CONFIG = BUILDER
      .comment("Can corals be bonemealed?")
      .define("coral", true);
  public static boolean CORAL_ENABLED;

  private static final ForgeConfigSpec.BooleanValue DEAD_BUSH_CONFIG = BUILDER
      .comment("Can dead bushes be bonemealed?")
      .define("dead_bush", false);
  public static boolean DEAD_BUSH_ENABLED;

  private static final ForgeConfigSpec.BooleanValue FLOWER_CONFIG = BUILDER
      .comment("Can small flowers be bonemealed?")
      .define("flower", true);
  public static boolean FLOWER_ENABLED;

  public static final ForgeConfigSpec.BooleanValue HANGING_ROOTS_CONFIG = BUILDER
      .comment("Can hanging roots be bonemealed?")
      .define("hanging_roots", true);
  public static boolean HANGING_ROOTS_ENABLED;

  private static final ForgeConfigSpec.BooleanValue LEAVES_CONFIG = BUILDER
      .comment("Can leaves be bonemealed?")
      .define("leaves", false);
  public static boolean LEAVES_ENABLED;

  private static final ForgeConfigSpec.BooleanValue MYCELIUM_CONFIG = BUILDER
      .comment("Can mycelium be bonemealed?")
      .define("mycelium", true);
  public static boolean MYCELIUM_ENABLED;

  private static final ForgeConfigSpec.BooleanValue NETHER_SPROUTS_CONFIG = BUILDER
      .comment("Can nether sprouts be bonemealed?")
      .define("sprouts", true);
  public static boolean NETHER_SPROUTS_ENABLED;

  private static final ForgeConfigSpec.BooleanValue NETHER_WART_CONFIG = BUILDER
      .comment("Can nether wart be bonemealed?")
      .define("nether_wart", true);
  public static boolean NETHER_WART_ENABLED;

  private static final ForgeConfigSpec.BooleanValue ROOTS_CONFIG = BUILDER
      .comment("Can warped/nylium roots be bonemealed?")
      .define("roots", true);
  public static boolean ROOTS_ENABLED;

  private static final ForgeConfigSpec.BooleanValue SPONGE_CONFIG = BUILDER
      .comment("Can sponges be bonemealed?")
      .define("sponge", false);
  public static boolean SPONGE_ENABLED;

  private static final ForgeConfigSpec.BooleanValue SPORE_BLOSSOM_CONFIG = BUILDER
      .comment("Can spore blossoms be bonemealed?")
      .define("spore_blossom", true);
  public static boolean SPORE_BLOSSOM_ENABLED;

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

  private static final ForgeConfigSpec.BooleanValue VINE_CONFIG = BUILDER
      .comment("Can vines be bonemealed?")
      .define("vine", true);
  public static boolean VINE_ENABLED;

  private static final ForgeConfigSpec.BooleanValue WATERLILY_CONFIG = BUILDER
      .comment("Can lilypads be bonemealed?")
      .define("waterlily", true);
  public static boolean WATERLILY_ENABLED;

  static final ForgeConfigSpec SPEC = BUILDER.build();

  @SubscribeEvent
  static void onLoad(final ModConfigEvent event) {
    CACTUS_ENABLED = CACTUS_CONFIG.get();
    CACTUS_GROWTH = CACTUS_GROWTH_CONFIG.get();
    CACTUS_HEIGHT = CACTUS_HEIGHT_CONFIG.get();
    CORAL_ENABLED = CORAL_CONFIG.get();
    CHORUS_ENABLED = CHORUS_CONFIG.get();
    CHORUS_CHANCE = CHORUS_CHANCE_CONFIG.get();
    CHORUS_SIZE = CHORUS_SIZE_CONFIG.get();
    DEAD_BUSH_ENABLED = DEAD_BUSH_CONFIG.get();
    FLOWER_ENABLED = FLOWER_CONFIG.get();
    HANGING_ROOTS_ENABLED = HANGING_ROOTS_CONFIG.get();
    LEAVES_ENABLED = LEAVES_CONFIG.get();
    MYCELIUM_ENABLED = MYCELIUM_CONFIG.get();
    NETHER_SPROUTS_ENABLED = NETHER_SPROUTS_CONFIG.get();
    NETHER_WART_ENABLED = NETHER_WART_CONFIG.get();
    ROOTS_ENABLED = ROOTS_CONFIG.get();
    SPONGE_ENABLED = SPONGE_CONFIG.get();
    SPORE_BLOSSOM_ENABLED = SPORE_BLOSSOM_CONFIG.get();
    SUGAR_CANE_ENABLED = SUGAR_CANE_CONFIG.get();
    SUGAR_CANE_GROWTH = SUGAR_CANE_GROWTH_CONFIG.get();
    SUGAR_CANE_HEIGHT = SUGAR_CANE_HEIGHT_CONFIG.get();
    VINE_ENABLED = VINE_CONFIG.get();
    WATERLILY_ENABLED = WATERLILY_CONFIG.get();
  }
}
