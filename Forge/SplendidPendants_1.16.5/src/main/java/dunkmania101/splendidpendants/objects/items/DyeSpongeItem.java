package dunkmania101.splendidpendants.objects.items;

import dunkmania101.splendidpendants.data.CustomValues;
import dunkmania101.splendidpendants.util.Tools;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeColor;
import net.minecraft.item.DyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.*;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class DyeSpongeItem extends Item {
    public DyeSpongeItem(Properties properties) {
        super(properties);
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> use(@Nonnull World worldIn, @Nonnull PlayerEntity playerIn, @Nonnull Hand handIn) {
        if (playerIn.isCrouching()) {
            ItemStack stack = playerIn.getItemInHand(handIn);
            Hand otherHand;
            if (handIn == Hand.MAIN_HAND) {
                otherHand = Hand.OFF_HAND;
            } else {
                otherHand = Hand.MAIN_HAND;
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

    public void addDye(ItemStack stack, DyeColor color, PlayerEntity player) {
        CompoundNBT nbt = stack.getOrCreateTag();

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
        player.displayClientMessage(new TranslationTextComponent("msg.splendidpendants.add_dye_success").withStyle(Style.EMPTY.withColor(Color.fromRgb(newColor))), true);
    }

    public int getColor(ItemStack stack) {
        return stack.getOrCreateTag().getInt(CustomValues.colorKey);
    }

    @Override
    public void appendHoverText(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslationTextComponent("msg.splendidpendants.dye_sponge_sneak_use_instructions").withStyle(TextFormatting.GRAY));
        int color = getColor(stack);
        tooltip.add(new TranslationTextComponent("msg.splendidpendants.sponge_color").withStyle(Style.EMPTY.withColor(Color.fromRgb(color))));
        tooltip.add(new StringTextComponent("R: " + Tools.getRed(color)).withStyle(TextFormatting.RED, TextFormatting.BOLD));
        tooltip.add(new StringTextComponent("G: " + Tools.getGreen(color)).withStyle(TextFormatting.GREEN, TextFormatting.BOLD));
        tooltip.add(new StringTextComponent("B: " + Tools.getBlue(color)).withStyle(TextFormatting.BLUE, TextFormatting.BOLD));
    }
}
