package dunkmania101.splendidpendants.data.models;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import dunkmania101.splendidpendants.data.CustomValues;
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

public class AtlanticTailModel extends BipedModel<LivingEntity> {
    private DyeColor color = DyeColor.LIME;

    public AtlanticTailModel(ItemStack stack) {
        super(1F);

        ItemStackHandler itemStackHandler = Tools.getItemStackHandlerOfStack(stack, CustomValues.dyeableSize, true);
        if (itemStackHandler.getSlots() > 0) {
            Item storedDye = itemStackHandler.getStackInSlot(0).getItem();
            if (storedDye instanceof DyeItem) {
                DyeItem dyeItem = (DyeItem) storedDye;
                this.color = dyeItem.getDyeColor();
            }
        }

        this.textureWidth = 32;
        this.textureHeight = 32;

        ModelRenderer leftLegModel = new ModelRenderer(this);
        leftLegModel.setRotationPoint(2.0F, 4.0F, -1.0F);
        leftLegModel.setTextureOffset(0, 0).addBox(-8.0F, -2.0F, 3.0F, 12.0F, 2.0F, 1.0F, 0.0F, false);
        leftLegModel.setTextureOffset(0, 0).addBox(-8.0F, -2.0F, -4.0F, 12.0F, 2.0F, 1.0F, 0.0F, false);
        leftLegModel.setTextureOffset(0, 0).addBox(3.0F, -2.0F, -3.0F, 1.0F, 2.0F, 6.0F, 0.0F, false);
        leftLegModel.setTextureOffset(0, 0).addBox(-8.0F, -2.0F, -3.0F, 1.0F, 2.0F, 6.0F, 0.0F, false);
        leftLegModel.setTextureOffset(0, 0).addBox(-8.0F, 0.0F, -4.0F, 12.0F, 3.0F, 8.0F, 0.0F, false);
        leftLegModel.setTextureOffset(0, 0).addBox(-7.0F, 3.0F, -3.0F, 10.0F, 10.0F, 6.0F, 0.0F, false);
        leftLegModel.setTextureOffset(0, 0).addBox(-4.0F, 13.0F, 0.0F, 4.0F, 2.0F, 0.0F, 0.0F, false);
        leftLegModel.setTextureOffset(0, 0).addBox(-5.0F, 16.0F, 0.0F, 6.0F, 1.0F, 0.0F, 0.0F, false);
        leftLegModel.setTextureOffset(0, 0).addBox(-6.0F, 15.0F, 0.0F, 8.0F, 1.0F, 0.0F, 0.0F, false);
        leftLegModel.setTextureOffset(0, 0).addBox(-6.0F, 17.0F, 0.0F, 3.0F, 1.0F, 0.0F, 0.0F, false);
        leftLegModel.setTextureOffset(0, 0).addBox(-1.0F, 17.0F, 0.0F, 3.0F, 1.0F, 0.0F, 0.0F, false);
        leftLegModel.setTextureOffset(0, 0).addBox(-5.0F, 18.0F, 0.0F, 2.0F, 1.0F, 0.0F, 0.0F, false);
        leftLegModel.setTextureOffset(0, 0).addBox(-1.0F, 18.0F, 0.0F, 2.0F, 1.0F, 0.0F, 0.0F, false);
        leftLegModel.setTextureOffset(0, 0).addBox(-4.0F, 19.0F, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, false);
        leftLegModel.setTextureOffset(0, 0).addBox(-1.0F, 19.0F, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, false);
        leftLegModel.setTextureOffset(0, 0).addBox(-3.0F, 3.0F, -4.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        leftLegModel.setTextureOffset(0, 0).addBox(-3.0F, 3.0F, 3.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        leftLegModel.setTextureOffset(0, 0).addBox(-4.0F, 4.0F, -4.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        leftLegModel.setTextureOffset(0, 0).addBox(-4.0F, 4.0F, 3.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        leftLegModel.setTextureOffset(0, 0).addBox(-4.0F, 6.0F, -4.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        leftLegModel.setTextureOffset(0, 0).addBox(-4.0F, 6.0F, 3.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        leftLegModel.setTextureOffset(0, 0).addBox(-4.0F, 8.0F, -4.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        leftLegModel.setTextureOffset(0, 0).addBox(-4.0F, 8.0F, 3.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        leftLegModel.setTextureOffset(0, 0).addBox(-4.0F, 10.0F, -4.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        leftLegModel.setTextureOffset(0, 0).addBox(-4.0F, 12.0F, -4.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        leftLegModel.setTextureOffset(0, 0).addBox(-4.0F, 10.0F, 3.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        leftLegModel.setTextureOffset(0, 0).addBox(-4.0F, 12.0F, 3.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        leftLegModel.setTextureOffset(0, 0).addBox(-1.0F, 4.0F, -4.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        leftLegModel.setTextureOffset(0, 0).addBox(-1.0F, 4.0F, 3.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        leftLegModel.setTextureOffset(0, 0).addBox(-1.0F, 6.0F, -4.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        leftLegModel.setTextureOffset(0, 0).addBox(-1.0F, 6.0F, 3.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        leftLegModel.setTextureOffset(0, 0).addBox(-1.0F, 8.0F, -4.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        leftLegModel.setTextureOffset(0, 0).addBox(-1.0F, 8.0F, 3.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        leftLegModel.setTextureOffset(0, 0).addBox(-1.0F, 10.0F, -4.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        leftLegModel.setTextureOffset(0, 0).addBox(-1.0F, 12.0F, -4.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        leftLegModel.setTextureOffset(0, 0).addBox(-1.0F, 10.0F, 3.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        leftLegModel.setTextureOffset(0, 0).addBox(-1.0F, 12.0F, 3.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        leftLegModel.setTextureOffset(0, 0).addBox(-3.0F, 5.0F, -4.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        leftLegModel.setTextureOffset(0, 0).addBox(-3.0F, 5.0F, 3.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        leftLegModel.setTextureOffset(0, 0).addBox(-3.0F, 7.0F, -4.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        leftLegModel.setTextureOffset(0, 0).addBox(-3.0F, 7.0F, 3.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        leftLegModel.setTextureOffset(0, 0).addBox(-3.0F, 9.0F, -4.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        leftLegModel.setTextureOffset(0, 0).addBox(-3.0F, 11.0F, -4.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        leftLegModel.setTextureOffset(0, 0).addBox(-3.0F, 9.0F, 3.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        leftLegModel.setTextureOffset(0, 0).addBox(-3.0F, 11.0F, 3.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);

        this.bipedHead = new ModelRenderer(this);
        this.bipedHeadwear = new ModelRenderer(this);
        this.bipedBody = new ModelRenderer(this);
        this.bipedRightArm = new ModelRenderer(this);
        this.bipedLeftArm = new ModelRenderer(this);
        this.bipedRightLeg = new ModelRenderer(this);
        this.bipedLeftLeg = leftLegModel;
    }

    @Override
    public void render(@Nonnull MatrixStack matrixStackIn, @Nonnull IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        float[] colors = this.color.getColorComponentValues();
        this.bipedLeftLeg.showModel = true;
        super.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, colors[0], colors[1], colors[2], alpha);
    }
}
