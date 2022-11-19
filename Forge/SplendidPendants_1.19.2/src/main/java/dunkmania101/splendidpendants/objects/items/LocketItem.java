package dunkmania101.splendidpendants.objects.items;

import java.util.List;

import javax.annotation.Nonnull;

import dunkmania101.splendidpendants.data.CustomValues;
import dunkmania101.splendidpendants.data.PendantArmorMaterial;
import dunkmania101.splendidpendants.data.models.BlankBipedModel;
import dunkmania101.splendidpendants.data.models.MultiBipedModel;
import dunkmania101.splendidpendants.objects.containers.LocketContainer;
import dunkmania101.splendidpendants.util.PendantTools;
import dunkmania101.splendidpendants.util.Tools;
import net.minecraft.ChatFormatting;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.items.ItemStackHandler;

public class LocketItem extends PendantItem {
    public LocketItem(Properties properties) {
        super(PendantArmorMaterial.LOCKET, properties);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public HumanoidModel<LivingEntity> getCustomModel(LivingEntity entityLiving, ItemStack itemStack,
            EquipmentSlot armorSlot, HumanoidModel<?> _default) {
        MultiBipedModel customModel = new MultiBipedModel(itemStack);
        ItemStackHandler itemStackHandler = Tools.getItemStackHandlerOfStack(itemStack, CustomValues.locketSize, false,
                false);
        boolean alsoTail = false;
        boolean alsoArmor = false;
        for (int s = 0; s < 2; s++) {
            for (int i = 0; i < itemStackHandler.getSlots(); i++) {
                ItemStack checkStack = itemStackHandler.getStackInSlot(i);
                if (!checkStack.isEmpty()) {
                    Item checkItem = checkStack.getItem();
                    if (checkItem instanceof PendantItem pendantItem) {
                        if (s == 0) {
                            if (pendantItem instanceof KnighthoodPendantItem) {
                                alsoArmor = true;
                            } else if (pendantItem instanceof AtlanticPendantItem && entityLiving.isInWater()) {
                                alsoTail = true;
                            }
                        } else {
                            if (pendantItem instanceof KnighthoodPendantItem knighthoodPendantItem && alsoTail) {
                                knighthoodPendantItem.nextRenderWithTail();
                            } else if (pendantItem instanceof AtlanticPendantItem atlanticPendantItem && alsoArmor) {
                                atlanticPendantItem.nextRenderWithArmor();
                            } else if (pendantItem instanceof HolyPendantItem holyPendantItem && alsoArmor) {
                                holyPendantItem.nextRenderWithArmor();
                            }
                            HumanoidModel<LivingEntity> subCustomModel = (HumanoidModel<LivingEntity>) pendantItem
                                    .getPendantArmorModel(entityLiving, checkStack, armorSlot, _default);
                            if (!(subCustomModel instanceof BlankBipedModel)) {
                                Tools.followBodyRotations(entityLiving, subCustomModel);
                                customModel.addChild(subCustomModel);
                            }
                        }
                    }
                }
            }
        }
        return customModel;
    }

    @Override
    public String getCustomTexture(ItemStack stack, Entity entity, EquipmentSlot equipmentSlot, String type) {
        ItemStack storedStack = PendantTools.getPrioritizedStoredStack(stack, entity);
        Item storedItem = storedStack.getItem();
        if (storedItem instanceof PendantItem) {
            PendantItem pendant = (PendantItem) storedItem;
            return pendant.getArmorTexture(storedStack, entity, slot, type);
        }
        return super.getCustomTexture(stack, entity, slot, type);
    }

    @Override
    public MenuProvider getContainerProvider(Level world, Player playerEntity, InteractionHand hand, ItemStack stack) {
        if (PendantTools.isEnabled(stack)) {
            return new SimpleMenuProvider(
                    (id, playerInventory, openingPlayer) -> new LocketContainer(id, playerInventory, stack),
                    stack.getDisplayName());
        }
        return super.getContainerProvider(world, playerEntity, hand, stack);
    }

    @Override
    public String getAltInvKey() {
        return "msg.splendidpendants.locket_sneak_use_instructions";
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@Nonnull ItemStack stack, Level worldIn, @Nonnull List<Component> tooltip,
            @Nonnull TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add(Component.translatable("msg.splendidpendants.stored_stacks").withStyle(ChatFormatting.GRAY));
        ItemStackHandler itemStackHandler = Tools.getItemStackHandlerOfStack(stack, CustomValues.locketSize, false);
        for (int i = 0; i < itemStackHandler.getSlots(); i++) {
            ItemStack storedSack = itemStackHandler.getStackInSlot(i);
            if (!storedSack.isEmpty()) {
                Component storedStackEnabled;
                if (PendantTools.isEnabled(storedSack)) {
                    storedStackEnabled = Component.translatable("msg.splendidpendants.enabled")
                            .withStyle(ChatFormatting.GREEN, ChatFormatting.BOLD);
                } else {
                    storedStackEnabled = Component.translatable("msg.splendidpendants.disabled")
                            .withStyle(ChatFormatting.RED, ChatFormatting.BOLD);
                }
                Integer dyeColorInt = ChatFormatting.GRAY.getColor();
                if (dyeColorInt == null) {
                    dyeColorInt = 0;
                }
                ItemStackHandler dyeStackHandler = Tools.getItemStackHandlerOfStack(storedSack,
                        CustomValues.dyeableSize, true);
                boolean colorDyed = false;
                if (dyeStackHandler.getSlots() > 0) {
                    ItemStack dyeStack = dyeStackHandler.getStackInSlot(0);
                    if (!dyeStack.isEmpty()) {
                        Item dyeItem = dyeStack.getItem();
                        if (dyeItem instanceof DyeItem) {
                            dyeColorInt = ((DyeItem) dyeItem).getDyeColor().getTextColor();
                            colorDyed = true;
                        } else if (dyeItem instanceof DyeSpongeItem) {
                            dyeColorInt = ((DyeSpongeItem) dyeItem).getColor(dyeStack);
                            colorDyed = true;
                        }
                    }
                }
                if (!colorDyed) {
                    Item storedItem = storedSack.getItem();
                    if (storedItem instanceof AtlanticPendantItem) {
                        dyeColorInt = DyeColor.LIME.getTextColor();
                    } else if (storedItem instanceof KnighthoodPendantItem) {
                        dyeColorInt = DyeColor.GRAY.getTextColor();
                    } else if (storedItem instanceof HolyPendantItem) {
                        dyeColorInt = DyeColor.YELLOW.getTextColor();
                    }
                }
                Style pendantColorStyle = Style.EMPTY.withColor(TextColor.fromRgb(dyeColorInt));
                tooltip.add(Component.literal(storedSack.getDisplayName().getString() + " - ")
                        .withStyle(pendantColorStyle).withStyle(ChatFormatting.BOLD).append(storedStackEnabled));
            }
        }
        tooltip.add(Component.translatable("msg.splendidpendants.divider"));
    }
}
