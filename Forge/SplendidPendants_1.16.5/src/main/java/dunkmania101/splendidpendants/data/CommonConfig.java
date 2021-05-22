package dunkmania101.splendidpendants.data;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

import java.nio.file.Path;

@Mod.EventBusSubscriber
public class CommonConfig {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static ForgeConfigSpec.BooleanValue ENABLE_CURIOS;
    public static ForgeConfigSpec.DoubleValue ATLANTIC_SWIM_SPEED;
    public static ForgeConfigSpec.IntValue ATLANTIC_VISION_AMPLIFIER;
    public static ForgeConfigSpec.IntValue RENDER_KNIGHTHOOD_TICKS;
    public static ForgeConfigSpec.DoubleValue KNIGHTHOOD_EXTRA_HEALTH;
    public static ForgeConfigSpec.DoubleValue KNIGHTHOOD_ARMOR;
    public static ForgeConfigSpec.DoubleValue KNIGHTHOOD_ARMOR_TOUGHNESS;
    public static ForgeConfigSpec.DoubleValue KNIGHTHOOD_KNOCK_BACK_RESISTANCE;
    public static ForgeConfigSpec.DoubleValue KNIGHTHOOD_KNOCK_BACK_BOOST;
    public static ForgeConfigSpec.DoubleValue KNIGHTHOOD_DAMAGE_BOOST;
    public static ForgeConfigSpec.DoubleValue KNIGHTHOOD_CRITICAL_DAMAGE;
    public static ForgeConfigSpec.DoubleValue HOLY_FLIGHT_SPEED;

    static {
        BUILDER.push("Splendid Pendants - Common Config: ");
        setup();
        BUILDER.pop();
    }

    public static void init(Path file) {
        final CommentedFileConfig data = CommentedFileConfig.builder(file)
                .sync()
                .autosave()
                .writingMode(WritingMode.REPLACE)
                .build();
        data.load();
        CONFIG.setConfig(data);
    }

    private static void setup() {
        BUILDER.push("Atlantic Pendant Stats: ");
        ATLANTIC_SWIM_SPEED = BUILDER.comment("Boosted swim speed of the atlantic pendant.")
                .defineInRange("atlantic_swim_speed", 3, 0, Double.MAX_VALUE);
        ATLANTIC_VISION_AMPLIFIER = BUILDER.comment("Amplifier for the night vision effect of the atlantic pendant.")
                .defineInRange("atlantic_vision_amplifier", 2, 0, Integer.MAX_VALUE);
        BUILDER.pop();

        BUILDER.push("Knighthood Pendant Stats: ");
        RENDER_KNIGHTHOOD_TICKS = BUILDER.comment("How long to render the knighthood pendant's armor model upon activation (in ticks, there are 20 in a second).")
                .defineInRange("render_knighthood_ticks", 60, 0, Integer.MAX_VALUE);
        KNIGHTHOOD_EXTRA_HEALTH = BUILDER.comment("Extra health from the knighthood pendant.")
                .defineInRange("knighthood_extra_health", 20, 0, Double.MAX_VALUE);
        KNIGHTHOOD_ARMOR = BUILDER.comment("Armor value of the knighthood pendant.")
                .defineInRange("knighthood_armor", 20, 0, Double.MAX_VALUE);
        KNIGHTHOOD_ARMOR_TOUGHNESS = BUILDER.comment("Armor toughness value of the knighthood pendant.")
                .defineInRange("knighthood_armor_toughness", 12, 0, Double.MAX_VALUE);
        KNIGHTHOOD_KNOCK_BACK_RESISTANCE = BUILDER.comment("Knock-back resistance value of the knighthood pendant.")
                .defineInRange("knighthood_knock_back_resistance", 10, 0, Double.MAX_VALUE);
        KNIGHTHOOD_KNOCK_BACK_BOOST = BUILDER.comment("Knock-back boost value of the knighthood pendant (when attacking).")
                .defineInRange("knighthood_knock_back_boost", 1, 0, Double.MAX_VALUE);
        KNIGHTHOOD_DAMAGE_BOOST = BUILDER.comment("Damage boost of the knighthood pendant.")
                .defineInRange("knighthood_damage_boost", 2, 0, Double.MAX_VALUE);
        KNIGHTHOOD_CRITICAL_DAMAGE = BUILDER.comment("Damage of the knighthood pendant's critical attack.")
                .defineInRange("knighthood_critical_damage", 9, 0, Double.MAX_VALUE);
        BUILDER.pop();

        BUILDER.push("MISC: ");
        ENABLE_CURIOS = BUILDER.comment("Whether to enable Curios compat.")
                .define("enable_curios", true);
        BUILDER.pop();

        BUILDER.push("Holy Pendant Stats: ");
        HOLY_FLIGHT_SPEED = BUILDER.comment("Speed boost value for flight with the holy pendant (while sprinting).")
                .defineInRange("holy_flight_speed", 10, 0, Double.MAX_VALUE);
        BUILDER.pop();
    }
    public static final ForgeConfigSpec CONFIG = BUILDER.build();
}
