/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.StatCollector
 */
package Zeno410Utils;

import net.minecraft.util.StatCollector;

public class MinecraftName {
    private final String unlocalized;

    public MinecraftName(String unlocalized) {
        this.unlocalized = unlocalized;
    }

    public String localized() {
        return StatCollector.func_74838_a((String)(this.unlocalized() + ".name"));
    }

    public String unlocalized() {
        return this.unlocalized;
    }

    public boolean legit() {
        return StatCollector.func_94522_b((String)(this.unlocalized + ".name"));
    }
}

