/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.unusualadventures.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredBlock;

import net.minecraft.world.level.block.Block;

import net.mcreator.unusualadventures.block.TiledNullPlatingBlock;
import net.mcreator.unusualadventures.block.SmallLabTilesStairsBlock;
import net.mcreator.unusualadventures.block.SmallLabTilesSlabBlock;
import net.mcreator.unusualadventures.block.SmallLabTilesBlock;
import net.mcreator.unusualadventures.block.RustyDrawerBlock;
import net.mcreator.unusualadventures.block.PoweredNullBlockBlock;
import net.mcreator.unusualadventures.block.PowerBlock;
import net.mcreator.unusualadventures.block.OfficeLampBlock;
import net.mcreator.unusualadventures.block.NullspacePortalBlock;
import net.mcreator.unusualadventures.block.NullWorkbenchBlock;
import net.mcreator.unusualadventures.block.NullPlatingBlock;
import net.mcreator.unusualadventures.block.NullExtensionBlock;
import net.mcreator.unusualadventures.block.NullEmitterBlock;
import net.mcreator.unusualadventures.block.NullBlockBlock;
import net.mcreator.unusualadventures.block.NullBitsBlock;
import net.mcreator.unusualadventures.block.MossyLabConcreteBlock;
import net.mcreator.unusualadventures.block.LeftoverPaperBlock;
import net.mcreator.unusualadventures.block.LabTilesBlock;
import net.mcreator.unusualadventures.block.LabConcreteStairsBlock;
import net.mcreator.unusualadventures.block.LabConcreteSlabBlock;
import net.mcreator.unusualadventures.block.LabConcreteBlock;
import net.mcreator.unusualadventures.block.DimmedChargedNullBlockBlock;
import net.mcreator.unusualadventures.block.CorruptedLabConcreteBlock;
import net.mcreator.unusualadventures.block.ComputerBlock;
import net.mcreator.unusualadventures.block.ChiseledNullPlatingBlock;
import net.mcreator.unusualadventures.block.ChiseledLabTilesBlock;
import net.mcreator.unusualadventures.block.ChargedNullPlatingBlock;
import net.mcreator.unusualadventures.block.ChargedNullBlockBlock;
import net.mcreator.unusualadventures.block.BrokenLightBlock;
import net.mcreator.unusualadventures.block.BrokenLabTilesBlock;
import net.mcreator.unusualadventures.block.BrokenComputerBlock;
import net.mcreator.unusualadventures.UnusualAdventuresMod;

public class UnusualAdventuresModBlocks {
	public static final DeferredRegister.Blocks REGISTRY = DeferredRegister.createBlocks(UnusualAdventuresMod.MODID);
	public static final DeferredBlock<Block> NULLSPACE_PORTAL = REGISTRY.register("nullspace_portal", NullspacePortalBlock::new);
	public static final DeferredBlock<Block> NULL_BITS = REGISTRY.register("null_bits", NullBitsBlock::new);
	public static final DeferredBlock<Block> NULL_BLOCK = REGISTRY.register("null_block", NullBlockBlock::new);
	public static final DeferredBlock<Block> COMPUTER = REGISTRY.register("computer", ComputerBlock::new);
	public static final DeferredBlock<Block> BROKEN_COMPUTER = REGISTRY.register("broken_computer", BrokenComputerBlock::new);
	public static final DeferredBlock<Block> NULL_PLATING = REGISTRY.register("null_plating", NullPlatingBlock::new);
	public static final DeferredBlock<Block> CHISELED_NULL_PLATING = REGISTRY.register("chiseled_null_plating", ChiseledNullPlatingBlock::new);
	public static final DeferredBlock<Block> TILED_NULL_PLATING = REGISTRY.register("tiled_null_plating", TiledNullPlatingBlock::new);
	public static final DeferredBlock<Block> CHARGED_NULL_PLATING = REGISTRY.register("charged_null_plating", ChargedNullPlatingBlock::new);
	public static final DeferredBlock<Block> POWER = REGISTRY.register("power", PowerBlock::new);
	public static final DeferredBlock<Block> NULL_EMITTER = REGISTRY.register("null_emitter", NullEmitterBlock::new);
	public static final DeferredBlock<Block> LAB_CONCRETE = REGISTRY.register("lab_concrete", LabConcreteBlock::new);
	public static final DeferredBlock<Block> MOSSY_LAB_CONCRETE = REGISTRY.register("mossy_lab_concrete", MossyLabConcreteBlock::new);
	public static final DeferredBlock<Block> LAB_TILES = REGISTRY.register("lab_tiles", LabTilesBlock::new);
	public static final DeferredBlock<Block> BROKEN_LAB_TILES = REGISTRY.register("broken_lab_tiles", BrokenLabTilesBlock::new);
	public static final DeferredBlock<Block> BROKEN_LIGHT = REGISTRY.register("broken_light", BrokenLightBlock::new);
	public static final DeferredBlock<Block> RUSTY_DRAWER = REGISTRY.register("rusty_drawer", RustyDrawerBlock::new);
	public static final DeferredBlock<Block> LAB_CONCRETE_SLAB = REGISTRY.register("lab_concrete_slab", LabConcreteSlabBlock::new);
	public static final DeferredBlock<Block> LAB_CONCRETE_STAIRS = REGISTRY.register("lab_concrete_stairs", LabConcreteStairsBlock::new);
	public static final DeferredBlock<Block> SMALL_LAB_TILES = REGISTRY.register("small_lab_tiles", SmallLabTilesBlock::new);
	public static final DeferredBlock<Block> SMALL_LAB_TILES_SLAB = REGISTRY.register("small_lab_tiles_slab", SmallLabTilesSlabBlock::new);
	public static final DeferredBlock<Block> SMALL_LAB_TILES_STAIRS = REGISTRY.register("small_lab_tiles_stairs", SmallLabTilesStairsBlock::new);
	public static final DeferredBlock<Block> LEFTOVER_PAPER = REGISTRY.register("leftover_paper", LeftoverPaperBlock::new);
	public static final DeferredBlock<Block> CHISELED_LAB_TILES = REGISTRY.register("chiseled_lab_tiles", ChiseledLabTilesBlock::new);
	public static final DeferredBlock<Block> LIVING_CODE_BLOCK = REGISTRY.register("living_code_block", PoweredNullBlockBlock::new);
	public static final DeferredBlock<Block> CHARGED_NULL_BLOCK = REGISTRY.register("charged_null_block", ChargedNullBlockBlock::new);
	public static final DeferredBlock<Block> DIMMED_CHARGED_NULL_BLOCK = REGISTRY.register("dimmed_charged_null_block", DimmedChargedNullBlockBlock::new);
	public static final DeferredBlock<Block> NULL_EXTENSION = REGISTRY.register("null_extension", NullExtensionBlock::new);
	public static final DeferredBlock<Block> OFFICE_LAMP = REGISTRY.register("office_lamp", OfficeLampBlock::new);
	public static final DeferredBlock<Block> CORRUPTED_LAB_CONCRETE = REGISTRY.register("corrupted_lab_concrete", CorruptedLabConcreteBlock::new);
	public static final DeferredBlock<Block> NULL_WORKBENCH = REGISTRY.register("null_workbench", NullWorkbenchBlock::new);
	// Start of user code block custom blocks
	// End of user code block custom blocks
}