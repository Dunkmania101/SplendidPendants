package dunkmania101.splendidpendants.objects.containers.screens;

import dunkmania101.splendidpendants.SplendidPendants;
import dunkmania101.splendidpendants.objects.containers.PendantContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LocketScreen extends PendantScreen {
    public LocketScreen(PendantContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
    }

    @Override
    protected ResourceLocation getBackgroundTexture() {
        return new ResourceLocation(SplendidPendants.modid, "textures/gui/locket_screen.png");
    }
}
