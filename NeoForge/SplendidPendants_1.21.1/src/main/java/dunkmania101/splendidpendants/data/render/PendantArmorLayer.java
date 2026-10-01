package dunkmania101.splendidpendants.data.render;

import javax.annotation.Nonnull;

import com.mojang.blaze3d.vertex.PoseStack;

import dunkmania101.splendidpendants.util.PendantTools;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PendantArmorLayer<T extends LivingEntity, M extends HumanoidModel<T>, A extends HumanoidModel<T>>
        extends HumanoidArmorLayer<T, M, A> {
    private boolean hideLegs = false;

    public PendantArmorLayer(RenderLayerParent<T, M> parent, A innerModel, A outerModel, ModelManager modelManager) {
        super(parent, innerModel, outerModel, modelManager);
    }

    @Override
    public void render(@Nonnull PoseStack poseStack, @Nonnull MultiBufferSource bufferSource, int packedLight,
            @Nonnull T entity, float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks,
            float netHeadYaw, float headPitch) {
        this.hideLegs = PendantTools.isAtlanticTailReplacingLegs(entity);
        try {
            super.render(poseStack, bufferSource, packedLight, entity, limbSwing, limbSwingAmount, partialTick,
                    ageInTicks, netHeadYaw, headPitch);
        } finally {
            this.hideLegs = false;
        }
    }

    @Override
    protected void setPartVisibility(@Nonnull A model, @Nonnull EquipmentSlot slot) {
        if (this.hideLegs && (slot == EquipmentSlot.LEGS || slot == EquipmentSlot.FEET)) {
            model.setAllVisible(false);
            return;
        }
        super.setPartVisibility(model, slot);
    }
}
