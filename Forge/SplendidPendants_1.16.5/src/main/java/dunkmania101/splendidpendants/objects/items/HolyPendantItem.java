package dunkmania101.splendidpendants.objects.items;

import dunkmania101.splendidpendants.data.CustomValues;
import dunkmania101.splendidpendants.data.PendantArmorMaterial;
import dunkmania101.splendidpendants.data.models.BlankBipedModel;
import dunkmania101.splendidpendants.data.models.HolyHaloModel;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class HolyPendantItem extends PendantItem {
    public HolyPendantItem(Properties properties) {
        super(PendantArmorMaterial.HOLY, properties);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public BipedModel<LivingEntity> getCustomModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot) {
        if (entityLiving instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entityLiving;
            if (player.abilities.flying && !player.isCreative() && !player.isSpectator()) {
                return new HolyHaloModel(itemStack);
            }
        }
        return new BlankBipedModel();
    }

    @Override
    public String getCustomTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
        if (entity instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entity;
            if (player.abilities.flying && !player.isCreative() && !player.isSpectator()) {
                return CustomValues.whiteTextureLocation;
            }
        }
        return super.getCustomTexture(stack, entity, slot, type);
    }
}
