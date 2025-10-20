package net.mcreator.unusualadventures.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.AdvancementHolder;

import net.mcreator.unusualadventures.init.UnusualAdventuresModMenus;

public class FullComputerAdvancementProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (!(entity instanceof ServerPlayer _plr0 && _plr0.level() instanceof ServerLevel && _plr0.getAdvancements().getOrStartProgress(_plr0.server.getAdvancements().get(ResourceLocation.parse("unusual_adventures:full_upgrade"))).isDone())) {
			if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof UnusualAdventuresModMenus.MenuAccessor _menu1 ? _menu1.getSlots().get(0).getItem() : ItemStack.EMPTY)
					.is(ItemTags.create(ResourceLocation.parse("unusual_adventures:upgrade")))
					&& (entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof UnusualAdventuresModMenus.MenuAccessor _menu3 ? _menu3.getSlots().get(1).getItem() : ItemStack.EMPTY)
							.is(ItemTags.create(ResourceLocation.parse("unusual_adventures:upgrade")))
					&& (entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof UnusualAdventuresModMenus.MenuAccessor _menu5 ? _menu5.getSlots().get(2).getItem() : ItemStack.EMPTY)
							.is(ItemTags.create(ResourceLocation.parse("unusual_adventures:upgrade")))
					&& (entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof UnusualAdventuresModMenus.MenuAccessor _menu7 ? _menu7.getSlots().get(3).getItem() : ItemStack.EMPTY)
							.is(ItemTags.create(ResourceLocation.parse("unusual_adventures:upgrade")))) {
				if (entity instanceof ServerPlayer _player) {
					AdvancementHolder _adv = _player.server.getAdvancements().get(ResourceLocation.parse("unusual_adventures:full_upgrade"));
					if (_adv != null) {
						AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
						if (!_ap.isDone()) {
							for (String criteria : _ap.getRemainingCriteria())
								_player.getAdvancements().award(_adv, criteria);
						}
					}
				}
			}
		}
	}
}