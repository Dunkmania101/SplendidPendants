package dunkmania101.splendidpendants.objects.items;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import dunkmania101.splendidpendants.SplendidPendants;
import dunkmania101.splendidpendants.data.models.BlankBipedModel;
import dunkmania101.splendidpendants.data.models.FakeHolyHaloModel;
import dunkmania101.splendidpendants.util.PendantTools;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderingRegistry.ModelProvider;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderingRegistry.TextureProvider;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class PendantItem extends ArmorItem implements ModelProvider, TextureProvider {
    public PendantItem(ArmorMaterial materialIn, Settings settingsIn) {
        super(materialIn, EquipmentSlot.CHEST, settingsIn);
    }

    // @Override
    // public boolean canEquip(ItemStack stack, EquipmentSlot armorType, Entity entity) {
    //     return checkEquipmentSlot(armorType);
    // }

    // public boolean checkEquipmentSlot(EquipmentSlot slot) {
    //     return slot != EquipmentSlot.MAINHAND
    //             && slot != EquipmentSlot.OFFHAND
    //             && slot != EquipmentSlot.HEAD
    //             && slot != EquipmentSlot.LEGS
    //             && slot != EquipmentSlot.FEET;
    // }

    @Environment(EnvType.CLIENT)
    @Override
    public BipedEntityModel<LivingEntity> getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, BipedEntityModel<LivingEntity> defaultModel) {
        if (PendantTools.isEnabled(itemStack)) {
            if (entityLiving instanceof PlayerEntity) {
                return getCustomModel(entityLiving, itemStack, armorSlot);
            }
            return new FakeHolyHaloModel(DyeColor.RED);
        }
        return new BlankBipedModel();
    }

    @Environment(EnvType.CLIENT)
    public BipedEntityModel<LivingEntity> getCustomModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot) {
        return new BlankBipedModel();
    }

    @Environment(EnvType.CLIENT)
    @Override
    public Identifier getArmorTexture(LivingEntity entity, ItemStack stack, EquipmentSlot slot, boolean secondLayer, @Nullable String suffix, Identifier defaultTexture) {
        if (PendantTools.isEnabled(stack)) {
            if (entity instanceof PlayerEntity) {
                return getCustomTexture(entity, stack, slot, secondLayer, suffix, defaultTexture);
            }
            return Identifier.tryParse(SplendidPendants.modid + ":textures/blank_white.png");
        }
        return Identifier.tryParse(SplendidPendants.modid + ":textures/blank.png");
    }

    public Identifier getCustomTexture(LivingEntity entity, ItemStack stack, EquipmentSlot slot, boolean secondLayer, @Nullable String suffix, Identifier defaultTexture) {
        return Identifier.tryParse(SplendidPendants.modid + ":textures/blank.png");
    }

    @Override
    public TypedActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack stack = playerIn.getStackInHand(handIn);
        if (playerIn.isSneaking()) {
            customClickActions(worldIn, playerIn, handIn, stack);
        } else {
            PendantTools.setEnabled(stack, !PendantTools.isEnabled(stack));
        }
        return TypedActionResult.success(stack);
    }

    public void customClickActions(World world, PlayerEntity player, Hand hand, ItemStack stack) {
    }

    @Override
    public boolean isDamageable() {
        return false;
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return PendantTools.isEnabled(stack);
    }

    @Override
    public void appendTooltip(ItemStack stack, World worldIn, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, worldIn, tooltip, context);
        tooltip.add(new TranslatableText("msg.splendidpendants.divider"));
        tooltip.add(new TranslatableText("msg.splendidpendants.use_instructions").formatted(Formatting.GRAY));
        tooltip.add(new TranslatableText("msg.splendidpendants.divider"));
        tooltip.add(new TranslatableText("msg.splendidpendants.is_enabled").formatted(Formatting.GRAY));
        if (PendantTools.isEnabled(stack)) {
            tooltip.add(new TranslatableText("msg.splendidpendants.enabled").formatted(Formatting.GREEN, Formatting.BOLD));
        } else {
            tooltip.add(new TranslatableText("msg.splendidpendants.disabled").formatted(Formatting.RED, Formatting.BOLD));
        }
        tooltip.add(new TranslatableText("msg.splendidpendants.divider"));
    }

    // @Override
    // public ICapabilityProvider initCapabilities(ItemStack stack, CompoundTag nbt) {
    //     if (Mods.CURIOS.isLoaded()) {
    //         return CuriosCompat.initPendantCapabilities(stack);
    //     }
    //     return super.initCapabilities(stack, nbt);
    // }
}
