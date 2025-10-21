package net.mcreator.unusualadventures.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.GameType;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.tags.BlockTags;
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

import java.util.Comparator;

public class NullGauntletEntitySwingsItemProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		String hand = "";
		double distance = 0;
		double pitch_rad = 0;
		double nb = 0;
		double yaw_rad = 0;
		double raytraceZ = 0;
		double raytraceY = 0;
		double raytraceX = 0;
		if ((entity instanceof Player _plrCldRem1 ? _plrCldRem1.getCooldowns().getCooldownPercent(itemstack.getItem(), 0f) * 100 : 0) == 0) {
			if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == itemstack.getItem()) {
				hand = "mainhand";
			} else {
				hand = "offhand";
			}
			if ((itemstack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getString("state")).equals("dash")) {
				if (itemstack.getDamageValue() + 40 < itemstack.getMaxDamage()) {
					if (world instanceof ServerLevel _level)
						_level.sendParticles((SimpleParticleType) (UnusualAdventuresModParticleTypes.BINARY.get()), x, (y + 0.5), z, 12, 0.4, 0.8, 0.4, 0);
					if (world instanceof ServerLevel _level)
						_level.sendParticles(ParticleTypes.POOF, x, (y + 0.5), z, 6, 0.4, 0.8, 0.4, 0);
					if (hand.equals("offhand")) {
						if (entity instanceof LivingEntity _entity)
							_entity.swing(InteractionHand.OFF_HAND, true);
					} else {
						if (entity instanceof LivingEntity _entity)
							_entity.swing(InteractionHand.MAIN_HAND, true);
					}
					entity.push((entity.getLookAngle().x * 1.2), (entity.getLookAngle().y * 0.6), (entity.getLookAngle().z * 1.2));
					if (entity instanceof Player _player)
						_player.getCooldowns().addCooldown(itemstack.getItem(), 6);
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.breeze.slide")), SoundSource.PLAYERS, (float) 1.1,
									(float) Mth.nextDouble(RandomSource.create(), 1.4, 1.6));
						} else {
							_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.breeze.slide")), SoundSource.PLAYERS, (float) 1.1, (float) Mth.nextDouble(RandomSource.create(), 1.4, 1.6), false);
						}
					}
					if (!(getEntityGameType(entity) == GameType.CREATIVE)) {
						if (world instanceof ServerLevel _level) {
							itemstack.hurtAndBreak(40, _level, null, _stkprov -> {
							});
						}
					}
				} else {
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal((Component.translatable("item.unusual_adventures.null_gauntlet.low_dura").getString())), true);
				}
			} else if ((itemstack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getString("state")).equals("heal")) {
				if (itemstack.getDamageValue() + 100 < itemstack.getMaxDamage()) {
					if (world instanceof ServerLevel _level)
						_level.sendParticles(ParticleTypes.HEART, x, (y + 0.5), z, 8, 0.4, 0.8, 0.4, 0);
					if (world instanceof ServerLevel _level)
						_level.sendParticles((SimpleParticleType) (UnusualAdventuresModParticleTypes.BINARY.get()), x, (y + 0.5), z, 8, 0.4, 0.8, 0.4, 0);
					if (hand.equals("offhand")) {
						if (entity instanceof LivingEntity _entity)
							_entity.swing(InteractionHand.OFF_HAND, true);
					} else {
						if (entity instanceof LivingEntity _entity)
							_entity.swing(InteractionHand.MAIN_HAND, true);
					}
					if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
						_entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 50, 3));
					if (entity instanceof Player _player)
						_player.getCooldowns().addCooldown(itemstack.getItem(), 200);
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.trial_spawner.spawn_item_begin")), SoundSource.PLAYERS, (float) 1.3,
									(float) Mth.nextDouble(RandomSource.create(), 0.6, 0.9));
						} else {
							_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.trial_spawner.spawn_item_begin")), SoundSource.PLAYERS, (float) 1.3, (float) Mth.nextDouble(RandomSource.create(), 0.6, 0.9),
									false);
						}
					}
					if (!(getEntityGameType(entity) == GameType.CREATIVE)) {
						if (world instanceof ServerLevel _level) {
							itemstack.hurtAndBreak(100, _level, null, _stkprov -> {
							});
						}
					}
				} else {
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal((Component.translatable("item.unusual_adventures.null_gauntlet.low_dura").getString())), true);
				}
			} else if ((itemstack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getString("state")).equals("laser")) {
				if (itemstack.getDamageValue() + 40 < itemstack.getMaxDamage()) {
					if (hand.equals("offhand")) {
						if (entity instanceof LivingEntity _entity)
							_entity.swing(InteractionHand.OFF_HAND, true);
					} else {
						if (entity instanceof LivingEntity _entity)
							_entity.swing(InteractionHand.MAIN_HAND, true);
					}
					if (entity instanceof Player _player)
						_player.getCooldowns().addCooldown(itemstack.getItem(), 40);
					if (!(getEntityGameType(entity) == GameType.CREATIVE)) {
						if (world instanceof ServerLevel _level) {
							itemstack.hurtAndBreak(40, _level, null, _stkprov -> {
							});
						}
					}
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.firework_rocket.twinkle")), SoundSource.PLAYERS, (float) 0.1,
									(float) Mth.nextDouble(RandomSource.create(), 1.6, 2));
						} else {
							_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.firework_rocket.twinkle")), SoundSource.PLAYERS, (float) 0.1, (float) Mth.nextDouble(RandomSource.create(), 1.6, 2), false);
						}
					}
					nb = 0.8 + entity.getXRot() * (entity.getXRot() < 0 ? -0.012 : 0.016);
					for (int index0 = 0; index0 < 55; index0++) {
						if (itemstack.getItem() == (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem()) {
							yaw_rad = (entity.getYRot() * (-1) * Math.PI) / 180 - 0.02;
						} else {
							yaw_rad = (entity.getYRot() * (-1) * Math.PI) / 180 + 0.02;
						}
						pitch_rad = (entity.getXRot() * (-1) * Math.PI) / 180;
						raytraceX = entity.getX() + nb * Math.cos(pitch_rad) * Math.sin(yaw_rad);
						raytraceY = entity.getY() + entity.getBbHeight() * 0.85 + nb * Math.sin(pitch_rad);
						raytraceZ = entity.getZ() + nb * Math.cos(pitch_rad) * Math.cos(yaw_rad);
						if (world instanceof ServerLevel _level)
							_level.sendParticles(ParticleTypes.END_ROD, raytraceX, raytraceY, raytraceZ, 1, 0.1, 0.1, 0.1, 0);
						nb = nb + 0.6;
						if (!world.getEntitiesOfClass(LivingEntity.class, new AABB(Vec3.ZERO, Vec3.ZERO).move(new Vec3(raytraceX, raytraceY, raytraceZ)).inflate(0.6 / 2d), e -> true).isEmpty()) {
							{
								final Vec3 _center = new Vec3(raytraceX, raytraceY, raytraceZ);
								for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(0.65 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center)))
										.toList()) {
									if (!(entityiterator == entity)) {
										raytraceX = entity.getX() + (nb - 0.3) * Math.cos(pitch_rad) * Math.sin(yaw_rad);
										raytraceY = entity.getY() + entity.getBbHeight() * 1.1 + (nb - 0.3) * Math.sin(pitch_rad);
										raytraceZ = entity.getZ() + (nb - 0.3) * Math.cos(pitch_rad) * Math.cos(yaw_rad);
										if (world instanceof ServerLevel _level)
											_level.sendParticles(ParticleTypes.FLAME, raytraceX, raytraceY, raytraceZ, 3, 0.2, 0.2, 0.2, 0);
										if (world instanceof ServerLevel _level)
											_level.sendParticles(ParticleTypes.LARGE_SMOKE, raytraceX, raytraceY, raytraceZ, 2, 0.3, 0.3, 0.3, 0);
										entityiterator.hurt(new DamageSource(world.holderOrThrow(DamageTypes.IN_FIRE), entity), 4);
										if (!entityiterator.fireImmune()) {
											entityiterator.invulnerableTime = 0;
										}
										entityiterator.igniteForSeconds(10);
									}
								}
							}
							break;
						}
						if (!(world.isEmptyBlock(BlockPos.containing(Math.floor(raytraceX), Math.floor(raytraceY), Math.floor(raytraceZ)))
								|| (world.getBlockState(BlockPos.containing(Math.floor(raytraceX), Math.floor(raytraceY), Math.floor(raytraceZ)))).getBlock() instanceof LiquidBlock
								|| (world.getBlockState(BlockPos.containing(Math.floor(raytraceX), Math.floor(raytraceY), Math.floor(raytraceZ)))).is(BlockTags.create(ResourceLocation.parse("minecraft:fire")))
								|| (world.getBlockState(BlockPos.containing(Math.floor(raytraceX), Math.floor(raytraceY), Math.floor(raytraceZ)))).is(BlockTags.create(ResourceLocation.parse("minecraft:impermeable")))
										&& world.getBlockState(BlockPos.containing(Math.floor(raytraceX), Math.floor(raytraceY), Math.floor(raytraceZ))).getLightBlock(world,
												BlockPos.containing(Math.floor(raytraceX), Math.floor(raytraceY), Math.floor(raytraceZ))) == 0)) {
							raytraceX = entity.getX() + (nb - 0.3) * Math.cos(pitch_rad) * Math.sin(yaw_rad);
							raytraceY = entity.getY() + entity.getBbHeight() * 1.1 + (nb - 0.3) * Math.sin(pitch_rad);
							raytraceZ = entity.getZ() + (nb - 0.3) * Math.cos(pitch_rad) * Math.cos(yaw_rad);
							if (world instanceof ServerLevel _level)
								_level.sendParticles(ParticleTypes.LARGE_SMOKE, raytraceX, raytraceY, raytraceZ, 3, 0.2, 0.2, 0.2, 0);
							break;
						}
					}
				} else {
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal((Component.translatable("item.unusual_adventures.null_gauntlet.low_dura").getString())), true);
				}
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