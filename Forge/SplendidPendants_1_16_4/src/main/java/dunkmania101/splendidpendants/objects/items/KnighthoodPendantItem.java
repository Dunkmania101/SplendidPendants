package dunkmania101.splendidpendants.objects.items;

import dunkmania101.splendidpendants.SplendidPendants;
import dunkmania101.splendidpendants.data.CustomValues;
import dunkmania101.splendidpendants.data.PendantArmorMaterial;
import dunkmania101.splendidpendants.data.models.KnighthoodArmorModel;
import dunkmania101.splendidpendants.objects.containers.DyeableContainer;
import dunkmania101.splendidpendants.util.PendantTools;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import java.util.List;

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
            return SplendidPendants.modid + ":textures/blank_gray.png";
        }
        return super.getCustomTexture(stack, entity, slot, type);
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
        if (PendantTools.isEnabled(stack)) {
            CompoundNBT data = player.getPersistentData();
            if (!data.contains(CustomValues.hasKnighthoodKey)) {
                data.putString(CustomValues.hasKnighthoodKey, "");
            }
        }
    }

    @Override
    public void customClickActions(World world, PlayerEntity player, Hand hand, ItemStack stack) {
        super.customClickActions(world, player, hand, stack);
        SimpleNamedContainerProvider newContainer = new SimpleNamedContainerProvider(
                (id, playerInventory, openingPlayer) -> new DyeableContainer(id, playerInventory, stack),
                stack.getDisplayName()
        );
        player.openContainer(newContainer);
    }

    @Override
    public void addInformation(@Nonnull ItemStack stack, World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslationTextComponent("msg.splendidpendants.dyeable_sneak_use_instructions"));
        tooltip.add(new TranslationTextComponent("msg.splendidpendants.divider"));
    }
}
