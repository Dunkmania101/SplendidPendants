package dunkmania101.splendidpendants.data.models;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import dunkmania101.splendidpendants.data.CustomValues;
import dunkmania101.splendidpendants.objects.items.DyeSpongeItem;
import dunkmania101.splendidpendants.util.Tools;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.DyeColor;
import net.minecraft.item.DyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

public class BaseDyeableModel extends BipedModel<LivingEntity> {
    private float[] colors;

    public BaseDyeableModel(float modelSize, ItemStack stack, DyeColor defaultColor) {
        super(modelSize);

        this.colors = defaultColor.getTextureDiffuseColors();

        ItemStackHandler itemStackHandler = Tools.getItemStackHandlerOfStack(stack, CustomValues.dyeableSize, true);
        if (itemStackHandler.getSlots() > 0) {
            ItemStack storedStack = itemStackHandler.getStackInSlot(0);
            Item storedDye = storedStack.getItem();
            if (storedDye instanceof DyeItem) {
                this.colors = ((DyeItem) storedDye).getDyeColor().getTextureDiffuseColors();
            } else if (storedDye instanceof DyeSpongeItem) {
                int colorInt = ((DyeSpongeItem) storedDye).getColor(storedStack);
                this.colors = new float[]{(float) Tools.getRed(colorInt) / 255f, (float) Tools.getGreen(colorInt) / 255f, (float) Tools.getBlue(colorInt) / 255f};
            }
        }
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }

    @Override
    public void renderToBuffer(@Nonnull MatrixStack matrixStackIn, @Nonnull IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        super.renderToBuffer(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, this.colors[0], this.colors[1], this.colors[2], alpha);
    }
}
