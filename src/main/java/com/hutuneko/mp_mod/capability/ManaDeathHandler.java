//package com.hutuneko.mp_mod.capability;
//
//import com.hutuneko.mp_mod.MPMod;
//import net.minecraft.world.entity.Mob;
//import net.minecraft.world.entity.LivingEntity;
//import net.minecraftforge.event.entity.living.LivingEvent;
//import net.minecraftforge.eventbus.api.SubscribeEvent;
//import net.minecraftforge.fml.common.Mod;
//
//@Mod.EventBusSubscriber(
//        modid = MPMod.MOD_ID,
//        bus   = Mod.EventBusSubscriber.Bus.FORGE
//)
//public class ManaDeathHandler {
//    @SubscribeEvent
//    public static void onLivingTick(LivingEvent.LivingTickEvent event) {
//        LivingEntity entity = event.getEntity();
//
//        // サーバ側だけ・モブだけ処理
//        if (entity.level().isClientSide || !(entity instanceof Mob mobEntity)) return;
//
//        // Capability が付いていて MP が 0 以下なら
//        mobEntity.getCapability(CapabilityMagicP.MANA_CAPABILITY).ifPresent(mana -> {
//            if (mana.getMana() <= 0) {
//                // 0 にセットすれば次の tick で死亡判定が走ります
//                mobEntity.setHealth(0.0F);
//            }
//        });
//    }
//}
