/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.unusualadventures.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.items.wrapper.SidedInvWrapper;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.core.registries.BuiltInRegistries;

import net.mcreator.unusualadventures.block.entity.RustyDrawerBlockEntity;
import net.mcreator.unusualadventures.block.entity.NullWorkbenchBlockEntity;
import net.mcreator.unusualadventures.block.entity.ComputerBlockEntity;
import net.mcreator.unusualadventures.block.entity.BrokenComputerBlockEntity;
import net.mcreator.unusualadventures.UnusualAdventuresMod;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class UnusualAdventuresModBlockEntities {
	public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, UnusualAdventuresMod.MODID);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ComputerBlockEntity>> COMPUTER = register("computer", UnusualAdventuresModBlocks.COMPUTER, ComputerBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<BrokenComputerBlockEntity>> BROKEN_COMPUTER = register("broken_computer", UnusualAdventuresModBlocks.BROKEN_COMPUTER, BrokenComputerBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<RustyDrawerBlockEntity>> RUSTY_DRAWER = register("rusty_drawer", UnusualAdventuresModBlocks.RUSTY_DRAWER, RustyDrawerBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<NullWorkbenchBlockEntity>> NULL_WORKBENCH = register("null_workbench", UnusualAdventuresModBlocks.NULL_WORKBENCH, NullWorkbenchBlockEntity::new);

	// Start of user code block custom block entities
	// End of user code block custom block entities
	private static <T extends BlockEntity> DeferredHolder<BlockEntityType<?>, BlockEntityType<T>> register(String registryname, DeferredHolder<Block, Block> block, BlockEntityType.BlockEntitySupplier<T> supplier) {
		return REGISTRY.register(registryname, () -> BlockEntityType.Builder.of(supplier, block.get()).build(null));
	}

	@SubscribeEvent
	public static void registerCapabilities(RegisterCapabilitiesEvent event) {
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, COMPUTER.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, BROKEN_COMPUTER.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, RUSTY_DRAWER.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, NULL_WORKBENCH.get(), SidedInvWrapper::new);
	}
}