package dunkmania101.splendidpendants.objects.items;

import dunkmania101.splendidpendants.data.CustomValues;
import dunkmania101.splendidpendants.data.PendantArmorMaterial;
import dunkmania101.splendidpendants.data.models.AtlanticTailModel;
import dunkmania101.splendidpendants.data.models.BlankBipedModel;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class AtlanticPendantItem extends PendantItem {
    public AtlanticPendantItem(Properties properties) {
        super(PendantArmorMaterial.ATLANTIC, properties);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public BipedModel<LivingEntity> getCustomModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot) {
        if (entityLiving.isInWater()) {
            return new AtlanticTailModel(itemStack);
        }
        return new BlankBipedModel();
    }

    @Override
    public String getCustomTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
        if (entity.isInWater()) {
            return CustomValues.whiteTextureLocation;
        }
        return super.getCustomTexture(stack, entity, slot, type);
    }
}
