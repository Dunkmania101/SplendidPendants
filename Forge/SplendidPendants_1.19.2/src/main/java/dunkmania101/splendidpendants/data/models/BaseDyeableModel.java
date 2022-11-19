package dunkmania101.splendidpendants.data.models;

import javax.annotation.Nonnull;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import dunkmania101.splendidpendants.data.CustomValues;
import dunkmania101.splendidpendants.objects.items.DyeSpongeItem;
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
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.items.ItemStackHandler;

@OnlyIn(Dist.CLIENT)
public class BaseDyeableModel extends HumanoidModel<LivingEntity> {
    private float[] colors;

    public BaseDyeableModel(LayerDefinition layer, ItemStack stack, DyeColor defaultColor, float blendRed, float blendGreen, float blendBlue) {
        super(layer.bakeRoot());

        this.colors = defaultColor.getTextureDiffuseColors();

        ItemStackHandler itemStackHandler = Tools.getItemStackHandlerOfStack(stack, CustomValues.dyeableSize, true);
        if (itemStackHandler.getSlots() > 0) {
            ItemStack storedStack = itemStackHandler.getStackInSlot(0);
            Item storedDye = storedStack.getItem();
            if (storedDye instanceof DyeItem) {
                this.colors = ((DyeItem) storedDye).getDyeColor().getTextureDiffuseColors();
            } else if (storedDye instanceof DyeSpongeItem) {
                int colorInt = ((DyeSpongeItem) storedDye).getColor(storedStack);
                this.colors = new float[] { (float) Tools.getRed(colorInt) / 255f,
                        (float) Tools.getGreen(colorInt) / 255f, (float) Tools.getBlue(colorInt) / 255f };
            }
        }

        this.colors[0] = Tools.blendColors(this.colors[0], blendRed);
        this.colors[1] = Tools.blendColors(this.colors[1], blendGreen);
        this.colors[2] = Tools.blendColors(this.colors[2], blendBlue);
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

    protected float getColor(int index, float base) {
        return Tools.blendColors(this.colors[index], base);
    }

    @Override
    public void renderToBuffer(@Nonnull PoseStack matrixStackIn, @Nonnull VertexConsumer bufferIn, int packedLightIn,
            int packedOverlayIn, float red, float green, float blue, float alpha) {
        setPartsVisible();
        super.renderToBuffer(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, getColor(0, red),
                getColor(1, green), getColor(2, blue), alpha);
    }
}
