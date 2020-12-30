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
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
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
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
        if (PendantTools.isEnabled(stack)) {
            ItemStackHandler itemStackHandler = Tools.getItemStackHandlerOfStack(stack, CustomValues.locketSize, false);
            for (int i = 0; i < itemStackHandler.getSlots(); i++) {
                ItemStack storedStack = itemStackHandler.getStackInSlot(i);
                storedStack.onArmorTick(world, player);
            }
        }
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
        tooltip.add(new TranslationTextComponent("msg.splendidpendants.locket_sneak_use_instructions"));
        tooltip.add(new TranslationTextComponent("msg.splendidpendants.divider"));
        tooltip.add(new TranslationTextComponent("msg.splendidpendants.stored_stacks"));
        ItemStackHandler itemStackHandler = Tools.getItemStackHandlerOfStack(stack, CustomValues.locketSize, false);
        for (int i = 0; i < itemStackHandler.getSlots(); i++) {
            ItemStack storedSack = itemStackHandler.getStackInSlot(i);
            if (!storedSack.isEmpty()) {
                String storedStackEnabled;
                if (PendantTools.isEnabled(storedSack)) {
                    storedStackEnabled = new TranslationTextComponent("msg.splendidpendants.enabled").getString();
                } else {
                    storedStackEnabled = new TranslationTextComponent("msg.splendidpendants.disabled").getString();
                }
                tooltip.add(new StringTextComponent(storedSack.getDisplayName().getString() + " - " + storedStackEnabled));
            }
        }
        tooltip.add(new TranslationTextComponent("msg.splendidpendants.divider"));
    }
}
