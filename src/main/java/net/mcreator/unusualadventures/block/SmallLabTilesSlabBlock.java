package net.mcreator.unusualadventures.block;

import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SlabBlock;

public class SmallLabTilesSlabBlock extends SlabBlock {
	public SmallLabTilesSlabBlock() {
		super(BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).strength(2f, 10f).requiresCorrectToolForDrops());
	}
}