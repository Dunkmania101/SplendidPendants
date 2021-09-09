package dunkmania101.splendidpendants.objects.containers;

import dunkmania101.splendidpendants.SplendidPendants;
import dunkmania101.splendidpendants.data.CustomValues;
import dunkmania101.splendidpendants.init.ContainerInit;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

public class DyeableContainer extends PendantContainer {
    public DyeableContainer(int id, Inventory playerInventory, ItemStack stack) {
        super(ContainerInit.DYEABLE_CONTAINER.get(), id, playerInventory, stack, CustomValues.dyeableSize, true);
    }

    public DyeableContainer(final int windowId, final Inventory playerInventory, final FriendlyByteBuf ignored) {
        this(windowId, playerInventory, playerInventory.getSelected());
    }

    @Override
    protected ResourceLocation getSlotBackground() {
        return new ResourceLocation(SplendidPendants.modid, "gui/dye_slot");
    }
}
