package net.mcreator.unusualadventures.procedures;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.mcreator.unusualadventures.init.UnusualAdventuresModParticleTypes;
import net.mcreator.unusualadventures.init.UnusualAdventuresModBlocks;

public class PoweredNullBlockOnTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		double old_x = 0;
		double old_z = 0;
		double main_x = 0;
		double main_z = 0;
		Direction orientation = Direction.NORTH;
		Direction rotation = Direction.NORTH;
		if (Math.random() < 0.3) {
			main_x = x;
			main_z = z;
			orientation = getDirectionFromBlockState(blockstate);
			for (int index0 = 0; index0 < Mth.nextInt(RandomSource.create(), 3, 6); index0++) {
				old_x = main_x;
				old_z = main_z;
				main_x = main_x + orientation.getStepX();
				main_z = main_z + orientation.getStepZ();
				if (((world.getBlockState(BlockPos.containing(main_x, y, main_z))).getBlock() == UnusualAdventuresModBlocks.NULL_BLOCK.get()
						|| (world.getBlockState(BlockPos.containing(main_x, y, main_z))).getBlock() == UnusualAdventuresModBlocks.CHARGED_NULL_BLOCK.get())
						&& (world.getBlockState(BlockPos.containing(old_x, y, old_z))).getBlock() == UnusualAdventuresModBlocks.LIVING_CODE_BLOCK.get()) {
					if (world instanceof ServerLevel _level)
						_level.sendParticles((SimpleParticleType) (UnusualAdventuresModParticleTypes.BINARY.get()), (main_x + 0.5), (y + 1), (main_z + 0.5), Mth.nextInt(RandomSource.create(), 1, 3), 0.6, 0.3, 0.6, 0);
					{
						BlockPos _bp = BlockPos.containing(main_x, y, main_z);
						BlockState _bs = UnusualAdventuresModBlocks.LIVING_CODE_BLOCK.get().defaultBlockState();
						BlockState _bso = world.getBlockState(_bp);
						for (Property<?> _propertyOld : _bso.getProperties()) {
							Property _propertyNew = _bs.getBlock().getStateDefinition().getProperty(_propertyOld.getName());
							if (_propertyNew != null && _bs.getValue(_propertyNew) != null)
								try {
									_bs = _bs.setValue(_propertyNew, _bso.getValue(_propertyOld));
								} catch (Exception e) {
								}
						}
						world.setBlock(_bp, _bs, 3);
					}
					{
						BlockPos _bp = BlockPos.containing(old_x, y, old_z);
						BlockState _bs = (UnusualAdventuresModBlocks.CHARGED_NULL_BLOCK.get().getStateDefinition().getProperty("persistent") instanceof BooleanProperty _withbp14
								? UnusualAdventuresModBlocks.CHARGED_NULL_BLOCK.get().defaultBlockState().setValue(_withbp14, false)
								: UnusualAdventuresModBlocks.CHARGED_NULL_BLOCK.get().defaultBlockState());
						BlockState _bso = world.getBlockState(_bp);
						for (Property<?> _propertyOld : _bso.getProperties()) {
							Property _propertyNew = _bs.getBlock().getStateDefinition().getProperty(_propertyOld.getName());
							if (_propertyNew != null && _bs.getValue(_propertyNew) != null)
								try {
									_bs = _bs.setValue(_propertyNew, _bso.getValue(_propertyOld));
								} catch (Exception e) {
								}
						}
						world.setBlock(_bp, _bs, 3);
					}
					world.levelEvent(2001, BlockPos.containing(old_x, y, old_z), Block.getId(UnusualAdventuresModBlocks.LIVING_CODE_BLOCK.get().defaultBlockState()));
				} else {
					main_x = old_x;
					main_z = old_z;
					break;
				}
			}
			if (Math.random() < 0.2) {
				if (Math.random() < 0.6) {
					rotation = (getDirectionFromBlockState(blockstate)).getClockWise(Direction.Axis.Y);
				} else {
					rotation = (getDirectionFromBlockState(blockstate)).getCounterClockWise(Direction.Axis.Y);
				}
				{
					Direction _dir = rotation;
					BlockPos _pos = BlockPos.containing(main_x, y, main_z);
					BlockState _bs = world.getBlockState(_pos);
					Property<?> _property = _bs.getBlock().getStateDefinition().getProperty("facing");
					if (_property instanceof DirectionProperty _dp && _dp.getPossibleValues().contains(_dir)) {
						world.setBlock(_pos, _bs.setValue(_dp, _dir), 3);
					} else {
						_property = _bs.getBlock().getStateDefinition().getProperty("axis");
						if (_property instanceof EnumProperty _ap && _ap.getPossibleValues().contains(_dir.getAxis()))
							world.setBlock(_pos, _bs.setValue(_ap, _dir.getAxis()), 3);
					}
				}
			}
		}
	}

	private static Direction getDirectionFromBlockState(BlockState blockState) {
		Property<?> prop = blockState.getBlock().getStateDefinition().getProperty("facing");
		if (prop instanceof DirectionProperty dp)
			return blockState.getValue(dp);
		prop = blockState.getBlock().getStateDefinition().getProperty("axis");
		return prop instanceof EnumProperty ep && ep.getPossibleValues().toArray()[0] instanceof Direction.Axis ? Direction.fromAxisAndDirection((Direction.Axis) blockState.getValue(ep), Direction.AxisDirection.POSITIVE) : Direction.NORTH;
	}
}