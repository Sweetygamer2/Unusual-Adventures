package net.mcreator.unusualadventures.procedures;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.BlockPos;

import net.mcreator.unusualadventures.init.UnusualAdventuresModParticleTypes;
import net.mcreator.unusualadventures.init.UnusualAdventuresModBlocks;
import net.mcreator.unusualadventures.entity.GooeyNullEntity;

public class GooeyNullOnEntityTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double X = 0;
		double Y = 0;
		double Z = 0;
		double output_x = 0;
		double output_y = 0;
		double output_z = 0;
		if (entity instanceof GooeyNullEntity _datEntSetI)
			_datEntSetI.getEntityData().set(GooeyNullEntity.DATA_animation, (int) ((entity instanceof GooeyNullEntity _datEntI ? _datEntI.getEntityData().get(GooeyNullEntity.DATA_animation) : 0) + 1));
		if (!((entity instanceof GooeyNullEntity _datEntI ? _datEntI.getEntityData().get(GooeyNullEntity.DATA_tp) : 0) == 0)) {
			if (entity instanceof GooeyNullEntity _datEntSetI)
				_datEntSetI.getEntityData().set(GooeyNullEntity.DATA_tp, (int) ((entity instanceof GooeyNullEntity _datEntI ? _datEntI.getEntityData().get(GooeyNullEntity.DATA_tp) : 0) - 1));
		}
		if ((entity instanceof GooeyNullEntity _datEntI ? _datEntI.getEntityData().get(GooeyNullEntity.DATA_animation) : 0) == 16) {
			if (entity instanceof GooeyNullEntity _datEntSetI)
				_datEntSetI.getEntityData().set(GooeyNullEntity.DATA_animation, 1);
		}
		if ((entity instanceof GooeyNullEntity _datEntI ? _datEntI.getEntityData().get(GooeyNullEntity.DATA_animation) : 0) > 12) {
			if (entity instanceof GooeyNullEntity customEntity)
				customEntity.setTexture("gooey_null_4");
		} else if ((entity instanceof GooeyNullEntity _datEntI ? _datEntI.getEntityData().get(GooeyNullEntity.DATA_animation) : 0) > 8) {
			if (entity instanceof GooeyNullEntity customEntity)
				customEntity.setTexture("gooey_null_3");
		} else if ((entity instanceof GooeyNullEntity _datEntI ? _datEntI.getEntityData().get(GooeyNullEntity.DATA_animation) : 0) > 4) {
			if (entity instanceof GooeyNullEntity customEntity)
				customEntity.setTexture("gooey_null_2");
		} else {
			if (entity instanceof GooeyNullEntity customEntity)
				customEntity.setTexture("gooey_null");
			if (Math.random() < 0.2) {
				if (world instanceof ServerLevel _level)
					_level.sendParticles((SimpleParticleType) (UnusualAdventuresModParticleTypes.BINARY.get()), x, (y + 0.4), z, 1, 0.3, 0.3, 0.3, 0);
			}
		}
		if ((world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == UnusualAdventuresModBlocks.NULL_BLOCK.get() && (entity instanceof GooeyNullEntity _datEntI ? _datEntI.getEntityData().get(GooeyNullEntity.DATA_tp) : 0) == 0) {
			if (Math.random() < 0.002) {
				for (int index0 = 0; index0 < 16; index0++) {
					X = x + Mth.nextInt(RandomSource.create(), -8, 8);
					Y = y + Mth.nextInt(RandomSource.create(), -1, 2);
					Z = z + Mth.nextInt(RandomSource.create(), -8, 8);
					if ((world.getBlockState(BlockPos.containing(X, Y - 1, Z))).getBlock() == UnusualAdventuresModBlocks.NULL_BLOCK.get() && !world.getBlockState(BlockPos.containing(X, Y, Z)).canOcclude()) {
						world.levelEvent(2001, BlockPos.containing(x, y - 0.5, z), Block.getId(UnusualAdventuresModBlocks.NULL_BLOCK.get().defaultBlockState()));
						if (world instanceof Level _level) {
							if (!_level.isClientSide()) {
								_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("item.chorus_fruit.teleport")), SoundSource.HOSTILE, (float) 0.8, (float) 1.2);
							} else {
								_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("item.chorus_fruit.teleport")), SoundSource.HOSTILE, (float) 0.8, (float) 1.2, false);
							}
						}
						if (entity instanceof GooeyNullEntity _datEntSetI)
							_datEntSetI.getEntityData().set(GooeyNullEntity.DATA_tp, 26);
						if (entity instanceof GooeyNullEntity _datEntSetI)
							_datEntSetI.getEntityData().set(GooeyNullEntity.DATA_x, (int) X);
						if (entity instanceof GooeyNullEntity _datEntSetI)
							_datEntSetI.getEntityData().set(GooeyNullEntity.DATA_y, (int) Y);
						if (entity instanceof GooeyNullEntity _datEntSetI)
							_datEntSetI.getEntityData().set(GooeyNullEntity.DATA_z, (int) Z);
						break;
					}
				}
			}
		}
		if ((entity instanceof GooeyNullEntity _datEntI ? _datEntI.getEntityData().get(GooeyNullEntity.DATA_tp) : 0) == 13) {
			output_x = entity instanceof GooeyNullEntity _datEntI ? _datEntI.getEntityData().get(GooeyNullEntity.DATA_x) : 0;
			output_y = entity instanceof GooeyNullEntity _datEntI ? _datEntI.getEntityData().get(GooeyNullEntity.DATA_y) : 0;
			output_z = entity instanceof GooeyNullEntity _datEntI ? _datEntI.getEntityData().get(GooeyNullEntity.DATA_z) : 0;
			world.levelEvent(2001, BlockPos.containing(output_x, output_y - 0.5, output_z), Block.getId(UnusualAdventuresModBlocks.NULL_BLOCK.get().defaultBlockState()));
			{
				Entity _ent = entity;
				_ent.teleportTo(output_x, output_y, output_z);
				if (_ent instanceof ServerPlayer _serverPlayer)
					_serverPlayer.connection.teleport(output_x, output_y, output_z, _ent.getYRot(), _ent.getXRot());
			}
			if (world instanceof ServerLevel _level)
				_level.sendParticles((SimpleParticleType) (UnusualAdventuresModParticleTypes.BINARY.get()), output_x, output_y, output_z, 15, 0.5, 1, 0.5, 0);
		}
	}
}