/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.IFuelHandler
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 */
package exterminatorJeff.undergroundBiomes.common;

import cpw.mods.fml.common.IFuelHandler;
import exterminatorJeff.undergroundBiomes.api.NamedItem;
import exterminatorJeff.undergroundBiomes.api.UBIDs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class FuelManager
implements IFuelHandler {
    public int getBurnTime(ItemStack fuel) {
        if (UBIDs.ligniteCoalName.matches(fuel.func_77973_b())) {
            return 200;
        }
        return 0;
    }
}

