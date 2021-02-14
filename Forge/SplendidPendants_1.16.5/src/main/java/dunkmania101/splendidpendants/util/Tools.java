package dunkmania101.splendidpendants.util;

import dunkmania101.splendidpendants.data.CustomValues;
import dunkmania101.splendidpendants.objects.items.DyeSpongeItem;
import dunkmania101.splendidpendants.objects.items.LocketItem;
import dunkmania101.splendidpendants.objects.items.PendantItem;
import net.minecraft.item.DyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.TranslationTextComponent;
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

    public static String translateString(String key) {
        return new TranslationTextComponent(key).getString();
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
}
