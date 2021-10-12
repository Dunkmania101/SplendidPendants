package dunkmania101.splendidpendants.data.models;

import javax.annotation.Nonnull;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AtlanticTailModel extends BaseDyeableModel {
    public AtlanticTailModel(ItemStack stack) {
        super(stack, DyeColor.LIME);

        CubeListBuilder leftLegModel = CubeListBuilder.create();
        leftLegModel.setPos(2.0F, 4.0F, -1.0F);
        leftLegModel.texOffs(0, 0).addBox(-8.0F, -2.0F, 3.0F, 12.0F, 2.0F, 1.0F, 0.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-8.0F, -2.0F, -4.0F, 12.0F, 2.0F, 1.0F, 0.0F, false);
        leftLegModel.texOffs(0, 0).addBox(3.0F, -2.0F, -3.0F, 1.0F, 2.0F, 6.0F, 0.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-8.0F, -2.0F, -3.0F, 1.0F, 2.0F, 6.0F, 0.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-8.0F, 0.0F, -4.0F, 12.0F, 3.0F, 8.0F, 0.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-7.0F, 3.0F, -3.0F, 10.0F, 10.0F, 6.0F, 0.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-4.0F, 13.0F, 0.0F, 4.0F, 2.0F, 0.0F, 0.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-5.0F, 16.0F, 0.0F, 6.0F, 1.0F, 0.0F, 0.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-6.0F, 15.0F, 0.0F, 8.0F, 1.0F, 0.0F, 0.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-6.0F, 17.0F, 0.0F, 3.0F, 1.0F, 0.0F, 0.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-1.0F, 17.0F, 0.0F, 3.0F, 1.0F, 0.0F, 0.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-5.0F, 18.0F, 0.0F, 2.0F, 1.0F, 0.0F, 0.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-1.0F, 18.0F, 0.0F, 2.0F, 1.0F, 0.0F, 0.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-4.0F, 19.0F, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-1.0F, 19.0F, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-3.0F, 3.0F, -4.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-3.0F, 3.0F, 3.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-4.0F, 4.0F, -4.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-4.0F, 4.0F, 3.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-4.0F, 6.0F, -4.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-4.0F, 6.0F, 3.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-4.0F, 8.0F, -4.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-4.0F, 8.0F, 3.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-4.0F, 10.0F, -4.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-4.0F, 12.0F, -4.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-4.0F, 10.0F, 3.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-4.0F, 12.0F, 3.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-1.0F, 4.0F, -4.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-1.0F, 4.0F, 3.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-1.0F, 6.0F, -4.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-1.0F, 6.0F, 3.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-1.0F, 8.0F, -4.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-1.0F, 8.0F, 3.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-1.0F, 10.0F, -4.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-1.0F, 12.0F, -4.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-1.0F, 10.0F, 3.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-1.0F, 12.0F, 3.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-3.0F, 5.0F, -4.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-3.0F, 5.0F, 3.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-3.0F, 7.0F, -4.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-3.0F, 7.0F, 3.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-3.0F, 9.0F, -4.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-3.0F, 11.0F, -4.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-3.0F, 9.0F, 3.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-3.0F, 11.0F, 3.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);

        MeshDefinition meshdefinition = HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);
        PartDefinition partdefinition = meshdefinition.getRoot();

        this.hat.visible = false;
        this.head.visible = false;
        this.body.visible = false;
        this.rightArm.visible = false;
        this.leftArm.visible = false;
        this.rightLeg.visible = false;
        this.leftLeg.visible = true;
        partdefinition.addOrReplaceChild(this.leftLeg.toString(), leftLegModel, PartPose.ZERO);
    }


    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        CubeListBuilder leftLegModel = CubeListBuilder.create();
        leftLegModel.texOffs(0, 0).addBox(-8.0F, -2.0F, 3.0F, 12.0F, 2.0F, 1.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-8.0F, -2.0F, -4.0F, 12.0F, 2.0F, 1.0F, false);
        leftLegModel.texOffs(0, 0).addBox(3.0F, -2.0F, -3.0F, 1.0F, 2.0F, 6.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-8.0F, -2.0F, -3.0F, 1.0F, 2.0F, 6.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-8.0F, 0.0F, -4.0F, 12.0F, 3.0F, 8.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-7.0F, 3.0F, -3.0F, 10.0F, 10.0F, 6.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-4.0F, 13.0F, 0.0F, 4.0F, 2.0F, 0.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-5.0F, 16.0F, 0.0F, 6.0F, 1.0F, 0.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-6.0F, 15.0F, 0.0F, 8.0F, 1.0F, 0.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-6.0F, 17.0F, 0.0F, 3.0F, 1.0F, 0.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-1.0F, 17.0F, 0.0F, 3.0F, 1.0F, 0.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-5.0F, 18.0F, 0.0F, 2.0F, 1.0F, 0.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-1.0F, 18.0F, 0.0F, 2.0F, 1.0F, 0.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-4.0F, 19.0F, 0.0F, 1.0F, 1.0F, 0.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-1.0F, 19.0F, 0.0F, 1.0F, 1.0F, 0.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-3.0F, 3.0F, -4.0F, 2.0F, 1.0F, 1.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-3.0F, 3.0F, 3.0F, 2.0F, 1.0F, 1.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-4.0F, 4.0F, -4.0F, 1.0F, 1.0F, 1.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-4.0F, 4.0F, 3.0F, 1.0F, 1.0F, 1.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-4.0F, 6.0F, -4.0F, 1.0F, 1.0F, 1.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-4.0F, 6.0F, 3.0F, 1.0F, 1.0F, 1.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-4.0F, 8.0F, -4.0F, 1.0F, 1.0F, 1.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-4.0F, 8.0F, 3.0F, 1.0F, 1.0F, 1.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-4.0F, 10.0F, -4.0F, 1.0F, 1.0F, 1.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-4.0F, 12.0F, -4.0F, 1.0F, 1.0F, 1.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-4.0F, 10.0F, 3.0F, 1.0F, 1.0F, 1.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-4.0F, 12.0F, 3.0F, 1.0F, 1.0F, 1.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-1.0F, 4.0F, -4.0F, 1.0F, 1.0F, 1.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-1.0F, 4.0F, 3.0F, 1.0F, 1.0F, 1.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-1.0F, 6.0F, -4.0F, 1.0F, 1.0F, 1.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-1.0F, 6.0F, 3.0F, 1.0F, 1.0F, 1.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-1.0F, 8.0F, -4.0F, 1.0F, 1.0F, 1.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-1.0F, 8.0F, 3.0F, 1.0F, 1.0F, 1.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-1.0F, 10.0F, -4.0F, 1.0F, 1.0F, 1.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-1.0F, 12.0F, -4.0F, 1.0F, 1.0F, 1.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-1.0F, 10.0F, 3.0F, 1.0F, 1.0F, 1.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-1.0F, 12.0F, 3.0F, 1.0F, 1.0F, 1.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-3.0F, 5.0F, -4.0F, 2.0F, 1.0F, 1.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-3.0F, 5.0F, 3.0F, 2.0F, 1.0F, 1.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-3.0F, 7.0F, -4.0F, 2.0F, 1.0F, 1.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-3.0F, 7.0F, 3.0F, 2.0F, 1.0F, 1.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-3.0F, 9.0F, -4.0F, 2.0F, 1.0F, 1.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-3.0F, 11.0F, -4.0F, 2.0F, 1.0F, 1.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-3.0F, 9.0F, 3.0F, 2.0F, 1.0F, 1.0F, false);
        leftLegModel.texOffs(0, 0).addBox(-3.0F, 11.0F, 3.0F, 2.0F, 1.0F, 1.0F, false);



        partdefinition.addOrReplaceChild("left_leg", leftLegModel, PartPose.offset(2.0F, 4.0F, -1.0F));
        return LayerDefinition.create(meshdefinition, 64, 32);
    }


    @Override
    public void renderToBuffer(@Nonnull MatrixStack matrixStackIn, @Nonnull IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        this.leftLeg.visible = true;
        super.renderToBuffer(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }
}
