package com.hutuneko.mp_mod.capability;

public interface IMana {
    int getMana();
    void setMana(int mana);
    void addMana(int amount);
    void consumeMana(int amount);
}
