package net.mcreator.unusualadventures.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.unusualadventures.init.UnusualAdventuresModMenus;
import net.mcreator.unusualadventures.init.UnusualAdventuresModItems;
import net.mcreator.unusualadventures.init.UnusualAdventuresModBlocks;

public class ReturnCanCombineChipProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof UnusualAdventuresModMenus.MenuAccessor _menu0 ? _menu0.getSlots().get(1).getItem() : ItemStack.EMPTY).getItem() == UnusualAdventuresModItems.NULL_GAUNTLET
				.get()
				&& (entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof UnusualAdventuresModMenus.MenuAccessor _menu2 ? _menu2.getSlots().get(2).getItem() : ItemStack.EMPTY)
						.is(ItemTags.create(ResourceLocation.parse("unusual_adventures:chip")))
				&& (entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof UnusualAdventuresModMenus.MenuAccessor _menu4 ? _menu4.getSlots().get(3).getItem() : ItemStack.EMPTY)
						.getItem() == UnusualAdventuresModBlocks.LIVING_CODE_BLOCK.get().asItem()) {
			return true;
		}
		return false;
	}
}