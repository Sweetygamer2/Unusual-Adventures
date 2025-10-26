package net.mcreator.unusualadventures.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.unusualadventures.entity.GooeyNullEntity;

public class GooeyNullEmergeProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		return (entity instanceof GooeyNullEntity _datEntI ? _datEntI.getEntityData().get(GooeyNullEntity.DATA_tp) : 0) <= 13 && (entity instanceof GooeyNullEntity _datEntI ? _datEntI.getEntityData().get(GooeyNullEntity.DATA_tp) : 0) > 0;
	}
}