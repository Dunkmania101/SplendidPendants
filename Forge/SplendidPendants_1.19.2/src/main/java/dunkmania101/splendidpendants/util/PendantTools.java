package dunkmania101.splendidpendants.util;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.lang3.tuple.ImmutableTriple;

import dunkmania101.splendidpendants.data.CommonConfig;
import dunkmania101.splendidpendants.data.CustomValues;
import dunkmania101.splendidpendants.data.compat.Mods;
import dunkmania101.splendidpendants.init.ItemInit;
import dunkmania101.splendidpendants.objects.items.AtlanticPendantItem;
import dunkmania101.splendidpendants.objects.items.HolyPendantItem;
import dunkmania101.splendidpendants.objects.items.KnighthoodPendantItem;
import dunkmania101.splendidpendants.objects.items.LocketItem;
import dunkmania101.splendidpendants.objects.items.PendantItem;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.entity.player.CriticalHitEvent;
import net.minecraftforge.items.ItemStackHandler;
import top.theillusivec4.curios.api.CuriosApi;

public class PendantTools {
    public static void runPendants(Player player) {
        CompoundTag data = player.getPersistentData();
        if (anyInventoryHasEnabledPendant(player, ItemInit.ATLANTIC_PENDANT.get())) {
            if (!data.contains(CustomValues.hasAtlanticKey)) {
                data.putString(CustomValues.hasAtlanticKey, "");
            }
            runAtlantic(player);
        } else if (data.contains(CustomValues.hasAtlanticKey)) {
            resetAtlantic(player);
        }
        if (anyInventoryHasEnabledPendant(player, ItemInit.KNIGHTHOOD_PENDANT.get())) {
            if (!data.contains(CustomValues.hasKnighthoodKey)) {
                data.putString(CustomValues.hasKnighthoodKey, "");
            }
            runKnighthood(player);
        } else if (data.contains(CustomValues.hasKnighthoodKey)) {
            resetKnighthood(player);
        }
        if (anyInventoryHasEnabledPendant(player, ItemInit.HOLY_PENDANT.get())) {
            if (!data.contains(CustomValues.hasHolyKey)) {
                data.putString(CustomValues.hasHolyKey, "");
            }
            runHoly(player);
        } else if (data.contains(CustomValues.hasHolyKey)) {
            resetHoly(player);
        }
        if (anyInventoryHasEnabledPendant(player, ItemInit.HOLDING_PENDANT.get())) {
            if (!data.contains(CustomValues.hasHoldingKey)) {
                data.putString(CustomValues.hasHoldingKey, "");
            }
            runHolding(player);
        } else if (data.contains(CustomValues.hasHoldingKey)) {
            resetHolding(player);
        }
        // if (anyInventoryHasEnabledPendant(player, ItemInit.MAGE_PENDANT.get())) {
        //     if (!data.contains(CustomValues.hasMageKey)) {
        //         data.putString(CustomValues.hasMageKey, "");
        //     }
        //     runMage(player);
        // } else if (data.contains(CustomValues.hasMageKey)) {
        //     resetMage(player);
        // }
    }

