package com.hutuneko.mp_mod.command;

import com.hutuneko.mp_mod.capability.CapabilityMagicP;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = "mpmod")
public class CommandRegistration {
    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent evt) {
        CommandDispatcher<CommandSourceStack> d = evt.getDispatcher();
        d.register(Commands.literal("mp")
                .executes(ctx -> {
                    var player = ctx.getSource().getPlayerOrException();
                    player.getCapability(CapabilityMagicP.MANA_CAPABILITY).ifPresent(m -> ctx.getSource().sendSuccess(
                            (Supplier<Component>) Component.literal("あなたのMP: " + m.getMana()),
                            false
                    ));
                    return 1;
                })
        );
    }
}
