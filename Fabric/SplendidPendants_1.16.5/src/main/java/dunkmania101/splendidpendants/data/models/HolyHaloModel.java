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
public class HolyHaloModel extends BaseDyeableModel {
    public HolyHaloModel(ItemStack stack) {
        super(1F, stack, DyeColor.YELLOW);

        this.textureWidth = 32;
        this.textureHeight = 32;

        ModelRenderer holyHaloModel = new ModelRenderer(this);
        holyHaloModel.setRotationPoint(0.0F, 23.5F, 0.0F);
        holyHaloModel.setTextureOffset(0, 0).addBox(-7.1747F, -19.2114F, 1.0686F, 12.0F, 1.0F, 3.0F, 0.0F, false);
        holyHaloModel.setTextureOffset(0, 0).addBox(-7.1747F, -19.2114F, -7.9314F, 12.0F, 1.0F, 3.0F, 0.0F, false);
        holyHaloModel.setTextureOffset(0, 0).addBox(1.8253F, -19.2114F, -4.9314F, 3.0F, 1.0F, 6.0F, 0.0F, false);
        holyHaloModel.setTextureOffset(0, 0).addBox(-7.1747F, -19.2114F, -4.9314F, 3.0F, 1.0F, 6.0F, 0.0F, false);
        holyHaloModel.setTextureOffset(0, 0).addBox(2.8253F, -20.2114F, -5.9314F, 1.0F, 1.0F, 8.0F, 0.0F, false);
        holyHaloModel.setTextureOffset(0, 0).addBox(-6.1747F, -20.2114F, -5.9314F, 1.0F, 1.0F, 8.0F, 0.0F, false);
        holyHaloModel.setTextureOffset(0, 0).addBox(-6.1747F, -20.2114F, -6.9314F, 10.0F, 1.0F, 1.0F, 0.0F, false);
        holyHaloModel.setTextureOffset(0, 0).addBox(-6.1747F, -20.2114F, 2.0686F, 10.0F, 1.0F, 1.0F, 0.0F, false);
        holyHaloModel.setTextureOffset(0, 0).addBox(-6.1747F, -18.2114F, 2.0686F, 10.0F, 1.0F, 1.0F, 0.0F, false);
        holyHaloModel.setTextureOffset(0, 0).addBox(2.8253F, -18.2114F, -5.9314F, 1.0F, 1.0F, 8.0F, 0.0F, false);
        holyHaloModel.setTextureOffset(0, 0).addBox(-6.1747F, -18.2114F, -6.9314F, 10.0F, 1.0F, 1.0F, 0.0F, false);
        holyHaloModel.setTextureOffset(0, 0).addBox(-6.1747F, -18.2114F, -5.9314F, 1.0F, 1.0F, 8.0F, 0.0F, false);
        setRotationAngle(holyHaloModel, -0.2182F, 0.0F, 0.1309F);

        this.bipedHeadwear = holyHaloModel;
        this.bipedHead = new ModelRenderer(this);
        this.bipedBody = new ModelRenderer(this);
        this.bipedRightArm = new ModelRenderer(this);
        this.bipedLeftArm = new ModelRenderer(this);
        this.bipedRightLeg = new ModelRenderer(this);
        this.bipedLeftLeg = new ModelRenderer(this);
    }

    @Override
    public void render(@Nonnull MatrixStack matrixStackIn, @Nonnull IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        this.bipedHeadwear.showModel = true;
        super.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }
}
