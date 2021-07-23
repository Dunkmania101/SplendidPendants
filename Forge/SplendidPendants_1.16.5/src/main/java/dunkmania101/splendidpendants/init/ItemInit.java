package dunkmania101.splendidpendants.init;

import dunkmania101.splendidpendants.SplendidPendants;
import dunkmania101.splendidpendants.objects.items.*;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SplendidPendants.modid);

    // Pendants
    public static final RegistryObject<LocketItem> LOCKET = ITEMS.register("locket",
            () -> new LocketItem(getNonStackableFireproofItemProperties()
            ));

    public static final RegistryObject<AtlanticPendantItem> ATLANTIC_PENDANT = ITEMS.register("atlantic_pendant",
            () -> new AtlanticPendantItem(getNonStackableFireproofItemProperties()
            ));

    public static final RegistryObject<KnighthoodPendantItem> KNIGHTHOOD_PENDANT = ITEMS.register("knighthood_pendant",
            () -> new KnighthoodPendantItem(getNonStackableFireproofItemProperties()
            ));

    public static final RegistryObject<HolyPendantItem> HOLY_PENDANT = ITEMS.register("holy_pendant",
            () -> new HolyPendantItem(getNonStackableFireproofItemProperties()
            ));

// Not done yet!
//    public static final RegistryObject<ConjuringPendantItem> CONJURING_PENDANT = ITEMS.register("conjuring_pendant",
//            () -> new ConjuringPendantItem((getNonStackableFireproofItemProperties()
//            ));

    // Random Utilities
    public static final RegistryObject<DyeSpongeItem> DYE_SPONGE = ITEMS.register("dye_sponge",
            () -> new DyeSpongeItem(getNonStackableItemProperties()
            ));

    // Ingredient items
    public static final RegistryObject<ShinyItem> ENCHANTED_LACE = ITEMS.register("enchanted_lace",
            () -> new ShinyItem(
                    getBaseItemProperties()
            ));

    public static final RegistryObject<ShinyItem> ENCHANTED_NECKLACE = ITEMS.register("enchanted_necklace",
            () -> new ShinyItem(
                    getBaseItemProperties()
            ));

    public static final RegistryObject<ShinyItem> ENCHANTED_GEMSTONE = ITEMS.register("enchanted_gemstone",
            () -> new ShinyItem(
                    getBaseItemProperties()
            ));

    public static final RegistryObject<Item> IRON_PLATE = ITEMS.register("iron_plate",
            () -> new Item(
                    getBaseItemProperties()
            ));

    public static final RegistryObject<Item> REINFORCED_PLATE = ITEMS.register("reinforced_plate",
            () -> new Item(
                    getBaseItemProperties()
            ));

    public static final RegistryObject<Item> LOCKET_CASING = ITEMS.register("locket_casing",
            () -> new Item(
                    getNonStackableItemProperties()
            ));

    public static final RegistryObject<Item> ARMORY_CORE = ITEMS.register("armory_core",
            () -> new Item(
                    getNonStackableItemProperties()
            ));

    public static final RegistryObject<Item> KNIGHTHOOD_CHARM = ITEMS.register("knighthood_charm",
            () -> new Item(
                    getNonStackableItemProperties()
            ));

    public static final RegistryObject<Item> ATLANTIC_CHARM = ITEMS.register("atlantic_charm",
            () -> new Item(
                    getNonStackableItemProperties()
            ));

    public static final RegistryObject<Item> HOLY_CHARM = ITEMS.register("holy_charm",
            () -> new Item(
                    getNonStackableItemProperties()
            ));

    // Methods
    public static Item.Properties getBaseItemProperties() {
        return new Item.Properties().tab(SplendidPendants.SPLENDID_PENDANTS_GROUP.instance);
    }

    public static Item.Properties getNonStackableItemProperties() {
        return getBaseItemProperties().stacksTo(1);
    }

    public static Item.Properties getNonStackableFireproofItemProperties() {
        return getNonStackableItemProperties().fireResistant();
    }
}
