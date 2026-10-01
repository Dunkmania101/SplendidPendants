package dunkmania101.splendidpendants.objects.items;

import dunkmania101.splendidpendants.data.CustomValues;
import dunkmania101.splendidpendants.data.models.AtlanticTailModel;
import dunkmania101.splendidpendants.data.models.BlankBipedModel;
import dunkmania101.splendidpendants.init.ArmorMaterialInit;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public class AtlanticPendantItem extends PendantItem {
    protected boolean ARMOR_TOGGLE = false;

    public AtlanticPendantItem(Properties properties) {
        super(ArmorMaterialInit.ATLANTIC, properties);
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
    public ResourceLocation getCustomTexture(ItemStack stack, Entity entity, EquipmentSlot slot) {
        if (entity.isInWater()) {
            return CustomValues.whiteTextureLocation;
        }
        return super.getCustomTexture(stack, entity, slot);
    }

    public void nextRenderWithArmor() {
        this.ARMOR_TOGGLE = true;
    }
}
