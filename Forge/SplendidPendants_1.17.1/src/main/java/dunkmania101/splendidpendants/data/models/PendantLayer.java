package dunkmania101.splendidpendants.data.models;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms.TransformType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.ForgeHooksClient.ClientEvents;
import net.minecraftforge.items.CapabilityItemHandler;

public class PendantLayer<T extends LivingEntity, M extends HumanoidModel<T>> extends RenderLayer<T, M>
{
    private final HumanoidModel<T> pendantModel;

    public PendantLayer(LivingEntityRenderer<T, M> livingEntityRenderer)
    {
        super(livingEntityRenderer);

        pendantModel = new BeltModel(Minecraft.getInstance().getEntityModels().bakeLayer(ClientEvents.BELT_LAYER));
    }

    private void translateToBody(PoseStack matrixStack)
    {
        this.getParentModel().body.translateAndRotate(matrixStack);
    }

    @Override
    public void render(PoseStack matrixStack, MultiBufferSource buffer, int lightness, T player, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch)
    {
        if (!ConfigData.showBeltOnPlayers)
            return;

        BeltFinder.findBelt(player, true).ifPresent((getter) -> {

            if (getter.isHidden())
                return;

            getter.getBelt().getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent((cap) -> {
                boolean rightHanded = player.getMainArm() == HumanoidArm.RIGHT;

                matrixStack.pushPose();
                this.translateToBody(matrixStack);

                ItemStack firstItem = cap.getStackInSlot(0);
                ItemStack secondItem = cap.getStackInSlot(1);

                ItemStack leftItem = rightHanded ? firstItem : secondItem;
                ItemStack rightItem = rightHanded ? secondItem : firstItem;

                if (!leftItem.isEmpty() || !rightItem.isEmpty())
                {
                    matrixStack.pushPose();

                    if (getParentModel().young)
                    {
                        matrixStack.translate(0.0F, 0.75F, 0.0F);
                        matrixStack.scale(0.5F, 0.5F, 0.5F);
                    }

                    renderHeldItem(player, rightItem, TransformType.THIRD_PERSON_RIGHT_HAND, HumanoidArm.RIGHT, matrixStack, buffer, lightness);
                    renderHeldItem(player, leftItem, TransformType.THIRD_PERSON_LEFT_HAND, HumanoidArm.LEFT, matrixStack, buffer, lightness);

                    matrixStack.popPose();
                }

                matrixStack.translate(0.0F, 0.19F, 0.0F);
                matrixStack.scale(0.85f, 0.6f, 0.78f);

                renderColoredCutoutModel(beltModel, TEXTURE_BELT, matrixStack, buffer, lightness, player, 1.0f, 1.0f, 1.0f);


                matrixStack.popPose();
            });
        });
    }
