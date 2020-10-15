package dunkmania101.splendidpendants.objects.items;

import dunkmania101.splendidpendants.SplendidPendants;
import dunkmania101.splendidpendants.data.compat.CuriosCompat;
import dunkmania101.splendidpendants.data.compat.Mods;
import dunkmania101.splendidpendants.data.models.BlankBipedModel;
import dunkmania101.splendidpendants.data.models.FakeHolyHaloModel;
import dunkmania101.splendidpendants.util.PendantTools;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.DyeColor;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nonnull;
import java.util.List;

public class PendantItem extends ArmorItem {
    public PendantItem(IArmorMaterial materialIn, Properties properties) {
        super(materialIn, EquipmentSlotType.CHEST, properties);
    }

    @Override
    public boolean canEquip(ItemStack stack, EquipmentSlotType armorType, Entity entity) {
        return checkEquipmentSlot(armorType);
    }

    public boolean checkEquipmentSlot(EquipmentSlotType slot) {
        return slot != EquipmentSlotType.MAINHAND
                && slot != EquipmentSlotType.OFFHAND
                && slot != EquipmentSlotType.HEAD
                && slot != EquipmentSlotType.LEGS
                && slot != EquipmentSlotType.FEET;
    }

    @Override
    public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default) {
        if (PendantTools.isEnabled(itemStack)) {
            if (entityLiving instanceof PlayerEntity) {
                return (A) getCustomModel(entityLiving, itemStack, armorSlot);
            } else {
                return (A) new FakeHolyHaloModel(DyeColor.RED);
            }
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

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, PlayerEntity playerIn, @Nonnull Hand handIn) {
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
    public boolean hasEffect(@Nonnull ItemStack stack) {
        return PendantTools.isEnabled(stack);
    }

    @Override
    public void addInformation(@Nonnull ItemStack stack, World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
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

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, CompoundNBT nbt) {
        if (Mods.CURIOS.isLoaded()) {
            return CuriosCompat.initPendantCapabilities(stack);
        }
        return super.initCapabilities(stack, nbt);
    }
}
