package dunkmania101.splendidpendants.data;

import dunkmania101.splendidpendants.SplendidPendants;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

import javax.annotation.Nonnull;

public enum PendantArmorMaterial implements IArmorMaterial {
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
    public int getDurability(@Nonnull EquipmentSlotType slotIn) {
        return 0;
    }

    @Override
    public int getDamageReductionAmount(@Nonnull EquipmentSlotType slotIn) {
        return this.damageReduce;
    }

    @Override
    public int getEnchantability() {
        return 0;
    }

    @Nonnull
    @Override
    public SoundEvent getSoundEvent() {
        return this.sound;
    }

    @Nonnull
    @Override
    public Ingredient getRepairMaterial() {
        return Ingredient.EMPTY;
    }

    @Nonnull
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
