package dunkmania101.splendidpendants.objects.items;

import dunkmania101.splendidpendants.SplendidPendants;
import dunkmania101.splendidpendants.data.PendantArmorMaterial;
import dunkmania101.splendidpendants.data.models.BlankBipedModel;
import dunkmania101.splendidpendants.data.models.HolyHaloModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class HolyPendantItem extends PendantItem {
    public HolyPendantItem(Properties properties) {
        super(PendantArmorMaterial.HOLY, properties);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public HumanoidModel<LivingEntity> getCustomModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, HumanoidModel<?> _default) {
        if (entityLiving instanceof Player) {
            Player player = (Player) entityLiving;
            if (player.getAbilities().flying && !player.isCreative() && !player.isSpectator()) {
                return new HolyHaloModel(itemStack);
            }
        }
        return new BlankBipedModel();
    }

    @Override
    public String getCustomTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        if (entity instanceof Player) {
            Player player = (Player) entity;
            if (player.getAbilities().flying && !player.isCreative() && !player.isSpectator()) {
                return SplendidPendants.modid + ":textures/blank_white.png";
            }
        }
        return super.getCustomTexture(stack, entity, slot, type);
    }
}
