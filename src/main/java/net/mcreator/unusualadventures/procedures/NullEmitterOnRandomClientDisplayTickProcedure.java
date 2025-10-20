package net.mcreator.unusualadventures.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.core.particles.SimpleParticleType;

import net.mcreator.unusualadventures.init.UnusualAdventuresModParticleTypes;

public class NullEmitterOnRandomClientDisplayTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		world.addParticle((SimpleParticleType) (UnusualAdventuresModParticleTypes.BINARY.get()), (x + Mth.nextDouble(RandomSource.create(), -5.5, 6)), (y + Mth.nextDouble(RandomSource.create(), -5.5, 6)),
				(z + Mth.nextDouble(RandomSource.create(), -5.5, 6)), 0, 0.1, 0);
	}
}