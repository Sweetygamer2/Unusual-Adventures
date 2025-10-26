/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.unusualadventures.init;

import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.mcreator.unusualadventures.client.renderer.GooeyNullRenderer;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class UnusualAdventuresModEntityRenderers {
	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(UnusualAdventuresModEntities.GOOEY_NULL.get(), GooeyNullRenderer::new);
	}
}