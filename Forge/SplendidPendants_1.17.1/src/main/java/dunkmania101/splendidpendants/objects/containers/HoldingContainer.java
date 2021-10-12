package dunkmania101.splendidpendants.objects.containers;

import dunkmania101.splendidpendants.data.CustomValues;
import dunkmania101.splendidpendants.init.ContainerInit;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;

public class HoldingContainer extends PendantContainer {
    public HoldingContainer(int id, PlayerInventory playerInventory, ItemStack stack) {
        super(ContainerInit.HOLDING_CONTAINER.get(), id, playerInventory, stack, CustomValues.holdingSize, false, true);
    }

    public HoldingContainer(final int windowId, final PlayerInventory playerInventory, final PacketBuffer ignored) {
        this(windowId, playerInventory, playerInventory.getSelected());
    }

    @Override
    protected ResourceLocation getSlotBackground() {
        return null;
    }
}
