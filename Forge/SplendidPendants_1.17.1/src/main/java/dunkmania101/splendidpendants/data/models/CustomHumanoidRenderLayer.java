package dunkmania101.splendidpendants.data.models;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.world.entity.LivingEntity;

public class CustomHumanoidRenderLayer extends HumanoidArmorLayer<LivingEntity, HumanoidModel<LivingEntity>, HumanoidModel<LivingEntity>> {
    public CustomHumanoidRenderLayer(RenderLayerParent<LivingEntity, HumanoidModel<LivingEntity>> parent, HumanoidModel<LivingEntity> model) {
        super(parent, model, model);
    }
}
