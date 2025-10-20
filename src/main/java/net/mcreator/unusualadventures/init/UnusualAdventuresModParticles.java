/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.unusualadventures.init;

import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.mcreator.unusualadventures.client.particle.BinaryParticle;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class UnusualAdventuresModParticles {
	@SubscribeEvent
	public static void registerParticles(RegisterParticleProvidersEvent event) {
		event.registerSpriteSet(UnusualAdventuresModParticleTypes.BINARY.get(), BinaryParticle::provider);
	}
}