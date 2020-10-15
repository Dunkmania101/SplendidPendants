package dunkmania101.splendidpendants.data.compat;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import dunkmania101.splendidpendants.SplendidPendants;
import dunkmania101.splendidpendants.data.CustomValues;
import dunkmania101.splendidpendants.data.models.BlankBipedModel;
import dunkmania101.splendidpendants.init.ItemInit;
import dunkmania101.splendidpendants.objects.items.LocketItem;
import dunkmania101.splendidpendants.objects.items.PendantItem;
import dunkmania101.splendidpendants.util.PendantTools;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.InterModComms;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.CuriosCapability;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.type.capability.ICurio;

import javax.annotation.Nonnull;

public class CuriosCompat {
    public static void enqueueImc() {
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> new SlotTypeMessage.Builder(CustomValues.pendantCuriosSlotName)
                .icon(new ResourceLocation(SplendidPendants.modid, "gui/pendant_slot"))
                .build());
    }

    public static ICapabilityProvider initPendantCapabilities(ItemStack stack) {
        ICurio curio = new ICurio() {
            @Override
            public boolean canRightClickEquip() {
                return false;
            }

            @Override
            public boolean canRender(String identifier, int index, LivingEntity livingEntity) {
                return identifier.equals(CustomValues.pendantCuriosSlotName);
            }

            @Override
            public boolean canEquip(String identifier, LivingEntity entityLivingBase) {
                if (identifier.equals(CustomValues.pendantCuriosSlotName)) {
                    if (entityLivingBase instanceof PlayerEntity) {
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
            public void curioTick(String identifier, int index, LivingEntity livingEntity) {
                if (livingEntity instanceof PlayerEntity) {
                    PlayerEntity player = (PlayerEntity) livingEntity;
                    stack.onArmorTick(player.getEntityWorld(), player);
                }
            }

            @Override
            @OnlyIn(Dist.CLIENT)
            public void render(String identifier, int index, MatrixStack matrixStack, IRenderTypeBuffer renderTypeBuffer, int light, LivingEntity livingEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
                if (PendantTools.isEnabled(stack)) {
                    if (livingEntity instanceof PlayerEntity) {
                        PlayerEntity player = (PlayerEntity) livingEntity;
                        Item pendantItem = stack.getItem();
                        ItemStack chestStack = player.inventory.armorInventory.get(EquipmentSlotType.CHEST.getIndex());
                        Item chestItem = chestStack.getItem();
                        boolean chestEnabled = PendantTools.isEnabled(chestStack);
                        if (chestItem instanceof LocketItem) {
                            chestStack = PendantTools.getPrioritizedStoredStack(chestStack, player);
                            chestItem = chestStack.getItem();
                        }
                        if (chestItem != pendantItem || (!chestEnabled && !PendantTools.isEnabled(chestStack))) {
                            BipedModel<LivingEntity> model = pendantItem.getArmorModel(livingEntity, stack, EquipmentSlotType.CHEST, new BlankBipedModel());
                            String texture = pendantItem.getArmorTexture(stack, livingEntity, EquipmentSlotType.CHEST, null);
                            if (model != null && texture != null) {
                                model.setRotationAngles(player, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
                                model.setLivingAnimations(player, limbSwing, limbSwingAmount, partialTicks);
                                RenderHelper.followBodyRotations(player, model);
                                IVertexBuilder vertexBuilder = ItemRenderer.getBuffer(renderTypeBuffer, model.getRenderType(new ResourceLocation(texture)), false, stack.hasEffect());
                                model.render(matrixStack, vertexBuilder, light, OverlayTexture.NO_OVERLAY, 1F, 1F, 1F, 1F);
                            }
                        }
                    }
                }
            }
        };

        return new ICapabilityProvider() {
            private final LazyOptional<ICurio> curioOpt = LazyOptional.of(() -> curio);

            @Nonnull
            @Override
            public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, Direction side) {
                return CuriosCapability.ITEM.orEmpty(cap, curioOpt);
            }
        };
    }
}
