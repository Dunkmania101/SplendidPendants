package dunkmania101.splendidpendants.objects.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class ShinyItem extends Item {
    public ShinyItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isFoil(@Nonnull ItemStack stack) {
        return true;
    }
}
