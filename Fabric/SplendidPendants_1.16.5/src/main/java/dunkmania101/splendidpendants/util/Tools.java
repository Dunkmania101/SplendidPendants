package dunkmania101.splendidpendants.util;

import java.util.Set;

import dunkmania101.splendidpendants.data.CustomValues;
import dunkmania101.splendidpendants.data.ICustomInventory;
import dunkmania101.splendidpendants.data.CustomInventory;
import dunkmania101.splendidpendants.objects.items.DyeSpongeItem;
import dunkmania101.splendidpendants.objects.items.LocketItem;
import dunkmania101.splendidpendants.objects.items.PendantItem;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.DyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.TranslatableText;

public class Tools {
    public static ICustomInventory getInventoryOfStack(ItemStack stack, int size, boolean isDyeable) {
        CustomInventory inventory = new CustomInventory(size) {
            @Override
            public int getMaxCountPerStack() {
                return 1;
            }

            @Override
            public boolean isValid(int slot, ItemStack questionStack) {
                Item checkItem = questionStack.getItem();
                if (!(checkItem instanceof DyeItem || checkItem instanceof DyeSpongeItem) && isDyeable) {
                    return false;
                }
                if ((!(checkItem instanceof PendantItem) && !isDyeable) || checkItem instanceof LocketItem) {
                    return false;
                }
                return super.isValid(slot, stack) && !this.containsAny(Set.of(checkItem));
            }
        };

        if (stack.getItem() instanceof PendantItem) {
            Inventories.fromTag(stack.getOrCreateTag().getCompound(CustomValues.storedItemStacksKey), inventory.getItems());
        }
        return inventory;
    }

    public static void saveCustomInventoryToStack(ItemStack stack, ICustomInventory inventory) {
        CompoundTag stacksTag = Inventories.toTag(new CompoundTag(), inventory.getItems());
        CompoundTag stackTag = stack.getOrCreateTag();
        if (stackTag.getCompound(CustomValues.storedItemStacksKey) != stacksTag) {
            stackTag.put(CustomValues.storedItemStacksKey, stacksTag);
        }
    }

    public static String translateString(String key) {
        return new TranslatableText(key).getString();
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
