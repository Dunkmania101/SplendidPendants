package dunkmania101.splendidpendants.data;

import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;

public class CustomInventory implements ICustomInventory {
    protected DefaultedList<ItemStack> STACKS = DefaultedList.ofSize(1, ItemStack.EMPTY);

    public CustomInventory(int size) {
        this.STACKS = DefaultedList.ofSize(size, ItemStack.EMPTY);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return this.STACKS;
    }
}
