package net.mcreator.unusualadventures.procedures;

import net.neoforged.neoforge.items.IItemHandlerModifiable;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.common.extensions.ILevelExtension;
import net.neoforged.neoforge.capabilities.Capabilities;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

import net.mcreator.unusualadventures.init.UnusualAdventuresModItems;
import net.mcreator.unusualadventures.init.UnusualAdventuresModBlocks;

public class NullWorkbenchOnTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		ItemStack gauntlet = ItemStack.EMPTY;
		gauntlet = (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 1).copy()).copy();
		if (gauntlet.getItem() == UnusualAdventuresModItems.NULL_GAUNTLET.get() && (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy()).getItem() == UnusualAdventuresModBlocks.POWER.get().asItem()) {
			if (gauntlet.isDamaged()) {
				gauntlet.setDamageValue((int) Math.max(gauntlet.getDamageValue() - 1000, 0));
				if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
					ItemStack _setstack = gauntlet.copy();
					_setstack.setCount(1);
					_itemHandlerModifiable.setStackInSlot(1, _setstack);
				}
				if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
					int _slotid = 0;
					ItemStack _stk = _itemHandlerModifiable.getStackInSlot(_slotid).copy();
					_stk.shrink(1);
					_itemHandlerModifiable.setStackInSlot(_slotid, _stk);
				}
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.fire.ambient")), SoundSource.BLOCKS, (float) 0.5, 2);
					} else {
						_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.fire.ambient")), SoundSource.BLOCKS, (float) 0.5, 2, false);
					}
				}
			}
		}
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