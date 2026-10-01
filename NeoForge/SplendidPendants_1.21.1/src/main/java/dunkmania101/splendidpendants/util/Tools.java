package dunkmania101.splendidpendants.util;

import javax.annotation.Nonnull;

import dunkmania101.splendidpendants.data.CustomValues;
import dunkmania101.splendidpendants.init.ComponentInit;
import dunkmania101.splendidpendants.objects.items.DyeSpongeItem;
import dunkmania101.splendidpendants.objects.items.LocketItem;
import dunkmania101.splendidpendants.objects.items.PendantItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.items.ComponentItemHandler;

public class Tools {
    public static ComponentItemHandler getItemStackHandlerOfStack(ItemStack stack, int size, boolean isDyeable,
            boolean isAnyItem) {
        DataComponentType<ItemContainerContents> component;
        if (isAnyItem) {
            component = ComponentInit.HELD_ITEMS.get();
        } else if (isDyeable) {
            component = ComponentInit.STORED_DYES.get();
        } else {
            component = ComponentInit.STORED_PENDANTS.get();
        }
        return new ComponentItemHandler(stack, component, size) {
            @Override
            public int getSlotLimit(int slot) {
                return 1;
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack questionStack) {
                if (questionStack.isEmpty()) {
                    return true;
                }
                if (!isAnyItem) {
                    Item checkItem = questionStack.getItem();
                    if (!(checkItem instanceof DyeItem || checkItem instanceof DyeSpongeItem) && isDyeable) {
                        return false;
                    }
                    if ((!(checkItem instanceof PendantItem) && !isDyeable) || checkItem instanceof LocketItem) {
                        return false;
                    }
                    for (int i = 0; i < getSlots(); i++) {
                        if (i != slot && getStackInSlot(i).getItem() == checkItem) {
                            return false;
                        }
                    }
                }
                return true;
            }
        };
    }

    public static ComponentItemHandler getItemStackHandlerOfStack(ItemStack stack, int size, boolean isDyeable) {
        return getItemStackHandlerOfStack(stack, size, isDyeable, false);
    }

    public static int getAlpha(int packedColor) {
        return packedColor >>> 24 & 255;
    }

    public static int getRed(int packedColor) {
        return packedColor >> 16 & 255;
    }

    public static int getGreen(int packedColor) {
        return packedColor >> 8 & 255;
    }

    public static int getBlue(int packedColor) {
        return packedColor & 255;
    }

    public static int packColor(int alpha, int red, int green, int blue) {
        return alpha << 24 | red << 16 | green << 8 | blue;
    }

    public static int blendColors(int color1, int color2) {
        return color1 * color2 / 255;
    }

    public static float blendColors(float color1, float color2) {
        return color1 * color2;
    }

    public static int multiplyColors(int color1, int color2) {
        return packColor(getAlpha(color2),
                blendColors(getRed(color1), getRed(color2)),
                blendColors(getGreen(color1), getGreen(color2)),
                blendColors(getBlue(color1), getBlue(color2)));
    }

    public static int scaleColor(int packedColor, float redFactor, float greenFactor, float blueFactor) {
        return packColor(getAlpha(packedColor),
                (int) (getRed(packedColor) * redFactor),
                (int) (getGreen(packedColor) * greenFactor),
                (int) (getBlue(packedColor) * blueFactor));
    }

    public static int getDyedColor(ItemStack stack, int defaultColor) {
        ComponentItemHandler itemStackHandler = getItemStackHandlerOfStack(stack, CustomValues.dyeableSize, true);
        if (itemStackHandler.getSlots() > 0) {
            ItemStack storedStack = itemStackHandler.getStackInSlot(0);
            Item storedDye = storedStack.getItem();
            if (storedDye instanceof DyeItem dyeItem) {
                return dyeItem.getDyeColor().getTextureDiffuseColor();
            } else if (storedDye instanceof DyeSpongeItem spongeItem) {
                int colorInt = spongeItem.getColor(storedStack);
                return packColor(255, getRed(colorInt), getGreen(colorInt), getBlue(colorInt));
            }
        }
        return defaultColor;
    }

    // Copied from CuriosAPI
    @OnlyIn(Dist.CLIENT)
    @SafeVarargs
    public static void followBodyRotations(final LivingEntity livingEntity,
            final HumanoidModel<LivingEntity>... models) {
        EntityRenderer<? super LivingEntity> render = Minecraft.getInstance().getEntityRenderDispatcher()
                .getRenderer(livingEntity);
        if (render instanceof LivingEntityRenderer) {
            @SuppressWarnings("unchecked")
            LivingEntityRenderer<LivingEntity, EntityModel<LivingEntity>> livingRenderer = (LivingEntityRenderer<LivingEntity, EntityModel<LivingEntity>>) render;
            EntityModel<LivingEntity> entityModel = livingRenderer.getModel();
            if (entityModel instanceof HumanoidModel) {
                for (HumanoidModel<LivingEntity> model : models) {
                    @SuppressWarnings("unchecked")
                    HumanoidModel<LivingEntity> bipedModel = (HumanoidModel<LivingEntity>) entityModel;
                    bipedModel.copyPropertiesTo(model);
                }
            }
        }
    }
}
