package dunkmania101.splendidpendants.data;

import dunkmania101.splendidpendants.SplendidPendants;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

public enum PendantArmorMaterial implements IArmorMaterial {
    ATLANTIC(SplendidPendants.modid + ":atlantic", SoundEvents.ENTITY_DOLPHIN_SPLASH, 0, 0, 0),
    KNIGHTHOOD(SplendidPendants.modid + ":knighthood", SoundEvents.ENTITY_IRON_GOLEM_STEP, CommonConfig.KNIGHTHOOD_ARMOR.get(), CommonConfig.KNIGHTHOOD_ARMOR_TOUGHNESS.get(), CommonConfig.KNIGHTHOOD_KNOCK_BACK_RESISTANCE.get()),
    HOLY(SplendidPendants.modid + ":holy", SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, 0, 0, 0),
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
    public int getDurability(EquipmentSlotType slotIn) {
        return 0;
    }

    @Override
    public int getDamageReductionAmount(EquipmentSlotType slotIn) {
        return this.damageReduce;
    }

    @Override
    public int getEnchantability() {
        return 0;
    }

    @Override
    public SoundEvent getSoundEvent() {
        return this.sound;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return Ingredient.EMPTY;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float func_230304_f_() {
        return this.knockBackResist;
    }
}
