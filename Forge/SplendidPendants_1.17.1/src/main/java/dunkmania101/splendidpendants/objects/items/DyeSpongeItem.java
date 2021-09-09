package dunkmania101.splendidpendants.objects.items;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import dunkmania101.splendidpendants.data.CustomValues;
import dunkmania101.splendidpendants.util.Tools;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class DyeSpongeItem extends Item {
    public DyeSpongeItem(Properties properties) {
        super(properties);
    }

    @Nonnull
    @Override
    public InteractionResultHolder<ItemStack> use(@Nonnull Level worldIn, @Nonnull Player playerIn, @Nonnull InteractionHand handIn) {
        if (playerIn.isCrouching()) {
            ItemStack stack = playerIn.getItemInHand(handIn);
            InteractionHand otherHand;
            if (handIn == InteractionHand.MAIN_HAND) {
                otherHand = InteractionHand.OFF_HAND;
            } else {
                otherHand = InteractionHand.MAIN_HAND;
            }
            ItemStack otherStack = playerIn.getItemInHand(otherHand);
            Item item = otherStack.getItem();
            if (item instanceof DyeItem) {
                addDye(stack, ((DyeItem) item).getDyeColor(), playerIn);
                if (!playerIn.isCreative()) {
                    otherStack.setCount(otherStack.getCount() - 1);
                }
            }
        }
        return super.use(worldIn, playerIn, handIn);
    }

    public void addDye(ItemStack stack, DyeColor color, Player player) {
        CompoundTag nbt = stack.getOrCreateTag();

        int colorInt = color.getTextColor();
        int currentColorInt = nbt.getInt(CustomValues.colorKey);

        int currentRedInt = Tools.getRed(currentColorInt);
        if (currentRedInt == 0) {
            currentRedInt = 255;
        }
        int currentGreenInt = Tools.getGreen(currentColorInt);
        if (currentGreenInt == 0) {
            currentGreenInt = 255;
        }
        int currentBlueInt = Tools.getBlue(currentColorInt);
        if (currentBlueInt == 0) {
            currentBlueInt = 255;
        }

        int red =  Tools.blendColors(currentRedInt, Tools.getRed(colorInt));
        int green = Tools.blendColors(currentGreenInt, Tools.getGreen(colorInt));
        int blue = Tools.blendColors(currentBlueInt, Tools.getBlue(colorInt));
        int newColor = Tools.packColor(255, red, green, blue);
        nbt.putInt(CustomValues.colorKey, newColor);

        stack.setTag(nbt);
        player.displayClientMessage(new TranslatableComponent("msg.splendidpendants.add_dye_success").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(newColor))), true);
    }

    public int getColor(ItemStack stack) {
        return stack.getOrCreateTag().getInt(CustomValues.colorKey);
    }

    @Override
    public void appendHoverText(@Nonnull ItemStack stack, @Nullable Level worldIn, @Nonnull List<Component> tooltip, @Nonnull TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslatableComponent("msg.splendidpendants.dye_sponge_sneak_use_instructions").withStyle(ChatFormatting.GRAY));
        int color = getColor(stack);
        tooltip.add(new TranslatableComponent("msg.splendidpendants.sponge_color").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(color))));
        tooltip.add(new TextComponent("R: " + Tools.getRed(color)).withStyle(ChatFormatting.RED, ChatFormatting.BOLD));
        tooltip.add(new TextComponent("G: " + Tools.getGreen(color)).withStyle(ChatFormatting.GREEN, ChatFormatting.BOLD));
        tooltip.add(new TextComponent("B: " + Tools.getBlue(color)).withStyle(ChatFormatting.BLUE, ChatFormatting.BOLD));
    }
}
