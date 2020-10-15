package dunkmania101.splendidpendants.data;

import dunkmania101.splendidpendants.SplendidPendants;

import java.util.UUID;

public class CustomValues {
    public static final int locketSize = 3;
    public static final int dyeableSize = 1;

    public static final int renderKnighthoodTicks = 60;

    public static final String pendantCuriosSlotName = "pendant";

    //NBT keys
    public static final String storedItemStacksKey = SplendidPendants.modid + "_storedItemStacks";
    public static final String enabledKey = SplendidPendants.modid + "_enabled";

    public static final String hasAtlanticKey = SplendidPendants.modid + "_hasAtlantic";
    public static final String hasKnighthoodKey = SplendidPendants.modid + "_hasKnighthood";
    public static final String hasHolyKey = SplendidPendants.modid + "_hasHoly";

    public static final String renderKnighthoodKey = SplendidPendants.modid + "_renderKnighthood";
    public static final String playerHealthKey = SplendidPendants.modid + "_playerHealth";

    // AttributeModifier UUIDs
    /**
     * If you're a mod maker and are reading this, please don't use these UUIDs as it may result in a conflict if both mods are installed!
     * If you need help getting some, here's a site that will generate up to 500 at a time:
     * https://www.uuidgenerator.net/
     **/
    public static final UUID atlanticSpeedUUID = UUID.fromString("c82bfd34-c285-40b1-8234-e2be23f29185");

    public static final UUID knighthoodMaxHealthUUID = UUID.fromString("748cb259-d1ed-46bb-ad25-268fd5334239");
    public static final UUID knighthoodArmorUUID = UUID.fromString("35e5dd79-8eb6-4a54-89b7-190ed5b40f3b");
    public static final UUID knighthoodArmorToughnessUUID = UUID.fromString("c191c58b-1a95-4e74-b329-b30f9e2d9d29");
    public static final UUID knighthoodKnockBackResistUUID = UUID.fromString("b2f1723e-4688-4cdc-acc1-6a5f7c09511c");
    public static final UUID knighthoodKnockBackBoostUUID = UUID.fromString("c198cd08-4044-46b5-b0ca-c23b00d7a31e");
    public static final UUID knighthoodDamageBoostUUID = UUID.fromString("c191c58b-1a95-4e74-b329-b30f9e2d9d29");

    public static final UUID holyFlightSpeedBoostUUID = UUID.fromString("2a85cec2-8ce6-471f-b091-572fa13d9fc1");

    // AttributeModifier names
    public static final String atlanticSpeedName = SplendidPendants.modid + "_atlanticSpeed";

    public static final String knighthoodMaxHealthName = SplendidPendants.modid + "_knighthoodMaxHealth";
    public static final String knighthoodArmorName = SplendidPendants.modid + "_knighthoodArmor";
    public static final String knighthoodArmorToughnessName = SplendidPendants.modid + "_knighthoodArmorToughness";
    public static final String knighthoodKnockBackResistName = SplendidPendants.modid + "_knighthoodKnockBackResist";
    public static final String knighthoodKnockBackBoostName = SplendidPendants.modid + "_knighthoodKnockBackBoost";
    public static final String knighthoodDamageBoostName = SplendidPendants.modid + "_knighthoodDamageBoost";

    public static final String holyFlightSpeedBoostName = SplendidPendants.modid + "_holyFlightSpeedBoost";
}