package net.mcreator.unusualadventures.procedures;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.BlockPos;

import net.mcreator.unusualadventures.init.UnusualAdventuresModParticleTypes;
import net.mcreator.unusualadventures.init.UnusualAdventuresModBlocks;

public class ChargedNullBlockOnTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		if (world instanceof ServerLevel _level)
			_level.sendParticles((SimpleParticleType) (UnusualAdventuresModParticleTypes.BINARY.get()), (x + 0.5), (y + 1), (z + 0.5), Mth.nextInt(RandomSource.create(), 1, 2), 0.6, 0.3, 0.6, 0);
		if ((blockstate.getBlock().getStateDefinition().getProperty("persistent") instanceof BooleanProperty _getbp3 && blockstate.getValue(_getbp3)) == false) {
			if (blockstate.getBlock() == UnusualAdventuresModBlocks.CHARGED_NULL_BLOCK.get()) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = (UnusualAdventuresModBlocks.DIMMED_CHARGED_NULL_BLOCK.get().getStateDefinition().getProperty("persistent") instanceof BooleanProperty _withbp6
							? UnusualAdventuresModBlocks.DIMMED_CHARGED_NULL_BLOCK.get().defaultBlockState().setValue(_withbp6, false)
							: UnusualAdventuresModBlocks.DIMMED_CHARGED_NULL_BLOCK.get().defaultBlockState());
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
			} else if (blockstate.getBlock() == UnusualAdventuresModBlocks.DIMMED_CHARGED_NULL_BLOCK.get()) {
				world.levelEvent(2001, BlockPos.containing(x, y, z), Block.getId(UnusualAdventuresModBlocks.DIMMED_CHARGED_NULL_BLOCK.get().defaultBlockState()));
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = UnusualAdventuresModBlocks.NULL_BLOCK.get().defaultBlockState();
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
			}
		}
	}
}