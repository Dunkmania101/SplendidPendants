package dunkmania101.splendidpendants.objects.items;

import dunkmania101.splendidpendants.data.CustomValues;
import dunkmania101.splendidpendants.data.PendantArmorMaterial;
import dunkmania101.splendidpendants.data.models.KnighthoodArmorModel;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class KnighthoodPendantItem extends PendantItem {
    public KnighthoodPendantItem(Properties properties) {
        super(PendantArmorMaterial.KNIGHTHOOD, properties);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public BipedModel<LivingEntity> getCustomModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot) {
        if (entityLiving.getPersistentData().getInt(CustomValues.renderKnighthoodKey) > 0) {
            return new KnighthoodArmorModel(itemStack);
        }
        return super.getCustomModel(entityLiving, itemStack, armorSlot);
    }

    @Override
    public String getCustomTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
        if (entity.getPersistentData().getInt(CustomValues.renderKnighthoodKey) > 0) {
            return CustomValues.grayTextureLocation;
        }
        return super.getCustomTexture(stack, entity, slot, type);
    }
}
