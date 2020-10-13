package dunkmania101.splendidpendants.objects.items;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import dunkmania101.splendidpendants.SplendidPendants;
import dunkmania101.splendidpendants.data.CustomValues;
import dunkmania101.splendidpendants.data.PendantArmorMaterial;
import dunkmania101.splendidpendants.data.models.KnighthoodArmorModel;
import dunkmania101.splendidpendants.objects.containers.DyeableContainer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import java.util.List;

public class KnighthoodPendantItem extends PendantItem {
    public KnighthoodPendantItem(Properties properties) {
        super(PendantArmorMaterial.KNIGHTHOOD, properties);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> customAttributeModifiers() {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        int armor = this.getArmorMaterial().getDamageReductionAmount(slot);
        float armorToughness = getArmorMaterial().getToughness();
        float knockBackReduce = getArmorMaterial().func_230304_f_();
        builder.put(Attributes.field_233826_i_, new AttributeModifier(CustomValues.knighthoodArmorUUID, CustomValues.knighthoodArmorName, armor, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.field_233827_j_, new AttributeModifier(CustomValues.knighthoodArmorToughnessUUID, CustomValues.knighthoodArmorToughnessName, armorToughness, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.field_233820_c_, new AttributeModifier(CustomValues.knighthoodKnockBackResistUUID, CustomValues.knighthoodKnockBackResistName, knockBackReduce, AttributeModifier.Operation.ADDITION));
        return builder.build();
    }

    @Override
    public BipedModel<LivingEntity> getCustomModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot) {
        if (entityLiving.getPersistentData().getInt(CustomValues.renderKnighthoodKey) > 0) {
            return new KnighthoodArmorModel(itemStack);
        }
        return super.getCustomModel(entityLiving, itemStack, armorSlot);
    }

    @Override
    public String getCustomTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
        return SplendidPendants.modid + ":textures/blank_gray.png";
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
    public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslationTextComponent("msg.splendidpendants.dyeable_sneak_use_instructions"));
        tooltip.add(new TranslationTextComponent("msg.splendidpendants.divider"));
    }
}
