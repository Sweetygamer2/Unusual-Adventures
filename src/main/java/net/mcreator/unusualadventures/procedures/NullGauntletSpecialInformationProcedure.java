package net.mcreator.unusualadventures.procedures;

import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;
import net.minecraft.core.component.DataComponents;
import net.minecraft.client.gui.screens.Screen;

public class NullGauntletSpecialInformationProcedure {
	public static String execute(ItemStack itemstack) {
		if ((itemstack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getString("state")).equals("dash")) {
			return Component.translatable("item.unusual_adventures.dash_module.description_1").getString();
		} else if ((itemstack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getString("state")).equals("heal")) {
			return Component.translatable("item.unusual_adventures.heal_module.description_1").getString();
		} else if ((itemstack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getString("state")).equals("kb")) {
			return Component.translatable("item.unusual_adventures.kb_module.description_1").getString();
		} else if ((itemstack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getString("state")).equals("laser")) {
			return Component.translatable("item.unusual_adventures.laser_module.description_1").getString();
		} else if (Screen.hasControlDown()) {
			return Component.translatable("item.unusual_adventures.no_module").getString() + "," + Component.translatable("item.unusual_adventures.use_workbench").getString();
		}
		return Component.translatable("item.unusual_adventures.no_module").getString() + "," + Component.translatable("block.unusual_adventures.short_desc").getString();
	}
}