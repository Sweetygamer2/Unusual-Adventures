package net.mcreator.unusualadventures.procedures;

import net.neoforged.neoforge.event.ItemAttributeModifierEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.component.DataComponents;

import net.mcreator.unusualadventures.init.UnusualAdventuresModItems;

import javax.annotation.Nullable;

@EventBusSubscriber
public class AttributesModifiersProcedure {
	@SubscribeEvent
	public static void addAttributeModifier(ItemAttributeModifierEvent event) {
		execute(event, event.getItemStack());
	}

	public static void execute(ItemStack itemstack) {
		execute((Event) null, itemstack);
	}

	private static void execute(@Nullable Event event, ItemStack itemstack) {
		AttributeModifier modifier = null;
		ItemAttributeModifierEvent _event;
		if (itemstack.getMaxStackSize() == 1) {
			if (event instanceof ItemAttributeModifierEvent) {
				_event = (ItemAttributeModifierEvent) event;
				if (itemstack.getItem() == UnusualAdventuresModItems.NULL_GAUNTLET.get() && (itemstack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getString("state")).equals("kb")
						&& (itemstack.getDamageValue() + 20 < itemstack.getMaxDamage())) {
					modifier = new AttributeModifier(ResourceLocation.fromNamespaceAndPath("unusual_adventures", "effect.null_kb"), 2.5D, Operation.ADD_VALUE);
					_event.addModifier(Attributes.ATTACK_KNOCKBACK, modifier, EquipmentSlotGroup.MAINHAND);
				}
			}
		}
	}
}