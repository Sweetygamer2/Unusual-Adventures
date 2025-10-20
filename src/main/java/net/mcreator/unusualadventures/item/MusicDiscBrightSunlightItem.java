package net.mcreator.unusualadventures.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;

import net.mcreator.unusualadventures.UnusualAdventuresMod;

public class MusicDiscBrightSunlightItem extends Item {
	public MusicDiscBrightSunlightItem() {
		super(new Item.Properties().stacksTo(1).rarity(Rarity.RARE).jukeboxPlayable(ResourceKey.create(Registries.JUKEBOX_SONG, ResourceLocation.fromNamespaceAndPath(UnusualAdventuresMod.MODID, "music_disc_bright_sunlight"))));
	}
}