package dunkmania101.splendidpendants.init;

import dunkmania101.splendidpendants.SplendidPendants;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Map;

public class ArmorMaterialInit {
    public static final DeferredRegister<ArmorMaterial> ARMOR_MATERIALS = DeferredRegister
            .create(Registries.ARMOR_MATERIAL, SplendidPendants.modid);

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> ATLANTIC = register("atlantic", SoundEvents.DOLPHIN_SPLASH);
    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> KNIGHTHOOD = register("knighthood", SoundEvents.IRON_GOLEM_STEP);
    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> HOLY = register("holy", SoundEvents.ENCHANTMENT_TABLE_USE);
    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> HOLDING = register("holding", SoundEvents.PHANTOM_FLAP);
    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> LOCKET = register("locket", SoundEvents.ARMOR_EQUIP_CHAIN.value());

    private static DeferredHolder<ArmorMaterial, ArmorMaterial> register(String name, SoundEvent equipSound) {
        return ARMOR_MATERIALS.register(name, () -> new ArmorMaterial(
                Map.of(ArmorItem.Type.CHESTPLATE, 0),
                0,
                BuiltInRegistries.SOUND_EVENT.wrapAsHolder(equipSound),
                () -> Ingredient.EMPTY,
                java.util.List.of(new ArmorMaterial.Layer(SplendidPendants.id(name), "", false)),
                0F,
                0F));
    }
}
