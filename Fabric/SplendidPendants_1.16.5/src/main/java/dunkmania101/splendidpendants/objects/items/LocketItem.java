package dunkmania101.splendidpendants.objects.items;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import dunkmania101.splendidpendants.data.CustomValues;
import dunkmania101.splendidpendants.data.ICustomInventory;
import dunkmania101.splendidpendants.data.PendantArmorMaterial;
import dunkmania101.splendidpendants.data.models.BlankBipedModel;
import dunkmania101.splendidpendants.objects.containers.LocketContainer;
import dunkmania101.splendidpendants.util.PendantTools;
import dunkmania101.splendidpendants.util.Tools;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class LocketItem extends PendantItem {
    public LocketItem(Settings settings) {
        super(PendantArmorMaterial.LOCKET, settings);
    }

    @Environment(EnvType.CLIENT)
    @Override
    public BipedEntityModel<LivingEntity> getCustomModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot) {
        ItemStack storedStack = PendantTools.getPrioritizedStoredStack(itemStack, entityLiving);
        Item storedItem = storedStack.getItem();
        if (storedItem instanceof PendantItem) {
            PendantItem pendant = (PendantItem) storedItem;
            return pendant.getArmorModel(entityLiving, storedStack, armorSlot, new BlankBipedModel());
        }
        return super.getCustomModel(entityLiving, itemStack, armorSlot);
    }

    @Override
    public Identifier getCustomTexture(LivingEntity entity, ItemStack stack, EquipmentSlot slot, boolean secondLayer, @Nullable String suffix, Identifier defaultTexture) {
        ItemStack storedStack = PendantTools.getPrioritizedStoredStack(stack, entity);
        Item storedItem = storedStack.getItem();
        if (storedItem instanceof PendantItem) {
            PendantItem pendant = (PendantItem) storedItem;
            return pendant.getArmorTexture(entity, stack, slot, secondLayer, suffix, defaultTexture);
        }
        return super.getCustomTexture(entity, stack, slot, secondLayer, suffix, defaultTexture);
    }

    @Override
    public void customClickActions(World world, PlayerEntity player, Hand hand, ItemStack stack) {
        super.customClickActions(world, player, hand, stack);
        SimpleNamedScreenHandlerFactory newContainer = new SimpleNamedScreenHandlerFactory(
                (id, playerInventory, openingPlayer) -> new LocketContainer(id, playerInventory, stack),
                stack.getName()
        );
        player.openHandledScreen(newContainer);
    }

    @Override
    public void appendTooltip(ItemStack stack, World worldIn, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, worldIn, tooltip, context);
        tooltip.add(new TranslatableText("msg.splendidpendants.locket_sneak_use_instructions").formatted(Formatting.GRAY));
        tooltip.add(new TranslatableText("msg.splendidpendants.divider"));
        tooltip.add(new TranslatableText("msg.splendidpendants.stored_stacks").formatted(Formatting.GRAY));
        ICustomInventory customInventory = Tools.getInventoryOfStack(stack, CustomValues.locketSize, false);
        for (int i = 0; i < customInventory.size(); i++) {
            ItemStack storedSack = customInventory.getStack(i);
            if (!storedSack.isEmpty()) {
                Text storedStackEnabled;
                if (PendantTools.isEnabled(storedSack)) {
                    storedStackEnabled = (Text) new TranslatableText("msg.splendidpendants.enabled").formatted(Formatting.GREEN, Formatting.BOLD);
                } else {
                    storedStackEnabled = (Text) new TranslatableText("msg.splendidpendants.disabled").formatted(Formatting.RED, Formatting.BOLD);
                }
                Style pendantColorStyle;
                Integer gray = Formatting.GRAY.getColorValue();
                if (gray != null) {
                    pendantColorStyle = Style.EMPTY.withColor(TextColor.fromRgb(gray));
                } else {
                    pendantColorStyle = Style.EMPTY.withColor(TextColor.fromRgb(0));
                }
                ICustomInventory dyeStackHandler = Tools.getInventoryOfStack(storedSack, CustomValues.dyeableSize, true);
                if (dyeStackHandler.size() > 0) {
                    ItemStack dyeStack = dyeStackHandler.getStack(0);
                    if (!dyeStack.isEmpty()) {
                        Item dyeItem = dyeStack.getItem();
                        if (dyeItem instanceof DyeItem) {
                            pendantColorStyle = Style.EMPTY.withColor(TextColor.fromRgb(((DyeItem) dyeItem).getColor().getSignColor()));
                        } else if (dyeItem instanceof DyeSpongeItem) {
                            pendantColorStyle = Style.EMPTY.withColor(TextColor.fromRgb(((DyeSpongeItem) dyeItem).getColor(dyeStack)));
                        }
                    }
                }
                tooltip.add(new LiteralText(storedSack.getName().getString() + " - ").setStyle(pendantColorStyle).formatted(Formatting.BOLD).append(storedStackEnabled));
            }
        }
        tooltip.add(new TranslatableText("msg.splendidpendants.divider"));
    }
}
