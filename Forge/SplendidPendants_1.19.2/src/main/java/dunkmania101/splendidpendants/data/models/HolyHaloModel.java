package dunkmania101.splendidpendants.data.models;

import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HolyHaloModel extends BaseDyeableModel {
    public HolyHaloModel(ItemStack stack, DyeColor color, boolean alsoArmor) {
        super(createHaloLayer(alsoArmor), stack, color);
    }

    public HolyHaloModel(ItemStack stack, DyeColor color) {
        this(stack, color, false);
    }

    public HolyHaloModel(ItemStack stack, boolean alsoArmor) {
        this(stack, DyeColor.YELLOW, alsoArmor);
    }

    public HolyHaloModel(ItemStack stack) {
        this(stack, false);
    }

    public static LayerDefinition createHaloLayer(boolean alsoArmor) {
        MeshDefinition meshdefinition = createBlankMesh();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition holy_halo = partdefinition.getChild("hat").addOrReplaceChild("holy_halo", CubeListBuilder.create(),
                PartPose.offset(0.0F, -11.0F, 0.0F));

        PartDefinition cube_r1 = holy_halo.addOrReplaceChild("cube_r1",
                CubeListBuilder.create().texOffs(0, 0)
                        .addBox(-7.0F, -2.0F, 6.0F, 13.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                        .texOffs(0, 0).addBox(-7.0F, -2.0F, -7.0F, 13.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                        .texOffs(0, 0).addBox(-5.0F, -3.0F, -6.0F, 9.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                        .texOffs(0, 0).addBox(-5.0F, -2.0F, 3.0F, 9.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                        .texOffs(0, 0).addBox(-5.0F, -2.0F, -4.0F, 9.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                        .texOffs(0, 0).addBox(-5.0F, -3.0F, 4.0F, 9.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.3054F, -1.4835F, 0.0F));

        PartDefinition cube_r2 = holy_halo.addOrReplaceChild("cube_r2",
                CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -3.0F, 4.0F, 12.0F, 3.0F, 2.0F,
                        new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0263F, 0.0832F, -0.3065F));

        PartDefinition cube_r3 = holy_halo.addOrReplaceChild("cube_r3",
                CubeListBuilder.create().texOffs(0, 0).mirror()
                        .addBox(-6.0F, -2.0F, -7.0F, 12.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                        .texOffs(0, 0).mirror().addBox(-6.0F, -2.0F, 7.0F, 12.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                        .mirror(false)
                        .texOffs(0, 0).mirror().addBox(-3.0F, -2.0F, -4.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                        .mirror(false)
                        .texOffs(0, 0).mirror().addBox(-3.0F, -2.0F, 4.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                        .mirror(false)
                        .texOffs(0, 0).mirror().addBox(-6.0F, -3.0F, 5.0F, 12.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                        .mirror(false),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -3.1153F, -0.0832F, 2.8351F));

        if (alsoArmor) {
            PartDefinition holy_armor = holy_halo.addOrReplaceChild("holy_armor", CubeListBuilder.create(), PartPose.offset(0.0F, 19.0F, 0.0F));

		    PartDefinition cube_r4 = holy_armor.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -6.0F, 4.0F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		    .texOffs(0, 0).addBox(-5.0F, -7.0F, 3.0F, 9.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		    .texOffs(0, 0).addBox(-4.0F, -6.0F, 4.0F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		    .texOffs(0, 0).addBox(-5.0F, -4.0F, 3.0F, 9.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		    .texOffs(0, 0).addBox(-5.0F, 3.0F, -7.0F, 9.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		    .texOffs(0, 0).addBox(-4.0F, 1.0F, -6.0F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		    .texOffs(0, 0).addBox(-5.0F, 0.0F, -7.0F, 9.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		    .texOffs(0, 0).addBox(-5.0F, -7.0F, 3.0F, 9.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		    .texOffs(0, 0).addBox(-5.0F, -4.0F, -7.0F, 9.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		    .texOffs(0, 0).addBox(-5.0F, -7.0F, -7.0F, 9.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		    .texOffs(0, 0).addBox(-4.0F, -6.0F, -6.0F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		    .texOffs(0, 0).addBox(-5.0F, 3.0F, 3.0F, 9.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		    .texOffs(0, 0).addBox(-4.0F, 1.0F, -6.0F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		    .texOffs(0, 0).addBox(-5.0F, 0.0F, -7.0F, 9.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		    .texOffs(0, 0).addBox(-5.0F, 3.0F, -7.0F, 9.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		    .texOffs(0, 0).addBox(-5.0F, 0.0F, 3.0F, 9.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		    .texOffs(0, 0).addBox(-4.0F, 1.0F, 4.0F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		    .texOffs(0, 0).addBox(-5.0F, -4.0F, 3.0F, 9.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -19.0F, 0.0F, -0.3054F, -1.4835F, 0.0F));

		    PartDefinition cube_r5 = holy_armor.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, 7.0F, -4.0F, 9.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
		    .texOffs(0, 0).addBox(-4.0F, 8.0F, -3.0F, 7.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		    .texOffs(0, 0).addBox(-5.0F, 10.0F, -4.0F, 9.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
		    .texOffs(0, 0).addBox(-5.0F, -8.0F, -4.0F, 9.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
		    .texOffs(0, 0).addBox(-4.0F, -10.0F, -3.0F, 7.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		    .texOffs(0, 0).addBox(-5.0F, -11.0F, -4.0F, 9.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -19.0F, 0.0F, -1.5971F, 0.0832F, -0.3065F));

		    PartDefinition cube_r6 = holy_armor.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, 7.0F, -4.0F, 9.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
		    .texOffs(0, 0).addBox(-4.0F, 8.0F, -3.0F, 7.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		    .texOffs(0, 0).addBox(-5.0F, 10.0F, -4.0F, 9.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
		    .texOffs(0, 0).addBox(-5.0F, -11.0F, -4.0F, 9.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
		    .texOffs(0, 0).addBox(-4.0F, -10.0F, -3.0F, 7.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		    .texOffs(0, 0).addBox(-5.0F, -8.0F, -4.0F, 9.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -19.0F, 0.0F, -1.8762F, -1.4835F, 0.0F));

		    PartDefinition cube_r7 = holy_armor.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -4.0F, 4.0F, 6.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		    .texOffs(0, 0).addBox(-2.0F, 1.0F, 5.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		    .texOffs(0, 0).addBox(-3.0F, 0.0F, 4.0F, 6.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		    .texOffs(0, 0).addBox(-3.0F, 3.0F, -7.0F, 6.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		    .texOffs(0, 0).addBox(-3.0F, 0.0F, -7.0F, 6.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		    .texOffs(0, 0).addBox(-2.0F, 1.0F, -6.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		    .texOffs(0, 0).addBox(-3.0F, 3.0F, 4.0F, 6.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		    .texOffs(0, 0).addBox(-2.0F, -6.0F, -6.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		    .texOffs(0, 0).addBox(-3.0F, -7.0F, -7.0F, 6.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		    .texOffs(0, 0).addBox(-3.0F, -4.0F, -7.0F, 6.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		    .texOffs(0, 0).addBox(-3.0F, -7.0F, 4.0F, 6.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		    .texOffs(0, 0).addBox(-2.0F, -6.0F, 5.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -19.0F, 0.0F, -3.1153F, -0.0832F, 2.8351F));
        }
        return LayerDefinition.create(meshdefinition, 16, 16);
    }

    @Override
    protected void setPartsVisible() {
        this.hat.visible = true;
    }
}
