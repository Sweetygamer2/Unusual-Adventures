package net.mcreator.unusualadventures.procedures;

import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class ComputerEmittedRedstonePowerProcedure {
	public static double execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		if ((blockstate.getBlock().getStateDefinition().getProperty("state") instanceof EnumProperty _getep1 ? blockstate.getValue(_getep1).toString() : "").equals("WARNING")) {
			return 15;
		} else if ((blockstate.getBlock().getStateDefinition().getProperty("state") instanceof EnumProperty _getep3 ? blockstate.getValue(_getep3).toString() : "").equals("CODING")) {
			if ((getBlockNBTString(world, BlockPos.containing(x, y, z), "CodingState")).equals("power")) {
				return getBlockNBTNumber(world, BlockPos.containing(x, y, z), "power");
			} else if ((getBlockNBTString(world, BlockPos.containing(x, y, z), "CodingState")).equals("clock")) {
				if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "clock") == 0) {
					return 15;
				}
			}
		}
		return 0;
	}

	private static String getBlockNBTString(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getString(tag);
		return "";
	}

	private static double getBlockNBTNumber(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getDouble(tag);
		return -1;
	}
}