package dunkmania101.splendidpendants.data.models;

import dunkmania101.splendidpendants.data.CustomValues;
import dunkmania101.splendidpendants.util.Tools;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.items.ItemStackHandler;

@OnlyIn(Dist.CLIENT)
public class HoldingHandsModel extends BaseDyeableModel {
    public HoldingHandsModel(ItemStack stack) {
        super(createHandsLayer(stack), stack, DyeColor.BROWN);
    }

    public static LayerDefinition createHandsLayer(int count) {
        MeshDefinition meshdefinition = createBlankMesh();
        PartDefinition partdefinition = meshdefinition.getRoot();

        for (int index = 0; index < count; index++) {
            CubeListBuilder holdingHand = CubeListBuilder.create();

            float x;
            if (index % 2 == 0) {
                x = 12F;
            } else {
                x = -12F;
            }

            float y;
            if (index == 1 || index == 2) {
                y = -6F;
            } else if (index == 3 || index == 4) {
                y = -12F;
            }else {
                y = -18F;
            }

            holdingHand.texOffs(0, 0).addBox(-1.0F, -2.0F, -2.0F, 3.0F, 2.0F, 9.0F, false);
            holdingHand.texOffs(0, 0).addBox(-1.0F, -3.0F, 1.0F, 3.0F, 1.0F, 6.0F, false);
            holdingHand.texOffs(0, 0).addBox(-2.0F, -2.0F, -2.0F, 1.0F, 1.0F, 11.0F, false);
            holdingHand.texOffs(0, 0).addBox(2.0F, -2.0F, -2.0F, 1.0F, 1.0F, 11.0F, false);
            holdingHand.texOffs(0, 0).addBox(-1.0F, -2.0F, -5.0F, 3.0F, 1.0F, 3.0F, false);
            holdingHand.texOffs(0, 0).addBox(-1.0F, -3.0F, -7.0F, 3.0F, 1.0F, 2.0F, false);
            holdingHand.texOffs(0, 0).addBox(2.0F, -3.0F, -5.0F, 1.0F, 1.0F, 3.0F, false);
            holdingHand.texOffs(0, 0).addBox(-2.0F, -3.0F, -5.0F, 1.0F, 1.0F, 3.0F, false);
            holdingHand.texOffs(0, 0).addBox(3.0F, -3.0F, -7.0F, 1.0F, 1.0F, 3.0F, false);
            holdingHand.texOffs(0, 0).addBox(1.0F, -3.0F, -8.0F, 1.0F, 1.0F, 1.0F, false);
            holdingHand.texOffs(0, 0).addBox(-1.0F, -3.0F, -8.0F, 1.0F, 1.0F, 1.0F, false);

            partdefinition.getChild("body").addOrReplaceChild("holding_hand_".concat(Integer.toString(count)), holdingHand, PartPose.offset(x, y, 0.0F));
        }

        return LayerDefinition.create(meshdefinition, 16, 16);
    }

    public static LayerDefinition createHandsLayer(ItemStack stack) {
        int count = 1;

        ItemStackHandler stackHandler = Tools.getItemStackHandlerOfStack(stack, CustomValues.holdingSize, false, true);
        for (int slot = 0; slot < stackHandler.getSlots(); slot++) {
            if (!stackHandler.getStackInSlot(slot).isEmpty()) {
                count++;
            }
        }

        return createHandsLayer(count);
    }

    @Override
    protected void setPartsVisible() {
        this.body.visible = true;
    }
}
