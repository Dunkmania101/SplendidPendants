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
public class HoldingPendantHandModel extends BaseDyeableModel {

    public HoldingPendantHandModel(ItemStack stack, int index) {
        super(1F, stack, DyeColor.BROWN);

        this.texWidth = 32;
        this.texHeight = 32;

        ModelRenderer holdingHand = new ModelRenderer(this);

        float y;
        if (index == 1 || index == 2) {
            y = -6F;
        } else if (index == 3 || index == 4) {
            y = -12F;
        }else {
            y = -18F;
        }

        holdingHand.setPos(0F, y, 0F);

        holdingHand.texOffs(0, 0).addBox(-1.0F, -2.0F, -2.0F, 3.0F, 2.0F, 9.0F, 0.0F, false);
        holdingHand.texOffs(0, 0).addBox(-1.0F, -3.0F, 1.0F, 3.0F, 1.0F, 6.0F, 0.0F, false);
        holdingHand.texOffs(0, 0).addBox(-2.0F, -2.0F, -2.0F, 1.0F, 1.0F, 11.0F, 0.0F, false);
        holdingHand.texOffs(0, 0).addBox(2.0F, -2.0F, -2.0F, 1.0F, 1.0F, 11.0F, 0.0F, false);
        holdingHand.texOffs(0, 0).addBox(-1.0F, -2.0F, -5.0F, 3.0F, 1.0F, 3.0F, 0.0F, false);
        holdingHand.texOffs(0, 0).addBox(-1.0F, -3.0F, -7.0F, 3.0F, 1.0F, 2.0F, 0.0F, false);
        holdingHand.texOffs(0, 0).addBox(2.0F, -3.0F, -5.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
        holdingHand.texOffs(0, 0).addBox(-2.0F, -3.0F, -5.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
        holdingHand.texOffs(0, 0).addBox(3.0F, -3.0F, -7.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
        holdingHand.texOffs(0, 0).addBox(1.0F, -3.0F, -8.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        holdingHand.texOffs(0, 0).addBox(-1.0F, -3.0F, -8.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        this.hat = new ModelRenderer(this);
        this.head = new ModelRenderer(this);
        this.body = new ModelRenderer(this);
        this.rightLeg = new ModelRenderer(this);
        this.leftLeg = new ModelRenderer(this);

        if (index % 2 == 0) {
            this.rightArm = holdingHand;
            this.rightArm.visible = true;
            this.leftArm = new ModelRenderer(this);
        } else {
            this.leftArm = holdingHand;
            this.leftArm.visible = true;
            this.rightArm = new ModelRenderer(this);
        }
    }

    @Override
    public void renderToBuffer(@Nonnull MatrixStack matrixStackIn, @Nonnull IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        super.renderToBuffer(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }
}
