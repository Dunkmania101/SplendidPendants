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

public class KnighthoodArmorModel extends BipedModel<LivingEntity> {
    private DyeColor color = DyeColor.GRAY;

    public KnighthoodArmorModel(ItemStack stack) {
        super(1F);

        ItemStackHandler itemStackHandler = Tools.getItemStackHandlerOfStack(stack, CustomValues.dyeableSize, true);
        Item storedDye = itemStackHandler.getStackInSlot(0).getItem();
        if (storedDye instanceof DyeItem) {
            DyeItem dyeItem = (DyeItem) storedDye;
            this.color = dyeItem.getDyeColor();
        }

        this.textureWidth = 32;
        this.textureHeight = 32;

        ModelRenderer knighthoodArmorHeadwear = new ModelRenderer(this);
        knighthoodArmorHeadwear.setRotationPoint(0.0F, 22.0F, 0.0F);
        knighthoodArmorHeadwear.setTextureOffset(0, 0).addBox(-7.0F, -2.0F, -7.0F, 14.0F, 4.0F, 14.0F, 0.0F, false);
        knighthoodArmorHeadwear.setTextureOffset(0, 0).addBox(-7.0F, -14.0F, -7.0F, 14.0F, 4.0F, 14.0F, 0.0F, false);
        knighthoodArmorHeadwear.setTextureOffset(0, 0).addBox(-6.0F, -10.0F, -6.0F, 12.0F, 8.0F, 12.0F, 0.0F, false);

        ModelRenderer knighthoodArmorHead = new ModelRenderer(this);
        knighthoodArmorHead.setRotationPoint(0.0F, 22.0F, 0.0F);
        knighthoodArmorHead.setTextureOffset(0, 0).addBox(-7.0F, -2.0F, -7.0F, 14.0F, 4.0F, 14.0F, 0.0F, false);
        knighthoodArmorHead.setTextureOffset(0, 0).addBox(-7.0F, -14.0F, -7.0F, 14.0F, 4.0F, 14.0F, 0.0F, false);
        knighthoodArmorHead.setTextureOffset(0, 0).addBox(-6.0F, -10.0F, -6.0F, 12.0F, 8.0F, 12.0F, 0.0F, false);

        ModelRenderer knighthoodArmorChest = new ModelRenderer(this);
        knighthoodArmorChest.setRotationPoint(0.0F, 7.0F, 0.0F);
        knighthoodArmorChest.setTextureOffset(0, 0).addBox(-8.0F, 13.0F, -8.0F, 16.0F, 4.0F, 16.0F, 0.0F, false);
        knighthoodArmorChest.setTextureOffset(0, 0).addBox(-8.0F, -1.0F, -8.0F, 16.0F, 4.0F, 16.0F, 0.0F, false);
        knighthoodArmorChest.setTextureOffset(0, 0).addBox(-7.0F, 3.0F, -7.0F, 14.0F, 10.0F, 14.0F, 0.0F, false);

        ModelRenderer knighthoodArmorRightArm = new ModelRenderer(this);
        knighthoodArmorRightArm.setRotationPoint(1.0F, 6.0F, -1.0F);
        knighthoodArmorRightArm.setTextureOffset(0, 0).addBox(-5.0F, 2.0F, -3.0F, 8.0F, 14.0F, 8.0F, 0.0F, false);
        knighthoodArmorRightArm.setTextureOffset(0, 0).addBox(-6.0F, 16.0F, -4.0F, 10.0F, 2.0F, 10.0F, 0.0F, false);
        knighthoodArmorRightArm.setTextureOffset(0, 0).addBox(-6.0F, 0.0F, -4.0F, 10.0F, 2.0F, 10.0F, 0.0F, false);

        ModelRenderer knighthoodArmorLeftArm = new ModelRenderer(this);
        knighthoodArmorLeftArm.setRotationPoint(1.0F, 6.0F, -1.0F);
        knighthoodArmorLeftArm.setTextureOffset(0, 0).addBox(-5.0F, 2.0F, -3.0F, 8.0F, 14.0F, 8.0F, 0.0F, false);
        knighthoodArmorLeftArm.setTextureOffset(0, 0).addBox(-6.0F, 16.0F, -4.0F, 10.0F, 2.0F, 10.0F, 0.0F, false);
        knighthoodArmorLeftArm.setTextureOffset(0, 0).addBox(-6.0F, 0.0F, -4.0F, 10.0F, 2.0F, 10.0F, 0.0F, false);

        ModelRenderer knighthoodArmorRightLeg = new ModelRenderer(this);
        knighthoodArmorRightLeg.setRotationPoint(1.0F, 6.0F, -1.0F);
        knighthoodArmorRightLeg.setTextureOffset(0, 0).addBox(-5.0F, 2.0F, -3.0F, 8.0F, 14.0F, 8.0F, 0.0F, false);
        knighthoodArmorRightLeg.setTextureOffset(0, 0).addBox(-6.0F, 16.0F, -4.0F, 10.0F, 2.0F, 10.0F, 0.0F, false);
        knighthoodArmorRightLeg.setTextureOffset(0, 0).addBox(-6.0F, 0.0F, -4.0F, 10.0F, 2.0F, 10.0F, 0.0F, false);

        ModelRenderer knighthoodArmorLeftLeg = new ModelRenderer(this);
        knighthoodArmorLeftLeg.setRotationPoint(1.0F, 6.0F, -1.0F);
        knighthoodArmorLeftLeg.setTextureOffset(0, 0).addBox(-5.0F, 2.0F, -3.0F, 8.0F, 14.0F, 8.0F, 0.0F, false);
        knighthoodArmorLeftLeg.setTextureOffset(0, 0).addBox(-6.0F, 16.0F, -4.0F, 10.0F, 2.0F, 10.0F, 0.0F, false);
        knighthoodArmorLeftLeg.setTextureOffset(0, 0).addBox(-6.0F, 0.0F, -4.0F, 10.0F, 2.0F, 10.0F, 0.0F, false);

        this.bipedHeadwear = knighthoodArmorHeadwear;
        this.bipedHead = knighthoodArmorHead;
        this.bipedBody = knighthoodArmorChest;
        this.bipedRightArm = knighthoodArmorRightArm;
        this.bipedLeftArm = knighthoodArmorLeftArm;
        this.bipedRightLeg = knighthoodArmorRightLeg;
        this.bipedLeftLeg = knighthoodArmorLeftLeg;
    }

    @Override
    public void render(@Nonnull MatrixStack matrixStackIn, @Nonnull IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        this.bipedHeadwear.showModel = true;
        this.bipedHead.showModel = true;
        this.bipedBody.showModel = true;
        this.bipedRightArm.showModel = true;
        this.bipedLeftArm.showModel = true;
        this.bipedRightLeg.showModel = true;
        this.bipedLeftLeg.showModel = true;
        float[] colors = this.color.getColorComponentValues();
        super.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, colors[0], colors[1], colors[2], alpha);
    }
}
