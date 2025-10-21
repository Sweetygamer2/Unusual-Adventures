package net.mcreator.unusualadventures.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.GameType;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.BlockPos;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.client.Minecraft;

import net.mcreator.unusualadventures.init.UnusualAdventuresModParticleTypes;

public class NullGauntletLivingEntityIsHitWithItemProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, Entity sourceentity, ItemStack itemstack) {
		if (entity == null || sourceentity == null)
			return;
		if ((itemstack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getString("state")).equals("kb")) {
			if (itemstack.getDamageValue() + 20 < itemstack.getMaxDamage()) {
				if (!(getEntityGameType(sourceentity) == GameType.CREATIVE)) {
					if (world instanceof ServerLevel _level) {
						itemstack.hurtAndBreak(20, _level, null, _stkprov -> {
						});
					}
				}
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.breeze.deflect")), SoundSource.PLAYERS, (float) 0.6,
								(float) Mth.nextDouble(RandomSource.create(), 0.8, 1.2));
					} else {
						_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.breeze.deflect")), SoundSource.PLAYERS, (float) 0.6, (float) Mth.nextDouble(RandomSource.create(), 0.8, 1.2), false);
					}
				}
				if (world instanceof ServerLevel _level)
					_level.sendParticles((SimpleParticleType) (UnusualAdventuresModParticleTypes.BINARY.get()), (entity.getX()), (entity.getY() + 0.5), (entity.getZ()), 4, 0.5, 0.5, 0.5, 0);
				if (Math.random() < 0.3) {
					if (world instanceof ServerLevel _level)
						_level.sendParticles(ParticleTypes.GUST, (entity.getX()), (entity.getY() + 1.2), (entity.getZ()), 1, 0.2, 0.2, 0.2, 0);
				} else {
					if (world instanceof ServerLevel _level)
						_level.sendParticles(ParticleTypes.POOF, (entity.getX()), (entity.getY() + 1.2), (entity.getZ()), 2, 0.4, 0.4, 0.4, 0.05);
				}
			} else {
				if (sourceentity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal((Component.translatable("item.unusual_adventures.null_gauntlet.low_dura").getString())), true);
			}
		}
	}

	private static GameType getEntityGameType(Entity entity) {
		if (entity instanceof ServerPlayer serverPlayer) {
			return serverPlayer.gameMode.getGameModeForPlayer();
		} else if (entity instanceof Player player && player.level().isClientSide()) {
			PlayerInfo playerInfo = Minecraft.getInstance().getConnection().getPlayerInfo(player.getGameProfile().getId());
			if (playerInfo != null)
				return playerInfo.getGameMode();
		}
		return null;
	}
}