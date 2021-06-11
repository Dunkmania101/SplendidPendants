package dunkmania101.splendidpendants.objects.containers;

import dunkmania101.splendidpendants.SplendidPendants;
import dunkmania101.splendidpendants.data.CustomValues;
import dunkmania101.splendidpendants.init.ContainerInit;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class LocketContainer extends PendantContainer {
    public LocketContainer(int id, PlayerInventory playerInventory, ItemStack stack) {
        super(ContainerInit.LOCKET_CONTAINER, id, playerInventory, stack, CustomValues.locketSize, false);
    }

    public LocketContainer(final int windowId, final PlayerInventory playerInventory, final PacketBuffer ignored) {
        this(windowId, playerInventory, playerInventory.getCurrentItem());
    }

    @Override
    protected Identifier getSlotBackground() {
        return new Identifier(SplendidPendants.modid, "gui/pendant_slot");
    }
}
