package dunkmania101.splendidpendants.data.models;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.DyeColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class FakeHolyHaloModel extends BipedModel<LivingEntity> {
    private final DyeColor color;

    public FakeHolyHaloModel(DyeColor color) {
        super(1F);

        this.color = color;

        this.texWidth = 32;
        this.texHeight = 32;

        ModelRenderer fakeHolyHaloModel = new ModelRenderer(this);
        fakeHolyHaloModel.setPos(0.0F, 23.5F, 0.0F);
        fakeHolyHaloModel.texOffs(0, 0).addBox(-7.1747F, -19.2114F, 1.0686F, 12.0F, 1.0F, 3.0F, 0.0F, false);
        fakeHolyHaloModel.texOffs(0, 0).addBox(-7.1747F, -19.2114F, -7.9314F, 12.0F, 1.0F, 3.0F, 0.0F, false);
        fakeHolyHaloModel.texOffs(0, 0).addBox(1.8253F, -19.2114F, -4.9314F, 3.0F, 1.0F, 6.0F, 0.0F, false);
        fakeHolyHaloModel.texOffs(0, 0).addBox(-7.1747F, -19.2114F, -4.9314F, 3.0F, 1.0F, 6.0F, 0.0F, false);
        fakeHolyHaloModel.texOffs(0, 0).addBox(2.8253F, -20.2114F, -5.9314F, 1.0F, 1.0F, 8.0F, 0.0F, false);
        fakeHolyHaloModel.texOffs(0, 0).addBox(-6.1747F, -20.2114F, -5.9314F, 1.0F, 1.0F, 8.0F, 0.0F, false);
        fakeHolyHaloModel.texOffs(0, 0).addBox(-6.1747F, -20.2114F, -6.9314F, 10.0F, 1.0F, 1.0F, 0.0F, false);
        fakeHolyHaloModel.texOffs(0, 0).addBox(-6.1747F, -20.2114F, 2.0686F, 10.0F, 1.0F, 1.0F, 0.0F, false);
        fakeHolyHaloModel.texOffs(0, 0).addBox(-6.1747F, -18.2114F, 2.0686F, 10.0F, 1.0F, 1.0F, 0.0F, false);
        fakeHolyHaloModel.texOffs(0, 0).addBox(2.8253F, -18.2114F, -5.9314F, 1.0F, 1.0F, 8.0F, 0.0F, false);
        fakeHolyHaloModel.texOffs(0, 0).addBox(-6.1747F, -18.2114F, -6.9314F, 10.0F, 1.0F, 1.0F, 0.0F, false);
        fakeHolyHaloModel.texOffs(0, 0).addBox(-6.1747F, -18.2114F, -5.9314F, 1.0F, 1.0F, 8.0F, 0.0F, false);
        setRotationAngle(fakeHolyHaloModel, -0.2182F, 0.0F, 0.1309F);

        this.hat = fakeHolyHaloModel;
        this.head = new ModelRenderer(this);
        this.body = new ModelRenderer(this);
        this.rightArm = new ModelRenderer(this);
        this.leftArm = new ModelRenderer(this);
        this.rightLeg = new ModelRenderer(this);
        this.leftLeg = new ModelRenderer(this);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }

    @Override
    public void renderToBuffer(@Nonnull MatrixStack matrixStackIn, @Nonnull IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        float[] colors = this.color.getTextureDiffuseColors();
        this.hat.visible = true;
        super.renderToBuffer(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, colors[0], colors[1], colors[2], alpha);
    }
}
