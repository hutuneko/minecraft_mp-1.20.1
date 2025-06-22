package com.hutuneko.mp_mod.capability;

import com.hutuneko.mp_mod.MPMod;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MPMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CapabilityMagicP {
    public static final Capability<IMana> MANA_CAPABILITY =
            CapabilityManager.get(new CapabilityToken<>() {
            });

    @SubscribeEvent
    public static void onRegisterCaps(RegisterCapabilitiesEvent event) {
        event.register(IMana.class);
    }
}
