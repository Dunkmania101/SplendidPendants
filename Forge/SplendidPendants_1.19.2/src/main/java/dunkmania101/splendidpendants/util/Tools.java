package dunkmania101.splendidpendants.util;

import javax.annotation.Nonnull;

import dunkmania101.splendidpendants.data.CustomValues;
import dunkmania101.splendidpendants.objects.items.DyeSpongeItem;
import dunkmania101.splendidpendants.objects.items.LocketItem;
import dunkmania101.splendidpendants.objects.items.PendantItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.items.ItemStackHandler;

public class Tools {
    public static ItemStackHandler getItemStackHandlerOfStack(ItemStack stack, int size, boolean isDyeable,
            boolean isAnyItem) {
        String key;
        if (isAnyItem) {
            key = CustomValues.heldItemStacksKey;
        } else if (isDyeable) {
            key = CustomValues.storedDyeStacksKey;
        } else {
            key = CustomValues.storedItemStacksKey;
        }
        ItemStackHandler stackHandler = new ItemStackHandler(size) {
            @Override
            public int getSlotLimit(int slot) {
                return 1;
            }

            @Override
            protected void onContentsChanged(int slot) {
                super.onContentsChanged(slot);
                saveItemStackHandlerToStack(stack, this, key);
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack questionStack) {
                if (!isAnyItem) {
                    Item checkItem = questionStack.getItem();
                    if (!(checkItem instanceof DyeItem || checkItem instanceof DyeSpongeItem) && isDyeable) {
                        return false;
                    }
                    if ((!(checkItem instanceof PendantItem) && !isDyeable) || checkItem instanceof LocketItem) {
                        return false;
                    }
                    for (ItemStack checkStack : this.stacks) {
                        if (checkStack.getItem() == checkItem) {
                            return false;
                        }
                    }
                }
                return super.isItemValid(slot, stack);
            }
        };
        if (stack.getItem() instanceof PendantItem) {
            stackHandler.deserializeNBT(stack.getOrCreateTag().getCompound(key));
        }
        return stackHandler;
    }

    public static ItemStackHandler getItemStackHandlerOfStack(ItemStack stack, int size, boolean isDyeable) {
        return getItemStackHandlerOfStack(stack, size, isDyeable, false);
    }

    public static void saveItemStackHandlerToStack(ItemStack stack, ItemStackHandler stackHandler, String key) {
        CompoundTag stacksNBT = stackHandler.serializeNBT();
        CompoundTag stackNBT = stack.getOrCreateTag();
        if (stackNBT.getCompound(key) != stacksNBT) {
            stackNBT.put(key, stacksNBT);
        }
    }

    public static void saveItemStackHandlerToStack(ItemStack stack, ItemStackHandler stackHandler) {
        saveItemStackHandlerToStack(stack, stackHandler, CustomValues.storedItemStacksKey);
    }

    // public static String translateString(String key) {
    // return new TranslationTextComponent(key).getString();
    // }

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
                    HumanoidModel<LivingEntity> bipedModel = (HumanoidModel<LivingEntity>) entityModel;
                    bipedModel.copyPropertiesTo(model);
                }
            }
        }
    }
}
