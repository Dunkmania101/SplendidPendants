package dunkmania101.splendidpendants.data.models;

import dunkmania101.splendidpendants.SplendidPendants;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AtlanticTailModel extends BaseDyeableModel {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(SplendidPendants.modid, "atlantic_tail"), "main");

    public AtlanticTailModel(ItemStack stack) {
        super(createTailLayer(), stack, DyeColor.LIME);
    }

	public static LayerDefinition createTailLayer() {
		MeshDefinition meshdefinition = createBlankMesh();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition atlantic_tail = partdefinition.getChild("left_leg").addOrReplaceChild("atlantic_tail", CubeListBuilder.create().texOffs(0, 0).addBox(1.0F, -5.0F, -5.0F, 14.0F, 5.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.0F, 1.0F, -5.0F, 14.0F, 5.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(3.0F, 0.0F, -3.0F, 10.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(2.0F, 7.0F, -5.0F, 12.0F, 5.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.0F, 12.0F, -4.0F, 8.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(2.0F, 13.0F, -6.0F, 12.0F, 5.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.0F, 6.0F, -3.0F, 8.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.0F, -7.0F, 3.0F, 14.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.0F, -7.0F, -5.0F, 14.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-10.0F, 5.0F, 0.0F));

		PartDefinition cube_r1 = atlantic_tail.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -7.0F, 1.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.0F, -7.0F, 13.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r2 = atlantic_tail.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 0).addBox(2.0F, 19.0F, -5.0F, 12.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.0F, 18.0F, -4.0F, 8.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 3.0F, -0.3054F, 0.0F, 0.0F));

		PartDefinition cube_r3 = atlantic_tail.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 0).addBox(3.0F, 31.0F, 5.0F, 10.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(2.0F, 24.0F, 4.0F, 12.0F, 7.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.0F, 23.0F, 3.0F, 8.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 3.0F, -0.6981F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 16, 16);
	}

    @Override
    protected void setPartsVisible() {
        this.leftLeg.visible = true;
    }
}
