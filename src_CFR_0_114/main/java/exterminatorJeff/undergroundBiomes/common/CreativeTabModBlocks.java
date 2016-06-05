/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.item.Item
 */
package exterminatorJeff.undergroundBiomes.common;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabModBlocks
extends CreativeTabs {
    public Item item;

    public CreativeTabModBlocks(String s) {
        super(s);
    }

    @SideOnly(value=Side.CLIENT)
    public Item func_78016_d() {
        return this.item;
    }
}

