//package dunkmania101.splendidpendants.objects.items;
//
//import dunkmania101.splendidpendants.data.PendantArmorMaterial;
//import dunkmania101.splendidpendants.objects.containers.DyeableContainer;
//import net.minecraft.client.renderer.entity.model.BipedModel;
//import net.minecraft.entity.LivingEntity;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.inventory.EquipmentSlotType;
//import net.minecraft.inventory.container.SimpleNamedContainerProvider;
//import net.minecraft.item.ItemStack;
//import net.minecraft.util.Hand;
//import net.minecraft.world.World;
//import net.minecraftforge.api.distmarker.Dist;
//import net.minecraftforge.api.distmarker.OnlyIn;
//
//public class ConjuringPendantItem extends PendantItem {
//    public ConjuringPendantItem(Properties properties) {
//        super(PendantArmorMaterial.CONJURING, properties);
//    }
//
//    @OnlyIn(Dist.CLIENT)
//    @Override
//    public BipedModel<LivingEntity> getCustomModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot) {
//        return super.getCustomModel(entityLiving, itemStack, armorSlot);
//    }
//
//    @Override
//    public void customClickActions(World world, PlayerEntity player, Hand hand, ItemStack stack) {
//        super.customClickActions(world, player, hand, stack);
//        SimpleNamedContainerProvider newContainer = new SimpleNamedContainerProvider(
//                (id, playerInventory, openingPlayer) -> new DyeableContainer(id, playerInventory, stack),
//                stack.getDisplayName()
//        );
//        player.openContainer(newContainer);
//    }
//}
