package dunkmania101.splendidpendants.data;

import dunkmania101.splendidpendants.SplendidPendants;
import net.minecraft.resources.ResourceLocation;

public class CustomValues {
    public static final int locketSize = 4;
    public static final int dyeableSize = 1;
    public static final int holdingSize = 6;

    public static final String pendantCuriosSlotName = "pendant";

    //NBT keys
    public static final String hasAtlanticKey = SplendidPendants.modid + "_hasAtlantic";
    public static final String hasKnighthoodKey = SplendidPendants.modid + "_hasKnighthood";
    public static final String hasHolyKey = SplendidPendants.modid + "_hasHoly";
    public static final String hasHoldingKey = SplendidPendants.modid + "_hasHolding";
//    public static final String hasMageKey = SplendidPendants.modid + "_hasMage";

    public static final String renderKnighthoodKey = SplendidPendants.modid + "_renderKnighthood";
    public static final String noRenderAtlanticKey = SplendidPendants.modid + "_noRenderAtlantic";

    public static final String isFlyingKey = SplendidPendants.modid + "_isFlying";
    public static final String isNoClipKey = SplendidPendants.modid + "_isNoClip";

    // Resource Locations
    public static final ResourceLocation blankTextureLocation = SplendidPendants.id("textures/blank.png");
    public static final ResourceLocation whiteTextureLocation = SplendidPendants.id("textures/blank_white.png");
    public static final ResourceLocation grayTextureLocation = SplendidPendants.id("textures/blank_gray.png");

    public static final ResourceLocation pendantSlotSprite = SplendidPendants.id("gui/pendant_slot");
    public static final ResourceLocation dyeSlotSprite = SplendidPendants.id("gui/dye_slot");

    // AttributeModifier ids
    /**
     * If you're a mod maker and are reading this, please don't use these ids as it may result in a conflict if both mods are installed!
     **/
    public static final ResourceLocation atlanticSpeedId = SplendidPendants.id("atlantic_speed");

    public static final ResourceLocation knighthoodMaxHealthId = SplendidPendants.id("knighthood_max_health");
    public static final ResourceLocation knighthoodArmorId = SplendidPendants.id("knighthood_armor");
    public static final ResourceLocation knighthoodArmorToughnessId = SplendidPendants.id("knighthood_armor_toughness");
    public static final ResourceLocation knighthoodKnockBackResistId = SplendidPendants.id("knighthood_knock_back_resist");
    public static final ResourceLocation knighthoodKnockBackBoostId = SplendidPendants.id("knighthood_knock_back_boost");
    public static final ResourceLocation knighthoodDamageBoostId = SplendidPendants.id("knighthood_damage_boost");

    public static final ResourceLocation holyFlightSpeedBoostId = SplendidPendants.id("holy_flight_speed_boost");

    public static final ResourceLocation holdingBlockReachBuffId = SplendidPendants.id("holding_block_reach_buff");
    public static final ResourceLocation holdingEntityReachBuffId = SplendidPendants.id("holding_entity_reach_buff");
}
