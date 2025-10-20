package net.mcreator.unusualadventures.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;

public class NullspaceDurabilityRegenProcedure {
	public static void execute(Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		if ((entity.level().dimension()) == ResourceKey.create(Registries.DIMENSION, ResourceLocation.parse("unusual_adventures:nullspace"))) {
			if (itemstack.getDamageValue() > 0) {
				itemstack.setDamageValue(itemstack.getDamageValue() - 1);
			}
		}
	}
}