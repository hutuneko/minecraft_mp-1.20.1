package com.hutuneko.mp_mod.capability;

import net.minecraft.core.Direction;
import net.minecraft.nbt.IntTag;
import net.minecraft.nbt.Tag;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;

public class ManaProvider implements ICapabilitySerializable<Tag> {
    private final IMana mana = new Mana();
    private final LazyOptional<IMana> opt = LazyOptional.of(() -> mana);

    @Override
    @Nonnull
    public <T> LazyOptional<T> getCapability(
            @Nonnull Capability<T> cap, Direction side) {
        return cap == CapabilityMagicP.MANA_CAPABILITY
                ? opt.cast()
                : LazyOptional.empty();
    }

    @Override
    public Tag serializeNBT() {
        return IntTag.valueOf(mana.getMana());
    }

    @Override
    public void deserializeNBT(Tag nbt) {
        mana.setMana(((IntTag) nbt).getAsInt());
    }
}

