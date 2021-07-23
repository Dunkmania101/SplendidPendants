package dunkmania101.splendidpendants.util;

import dunkmania101.splendidpendants.data.CommonConfig;
import dunkmania101.splendidpendants.data.CustomValues;
import dunkmania101.splendidpendants.data.compat.Mods;
import dunkmania101.splendidpendants.init.ItemInit;
import dunkmania101.splendidpendants.objects.items.*;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.entity.player.CriticalHitEvent;
import net.minecraftforge.items.ItemStackHandler;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public class PendantTools {
    public static void runPendants(PlayerEntity player) {
        CompoundNBT data = player.getPersistentData();
        if (inventoryHasEnabledPendant(player, ItemInit.ATLANTIC_PENDANT.get())) {
            if (!data.contains(CustomValues.hasAtlanticKey)) {
                data.putString(CustomValues.hasAtlanticKey, "");
            }
            runAtlantic(player);
        } else if (data.contains(CustomValues.hasAtlanticKey)) {
            resetAtlantic(player);
        }
        if (inventoryHasEnabledPendant(player, ItemInit.KNIGHTHOOD_PENDANT.get())) {
            if (!data.contains(CustomValues.hasKnighthoodKey)) {
                data.putString(CustomValues.hasKnighthoodKey, "");
            }
            runKnighthood(player);
        } else if (data.contains(CustomValues.hasKnighthoodKey)) {
            resetKnighthood(player);
        }
        if (inventoryHasEnabledPendant(player, ItemInit.HOLY_PENDANT.get())) {
            if (!data.contains(CustomValues.hasHolyKey)) {
                data.putString(CustomValues.hasHolyKey, "");
            }
            runHoly(player);
        } else if (data.contains(CustomValues.hasHolyKey)) {
            resetHoly(player);
        }
    }

    public static boolean inventoryHasEnabledPendant(PlayerEntity player, PendantItem pendant) {
        ArrayList<ItemStack> checkStacks = new ArrayList<>();
        checkStacks.add(player.inventory.armor.get(EquipmentSlotType.CHEST.getIndex()));
        if (Mods.CURIOS.isLoaded()) {
            Optional<ImmutableTriple<String, Integer, ItemStack>> optionalStack = CuriosApi.getCuriosHelper().findEquippedCurio(pendant, player);
            if (optionalStack.isPresent()) {
                checkStacks.add(optionalStack.get().right);
            } else {
                optionalStack = CuriosApi.getCuriosHelper().findEquippedCurio(ItemInit.LOCKET.get(), player);
                optionalStack.ifPresent(stackTriple -> checkStacks.add(stackTriple.right));
            }
        }
        for (ItemStack checkStack : checkStacks) {
            Item checkItem = checkStack.getItem();
            if (checkItem instanceof LocketItem) {
                if (isEnabled(checkStack)) {
                    ItemStackHandler itemHandler = Tools.getItemStackHandlerOfStack(checkStack, CustomValues.locketSize, false);
                    for (int slot = 0; slot < itemHandler.getSlots(); slot++) {
                        ItemStack pendantStack = itemHandler.getStackInSlot(slot);
                        if (pendantStack.getItem() instanceof PendantItem) {
                            PendantItem pendantItem = (PendantItem) pendantStack.getItem();
                            if (pendantItem == pendant) {
                                if (isEnabled(pendantStack)) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            } else if (checkItem instanceof PendantItem) {
                if (checkItem == pendant) {
                    if (isEnabled(checkStack)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean isEnabled(ItemStack stack) {
        if (stack != null) {
            CompoundNBT nbt = stack.getTag();
            if (nbt != null) {
                return nbt.getBoolean(CustomValues.enabledKey);
            }
        }
        return false;
    }

    public static void setEnabled(ItemStack stack, boolean enabled) {
        if (stack != null) {
            stack.getOrCreateTag().putBoolean(CustomValues.enabledKey, enabled);
        }
    }

    public static ItemStack getPrioritizedStoredStack(ItemStack thisStack, Entity entity) {
        ItemStack storedStack = ItemStack.EMPTY;
        ItemStackHandler itemStackHandler = Tools.getItemStackHandlerOfStack(thisStack, CustomValues.locketSize, false);
        for (int i = 0; i < itemStackHandler.getSlots(); i++) {
            ItemStack checkStack = itemStackHandler.getStackInSlot(i);
            Item checkItem = checkStack.getItem();
            Item storedItem = storedStack.getItem();
            if (
                    (checkItem instanceof AtlanticPendantItem && entity.isInWater())
                            || ((checkItem instanceof KnighthoodPendantItem && entity.getPersistentData().getInt(CustomValues.renderKnighthoodKey) > 0)
                            && !(storedItem instanceof AtlanticPendantItem && entity.isInWater()))
                            || ((checkItem instanceof HolyPendantItem && entity instanceof PlayerEntity && ((PlayerEntity) entity).abilities.flying)
                            && !((storedItem instanceof AtlanticPendantItem && entity.isInWater())
                            || (storedItem instanceof KnighthoodPendantItem && entity.getPersistentData().getInt(CustomValues.renderKnighthoodKey) > 0)))
            ) {
                storedStack = checkStack;
            }
        }
        return storedStack;
    }

    public static AttributeModifier genAttributeModifier(double additive, UUID uuid, String name) {
        return new AttributeModifier(uuid, name, additive, AttributeModifier.Operation.ADDITION);
    }

    public static void modifyPlayerAttribute(ModifiableAttributeInstance playerAttribute, double additive, UUID uuid, String name) {
        if (additive != 0) {
            if (playerAttribute != null) {
                AttributeModifier modifier = genAttributeModifier(additive, uuid, name);
                AttributeModifier existingModifier = playerAttribute.getModifier(uuid);
                if (existingModifier != null) {
                    if (existingModifier.getAmount() != additive || existingModifier.getOperation() != modifier.getOperation()) {
                        resetPlayerAttribute(playerAttribute, uuid);
                    }
                }
                if (!playerAttribute.hasModifier(modifier)) {
                    playerAttribute.addPermanentModifier(modifier);
                }
            }
        }
    }

    public static void resetPlayerAttribute(ModifiableAttributeInstance playerAttribute, UUID uuid) {
        if (playerAttribute != null) {
            playerAttribute.removeModifier(uuid);
        }
    }

    public static void runPendantModel(RenderPlayerEvent event) {
        PlayerEntity player = event.getPlayer();
        if (player.isInWater()) {
            CompoundNBT data = player.getPersistentData();
            if (data.contains(CustomValues.hasAtlanticKey)) {
                if (inventoryHasEnabledPendant(player, ItemInit.ATLANTIC_PENDANT.get())) {
                    PlayerModel<AbstractClientPlayerEntity> model = event.getRenderer().getModel();
                    model.rightLeg.visible = false;
                    model.rightPants.visible = false;
                    model.leftPants.visible = false;
                }
            }
        }
    }

    public static void runAtlantic(PlayerEntity player) {
        ModifiableAttributeInstance swimSpeed = player.getAttribute(ForgeMod.SWIM_SPEED.get());
        if (player.isSprinting()) {
            double speed = CommonConfig.ATLANTIC_SWIM_SPEED.get();
            modifyPlayerAttribute(swimSpeed, speed, CustomValues.atlanticSpeedUUID, CustomValues.atlanticSpeedName);
        } else {
            resetPlayerAttribute(swimSpeed, CustomValues.atlanticSpeedUUID);
        }

        int maxAir = player.getMaxAirSupply();
        if (player.getAirSupply() < maxAir) {
            player.setAirSupply(maxAir);
        }

        if (player.isInWater()) {
            int duration = CommonConfig.ATLANTIC_VISION_DURATION.get();
            int amplifier = CommonConfig.ATLANTIC_VISION_AMPLIFIER.get();
            if (duration != 0 && amplifier != 0) {
                EffectInstance nightVision = new EffectInstance(Effects.NIGHT_VISION, duration, amplifier, false, false, false);
                player.addEffect(nightVision);
            }
        }
    }

    public static void resetAtlantic(PlayerEntity player) {
        CompoundNBT data = player.getPersistentData();
        data.remove(CustomValues.hasAtlanticKey);

        resetPlayerAttribute(player.getAttribute(ForgeMod.SWIM_SPEED.get()), CustomValues.atlanticSpeedUUID);
    }

    public static void runKnighthood(PlayerEntity player) {
        double health = CommonConfig.KNIGHTHOOD_EXTRA_HEALTH.get();
        double armor = CommonConfig.KNIGHTHOOD_ARMOR.get();
        double armorToughness = CommonConfig.KNIGHTHOOD_ARMOR_TOUGHNESS.get();
        double knockBackReduce = CommonConfig.KNIGHTHOOD_KNOCK_BACK_RESISTANCE.get();
        double knockBackBoost = CommonConfig.KNIGHTHOOD_KNOCK_BACK_BOOST.get();
        double damageBoost = CommonConfig.KNIGHTHOOD_DAMAGE_BOOST.get();

        modifyPlayerAttribute(player.getAttribute(Attributes.MAX_HEALTH), health, CustomValues.knighthoodMaxHealthUUID, CustomValues.knighthoodMaxHealthName);
        modifyPlayerAttribute(player.getAttribute(Attributes.ARMOR), armor, CustomValues.knighthoodArmorUUID, CustomValues.knighthoodArmorName);
        modifyPlayerAttribute(player.getAttribute(Attributes.ARMOR_TOUGHNESS), armorToughness, CustomValues.knighthoodArmorToughnessUUID, CustomValues.knighthoodArmorToughnessName);
        modifyPlayerAttribute(player.getAttribute(Attributes.KNOCKBACK_RESISTANCE), knockBackReduce, CustomValues.knighthoodKnockBackResistUUID, CustomValues.knighthoodKnockBackResistName);
        modifyPlayerAttribute(player.getAttribute(Attributes.ATTACK_KNOCKBACK), knockBackBoost, CustomValues.knighthoodKnockBackBoostUUID, CustomValues.knighthoodKnockBackBoostName);
        modifyPlayerAttribute(player.getAttribute(Attributes.ATTACK_DAMAGE), damageBoost, CustomValues.knighthoodDamageBoostUUID, CustomValues.knighthoodDamageBoostName);

        CompoundNBT data = player.getPersistentData();
        player.setRemainingFireTicks(0);
        player.setArrowCount(0);

        if (data.contains(CustomValues.renderKnighthoodKey)) {
            int renderKnighthoodTicks = data.getInt(CustomValues.renderKnighthoodKey);
            if (renderKnighthoodTicks > -1) {
                data.putInt(CustomValues.renderKnighthoodKey, renderKnighthoodTicks - 1);
            }
        }

        if (player.hurtTime == player.hurtDuration - 1) {
            activateKnighthoodEffects(player);
        }
    }

    public static void activateKnighthoodEffects(PlayerEntity player) {
        CompoundNBT data = player.getPersistentData();
        if (data.contains(CustomValues.hasKnighthoodKey)) {
            if (inventoryHasEnabledPendant(player, ItemInit.KNIGHTHOOD_PENDANT.get())) {
                int renderKnighthoodTicks = CommonConfig.RENDER_KNIGHTHOOD_TICKS.get();
                if (renderKnighthoodTicks != 0) {
                    data.putInt(CustomValues.renderKnighthoodKey, renderKnighthoodTicks);
                }

                player.playSound(SoundEvents.IRON_GOLEM_HURT, 1, -1);
                player.playSound(SoundEvents.ARMOR_EQUIP_IRON, 1, 3);
            }
        }
    }

    public static void runKnighthoodCritical(CriticalHitEvent event) {
        if (event.isVanillaCritical()) {
            PlayerEntity player = event.getPlayer();
            CompoundNBT data = player.getPersistentData();
            if (data.contains(CustomValues.hasKnighthoodKey)) {
                if (inventoryHasEnabledPendant(player, ItemInit.KNIGHTHOOD_PENDANT.get())) {
                    double damage = CommonConfig.KNIGHTHOOD_CRITICAL_DAMAGE.get();
                    event.setDamageModifier((float) (event.getDamageModifier() + damage));

                    player.playSound(SoundEvents.ENDER_DRAGON_FLAP, 1, -1);
                    activateKnighthoodEffects(player);
                }
            }
        }
    }

    public static void resetKnighthood(PlayerEntity player) {
        CompoundNBT data = player.getPersistentData();
        data.remove(CustomValues.hasKnighthoodKey);
        data.remove(CustomValues.renderKnighthoodKey);

        resetPlayerAttribute(player.getAttribute(Attributes.MAX_HEALTH), CustomValues.knighthoodMaxHealthUUID);
        resetPlayerAttribute(player.getAttribute(Attributes.ARMOR), CustomValues.knighthoodArmorUUID);
        resetPlayerAttribute(player.getAttribute(Attributes.ARMOR_TOUGHNESS), CustomValues.knighthoodArmorToughnessUUID);
        resetPlayerAttribute(player.getAttribute(Attributes.KNOCKBACK_RESISTANCE), CustomValues.knighthoodKnockBackResistUUID);
        resetPlayerAttribute(player.getAttribute(Attributes.ATTACK_KNOCKBACK), CustomValues.knighthoodKnockBackBoostUUID);
        resetPlayerAttribute(player.getAttribute(Attributes.ATTACK_DAMAGE), CustomValues.knighthoodDamageBoostUUID);
    }

    public static void runHoly(PlayerEntity player) {
        boolean noClipEnabled = CommonConfig.HOLY_ENABLE_NOCLIP.get();
        ModifiableAttributeInstance flySpeed = player.getAttribute(Attributes.FLYING_SPEED);
        if (player.isSprinting()) {
            if (!player.isSpectator() && noClipEnabled) {
                player.noPhysics = player.abilities.flying;
            }
            double speed = CommonConfig.HOLY_FLIGHT_SPEED.get();
            modifyPlayerAttribute(flySpeed, speed, CustomValues.holyFlightSpeedBoostUUID, CustomValues.holyFlightSpeedBoostName);
        } else {
            resetPlayerAttribute(flySpeed, CustomValues.holyFlightSpeedBoostUUID);
            if (!player.isSpectator() && noClipEnabled) {
                player.noPhysics = false;
            }
        }

        if (!player.isCreative() && !player.isSpectator()) {
            if (!player.abilities.mayfly) {
                player.abilities.mayfly = true;
                player.onUpdateAbilities();
            }
            if ((player.isInWall() || player.noPhysics) && noClipEnabled) {
                if (!player.abilities.flying) {
                    player.abilities.flying = true;
                    player.onUpdateAbilities();
                }
                player.noPhysics = true;
            }
        }
    }

    public static void resetHoly(PlayerEntity player) {
        boolean noClipEnabled = CommonConfig.HOLY_ENABLE_NOCLIP.get();
        CompoundNBT data = player.getPersistentData();
        data.remove(CustomValues.hasHolyKey);

        resetPlayerAttribute(player.getAttribute(Attributes.FLYING_SPEED), CustomValues.holyFlightSpeedBoostUUID);

        if (!player.isCreative() && !player.isSpectator()) {
            boolean changed = false;
            if (player.abilities.flying) {
                player.abilities.flying = false;
                changed = true;
            }
            if (player.abilities.mayfly) {
                player.abilities.mayfly = false;
                changed = true;
            }
            if (changed) {
                player.onUpdateAbilities();
            }
            if (noClipEnabled) {
                player.noPhysics = false;
            }
        }
    }
}