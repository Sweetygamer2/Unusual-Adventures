package net.mcreator.unusualadventures.item;

import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.util.Mth;
import net.minecraft.network.chat.Component;
import net.minecraft.core.component.DataComponents;
import net.minecraft.client.Minecraft;

import net.mcreator.unusualadventures.procedures.NullspaceDurabilityRegenProcedure;
import net.mcreator.unusualadventures.procedures.NullGauntletSpecialInformationProcedure;
import net.mcreator.unusualadventures.procedures.NullGauntletLivingEntityIsHitWithItemProcedure;
import net.mcreator.unusualadventures.procedures.NullGauntletEntitySwingsItemProcedure;

import java.util.List;

public class NullGauntletItem extends Item {
	public NullGauntletItem() {
		super(new Item.Properties().durability(3000).rarity(Rarity.UNCOMMON));
	}

	@Override
	public int getBarColor(ItemStack stack) {
		if ((stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getString("state")).equals("kb")) {
			return 8388352;
		} else if ((stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getString("state")).equals("heal")) {
			return 16728000;
		} else if ((stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getString("state")).equals("laser")) {
			return 16750848;
		} else if ((stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getString("state")).equals("dash")) {
			return 6074606;
		}
		return Mth.hsvToRgb(Math.max(0.0F, 1.0F - (float) stack.getDamageValue() / stack.getMaxDamage()) / 3.0F, 1.0F, 1.0F);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack itemstack, Item.TooltipContext context, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, context, list, flag);
		Entity entity = itemstack.getEntityRepresentation() != null ? itemstack.getEntityRepresentation() : Minecraft.getInstance().player;
		String hoverText = NullGauntletSpecialInformationProcedure.execute(itemstack);
		if (hoverText != null) {
			for (String line : hoverText.split(",")) {
				list.add(Component.literal(line));
			}
		}
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
		InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);
		NullGauntletEntitySwingsItemProcedure.execute(world, entity.getX(), entity.getY(), entity.getZ(), entity, ar.getObject());
		return ar;
	}

	@Override
	public boolean hurtEnemy(ItemStack itemstack, LivingEntity entity, LivingEntity sourceentity) {
		boolean retval = super.hurtEnemy(itemstack, entity, sourceentity);
		NullGauntletLivingEntityIsHitWithItemProcedure.execute(entity.level(), entity.getX(), entity.getY(), entity.getZ(), entity, sourceentity, itemstack);
		return retval;
	}

	@Override
	public void inventoryTick(ItemStack itemstack, Level world, Entity entity, int slot, boolean selected) {
		super.inventoryTick(itemstack, world, entity, slot, selected);
		if (selected)
			NullspaceDurabilityRegenProcedure.execute(entity, itemstack);
	}
}