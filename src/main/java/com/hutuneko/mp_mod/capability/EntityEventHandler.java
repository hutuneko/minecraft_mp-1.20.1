package com.hutuneko.mp_mod.capability;

import com.hutuneko.mp_mod.MPMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MPMod.MOD_ID)
public class EntityEventHandler {
    @SubscribeEvent
    public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> evt) {
        if (evt.getObject() instanceof LivingEntity) {
            evt.addCapability(
                    new ResourceLocation(MPMod.MOD_ID, "mana"),
                    new ManaProvider()
            );
        }
    }
}
