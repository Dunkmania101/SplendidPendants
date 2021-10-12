package dunkmania101.splendidpendants.events;

import dunkmania101.splendidpendants.SplendidPendants;
import dunkmania101.splendidpendants.data.CommonConfig;
import dunkmania101.splendidpendants.data.CustomValues;
import dunkmania101.splendidpendants.init.ItemInit;
import dunkmania101.splendidpendants.util.PendantTools;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.CriticalHitEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
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

    // @SubscribeEvent
    // public static void onPlayerAttack(AttackEntityEvent event) {
    // Entity target = event.getTarget();
    // if (target instanceof LivingEntity) {
    // PendantTools.runPlayerAttack(event.getPlayer(), (LivingEntity) target);
    // }
    // }

    @SubscribeEvent
    public static void loggedOut(PlayerEvent.PlayerLoggedOutEvent event) {
        PlayerEntity player = event.getPlayer();
        CompoundNBT data = player.getPersistentData();
        if (!player.isCreative() && !player.isSpectator()
                && PendantTools.anyInventoryHasEnabledPendant(player, ItemInit.HOLY_PENDANT.get())) {
            if (!data.contains(CustomValues.isFlyingKey) && player.abilities.flying) {
                data.putString(CustomValues.isFlyingKey, "");
            } else if (data.contains(CustomValues.isFlyingKey)) {
                data.remove(CustomValues.isFlyingKey);
            }
        }
    }

    @SubscribeEvent
    public static void onRenderPlayer(RenderPlayerEvent event) {
        PendantTools.runPendantModel(event);
    }

    @SubscribeEvent
    public static void onCriticalHit(CriticalHitEvent event) {
        PendantTools.runCriticalAttack(event);
    }

    @SubscribeEvent
    public static void breakSpeed(PlayerEvent.BreakSpeed event) {
        if (CommonConfig.HOLY_ENABLE_BREAK_SPEED.get()) {
            PlayerEntity player = event.getPlayer();
            if (!player.isOnGround()
                    && PendantTools.anyInventoryHasEnabledPendant(player, ItemInit.HOLY_PENDANT.get())) {
                float newSpeed = event.getNewSpeed();
                if (newSpeed < event.getOriginalSpeed() * 5F) {
                    event.setNewSpeed(newSpeed * 5F);
                }
            }
        }
    }
}
