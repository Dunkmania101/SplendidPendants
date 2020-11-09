package dunkmania101.splendidpendants.init;

import dunkmania101.splendidpendants.SplendidPendants;
import dunkmania101.splendidpendants.objects.items.*;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SplendidPendants.modid);

    public static final RegistryObject<LocketItem> LOCKET = ITEMS.register("locket",
            () -> new LocketItem(
                    new Item.Properties()
                            .group(SplendidPendants.SPLENDID_PENDANTS_GROUP.instance)
                            .maxStackSize(1)
                            .isImmuneToFire()
            ));

    public static final RegistryObject<AtlanticPendantItem> ATLANTIC_PENDANT = ITEMS.register("atlantic_pendant",
            () -> new AtlanticPendantItem(
                    new Item.Properties()
                            .group(SplendidPendants.SPLENDID_PENDANTS_GROUP.instance)
                            .maxStackSize(1)
                            .isImmuneToFire()
            ));

    public static final RegistryObject<KnighthoodPendantItem> KNIGHTHOOD_PENDANT = ITEMS.register("knighthood_pendant",
            () -> new KnighthoodPendantItem(
                    new Item.Properties()
                            .group(SplendidPendants.SPLENDID_PENDANTS_GROUP.instance)
                            .maxStackSize(1)
                            .isImmuneToFire()
            ));

    public static final RegistryObject<HolyPendantItem> HOLY_PENDANT = ITEMS.register("holy_pendant",
            () -> new HolyPendantItem(
                    new Item.Properties()
                            .group(SplendidPendants.SPLENDID_PENDANTS_GROUP.instance)
                            .maxStackSize(1)
                            .isImmuneToFire()
            ));

    // Ingredient items
    public static final RegistryObject<ShinyItem> ENCHANTED_LACE = ITEMS.register("enchanted_lace",
            () -> new ShinyItem(
                    new Item.Properties()
                            .group(SplendidPendants.SPLENDID_PENDANTS_GROUP.instance)
            ));

    public static final RegistryObject<ShinyItem> ENCHANTED_NECKLACE = ITEMS.register("enchanted_necklace",
            () -> new ShinyItem(
                    new Item.Properties()
                            .group(SplendidPendants.SPLENDID_PENDANTS_GROUP.instance)
            ));

    public static final RegistryObject<ShinyItem> ENCHANTED_GEMSTONE = ITEMS.register("enchanted_gemstone",
            () -> new ShinyItem(
                    new Item.Properties()
                            .group(SplendidPendants.SPLENDID_PENDANTS_GROUP.instance)
            ));

    public static final RegistryObject<Item> IRON_PLATE = ITEMS.register("iron_plate",
            () -> new Item(
                    new Item.Properties()
                            .group(SplendidPendants.SPLENDID_PENDANTS_GROUP.instance)
            ));

    public static final RegistryObject<Item> REINFORCED_PLATE = ITEMS.register("reinforced_plate",
            () -> new Item(
                    new Item.Properties()
                            .group(SplendidPendants.SPLENDID_PENDANTS_GROUP.instance)
            ));

    public static final RegistryObject<Item> LOCKET_CASING = ITEMS.register("locket_casing",
            () -> new Item(
                    new Item.Properties()
                            .group(SplendidPendants.SPLENDID_PENDANTS_GROUP.instance)
                            .maxStackSize(1)
            ));

    public static final RegistryObject<Item> ARMORY_CORE = ITEMS.register("armory_core",
            () -> new Item(
                    new Item.Properties()
                            .group(SplendidPendants.SPLENDID_PENDANTS_GROUP.instance)
                            .maxStackSize(1)
            ));

    public static final RegistryObject<Item> KNIGHTHOOD_CHARM = ITEMS.register("knighthood_charm",
            () -> new Item(
                    new Item.Properties()
                            .group(SplendidPendants.SPLENDID_PENDANTS_GROUP.instance)
                            .maxStackSize(1)
            ));

    public static final RegistryObject<Item> ATLANTIC_CHARM = ITEMS.register("atlantic_charm",
            () -> new Item(
                    new Item.Properties()
                            .group(SplendidPendants.SPLENDID_PENDANTS_GROUP.instance)
                            .maxStackSize(1)
            ));

    public static final RegistryObject<Item> HOLY_CHARM = ITEMS.register("holy_charm",
            () -> new Item(
                    new Item.Properties()
                            .group(SplendidPendants.SPLENDID_PENDANTS_GROUP.instance)
                            .maxStackSize(1)
            ));
}
