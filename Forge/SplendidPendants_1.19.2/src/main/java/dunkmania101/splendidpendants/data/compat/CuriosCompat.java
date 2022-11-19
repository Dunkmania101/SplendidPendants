package dunkmania101.splendidpendants.data.compat;

import javax.annotation.Nonnull;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import dunkmania101.splendidpendants.SplendidPendants;
import dunkmania101.splendidpendants.data.CustomValues;
import dunkmania101.splendidpendants.data.models.BlankBipedModel;
import dunkmania101.splendidpendants.init.ItemInit;
import dunkmania101.splendidpendants.objects.items.LocketItem;
import dunkmania101.splendidpendants.objects.items.PendantItem;
import dunkmania101.splendidpendants.util.PendantTools;
import dunkmania101.splendidpendants.util.Tools;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.InterModComms;
import top.theillusivec4.curios.api.CuriosCapability;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;
import top.theillusivec4.curios.api.client.ICurioRenderer;
import top.theillusivec4.curios.api.type.capability.ICurio;

public class CuriosCompat {
    public static void enqueueImc() {
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE,
                () -> new SlotTypeMessage.Builder(CustomValues.pendantCuriosSlotName)
                        .icon(new ResourceLocation(SplendidPendants.modid, "gui/pendant_slot")).build());
    }

    public static void curiosClientSetup() {
        ItemInit.ITEMS.getEntries().forEach((entry) -> {
            Item item = entry.get();
            if (item instanceof PendantItem) {
                CuriosRendererRegistry.register(item, () -> new CuriosCompat.PendantCurioRenderer());
            }
        });
    }

    public static ICapabilityProvider initPendantCapabilities(ItemStack stack) {
        ICurio pendantCurio = new ICurio() {
            @Override
            public boolean canEquipFromUse(SlotContext slotContext) {
                return false;
            }

            @Override
            public ItemStack getStack() {
                return stack;
            }

            @Override
            public void curioTick(SlotContext slotContext) {
                ICurio.super.curioTick(slotContext);
                if (slotContext.entity() instanceof Player player) {
                    if (!slotContext.visible()) {
                        player.getPersistentData().putString(CustomValues.noRenderAtlanticKey, "");
                    }
                }
            }

            // @Override
            // public boolean canRender(String identifier, int index, LivingEntity
            // livingEntity) {
            // return identifier.equals(CustomValues.pendantCuriosSlotName);
            // }

            @Override
            public boolean canEquip(SlotContext slotContext) {
                // return slotContext.identifier().equals(CustomValues.pendantCuriosSlotName);
                if (slotContext.identifier().equals(CustomValues.pendantCuriosSlotName)) {
                    // if (slotContext.entity() instanceof Player) {
                    Item pendantItem = stack.getItem();
                    // if (CuriosApi.getCuriosHelper()
                    // .findEquippedCurio(ItemInit.ATLANTIC_PENDANT.get(),
                    // entityLivingBase).isPresent()) {
                    // return false;
                    // }
                    // if (CuriosApi.getCuriosHelper()
                    // .findEquippedCurio(ItemInit.KNIGHTHOOD_PENDANT.get(),
                    // entityLivingBase).isPresent()) {
                    // return false;
                    // }
                    // if
                    // (CuriosApi.getCuriosHelper().findEquippedCurio(ItemInit.HOLY_PENDANT.get(),
                    // entityLivingBase)
                    // .isPresent()) {
                    // return false;
                    // }
                    // if (CuriosApi.getCuriosHelper().findEquippedCurio(ItemInit.LOCKET.get(),
                    // entityLivingBase)
                    // .isPresent()) {
                    // return false;
                    // }
                    return pendantItem instanceof PendantItem;
                    // }
                }
                return false;
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

    public static class PendantCurioRenderer implements ICurioRenderer {
        @Override
        public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack stack, SlotContext slotContext,
                PoseStack matrixStack, RenderLayerParent<T, M> renderLayerParent, MultiBufferSource renderTypeBuffer,
                int light, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks,
                float netHeadYaw, float headPitch) {
            if (slotContext.entity() instanceof Player player) {
                if (slotContext.visible()) {
                    player.getPersistentData().remove(CustomValues.noRenderAtlanticKey);
                    if (PendantTools.isEnabled(stack)) {
                        Item item = stack.getItem();
                        ItemStack chestStack = player.getInventory().armor.get(EquipmentSlot.CHEST.getIndex());
                        Item chestItem = chestStack.getItem();
                        boolean chestEnabled = PendantTools.isEnabled(chestStack);
                        if (chestItem instanceof LocketItem) {
                            chestStack = PendantTools.getPrioritizedStoredStack(chestStack, player);
                            chestItem = chestStack.getItem();
                        }
                        if ((chestItem != item || (!chestEnabled && !PendantTools.isEnabled(chestStack)))
                            && (item instanceof PendantItem pendantItem)) {
                            HumanoidModel<T> model = (HumanoidModel<T>) pendantItem.getPendantArmorModel(player, stack,
                                                                                                         EquipmentSlot.CHEST, new BlankBipedModel());
                            String texture = pendantItem.getArmorTexture(stack, player, EquipmentSlot.CHEST, null);
                            if (model != null && texture != null) {
                                T playerT = (T) player;
                                model.setupAnim(playerT, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
                                model.prepareMobModel(playerT, limbSwing, limbSwingAmount, partialTicks);
                                Tools.followBodyRotations(playerT, (HumanoidModel<LivingEntity>) model);
                                VertexConsumer vertexBuilder = ItemRenderer.getFoilBufferDirect(renderTypeBuffer,
                                                                                                model.renderType(new ResourceLocation(texture)), false, stack.hasFoil());
                                model.renderToBuffer(matrixStack, vertexBuilder, light, OverlayTexture.NO_OVERLAY, 1F, 1F,
                                                     1F, 1F);
                            }
                        }
                    }
                }
            }
        }
    }
}
