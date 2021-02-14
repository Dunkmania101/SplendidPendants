package dunkmania101.splendidpendants.objects.items;

import dunkmania101.splendidpendants.data.CustomValues;
import dunkmania101.splendidpendants.data.PendantArmorMaterial;
import dunkmania101.splendidpendants.data.models.BlankBipedModel;
import dunkmania101.splendidpendants.objects.containers.LocketContainer;
import dunkmania101.splendidpendants.util.PendantTools;
import dunkmania101.splendidpendants.util.Tools;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
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
        ItemStack storedStack = PendantTools.getPrioritizedStoredStack(itemStack, entityLiving);
        Item storedItem = storedStack.getItem();
        if (storedItem instanceof PendantItem) {
            PendantItem pendant = (PendantItem) storedItem;
            return pendant.getArmorModel(entityLiving, storedStack, armorSlot, new BlankBipedModel());
        }
        return super.getCustomModel(entityLiving, itemStack, armorSlot);
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
    public void customClickActions(World world, PlayerEntity player, Hand hand, ItemStack stack) {
        super.customClickActions(world, player, hand, stack);
        SimpleNamedContainerProvider newContainer = new SimpleNamedContainerProvider(
                (id, playerInventory, openingPlayer) -> new LocketContainer(id, playerInventory, stack),
                stack.getDisplayName()
        );
        player.openContainer(newContainer);
    }

    @Override
    public void addInformation(@Nonnull ItemStack stack, World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslationTextComponent("msg.splendidpendants.locket_sneak_use_instructions").mergeStyle(TextFormatting.GRAY));
        tooltip.add(new TranslationTextComponent("msg.splendidpendants.divider"));
        tooltip.add(new TranslationTextComponent("msg.splendidpendants.stored_stacks").mergeStyle(TextFormatting.GRAY));
        ItemStackHandler itemStackHandler = Tools.getItemStackHandlerOfStack(stack, CustomValues.locketSize, false);
        for (int i = 0; i < itemStackHandler.getSlots(); i++) {
            ItemStack storedSack = itemStackHandler.getStackInSlot(i);
            if (!storedSack.isEmpty()) {
                TextComponent storedStackEnabled;
                if (PendantTools.isEnabled(storedSack)) {
                    storedStackEnabled = (TextComponent) new TranslationTextComponent("msg.splendidpendants.enabled").mergeStyle(TextFormatting.GREEN, TextFormatting.BOLD);
                } else {
                    storedStackEnabled = (TextComponent) new TranslationTextComponent("msg.splendidpendants.disabled").mergeStyle(TextFormatting.RED, TextFormatting.BOLD);
                }
                Style pendantColorStyle;
                Integer gray = TextFormatting.GRAY.getColor();
                if (gray != null) {
                    pendantColorStyle = Style.EMPTY.setColor(Color.fromInt(gray));
                } else {
                    pendantColorStyle = Style.EMPTY.setColor(Color.fromInt(0));
                }
                ItemStackHandler dyeStackHandler = Tools.getItemStackHandlerOfStack(storedSack, CustomValues.dyeableSize, true);
                if (dyeStackHandler.getSlots() > 0) {
                    ItemStack dyeStack = dyeStackHandler.getStackInSlot(0);
                    if (!dyeStack.isEmpty()) {
                        Item dyeItem = dyeStack.getItem();
                        if (dyeItem instanceof DyeItem) {
                            pendantColorStyle = Style.EMPTY.setColor(Color.fromInt(((DyeItem) dyeItem).getDyeColor().getTextColor()));
                        } else if (dyeItem instanceof DyeSpongeItem) {
                            pendantColorStyle = Style.EMPTY.setColor(Color.fromInt(((DyeSpongeItem) dyeItem).getColor(dyeStack)));
                        }
                    }
                }
                tooltip.add(new StringTextComponent(storedSack.getDisplayName().getString() + " - ").mergeStyle(pendantColorStyle).mergeStyle(TextFormatting.BOLD).append(storedStackEnabled));
            }
        }
        tooltip.add(new TranslationTextComponent("msg.splendidpendants.divider"));
    }
}
