package dunkmania101.splendidpendants.objects.containers.screens;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import dunkmania101.splendidpendants.objects.containers.PendantContainer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class PendantScreen extends ContainerScreen<PendantContainer> {
    public PendantScreen(PendantContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);

        this.width = 176;
        this.height = 125;
        this.leftPos = 0;
        this.topPos = 0;
    }

    @Override
    public void render(@Nonnull MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(stack);
        super.render(stack, mouseX, mouseY, partialTicks);
        this.renderTooltip(stack, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(@Nonnull MatrixStack matrixStack, int x, int y) {
    }

    @Override
    protected void renderBg(@Nonnull MatrixStack matrixStack, float ignored, int ignored1, int ignored2) {
        RenderSystem.blendColor(1.0F, 1.0F, 1.0F, 1.0F);
        this.getMinecraft().getTextureManager().bind(getBackgroundTexture());
        int x = (this.width - this.getXSize()) / 2;
        int y = (this.height - this.getYSize()) / 2;
        blit(matrixStack, x, y, 0, 0, getXSize(), getYSize());
    }

    protected ResourceLocation getBackgroundTexture() {
        return BACKGROUND_LOCATION;
    }
}
