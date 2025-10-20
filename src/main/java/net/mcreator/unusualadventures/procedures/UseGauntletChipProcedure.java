package net.mcreator.unusualadventures.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.tags.ItemTags;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.BlockPos;

import net.mcreator.unusualadventures.init.UnusualAdventuresModMenus;
import net.mcreator.unusualadventures.init.UnusualAdventuresModItems;
import net.mcreator.unusualadventures.init.UnusualAdventuresModBlocks;

public class UseGauntletChipProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof UnusualAdventuresModMenus.MenuAccessor _menu0 ? _menu0.getSlots().get(1).getItem() : ItemStack.EMPTY).getItem() == UnusualAdventuresModItems.NULL_GAUNTLET
				.get()
				&& (entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof UnusualAdventuresModMenus.MenuAccessor _menu2 ? _menu2.getSlots().get(2).getItem() : ItemStack.EMPTY)
						.is(ItemTags.create(ResourceLocation.parse("unusual_adventures:chip")))
				&& (entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof UnusualAdventuresModMenus.MenuAccessor _menu4 ? _menu4.getSlots().get(3).getItem() : ItemStack.EMPTY)
						.getItem() == UnusualAdventuresModBlocks.LIVING_CODE_BLOCK.get().asItem()) {
			if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof UnusualAdventuresModMenus.MenuAccessor _menu6 ? _menu6.getSlots().get(2).getItem() : ItemStack.EMPTY).getItem() == UnusualAdventuresModItems.DASH_MODULE
					.get()) {
				{
					final String _tagName = "state";
					final String _tagValue = "dash";
					CustomData.update(DataComponents.CUSTOM_DATA, (entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof UnusualAdventuresModMenus.MenuAccessor _menu8 ? _menu8.getSlots().get(1).getItem() : ItemStack.EMPTY),
							tag -> tag.putString(_tagName, _tagValue));
				}
			} else if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof UnusualAdventuresModMenus.MenuAccessor _menu10 ? _menu10.getSlots().get(2).getItem() : ItemStack.EMPTY)
					.getItem() == UnusualAdventuresModItems.HEAL_MODULE.get()) {
				{
					final String _tagName = "state";
					final String _tagValue = "heal";
					CustomData.update(DataComponents.CUSTOM_DATA, (entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof UnusualAdventuresModMenus.MenuAccessor _menu12 ? _menu12.getSlots().get(1).getItem() : ItemStack.EMPTY),
							tag -> tag.putString(_tagName, _tagValue));
				}
			} else if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof UnusualAdventuresModMenus.MenuAccessor _menu14 ? _menu14.getSlots().get(2).getItem() : ItemStack.EMPTY)
					.getItem() == UnusualAdventuresModItems.KB_MODULE.get()) {
				{
					final String _tagName = "state";
					final String _tagValue = "kb";
					CustomData.update(DataComponents.CUSTOM_DATA, (entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof UnusualAdventuresModMenus.MenuAccessor _menu16 ? _menu16.getSlots().get(1).getItem() : ItemStack.EMPTY),
							tag -> tag.putString(_tagName, _tagValue));
				}
			} else if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof UnusualAdventuresModMenus.MenuAccessor _menu18 ? _menu18.getSlots().get(2).getItem() : ItemStack.EMPTY)
					.getItem() == UnusualAdventuresModItems.LASER_MODULE.get()) {
				{
					final String _tagName = "state";
					final String _tagValue = "laser";
					CustomData.update(DataComponents.CUSTOM_DATA, (entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof UnusualAdventuresModMenus.MenuAccessor _menu20 ? _menu20.getSlots().get(1).getItem() : ItemStack.EMPTY),
							tag -> tag.putString(_tagName, _tagValue));
				}
			}
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.vault.reject_rewarded_player")), SoundSource.BLOCKS, (float) 0.8,
							(float) Mth.nextDouble(RandomSource.create(), 1.2, 1.3));
				} else {
					_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.vault.reject_rewarded_player")), SoundSource.BLOCKS, (float) 0.8, (float) Mth.nextDouble(RandomSource.create(), 1.2, 1.3), false);
				}
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof UnusualAdventuresModMenus.MenuAccessor _menu) {
				_menu.getSlots().get(3).remove(1);
				_player.containerMenu.broadcastChanges();
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof UnusualAdventuresModMenus.MenuAccessor _menu) {
				_menu.getSlots().get(2).remove(1);
				_player.containerMenu.broadcastChanges();
			}
		}
	}
}