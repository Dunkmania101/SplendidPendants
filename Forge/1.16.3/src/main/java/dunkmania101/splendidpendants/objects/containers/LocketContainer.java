package dunkmania101.splendidpendants.objects.containers;

import dunkmania101.splendidpendants.SplendidPendants;
import dunkmania101.splendidpendants.data.CustomValues;
import dunkmania101.splendidpendants.init.ContainerInit;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;

public class LocketContainer extends PendantContainer {
    public LocketContainer(int id, PlayerInventory playerInventory, ItemStack stack) {
        super(ContainerInit.LOCKET_CONTAINER.get(), id, playerInventory, stack, CustomValues.locketSize, false);
    }

    public LocketContainer(final int windowId, final PlayerInventory playerInventory, final PacketBuffer data) {
        this(windowId, playerInventory, playerInventory.getCurrentItem());
    }

    @Override
    protected ResourceLocation getSlotBackground() {
        return new ResourceLocation(SplendidPendants.modid, "gui/pendant_slot");
    }
}
