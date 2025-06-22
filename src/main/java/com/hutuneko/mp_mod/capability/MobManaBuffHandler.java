package com.hutuneko.mp_mod.capability;

import com.hutuneko.mp_mod.MPMod;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(
        modid = MPMod.MOD_ID,
        bus   = Mod.EventBusSubscriber.Bus.FORGE
)
public class MobManaBuffHandler {
    @SubscribeEvent
    public static void onLivingTick(LivingEvent.LivingTickEvent event) {
        LivingEntity entity = event.getEntity();

        if (!(entity instanceof Mob mobEntity)) return;

        if (mobEntity.level().isClientSide) return;

        LivingEntity target = mobEntity.getTarget();
        if (target == null) return;
        if (!(target instanceof Player)) return;

        mobEntity.getCapability(CapabilityMagicP.MANA_CAPABILITY).ifPresent(mana -> {
            int cost = 20;
            if (mana.getMana() >= cost && !mobEntity.hasEffect(MobEffects.DAMAGE_BOOST)) {
                mana.consumeMana(cost);
                ManaUtils.consume(mobEntity, cost);
                mobEntity.addEffect(new MobEffectInstance(
                        MobEffects.DAMAGE_BOOST, 200, 1, false, true
                ));
                mobEntity.addEffect(new MobEffectInstance(
                        MobEffects.MOVEMENT_SPEED, 200, 1, false, true
                ));
            }
        });
    }
}