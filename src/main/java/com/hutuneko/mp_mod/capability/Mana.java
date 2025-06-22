package com.hutuneko.mp_mod.capability;

import net.minecraft.util.Mth;


public class Mana implements IMana {
    private int mana = 20;
    private static final int MAX = 100;
    @Override public int getMana() { return mana; }
    @Override public void setMana(int m) { this.mana = Mth.clamp(m, 0, MAX);
    }
    @Override public void addMana(int a) { setMana(this.mana + a); }
    @Override public void consumeMana(int a) { setMana(this.mana - a); }
}