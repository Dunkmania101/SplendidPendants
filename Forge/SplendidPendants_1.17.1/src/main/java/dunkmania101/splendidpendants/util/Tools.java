package dunkmania101.splendidpendants.util;

import com.mojang.blaze3d.vertex.VertexBuffer;

import dunkmania101.splendidpendants.data.CustomValues;
import dunkmania101.splendidpendants.objects.items.DyeSpongeItem;
import dunkmania101.splendidpendants.objects.items.LocketItem;
import dunkmania101.splendidpendants.objects.items.PendantItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.items.ItemStackHandler;

public class Tools {
    public static ItemStackHandler getItemStackHandlerOfStack(ItemStack stack, int size, boolean isDyeable) {
        ItemStackHandler stackHandler = new ItemStackHandler(size) {
            @Override
            public int getSlotLimit(int slot) {
                return 1;
            }

            @Override
            public boolean isItemValid(int slot, ItemStack questionStack) {
                Item checkItem = questionStack.getItem();
                if (!(checkItem instanceof DyeItem || checkItem instanceof DyeSpongeItem) && isDyeable) {
                    return false;
                }
                if ((!(checkItem instanceof PendantItem) && !isDyeable) || checkItem instanceof LocketItem) {
                    return false;
                }
                for (ItemStack checkStack : this.stacks) {
                    if (checkStack.getItem() == checkItem) {
                        return false;
                    }
                }
                return super.isItemValid(slot, stack);
            }
        };
        if (stack.getItem() instanceof PendantItem) {
            stackHandler.deserializeNBT(stack.getOrCreateTag().getCompound(CustomValues.storedItemStacksKey));
        }
        return stackHandler;
    }

    public static void saveItemStackHandlerOfStack(ItemStack stack, ItemStackHandler stackHandler) {
        CompoundTag stacksNBT = stackHandler.serializeNBT();
        CompoundTag stackNBT = stack.getOrCreateTag();
        if (stackNBT.getCompound(CustomValues.storedItemStacksKey) != stacksNBT) {
            stackNBT.put(CustomValues.storedItemStacksKey, stacksNBT);
        }
    }

//    public static String translateString(String key) {
//        return new TranslationTextComponent(key).getString();
//    }

    public static int getRed(int packedColor) {
        return packedColor >> 16 & 255;
    }

    public static int getGreen(int packedColor) {
        return packedColor >> 8 & 255;
    }

    public static int getBlue(int packedColor) {
        return packedColor & 255;
    }

    public static int packColor(int alpha, int red, int green, int blue) {
        return alpha << 24 | red << 16 | green << 8 | blue;
    }

    public static int blendColors(int color1, int color2) {
        return color1 * color2 / 255;
    }

    // From CuriosAPI
    @OnlyIn(Dist.CLIENT)
    @SafeVarargs
    public static void followBodyRotations(final LivingEntity livingEntity, final HumanoidModel<LivingEntity>... models) {
        EntityRenderer<? super LivingEntity> render = Minecraft.getInstance().getEntityRenderDispatcher().getRenderer(livingEntity);
        if (render instanceof LivingEntityRenderer) {
            @SuppressWarnings("unchecked") LivingEntityRenderer<LivingEntity, EntityModel<LivingEntity>> livingRenderer = (LivingEntityRenderer<LivingEntity, EntityModel<LivingEntity>>) render;
            EntityModel<LivingEntity> entityModel = livingRenderer.getModel();
            if (entityModel instanceof HumanoidModel) {
                for (HumanoidModel<LivingEntity> model : models) {
                    HumanoidModel<LivingEntity> bipedModel = (HumanoidModel<LivingEntity>) entityModel;
                    bipedModel.copyPropertiesTo(model);
                }
            }
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void renderModelToPlayer(HumanoidModel model, IRenderTypeBuffer renderTypeBuffer, int light, LivingEntity livingEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
            if (livingEntity instanceof Player) {
                Player player = (Player) livingEntity;
                    BipedModel<LivingEntity> model = pendantItem.getArmorModel(livingEntity, stack, EquipmentSlot.CHEST, new BlankBipedModel());
                    if (model != null && texture != null) {
                        model.setupAnim(player, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
                        model.prepareMobModel(player, limbSwing, limbSwingAmount, partialTicks);
                        Tools.followBodyRotations(player, model);
                        IVertexBuilder vertexBuilder = ItemRenderer.getFoilBufferDirect(renderTypeBuffer, model.renderType(new ResourceLocation(texture)), false, stack.hasFoil());
                        model.renderToBuffer(matrixStack, vertexBuilder, light, OverlayTexture.NO_OVERLAY, 1F, 1F, 1F, 1F);
                    }
                }
            }
    };
}
