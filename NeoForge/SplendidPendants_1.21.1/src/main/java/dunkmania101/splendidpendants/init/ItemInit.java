package dunkmania101.splendidpendants.init;

import dunkmania101.splendidpendants.SplendidPendants;
import dunkmania101.splendidpendants.objects.items.AtlanticPendantItem;
import dunkmania101.splendidpendants.objects.items.DyeSpongeItem;
import dunkmania101.splendidpendants.objects.items.HoldingPendantItem;
import dunkmania101.splendidpendants.objects.items.HolyPendantItem;
import dunkmania101.splendidpendants.objects.items.KnighthoodPendantItem;
import dunkmania101.splendidpendants.objects.items.LocketItem;
import dunkmania101.splendidpendants.objects.items.ShinyItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ItemInit {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(SplendidPendants.modid);

    // Pendants
    public static final DeferredItem<LocketItem> LOCKET = ITEMS.register("locket",
            () -> new LocketItem(getNonStackableFireproofItemProperties()));

    public static final DeferredItem<AtlanticPendantItem> ATLANTIC_PENDANT = ITEMS.register("atlantic_pendant",
            () -> new AtlanticPendantItem(getNonStackableFireproofItemProperties()));

    public static final DeferredItem<KnighthoodPendantItem> KNIGHTHOOD_PENDANT = ITEMS.register("knighthood_pendant",
            () -> new KnighthoodPendantItem(getNonStackableFireproofItemProperties()));

    public static final DeferredItem<HolyPendantItem> HOLY_PENDANT = ITEMS.register("holy_pendant",
            () -> new HolyPendantItem(getNonStackableFireproofItemProperties()));

    public static final DeferredItem<HoldingPendantItem> HOLDING_PENDANT = ITEMS.register("holding_pendant",
            () -> new HoldingPendantItem(getNonStackableFireproofItemProperties()));

    // Not done yet!
    // public static final DeferredItem<MagePendantItem> MAGE_PENDANT =
    // ITEMS.register("mage_pendant",
    // () -> new MagePendantItem(getNonStackableFireproofItemProperties()
    // ));

    // Random Utilities
    public static final DeferredItem<DyeSpongeItem> DYE_SPONGE = ITEMS.register("dye_sponge",
            () -> new DyeSpongeItem(getNonStackableItemProperties()));

    // Ingredient items
    public static final DeferredItem<ShinyItem> ENCHANTED_LACE = ITEMS.register("enchanted_lace",
            () -> new ShinyItem(
                    getBaseItemProperties()));

    public static final DeferredItem<ShinyItem> ENCHANTED_NECKLACE = ITEMS.register("enchanted_necklace",
            () -> new ShinyItem(
                    getBaseItemProperties()));

    public static final DeferredItem<ShinyItem> ENCHANTED_GEMSTONE = ITEMS.register("enchanted_gemstone",
            () -> new ShinyItem(
                    getBaseItemProperties()));

    public static final DeferredItem<Item> IRON_PLATE = ITEMS.register("iron_plate",
            () -> new Item(
                    getBaseItemProperties()));

    public static final DeferredItem<Item> REINFORCED_PLATE = ITEMS.register("reinforced_plate",
            () -> new Item(
                    getBaseItemProperties()));

    public static final DeferredItem<Item> LOCKET_CASING = ITEMS.register("locket_casing",
            () -> new Item(
                    getNonStackableItemProperties()));

    public static final DeferredItem<Item> ARMORY_CORE = ITEMS.register("armory_core",
            () -> new Item(
                    getNonStackableItemProperties()));

    public static final DeferredItem<Item> KNIGHTHOOD_CHARM = ITEMS.register("knighthood_charm",
            () -> new Item(
                    getNonStackableItemProperties()));

    public static final DeferredItem<Item> ATLANTIC_CHARM = ITEMS.register("atlantic_charm",
            () -> new Item(
                    getNonStackableItemProperties()));

    public static final DeferredItem<Item> HOLY_CHARM = ITEMS.register("holy_charm",
            () -> new Item(
                    getNonStackableItemProperties()));

    public static final DeferredItem<Item> LEATHER_HAND = ITEMS.register("leather_hand",
            () -> new Item(getBaseItemProperties()));

    public static final DeferredItem<Item> HOLDING_CHARM = ITEMS.register("holding_charm",
            () -> new Item(getNonStackableItemProperties()));

    // Methods
    public static Item.Properties getBaseItemProperties() {
        return new Item.Properties();
    }

    public static Item.Properties getNonStackableItemProperties() {
        return getBaseItemProperties().stacksTo(1);
    }

    public static Item.Properties getNonStackableFireproofItemProperties() {
        return getNonStackableItemProperties().fireResistant();
    }
}
