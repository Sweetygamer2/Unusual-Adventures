package net.mcreator.unusualadventures;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

public record ClientboundMusicSyncPacket(BlockPos pos, int trackId, long startTick, boolean isPlaying) implements CustomPacketPayload {

	public static final Type<ClientboundMusicSyncPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath("unusualadventures", "music_sync"));

	public static final StreamCodec<FriendlyByteBuf, ClientboundMusicSyncPacket> STREAM_CODEC = StreamCodec.composite(BlockPos.STREAM_CODEC, ClientboundMusicSyncPacket::pos, ByteBufCodecs.VAR_INT, ClientboundMusicSyncPacket::trackId,
			ByteBufCodecs.VAR_LONG, ClientboundMusicSyncPacket::startTick, ByteBufCodecs.BOOL, ClientboundMusicSyncPacket::isPlaying, ClientboundMusicSyncPacket::new);
	@Override
	public Type<ClientboundMusicSyncPacket> type() {
		return TYPE;
	}
}