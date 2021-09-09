package dunkmania101.splendidpendants.data.models;

import java.util.ArrayList;

import javax.annotation.Nonnull;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MultiBipedModel extends BipedModel<LivingEntity> {
    protected final ArrayList<BipedModel<LivingEntity>> children = new ArrayList<>();

    public MultiBipedModel() {
        super(1F);
    }

    public void addChild(BipedModel<LivingEntity> child) {
        this.children.add(child);
    }

    @Override
    public void setupAnim(@Nonnull LivingEntity entity, float a, float b, float c, float d, float e) {
        super.setupAnim(entity, a, b, c, d, e);
        for (BipedModel<LivingEntity> child : this.children) {
            child.setupAnim(entity, a, b, c, d, e);
        }
    }

    @Override
    public void prepareMobModel(@Nonnull LivingEntity entity, float a, float b, float c) {
        super.prepareMobModel(entity, a, b, c);
        for (BipedModel<LivingEntity> child : this.children) {
            child.prepareMobModel(entity, a, b, c);
        }
    }

    @Override
    public void renderToBuffer(@Nonnull MatrixStack matrixStackIn, @Nonnull IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        for (BipedModel<LivingEntity> child : this.children) {
            child.renderToBuffer(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        }
    }
}
