package dunkmania101.splendidpendants.objects.items;

import dunkmania101.splendidpendants.data.CustomValues;
import dunkmania101.splendidpendants.data.PendantArmorMaterial;
import dunkmania101.splendidpendants.data.models.BlankBipedModel;
import dunkmania101.splendidpendants.data.models.MultiBipedModel;
import dunkmania101.splendidpendants.objects.containers.LocketContainer;
import dunkmania101.splendidpendants.util.PendantTools;
import dunkmania101.splendidpendants.util.Tools;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.item.DyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.text.*;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import java.util.List;

public class LocketItem extends PendantItem {
    public LocketItem(Properties properties) {
        super(PendantArmorMaterial.LOCKET, properties);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public BipedModel<LivingEntity> getCustomModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot) {
        MultiBipedModel customModel = new MultiBipedModel();
        ItemStackHandler itemStackHandler = Tools.getItemStackHandlerOfStack(itemStack, CustomValues.locketSize, false);
        for (int i = 0; i < itemStackHandler.getSlots(); i++) {
            ItemStack checkStack = itemStackHandler.getStackInSlot(i);
            if (!checkStack.isEmpty()) {
                Item checkItem = checkStack.getItem();
                if (checkItem instanceof PendantItem) {
                    BipedModel<LivingEntity> subCustomModel = ((PendantItem) checkItem).getCustomModel(entityLiving, checkStack, armorSlot);
                    if (!(subCustomModel instanceof BlankBipedModel)) {
                        Tools.followBodyRotations(entityLiving, subCustomModel);
                        customModel.addChild(subCustomModel);
                    }
                }
            }
        }
        return customModel;
    }

    @Override
    public String getCustomTexture(ItemStack stack, Entity entity, EquipmentSlotType equipmentSlot, String type) {
        ItemStack storedStack = PendantTools.getPrioritizedStoredStack(stack, entity);
        Item storedItem = storedStack.getItem();
        if (storedItem instanceof PendantItem) {
            PendantItem pendant = (PendantItem) storedItem;
            return pendant.getArmorTexture(storedStack, entity, slot, type);
        }
        return super.getCustomTexture(stack, entity, slot, type);
    }

    @Override
    public INamedContainerProvider getContainerProvider(World world, PlayerEntity playerEntity, Hand hand, ItemStack stack) {
        return new SimpleNamedContainerProvider(
                (id, playerInventory, openingPlayer) -> new LocketContainer(id, playerInventory, stack),
                stack.getDisplayName()
        );
    }

    @Override
    public void appendHoverText(@Nonnull ItemStack stack, World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslationTextComponent("msg.splendidpendants.locket_sneak_use_instructions").withStyle(TextFormatting.GRAY));
        tooltip.add(new TranslationTextComponent("msg.splendidpendants.divider"));
        tooltip.add(new TranslationTextComponent("msg.splendidpendants.stored_stacks").withStyle(TextFormatting.GRAY));
        ItemStackHandler itemStackHandler = Tools.getItemStackHandlerOfStack(stack, CustomValues.locketSize, false);
        for (int i = 0; i < itemStackHandler.getSlots(); i++) {
            ItemStack storedSack = itemStackHandler.getStackInSlot(i);
            if (!storedSack.isEmpty()) {
                TextComponent storedStackEnabled;
                if (PendantTools.isEnabled(storedSack)) {
                    storedStackEnabled = (TextComponent) new TranslationTextComponent("msg.splendidpendants.enabled").withStyle(TextFormatting.GREEN, TextFormatting.BOLD);
                } else {
                    storedStackEnabled = (TextComponent) new TranslationTextComponent("msg.splendidpendants.disabled").withStyle(TextFormatting.RED, TextFormatting.BOLD);
                }
                Style pendantColorStyle;
                Integer gray = TextFormatting.GRAY.getColor();
                if (gray == null) {
                    gray = 0;
                }
                pendantColorStyle = Style.EMPTY.withColor(Color.fromRgb(gray));
                ItemStackHandler dyeStackHandler = Tools.getItemStackHandlerOfStack(storedSack, CustomValues.dyeableSize, true);
                if (dyeStackHandler.getSlots() > 0) {
                    ItemStack dyeStack = dyeStackHandler.getStackInSlot(0);
                    if (!dyeStack.isEmpty()) {
                        Item dyeItem = dyeStack.getItem();
                        if (dyeItem instanceof DyeItem) {
                            pendantColorStyle = Style.EMPTY.withColor(Color.fromRgb(((DyeItem) dyeItem).getDyeColor().getTextColor()));
                        } else if (dyeItem instanceof DyeSpongeItem) {
                            pendantColorStyle = Style.EMPTY.withColor(Color.fromRgb(((DyeSpongeItem) dyeItem).getColor(dyeStack)));
                        }
                    }
                }
                tooltip.add(new StringTextComponent(storedSack.getDisplayName().getString() + " - ").withStyle(pendantColorStyle).withStyle(TextFormatting.BOLD).append(storedStackEnabled));
            }
        }
        tooltip.add(new TranslationTextComponent("msg.splendidpendants.divider"));
    }
}
