package dunkmania101.splendidpendants.events;

import dunkmania101.splendidpendants.SplendidPendants;
import dunkmania101.splendidpendants.data.CustomValues;
import dunkmania101.splendidpendants.init.ItemInit;
import dunkmania101.splendidpendants.util.PendantTools;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.CriticalHitEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

@EventBusSubscriber(modid = SplendidPendants.modid)
public class PlayerEvents {
    @SubscribeEvent
    public static void onLivingUpdate(EntityTickEvent.Post event) {
        if (event.getEntity() instanceof Player player) {
            PendantTools.runPendants(player);
        }
    }

    // @SubscribeEvent
    // public static void onPlayerAttack(AttackEntityEvent event) {
    //     Entity target = event.getTarget();
    //     if (target instanceof LivingEntity) {
    //         PendantTools.runPlayerAttack(event.getEntity(), (LivingEntity) target);
    //     }
    // }

    @SubscribeEvent
    public static void loggedOut(PlayerEvent.PlayerLoggedOutEvent event) {
        Player player = event.getEntity();
        CompoundTag data = player.getPersistentData();
        if (!player.isCreative() && !player.isSpectator() && PendantTools.anyInventoryHasEnabledPendant(player, ItemInit.HOLY_PENDANT.get())) {
            if (!data.contains(CustomValues.isFlyingKey) && player.getAbilities().flying) {
                data.putString(CustomValues.isFlyingKey, "");
            } else if (data.contains(CustomValues.isFlyingKey)) {
                data.remove(CustomValues.isFlyingKey);
            }
        }
    }

    @SubscribeEvent
    public static void onCriticalHit(CriticalHitEvent event) {
        PendantTools.runCriticalAttack(event);
    }
}
