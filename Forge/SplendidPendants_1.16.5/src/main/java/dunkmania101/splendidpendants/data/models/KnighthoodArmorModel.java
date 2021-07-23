package dunkmania101.splendidpendants.data.models;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class KnighthoodArmorModel extends BaseDyeableModel {
    public KnighthoodArmorModel(ItemStack stack) {
        super(1F, stack, DyeColor.GRAY);

        this.texWidth = 32;
        this.texHeight = 32;

        ModelRenderer knighthoodArmorHeadwear = new ModelRenderer(this);
        knighthoodArmorHeadwear.setPos(0.0F, 22.0F, 0.0F);
        knighthoodArmorHeadwear.texOffs(0, 0).addBox(-7.0F, -2.0F, -7.0F, 14.0F, 4.0F, 14.0F, 0.0F, false);
        knighthoodArmorHeadwear.texOffs(0, 0).addBox(-7.0F, -14.0F, -7.0F, 14.0F, 4.0F, 14.0F, 0.0F, false);
        knighthoodArmorHeadwear.texOffs(0, 0).addBox(-6.0F, -10.0F, -6.0F, 12.0F, 8.0F, 12.0F, 0.0F, false);

        ModelRenderer knighthoodArmorHead = new ModelRenderer(this);
        knighthoodArmorHead.setPos(0.0F, 22.0F, 0.0F);
        knighthoodArmorHead.texOffs(0, 0).addBox(-7.0F, -2.0F, -7.0F, 14.0F, 4.0F, 14.0F, 0.0F, false);
        knighthoodArmorHead.texOffs(0, 0).addBox(-7.0F, -14.0F, -7.0F, 14.0F, 4.0F, 14.0F, 0.0F, false);
        knighthoodArmorHead.texOffs(0, 0).addBox(-6.0F, -10.0F, -6.0F, 12.0F, 8.0F, 12.0F, 0.0F, false);

        ModelRenderer knighthoodArmorChest = new ModelRenderer(this);
        knighthoodArmorChest.setPos(0.0F, 7.0F, 0.0F);
        knighthoodArmorChest.texOffs(0, 0).addBox(-8.0F, 13.0F, -8.0F, 16.0F, 4.0F, 16.0F, 0.0F, false);
        knighthoodArmorChest.texOffs(0, 0).addBox(-8.0F, -1.0F, -8.0F, 16.0F, 4.0F, 16.0F, 0.0F, false);
        knighthoodArmorChest.texOffs(0, 0).addBox(-7.0F, 3.0F, -7.0F, 14.0F, 10.0F, 14.0F, 0.0F, false);

        ModelRenderer knighthoodArmorRightArm = new ModelRenderer(this);
        knighthoodArmorRightArm.setPos(1.0F, 6.0F, -1.0F);
        knighthoodArmorRightArm.texOffs(0, 0).addBox(-5.0F, 2.0F, -3.0F, 8.0F, 14.0F, 8.0F, 0.0F, false);
        knighthoodArmorRightArm.texOffs(0, 0).addBox(-6.0F, 16.0F, -4.0F, 10.0F, 2.0F, 10.0F, 0.0F, false);
        knighthoodArmorRightArm.texOffs(0, 0).addBox(-6.0F, 0.0F, -4.0F, 10.0F, 2.0F, 10.0F, 0.0F, false);

        ModelRenderer knighthoodArmorLeftArm = new ModelRenderer(this);
        knighthoodArmorLeftArm.setPos(1.0F, 6.0F, -1.0F);
        knighthoodArmorLeftArm.texOffs(0, 0).addBox(-5.0F, 2.0F, -3.0F, 8.0F, 14.0F, 8.0F, 0.0F, false);
        knighthoodArmorLeftArm.texOffs(0, 0).addBox(-6.0F, 16.0F, -4.0F, 10.0F, 2.0F, 10.0F, 0.0F, false);
        knighthoodArmorLeftArm.texOffs(0, 0).addBox(-6.0F, 0.0F, -4.0F, 10.0F, 2.0F, 10.0F, 0.0F, false);

        ModelRenderer knighthoodArmorRightLeg = new ModelRenderer(this);
        knighthoodArmorRightLeg.setPos(1.0F, 6.0F, -1.0F);
        knighthoodArmorRightLeg.texOffs(0, 0).addBox(-5.0F, 2.0F, -3.0F, 8.0F, 14.0F, 8.0F, 0.0F, false);
        knighthoodArmorRightLeg.texOffs(0, 0).addBox(-6.0F, 16.0F, -4.0F, 10.0F, 2.0F, 10.0F, 0.0F, false);
        knighthoodArmorRightLeg.texOffs(0, 0).addBox(-6.0F, 0.0F, -4.0F, 10.0F, 2.0F, 10.0F, 0.0F, false);

        ModelRenderer knighthoodArmorLeftLeg = new ModelRenderer(this);
        knighthoodArmorLeftLeg.setPos(1.0F, 6.0F, -1.0F);
        knighthoodArmorLeftLeg.texOffs(0, 0).addBox(-5.0F, 2.0F, -3.0F, 8.0F, 14.0F, 8.0F, 0.0F, false);
        knighthoodArmorLeftLeg.texOffs(0, 0).addBox(-6.0F, 16.0F, -4.0F, 10.0F, 2.0F, 10.0F, 0.0F, false);
        knighthoodArmorLeftLeg.texOffs(0, 0).addBox(-6.0F, 0.0F, -4.0F, 10.0F, 2.0F, 10.0F, 0.0F, false);

        this.hat = knighthoodArmorHeadwear;
        this.head = knighthoodArmorHead;
        this.body = knighthoodArmorChest;
        this.rightArm = knighthoodArmorRightArm;
        this.leftArm = knighthoodArmorLeftArm;
        this.rightLeg = knighthoodArmorRightLeg;
        this.leftLeg = knighthoodArmorLeftLeg;
    }

    @Override
    public void renderToBuffer(@Nonnull MatrixStack matrixStackIn, @Nonnull IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        this.hat.visible = true;
        this.head.visible = true;
        this.body.visible = true;
        this.rightArm.visible = true;
        this.leftArm.visible = true;
        this.rightLeg.visible = true;
        this.leftLeg.visible = true;
        super.renderToBuffer(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }
}
