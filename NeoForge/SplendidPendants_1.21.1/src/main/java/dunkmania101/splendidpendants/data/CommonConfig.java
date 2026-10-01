package dunkmania101.splendidpendants.data;

import net.neoforged.neoforge.common.ModConfigSpec;

public class CommonConfig {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static ModConfigSpec.BooleanValue ENABLE_CURIOS;
    public static ModConfigSpec.BooleanValue ENABLE_MODELS;
    public static ModConfigSpec.DoubleValue ATLANTIC_SWIM_SPEED;
    public static ModConfigSpec.IntValue ATLANTIC_CONDUIT_POWER_DURATION;
    public static ModConfigSpec.IntValue ATLANTIC_CONDUIT_POWER_AMPLIFIER;
    public static ModConfigSpec.IntValue RENDER_KNIGHTHOOD_TICKS;
    public static ModConfigSpec.DoubleValue KNIGHTHOOD_EXTRA_HEALTH;
    public static ModConfigSpec.DoubleValue KNIGHTHOOD_ARMOR;
    public static ModConfigSpec.DoubleValue KNIGHTHOOD_ARMOR_TOUGHNESS;
    public static ModConfigSpec.DoubleValue KNIGHTHOOD_KNOCK_BACK_RESISTANCE;
    public static ModConfigSpec.DoubleValue KNIGHTHOOD_KNOCK_BACK_BOOST;
    public static ModConfigSpec.DoubleValue KNIGHTHOOD_DAMAGE_BOOST;
    public static ModConfigSpec.DoubleValue KNIGHTHOOD_CRITICAL_DAMAGE;
    public static ModConfigSpec.DoubleValue HOLY_FLIGHT_SPEED;
    public static ModConfigSpec.BooleanValue HOLY_ENABLE_NOCLIP;
    public static ModConfigSpec.DoubleValue HOLDING_REACH_DISTANCE;

    static {
        BUILDER.push("Splendid Pendants - Common Config: ");
        setup();
        BUILDER.pop();
    }

    private static void setup() {
        BUILDER.push("Atlantic Pendant Stats: ");
        ATLANTIC_SWIM_SPEED = BUILDER.comment("Boosted swim speed of the atlantic pendant.")
                .defineInRange("atlantic_swim_speed", 3, 0, Double.MAX_VALUE);
        ATLANTIC_CONDUIT_POWER_DURATION = BUILDER
                .comment("Duration for the conduit power effect of the atlantic pendant (in ticks, there are 20 in a second) (0 to disable).")
                .defineInRange("atlantic_conduit_power_duration", 40, 0, Integer.MAX_VALUE);
        ATLANTIC_CONDUIT_POWER_AMPLIFIER = BUILDER
                .comment("Amplifier for the conduit power effect of the atlantic pendant (0 to disable).")
                .defineInRange("atlantic_conduit_power_amplifier", 2, 0, Integer.MAX_VALUE);

        BUILDER.pop();

        BUILDER.push("Knighthood Pendant Stats: ");
        RENDER_KNIGHTHOOD_TICKS = BUILDER.comment(
                "How long to render the knighthood pendant's armor model upon activation (in ticks, there are 20 in a second) (0 to disable).")
                .defineInRange("render_knighthood_ticks", 60, 0, Integer.MAX_VALUE);
        KNIGHTHOOD_EXTRA_HEALTH = BUILDER.comment("Extra health from the knighthood pendant (0 to disable).")
                .defineInRange("knighthood_extra_health", 20, 0, Double.MAX_VALUE);
        KNIGHTHOOD_ARMOR = BUILDER.comment("Armor value of the knighthood pendant (0 to disable).")
                .defineInRange("knighthood_armor", 20, 0, Double.MAX_VALUE);
        KNIGHTHOOD_ARMOR_TOUGHNESS = BUILDER.comment("Armor toughness value of the knighthood pendant (0 to disable).")
                .defineInRange("knighthood_armor_toughness", 12, 0, Double.MAX_VALUE);
        KNIGHTHOOD_KNOCK_BACK_RESISTANCE = BUILDER
                .comment("Knock-back resistance value of the knighthood pendant (0 to disable).")
                .defineInRange("knighthood_knock_back_resistance", 10, 0, Double.MAX_VALUE);
        KNIGHTHOOD_KNOCK_BACK_BOOST = BUILDER
                .comment("Knock-back boost value of the knighthood pendant (when attacking) (0 to disable).")
                .defineInRange("knighthood_knock_back_boost", 1, 0, Double.MAX_VALUE);
        KNIGHTHOOD_DAMAGE_BOOST = BUILDER.comment("Damage boost of the knighthood pendant.")
                .defineInRange("knighthood_damage_boost", 2, 0, Double.MAX_VALUE);
        KNIGHTHOOD_CRITICAL_DAMAGE = BUILDER.comment("Damage of the knighthood pendant's critical attack (0 to disable).")
                .defineInRange("knighthood_critical_damage", 9, 0, Double.MAX_VALUE);
        BUILDER.pop();

        BUILDER.push("Holy Pendant Stats: ");
        HOLY_FLIGHT_SPEED = BUILDER
                .comment("Speed boost value for flight with the holy pendant (while sprinting) (0 to disable).")
                .defineInRange("holy_flight_speed", 16, 0, Double.MAX_VALUE);
        HOLY_ENABLE_NOCLIP = BUILDER.comment("Whether to enable noclip during flight while sprinting.").define("holy_enable_noclip", true);
        BUILDER.pop();

        BUILDER.push("Holding Pendant Stats: ");
        HOLDING_REACH_DISTANCE = BUILDER
                .comment("Reach distance buff value with the holding pendant (0 to disable).")
                .defineInRange("holding_reach_distance_speed", 7, 0, Double.MAX_VALUE);
        BUILDER.pop();

        BUILDER.push("MISC: ");
        ENABLE_CURIOS = BUILDER.comment("Whether to enable Curios compat.").define("enable_curios", true);
        ENABLE_MODELS = BUILDER.comment(
                "Whether to render the pendants' models on the wearer (the atlantic tail, holy halo, knighthood armor and holding hands).",
                "Disabling this keeps every pendant's effects; it only stops them from being drawn.")
                .define("enable_models", true);
        BUILDER.pop();
    }

    public static final ModConfigSpec CONFIG = BUILDER.build();

    public static boolean getOrDefault(ModConfigSpec.BooleanValue value, boolean fallback) {
        return CONFIG.isLoaded() ? value.get() : fallback;
    }
}
