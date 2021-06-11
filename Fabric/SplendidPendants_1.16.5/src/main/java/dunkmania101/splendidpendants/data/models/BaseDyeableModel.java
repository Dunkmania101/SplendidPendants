package dunkmania101.splendidpendants.data.models;

import dunkmania101.splendidpendants.data.CustomValues;
import dunkmania101.splendidpendants.objects.items.DyeSpongeItem;
import dunkmania101.splendidpendants.util.Tools;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.DyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BaseDyeableModel extends BipedEntityModel<LivingEntity> {
    private float[] colors;

    public BaseDyeableModel(float modelSize, ItemStack stack, DyeColor defaultColor) {
        super(modelSize);

        this.colors = defaultColor.getColorComponentValues();

        Inventory itemStackHandler = Tools.getInventoryOfStack(stack, CustomValues.dyeableSize, true);
        if (itemStackHandler.getSlots() > 0) {
            ItemStack storedStack = itemStackHandler.getStackInSlot(0);
            Item storedDye = storedStack.getItem();
            if (storedDye instanceof DyeItem) {
                this.colors = ((DyeItem) storedDye).getDyeColor().getColorComponentValues();
            } else if (storedDye instanceof DyeSpongeItem) {
                int colorInt = ((DyeSpongeItem) storedDye).getColor(storedStack);
                this.colors = new float[]{(float) Tools.getRed(colorInt) / 255f, (float) Tools.getGreen(colorInt) / 255f, (float) Tools.getBlue(colorInt) / 255f};
            }
        }
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void render(@Nonnull MatrixStack matrixStackIn, @Nonnull IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        super.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, this.colors[0], this.colors[1], this.colors[2], alpha);
    }
}
