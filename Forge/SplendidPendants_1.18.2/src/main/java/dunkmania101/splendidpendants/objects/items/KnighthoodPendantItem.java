package dunkmania101.splendidpendants.objects.items;

import dunkmania101.splendidpendants.SplendidPendants;
import dunkmania101.splendidpendants.data.CustomValues;
import dunkmania101.splendidpendants.data.PendantArmorMaterial;
import dunkmania101.splendidpendants.data.models.BlankBipedModel;
import dunkmania101.splendidpendants.data.models.KnighthoodArmorModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class KnighthoodPendantItem extends PendantItem {
    public KnighthoodPendantItem(Properties properties) {
        super(PendantArmorMaterial.KNIGHTHOOD, properties);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public HumanoidModel<LivingEntity> getCustomModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, HumanoidModel<?> _default) {
        if (entityLiving.getPersistentData().getInt(CustomValues.renderKnighthoodKey) > 0) {
            return new KnighthoodArmorModel(itemStack);
        }
        return new BlankBipedModel();
    }

    @Override
    public String getCustomTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        if (entity.getPersistentData().getInt(CustomValues.renderKnighthoodKey) > 0) {
            return SplendidPendants.modid + ":textures/blank_gray.png";
        }
        return super.getCustomTexture(stack, entity, slot, type);
    }
}
