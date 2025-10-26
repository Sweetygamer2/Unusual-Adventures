package net.mcreator.unusualadventures.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.core.particles.SimpleParticleType;

import net.mcreator.unusualadventures.init.UnusualAdventuresModParticleTypes;

public class NullWorkbenchOnRandomClientDisplayTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (Math.random() < 0.3) {
			world.addParticle((SimpleParticleType) (UnusualAdventuresModParticleTypes.BINARY.get()), (x + Mth.nextDouble(RandomSource.create(), 0.15, 0.85)), (y + Mth.nextDouble(RandomSource.create(), 0.5, 0.9)),
					(z + Mth.nextDouble(RandomSource.create(), 0.15, 0.85)), 0, 0.1, 0);
		}
	}
}