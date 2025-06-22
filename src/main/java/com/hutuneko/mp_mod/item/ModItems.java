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
                            Tiers.IRON,      // ティア（例：鉄剣相当）
                            3,               // 追加攻撃力
                            -2.4f,           // 攻撃速度調整値
                            new Item.Properties(),
                            5                // 毎回吸収する MP 量
                    )
            );

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
