package dunkmania101.splendidpendants.data.models;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DyeColor;

@Environment(EnvType.CLIENT)
public class FakeHolyHaloModel extends BipedEntityModel<LivingEntity> {
    private final DyeColor color;

    public FakeHolyHaloModel(DyeColor color) {
        super(1F);

        this.color = color;

        this.textureWidth = 32;
        this.textureHeight = 32;

        ModelPart fakeHolyHaloModel = new ModelPart(this);
        fakeHolyHaloModel.setPivot(0.0F, 23.5F, 0.0F);
        fakeHolyHaloModel.setTextureOffset(0, 0).addCuboid(-7.1747F, -19.2114F, 1.0686F, 12.0F, 1.0F, 3.0F, 0.0F, false);
        fakeHolyHaloModel.setTextureOffset(0, 0).addCuboid(-7.1747F, -19.2114F, -7.9314F, 12.0F, 1.0F, 3.0F, 0.0F, false);
        fakeHolyHaloModel.setTextureOffset(0, 0).addCuboid(1.8253F, -19.2114F, -4.9314F, 3.0F, 1.0F, 6.0F, 0.0F, false);
        fakeHolyHaloModel.setTextureOffset(0, 0).addCuboid(-7.1747F, -19.2114F, -4.9314F, 3.0F, 1.0F, 6.0F, 0.0F, false);
        fakeHolyHaloModel.setTextureOffset(0, 0).addCuboid(2.8253F, -20.2114F, -5.9314F, 1.0F, 1.0F, 8.0F, 0.0F, false);
        fakeHolyHaloModel.setTextureOffset(0, 0).addCuboid(-6.1747F, -20.2114F, -5.9314F, 1.0F, 1.0F, 8.0F, 0.0F, false);
        fakeHolyHaloModel.setTextureOffset(0, 0).addCuboid(-6.1747F, -20.2114F, -6.9314F, 10.0F, 1.0F, 1.0F, 0.0F, false);
        fakeHolyHaloModel.setTextureOffset(0, 0).addCuboid(-6.1747F, -20.2114F, 2.0686F, 10.0F, 1.0F, 1.0F, 0.0F, false);
        fakeHolyHaloModel.setTextureOffset(0, 0).addCuboid(-6.1747F, -18.2114F, 2.0686F, 10.0F, 1.0F, 1.0F, 0.0F, false);
        fakeHolyHaloModel.setTextureOffset(0, 0).addCuboid(2.8253F, -18.2114F, -5.9314F, 1.0F, 1.0F, 8.0F, 0.0F, false);
        fakeHolyHaloModel.setTextureOffset(0, 0).addCuboid(-6.1747F, -18.2114F, -6.9314F, 10.0F, 1.0F, 1.0F, 0.0F, false);
        fakeHolyHaloModel.setTextureOffset(0, 0).addCuboid(-6.1747F, -18.2114F, -5.9314F, 1.0F, 1.0F, 8.0F, 0.0F, false);
        setRotationAngle(fakeHolyHaloModel, -0.2182F, 0.0F, 0.1309F);

        this.helmet = fakeHolyHaloModel;
        this.head = new ModelPart(this);
        this.torso = new ModelPart(this);
        this.rightArm = new ModelPart(this);
        this.leftArm = new ModelPart(this);
        this.rightLeg = new ModelPart(this);
        this.leftLeg = new ModelPart(this);
    }

    public void setRotationAngle(ModelPart modelPart, float x, float y, float z) {
        modelPart.pivotX = x;
        modelPart.pivotY = y;
        modelPart.pivotZ = z;
    }

    @Override
    public void render(MatrixStack matrixStackIn, VertexConsumer consumerIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        float[] colors = this.color.getColorComponents();
        this.helmet.visible = true;
        super.render(matrixStackIn, consumerIn, packedLightIn, packedOverlayIn, colors[0], colors[1], colors[2], alpha);
    }
}
