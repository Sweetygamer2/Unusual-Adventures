package net.mcreator.unusualadventures;

import net.neoforged.neoforge.network.handling.IPayloadContext;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.FriendlyByteBuf;

import net.mcreator.unusualadventures.procedures.ExecuteCommandProcedure;

public record ComputerCommandMessage(String command, int x, int y, int z) implements CustomPacketPayload {

	public static final Type<ComputerCommandMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(UnusualAdventuresMod.MODID, "computer_command"));
	public static final StreamCodec<RegistryFriendlyByteBuf, ComputerCommandMessage> STREAM_CODEC = StreamCodec.of(ComputerCommandMessage::write, ComputerCommandMessage::read);
	public static void write(FriendlyByteBuf buffer, ComputerCommandMessage message) {
		buffer.writeUtf(message.command);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}

	public static ComputerCommandMessage read(FriendlyByteBuf buffer) {
		String command = buffer.readUtf();
		int x = buffer.readInt();
		int y = buffer.readInt();
		int z = buffer.readInt();
		return new ComputerCommandMessage(command, x, y, z);
	}

	@Override
	public Type<ComputerCommandMessage> type() {
		return TYPE;
	}

	public static void handle(ComputerCommandMessage message, IPayloadContext context) {
		context.enqueueWork(() -> {
			ExecuteCommandProcedure.execute(context.player().level(), message.x, message.y, message.z, context.player(), message.command);
		});
	}
}