    public static ArrayList<ItemStack> getAllEnabledPendantsInInventory(ArrayList<ItemStack> inventory, PendantItem pendant) {
        ArrayList<ItemStack> enabledPendants = new ArrayList<>();
        for (ItemStack checkStack : inventory) {
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
                                    enabledPendants.add(pendantStack);
                                }
                            }
                        }
                    }
                }
            } else if (checkItem instanceof PendantItem) {
                if (checkItem == pendant) {
                    if (isEnabled(checkStack)) {
                        enabledPendants.add(checkStack);
                    }
                }
            }
        }
        return enabledPendants;
    }

    public static ArrayList<ItemStack> getAllEnabledPendantsOnPlayer(Player player, PendantItem pendant) {
        ArrayList<ItemStack> pendants = new ArrayList<>();
        if (Mods.CURIOS.isLoaded()) {
            Optional<ImmutableTriple<String, Integer, ItemStack>> optionalStack = CuriosApi.getCuriosHelper().findEquippedCurio(pendant, player);
            if (optionalStack.isPresent()) {
                pendants.add(optionalStack.get().right);
            } else {
                optionalStack = CuriosApi.getCuriosHelper().findEquippedCurio(ItemInit.LOCKET.get(), player);
                optionalStack.ifPresent(stackTriple -> pendants.add(stackTriple.right));
            }
        }
        ItemStack armorStack = player.getItemBySlot(EquipmentSlot.CHEST);
        if (armorStack.getItem() == pendant) {
            pendants.add(armorStack);
        }
        return getAllEnabledPendantsInInventory(pendants, pendant);
    }

    public static boolean inventoryHasEnabledPendant(ArrayList<ItemStack> checkStacks, PendantItem pendant) {
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

    public static boolean armorInventoryHasEnabledPendant(Player player, PendantItem pendant) {
        ArrayList<net.minecraft.world.item.ItemStack> checkStacks = new ArrayList<>();
        checkStacks.add(player.getInventory().armor.get(EquipmentSlot.CHEST.getIndex()));
        return inventoryHasEnabledPendant(checkStacks, pendant);
    }

    public static boolean curiosInventoryHasEnabledPendant(Player player, PendantItem pendant) {
        if (Mods.CURIOS.isLoaded()) {
            ArrayList<ItemStack> checkStacks = new ArrayList<>();
            Optional<ImmutableTriple<String, Integer, ItemStack>> optionalStack = CuriosApi.getCuriosHelper().findEquippedCurio(pendant, player);
            if (optionalStack.isPresent()) {
                checkStacks.add(optionalStack.get().right);
            } else {
                optionalStack = CuriosApi.getCuriosHelper().findEquippedCurio(ItemInit.LOCKET.get(), player);
                optionalStack.ifPresent(stackTriple -> checkStacks.add(stackTriple.right));
            }
            return inventoryHasEnabledPendant(checkStacks, pendant);
        }
        return false;
    }

    public static boolean anyInventoryHasEnabledPendant(Player player, PendantItem pendant) {
        return armorInventoryHasEnabledPendant(player, pendant) || curiosInventoryHasEnabledPendant(player, pendant);
    }

    public static boolean isEnabled(ItemStack stack) {
        if (stack != null) {
            CompoundTag nbt = stack.getTag();
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
                            || ((checkItem instanceof HolyPendantItem && entity instanceof Player && ((Player) entity).getAbilities().flying)
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

    public static void modifyPlayerAttribute(AttributeInstance playerAttribute, double additive, UUID uuid, String name) {
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
                    playerAttribute.addTransientModifier(modifier);
                }
            }
        }
    }

    public static void resetPlayerAttribute(AttributeInstance playerAttribute, UUID uuid) {
        if (playerAttribute != null) {
            playerAttribute.removeModifier(uuid);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static void runPendantModel(RenderPlayerEvent event) {
        Player player = event.getEntity();
        CompoundTag data = player.getPersistentData();
        if (!data.contains(CustomValues.noRenderAtlanticKey)) {
            if (player.isInWater()) {
                if (data.contains(CustomValues.hasAtlanticKey)) {
                    if (anyInventoryHasEnabledPendant(player, ItemInit.ATLANTIC_PENDANT.get())) {
                        PlayerModel<AbstractClientPlayer> model = event.getRenderer().getModel();
                        model.rightLeg.visible = false;
                        model.rightPants.visible = false;
                        model.leftLeg.visible = false;
                        model.leftPants.visible = false;
                    }
                }
            }
        }
    }

    public static void runCriticalAttack(CriticalHitEvent event) {
        if (event.isVanillaCritical()) {
            Player player = event.getEntity();
            CompoundTag data = player.getPersistentData();
            if (data.contains(CustomValues.hasKnighthoodKey)) {
                if (anyInventoryHasEnabledPendant(player, ItemInit.KNIGHTHOOD_PENDANT.get())) {
                    double damage = CommonConfig.KNIGHTHOOD_CRITICAL_DAMAGE.get();
                    event.setDamageModifier((float) (event.getDamageModifier() + damage));

                    player.playSound(SoundEvents.ENDER_DRAGON_FLAP, 1, -1);
                    activateKnighthoodEffects(player);
                }
            }
        }
    }

    // public static void runPlayerAttack(Player player, LivingEntity target) {
    //     CompoundTag data = player.getPersistentData();
    //     if (data.contains(CustomValues.hasMageKey)) {
    //         if (anyInventoryHasEnabledPendant(player, ItemInit.MAGE_PENDANT.get())) {
    //             if (player.isSprinting()) {
    //             }
    //         }
    //     }
    // }

    public static void runAtlantic(Player player) {
        AttributeInstance swimSpeed = player.getAttribute(ForgeMod.SWIM_SPEED.get());
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
            int duration = CommonConfig.ATLANTIC_CONDUIT_POWER_DURATION.get();
            int amplifier = CommonConfig.ATLANTIC_CONDUIT_POWER_AMPLIFIER.get();
            if (duration != 0 && amplifier != 0) {
                MobEffectInstance conduitPower = new MobEffectInstance(MobEffects.CONDUIT_POWER, duration, amplifier, false, false, false);
                player.addEffect(conduitPower);
            }
        }
    }

    public static void resetAtlantic(Player player) {
        CompoundTag data = player.getPersistentData();
        data.remove(CustomValues.hasAtlanticKey);

        resetPlayerAttribute(player.getAttribute(ForgeMod.SWIM_SPEED.get()), CustomValues.atlanticSpeedUUID);
    }

    public static void runKnighthood(Player player) {
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

        CompoundTag data = player.getPersistentData();
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

    public static void activateKnighthoodEffects(Player player) {
        CompoundTag data = player.getPersistentData();
        if (data.contains(CustomValues.hasKnighthoodKey)) {
            if (anyInventoryHasEnabledPendant(player, ItemInit.KNIGHTHOOD_PENDANT.get())) {
                int renderKnighthoodTicks = CommonConfig.RENDER_KNIGHTHOOD_TICKS.get();
                if (renderKnighthoodTicks != 0) {
                    data.putInt(CustomValues.renderKnighthoodKey, renderKnighthoodTicks);
                }

                player.playSound(SoundEvents.IRON_GOLEM_HURT, 1, -1);
                player.playSound(SoundEvents.ARMOR_EQUIP_IRON, 1, 3);
            }
        }
    }

    public static void resetKnighthood(Player player) {
        CompoundTag data = player.getPersistentData();
        data.remove(CustomValues.hasKnighthoodKey);
        data.remove(CustomValues.renderKnighthoodKey);

        resetPlayerAttribute(player.getAttribute(Attributes.MAX_HEALTH), CustomValues.knighthoodMaxHealthUUID);
        resetPlayerAttribute(player.getAttribute(Attributes.ARMOR), CustomValues.knighthoodArmorUUID);
        resetPlayerAttribute(player.getAttribute(Attributes.ARMOR_TOUGHNESS), CustomValues.knighthoodArmorToughnessUUID);
        resetPlayerAttribute(player.getAttribute(Attributes.KNOCKBACK_RESISTANCE), CustomValues.knighthoodKnockBackResistUUID);
        resetPlayerAttribute(player.getAttribute(Attributes.ATTACK_KNOCKBACK), CustomValues.knighthoodKnockBackBoostUUID);
        resetPlayerAttribute(player.getAttribute(Attributes.ATTACK_DAMAGE), CustomValues.knighthoodDamageBoostUUID);
    }

    public static void runHoly(Player player) {
        boolean noClipEnabled = CommonConfig.HOLY_ENABLE_NOCLIP.get();
        CompoundTag data = player.getPersistentData();
        AttributeInstance flySpeed = player.getAttribute(Attributes.FLYING_SPEED);
        if (player.isSprinting()) {
            if (!player.isSpectator() && noClipEnabled) {
                player.noPhysics = player.getAbilities().flying;
                if (player.noPhysics) {
                    if (!data.contains(CustomValues.isNoClipKey)) {
                        data.putString(CustomValues.isNoClipKey, "");
                    }
                } else {
                    data.remove(CustomValues.isNoClipKey);
                }
            }
            double speed = CommonConfig.HOLY_FLIGHT_SPEED.get();
            if (speed > 0 && !player.isCrouching()) {
                modifyPlayerAttribute(flySpeed, speed, CustomValues.holyFlightSpeedBoostUUID, CustomValues.holyFlightSpeedBoostName);
            } else {
                resetPlayerAttribute(flySpeed, CustomValues.holyFlightSpeedBoostUUID);
            }
        } else {
            resetPlayerAttribute(flySpeed, CustomValues.holyFlightSpeedBoostUUID);
            if (!player.isSpectator() && data.contains(CustomValues.isNoClipKey) && noClipEnabled) {
                player.noPhysics = false;
                data.remove(CustomValues.isNoClipKey);
            }
        }

        if (!player.isCreative() && !player.isSpectator()) {
            if (!player.getAbilities().mayfly) {
                player.getAbilities().mayfly = true;
                player.onUpdateAbilities();
            }
            if ((player.isInWall() || player.noPhysics) && noClipEnabled) {
                if (!player.getAbilities().flying) {
                    player.getAbilities().flying = true;
                    player.onUpdateAbilities();
                }
                player.noPhysics = true;
            }
            if (data.contains(CustomValues.isFlyingKey) && !player.getAbilities().flying) {
                player.getAbilities().flying = true;
                player.onUpdateAbilities();
                data.remove(CustomValues.isFlyingKey);
            }
        }
    }

    public static void resetHoly(Player player) {
        boolean noClipEnabled = CommonConfig.HOLY_ENABLE_NOCLIP.get();
        CompoundTag data = player.getPersistentData();
        data.remove(CustomValues.hasHolyKey);
        data.remove(CustomValues.isFlyingKey);
        data.remove(CustomValues.isNoClipKey);

        resetPlayerAttribute(player.getAttribute(Attributes.FLYING_SPEED), CustomValues.holyFlightSpeedBoostUUID);

        if (!player.isCreative() && !player.isSpectator()) {
            boolean changed = false;
            if (player.getAbilities().flying) {
                player.getAbilities().flying = false;
                changed = true;
            }
            if (player.getAbilities().mayfly) {
                player.getAbilities().mayfly = false;
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

    public static void runHolding(Player player) {
        for (ItemStack holdingPendantStack : getAllEnabledPendantsOnPlayer(player, ItemInit.HOLDING_PENDANT.get())) {
            ItemStackHandler holdingStackHandler = Tools.getItemStackHandlerOfStack(holdingPendantStack, CustomValues.holdingSize, false, true);
            for (int i = 0; i < holdingStackHandler.getSlots(); i++) {
                holdingStackHandler.getStackInSlot(i).inventoryTick(player.level, player, -1, true);
            }
        }

        double reachBuff = CommonConfig.HOLDING_REACH_DISTANCE.get();
        modifyPlayerAttribute(player.getAttribute(ForgeMod.REACH_DISTANCE.get()), reachBuff, CustomValues.holdingReachBuffUUID, CustomValues.holdingReachBuffName);
    }

    public static void resetHolding(Player player) {
        resetPlayerAttribute(player.getAttribute(ForgeMod.REACH_DISTANCE.get()), CustomValues.holdingReachBuffUUID);
    }

    //public static void runMage(Player player) {
    //}

    //public static void resetMage(Player player) {
    //}
}
