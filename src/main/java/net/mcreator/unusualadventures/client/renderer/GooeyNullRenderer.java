package net.mcreator.unusualadventures.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.HierarchicalModel;

import net.mcreator.unusualadventures.entity.GooeyNullEntity;
import net.mcreator.unusualadventures.client.model.animations.gooey_nullAnimation;
import net.mcreator.unusualadventures.client.model.Modelgooey_null;

import com.mojang.blaze3d.vertex.PoseStack;

public class GooeyNullRenderer extends MobRenderer<GooeyNullEntity, Modelgooey_null<GooeyNullEntity>> {
	public GooeyNullRenderer(EntityRendererProvider.Context context) {
		super(context, new AnimatedModel(context.bakeLayer(Modelgooey_null.LAYER_LOCATION)), 0.5f);
	}

	@Override
	protected void scale(GooeyNullEntity entity, PoseStack poseStack, float f) {
		poseStack.scale(1.3f, 1.3f, 1.3f);
	}

	@Override
	public ResourceLocation getTextureLocation(GooeyNullEntity entity) {
		return ResourceLocation.parse("unusual_adventures:textures/entities/" + entity.getTexture() + ".png");
	}

	private static final class AnimatedModel extends Modelgooey_null<GooeyNullEntity> {
		private final ModelPart root;
		private final HierarchicalModel animator = new HierarchicalModel<GooeyNullEntity>() {
			@Override
			public ModelPart root() {
				return root;
			}

			@Override
			public void setupAnim(GooeyNullEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
				this.root().getAllParts().forEach(ModelPart::resetPose);
				this.animateWalk(gooey_nullAnimation.walk, limbSwing, limbSwingAmount, 4f, 8f);
				this.animate(entity.animationState1, gooey_nullAnimation.idle, ageInTicks, 0.9f);
				this.animate(entity.animationState2, gooey_nullAnimation.attack, ageInTicks, 1.1f);
				this.animate(entity.animationState3, gooey_nullAnimation.death, ageInTicks, 0.9f);
				this.animate(entity.animationState4, gooey_nullAnimation.emerge, ageInTicks, 1f);
				this.animate(entity.animationState5, gooey_nullAnimation.dive, ageInTicks, 1f);
			}
		};

		public AnimatedModel(ModelPart root) {
			super(root);
			this.root = root;
		}

		@Override
		public void setupAnim(GooeyNullEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
			animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
			super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		}
	}
}