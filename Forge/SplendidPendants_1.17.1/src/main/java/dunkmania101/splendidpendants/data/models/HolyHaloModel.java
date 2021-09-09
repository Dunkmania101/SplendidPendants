package dunkmania101.splendidpendants.data.models;

import javax.annotation.Nonnull;

import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HolyHaloModel extends BaseDyeableModel {
    public HolyHaloModel(ItemStack stack) {
        super(stack, DyeColor.YELLOW);

        ModelRenderer holyHaloModel = new ModelRenderer(this);
        holyHaloModel.setPos(0.0F, 23.5F, 0.0F);
        holyHaloModel.texOffs(0, 0).addBox(-7.1747F, -19.2114F, 1.0686F, 12.0F, 1.0F, 3.0F, 0.0F, false);
        holyHaloModel.texOffs(0, 0).addBox(-7.1747F, -19.2114F, -7.9314F, 12.0F, 1.0F, 3.0F, 0.0F, false);
        holyHaloModel.texOffs(0, 0).addBox(1.8253F, -19.2114F, -4.9314F, 3.0F, 1.0F, 6.0F, 0.0F, false);
        holyHaloModel.texOffs(0, 0).addBox(-7.1747F, -19.2114F, -4.9314F, 3.0F, 1.0F, 6.0F, 0.0F, false);
        holyHaloModel.texOffs(0, 0).addBox(2.8253F, -20.2114F, -5.9314F, 1.0F, 1.0F, 8.0F, 0.0F, false);
        holyHaloModel.texOffs(0, 0).addBox(-6.1747F, -20.2114F, -5.9314F, 1.0F, 1.0F, 8.0F, 0.0F, false);
        holyHaloModel.texOffs(0, 0).addBox(-6.1747F, -20.2114F, -6.9314F, 10.0F, 1.0F, 1.0F, 0.0F, false);
        holyHaloModel.texOffs(0, 0).addBox(-6.1747F, -20.2114F, 2.0686F, 10.0F, 1.0F, 1.0F, 0.0F, false);
        holyHaloModel.texOffs(0, 0).addBox(-6.1747F, -18.2114F, 2.0686F, 10.0F, 1.0F, 1.0F, 0.0F, false);
        holyHaloModel.texOffs(0, 0).addBox(2.8253F, -18.2114F, -5.9314F, 1.0F, 1.0F, 8.0F, 0.0F, false);
        holyHaloModel.texOffs(0, 0).addBox(-6.1747F, -18.2114F, -6.9314F, 10.0F, 1.0F, 1.0F, 0.0F, false);
        holyHaloModel.texOffs(0, 0).addBox(-6.1747F, -18.2114F, -5.9314F, 1.0F, 1.0F, 8.0F, 0.0F, false);
        setRotationAngle(holyHaloModel, -0.2182F, 0.0F, 0.1309F);

        this.hat = holyHaloModel;
        this.head = new ModelRenderer(this);
        this.body = new ModelRenderer(this);
        this.rightArm = new ModelRenderer(this);
        this.leftArm = new ModelRenderer(this);
        this.rightLeg = new ModelRenderer(this);
        this.leftLeg = new ModelRenderer(this);
    }

    @Override
    public void renderToBuffer(@Nonnull MatrixStack matrixStackIn, @Nonnull IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        this.hat.visible = true;
        super.renderToBuffer(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }
}
