package net.mcreator.unusualadventures.procedures;

import org.checkerframework.checker.units.qual.s;

import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.common.extensions.ILevelExtension;
import net.neoforged.neoforge.capabilities.Capabilities;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.network.chat.Component;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

import net.mcreator.unusualadventures.init.UnusualAdventuresModMenus;
import net.mcreator.unusualadventures.init.UnusualAdventuresModItems;
import net.mcreator.unusualadventures.init.UnusualAdventuresModBlocks;
import net.mcreator.unusualadventures.block.NullspacePortalBlock;

import java.util.ArrayList;

public class ExecuteCommandProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, String commandText) {
		if (entity == null || commandText == null)
			return;
		String minute_zero = "";
		String hour_zero = "";
		String time = "";
		String input = "";
		double hour = 0;
		double minute = 0;
		double slot = 0;
		double maxDiscs = 0;
		double portalX = 0;
		double portalY = 0;
		double portalZ = 0;
		input = commandText;
		if (input.startsWith("print ")) {
			if (entity instanceof Player _player && _player.containerMenu instanceof UnusualAdventuresModMenus.MenuAccessor _menu)
				_menu.sendMenuStateUpdate(_player, 0, "console", "", true);
			for (Entity entityiterator : new ArrayList<>(world.players())) {
				if (entityiterator instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal(("\u00A72[" + entity.getDisplayName().getString() + "] " + input.substring(6))), false);
			}
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null)
					_blockEntity.getPersistentData().putString("Answer", "Message sent");
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
		} else if (input.startsWith("transfer link")) {
			slot = 0;
			for (int index0 = 0; index0 < 4; index0++) {
				if ((itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).copy()).getItem() == UnusualAdventuresModItems.TRANSFER_MODULE.get()) {
					if (entity instanceof Player _player && _player.containerMenu instanceof UnusualAdventuresModMenus.MenuAccessor _menu)
						_menu.sendMenuStateUpdate(_player, 0, "console", "", true);
					if ((itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).copy()).getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getBoolean("bound") == true) {
						if ((entity.level().dimension()) == ResourceKey.create(Registries.DIMENSION, ResourceLocation.parse("unusual_adventures:nullspace"))) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putString("Answer", "");
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putString("CodingState", "tp");
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
							{
								String _value = "coding";
								BlockPos _pos = BlockPos.containing(x, y, z);
								BlockState _bs = world.getBlockState(_pos);
								if (_bs.getBlock().getStateDefinition().getProperty("state") instanceof EnumProperty _enumProp && _enumProp.getValue(_value).isPresent())
									world.setBlock(_pos, _bs.setValue(_enumProp, (Enum) _enumProp.getValue(_value).get()), 3);
							}
							if (world instanceof Level _level)
								_level.updateNeighborsAt(BlockPos.containing(x, y, z), _level.getBlockState(BlockPos.containing(x, y, z)).getBlock());
							if (entity instanceof Player _player)
								_player.closeContainer();
							break;
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putString("Answer", "Only works in the Nullspace");
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
					} else {
						if (!world.isClientSide()) {
							BlockPos _bp = BlockPos.containing(x, y, z);
							BlockEntity _blockEntity = world.getBlockEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_blockEntity != null)
								_blockEntity.getPersistentData().putString("Answer", "Disk has to be linked to a Transfer Pad");
							if (world instanceof Level _level)
								_level.sendBlockUpdated(_bp, _bs, _bs, 3);
						}
					}
				}
				slot = slot + 1;
			}
		} else if (input.startsWith("disk")) {
			if (entity instanceof Player _player && _player.containerMenu instanceof UnusualAdventuresModMenus.MenuAccessor _menu)
				_menu.sendMenuStateUpdate(_player, 0, "console", "", true);
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null)
					_blockEntity.getPersistentData().putString("Answer", ("1. Shut down computer." + System.lineSeparator() + "> 2. Sneak-Interact to access storage"));
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
		} else if (input.startsWith("time")) {
			slot = 0;
			for (int index1 = 0; index1 < 4; index1++) {
				if ((itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).copy()).getItem() == UnusualAdventuresModItems.ANALYSE_MODULE.get()) {
					if (entity instanceof Player _player && _player.containerMenu instanceof UnusualAdventuresModMenus.MenuAccessor _menu)
						_menu.sendMenuStateUpdate(_player, 0, "console", "", true);
					if ((input.substring(5)).equals("hour")) {
						hour = ((world.dayTime() + 6000) % 24000) / 1000;
						minute = (world.dayTime() % 1000) / 16.7;
						if ((new java.text.DecimalFormat("##").format(hour)).length() == 1) {
							hour_zero = "0";
						} else {
							hour_zero = "";
						}
						if ((new java.text.DecimalFormat("##").format(minute)).startsWith("60")) {
							minute = 0;
							hour = hour + 1;
						}
						if ((new java.text.DecimalFormat("##").format(minute)).length() == 1) {
							minute_zero = "0";
						} else {
							minute_zero = "";
						}
						if (!world.isClientSide()) {
							BlockPos _bp = BlockPos.containing(x, y, z);
							BlockEntity _blockEntity = world.getBlockEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_blockEntity != null)
								_blockEntity.getPersistentData().putString("Answer", ("Current time is : " + hour_zero + new java.text.DecimalFormat("##").format(hour) + ":" + minute_zero + new java.text.DecimalFormat("##").format(minute)));
							if (world instanceof Level _level)
								_level.sendBlockUpdated(_bp, _bs, _bs, 3);
						}
					} else if ((input.substring(5)).equals("day")) {
						if (!world.isClientSide()) {
							BlockPos _bp = BlockPos.containing(x, y, z);
							BlockEntity _blockEntity = world.getBlockEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_blockEntity != null)
								_blockEntity.getPersistentData().putString("Answer", ("Current days is : " + world.getServer().overworld().getGameTime() / 24000 + ""));
							if (world instanceof Level _level)
								_level.sendBlockUpdated(_bp, _bs, _bs, 3);
						}
					} else {
						if (!world.isClientSide()) {
							BlockPos _bp = BlockPos.containing(x, y, z);
							BlockEntity _blockEntity = world.getBlockEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_blockEntity != null)
								_blockEntity.getPersistentData().putString("Answer", "Invalid time input");
							if (world instanceof Level _level)
								_level.sendBlockUpdated(_bp, _bs, _bs, 3);
						}
					}
					break;
				}
				slot = slot + 1;
			}
		} else if (input.startsWith("power ")) {
			slot = 0;
			for (int index2 = 0; index2 < 4; index2++) {
				if ((itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).copy()).getItem() == UnusualAdventuresModItems.REDSTONE_MODULE.get()) {
					if (new Object() {
						double convert(String s) {
							try {
								return Double.parseDouble(s.trim());
							} catch (Exception e) {
							}
							return 0;
						}
					}.convert(input.substring(6)) >= 1 && new Object() {
						double convert(String s) {
							try {
								return Double.parseDouble(s.trim());
							} catch (Exception e) {
							}
							return 0;
						}
					}.convert(input.substring(6)) <= 15) {
						if (!world.isClientSide()) {
							BlockPos _bp = BlockPos.containing(x, y, z);
							BlockEntity _blockEntity = world.getBlockEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_blockEntity != null)
								_blockEntity.getPersistentData().putString("Answer", "");
							if (world instanceof Level _level)
								_level.sendBlockUpdated(_bp, _bs, _bs, 3);
						}
						if (!world.isClientSide()) {
							BlockPos _bp = BlockPos.containing(x, y, z);
							BlockEntity _blockEntity = world.getBlockEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_blockEntity != null)
								_blockEntity.getPersistentData().putString("CodingState", "power");
							if (world instanceof Level _level)
								_level.sendBlockUpdated(_bp, _bs, _bs, 3);
						}
						{
							String _value = "coding";
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
								_blockEntity.getPersistentData().putDouble("power", new Object() {
									double convert(String s) {
										try {
											return Double.parseDouble(s.trim());
										} catch (Exception e) {
										}
										return 0;
									}
								}.convert(input.substring(6)));
							if (world instanceof Level _level)
								_level.sendBlockUpdated(_bp, _bs, _bs, 3);
						}
						if (world instanceof Level _level)
							_level.updateNeighborsAt(BlockPos.containing(x, y, z), _level.getBlockState(BlockPos.containing(x, y, z)).getBlock());
						if (entity instanceof Player _player)
							_player.closeContainer();
					} else {
						if (!world.isClientSide()) {
							BlockPos _bp = BlockPos.containing(x, y, z);
							BlockEntity _blockEntity = world.getBlockEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_blockEntity != null)
								_blockEntity.getPersistentData().putString("Answer", "Invalid power value");
							if (world instanceof Level _level)
								_level.sendBlockUpdated(_bp, _bs, _bs, 3);
						}
					}
					break;
				}
				slot = slot + 1;
			}
		} else if (input.startsWith("clock ")) {
			slot = 0;
			for (int index3 = 0; index3 < 4; index3++) {
				if ((itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).copy()).getItem() == UnusualAdventuresModItems.REDSTONE_MODULE.get()) {
					if (new Object() {
						double convert(String s) {
							try {
								return Double.parseDouble(s.trim());
							} catch (Exception e) {
							}
							return 0;
						}
					}.convert(input.substring(6)) >= 2 && new Object() {
						double convert(String s) {
							try {
								return Double.parseDouble(s.trim());
							} catch (Exception e) {
							}
							return 0;
						}
					}.convert(input.substring(6)) <= 60) {
						if (!world.isClientSide()) {
							BlockPos _bp = BlockPos.containing(x, y, z);
							BlockEntity _blockEntity = world.getBlockEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_blockEntity != null)
								_blockEntity.getPersistentData().putString("Answer", "");
							if (world instanceof Level _level)
								_level.sendBlockUpdated(_bp, _bs, _bs, 3);
						}
						if (!world.isClientSide()) {
							BlockPos _bp = BlockPos.containing(x, y, z);
							BlockEntity _blockEntity = world.getBlockEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_blockEntity != null)
								_blockEntity.getPersistentData().putString("CodingState", "clock");
							if (world instanceof Level _level)
								_level.sendBlockUpdated(_bp, _bs, _bs, 3);
						}
						{
							String _value = "coding";
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
								_blockEntity.getPersistentData().putDouble("waiting", new Object() {
									double convert(String s) {
										try {
											return Double.parseDouble(s.trim());
										} catch (Exception e) {
										}
										return 0;
									}
								}.convert(input.substring(6)));
							if (world instanceof Level _level)
								_level.sendBlockUpdated(_bp, _bs, _bs, 3);
						}
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
						if (entity instanceof Player _player)
							_player.closeContainer();
					} else {
						if (!world.isClientSide()) {
							BlockPos _bp = BlockPos.containing(x, y, z);
							BlockEntity _blockEntity = world.getBlockEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_blockEntity != null)
								_blockEntity.getPersistentData().putString("Answer", "Invalid delay value");
							if (world instanceof Level _level)
								_level.sendBlockUpdated(_bp, _bs, _bs, 3);
						}
					}
					break;
				}
				slot = slot + 1;
			}
		} else if (input.startsWith("wallpaper")) {
			if (entity instanceof Player _player && _player.containerMenu instanceof UnusualAdventuresModMenus.MenuAccessor _menu)
				_menu.sendMenuStateUpdate(_player, 0, "console", "", true);
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null)
					_blockEntity.getPersistentData().putString("Answer", "");
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
			{
				String _value = "background";
				BlockPos _pos = BlockPos.containing(x, y, z);
				BlockState _bs = world.getBlockState(_pos);
				if (_bs.getBlock().getStateDefinition().getProperty("state") instanceof EnumProperty _enumProp && _enumProp.getValue(_value).isPresent())
					world.setBlock(_pos, _bs.setValue(_enumProp, (Enum) _enumProp.getValue(_value).get()), 3);
			}
			if (entity instanceof Player _player)
				_player.closeContainer();
		} else if (input.startsWith("play ")) {
			slot = 0;
			for (int index4 = 0; index4 < 4; index4++) {
				if ((itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).copy()).getItem() == UnusualAdventuresModItems.MUSIC_MODULE.get()) {
					if (entity instanceof Player _player && _player.containerMenu instanceof UnusualAdventuresModMenus.MenuAccessor _menu)
						_menu.sendMenuStateUpdate(_player, 0, "console", "", true);
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null)
							_blockEntity.getPersistentData().putBoolean("IsPlayingMusic", true);
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
					maxDiscs = world.registryAccess().registryOrThrow(Registries.JUKEBOX_SONG).size();
					if ((int) new Object() {
						double convert(String s) {
							try {
								return Double.parseDouble(s.trim());
							} catch (Exception e) {
							}
							return 0;
						}
					}.convert(input.substring(5)) >= 1 && (int) new Object() {
						double convert(String s) {
							try {
								return Double.parseDouble(s.trim());
							} catch (Exception e) {
							}
							return 0;
						}
					}.convert(input.substring(5)) <= maxDiscs) {
						if (!world.isClientSide()) {
							BlockPos _bp = BlockPos.containing(x, y, z);
							BlockEntity _blockEntity = world.getBlockEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_blockEntity != null)
								_blockEntity.getPersistentData().putString("Answer", "");
							if (world instanceof Level _level)
								_level.sendBlockUpdated(_bp, _bs, _bs, 3);
						}
						{
							String _value = "music";
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
								_blockEntity.getPersistentData().putDouble("track", ((int) new Object() {
									double convert(String s) {
										try {
											return Double.parseDouble(s.trim());
										} catch (Exception e) {
										}
										return 0;
									}
								}.convert(input.substring(5)) - 1));
							if (world instanceof Level _level)
								_level.sendBlockUpdated(_bp, _bs, _bs, 3);
						}
						if (entity instanceof Player _player)
							_player.closeContainer();
					} else {
						if (!world.isClientSide()) {
							BlockPos _bp = BlockPos.containing(x, y, z);
							BlockEntity _blockEntity = world.getBlockEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_blockEntity != null)
								_blockEntity.getPersistentData().putString("Answer", "Invalid track value");
							if (world instanceof Level _level)
								_level.sendBlockUpdated(_bp, _bs, _bs, 3);
						}
					}
					break;
				}
				slot = slot + 1;
			}
		} else if (input.startsWith("shutdown")) {
			if (entity instanceof Player _player && _player.containerMenu instanceof UnusualAdventuresModMenus.MenuAccessor _menu)
				_menu.sendMenuStateUpdate(_player, 0, "console", "", true);
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null)
					_blockEntity.getPersistentData().putString("Answer", "");
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
			{
				String _value = "off";
				BlockPos _pos = BlockPos.containing(x, y, z);
				BlockState _bs = world.getBlockState(_pos);
				if (_bs.getBlock().getStateDefinition().getProperty("state") instanceof EnumProperty _enumProp && _enumProp.getValue(_value).isPresent())
					world.setBlock(_pos, _bs.setValue(_enumProp, (Enum) _enumProp.getValue(_value).get()), 3);
			}
			if (entity instanceof Player _player)
				_player.closeContainer();
		} else if (input.startsWith("scan")) {
			slot = 0;
			for (int index5 = 0; index5 < 4; index5++) {
				if ((itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).copy()).getItem() == UnusualAdventuresModItems.ANALYSE_MODULE.get()) {
					if ((input.substring(5, 11)).equals("player") || (input.substring(5, 12)).equals("monster") || (input.substring(5, 9)).equals("both")) {
						if (new Object() {
							double convert(String s) {
								try {
									return Double.parseDouble(s.trim());
								} catch (Exception e) {
								}
								return 0;
							}
						}.convert(input.substring((input).length() - 2)) >= 12 && new Object() {
							double convert(String s) {
								try {
									return Double.parseDouble(s.trim());
								} catch (Exception e) {
								}
								return 0;
							}
						}.convert(input.substring((input).length() - 2)) <= 64) {
							if (entity instanceof Player _player && _player.containerMenu instanceof UnusualAdventuresModMenus.MenuAccessor _menu)
								_menu.sendMenuStateUpdate(_player, 0, "console", "", true);
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putString("Answer", "Scan done !");
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
							if ((input.substring(5, 11)).equals("player")) {
								if (!world.isClientSide()) {
									BlockPos _bp = BlockPos.containing(x, y, z);
									BlockEntity _blockEntity = world.getBlockEntity(_bp);
									BlockState _bs = world.getBlockState(_bp);
									if (_blockEntity != null)
										_blockEntity.getPersistentData().putString("ScanType", "player");
									if (world instanceof Level _level)
										_level.sendBlockUpdated(_bp, _bs, _bs, 3);
								}
							} else if ((input.substring(5, 12)).equals("monster")) {
								if (!world.isClientSide()) {
									BlockPos _bp = BlockPos.containing(x, y, z);
									BlockEntity _blockEntity = world.getBlockEntity(_bp);
									BlockState _bs = world.getBlockState(_bp);
									if (_blockEntity != null)
										_blockEntity.getPersistentData().putString("ScanType", "monster");
									if (world instanceof Level _level)
										_level.sendBlockUpdated(_bp, _bs, _bs, 3);
								}
							} else {
								if (!world.isClientSide()) {
									BlockPos _bp = BlockPos.containing(x, y, z);
									BlockEntity _blockEntity = world.getBlockEntity(_bp);
									BlockState _bs = world.getBlockState(_bp);
									if (_blockEntity != null)
										_blockEntity.getPersistentData().putString("ScanType", "both");
									if (world instanceof Level _level)
										_level.sendBlockUpdated(_bp, _bs, _bs, 3);
								}
							}
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("ScanRange", new Object() {
										double convert(String s) {
											try {
												return Double.parseDouble(s.trim());
											} catch (Exception e) {
											}
											return 0;
										}
									}.convert(commandText.substring((commandText).length() - 2)));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
							{
								String _value = "scanning";
								BlockPos _pos = BlockPos.containing(x, y, z);
								BlockState _bs = world.getBlockState(_pos);
								if (_bs.getBlock().getStateDefinition().getProperty("state") instanceof EnumProperty _enumProp && _enumProp.getValue(_value).isPresent())
									world.setBlock(_pos, _bs.setValue(_enumProp, (Enum) _enumProp.getValue(_value).get()), 3);
							}
							if (entity instanceof Player _player)
								_player.closeContainer();
						} else {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putString("Answer", "Need valid range (12-64)");
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
					} else {
						if (!world.isClientSide()) {
							BlockPos _bp = BlockPos.containing(x, y, z);
							BlockEntity _blockEntity = world.getBlockEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_blockEntity != null)
								_blockEntity.getPersistentData().putString("Answer", "Need specification (player/monster/both)");
							if (world instanceof Level _level)
								_level.sendBlockUpdated(_bp, _bs, _bs, 3);
						}
					}
					break;
				}
				slot = slot + 1;
			}
		} else if (input.startsWith("rift open")) {
			slot = 0;
			for (int index6 = 0; index6 < 4; index6++) {
				if ((itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).copy()).getItem() == UnusualAdventuresModItems.EXPERIMENTAL_MODULE.get()) {
					Direction facing = world.getBlockState(BlockPos.containing(x, y, z)).getValue(BlockStateProperties.HORIZONTAL_FACING);
					if (world instanceof Level _level)
						NullspacePortalBlock.portalSpawn(_level, BlockPos.containing(x, y, z).relative(facing.getOpposite()).relative(facing.getClockWise()));
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.respawn_anchor.set_spawn")), SoundSource.BLOCKS, (float) 0.9, 2);
						} else {
							_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.respawn_anchor.set_spawn")), SoundSource.BLOCKS, (float) 0.9, 2, false);
						}
					}
					if (entity instanceof Player _player && _player.containerMenu instanceof UnusualAdventuresModMenus.MenuAccessor _menu)
						_menu.sendMenuStateUpdate(_player, 0, "console", "", true);
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null)
							_blockEntity.getPersistentData().putString("Answer", "Rift formation attempted...");
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
					if (entity instanceof Player _player)
						_player.closeContainer();
					break;
				}
				slot = slot + 1;
			}
		}
		if (input.startsWith("help")) {
			if (entity instanceof Player _player && _player.containerMenu instanceof UnusualAdventuresModMenus.MenuAccessor _menu)
				_menu.sendMenuStateUpdate(_player, 0, "console", "", true);
			if ((input.substring(5)).startsWith("print")) {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null)
						_blockEntity.getPersistentData().putString("Answer", ("Broadcast your message with special" + System.lineSeparator() + "formatting"));
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			} else if ((input.substring(5)).startsWith("transfer")) {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null)
						_blockEntity.getPersistentData().putString("Answer", ("Set destination of Transfer Pads next to" + System.lineSeparator() + "this block to the Pad the Disk is linked to"));
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			} else if ((input.substring(5)).startsWith("time")) {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null)
						_blockEntity.getPersistentData().putString("Answer", ("Provide informations about the current" + System.lineSeparator() + "day, or time of the day"));
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			} else if ((input.substring(5)).startsWith("power")) {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null)
						_blockEntity.getPersistentData().putString("Answer", ("Emits a Redstone signal equal to the" + System.lineSeparator() + "provided value"));
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			} else if ((input.substring(5)).startsWith("clock")) {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null)
						_blockEntity.getPersistentData().putString("Answer", ("Repeatedly emits a Redstone signal of 15" + System.lineSeparator() + "> A delay of 4 equals 1 second of delay"));
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			} else if ((input.substring(5)).startsWith("wallpaper")) {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null)
						_blockEntity.getPersistentData().putString("Answer", "Display a cosmetic background");
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			} else if ((input.substring(5)).startsWith("play")) {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null)
						_blockEntity.getPersistentData().putString("Answer", ("Play any Music Disc track via an ID" + System.lineSeparator() + "> Tracks IDs are attributed alphabetically"));
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			} else if ((input.substring(5)).startsWith("shutdown")) {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null)
						_blockEntity.getPersistentData().putString("Answer", "Turns off the Computer");
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			} else if ((input.substring(5)).startsWith("scan")) {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null)
						_blockEntity.getPersistentData().putString("Answer", ("Scan for entities in a short radius" + System.lineSeparator() + "> Emits a Redstone signal of 15 when positive"));
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			} else if ((input.substring(5)).startsWith("rift")) {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null)
						_blockEntity.getPersistentData().putString("Answer", ("Opens a valid nearby portal" + System.lineSeparator() + "> Can be made out of most Nullspace blocks"));
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			}
		}
		if ((input).toLowerCase().startsWith("wassman")) {
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null)
					_blockEntity.getPersistentData().putString("Answer", "Ca avance le montage ?");
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
		} else if ((input).toLowerCase().startsWith("shout it's a rocket")) {
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
						"summon firework_rocket ~ ~1 ~ {LifeTime:30,FireworksItem:{id:firework_rocket,count:1,components:{fireworks:{flight_duration:2,explosions:[{shape:\"burst\",has_twinkle:1,has_trail:1,colors:[I;11743532,15790320],fade_colors:[I;14602026,15790320]}]}}}}");
		} else if ((input).toLowerCase().startsWith("furti")) {
			if (entity instanceof Player _player && _player.containerMenu instanceof UnusualAdventuresModMenus.MenuAccessor _menu)
				_menu.sendMenuStateUpdate(_player, 0, "console", "Furtos", true);
		} else if ((input).toLowerCase().startsWith("dari")) {
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null)
					_blockEntity.getPersistentData().putString("Answer", ("pov : tu as appris que y a de l'or " + System.lineSeparator() + "dans les pc"));
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
		} else if ((input).toLowerCase().startsWith("to0pa")) {
			if (entity instanceof Player _player && _player.containerMenu instanceof UnusualAdventuresModMenus.MenuAccessor _menu)
				_menu.sendMenuStateUpdate(_player, 0, "console", "cacahu\u00E8te", true);
		} else if ((input).toLowerCase().startsWith("sweety")) {
			if (entity instanceof Player _player && _player.containerMenu instanceof UnusualAdventuresModMenus.MenuAccessor _menu)
				_menu.sendMenuStateUpdate(_player, 0, "console", "SWITI", true);
		} else if ((input).toLowerCase().startsWith("ultraman")) {
			if (entity instanceof Player _player)
				_player.closeContainer();
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
		}
	}

	private static ItemStack itemFromBlockInventory(LevelAccessor world, BlockPos pos, int slot) {
		if (world instanceof ILevelExtension ext) {
			IItemHandler itemHandler = ext.getCapability(Capabilities.ItemHandler.BLOCK, pos, null);
			if (itemHandler != null)
				return itemHandler.getStackInSlot(slot);
		}
		return ItemStack.EMPTY;
	}
}