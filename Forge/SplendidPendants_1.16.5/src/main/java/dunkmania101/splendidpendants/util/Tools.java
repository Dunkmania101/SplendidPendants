package dunkmania101.splendidpendants.util;

import dunkmania101.splendidpendants.data.CustomValues;
import dunkmania101.splendidpendants.objects.items.DyeSpongeItem;
import dunkmania101.splendidpendants.objects.items.LocketItem;
import dunkmania101.splendidpendants.objects.items.PendantItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.DyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.items.ItemStackHandler;

public class Tools {
    public static ItemStackHandler getItemStackHandlerOfStack(ItemStack stack, int size, boolean isDyeable) {
        ItemStackHandler stackHandler = new ItemStackHandler(size) {
            @Override
            public int getSlotLimit(int slot) {
                return 1;
            }

            @Override
            public boolean isItemValid(int slot, ItemStack questionStack) {
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
                return super.isItemValid(slot, stack);
            }
        };
        if (stack.getItem() instanceof PendantItem) {
            stackHandler.deserializeNBT(stack.getOrCreateTag().getCompound(CustomValues.storedItemStacksKey));
        }
        return stackHandler;
    }

    public static void saveItemStackHandlerOfStack(ItemStack stack, ItemStackHandler stackHandler) {
        CompoundNBT stacksNBT = stackHandler.serializeNBT();
        CompoundNBT stackNBT = stack.getOrCreateTag();
        if (stackNBT.getCompound(CustomValues.storedItemStacksKey) != stacksNBT) {
            stackNBT.put(CustomValues.storedItemStacksKey, stacksNBT);
        }
    }

//    public static String translateString(String key) {
//        return new TranslationTextComponent(key).getString();
//    }

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

    // From CuriosAPI
    @OnlyIn(Dist.CLIENT)
    @SafeVarargs
    public static void followBodyRotations(final LivingEntity livingEntity, final BipedModel<LivingEntity>... models) {
        EntityRenderer<? super LivingEntity> render = Minecraft.getInstance().getEntityRenderDispatcher().getRenderer(livingEntity);
        if (render instanceof LivingRenderer) {
            @SuppressWarnings("unchecked") LivingRenderer<LivingEntity, EntityModel<LivingEntity>> livingRenderer = (LivingRenderer<LivingEntity, EntityModel<LivingEntity>>) render;
            EntityModel<LivingEntity> entityModel = livingRenderer.getModel();
            if (entityModel instanceof BipedModel) {
                for (BipedModel<LivingEntity> model : models) {
                    BipedModel<LivingEntity> bipedModel = (BipedModel<LivingEntity>) entityModel;
                    bipedModel.copyPropertiesTo(model);
                }
            }
        }
    }
}
