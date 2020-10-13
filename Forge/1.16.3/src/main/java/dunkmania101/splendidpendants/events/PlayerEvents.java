package dunkmania101.splendidpendants.events;

import dunkmania101.splendidpendants.SplendidPendants;
import dunkmania101.splendidpendants.util.PendantTools;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.CriticalHitEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SplendidPendants.modid, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerEvents {
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.START) {
            PlayerEntity player = event.player;
            PendantTools.checkPlayerPendants(player);
        }
    }

    @SubscribeEvent
    public static void onRenderPlayer(RenderPlayerEvent event) {
        PendantTools.runAtlanticModel(event);
    }

    @SubscribeEvent
    public static void onCriticalHit(CriticalHitEvent event) {
        PendantTools.runKnighthoodCritical(event);
    }
}
