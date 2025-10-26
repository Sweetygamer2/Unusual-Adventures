package net.mcreator.unusualadventures.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;

import net.mcreator.unusualadventures.entity.GooeyNullEntity;

public class GooeyNullNaturalEntitySpawningConditionProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		return !(!world.getEntitiesOfClass(GooeyNullEntity.class, new AABB(Vec3.ZERO, Vec3.ZERO).move(new Vec3(x, y, z)).inflate(180 / 2d), e -> true).isEmpty());
	}
}