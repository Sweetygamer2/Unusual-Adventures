/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.unusualadventures.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;

import net.mcreator.unusualadventures.item.WallpaperModuleItem;
import net.mcreator.unusualadventures.item.RedstoneModuleItem;
import net.mcreator.unusualadventures.item.NullGauntletItem;
import net.mcreator.unusualadventures.item.MusicModuleItem;
import net.mcreator.unusualadventures.item.MusicDiscBrightSunlightItem;
import net.mcreator.unusualadventures.item.MatrixVillagerBannerPatternItem;
import net.mcreator.unusualadventures.item.LaserModuleItem;
import net.mcreator.unusualadventures.item.LaboratoryPlansItem;
import net.mcreator.unusualadventures.item.KbModuleItem;
import net.mcreator.unusualadventures.item.HealModuleItem;
import net.mcreator.unusualadventures.item.ExperimentalModuleItem;
import net.mcreator.unusualadventures.item.DashModuleItem;
import net.mcreator.unusualadventures.item.AnalyseModuleItem;
import net.mcreator.unusualadventures.UnusualAdventuresMod;

public class UnusualAdventuresModItems {
	public static final DeferredRegister.Items REGISTRY = DeferredRegister.createItems(UnusualAdventuresMod.MODID);
	public static final DeferredItem<Item> NULL_BITS = block(UnusualAdventuresModBlocks.NULL_BITS);
	public static final DeferredItem<Item> NULL_BLOCK = block(UnusualAdventuresModBlocks.NULL_BLOCK);
	public static final DeferredItem<Item> COMPUTER = block(UnusualAdventuresModBlocks.COMPUTER, new Item.Properties().rarity(Rarity.EPIC));
	public static final DeferredItem<Item> BROKEN_COMPUTER = block(UnusualAdventuresModBlocks.BROKEN_COMPUTER);
	public static final DeferredItem<Item> NULL_PLATING = block(UnusualAdventuresModBlocks.NULL_PLATING);
	public static final DeferredItem<Item> CHISELED_NULL_PLATING = block(UnusualAdventuresModBlocks.CHISELED_NULL_PLATING);
	public static final DeferredItem<Item> TILED_NULL_PLATING = block(UnusualAdventuresModBlocks.TILED_NULL_PLATING);
	public static final DeferredItem<Item> CHARGED_NULL_PLATING = block(UnusualAdventuresModBlocks.CHARGED_NULL_PLATING);
	public static final DeferredItem<Item> POWER = block(UnusualAdventuresModBlocks.POWER, new Item.Properties().rarity(Rarity.UNCOMMON));
	public static final DeferredItem<Item> NULL_EMITTER = block(UnusualAdventuresModBlocks.NULL_EMITTER, new Item.Properties().rarity(Rarity.UNCOMMON));
	public static final DeferredItem<Item> MUSIC_DISC_BRIGHT_SUNLIGHT = REGISTRY.register("music_disc_bright_sunlight", MusicDiscBrightSunlightItem::new);
	public static final DeferredItem<Item> ANALYSE_MODULE = REGISTRY.register("analyse_module", AnalyseModuleItem::new);
	public static final DeferredItem<Item> REDSTONE_MODULE = REGISTRY.register("redstone_module", RedstoneModuleItem::new);
	public static final DeferredItem<Item> EXPERIMENTAL_MODULE = REGISTRY.register("experimental_module", ExperimentalModuleItem::new);
	public static final DeferredItem<Item> LAB_CONCRETE = block(UnusualAdventuresModBlocks.LAB_CONCRETE);
	public static final DeferredItem<Item> MOSSY_LAB_CONCRETE = block(UnusualAdventuresModBlocks.MOSSY_LAB_CONCRETE);
	public static final DeferredItem<Item> LAB_TILES = block(UnusualAdventuresModBlocks.LAB_TILES);
	public static final DeferredItem<Item> BROKEN_LAB_TILES = block(UnusualAdventuresModBlocks.BROKEN_LAB_TILES);
	public static final DeferredItem<Item> BROKEN_LIGHT = block(UnusualAdventuresModBlocks.BROKEN_LIGHT);
	public static final DeferredItem<Item> RUSTY_DRAWER = block(UnusualAdventuresModBlocks.RUSTY_DRAWER);
	public static final DeferredItem<Item> LAB_CONCRETE_SLAB = block(UnusualAdventuresModBlocks.LAB_CONCRETE_SLAB);
	public static final DeferredItem<Item> LAB_CONCRETE_STAIRS = block(UnusualAdventuresModBlocks.LAB_CONCRETE_STAIRS);
	public static final DeferredItem<Item> SMALL_LAB_TILES = block(UnusualAdventuresModBlocks.SMALL_LAB_TILES);
	public static final DeferredItem<Item> SMALL_LAB_TILES_SLAB = block(UnusualAdventuresModBlocks.SMALL_LAB_TILES_SLAB);
	public static final DeferredItem<Item> SMALL_LAB_TILES_STAIRS = block(UnusualAdventuresModBlocks.SMALL_LAB_TILES_STAIRS);
	public static final DeferredItem<Item> LEFTOVER_PAPER = block(UnusualAdventuresModBlocks.LEFTOVER_PAPER);
	public static final DeferredItem<Item> CHISELED_LAB_TILES = block(UnusualAdventuresModBlocks.CHISELED_LAB_TILES);
	public static final DeferredItem<Item> MUSIC_MODULE = REGISTRY.register("music_module", MusicModuleItem::new);
	public static final DeferredItem<Item> LIVING_CODE_BLOCK = block(UnusualAdventuresModBlocks.LIVING_CODE_BLOCK, new Item.Properties().rarity(Rarity.UNCOMMON));
	public static final DeferredItem<Item> CHARGED_NULL_BLOCK = block(UnusualAdventuresModBlocks.CHARGED_NULL_BLOCK);
	public static final DeferredItem<Item> WALLPAPER_MODULE = REGISTRY.register("wallpaper_module", WallpaperModuleItem::new);
	public static final DeferredItem<Item> DIMMED_CHARGED_NULL_BLOCK = block(UnusualAdventuresModBlocks.DIMMED_CHARGED_NULL_BLOCK);
	public static final DeferredItem<Item> NULL_EXTENSION = block(UnusualAdventuresModBlocks.NULL_EXTENSION);
	public static final DeferredItem<Item> MATRIX_VILLAGER_BANNER_PATTERN = REGISTRY.register("matrix_villager_banner_pattern", MatrixVillagerBannerPatternItem::new);
	public static final DeferredItem<Item> OFFICE_LAMP = block(UnusualAdventuresModBlocks.OFFICE_LAMP);
	public static final DeferredItem<Item> CORRUPTED_LAB_CONCRETE = block(UnusualAdventuresModBlocks.CORRUPTED_LAB_CONCRETE);
	public static final DeferredItem<Item> NULL_GAUNTLET = REGISTRY.register("null_gauntlet", NullGauntletItem::new);
	public static final DeferredItem<Item> DASH_MODULE = REGISTRY.register("dash_module", DashModuleItem::new);
	public static final DeferredItem<Item> HEAL_MODULE = REGISTRY.register("heal_module", HealModuleItem::new);
	public static final DeferredItem<Item> NULL_WORKBENCH = block(UnusualAdventuresModBlocks.NULL_WORKBENCH, new Item.Properties().rarity(Rarity.UNCOMMON));
	public static final DeferredItem<Item> KB_MODULE = REGISTRY.register("kb_module", KbModuleItem::new);
	public static final DeferredItem<Item> LASER_MODULE = REGISTRY.register("laser_module", LaserModuleItem::new);
	public static final DeferredItem<Item> LABORATORY_PLANS = REGISTRY.register("laboratory_plans", LaboratoryPlansItem::new);

	// Start of user code block custom items
	// End of user code block custom items
	private static DeferredItem<Item> block(DeferredHolder<Block, Block> block) {
		return block(block, new Item.Properties());
	}

	private static DeferredItem<Item> block(DeferredHolder<Block, Block> block, Item.Properties properties) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), properties));
	}
}