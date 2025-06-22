package com.hutuneko.mp_mod;

import com.hutuneko.mp_mod.capability.NetworkHandler;
import com.hutuneko.mp_mod.item.ModItems;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(com.hutuneko.mp_mod.MPMod.MOD_ID)
public class MPMod {

    public static final String MOD_ID = "mpmod";
    public MPMod() {
        FMLJavaModLoadingContext.get().getModEventBus()
                .addListener(this::setup);
        ModItems.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
    private void setup(final FMLCommonSetupEvent evt) {
        NetworkHandler.register();
    }
}
