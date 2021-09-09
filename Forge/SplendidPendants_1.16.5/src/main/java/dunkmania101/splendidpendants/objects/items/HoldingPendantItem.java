package dunkmania101.splendidpendants.objects.items;

import dunkmania101.splendidpendants.data.CustomValues;
import dunkmania101.splendidpendants.data.PendantArmorMaterial;
import dunkmania101.splendidpendants.data.models.HoldingHandsModel;
import dunkmania101.splendidpendants.objects.containers.HoldingContainer;
import dunkmania101.splendidpendants.util.PendantTools;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.List;

public class HoldingPendantItem extends PendantItem {
    public HoldingPendantItem(Properties properties) {
        super(PendantArmorMaterial.HOLDING, properties);
    }

    @Override
    public INamedContainerProvider getContainerProvider(World world, PlayerEntity playerEntity, Hand hand, ItemStack stack) {
        if (PendantTools.isEnabled(stack)) {
            return new SimpleNamedContainerProvider(
                    (id, playerInventory, openingPlayer) -> new HoldingContainer(id, playerInventory, stack),
                    stack.getDisplayName()
            );
        }
        return super.getContainerProvider(world, playerEntity, hand, stack);
    }

    @Override
    public BipedModel<LivingEntity> getCustomModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot) {
        return new HoldingHandsModel(itemStack);
    }

    @Override
    public String getCustomTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
        return CustomValues.grayTextureLocation;
    }

    @Override
    public void appendHoverText(@Nonnull ItemStack stack, World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
    }
}
