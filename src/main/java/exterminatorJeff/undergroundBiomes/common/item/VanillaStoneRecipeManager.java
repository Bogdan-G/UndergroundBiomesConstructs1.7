
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

/**
 *
 * @author Zeno410
 */
public class VanillaStoneRecipeManager extends Acceptor<Integer>{

    private final MutableRecipe recipe = new MutableRecipe();
    private final String oreCobblestoneName;
    public VanillaStoneRecipeManager(String oreCobblestoneName) {
        this.oreCobblestoneName = oreCobblestoneName;
    }

    @Override
    public void accept(Integer accepted) {
        MutableRecipe.logger.info("setting recipe "+accepted);
        recipe.set(recipeFor(accepted));
    }

    public IRecipe recipeFor(int index) {
         switch (index) {
            case 1:
                return new ShapelessOreRecipe(new ItemStack(NamedVanillaBlock.cobblestone.block(), 1), oreCobblestoneName);
            case 2:
                return new ShapelessOreRecipe(new ItemStack(NamedVanillaBlock.cobblestone.block(), 1), NamedVanillaItem.redstone.cachedItem(), oreCobblestoneName);
            case 3:
                return new ShapedOreRecipe(new ItemStack(NamedVanillaBlock.cobblestone.block(), 1), "XX", "XX", 'X', oreCobblestoneName);
            case 4:
                return new ShapedOreRecipe(new ItemStack(NamedVanillaBlock.cobblestone.block(), 4), "XX", "XX", 'X', oreCobblestoneName);
            default:
                return null;
        }
    }

}
