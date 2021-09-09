package dunkmania101.splendidpendants.data.models;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import dunkmania101.splendidpendants.data.CustomValues;
import dunkmania101.splendidpendants.util.Tools;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class HoldingHandsModel extends BaseDyeableModel {
    public HoldingHandsModel(ItemStack stack) {
        super(1F, stack, DyeColor.BROWN);

        this.texWidth = 32;
        this.texHeight = 32;

        ModelRenderer holdingHands = new ModelRenderer(this);

        int count = 1;

        ItemStackHandler stackHandler = Tools.getItemStackHandlerOfStack(stack, CustomValues.holdingSize, false, true);
        for (int slot = 0; slot < stackHandler.getSlots(); slot++) {
            if (!stackHandler.getStackInSlot(slot).isEmpty()) {
                count++;
            }
        }
        for (int index = 0; index < count; index++) {
            ModelRenderer holdingHand = new ModelRenderer(this);

            float x;
            if (index % 2 == 0) {
                x = 12F;
            } else {
                x = -12F;
            }

            float y;
            if (index == 1 || index == 2) {
                y = -6F;
            } else if (index == 3 || index == 4) {
                y = -12F;
            }else {
                y = -18F;
            }

            holdingHand.setPos(x, y, 0F);

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

            holdingHands.addChild(holdingHand);
        }

        this.hat = new ModelRenderer(this);
        this.head = new ModelRenderer(this);
        this.body = holdingHands;
        this.rightArm = new ModelRenderer(this);
        this.leftArm = new ModelRenderer(this);
        this.rightLeg = new ModelRenderer(this);
        this.leftLeg = new ModelRenderer(this);
    }

    @Override
    public void renderToBuffer(@Nonnull MatrixStack matrixStackIn, @Nonnull IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        this.body.visible = true;
        super.renderToBuffer(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }
}
