package dunkmania101.splendidpendants.data.compat;

import javax.annotation.Nonnull;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import dunkmania101.splendidpendants.SplendidPendants;
import dunkmania101.splendidpendants.data.CustomValues;
import dunkmania101.splendidpendants.data.models.BlankBipedModel;
import dunkmania101.splendidpendants.init.ItemInit;
import dunkmania101.splendidpendants.objects.items.LocketItem;
import dunkmania101.splendidpendants.objects.items.PendantItem;
import dunkmania101.splendidpendants.util.PendantTools;
import dunkmania101.splendidpendants.util.Tools;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.entity.player.Player;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.InterModComms;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.CuriosCapability;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.type.capability.ICurio;

public class CuriosCompat {
    public static void enqueueImc() {
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> new SlotTypeMessage.Builder(CustomValues.pendantCuriosSlotName)
                .icon(new ResourceLocation(SplendidPendants.modid, "gui/pendant_slot"))
                .build());
    }

    public static ICapabilityProvider initPendantCapabilities(ItemStack stack) {
        ICurio pendantCurio = new ICurio() {
            @Override
            public boolean canEquipFromUse(SlotContext slotContext) {
                return false;
            }

            // @Override
            // public boolean canRender(String identifier, int index, LivingEntity livingEntity) {
                // return identifier.equals(CustomValues.pendantCuriosSlotName);
            // }

            @Override
            public boolean canEquip(String identifier, LivingEntity entityLivingBase) {
                if (identifier.equals(CustomValues.pendantCuriosSlotName)) {
                    if (entityLivingBase instanceof Player) {
                        Item pendantItem = stack.getItem();
                        if (CuriosApi.getCuriosHelper().findEquippedCurio(ItemInit.ATLANTIC_PENDANT.get(), entityLivingBase).isPresent()) {
                            return false;
                        }
                        if (CuriosApi.getCuriosHelper().findEquippedCurio(ItemInit.KNIGHTHOOD_PENDANT.get(), entityLivingBase).isPresent()) {
                            return false;
                        }
                        if (CuriosApi.getCuriosHelper().findEquippedCurio(ItemInit.HOLY_PENDANT.get(), entityLivingBase).isPresent()) {
                            return false;
                        }
                        if (CuriosApi.getCuriosHelper().findEquippedCurio(ItemInit.LOCKET.get(), entityLivingBase).isPresent()) {
                            return false;
                        }
                        return pendantItem instanceof PendantItem;
                    }
                }
                return false;
            }

            @Override
            @OnlyIn(Dist.CLIENT)
            public void render(String identifier, int index, MatrixStack matrixStack, IRenderTypeBuffer renderTypeBuffer, int light, LivingEntity livingEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
                if (PendantTools.isEnabled(stack)) {
                    if (livingEntity instanceof Player) {
                        Player player = (Player) livingEntity;
                        Item pendantItem = stack.getItem();
                        ItemStack chestStack = player.inventory.armor.get(EquipmentSlot.CHEST.getIndex());
                        Item chestItem = chestStack.getItem();
                        boolean chestEnabled = PendantTools.isEnabled(chestStack);
                        if (chestItem instanceof LocketItem) {
                            chestStack = PendantTools.getPrioritizedStoredStack(chestStack, player);
                            chestItem = chestStack.getItem();
                        }
                        if (chestItem != pendantItem || (!chestEnabled && !PendantTools.isEnabled(chestStack))) {
                            BipedModel<LivingEntity> model = pendantItem.getArmorModel(livingEntity, stack, EquipmentSlot.CHEST, new BlankBipedModel());
                            String texture = pendantItem.getArmorTexture(stack, livingEntity, EquipmentSlot.CHEST, null);
                            if (model != null && texture != null) {
                                model.setupAnim(player, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
                                model.prepareMobModel(player, limbSwing, limbSwingAmount, partialTicks);
                                Tools.followBodyRotations(player, model);
                                IVertexBuilder vertexBuilder = ItemRenderer.getFoilBufferDirect(renderTypeBuffer, model.renderType(new ResourceLocation(texture)), false, stack.hasFoil());
                                model.renderToBuffer(matrixStack, vertexBuilder, light, OverlayTexture.NO_OVERLAY, 1F, 1F, 1F, 1F);
                            }
                        }
                    }
                }
            }
        };

        return new ICapabilityProvider() {
            private final LazyOptional<ICurio> pendantCurioOpt = LazyOptional.of(() -> pendantCurio);

            @Nonnull
            @Override
            public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, Direction side) {
                return CuriosCapability.ITEM.orEmpty(cap, pendantCurioOpt);
            }
        };
    }
}
