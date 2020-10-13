package dunkmania101.splendidpendants.objects.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ShinyItem extends Item {
    public ShinyItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return true;
    }
}
