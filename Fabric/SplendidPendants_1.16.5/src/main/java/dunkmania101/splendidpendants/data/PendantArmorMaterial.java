package dunkmania101.splendidpendants.data;

import dunkmania101.splendidpendants.SplendidPendants;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;


public enum PendantArmorMaterial implements ArmorMaterial {
    ATLANTIC(SplendidPendants.modid + ":atlantic", SoundEvents.ENTITY_DOLPHIN_SPLASH, 0, 0, 0),
    KNIGHTHOOD(SplendidPendants.modid + ":knighthood", SoundEvents.ENTITY_IRON_GOLEM_STEP, 0, 0, 0),
    HOLY(SplendidPendants.modid + ":holy", SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, 0, 0, 0),
//    CONJURING(SplendidPendants.modid + ":conjuring", SoundEvents.ENTITY_EVOKER_FANGS_ATTACK, 0, 0, 0),
    LOCKET(SplendidPendants.modid + ":locket", SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 0, 0, 0);

    private final String name;
    private final SoundEvent sound;
    private final int damageReduce;
    private final float toughness;
    private final float knockBackResist;

    PendantArmorMaterial(String name, SoundEvent sound, int damageReduce, double toughness, double knockBackResist) {
        this.name = name;
        this.sound = sound;
        this.damageReduce = damageReduce;
        this.toughness = (float) toughness;
        this.knockBackResist = (float) knockBackResist;
    }

    @Override
    public int getDurability(EquipmentSlot slotIn) {
        return 0;
    }

    @Override
    public int getProtectionAmount(EquipmentSlot slotIn) {
        return this.damageReduce;
    }

    @Override
    public int getEnchantability() {
        return 0;
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.sound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.EMPTY;
    }

    @Environment(EnvType.CLIENT)
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockBackResist;
    }
}
