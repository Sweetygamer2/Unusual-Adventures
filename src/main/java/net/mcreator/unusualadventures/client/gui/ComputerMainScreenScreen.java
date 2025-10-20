package net.mcreator.unusualadventures.client.gui;

import net.neoforged.neoforge.network.PacketDistributor;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.Minecraft;

import net.mcreator.unusualadventures.world.inventory.ComputerMainScreenMenu;
import net.mcreator.unusualadventures.procedures.HelpCommandProcedure;
import net.mcreator.unusualadventures.procedures.ConsoleAnswerProcedure;
import net.mcreator.unusualadventures.init.UnusualAdventuresModScreens;
import net.mcreator.unusualadventures.ComputerCommandMessage;

import com.mojang.blaze3d.systems.RenderSystem;

public class ComputerMainScreenScreen extends AbstractContainerScreen<ComputerMainScreenMenu> implements UnusualAdventuresModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	EditBox console;

	public ComputerMainScreenScreen(ComputerMainScreenMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 240;
		this.imageHeight = 160;
	}

	@Override
	public void updateMenuState(int elementType, String name, Object elementState) {
		menuStateUpdateActive = true;
		if (elementType == 0 && elementState instanceof String stringState) {
			if (name.equals("console"))
				console.setValue(stringState);
		}
		menuStateUpdateActive = false;
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		console.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(ResourceLocation.parse("unusual_adventures:textures/screens/console.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 240, 160, 240, 160);
		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		if (console.isFocused()) {
			if (key == 257) {
				String commandText = console.getValue();
				PacketDistributor.sendToServer(new ComputerCommandMessage(commandText, x, y, z));
				return true;
			}
			return console.keyPressed(key, b, c);
		}
		return super.keyPressed(key, b, c);
	}

	@Override
	public void resize(Minecraft minecraft, int width, int height) {
		String consoleValue = console.getValue();
		super.resize(minecraft, width, height);
		console.setValue(consoleValue);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		String[] lines0 = ConsoleAnswerProcedure.execute(world, this.x, this.y, this.z).split("\r?\n");
		int yOffset0 = 18;
		for (String line : lines0) {
			guiGraphics.drawString(this.font, line, 6, yOffset0, -14847448, false);
			yOffset0 += this.font.lineHeight;
		}
		guiGraphics.drawString(this.font, Component.translatable("gui.unusual_adventures.console.available"), 5, 45, -15379939, false);
		String[] lines = HelpCommandProcedure.execute(world, this.x, this.y, this.z).split("\r?\n");
		int yOffset = (47 + this.font.lineHeight);
		for (String line : lines) {
			guiGraphics.drawString(this.font, line, 6, yOffset, -15379939, false);
			yOffset += this.font.lineHeight;
		}
	}

	@Override
	public void init() {
		super.init();
		console = new EditBox(this.font, this.leftPos + 12, this.topPos + 6, 220, 18, Component.translatable("gui.unusual_adventures.computer_main_screen.console"));
		console.setMaxLength(38);
		console.setBordered(false);
		console.setTextShadow(false);
		console.setTextColor(0x1D7228);
		console.setFocused(true);
		console.setResponder(content -> {
			if (!menuStateUpdateActive)
				menu.sendMenuStateUpdate(entity, 0, "console", content, false);
		});
		console.setHint(Component.translatable("gui.unusual_adventures.computer_main_screen.console"));
		this.addWidget(this.console);
		this.setFocused(console);
	}
}