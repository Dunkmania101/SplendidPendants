package dunkmania101.splendidpendants.objects.items;

import java.util.List;

import javax.annotation.Nonnull;

import dunkmania101.splendidpendants.SplendidPendants;
import dunkmania101.splendidpendants.data.compat.CuriosCompat;
import dunkmania101.splendidpendants.data.compat.Mods;
import dunkmania101.splendidpendants.data.models.BlankBipedModel;
import dunkmania101.splendidpendants.data.models.FakeHolyHaloModel;
import dunkmania101.splendidpendants.objects.containers.DyeableContainer;
import dunkmania101.splendidpendants.util.PendantTools;
import net.minecraft.ChatFormatting;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.IItemRenderProperties;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

public class PendantItem extends ArmorItem implements IItemRenderProperties {
    public PendantItem(ArmorMaterial materialIn, Properties properties) {
        super(materialIn, EquipmentSlot.CHEST, properties);
    }

    @Override
    public boolean canEquip(ItemStack stack, EquipmentSlot armorType, Entity entity) {
        return checkEquipmentSlot(armorType);
    }

    public boolean checkEquipmentSlot(EquipmentSlot slot) {
        return slot != EquipmentSlot.MAINHAND
                && slot != EquipmentSlot.OFFHAND
                && slot != EquipmentSlot.HEAD
                && slot != EquipmentSlot.LEGS
                && slot != EquipmentSlot.FEET;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public <A extends HumanoidModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, A _default) {
        if (PendantTools.isEnabled(itemStack)) {
            if (entityLiving instanceof Player) {
                return (A) getCustomModel(entityLiving, itemStack, armorSlot);
            }
            return (A) new FakeHolyHaloModel(DyeColor.RED);
        }
        return (A) new BlankBipedModel();
    }

    @OnlyIn(Dist.CLIENT)
    public HumanoidModel<LivingEntity> getCustomModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot) {
        return new BlankBipedModel();
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        if (PendantTools.isEnabled(stack)) {
            if (entity instanceof Player) {
                return getCustomTexture(stack, entity, slot, type);
            }
            return SplendidPendants.modid + ":textures/blank_white.png";
        }
        return SplendidPendants.modid + ":textures/blank.png";
    }

    public String getCustomTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return SplendidPendants.modid + ":textures/blank.png";
    }

    @Nonnull
    @Override
    public InteractionResultHolder<ItemStack> use(@Nonnull Level worldIn, Player playerIn, @Nonnull InteractionHand handIn) {
        ItemStack stack = playerIn.getItemInHand(handIn);
        if (playerIn.isCrouching()) {
            customClickActions(worldIn, playerIn, handIn, stack);
        } else {
            PendantTools.setEnabled(stack, !PendantTools.isEnabled(stack));
        }
        return InteractionResultHolder.success(stack);
    }

    public void customClickActions(Level world, Player player, InteractionHand hand, ItemStack stack) {
        MenuProvider containerProvider = getContainerProvider(world, player, hand, stack);
        if (containerProvider != null) {
            player.openMenu(containerProvider);
        }
    }

    public MenuProvider getContainerProvider(Level world, Player playerEntity, InteractionHand hand, ItemStack stack) {
        return new SimpleMenuProvider(
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
    public void appendHoverText(@Nonnull ItemStack stack, Level worldIn, @Nonnull List<Component> tooltip, @Nonnull TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslatableComponent("msg.splendidpendants.divider"));
        tooltip.add(new TranslatableComponent("msg.splendidpendants.use_instructions").withStyle(ChatFormatting.GRAY));
        tooltip.add(new TranslatableComponent("msg.splendidpendants.divider"));
        tooltip.add(new TranslatableComponent("msg.splendidpendants.is_enabled").withStyle(ChatFormatting.GRAY));
        if (PendantTools.isEnabled(stack)) {
            tooltip.add(new TranslatableComponent("msg.splendidpendants.enabled").withStyle(ChatFormatting.GREEN, ChatFormatting.BOLD));
        } else {
            tooltip.add(new TranslatableComponent("msg.splendidpendants.disabled").withStyle(ChatFormatting.RED, ChatFormatting.BOLD));
        }
        tooltip.add(new TranslatableComponent("msg.splendidpendants.divider"));
        if (isDyeable()) {
            tooltip.add(new TranslatableComponent("msg.splendidpendants.dyeable_sneak_use_instructions").withStyle(ChatFormatting.GRAY));
            tooltip.add(new TranslatableComponent("msg.splendidpendants.divider"));
        }
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, CompoundTag nbt) {
        if (Mods.CURIOS.isLoaded()) {
            return CuriosCompat.initPendantCapabilities(stack);
        }
        return super.initCapabilities(stack, nbt);
    }
}
