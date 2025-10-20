package net.mcreator.unusualadventures.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.core.particles.ParticleTypes;

public class BrokenComputerOnRandomClientDisplayTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (Math.random() < 0.1) {
			world.addParticle(ParticleTypes.SMOKE, (x + Mth.nextDouble(RandomSource.create(), 0.2, 0.8)), (y + Mth.nextDouble(RandomSource.create(), 0.2, 0.8)), (z + Mth.nextDouble(RandomSource.create(), 0.2, 0.8)), 0, 0, 0);
		}
		if (Math.random() < 0.01) {
			world.addParticle(ParticleTypes.SMALL_FLAME, (x + Mth.nextDouble(RandomSource.create(), 0.3, 0.7)), (y + Mth.nextDouble(RandomSource.create(), 0.3, 0.7)), (z + Mth.nextDouble(RandomSource.create(), 0.3, 0.7)), 0, 0, 0);
		}
	}
}