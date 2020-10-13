package dunkmania101.splendidpendants.util;

import dunkmania101.splendidpendants.data.CustomValues;
import dunkmania101.splendidpendants.objects.items.LocketItem;
import dunkmania101.splendidpendants.objects.items.PendantItem;
import net.minecraft.item.DyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.items.ItemStackHandler;

public class Tools {
    public static ItemStackHandler getItemStackHandlerOfStack(ItemStack stack, int size, boolean isDyeable) {
        ItemStackHandler stackHandler = new ItemStackHandler(size) {
            @Override
            public boolean isItemValid(int slot, ItemStack questionStack) {
                Item checkItem = questionStack.getItem();
                if (!(checkItem instanceof DyeItem) && isDyeable) {
                    return false;
                }
                if (!isDyeable && !(checkItem instanceof PendantItem) || checkItem instanceof LocketItem) {
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
}
