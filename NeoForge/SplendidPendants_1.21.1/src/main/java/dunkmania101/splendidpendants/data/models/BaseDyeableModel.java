package dunkmania101.splendidpendants.data.models;

import javax.annotation.Nonnull;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import dunkmania101.splendidpendants.util.Tools;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BaseDyeableModel extends HumanoidModel<LivingEntity> {
    private final int color;

    public BaseDyeableModel(LayerDefinition layer, ItemStack stack, DyeColor defaultColor, float blendRed, float blendGreen, float blendBlue) {
        super(layer.bakeRoot());

        int dyedColor = Tools.getDyedColor(stack, defaultColor.getTextureDiffuseColor());
        this.color = Tools.scaleColor(dyedColor, blendRed, blendGreen, blendBlue);
    }

    public BaseDyeableModel(LayerDefinition layer, ItemStack stack, DyeColor defaultColor) {
        this(layer, stack, defaultColor, 1F, 1F, 1F);
    }

    public static MeshDefinition createBlankMesh(float scale) {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0),
                PartPose.offset(0.0F, 0.0F + scale, 0.0F));
        partdefinition.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(32, 0),
                PartPose.offset(0.0F, 0.0F + scale, 0.0F));
        partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16),
                PartPose.offset(0.0F, 0.0F + scale, 0.0F));
        partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16),
                PartPose.offset(-5.0F, 2.0F + scale, 0.0F));
        partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(40, 16).mirror(),
                PartPose.offset(5.0F, 2.0F + scale, 0.0F));
        partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16),
                PartPose.offset(-1.9F, 12.0F + scale, 0.0F));
        partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 16).mirror(),
                PartPose.offset(1.9F, 12.0F + scale, 0.0F));
        return meshdefinition;
    }

    public static MeshDefinition createBlankMesh() {
        return createBlankMesh(1F);
    }

    public static MeshDefinition createMesh() {
        return createMesh(CubeDeformation.NONE, 1F);
    }

    public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }

    protected void setPartsVisible() {
    }

    protected int getBlendedColor(int baseColor) {
        return Tools.multiplyColors(this.color, baseColor);
    }

    @Override
    public void renderToBuffer(@Nonnull PoseStack matrixStackIn, @Nonnull VertexConsumer bufferIn, int packedLightIn,
            int packedOverlayIn, int color) {
        setPartsVisible();
        super.renderToBuffer(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, getBlendedColor(color));
    }
}
