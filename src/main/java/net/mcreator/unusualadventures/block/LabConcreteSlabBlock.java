package net.mcreator.unusualadventures.block;

import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.SlabBlock;

public class LabConcreteSlabBlock extends SlabBlock {
	public LabConcreteSlabBlock() {
		super(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).sound(SoundType.POLISHED_TUFF).strength(2f, 10f).requiresCorrectToolForDrops());
	}
}