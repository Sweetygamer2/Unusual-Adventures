package net.mcreator.unusualadventures.procedures;

import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.common.extensions.ILevelExtension;
import net.neoforged.neoforge.capabilities.Capabilities;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.BlockPos;

import net.mcreator.unusualadventures.init.UnusualAdventuresModItems;

public class HelpCommandProcedure {
	public static String execute(LevelAccessor world, double x, double y, double z) {
		double slot = 0;
		String time = "";
		String scan = "";
		String power = "";
		String music = "";
		String clock = "";
		String wallpaper = "";
		String tp = "";
		for (int index0 = 0; index0 < 4; index0++) {
			if ((itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).copy()).getItem() == UnusualAdventuresModItems.MUSIC_MODULE.get()) {
				music = "MUSIC (loop) : play <1-" + world.registryAccess().registryOrThrow(Registries.JUKEBOX_SONG).size() + ">" + System.lineSeparator();
			}
			if ((itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).copy()).getItem() == UnusualAdventuresModItems.ANALYSE_MODULE.get()) {
				time = "TIME : time <hour/day>" + System.lineSeparator();
				scan = "SCAN : scan <monster/player/both> <12-64>" + System.lineSeparator();
			}
			if ((itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).copy()).getItem() == UnusualAdventuresModItems.REDSTONE_MODULE.get()) {
				power = "REDSTONE : power <1-15>" + System.lineSeparator();
				clock = "REDSTONE CLOCK : clock <2-60>" + System.lineSeparator();
			}
			if ((itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).copy()).getItem() == UnusualAdventuresModItems.WALLPAPER_MODULE.get()) {
				wallpaper = "WALLPAPER : wallpaper" + System.lineSeparator();
			}
			if ((itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).copy()).getItem() == UnusualAdventuresModItems.EXPERIMENTAL_MODULE.get()) {
				tp = "NULLSPACE : rift <open>" + System.lineSeparator();
			}
			slot = slot + 1;
		}
		return "MESSAGE : print <message>" + System.lineSeparator() + music + time + scan + power + clock + wallpaper + tp + "---" + System.lineSeparator() + "INSTALL PROGRAMS : disk" + System.lineSeparator() + "HELP : help <command>"
				+ System.lineSeparator() + "STOP : shutdown" + System.lineSeparator();
	}

	private static ItemStack itemFromBlockInventory(LevelAccessor world, BlockPos pos, int slot) {
		if (world instanceof ILevelExtension ext) {
			IItemHandler itemHandler = ext.getCapability(Capabilities.ItemHandler.BLOCK, pos, null);
			if (itemHandler != null)
				return itemHandler.getStackInSlot(slot);
		}
		return ItemStack.EMPTY;
	}
}