package net.mcreator.unusualadventures.item;

import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BannerPatternItem;
import net.minecraft.tags.TagKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;

import net.mcreator.unusualadventures.UnusualAdventuresMod;

public class MatrixVillagerBannerPatternItem extends BannerPatternItem {
	public static final TagKey<BannerPattern> PROVIDED_PATTERNS = TagKey.create(Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(UnusualAdventuresMod.MODID, "pattern_item/matrix_villager_banner_pattern"));

	public MatrixVillagerBannerPatternItem() {
		super(PROVIDED_PATTERNS, new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON));
	}

	public MutableComponent getDisplayName() {
		return Component.translatable(this.getDescriptionId() + ".patterns");
	}
}