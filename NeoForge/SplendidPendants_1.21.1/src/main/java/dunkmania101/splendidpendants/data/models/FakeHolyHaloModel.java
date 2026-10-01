package dunkmania101.splendidpendants.data.models;

import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FakeHolyHaloModel extends HolyHaloModel {
    public FakeHolyHaloModel(DyeColor color) {
        super(ItemStack.EMPTY, color);
    }
}
