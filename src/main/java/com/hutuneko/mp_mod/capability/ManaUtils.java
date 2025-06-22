package com.hutuneko.mp_mod.capability;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.network.PacketDistributor;


public class ManaUtils {
    public static void consume(LivingEntity entity, int amount) {
        entity.getCapability(CapabilityMagicP.MANA_CAPABILITY).ifPresent(m -> {
            m.consumeMana(amount);
            NetworkHandler.CHANNEL.send(
                    PacketDistributor.TRACKING_ENTITY.with(() -> entity),
                    new NetworkHandler.SyncMana(entity.getId(), m.getMana())
            );
        });
    }

    public static void add(LivingEntity entity, int amount) {
        entity.getCapability(CapabilityMagicP.MANA_CAPABILITY).ifPresent(m -> {
            m.addMana(amount);
            NetworkHandler.SyncMana pkt = new NetworkHandler.SyncMana(entity.getId(), m.getMana());
            if (entity instanceof ServerPlayer sp) {
                NetworkHandler.CHANNEL.send(
                        PacketDistributor.PLAYER.with(() -> sp),
                        pkt
                );
            } else {
                NetworkHandler.CHANNEL.send(
                        PacketDistributor.TRACKING_ENTITY.with(() -> entity),
                        pkt
                );
            }
        });
    }
}
