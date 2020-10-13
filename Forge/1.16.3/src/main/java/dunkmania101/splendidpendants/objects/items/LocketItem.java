package dunkmania101.splendidpendants.objects.items;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import dunkmania101.splendidpendants.data.CustomValues;
import dunkmania101.splendidpendants.data.PendantArmorMaterial;
import dunkmania101.splendidpendants.data.models.BlankBipedModel;
import dunkmania101.splendidpendants.objects.containers.LocketContainer;
import dunkmania101.splendidpendants.util.Tools;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;

import java.util.List;
import java.util.Map;

public class LocketItem extends PendantItem {
    public LocketItem(Properties properties) {
        super(PendantArmorMaterial.LOCKET, properties);
    }

    @Override
    public BipedModel<LivingEntity> getCustomModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot) {
        ItemStack storedStack = getPrioritizedStoredStack(itemStack, entityLiving);
        Item storedItem = storedStack.getItem();
        if (storedItem instanceof PendantItem) {
            PendantItem pendant = (PendantItem) storedItem;
            return pendant.getArmorModel(entityLiving, storedStack, armorSlot, new BlankBipedModel());
        }
        return super.getCustomModel(entityLiving, itemStack, armorSlot);
    }

    @Override
    public String getCustomTexture(ItemStack stack, Entity entity, EquipmentSlotType equipmentSlot, String type) {
        if (entity instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity) entity;
            ItemStack storedStack = getPrioritizedStoredStack(stack, livingEntity);
            Item storedItem = storedStack.getItem();
            if (storedItem instanceof PendantItem) {
                PendantItem pendant = (PendantItem) storedItem;
                return pendant.getArmorTexture(storedStack, entity, slot, type);
            }
        }
        return super.getCustomTexture(stack, entity, slot, type);
    }

    public ItemStack getPrioritizedStoredStack(ItemStack thisStack, LivingEntity livingEntity) {
        ItemStackHandler itemStackHandler = Tools.getItemStackHandlerOfStack(thisStack, CustomValues.locketSize, false);
        ItemStack storedStack = ItemStack.EMPTY;
        for (int i = 0; i < itemStackHandler.getSlots(); i++) {
            ItemStack checkStack = itemStackHandler.getStackInSlot(i);
            Item checkItem = checkStack.getItem();
            Item storedItem = storedStack.getItem();
            if (checkItem instanceof AtlanticPendantItem) {
                storedStack = checkStack;
            } else if (checkItem instanceof KnighthoodPendantItem && !(storedItem instanceof AtlanticPendantItem && livingEntity.isInWater())) {
                storedStack = checkStack;
            } else if (checkItem instanceof HolyPendantItem && !((storedItem instanceof AtlanticPendantItem && livingEntity.isInWater()) || (storedItem instanceof KnighthoodPendantItem && livingEntity.getPersistentData().getInt(CustomValues.renderKnighthoodKey) > 0))) {
                storedStack = checkStack;
            }
        }
        return storedStack;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot, ItemStack stack) {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        ItemStackHandler itemStackHandler = Tools.getItemStackHandlerOfStack(stack, CustomValues.locketSize, false);
        for (int i = 0; i < itemStackHandler.getSlots(); i++) {
            ItemStack storedStack = itemStackHandler.getStackInSlot(i);
            if (storedStack.getItem() instanceof PendantItem) {
                for (Map.Entry<Attribute, AttributeModifier> entry : storedStack.getAttributeModifiers(equipmentSlot).entries()) {
                    if (!builder.build().containsKey(entry.getKey())) {
                        builder.put(entry);
                    }
                }
            }
        }
        return builder.build();
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
    public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslationTextComponent("msg.splendidpendants.locket_sneak_use_instructions"));
        tooltip.add(new TranslationTextComponent("msg.splendidpendants.divider"));
        tooltip.add(new TranslationTextComponent("msg.splendidpendants.stored_stacks"));
        ItemStackHandler itemStackHandler = Tools.getItemStackHandlerOfStack(stack, CustomValues.locketSize, false);
        for (int i = 0; i < itemStackHandler.getSlots(); i++) {
            ItemStack checkStack = itemStackHandler.getStackInSlot(i);
            Item checkItem = checkStack.getItem();
            if (checkItem instanceof PendantItem) {
                tooltip.add(checkItem.getName());
            }
        }
        tooltip.add(new TranslationTextComponent("msg.splendidpendants.divider"));
    }
}
