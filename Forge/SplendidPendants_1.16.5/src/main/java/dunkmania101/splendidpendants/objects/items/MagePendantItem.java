// package dunkmania101.splendidpendants.objects.items;

// import dunkmania101.splendidpendants.data.CustomValues;
// import dunkmania101.splendidpendants.data.PendantArmorMaterial;
// import dunkmania101.splendidpendants.data.models.MageCloakModel;
// import net.minecraft.client.renderer.entity.model.BipedModel;
// import net.minecraft.entity.Entity;
// import net.minecraft.entity.LivingEntity;
// import net.minecraft.entity.player.PlayerEntity;
// import net.minecraft.inventory.EquipmentSlotType;
// import net.minecraft.item.ItemStack;
// import net.minecraftforge.api.distmarker.Dist;
// import net.minecraftforge.api.distmarker.OnlyIn;

// public class MagePendantItem extends PendantItem {
//     public MagePendantItem(Properties properties) {
//         super(PendantArmorMaterial.MAGE, properties);
//     }

//     @OnlyIn(Dist.CLIENT)
//     @Override
//     public BipedModel<LivingEntity> getCustomModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot) {
//         if (entityLiving instanceof PlayerEntity) {
//             PlayerEntity player = (PlayerEntity) entityLiving;
//             if (true) {
//                 return new MageCloakModel(itemStack);
//             }
//         }
//         return super.getCustomModel(entityLiving, itemStack, armorSlot);
//     }

//     @Override
//     public String getCustomTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
//         if (entity instanceof PlayerEntity) {
//             PlayerEntity player = (PlayerEntity) entity;
//             if (true) {
//                 return CustomValues.grayTextureLocation;
//             }
//         }
//         return super.getCustomTexture(stack, entity, slot, type);
//     }
// }
