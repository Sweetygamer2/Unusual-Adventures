/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.unusualadventures.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;

import net.mcreator.unusualadventures.UnusualAdventuresMod;

public class UnusualAdventuresModSounds {
	public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(Registries.SOUND_EVENT, UnusualAdventuresMod.MODID);
	public static final DeferredHolder<SoundEvent, SoundEvent> MUSIC_DISC_BRIGHT_SUNLIGHT = REGISTRY.register("music_disc_bright_sunlight",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("unusual_adventures", "music_disc_bright_sunlight")));
	public static final DeferredHolder<SoundEvent, SoundEvent> MUSIC_NULLSPACE = REGISTRY.register("music.nullspace", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("unusual_adventures", "music.nullspace")));
}