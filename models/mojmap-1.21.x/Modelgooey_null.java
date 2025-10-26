// Made with Blockbench 5.0.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

public class Modelgooey_null<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			new ResourceLocation("modid", "gooey_null"), "main");
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart leftarm;
	private final ModelPart rightarm;

	public Modelgooey_null(ModelPart root) {
		this.body = root.getChild("body");
		this.head = root.getChild("head");
		this.leftarm = root.getChild("leftarm");
		this.rightarm = root.getChild("rightarm");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(
				-1.0F, -6.0F, -6.0F, 9.0F, 6.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 24.0F, 1.0F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create(),
				PartPose.offset(-3.0F, 24.0F, 1.0F));

		PartDefinition overlay_2_r1 = head.addOrReplaceChild("overlay_2_r1",
				CubeListBuilder.create().texOffs(0, 38).addBox(-2.0F, -1.0F, -1.0F, 3.0F, 6.0F, 0.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(4.25F, -11.0F, 0.5F, 0.0F, 0.0F, 1.3526F));

		PartDefinition overlay_1_r1 = head.addOrReplaceChild("overlay_1_r1",
				CubeListBuilder.create().texOffs(0, 26).addBox(-2.0F, -1.0F, -1.0F, 5.0F, 6.0F, 0.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(2.5F, -10.0F, -1.5F, 0.0F, 0.0F, 1.5708F));

		PartDefinition cube_r1 = head.addOrReplaceChild("cube_r1",
				CubeListBuilder.create().texOffs(0, 15).addBox(-1.0F, -5.0F, -5.0F, 6.0F, 5.0F, 6.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.0F, -5.0F, 0.0F, 0.0F, 0.0F, -0.1309F));

		PartDefinition leftarm = partdefinition.addOrReplaceChild("leftarm",
				CubeListBuilder.create().texOffs(24, 15).addBox(-5.5F, -1.0F, -1.0F, 3.0F, 6.0F, 3.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.25F, 23.0F, -1.0F, -0.3747F, 0.2291F, 0.6982F));

		PartDefinition overlay_2_r2 = leftarm
				.addOrReplaceChild("overlay_2_r2",
						CubeListBuilder.create().texOffs(12, 26).addBox(-1.0F, -1.0F, -1.0F, 3.0F, 5.0F, 0.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(-6.5F, 0.0F, 2.5F, 0.0F, 0.0F, 0.1309F));

		PartDefinition overlay_1_r2 = leftarm.addOrReplaceChild("overlay_1_r2",
				CubeListBuilder.create().texOffs(0, 32).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 6.0F, 0.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-6.5F, 0.0F, 0.5F, 0.0F, 0.0F, -0.0436F));

		PartDefinition rightarm = partdefinition.addOrReplaceChild("rightarm",
				CubeListBuilder.create().texOffs(24, 24).addBox(-0.5F, -1.0F, -1.5F, 3.0F, 6.0F, 3.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(4.75F, 20.0F, -0.5F, -0.1745F, 0.0F, -0.3927F));

		PartDefinition overlay_2_r3 = rightarm
				.addOrReplaceChild("overlay_2_r3",
						CubeListBuilder.create().texOffs(12, 31).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 6.0F, 0.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(3.5F, 0.0F, 2.0F, 0.0F, 0.0F, 0.0873F));

		PartDefinition overlay_1_r3 = rightarm.addOrReplaceChild("overlay_1_r3",
				CubeListBuilder.create().texOffs(18, 26).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 6.0F, 0.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(2.75F, 0.0F, 0.0F, 0.0F, 0.0F, -0.1309F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay,
			float red, float green, float blue, float alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		leftarm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		rightarm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch) {
	}
}