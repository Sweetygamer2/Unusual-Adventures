/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.unusualadventures.init;

import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.mcreator.unusualadventures.client.gui.NullWorkbenchGUIScreen;
import net.mcreator.unusualadventures.client.gui.DrawerStorageScreen;
import net.mcreator.unusualadventures.client.gui.ComputerStorageScreen;
import net.mcreator.unusualadventures.client.gui.ComputerMainScreenScreen;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class UnusualAdventuresModScreens {
	@SubscribeEvent
	public static void clientLoad(RegisterMenuScreensEvent event) {
		event.register(UnusualAdventuresModMenus.COMPUTER_MAIN_SCREEN.get(), ComputerMainScreenScreen::new);
		event.register(UnusualAdventuresModMenus.COMPUTER_STORAGE.get(), ComputerStorageScreen::new);
		event.register(UnusualAdventuresModMenus.DRAWER_STORAGE.get(), DrawerStorageScreen::new);
		event.register(UnusualAdventuresModMenus.NULL_WORKBENCH_GUI.get(), NullWorkbenchGUIScreen::new);
	}

	public interface ScreenAccessor {
		void updateMenuState(int elementType, String name, Object elementState);
	}
}