package dunkmania101.splendidpendants.objects.containers;

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
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public class PendantContainer extends Container {
    protected final ItemStack stack;
    protected ItemStackHandler itemStackHandler;
    protected int thisSlot = -1;

    public PendantContainer(ContainerType<?> type, int id, PlayerInventory playerInventory, ItemStack stack, int size, boolean isDyeable, boolean isAnyItem) {
        super(type, id);

        this.stack = stack;
        this.itemStackHandler = Tools.getItemStackHandlerOfStack(stack, size, isDyeable, isAnyItem);
        drawSlots(playerInventory);
    }

    public PendantContainer(ContainerType<?> type, int id, PlayerInventory playerInventory, ItemStack stack, int size, boolean isDyeable) {
        this(type, id, playerInventory, stack, size, isDyeable, false);
    }

    protected void drawSlots(PlayerInventory playerInventory) {
        int startX = 7;
        int startY = 17;
        int slotSizePlus2 = 18;

        // Main Inventory
        for (int x = 0; x < this.itemStackHandler.getSlots(); ++x) {
            int valueX = startX + (x * slotSizePlus2);
            if (x > 0) {
                valueX += 9 * x;
            }
            SlotItemHandler slot = new SlotItemHandler(this.itemStackHandler, x, valueX, startY) {
                @Override
                public int getMaxStackSize() {
                    return 1;
                }
            };
            if (getSlotBackground() != null) {
                slot.setBackground(PlayerContainer.BLOCK_ATLAS, getSlotBackground());
            }
            this.addSlot(slot);
        }

        // Main Player Inventory
        int startPlayerInvY = startY + slotSizePlus2 + 4;
        for (int row = 0; row < 3; ++row) {
            for (int column = 0; column < 9; ++column) {
                this.addSlot(new Slot(playerInventory, 9 + (row * 9) + column, startX + 1 + (column * slotSizePlus2), startPlayerInvY + (row * slotSizePlus2)));
            }
        }

        // HotBar
        int startPlayerHotBarY = startPlayerInvY + (slotSizePlus2 * 3) + 4;
        for (int x = 0; x < 9; ++x) {
            Slot slot = addSlot(new Slot(playerInventory, x, startX + 1 + (x * slotSizePlus2), startPlayerHotBarY) {
                @Override
                public boolean mayPickup(@Nonnull PlayerEntity playerIn) {
                    return index != thisSlot;
                }
            });
            if (x == playerInventory.selected && ItemStack.isSame(playerInventory.getSelected(), this.stack)) {
                thisSlot = slot.index;
            }
        }
    }

    protected ResourceLocation getSlotBackground() {
        return new ResourceLocation(SplendidPendants.modid, "");
    }

    @Nonnull
    @Override
    public ItemStack quickMoveStack(@Nonnull PlayerEntity playerIn, int index) {
        Slot slot = this.getSlot(index);

        if (!slot.mayPickup(playerIn)) {
            return slot.getItem();
        }

        if (index == thisSlot || !slot.hasItem()) {
            return ItemStack.EMPTY;
        }

        ItemStack stack = slot.getItem();
        ItemStack newStack = stack.copy();

        int containerSlots = this.itemStackHandler.getSlots();
        if (index < containerSlots) {
            if (!this.moveItemStackTo(stack, containerSlots, this.slots.size(), true)) {
                return ItemStack.EMPTY;
            }
            slot.setChanged();
        } else if (!this.moveItemStackTo(stack, 0, containerSlots, false)) {
            return ItemStack.EMPTY;
        }

        if (stack.isEmpty()) {
            slot.set(ItemStack.EMPTY);
        } else {
            slot.setChanged();
        }

        return slot.onTake(playerIn, newStack);
    }

    @Override
    public boolean stillValid(@Nonnull PlayerEntity playerIn) {
        return true;
    }
}
