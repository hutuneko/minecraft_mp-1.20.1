package com.hutuneko.mp_mod.capability;

import com.hutuneko.mp_mod.MPMod;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.*;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.simple.SimpleChannel;

import java.util.Optional;
import java.util.function.Supplier;

public class NetworkHandler {
    private static final String PROTOCOL = "1";
    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(MPMod.MOD_ID, "channel"),
            () -> PROTOCOL, PROTOCOL::equals, PROTOCOL::equals
    );
    private static int id = 0;

    public static void register() {
        CHANNEL.registerMessage(id++, SyncMana.class,
                SyncMana::encode, SyncMana::decode, SyncMana::handle,
                Optional.of(NetworkDirection.PLAY_TO_CLIENT));
    }

    public static class SyncMana {
        public final int entityId, mana;
        public SyncMana(int id, int m) { this.entityId = id; this.mana = m; }
        public static void encode(SyncMana pkt, FriendlyByteBuf buf) {
            buf.writeInt(pkt.entityId);
            buf.writeInt(pkt.mana);
        }
        public static SyncMana decode(FriendlyByteBuf buf) {
            return new SyncMana(buf.readInt(), buf.readInt());
        }
        public static void handle(SyncMana pkt, Supplier<NetworkEvent.Context> ctx) {
            ctx.get().enqueueWork(() -> {
                LivingEntity e = null;
                if (Minecraft.getInstance().level != null) {
                    e = (LivingEntity)
                            Minecraft.getInstance().level.getEntity(pkt.entityId);
                }
                if (e != null) {
                    e.getCapability(CapabilityMagicP.MANA_CAPABILITY)
                            .ifPresent(m -> m.setMana(pkt.mana));
                }
            });
            ctx.get().setPacketHandled(true);
        }
    }
}