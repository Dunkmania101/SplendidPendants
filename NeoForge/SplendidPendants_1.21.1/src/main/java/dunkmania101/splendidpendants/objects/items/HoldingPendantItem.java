package dunkmania101.splendidpendants.objects.items;

import dunkmania101.splendidpendants.data.CustomValues;
import dunkmania101.splendidpendants.data.models.HoldingHandsModel;
import dunkmania101.splendidpendants.init.ArmorMaterialInit;
import dunkmania101.splendidpendants.objects.containers.HoldingContainer;
import dunkmania101.splendidpendants.util.PendantTools;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public class HoldingPendantItem extends PendantItem {
    public HoldingPendantItem(Properties properties) {
        super(ArmorMaterialInit.HOLDING, properties);
    }

    @Override
    public MenuProvider getContainerProvider(Level world, Player playerEntity, InteractionHand hand, ItemStack stack) {
        if (PendantTools.isEnabled(stack)) {
            return new SimpleMenuProvider(
                    (id, playerInventory, openingPlayer) -> new HoldingContainer(id, playerInventory, stack),
                    stack.getDisplayName()
            );
        }
        return super.getContainerProvider(world, playerEntity, hand, stack);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public HumanoidModel<LivingEntity> getCustomModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, HumanoidModel<?> _default) {
        return new HoldingHandsModel(itemStack);
    }

    @Override
    public ResourceLocation getCustomTexture(ItemStack stack, Entity entity, EquipmentSlot slot) {
        return CustomValues.grayTextureLocation;
    }

    @Override
    public String getAltInvKey() {
        return "msg.splendidpendants.holding_sneak_use_instructions";
    }
}
