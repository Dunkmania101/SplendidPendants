package dunkmania101.splendidpendants.objects.items;

import dunkmania101.splendidpendants.data.CustomValues;
import dunkmania101.splendidpendants.data.compat.CuriosCompat;
import dunkmania101.splendidpendants.data.compat.Mods;
import dunkmania101.splendidpendants.data.models.BlankBipedModel;
import dunkmania101.splendidpendants.data.models.FakeHolyHaloModel;
import dunkmania101.splendidpendants.objects.containers.DyeableContainer;
import dunkmania101.splendidpendants.util.PendantTools;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ArmorStandEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.DyeColor;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
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

    @OnlyIn(Dist.CLIENT)
    @Override
    public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default) {
        if (entityLiving instanceof PlayerEntity || (entityLiving instanceof ArmorStandEntity)) {
            if (PendantTools.isEnabled(itemStack)) {
                return (A) getCustomModel(entityLiving, itemStack, armorSlot);
            }
            return (A) new BlankBipedModel();
        }
        return (A) new FakeHolyHaloModel(DyeColor.RED);
    }

    @OnlyIn(Dist.CLIENT)
    public BipedModel<LivingEntity> getCustomModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot) {
        return new BlankBipedModel();
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
        if (entity instanceof PlayerEntity) {
            if (PendantTools.isEnabled(stack)) {
                return getCustomTexture(stack, entity, slot, type);
            }
            return CustomValues.blankTextureLocation;
        }
        return CustomValues.whiteTextureLocation;
    }

    public String getCustomTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
        return CustomValues.blankTextureLocation;
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> use(@Nonnull World worldIn, PlayerEntity playerIn, @Nonnull Hand handIn) {
        ItemStack stack = playerIn.getItemInHand(handIn);
        if (playerIn.isCrouching()) {
            customClickActions(worldIn, playerIn, handIn, stack);
        } else {
            PendantTools.setEnabled(stack, !PendantTools.isEnabled(stack));
        }
        return ActionResult.success(stack);
    }

    public void customClickActions(World world, PlayerEntity player, Hand hand, ItemStack stack) {
        INamedContainerProvider containerProvider = getContainerProvider(world, player, hand, stack);
        if (containerProvider != null) {
            player.openMenu(containerProvider);
        }
    }

    public INamedContainerProvider getContainerProvider(World world, PlayerEntity playerEntity, Hand hand, ItemStack stack) {
        return new SimpleNamedContainerProvider(
                (id, playerInventory, openingPlayer) -> new DyeableContainer(id, playerInventory, stack),
                stack.getDisplayName()
        );
    }

    @Override
    public boolean canBeDepleted() {
        return false;
    }

    @Override
    public boolean isFoil(@Nonnull ItemStack stack) {
        return PendantTools.isEnabled(stack);
    }

    public boolean isDyeable() {
        return true;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@Nonnull ItemStack stack, World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslationTextComponent("msg.splendidpendants.divider"));
        tooltip.add(new TranslationTextComponent("msg.splendidpendants.use_instructions").withStyle(TextFormatting.GRAY));
        tooltip.add(new TranslationTextComponent("msg.splendidpendants.divider"));
        tooltip.add(new TranslationTextComponent("msg.splendidpendants.is_enabled").withStyle(TextFormatting.GRAY));
        if (PendantTools.isEnabled(stack)) {
            tooltip.add(new TranslationTextComponent("msg.splendidpendants.enabled").withStyle(TextFormatting.GREEN, TextFormatting.BOLD));
        } else {
            tooltip.add(new TranslationTextComponent("msg.splendidpendants.disabled").withStyle(TextFormatting.RED, TextFormatting.BOLD));
        }
        tooltip.add(new TranslationTextComponent("msg.splendidpendants.divider"));
        if (isDyeable()) {
            tooltip.add(new TranslationTextComponent("msg.splendidpendants.dyeable_sneak_use_instructions").withStyle(TextFormatting.GRAY));
            tooltip.add(new TranslationTextComponent("msg.splendidpendants.divider"));
        }
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, CompoundNBT nbt) {
        if (Mods.CURIOS.isLoaded()) {
            return CuriosCompat.initPendantCapabilities(stack);
        }
        return super.initCapabilities(stack, nbt);
    }
}
