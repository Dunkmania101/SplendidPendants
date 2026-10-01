package dunkmania101.splendidpendants.events;

import java.util.List;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import dunkmania101.splendidpendants.SplendidPendants;
import dunkmania101.splendidpendants.data.render.PendantArmorLayer;
import dunkmania101.splendidpendants.init.ContainerInit;
import dunkmania101.splendidpendants.objects.containers.screens.DyeableScreen;
import dunkmania101.splendidpendants.objects.containers.screens.HoldingScreen;
import dunkmania101.splendidpendants.objects.containers.screens.LocketScreen;
import dunkmania101.splendidpendants.util.PendantTools;
import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.resources.PlayerSkin;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.event.RenderPlayerEvent;

@EventBusSubscriber(modid = SplendidPendants.modid, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {
    private static final Logger LOGGER = LogUtils.getLogger();

    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(ContainerInit.LOCKET_CONTAINER.get(), LocketScreen::new);
        event.register(ContainerInit.DYEABLE_CONTAINER.get(), DyeableScreen::new);
        event.register(ContainerInit.HOLDING_CONTAINER.get(), HoldingScreen::new);
    }

    @SubscribeEvent
    public static void replaceArmorLayer(EntityRenderersEvent.AddLayers event) {
        for (PlayerSkin.Model skinModel : event.getSkins()) {
            PlayerRenderer renderer = event.getSkin(skinModel);
            if (renderer == null) {
                continue;
            }
            boolean slim = skinModel == PlayerSkin.Model.SLIM;
            EntityRendererProvider.Context context = event.getContext();
            PendantArmorLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>, HumanoidArmorModel<AbstractClientPlayer>> replacement = new PendantArmorLayer<>(
                    renderer,
                    new HumanoidArmorModel<>(context.bakeLayer(slim ? ModelLayers.PLAYER_SLIM_INNER_ARMOR : ModelLayers.PLAYER_INNER_ARMOR)),
                    new HumanoidArmorModel<>(context.bakeLayer(slim ? ModelLayers.PLAYER_SLIM_OUTER_ARMOR : ModelLayers.PLAYER_OUTER_ARMOR)),
                    context.getModelManager());

            List<RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>>> layers = renderer.layers;
            boolean replaced = false;
            for (int i = 0; i < layers.size(); i++) {
                if (layers.get(i).getClass() == HumanoidArmorLayer.class) {
                    layers.set(i, replacement);
                    replaced = true;
                }
            }
            if (!replaced) {
                LOGGER.debug("No vanilla armor layer on the {} player renderer; leg armor will stay visible with the atlantic tail.", skinModel);
            }
        }
    }

    @EventBusSubscriber(modid = SplendidPendants.modid, value = Dist.CLIENT)
    public static class Game {
        @SubscribeEvent(priority = EventPriority.LOWEST)
        public static void onRenderPlayer(RenderPlayerEvent.Pre event) {
            PendantTools.runPendantModel(event);
        }
    }
}
