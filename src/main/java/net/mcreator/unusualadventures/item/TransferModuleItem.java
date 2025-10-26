package net.mcreator.unusualadventures.item;

import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.InteractionResult;
import net.minecraft.network.chat.Component;
import net.minecraft.core.component.DataComponents;
import net.minecraft.client.gui.screens.Screen;

import net.mcreator.unusualadventures.procedures.TransferModuleRightclickedOnBlockProcedure;

import java.util.List;

public class TransferModuleItem extends Item {
	public TransferModuleItem() {
		super(new Item.Properties().stacksTo(16).rarity(Rarity.UNCOMMON));
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack itemstack, Item.TooltipContext context, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, context, list, flag);
		int TpX = 0;
		int TpY = 0;
		int TpZ = 0;
		TpX = (int) itemstack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDouble("TpX");
		TpY = (int) itemstack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDouble("TpY");
		TpZ = (int) itemstack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDouble("TpZ");
		if (Screen.hasControlDown()) {
			list.add(Component.translatable("item.unusual_adventures.transfer_module.description_0"));
			list.add(Component.translatable("item.unusual_adventures.transfer_module.description_1"));
			list.add(Component.translatable("item.unusual_adventures.transfer_module.description_2"));
			list.add(Component.translatable("item.unusual_adventures.transfer_module.description_3"));
			list.add(Component.translatable("item.unusual_adventures.transfer_module.description_4").append(Component.literal("§8" + TpX + " " + TpY + " " + TpZ)));
		} else {
			list.add(Component.translatable("item.unusual_adventures.transfer_module.description_0"));
			list.add(Component.translatable("item.unusual_adventures.transfer_module.description_1"));
			list.add(Component.translatable("block.unusual_adventures.short_desc"));
		}
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		super.useOn(context);
		return TransferModuleRightclickedOnBlockProcedure.execute(context.getLevel(), context.getClickedPos().getX(), context.getClickedPos().getY(), context.getClickedPos().getZ(), context.getItemInHand());
	}
}