package net.succ.succsessentials_extended.network.packet;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.succ.succsessentials_extended.Succsessentials_extended;
import net.succ.succsessentials_extended.block.entity.custom.NuclearReactorControllerBlockEntity;

public record ToggleReactorPacket(BlockPos pos) implements CustomPacketPayload {

    public static final Type<ToggleReactorPacket> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath(Succsessentials_extended.MOD_ID, "toggle_reactor"));

    public static final StreamCodec<FriendlyByteBuf, ToggleReactorPacket> STREAM_CODEC =
            StreamCodec.composite(
                    BlockPos.STREAM_CODEC,
                    ToggleReactorPacket::pos,
                    ToggleReactorPacket::new
            );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(ToggleReactorPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            ServerLevel level = (ServerLevel) context.player().level();
            if (level.getBlockEntity(packet.pos()) instanceof NuclearReactorControllerBlockEntity be) {
                be.setRunning(!be.isRunning());
            }
        });
    }
}