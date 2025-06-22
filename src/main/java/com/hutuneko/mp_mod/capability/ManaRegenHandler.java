package com.hutuneko.mp_mod.capability;

import com.hutuneko.mp_mod.MPMod;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(
        modid = MPMod.MOD_ID,
        bus   = Mod.EventBusSubscriber.Bus.FORGE
)
public class ManaRegenHandler {
    @SubscribeEvent
    public static void onLivingTick(LivingEvent.LivingTickEvent event) {
        LivingEntity entity = event.getEntity();

        if (entity.level().isClientSide) return;

        if (entity.tickCount % 100 != 0) return;

        entity.getCapability(CapabilityMagicP.MANA_CAPABILITY).ifPresent(mana -> {
            mana.addMana(10);
            ManaUtils.add(entity, 10);
        });
    }
}
