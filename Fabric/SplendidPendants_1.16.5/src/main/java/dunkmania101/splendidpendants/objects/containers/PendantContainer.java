package dunkmania101.splendidpendants.objects.containers;

import com.mojang.datafixers.util.Pair;
import dunkmania101.splendidpendants.SplendidPendants;
import dunkmania101.splendidpendants.data.ICustomInventory;
import dunkmania101.splendidpendants.util.Tools;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.Identifier;

public class PendantContainer extends ScreenHandler {
    protected final ItemStack stack;
    protected ICustomInventory customInventory;
    protected int thisSlot = -1;

    public PendantContainer(ScreenHandlerType<?> type, int id, PlayerInventory playerInventory, ItemStack stack, int size, boolean isDyeable) {
        super(type, id);

        this.stack = stack;
        this.customInventory = Tools.getInventoryOfStack(stack, size, isDyeable);
        drawSlots(playerInventory);
    }

    protected void drawSlots(PlayerInventory playerInventory) {
        int startX = 7;
        int startY = 17;
        int slotSizePlus2 = 18;

        // Main Inventory
        for (int x = 0; x < this.customInventory.size(); ++x) {
            int valueX = startX + (x * slotSizePlus2);
            if (x > 0) {
                valueX += 9 * x;
            }
            Slot slot = new Slot(this.customInventory, x, valueX, startY) {
                @Override
                public int getMaxItemCount() {
                    return 1;
                }

                @Environment(EnvType.CLIENT)
                @Override
                public Pair<Identifier, Identifier> getBackgroundSprite() {
                    return Pair.of(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE, getSlotBackground());
                }
            };
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
                public boolean canTakeItems(PlayerEntity playerEntity) {
                    return this.id != thisSlot;
                }
            });
            if (x == playerInventory.selectedSlot && ItemStack.areEqual(playerInventory.getStack(playerInventory.selectedSlot), this.stack)) {
                thisSlot = slot.id;
            }
        }
    }

    protected Identifier getSlotBackground() {
        return new Identifier(SplendidPendants.modid, "");
    }

    @Override
    public ItemStack transferSlot(PlayerEntity playerIn, int index) {
        Slot slot = this.getSlot(index);

        if (!slot.canTakeItems(playerIn)) {
            return slot.getStack();
        }

        if (index == thisSlot || !slot.hasStack()) {
            return ItemStack.EMPTY;
        }

        ItemStack stack = slot.getStack();
        ItemStack newStack = stack.copy();

        int containerSlots = this.customInventory.size();
        if (index < containerSlots) {
            if (!this.insertItem(stack, containerSlots, this.slots.size(), true)) {
                return ItemStack.EMPTY;
            }
            slot.markDirty();
        } else if (!this.insertItem(stack, 0, containerSlots, false)) {
            return ItemStack.EMPTY;
        }

        if (stack.isEmpty()) {
            slot.setStack(ItemStack.EMPTY);
        } else {
            slot.markDirty();
        }

        return slot.onTakeItem(playerIn, newStack);
    }

    @Override
    public void close(PlayerEntity playerIn) {
        super.close(playerIn);
        Tools.saveCustomInventoryToStack(this.stack, this.customInventory);
    }

    @Override
    public boolean canUse(PlayerEntity playerIn) {
        return true;
    }
}
