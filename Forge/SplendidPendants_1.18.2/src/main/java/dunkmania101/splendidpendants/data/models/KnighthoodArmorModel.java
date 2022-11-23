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
public class KnighthoodArmorModel extends BaseDyeableModel {
    public KnighthoodArmorModel(ItemStack stack, boolean alsoAtlantic) {
        super(createArmorLayer(alsoAtlantic), stack, DyeColor.GRAY);
    }

    public KnighthoodArmorModel(ItemStack stack) {
        this(stack, false);
    }

    public static LayerDefinition createArmorLayer(boolean alsoAtlantic) {
        MeshDefinition meshdefinition = createBlankMesh();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition knighthoodArmorHead = partdefinition.getChild("head").addOrReplaceChild("knighthood_helmet",
                CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition neck = knighthoodArmorHead.addOrReplaceChild("neck",
                CubeListBuilder.create().texOffs(0, 0)
                        .addBox(5.0F, -2.0F, -6.0F, 2.0F, 2.0F, 12.0F, new CubeDeformation(0.0F))
                        .texOffs(0, 0).addBox(-7.0F, -2.0F, -6.0F, 2.0F, 2.0F, 12.0F, new CubeDeformation(0.0F))
                        .texOffs(0, 0).addBox(-3.0F, -2.0F, -4.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                        .texOffs(0, 0).addBox(3.0F, -2.0F, -5.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                        .texOffs(0, 0).addBox(-5.0F, -2.0F, -5.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r1 = neck
                .addOrReplaceChild("cube_r1",
                        CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -3.0F, -5.0F, 2.0F, 3.0F, 10.0F,
                                new CubeDeformation(0.0F)),
                        PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

        PartDefinition ears = knighthoodArmorHead.addOrReplaceChild("ears", CubeListBuilder.create(),
                PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition left = ears.addOrReplaceChild("left", CubeListBuilder.create(),
                PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r2 = left.addOrReplaceChild("cube_r2",
                CubeListBuilder.create().texOffs(0, 0).addBox(4.0F, -11.0F, -3.0F, 2.0F, 9.0F, 9.0F,
                        new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1329F, 0.173F, 0.023F));

        PartDefinition right = ears.addOrReplaceChild("right", CubeListBuilder.create(),
                PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r3 = right.addOrReplaceChild("cube_r3",
                CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -11.0F, -3.0F, 2.0F, 9.0F, 9.0F,
                        new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1329F, -0.173F, -0.023F));

        PartDefinition faceplate = knighthoodArmorHead.addOrReplaceChild("faceplate", CubeListBuilder.create(),
                PartPose.offset(0.0F, 0.0F, 6.0F));

        PartDefinition cube_r4 = faceplate.addOrReplaceChild("cube_r4",
                CubeListBuilder.create().texOffs(0, 0)
                        .addBox(15.0F, -8.0F, -5.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                        .texOffs(0, 0).addBox(15.0F, -8.0F, 3.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                        .texOffs(0, 0).addBox(15.0F, -8.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                        .texOffs(0, 0).addBox(15.0F, -5.0F, -4.0F, 2.0F, 3.0F, 8.0F, new CubeDeformation(0.0F))
                        .texOffs(0, 0).addBox(15.0F, -7.0F, -5.0F, 2.0F, 2.0F, 10.0F, new CubeDeformation(0.0F))
                        .texOffs(0, 0).addBox(15.0F, -10.0F, -5.0F, 2.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, -3.0F, 6.0F, 1.5708F, 1.3963F, 1.5708F));

        PartDefinition back = knighthoodArmorHead.addOrReplaceChild("back", CubeListBuilder.create(),
                PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r5 = back.addOrReplaceChild("cube_r5",
                CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -11.0F, -5.0F, 2.0F, 9.0F, 10.0F,
                        new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.5708F, 1.3963F, 1.5708F));

        PartDefinition scalp = knighthoodArmorHead.addOrReplaceChild("scalp", CubeListBuilder.create(),
                PartPose.offsetAndRotation(0.0F, -3.0F, 6.0F, 1.5708F, 0.0F, 0.0F));

        PartDefinition cube_r6 = scalp.addOrReplaceChild("cube_r6",
                CubeListBuilder.create().texOffs(0, 0).addBox(-10.0F, -10.0F, -5.0F, 2.0F, 8.0F, 10.0F,
                        new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.5708F, 1.3963F, 1.5708F));

        PartDefinition plume = knighthoodArmorHead.addOrReplaceChild("plume", CubeListBuilder.create(),
                PartPose.offsetAndRotation(-5.0F, -8.0F, 0.0F, 0.0F, -0.1309F, 0.0F));

        PartDefinition cube_r7 = plume.addOrReplaceChild("cube_r7",
                CubeListBuilder.create().texOffs(0, 0)
                        .addBox(4.0F, -10.0F, 0.0F, 2.0F, 6.0F, 6.0F, new CubeDeformation(0.0F))
                        .texOffs(0, 0).addBox(4.0F, -9.0F, -4.0F, 2.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.132F, 0.1298F, 0.0172F));

        CubeListBuilder knighthoodArmorChest = CubeListBuilder.create();
        knighthoodArmorChest.texOffs(0, 0).addBox(-8.0F, 13.0F, -8.0F, 16.0F, 4.0F, 16.0F, false);
        knighthoodArmorChest.texOffs(0, 0).addBox(-8.0F, -1.0F, -8.0F, 16.0F, 4.0F, 16.0F, false);
        knighthoodArmorChest.texOffs(0, 0).addBox(-7.0F, 3.0F, -7.0F, 14.0F, 10.0F, 14.0F, false);

        CubeListBuilder knighthoodArmorRightArm = CubeListBuilder.create();
        knighthoodArmorRightArm.texOffs(0, 0).addBox(-5.0F, 2.0F, -3.0F, 8.0F, 14.0F, 8.0F, false);
        knighthoodArmorRightArm.texOffs(0, 0).addBox(-6.0F, 16.0F, -4.0F, 10.0F, 2.0F, 10.0F, false);
        knighthoodArmorRightArm.texOffs(0, 0).addBox(-6.0F, 0.0F, -4.0F, 10.0F, 2.0F, 10.0F, false);

        CubeListBuilder knighthoodArmorLeftArm = CubeListBuilder.create();
        knighthoodArmorLeftArm.texOffs(0, 0).addBox(-5.0F, 2.0F, -3.0F, 8.0F, 14.0F, 8.0F, false);
        knighthoodArmorLeftArm.texOffs(0, 0).addBox(-6.0F, 16.0F, -4.0F, 10.0F, 2.0F, 10.0F, false);
        knighthoodArmorLeftArm.texOffs(0, 0).addBox(-6.0F, 0.0F, -4.0F, 10.0F, 2.0F, 10.0F, false);

        if (!alsoAtlantic) {
            CubeListBuilder knighthoodArmorRightLeg = CubeListBuilder.create();
            knighthoodArmorRightLeg.texOffs(0, 0).addBox(-5.0F, 2.0F, -3.0F, 8.0F, 14.0F, 8.0F, false);
            knighthoodArmorRightLeg.texOffs(0, 0).addBox(-6.0F, 16.0F, -4.0F, 10.0F, 2.0F, 10.0F, false);
            knighthoodArmorRightLeg.texOffs(0, 0).addBox(-6.0F, 0.0F, -4.0F, 10.0F, 2.0F, 10.0F, false);

            CubeListBuilder knighthoodArmorLeftLeg = CubeListBuilder.create();
            knighthoodArmorLeftLeg.texOffs(0, 0).addBox(-5.0F, 2.0F, -3.0F, 8.0F, 14.0F, 8.0F, false);
            knighthoodArmorLeftLeg.texOffs(0, 0).addBox(-6.0F, 16.0F, -4.0F, 10.0F, 2.0F, 10.0F, false);
            knighthoodArmorLeftLeg.texOffs(0, 0).addBox(-6.0F, 0.0F, -4.0F, 10.0F, 2.0F, 10.0F, false);

            partdefinition.getChild("right_leg").addOrReplaceChild("knighthood_right_leg", knighthoodArmorRightLeg,
                    PartPose.offset(1.0F, -6.0F, -1.0F));
            partdefinition.getChild("left_leg").addOrReplaceChild("knighthood_left_leg", knighthoodArmorLeftLeg,
                    PartPose.offset(1.0F, -6.0F, -1.0F));
        }

        // partdefinition.getChild("head").addOrReplaceChild("knighthood_head",
        // knighthoodArmorHead, PartPose.offset(0.0F, -2.0F, 0.0F));
        partdefinition.getChild("body").addOrReplaceChild("knighthood_body", knighthoodArmorChest,
                PartPose.offset(0.0F, 0.0F, 0.0F));
        partdefinition.getChild("right_arm").addOrReplaceChild("knighthood_right_arm", knighthoodArmorRightArm,
                PartPose.offset(1.0F, -6.0F, -1.0F));
        partdefinition.getChild("left_arm").addOrReplaceChild("knighthood_left_arm", knighthoodArmorLeftArm,
                PartPose.offset(1.0F, -6.0F, -1.0F));

        return LayerDefinition.create(meshdefinition, 16, 16);
    }

    @Override
    protected void setPartsVisible() {
        this.head.visible = true;
        this.body.visible = true;
        this.rightArm.visible = true;
        this.leftArm.visible = true;
        this.rightLeg.visible = true;
        this.leftLeg.visible = true;
    }
}
