package net.mcreator.unusualadventures.procedures;

import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.common.extensions.ILevelExtension;
import net.neoforged.neoforge.capabilities.Capabilities;

import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.mcreator.unusualadventures.init.UnusualAdventuresModParticleTypes;
import net.mcreator.unusualadventures.init.UnusualAdventuresModItems;
import net.mcreator.unusualadventures.init.UnusualAdventuresModBlocks;

public class TransferPadEntityWalksOnTheBlockProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double offset_x = 0;
		double offset_z = 0;
		double slot = 0;
		double TpX = 0;
		double TpY = 0;
		double TpZ = 0;
		ItemStack scan_item = ItemStack.EMPTY;
		if ((entity.level().dimension()) == ResourceKey.create(Registries.DIMENSION, ResourceLocation.parse("unusual_adventures:nullspace"))) {
			if (x + 0.8 > entity.getX() && x + 0.2 < entity.getX() && z + 0.8 > entity.getZ() && z + 0.2 < entity.getZ()) {
				for (Direction directioniterator : Direction.Plane.HORIZONTAL) {
					if ((world.getBlockState(BlockPos.containing(x + directioniterator.getStepX(), y, z + directioniterator.getStepZ()))).getBlock() == UnusualAdventuresModBlocks.COMPUTER.get()) {
						if (((world.getBlockState(BlockPos.containing(x + directioniterator.getStepX(), y, z + directioniterator.getStepZ()))).getBlock().getStateDefinition().getProperty("state") instanceof EnumProperty _getep18
								? (world.getBlockState(BlockPos.containing(x + directioniterator.getStepX(), y, z + directioniterator.getStepZ()))).getValue(_getep18).toString()
								: "").equals("coding")) {
							if (entity instanceof Player _player && !_player.level().isClientSide())
								_player.displayClientMessage(Component.literal("state"), false);
						}
						if ((getBlockNBTString(world, BlockPos.containing(x + directioniterator.getStepX(), y, z + directioniterator.getStepZ()), "CodingState")).equals("tp")
								&& ((world.getBlockState(BlockPos.containing(x + directioniterator.getStepX(), y, z + directioniterator.getStepZ()))).getBlock().getStateDefinition().getProperty("state") instanceof EnumProperty _getep30
										? (world.getBlockState(BlockPos.containing(x + directioniterator.getStepX(), y, z + directioniterator.getStepZ()))).getValue(_getep30).toString()
										: "").equals("CODING")) {
							offset_x = x + directioniterator.getStepX();
							offset_z = z + directioniterator.getStepZ();
							slot = 0;
							for (int index0 = 0; index0 < 4; index0++) {
								scan_item = (itemFromBlockInventory(world, BlockPos.containing(x + directioniterator.getStepX(), y, z + directioniterator.getStepZ()), (int) slot).copy()).copy();
								if (scan_item.getItem() == UnusualAdventuresModItems.TRANSFER_MODULE.get()) {
									if (scan_item.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getBoolean("bound") == true) {
										if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "cooldown") == 0) {
											if (!world.isClientSide()) {
												BlockPos _bp = BlockPos.containing(x, y, z);
												BlockEntity _blockEntity = world.getBlockEntity(_bp);
												BlockState _bs = world.getBlockState(_bp);
												if (_blockEntity != null)
													_blockEntity.getPersistentData().putDouble("cooldown", 5);
												if (world instanceof Level _level)
													_level.sendBlockUpdated(_bp, _bs, _bs, 3);
											}
											TpX = scan_item.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDouble("TpX") + 0.5;
											TpY = scan_item.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDouble("TpY") + 0.5;
											TpZ = scan_item.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDouble("TpZ") + 0.5;
											entity.fallDistance = 0;
											{
												Entity _ent = entity;
												_ent.teleportTo(TpX, TpY, TpZ);
												if (_ent instanceof ServerPlayer _serverPlayer)
													_serverPlayer.connection.teleport(TpX, TpY, TpZ, _ent.getYRot(), _ent.getXRot());
											}
											if (!world.isClientSide()) {
												BlockPos _bp = BlockPos.containing(TpX, TpY, TpZ);
												BlockEntity _blockEntity = world.getBlockEntity(_bp);
												BlockState _bs = world.getBlockState(_bp);
												if (_blockEntity != null)
													_blockEntity.getPersistentData().putDouble("cooldown", 5);
												if (world instanceof Level _level)
													_level.sendBlockUpdated(_bp, _bs, _bs, 3);
											}
											if (world instanceof ServerLevel _level)
												_level.sendParticles((SimpleParticleType) (UnusualAdventuresModParticleTypes.BINARY.get()), (x + 0.5), (y + 1), (z + 0.5), 15, 0.5, 1, 0.5, 0);
											if (world instanceof ServerLevel _level)
												_level.sendParticles((SimpleParticleType) (UnusualAdventuresModParticleTypes.BINARY.get()), TpX, (TpY + 0.5), TpZ, 15, 0.5, 1, 0.5, 0);
											if (world instanceof Level _level) {
												if (!_level.isClientSide()) {
													_level.playSound(null, BlockPos.containing(x + 0.5, y + 0.5, z + 0.5), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.player.teleport")), SoundSource.PLAYERS, (float) 0.8,
															(float) 1.6);
												} else {
													_level.playLocalSound((x + 0.5), (y + 0.5), (z + 0.5), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.player.teleport")), SoundSource.PLAYERS, (float) 0.8, (float) 1.6, false);
												}
											}
											if (world instanceof Level _level) {
												if (!_level.isClientSide()) {
													_level.playSound(null, BlockPos.containing(TpX, TpY + 0.5, TpZ), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.player.teleport")), SoundSource.PLAYERS, (float) 0.8, (float) 1.6);
												} else {
													_level.playLocalSound(TpX, (TpY + 0.5), TpZ, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.player.teleport")), SoundSource.PLAYERS, (float) 0.8, (float) 1.6, false);
												}
											}
											if (!((world.getBlockState(BlockPos.containing(TpX, TpY, TpZ))).getBlock() == UnusualAdventuresModBlocks.TRANSFER_PAD.get())) {
												entity.hurt(new DamageSource(world.holderOrThrow(DamageTypes.FALL)), Mth.nextInt(RandomSource.create(), 3, 6));
												if (entity instanceof Player _player && !_player.level().isClientSide())
													_player.displayClientMessage(Component.literal((Component.translatable("item.unusual_adventures.transfer_module.damage").getString())), true);
											}
											break;
										} else {
											if (entity instanceof Player _player && !_player.level().isClientSide())
												_player.displayClientMessage(Component.literal((Component.translatable("item.unusual_adventures.transfer_module.cooldown").getString())), true);
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

	private static String getBlockNBTString(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getString(tag);
		return "";
	}

	private static ItemStack itemFromBlockInventory(LevelAccessor world, BlockPos pos, int slot) {
		if (world instanceof ILevelExtension ext) {
			IItemHandler itemHandler = ext.getCapability(Capabilities.ItemHandler.BLOCK, pos, null);
			if (itemHandler != null)
				return itemHandler.getStackInSlot(slot);
		}
		return ItemStack.EMPTY;
	}

	private static double getBlockNBTNumber(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getDouble(tag);
		return -1;
	}
}