package dunkmania101.splendidpendants.data.models;

import javax.annotation.Nonnull;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import dunkmania101.splendidpendants.data.CustomValues;
import dunkmania101.splendidpendants.objects.items.DyeSpongeItem;
import dunkmania101.splendidpendants.util.Tools;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

public class BaseDyeableModel extends HumanoidModel<LivingEntity> {
    private float[] colors;

    public BaseDyeableModel(ItemStack stack, DyeColor defaultColor) {
        super(createMesh(new CubeDeformation(1F), 1F).getRoot().bake(1, 1));

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

    public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }

    @Override
    public void renderToBuffer(@Nonnull PoseStack matrixStackIn, @Nonnull VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        super.renderToBuffer(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, this.colors[0], this.colors[1], this.colors[2], alpha);
    }
}
