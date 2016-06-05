/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.Item
 */
package exterminatorJeff.undergroundBiomes.api;

import exterminatorJeff.undergroundBiomes.api.NamedItem;
import exterminatorJeff.undergroundBiomes.api.UBIDs;
import net.minecraft.item.Item;

public class NamedVanillaItem
extends NamedItem {
    public static final NamedItem axeStone = new NamedVanillaItem("stone_pickaxe");
    public static final NamedItem blazeRod = new NamedVanillaItem("blaze_rod");
    public static final NamedItem bow = new NamedVanillaItem("bow");
    public static final NamedItem brewingStand = new NamedVanillaItem("brewing_stand");
    public static final NamedItem clay = new NamedVanillaItem("clay");
    public static final NamedItem coal = new NamedVanillaItem("coal");
    public static final NamedItem dyePowder = new NamedVanillaItem("dye");
    public static final NamedItem flint = new NamedVanillaItem("flint");
    public static final NamedItem goldNugget = new NamedVanillaItem("gold_nugget");
    public static final NamedItem hoeStone = new NamedVanillaItem("stone_hoe");
    public static final NamedItem ingotIron = new NamedVanillaItem("iron_ingot");
    public static final NamedItem pickaxeStone = new NamedVanillaItem("stone_pickaxe");
    public static final NamedItem redstone = new NamedVanillaItem("redstone");
    public static final NamedItem redstoneRepeater = new NamedVanillaItem("repeater");
    public static final NamedItem shovelStone = new NamedVanillaItem("stone_shovel");
    public static final NamedItem stick = new NamedVanillaItem("stick");
    public static final NamedItem swordStone = new NamedVanillaItem("stone_sword");

    public NamedVanillaItem(String name) {
        super(name);
        this.id = UBIDs.itemID(name);
        this.item = UBIDs.itemNamed(name);
    }
}

