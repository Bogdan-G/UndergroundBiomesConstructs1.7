/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.IRecipe
 *  net.minecraftforge.oredict.ShapedOreRecipe
 *  net.minecraftforge.oredict.ShapelessOreRecipe
 */
package exterminatorJeff.undergroundBiomes.common.item;

import Zeno410Utils.Acceptor;
import exterminatorJeff.undergroundBiomes.api.NamedBlock;
import exterminatorJeff.undergroundBiomes.api.NamedItem;
import exterminatorJeff.undergroundBiomes.api.NamedVanillaBlock;
import exterminatorJeff.undergroundBiomes.api.NamedVanillaItem;
import exterminatorJeff.undergroundBiomes.common.item.MutableRecipe;
import java.util.logging.Logger;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class VanillaStoneRecipeManager
extends Acceptor<Integer> {
    private final MutableRecipe recipe = new MutableRecipe();
    private final String oreCobblestoneName;

    public VanillaStoneRecipeManager(String oreCobblestoneName) {
        this.oreCobblestoneName = oreCobblestoneName;
    }

    @Override
    public void accept(Integer accepted) {
        MutableRecipe.logger.info("setting recipe " + accepted);
        this.recipe.set(this.recipeFor(accepted));
    }

    public IRecipe recipeFor(int index) {
        switch (index) {
            case 1: {
                return new ShapelessOreRecipe(new ItemStack(NamedVanillaBlock.cobblestone.block(), 1), new Object[]{this.oreCobblestoneName});
            }
            case 2: {
                return new ShapelessOreRecipe(new ItemStack(NamedVanillaBlock.cobblestone.block(), 1), new Object[]{NamedVanillaItem.redstone.cachedItem(), this.oreCobblestoneName});
            }
            case 3: {
                return new ShapedOreRecipe(new ItemStack(NamedVanillaBlock.cobblestone.block(), 1), new Object[]{"XX", "XX", Character.valueOf('X'), this.oreCobblestoneName});
            }
            case 4: {
                return new ShapedOreRecipe(new ItemStack(NamedVanillaBlock.cobblestone.block(), 4), new Object[]{"XX", "XX", Character.valueOf('X'), this.oreCobblestoneName});
            }
        }
        return null;
    }
}

