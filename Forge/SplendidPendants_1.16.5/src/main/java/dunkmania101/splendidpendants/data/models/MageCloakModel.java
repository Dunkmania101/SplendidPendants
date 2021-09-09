//package dunkmania101.splendidpendants.data.models;
//
//import com.mojang.blaze3d.matrix.MatrixStack;
//import com.mojang.blaze3d.vertex.IVertexBuilder;
//import net.minecraft.client.renderer.model.ModelRenderer;
//import net.minecraft.item.DyeColor;
//import net.minecraft.item.ItemStack;
//import net.minecraftforge.api.distmarker.Dist;
//import net.minecraftforge.api.distmarker.OnlyIn;
//
//import javax.annotation.Nonnull;
//
//@OnlyIn(Dist.CLIENT)
//public class MageCloakModel extends BaseDyeableModel {
//    public MageCloakModel(ItemStack stack) {
//        super(1F, stack, DyeColor.PURPLE);
//
//        this.texWidth = 32;
//        this.texHeight = 32;
//
//        ModelRenderer mageCloakBody = new ModelRenderer(this);
//        mageCloakBody.setPos(0.0F, 24.0F, 0.0F);
//        mageCloakBody.texOffs(0, 0).addBox(-4.0F, -15.0F, -5.0F, 8.0F, 15.0F, 1.0F, 0.0F, false);
//        mageCloakBody.texOffs(0, 0).addBox(-2.0F, -8.0F, -6.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
//        mageCloakBody.texOffs(0, 0).addBox(1.0F, -8.0F, -6.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
//        mageCloakBody.texOffs(0, 0).addBox(1.0F, -13.0F, -6.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
//        mageCloakBody.texOffs(0, 0).addBox(-2.0F, -13.0F, -6.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
//        mageCloakBody.texOffs(0, 0).addBox(-1.0F, -9.0F, -6.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
//        mageCloakBody.texOffs(0, 0).addBox(-3.0F, -13.0F, -6.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
//        mageCloakBody.texOffs(0, 0).addBox(2.0F, -13.0F, -6.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
//        mageCloakBody.texOffs(0, 0).addBox(2.0F, -4.0F, -6.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
//        mageCloakBody.texOffs(0, 0).addBox(-3.0F, -4.0F, -6.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
//        mageCloakBody.texOffs(0, 0).addBox(-4.0F, -15.0F, 4.0F, 8.0F, 15.0F, 1.0F, 0.0F, false);
//        mageCloakBody.texOffs(0, 0).addBox(-2.0F, -8.0F, 5.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
//        mageCloakBody.texOffs(0, 0).addBox(1.0F, -8.0F, 5.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
//        mageCloakBody.texOffs(0, 0).addBox(1.0F, -13.0F, 5.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
//        mageCloakBody.texOffs(0, 0).addBox(-1.0F, -9.0F, 5.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
//        mageCloakBody.texOffs(0, 0).addBox(-3.0F, -13.0F, 5.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
//        mageCloakBody.texOffs(0, 0).addBox(2.0F, -13.0F, 5.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
//        mageCloakBody.texOffs(0, 0).addBox(2.0F, -4.0F, 5.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
//        mageCloakBody.texOffs(0, 0).addBox(-4.0F, -15.0F, -5.0F, 8.0F, 15.0F, 1.0F, 0.0F, false);
//        mageCloakBody.texOffs(0, 0).addBox(-3.0F, -4.0F, 5.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
//        mageCloakBody.texOffs(0, 0).addBox(-2.0F, -13.0F, 5.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
//
//        ModelRenderer cube_r1 = new ModelRenderer(this);
//        cube_r1.setPos(0.0F, 0.0F, 0.0F);
//        mageCloakBody.addChild(cube_r1);
//        setRotationAngle(cube_r1, 0.0F, 0.0873F, 0.0F);
//        cube_r1.texOffs(0, 0).addBox(3.0F, -15.0F, -4.0F, 1.0F, 15.0F, 9.0F, 0.0F, true);
//
//        ModelRenderer cube_r2 = new ModelRenderer(this);
//        cube_r2.setPos(0.0F, 0.0F, 0.0F);
//        mageCloakBody.addChild(cube_r2);
//        setRotationAngle(cube_r2, 0.0F, -0.0873F, 0.0F);
//        cube_r2.texOffs(0, 0).addBox(-4.0F, -15.0F, -4.0F, 1.0F, 15.0F, 9.0F, 0.0F, false);
//
//        this.hat = new ModelRenderer(this);
//        this.head = new ModelRenderer(this);
//        this.body = mageCloakBody;
//        this.rightArm = new ModelRenderer(this);
//        this.leftArm = new ModelRenderer(this);
//        this.rightLeg = new ModelRenderer(this);
//        this.leftLeg = new ModelRenderer(this);
//    }
//
//    @Override
//    public void renderToBuffer(@Nonnull MatrixStack matrixStackIn, @Nonnull IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
//        this.body.visible = true;
//        super.renderToBuffer(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
//    }
//}
