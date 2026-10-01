package dunkmania101.splendidpendants.data.compat;

import java.util.Optional;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

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
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.CuriosCapability;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.SlotResult;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;
import top.theillusivec4.curios.api.client.ICurioRenderer;
import top.theillusivec4.curios.api.type.capability.ICurio;

public class CuriosCompat {
    public static void registerPendantCapabilities(RegisterCapabilitiesEvent event) {
        ItemInit.ITEMS.getEntries().forEach(entry -> {
            Item item = entry.get();
            if (item instanceof PendantItem) {
                event.registerItem(CuriosCapability.ITEM, (stack, context) -> createPendantCurio(stack), item);
            }
        });
    }

    public static void curiosClientSetup() {
        ItemInit.ITEMS.getEntries().forEach((entry) -> {
            Item item = entry.get();
            if (item instanceof PendantItem) {
                CuriosRendererRegistry.register(item, CuriosCompat.PendantCurioRenderer::new);
            }
        });
    }

    public static Optional<ItemStack> findEquippedCurio(LivingEntity entity, Item item) {
        return CuriosApi.getCuriosInventory(entity)
                .flatMap(inventory -> inventory.findFirstCurio(item))
                .map(SlotResult::stack);
    }

    private static ICurio createPendantCurio(ItemStack stack) {
        return new ICurio() {
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

            @Override
            public boolean canEquip(SlotContext slotContext) {
                if (slotContext.identifier().equals(CustomValues.pendantCuriosSlotName)) {
                    return stack.getItem() instanceof PendantItem;
                }
                return false;
            }
        };
    }

    public static class PendantCurioRenderer implements ICurioRenderer {
        @SuppressWarnings("unchecked")
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
                        ItemStack chestStack = player.getItemBySlot(EquipmentSlot.CHEST);
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
                            ResourceLocation texture = pendantItem.getPendantArmorTexture(stack, player, EquipmentSlot.CHEST);
                            if (model != null && texture != null) {
                                T playerT = (T) player;
                                model.setupAnim(playerT, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
                                model.prepareMobModel(playerT, limbSwing, limbSwingAmount, partialTicks);
                                Tools.followBodyRotations(playerT, (HumanoidModel<LivingEntity>) model);
                                VertexConsumer vertexBuilder = ItemRenderer.getFoilBufferDirect(renderTypeBuffer,
                                                                                                model.renderType(texture), false, stack.hasFoil());
                                model.renderToBuffer(matrixStack, vertexBuilder, light, OverlayTexture.NO_OVERLAY, -1);
                            }
                        }
                    }
                }
            }
        }
    }
}
