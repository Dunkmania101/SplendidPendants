package dunkmania101.splendidpendants.objects.items;

import dunkmania101.splendidpendants.data.CustomValues;
import dunkmania101.splendidpendants.data.models.BlankBipedModel;
import dunkmania101.splendidpendants.data.models.KnighthoodArmorModel;
import dunkmania101.splendidpendants.init.ArmorMaterialInit;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public class KnighthoodPendantItem extends PendantItem {
    protected boolean TAIL_TOGGLE = false;

    public KnighthoodPendantItem(Properties properties) {
        super(ArmorMaterialInit.KNIGHTHOOD, properties);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public HumanoidModel<LivingEntity> getCustomModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, HumanoidModel<?> _default) {
        boolean tail_toggle = this.TAIL_TOGGLE;
        if (this.TAIL_TOGGLE) {
            this.TAIL_TOGGLE = false;
        }
        if (entityLiving.getPersistentData().getInt(CustomValues.renderKnighthoodKey) > 0) {
            return new KnighthoodArmorModel(itemStack, tail_toggle);
        }
        return new BlankBipedModel();
    }

    @Override
    public ResourceLocation getCustomTexture(ItemStack stack, Entity entity, EquipmentSlot slot) {
        if (entity.getPersistentData().getInt(CustomValues.renderKnighthoodKey) > 0) {
            return CustomValues.grayTextureLocation;
        }
        return super.getCustomTexture(stack, entity, slot);
    }

    public void nextRenderWithTail() {
        this.TAIL_TOGGLE = true;
    }
}
