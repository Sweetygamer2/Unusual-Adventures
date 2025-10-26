/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.unusualadventures.init;

import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.mcreator.unusualadventures.client.model.Modelgooey_null;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class UnusualAdventuresModModels {
	@SubscribeEvent
	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(Modelgooey_null.LAYER_LOCATION, Modelgooey_null::createBodyLayer);
	}
}