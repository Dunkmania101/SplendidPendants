package dunkmania101.splendidpendants.data.models;

import java.util.ArrayList;

import javax.annotation.Nonnull;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MultiBipedModel extends BaseDyeableModel {
    protected final ArrayList<HumanoidModel<LivingEntity>> children = new ArrayList<>();

    public MultiBipedModel(ItemStack stack, DyeColor defaultColor) {
        super(LayerDefinition.create(createBlankMesh(), 16, 16), stack, defaultColor);
    }

    public MultiBipedModel(ItemStack stack) {
        this(stack, DyeColor.WHITE);
    }

    public void addChild(HumanoidModel<LivingEntity> child) {
        this.children.add(child);
    }

    @Override
    public void setupAnim(@Nonnull LivingEntity entity, float a, float b, float c, float d, float e) {
        super.setupAnim(entity, a, b, c, d, e);
        for (HumanoidModel<LivingEntity> child : children) {
            child.setupAnim(entity, a, b, c, d, e);
        }
    }

    @Override
    public void prepareMobModel(@Nonnull LivingEntity entity, float a, float b, float c) {
        super.prepareMobModel(entity, a, b, c);
        for (HumanoidModel<LivingEntity> child : children) {
            child.prepareMobModel(entity, a, b, c);
        }
    }

    @Override
    public void renderToBuffer(@Nonnull PoseStack matrixStackIn, @Nonnull VertexConsumer bufferIn, int packedLightIn,
            int packedOverlayIn, float red, float green, float blue, float alpha) {
        super.renderToBuffer(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        for (HumanoidModel<LivingEntity> child : this.children) {
            child.renderToBuffer(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, getColor(0, red), getColor(1, green), getColor(2, blue), alpha);
        }
    }
}
