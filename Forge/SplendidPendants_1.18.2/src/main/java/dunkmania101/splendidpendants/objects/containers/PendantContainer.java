package dunkmania101.splendidpendants.objects.containers;

import javax.annotation.Nonnull;

import dunkmania101.splendidpendants.util.Tools;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class PendantContainer extends AbstractContainerMenu {
    protected final ItemStack stack;
    protected ItemStackHandler itemStackHandler;
    protected int thisSlot = -1;

    public PendantContainer(MenuType<?> type, int id, Inventory playerInventory, ItemStack stack, int size, boolean isDyeable, boolean isAnyItem) {
        super(type, id);

        this.stack = stack;
        this.itemStackHandler = Tools.getItemStackHandlerOfStack(stack, size, isDyeable, isAnyItem);
        drawSlots(playerInventory);
    }

    protected void drawSlots(Inventory playerInventory) {
        int startX = 7;
        int startY = 17;
        int slotSizePlus2 = 18;

        // Main Inventory
        for (int x = 0; x < this.itemStackHandler.getSlots(); ++x) {
            int valueX = startX + (x * slotSizePlus2);
            if (x > 0) {
                valueX += (8 * x) + 1;
            }
            SlotItemHandler slot = new SlotItemHandler(this.itemStackHandler, x, valueX, startY) {
                @Override
                public int getMaxStackSize() {
                    return 1;
                }
            };
            if (getSlotBackground() != null) {
                slot.setBackground(InventoryMenu.BLOCK_ATLAS, getSlotBackground());
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
                public boolean mayPickup(@Nonnull Player playerIn) {
                    return index != thisSlot;
                }
            });
            if (x == playerInventory.selected && ItemStack.isSame(playerInventory.getSelected(), this.stack)) {
                thisSlot = slot.index;
            }
        }
    }

    protected ResourceLocation getSlotBackground() {
        return null;
    }

    @Nonnull
    @Override
    public ItemStack quickMoveStack(@Nonnull Player playerIn, int index) {
        Slot slot = this.getSlot(index);

        if (!slot.mayPickup(playerIn)) {
            return slot.getItem();
        }

        if (index == thisSlot || !slot.hasItem()) {
            return ItemStack.EMPTY;
        }

        ItemStack stack = slot.getItem();

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

        return slot.getItem();
    }

    @Override
    public boolean stillValid(@Nonnull Player playerIn) {
        return true;
    }
}
