/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.unusualadventures.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;

import net.mcreator.unusualadventures.UnusualAdventuresMod;

public class UnusualAdventuresModTabs {
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, UnusualAdventuresMod.MODID);
	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> UNUSUAL_ADVENTURES = REGISTRY.register("unusual_adventures",
			() -> CreativeModeTab.builder().title(Component.translatable("item_group.unusual_adventures.unusual_adventures")).icon(() -> new ItemStack(UnusualAdventuresModBlocks.BROKEN_COMPUTER.get())).displayItems((parameters, tabData) -> {
				tabData.accept(UnusualAdventuresModBlocks.COMPUTER.get().asItem());
				tabData.accept(UnusualAdventuresModBlocks.BROKEN_COMPUTER.get().asItem());
				tabData.accept(UnusualAdventuresModItems.ANALYSE_MODULE.get());
				tabData.accept(UnusualAdventuresModItems.REDSTONE_MODULE.get());
				tabData.accept(UnusualAdventuresModItems.MUSIC_MODULE.get());
				tabData.accept(UnusualAdventuresModItems.WALLPAPER_MODULE.get());
				tabData.accept(UnusualAdventuresModItems.EXPERIMENTAL_MODULE.get());
				tabData.accept(UnusualAdventuresModBlocks.RUSTY_DRAWER.get().asItem());
				tabData.accept(UnusualAdventuresModBlocks.OFFICE_LAMP.get().asItem());
				tabData.accept(UnusualAdventuresModItems.NULL_GAUNTLET.get());
				tabData.accept(UnusualAdventuresModItems.DASH_MODULE.get());
				tabData.accept(UnusualAdventuresModItems.HEAL_MODULE.get());
				tabData.accept(UnusualAdventuresModItems.KB_MODULE.get());
				tabData.accept(UnusualAdventuresModItems.LASER_MODULE.get());
				tabData.accept(UnusualAdventuresModBlocks.NULL_WORKBENCH.get().asItem());
				tabData.accept(UnusualAdventuresModBlocks.CHARGED_NULL_PLATING.get().asItem());
				tabData.accept(UnusualAdventuresModBlocks.NULL_EMITTER.get().asItem());
				tabData.accept(UnusualAdventuresModBlocks.POWER.get().asItem());
				tabData.accept(UnusualAdventuresModBlocks.NULL_BITS.get().asItem());
				tabData.accept(UnusualAdventuresModBlocks.NULL_EXTENSION.get().asItem());
				tabData.accept(UnusualAdventuresModBlocks.NULL_BLOCK.get().asItem());
				tabData.accept(UnusualAdventuresModBlocks.DIMMED_CHARGED_NULL_BLOCK.get().asItem());
				tabData.accept(UnusualAdventuresModBlocks.CHARGED_NULL_BLOCK.get().asItem());
				tabData.accept(UnusualAdventuresModBlocks.LIVING_CODE_BLOCK.get().asItem());
				tabData.accept(UnusualAdventuresModBlocks.NULL_PLATING.get().asItem());
				tabData.accept(UnusualAdventuresModBlocks.CHISELED_NULL_PLATING.get().asItem());
				tabData.accept(UnusualAdventuresModBlocks.TILED_NULL_PLATING.get().asItem());
				tabData.accept(UnusualAdventuresModBlocks.CORRUPTED_LAB_CONCRETE.get().asItem());
				tabData.accept(UnusualAdventuresModBlocks.MOSSY_LAB_CONCRETE.get().asItem());
				tabData.accept(UnusualAdventuresModBlocks.LAB_CONCRETE.get().asItem());
				tabData.accept(UnusualAdventuresModBlocks.LAB_CONCRETE_STAIRS.get().asItem());
				tabData.accept(UnusualAdventuresModBlocks.LAB_CONCRETE_SLAB.get().asItem());
				tabData.accept(UnusualAdventuresModBlocks.LAB_TILES.get().asItem());
				tabData.accept(UnusualAdventuresModBlocks.BROKEN_LAB_TILES.get().asItem());
				tabData.accept(UnusualAdventuresModBlocks.CHISELED_LAB_TILES.get().asItem());
				tabData.accept(UnusualAdventuresModBlocks.SMALL_LAB_TILES.get().asItem());
				tabData.accept(UnusualAdventuresModBlocks.SMALL_LAB_TILES_STAIRS.get().asItem());
				tabData.accept(UnusualAdventuresModBlocks.SMALL_LAB_TILES_SLAB.get().asItem());
				tabData.accept(UnusualAdventuresModBlocks.BROKEN_LIGHT.get().asItem());
				tabData.accept(UnusualAdventuresModBlocks.LEFTOVER_PAPER.get().asItem());
				tabData.accept(UnusualAdventuresModItems.MATRIX_VILLAGER_BANNER_PATTERN.get());
				tabData.accept(UnusualAdventuresModItems.LABORATORY_PLANS.get());
				tabData.accept(UnusualAdventuresModItems.MUSIC_DISC_BRIGHT_SUNLIGHT.get());
			}).build());
}