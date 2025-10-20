package net.mcreator.unusualadventures.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.JukeboxSong;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.AdvancementHolder;

import net.mcreator.unusualadventures.init.UnusualAdventuresModBlocks;

import java.util.Comparator;

public class ComputerTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		String state = "";
		double loop = 0;
		double test = 0;
		double timer = 0;
		double clock = 0;
		state = (world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("state") instanceof EnumProperty _getep1 ? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getep1).toString() : "";
		if ((state).equals("LOGO")) {
			if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "timer") >= 0) {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null)
						_blockEntity.getPersistentData().putDouble("timer", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "timer") + 1));
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			} else {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null)
						_blockEntity.getPersistentData().putDouble("timer", 0);
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			}
			timer = getBlockNBTNumber(world, BlockPos.containing(x, y, z), "timer");
			if (timer == 0 || timer == 2) {
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.note_block.bit")), SoundSource.BLOCKS, (float) 0.8, (float) 1.3);
					} else {
						_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.note_block.bit")), SoundSource.BLOCKS, (float) 0.8, (float) 1.3, false);
					}
				}
			} else if (timer == 1 || timer == 3 || timer == 5) {
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.note_block.bit")), SoundSource.BLOCKS, (float) 0.8, (float) 0.85);
					} else {
						_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.note_block.bit")), SoundSource.BLOCKS, (float) 0.8, (float) 0.85, false);
					}
				}
			} else if (timer == 4) {
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.note_block.bit")), SoundSource.BLOCKS, (float) 0.8, (float) 0.65);
					} else {
						_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.note_block.bit")), SoundSource.BLOCKS, (float) 0.8, (float) 0.65, false);
					}
				}
			} else if (timer >= 12) {
				{
					String _value = "console";
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("state") instanceof EnumProperty _enumProp && _enumProp.getValue(_value).isPresent())
						world.setBlock(_pos, _bs.setValue(_enumProp, (Enum) _enumProp.getValue(_value).get()), 3);
				}
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null)
						_blockEntity.getPersistentData().putDouble("timer", 0);
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			}
		} else if ((state).equals("CODING")) {
			if ((getBlockNBTString(world, BlockPos.containing(x, y, z), "CodingState")).equals("clock")) {
				clock = getBlockNBTNumber(world, BlockPos.containing(x, y, z), "clock");
				if (clock >= getBlockNBTNumber(world, BlockPos.containing(x, y, z), "waiting")) {
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null)
							_blockEntity.getPersistentData().putDouble("clock", 0);
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
					if (world instanceof Level _level)
						_level.updateNeighborsAt(BlockPos.containing(x, y, z), _level.getBlockState(BlockPos.containing(x, y, z)).getBlock());
				} else {
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null)
							_blockEntity.getPersistentData().putDouble("clock", (clock + 1));
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
				}
				if (clock == 1) {
					if (world instanceof Level _level)
						_level.updateNeighborsAt(BlockPos.containing(x, y, z), _level.getBlockState(BlockPos.containing(x, y, z)).getBlock());
				}
			}
		} else if ((state).equals("SCANNING")) {
			if ((getBlockNBTString(world, BlockPos.containing(x, y, z), "ScanType")).equals("monster")) {
				if (!((findEntityInWorldRange(world, Monster.class, x, y, z, (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "ScanRange")))) == null)) {
					{
						String _value = "warning";
						BlockPos _pos = BlockPos.containing(x, y, z);
						BlockState _bs = world.getBlockState(_pos);
						if (_bs.getBlock().getStateDefinition().getProperty("state") instanceof EnumProperty _enumProp && _enumProp.getValue(_value).isPresent())
							world.setBlock(_pos, _bs.setValue(_enumProp, (Enum) _enumProp.getValue(_value).get()), 3);
					}
					if (world instanceof Level _level)
						_level.updateNeighborsAt(BlockPos.containing(x, y, z), _level.getBlockState(BlockPos.containing(x, y, z)).getBlock());
				}
			} else if ((getBlockNBTString(world, BlockPos.containing(x, y, z), "ScanType")).equals("player")) {
				if (!(world.getNearestPlayer(x, y, z, (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "ScanRange")), true) == null)) {
					{
						String _value = "warning";
						BlockPos _pos = BlockPos.containing(x, y, z);
						BlockState _bs = world.getBlockState(_pos);
						if (_bs.getBlock().getStateDefinition().getProperty("state") instanceof EnumProperty _enumProp && _enumProp.getValue(_value).isPresent())
							world.setBlock(_pos, _bs.setValue(_enumProp, (Enum) _enumProp.getValue(_value).get()), 3);
					}
					if (world instanceof Level _level)
						_level.updateNeighborsAt(BlockPos.containing(x, y, z), _level.getBlockState(BlockPos.containing(x, y, z)).getBlock());
				}
			} else if ((getBlockNBTString(world, BlockPos.containing(x, y, z), "ScanType")).equals("both")) {
				if (!(world.getNearestPlayer(x, y, z, (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "ScanRange")), true) == null)
						|| !((findEntityInWorldRange(world, Monster.class, x, y, z, (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "ScanRange")))) == null)) {
					{
						String _value = "warning";
						BlockPos _pos = BlockPos.containing(x, y, z);
						BlockState _bs = world.getBlockState(_pos);
						if (_bs.getBlock().getStateDefinition().getProperty("state") instanceof EnumProperty _enumProp && _enumProp.getValue(_value).isPresent())
							world.setBlock(_pos, _bs.setValue(_enumProp, (Enum) _enumProp.getValue(_value).get()), 3);
					}
					if (world instanceof Level _level)
						_level.updateNeighborsAt(BlockPos.containing(x, y, z), _level.getBlockState(BlockPos.containing(x, y, z)).getBlock());
				}
			}
		} else if ((state).equals("WARNING")) {
			if ((getBlockNBTString(world, BlockPos.containing(x, y, z), "ScanType")).equals("monster") && !(!((findEntityInWorldRange(world, Monster.class, x, y, z, (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "ScanRange")))) == null))) {
				{
					String _value = "scanning";
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("state") instanceof EnumProperty _enumProp && _enumProp.getValue(_value).isPresent())
						world.setBlock(_pos, _bs.setValue(_enumProp, (Enum) _enumProp.getValue(_value).get()), 3);
				}
				if (world instanceof Level _level)
					_level.updateNeighborsAt(BlockPos.containing(x, y, z), _level.getBlockState(BlockPos.containing(x, y, z)).getBlock());
			} else if ((getBlockNBTString(world, BlockPos.containing(x, y, z), "ScanType")).equals("player") && !(!(world.getNearestPlayer(x, y, z, (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "ScanRange")), true) == null))) {
				{
					String _value = "scanning";
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("state") instanceof EnumProperty _enumProp && _enumProp.getValue(_value).isPresent())
						world.setBlock(_pos, _bs.setValue(_enumProp, (Enum) _enumProp.getValue(_value).get()), 3);
				}
				if (world instanceof Level _level)
					_level.updateNeighborsAt(BlockPos.containing(x, y, z), _level.getBlockState(BlockPos.containing(x, y, z)).getBlock());
			} else if ((getBlockNBTString(world, BlockPos.containing(x, y, z), "ScanType")).equals("both") && !(!((findEntityInWorldRange(world, Monster.class, x, y, z, (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "ScanRange")))) == null))
					&& !(!(world.getNearestPlayer(x, y, z, (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "ScanRange")), true) == null))) {
				{
					String _value = "scanning";
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("state") instanceof EnumProperty _enumProp && _enumProp.getValue(_value).isPresent())
						world.setBlock(_pos, _bs.setValue(_enumProp, (Enum) _enumProp.getValue(_value).get()), 3);
				}
				if (world instanceof Level _level)
					_level.updateNeighborsAt(BlockPos.containing(x, y, z), _level.getBlockState(BlockPos.containing(x, y, z)).getBlock());
			}
		} else if ((state).equals("MUSIC")) {
			int trackId = (int) getBlockNBTNumber(world, BlockPos.containing(x, y, z), "track");
			long startTick = (long) getBlockNBTNumber(world, BlockPos.containing(x, y, z), "MusicStartTick");
			long currentTick = 0;
			if (world instanceof Level _level) {
				currentTick = _level.getGameTime();
			}
			if (startTick == 0) {
				if (world instanceof Level _level) {
					int registryId = _level.registryAccess().registryOrThrow(Registries.JUKEBOX_SONG).getId(_level.registryAccess().registryOrThrow(Registries.JUKEBOX_SONG).stream().toList().get(trackId));
					_level.levelEvent(null, 1010, BlockPos.containing(x, y, z), registryId);
				}
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					if (_blockEntity != null)
						_blockEntity.getPersistentData().putLong("MusicStartTick", currentTick);
				}
			} else {
				try {
					var jukeboxRegistry = world.registryAccess().registryOrThrow(Registries.JUKEBOX_SONG);
					var songs = jukeboxRegistry.stream().toList();
					if (trackId < songs.size()) {
						JukeboxSong song = songs.get(trackId);
						long elapsed = currentTick - startTick;
						if (song.hasFinished(elapsed)) {
							if (world instanceof Level _level) {
								int registryId = jukeboxRegistry.getId(song);
								_level.levelEvent(null, 1010, BlockPos.containing(x, y, z), registryId);
							}
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putLong("MusicStartTick", currentTick);
							}
						} else if (elapsed % 20 == 0) {
							world.gameEvent(GameEvent.JUKEBOX_PLAY, BlockPos.containing(x, y, z), GameEvent.Context.of((BlockState) null));
						}
					}
				} catch (Exception e) {
				}
			}
		}
		if (((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("waterlogged") instanceof BooleanProperty _getbp58 && (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getbp58)) == true) {
			if (world instanceof Level _level && !_level.isClientSide())
				_level.explode(null, x, y, z, 0, Level.ExplosionInteraction.BLOCK);
			if (world instanceof ServerLevel _level)
				_level.sendParticles(ParticleTypes.LAVA, (x + 0.5), (y + 0.5), (z + 0.5), 10, 1, 1, 1, 0);
			if (world instanceof ServerLevel _level)
				_level.sendParticles(ParticleTypes.SMOKE, (x + 0.5), (y + 0.5), (z + 0.5), 5, 1, 1, 1, 0);
			{
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockState _bs = UnusualAdventuresModBlocks.BROKEN_COMPUTER.get().defaultBlockState();
				BlockState _bso = world.getBlockState(_bp);
				for (Property<?> _propertyOld : _bso.getProperties()) {
					Property _propertyNew = _bs.getBlock().getStateDefinition().getProperty(_propertyOld.getName());
					if (_propertyNew != null && _bs.getValue(_propertyNew) != null)
						try {
							_bs = _bs.setValue(_propertyNew, _bso.getValue(_propertyOld));
						} catch (Exception e) {
						}
				}
				BlockEntity _be = world.getBlockEntity(_bp);
				CompoundTag _bnbt = null;
				if (_be != null) {
					_bnbt = _be.saveWithFullMetadata(world.registryAccess());
					_be.setRemoved();
				}
				world.setBlock(_bp, _bs, 3);
				if (_bnbt != null) {
					_be = world.getBlockEntity(_bp);
					if (_be != null) {
						try {
							_be.loadWithComponents(_bnbt, world.registryAccess());
						} catch (Exception ignored) {
						}
					}
				}
			}
			{
				final Vec3 _center = new Vec3(x, y, z);
				for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(8 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList()) {
					if (entityiterator instanceof Player) {
						if (!(entityiterator instanceof ServerPlayer _plr64 && _plr64.level() instanceof ServerLevel
								&& _plr64.getAdvancements().getOrStartProgress(_plr64.server.getAdvancements().get(ResourceLocation.parse("unusual_adventures:break_computer"))).isDone())) {
							if (entityiterator instanceof ServerPlayer _player) {
								AdvancementHolder _adv = _player.server.getAdvancements().get(ResourceLocation.parse("unusual_adventures:break_computer"));
								if (_adv != null) {
									AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
									if (!_ap.isDone()) {
										for (String criteria : _ap.getRemainingCriteria())
											_player.getAdvancements().award(_adv, criteria);
									}
								}
							}
						}
					}
				}
			}
		}
	}

	private static double getBlockNBTNumber(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getDouble(tag);
		return -1;
	}

	private static String getBlockNBTString(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getString(tag);
		return "";
	}

	private static Entity findEntityInWorldRange(LevelAccessor world, Class<? extends Entity> clazz, double x, double y, double z, double range) {
		return (Entity) world.getEntitiesOfClass(clazz, AABB.ofSize(new Vec3(x, y, z), range, range, range), e -> true).stream().sorted(Comparator.comparingDouble(e -> e.distanceToSqr(x, y, z))).findFirst().orElse(null);
	}
}