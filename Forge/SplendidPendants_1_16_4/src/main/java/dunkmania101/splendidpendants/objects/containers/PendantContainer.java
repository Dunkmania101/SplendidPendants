package dunkmania101.splendidpendants.objects.containers;

import com.mojang.datafixers.util.Pair;
import dunkmania101.splendidpendants.SplendidPendants;
import dunkmania101.splendidpendants.util.Tools;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public class PendantContainer extends Container {
    protected final ItemStack stack;
    protected ItemStackHandler itemStackHandler;
    protected int thisSlot = -1;

    public PendantContainer(ContainerType<?> type, int id, PlayerInventory playerInventory, ItemStack stack, int size, boolean isDyeable) {
        super(type, id);

        this.stack = stack;
        this.itemStackHandler = Tools.getItemStackHandlerOfStack(stack, size, isDyeable);
        drawSlots(playerInventory);
    }

    protected void drawSlots(PlayerInventory playerInventory) {
        int startX = 7;
        int startY = 17;
        int slotSizePlus2 = 18;

        // Main Inventory
        for (int x = 0; x < this.itemStackHandler.getSlots(); ++x) {
            int valueX = startX + (x * slotSizePlus2);
            if (x > 0) {
                valueX += 8 * x;
            }
            SlotItemHandler slot = new SlotItemHandler(this.itemStackHandler, x, valueX, startY) {
                public int getSlotStackLimit() {
                    return 1;
                }

                @OnlyIn(Dist.CLIENT)
                @Override
                public Pair<ResourceLocation, ResourceLocation> getBackground() {
                    return Pair.of(PlayerContainer.LOCATION_BLOCKS_TEXTURE, getSlotBackground());
                }
            };
            this.addSlot(slot);
        }

        // Main Player Inventory
        int startPlayerInvY = startY + slotSizePlus2 + 4;
        for (int row = 0; row < 3; ++row) {
            for (int column = 0; column < 9; ++column) {
                this.addSlot(new Slot(playerInventory, 9 + (row * 9) + column, startX + 2 + (column * slotSizePlus2), startPlayerInvY + (row * slotSizePlus2)));
            }
        }

        // HotBar
        int startPlayerHotBarY = startPlayerInvY + (slotSizePlus2 * 3) + 4;
        for (int x = 0; x < 9; ++x) {
            Slot slot = addSlot(new Slot(playerInventory, x, 8 + (x * slotSizePlus2), startPlayerHotBarY) {
                @Override
                public boolean canTakeStack(@Nonnull PlayerEntity playerIn) {
                    return slotNumber != thisSlot;
                }
            });
            if (x == playerInventory.currentItem && ItemStack.areItemStacksEqual(playerInventory.getCurrentItem(), this.stack)) {
                thisSlot = slot.slotNumber;
            }
        }
    }

    protected ResourceLocation getSlotBackground() {
        return new ResourceLocation(SplendidPendants.modid, "");
    }

    @Nonnull
    @Override
    public ItemStack transferStackInSlot(@Nonnull PlayerEntity playerIn, int index) {
        Slot slot = this.getSlot(index);

        if (!slot.canTakeStack(playerIn)) {
            return slot.getStack();
        }

        if (index == thisSlot || !slot.getHasStack()) {
            return ItemStack.EMPTY;
        }

        ItemStack stack = slot.getStack();
        ItemStack newStack = stack.copy();

        int containerSlots = this.itemStackHandler.getSlots();
        if (index < containerSlots) {
            if (!this.mergeItemStack(stack, containerSlots, this.inventorySlots.size(), true)) {
                return ItemStack.EMPTY;
            }
            slot.onSlotChanged();
        } else if (!this.mergeItemStack(stack, 0, containerSlots, false)) {
            return ItemStack.EMPTY;
        }

        if (stack.isEmpty()) {
            slot.putStack(ItemStack.EMPTY);
        } else {
            slot.onSlotChanged();
        }

        return slot.onTake(playerIn, newStack);
    }

    @Override
    public void onContainerClosed(@Nonnull PlayerEntity playerIn) {
        super.onContainerClosed(playerIn);
        Tools.saveItemStackHandlerOfStack(this.stack, this.itemStackHandler);
    }

    @Override
    public boolean canInteractWith(@Nonnull PlayerEntity playerIn) {
        return true;
    }
}
