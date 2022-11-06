package dunkmania101.splendidpendants.objects.containers.screens;

import dunkmania101.splendidpendants.SplendidPendants;
import dunkmania101.splendidpendants.objects.containers.PendantContainer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LocketScreen extends PendantScreen {
    public LocketScreen(PendantContainer screenContainer, Inventory inv, Component titleIn) {
        super(screenContainer, inv, titleIn);
    }

    @Override
    protected ResourceLocation getBackgroundTexture() {
        return new ResourceLocation(SplendidPendants.modid, "textures/gui/locket_screen.png");
    }
}
