package dunkmania101.splendidpendants.events;

import dunkmania101.splendidpendants.SplendidPendants;
import dunkmania101.splendidpendants.util.PendantTools;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.CriticalHitEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SplendidPendants.modid, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerEvents {
    @SubscribeEvent
    public static void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
        LivingEntity livingEntity = event.getEntityLiving();
        if (livingEntity instanceof PlayerEntity) {
            PendantTools.runPendants((PlayerEntity) livingEntity);
        }
    }

    @SubscribeEvent
    public static void onRenderPlayer(RenderPlayerEvent event) {
        PendantTools.runPendantModel(event);
    }

    @SubscribeEvent
    public static void onCriticalHit(CriticalHitEvent event) {
        PendantTools.runKnighthoodCritical(event);
    }
}
