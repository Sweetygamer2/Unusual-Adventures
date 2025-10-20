/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.unusualadventures.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.core.registries.Registries;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.particles.ParticleType;

import net.mcreator.unusualadventures.UnusualAdventuresMod;

public class UnusualAdventuresModParticleTypes {
	public static final DeferredRegister<ParticleType<?>> REGISTRY = DeferredRegister.create(Registries.PARTICLE_TYPE, UnusualAdventuresMod.MODID);
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> BINARY = REGISTRY.register("binary", () -> new SimpleParticleType(false));
}