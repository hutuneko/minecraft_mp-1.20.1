package com.hutuneko.mp_mod.item;

import com.hutuneko.mp_mod.MPMod;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MPMod.MOD_ID);

    public static final RegistryObject<Item> LIFE_STEAL_SWORD =
            ITEMS.register("lifesteal_sword", () ->
                    new LifestealSword(
                            Tiers.IRON,
                            3,
                            -2.4f,
                            new Item.Properties(),
                            5
                    )
            );

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
