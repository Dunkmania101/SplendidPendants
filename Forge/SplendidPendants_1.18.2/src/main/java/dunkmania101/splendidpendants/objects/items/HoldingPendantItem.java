package dunkmania101.splendidpendants.objects.items;

import java.util.List;

import javax.annotation.Nonnull;

import dunkmania101.splendidpendants.data.CustomValues;
import dunkmania101.splendidpendants.data.PendantArmorMaterial;
import dunkmania101.splendidpendants.data.models.HoldingHandsModel;
import dunkmania101.splendidpendants.objects.containers.HoldingContainer;
import dunkmania101.splendidpendants.util.PendantTools;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class HoldingPendantItem extends PendantItem {
    public HoldingPendantItem(Properties properties) {
        super(PendantArmorMaterial.HOLDING, properties);
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

    @Override
    public HumanoidModel<LivingEntity> getCustomModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, HumanoidModel<?> _default) {
        return new HoldingHandsModel(itemStack);
    }

    @Override
    public String getCustomTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return CustomValues.grayTextureLocation;
    }

    @Override
    public void appendHoverText(@Nonnull ItemStack stack, Level worldIn, @Nonnull List<Component> tooltip, @Nonnull TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
    }
}
