package dunkmania101.splendidpendants.objects.containers.screens;

import javax.annotation.Nonnull;

import dunkmania101.splendidpendants.objects.containers.PendantContainer;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PendantScreen extends AbstractContainerScreen<PendantContainer> {
    public PendantScreen(PendantContainer screenContainer, Inventory inv, Component titleIn) {
        super(screenContainer, inv, titleIn);

        this.width = 176;
        this.height = 121;
        this.leftPos = 0;
        this.topPos = 0;
    }

    @Override
    public void render(@Nonnull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(@Nonnull GuiGraphics guiGraphics, int x, int y) {
    }

    @Override
    protected void renderBg(@Nonnull GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        guiGraphics.blit(getBackgroundTexture(), this.leftPos, this.topPos, 0, 0, getXSize(), getYSize());
    }

    protected ResourceLocation getBackgroundTexture() {
        return INVENTORY_LOCATION;
    }
}
