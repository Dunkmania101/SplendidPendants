package dunkmania101.splendidpendants.objects.items;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import dunkmania101.splendidpendants.SplendidPendants;
import dunkmania101.splendidpendants.data.models.BlankBipedModel;
import dunkmania101.splendidpendants.util.PendantTools;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import java.util.List;

public class PendantItem extends ArmorItem {
    public PendantItem(IArmorMaterial materialIn, Properties properties) {
        super(materialIn, EquipmentSlotType.CHEST, properties);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot, ItemStack stack) {
        if (PendantTools.isEnabled(stack) && equipmentSlot == this.getEquipmentSlot()) {
            return customAttributeModifiers();
        }
        return ImmutableMultimap.of();
    }

    public Multimap<Attribute, AttributeModifier> customAttributeModifiers() {
        return ImmutableMultimap.of();
    }

    @Override
    public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default) {
        if (PendantTools.isEnabled(itemStack)) {
            return (A) getCustomModel(entityLiving, itemStack, armorSlot);
        }
        return (A) new BlankBipedModel();
    }

    public BipedModel<LivingEntity> getCustomModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot) {
        return new BlankBipedModel();
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
        if (PendantTools.isEnabled(stack)) {
            return getCustomTexture(stack, entity, slot, type);
        } else {
            return SplendidPendants.modid + ":textures/blank.png";
        }
    }

    public String getCustomTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
        return SplendidPendants.modid + ":textures/blank.png";
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);
        if (playerIn.isCrouching()) {
            customClickActions(worldIn, playerIn, handIn, stack);
        } else {
            PendantTools.setEnabled(stack, !PendantTools.isEnabled(stack));
        }
        return ActionResult.resultSuccess(stack);
    }

    public void customClickActions(World world, PlayerEntity player, Hand hand, ItemStack stack) {
    }

    @Override
    public boolean isDamageable() {
        return false;
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return PendantTools.isEnabled(stack);
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslationTextComponent("msg.splendidpendants.divider"));
        tooltip.add(new TranslationTextComponent("msg.splendidpendants.use_instructions"));
        tooltip.add(new TranslationTextComponent("msg.splendidpendants.divider"));
        tooltip.add(new TranslationTextComponent("msg.splendidpendants.is_enabled"));
        if (PendantTools.isEnabled(stack)) {
            tooltip.add(new TranslationTextComponent("msg.splendidpendants.enabled"));
        } else {
            tooltip.add(new TranslationTextComponent("msg.splendidpendants.disabled"));
        }
        tooltip.add(new TranslationTextComponent("msg.splendidpendants.divider"));
    }
}
