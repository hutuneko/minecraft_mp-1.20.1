package com.hutuneko.mp_mod.client;

import com.hutuneko.mp_mod.capability.CapabilityMagicP;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.Font.DisplayMode;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.client.renderer.MultiBufferSource;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = "mpmod")
public class ClientEventHandler {
    @SubscribeEvent
    public static void onRenderLivingPost(RenderLivingEvent.Post<LivingEntity, ?> event) {
        LivingEntity entity = event.getEntity();
        entity.getCapability(CapabilityMagicP.MANA_CAPABILITY).ifPresent(mana -> {
            int mp = mana.getMana();
            if (mp <= 0) return;

            PoseStack stack = event.getPoseStack();
            MultiBufferSource buffers = event.getMultiBufferSource();
            int packedLight = event.getPackedLight();

            stack.pushPose();

            stack.translate(0.0D, entity.getBbHeight() + 0.5, 0.0D);

            stack.mulPose(Minecraft.getInstance()
                    .getEntityRenderDispatcher()
                    .cameraOrientation());

            float scale = 0.025f;
            stack.scale(-scale, -scale, scale);

            Font font = Minecraft.getInstance().font;
            String text = String.valueOf(mp);
            int textWidth = font.width(text);
            org.joml.Matrix4f matrix = stack.last().pose();

            font.drawInBatch(
                    text,
                    -textWidth / 2f,
                    0f,
                    0xFFFFFF,
                    true,
                    matrix,
                    buffers,
                    DisplayMode.NORMAL,
                    packedLight,
                    0,
                    false
            );

            stack.popPose();
        });
    }
}
