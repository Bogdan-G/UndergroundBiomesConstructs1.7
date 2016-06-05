/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  net.minecraft.inventory.InventoryCrafting
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.IRecipe
 *  net.minecraft.world.World
 */
package exterminatorJeff.undergroundBiomes.constructs.item;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public abstract class ReferredRecipe
implements IRecipe {
    public abstract IRecipe referred();

    public boolean func_77569_a(InventoryCrafting arg0, World arg1) {
        return this.referred().func_77569_a(arg0, arg1);
    }

    public ItemStack func_77572_b(InventoryCrafting arg0) {
        return this.referred().func_77572_b(arg0);
    }

    public int func_77570_a() {
        return this.referred().func_77570_a();
    }

    public ItemStack func_77571_b() {
        return this.referred().func_77571_b();
    }
}

