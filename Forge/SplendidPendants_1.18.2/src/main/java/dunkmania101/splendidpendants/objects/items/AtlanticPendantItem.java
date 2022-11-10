package dunkmania101.splendidpendants.objects.items;

import dunkmania101.splendidpendants.SplendidPendants;
import dunkmania101.splendidpendants.data.PendantArmorMaterial;
import dunkmania101.splendidpendants.data.models.AtlanticTailModel;
import dunkmania101.splendidpendants.data.models.BlankBipedModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class AtlanticPendantItem extends PendantItem {
    protected boolean ARMOR_TOGGLE = false;

    public AtlanticPendantItem(Properties properties) {
        super(PendantArmorMaterial.ATLANTIC, properties);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public HumanoidModel<LivingEntity> getCustomModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, HumanoidModel<?> _default) {
        boolean armor_toggle = this.ARMOR_TOGGLE;
        if (this.ARMOR_TOGGLE) {
            this.ARMOR_TOGGLE = false;
        }
        if (entityLiving.isInWater()) {
            return new AtlanticTailModel(itemStack, armor_toggle);
        }
        return new BlankBipedModel();
    }

    @Override
    public String getCustomTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        if (entity.isInWater()) {
            return SplendidPendants.modid + ":textures/blank_white.png";
        }
        return super.getCustomTexture(stack, entity, slot, type);
    }

    public void nextRenderWithArmor() {
        this.ARMOR_TOGGLE = true;
    }
}
