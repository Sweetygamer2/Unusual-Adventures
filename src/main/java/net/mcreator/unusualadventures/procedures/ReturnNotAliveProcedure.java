package net.mcreator.unusualadventures.procedures;

import net.minecraft.world.entity.Entity;

public class ReturnNotAliveProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		return !entity.isAlive();
	}
}