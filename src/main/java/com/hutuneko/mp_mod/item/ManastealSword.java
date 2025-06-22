package com.hutuneko.mp_mod.item;

import com.hutuneko.mp_mod.capability.CapabilityMagicP;
import com.hutuneko.mp_mod.capability.ManaUtils;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tiers;
import org.jetbrains.annotations.NotNull;

public class ManastealSword extends SwordItem {
    private final int drainAmount;

    public ManastealSword(Tiers tier, int attackDamageModifier, float attackSpeedModifier, Properties properties, int drainAmount) {
        super(tier, attackDamageModifier, attackSpeedModifier, properties);
        this.drainAmount = drainAmount;
    }

    @Override
    public boolean hurtEnemy(@NotNull ItemStack stack, @NotNull LivingEntity target, LivingEntity attacker) {
        if (!attacker.level().isClientSide) {
            target.getCapability(CapabilityMagicP.MANA_CAPABILITY).ifPresent(tp -> {
                int actualDrain = Math.min(drainAmount, tp.getMana());
                tp.consumeMana(actualDrain);
                ManaUtils.consume(target, actualDrain);
                attacker.getCapability(CapabilityMagicP.MANA_CAPABILITY).ifPresent(ap -> {
                    ap.addMana(actualDrain);
                    ManaUtils.add(attacker, actualDrain);
                });
            });
        }
        return super.hurtEnemy(stack, target, attacker);
    }
}
