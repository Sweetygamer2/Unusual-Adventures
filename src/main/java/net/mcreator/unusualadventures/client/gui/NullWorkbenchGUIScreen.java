package net.mcreator.unusualadventures.client.gui;

import net.neoforged.neoforge.network.PacketDistributor;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.GuiGraphics;

import net.mcreator.unusualadventures.world.inventory.NullWorkbenchGUIMenu;
import net.mcreator.unusualadventures.procedures.ReturnCanCombineChipProcedure;
import net.mcreator.unusualadventures.network.NullWorkbenchGUIButtonMessage;
import net.mcreator.unusualadventures.init.UnusualAdventuresModScreens;
import net.mcreator.unusualadventures.init.UnusualAdventuresModMenus;

import com.mojang.blaze3d.systems.RenderSystem;

public class NullWorkbenchGUIScreen extends AbstractContainerScreen<NullWorkbenchGUIMenu> implements UnusualAdventuresModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	ImageButton imagebutton_apply_chip;

	public NullWorkbenchGUIScreen(NullWorkbenchGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 140;
	}

	@Override
	public void updateMenuState(int elementType, String name, Object elementState) {
		menuStateUpdateActive = true;
		menuStateUpdateActive = false;
	}

	private static final ResourceLocation texture = ResourceLocation.parse("unusual_adventures:textures/screens/null_workbench_gui.png");

	private static int getAmountInGUISlot(Entity entity, int sltid) {
		if (entity instanceof Player player && player.containerMenu instanceof UnusualAdventuresModMenus.MenuAccessor menuAccessor) {
			ItemStack stack = menuAccessor.getSlots().get(sltid).getItem();
			if (stack != null)
				return stack.getCount();
		}
		return 0;
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		boolean customTooltipShown = false;
		if (getAmountInGUISlot(entity, 0) == 0) {
			if (mouseX > leftPos + 43 && mouseX < leftPos + 59 && mouseY > topPos + 22 && mouseY < topPos + 38) {
				guiGraphics.renderTooltip(font, Component.translatable("gui.unusual_adventures.null_workbench_gui.tooltip_power_block"), mouseX, mouseY);
				customTooltipShown = true;
			}
		}
		if (getAmountInGUISlot(entity, 3) == 0) {
			if (mouseX > leftPos + 133 && mouseX < leftPos + 149 && mouseY > topPos + 14 && mouseY < topPos + 30) {
				guiGraphics.renderTooltip(font, Component.translatable("block.unusual_adventures.living_code_block"), mouseX, mouseY);
				customTooltipShown = true;
			}
		}
		if (getAmountInGUISlot(entity, 2) == 0) {
			if (mouseX > leftPos + 115 && mouseX < leftPos + 131 && mouseY > topPos + 14 && mouseY < topPos + 30) {
				guiGraphics.renderTooltip(font, Component.translatable("gui.unusual_adventures.null_workbench_gui.tooltip_chip"), mouseX, mouseY);
				customTooltipShown = true;
			}
		}
		if (getAmountInGUISlot(entity, 1) == 0) {
			if (mouseX > leftPos + 79 && mouseX < leftPos + 95 && mouseY > topPos + 22 && mouseY < topPos + 38) {
				guiGraphics.renderTooltip(font, Component.translatable("item.unusual_adventures.null_gauntlet"), mouseX, mouseY);
				customTooltipShown = true;
			}
		}
		if (ReturnCanCombineChipProcedure.execute(entity)) {
			if (mouseX > leftPos + 115 && mouseX < leftPos + 140 && mouseY > topPos + 35 && mouseY < topPos + 45) {
				guiGraphics.renderTooltip(font, Component.translatable("gui.unusual_adventures.null_workbench_gui.tooltip_upgrade"), mouseX, mouseY);
				customTooltipShown = true;
			}
		}
		if (!customTooltipShown)
			this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(texture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		if (getAmountInGUISlot(entity, 0) == 0) {
			guiGraphics.blit(ResourceLocation.parse("unusual_adventures:textures/screens/block_outline.png"), this.leftPos + 43, this.topPos + 22, 0, 0, 16, 16, 16, 16);
		}
		if (getAmountInGUISlot(entity, 3) == 0) {
			guiGraphics.blit(ResourceLocation.parse("unusual_adventures:textures/screens/block_outline.png"), this.leftPos + 133, this.topPos + 14, 0, 0, 16, 16, 16, 16);
		}
		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		return super.keyPressed(key, b, c);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		var poseStack = guiGraphics.pose();
		int text_color = -15525613;
		if (ReturnCanCombineChipProcedure.execute(entity)) {
			text_color = -11751600;
		}
		poseStack.pushPose();
		poseStack.translate(115, 35, 0);
		poseStack.scale(0.7f, 0.7f, 1.0f);
		poseStack.translate(-115, -35, 0);
		guiGraphics.drawString(this.font, Component.translatable("gui.unusual_adventures.null_workbench_gui.label_upgrade"), 119, 38, text_color, false);
		poseStack.popPose();
		//guiGraphics.drawString(this.font, Component.translatable("gui.unusual_adventures.null_workbench_gui.label_upgrade"), Math.round(117 / 0.7f), Math.round(37 / 0.7f), -11751600, false);
	}

	@Override
	public void init() {
		super.init();
		imagebutton_apply_chip = new ImageButton(this.leftPos + 114, this.topPos + 34, 36, 11,
				new WidgetSprites(ResourceLocation.parse("unusual_adventures:textures/screens/apply_chip.png"), ResourceLocation.parse("unusual_adventures:textures/screens/apply_chip.png")), e -> {
					int x = NullWorkbenchGUIScreen.this.x;
					int y = NullWorkbenchGUIScreen.this.y;
					if (ReturnCanCombineChipProcedure.execute(entity)) {
						PacketDistributor.sendToServer(new NullWorkbenchGUIButtonMessage(0, x, y, z));
						NullWorkbenchGUIButtonMessage.handleButtonAction(entity, 0, x, y, z);
					}
				}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int x, int y, float partialTicks) {
				if (ReturnCanCombineChipProcedure.execute(entity))
					guiGraphics.blit(sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0, 0, width, height, width, height);
			}
		};
		this.addRenderableWidget(imagebutton_apply_chip);
	}
}