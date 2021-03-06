
package exterminatorJeff.undergroundBiomes.common.item;

import Zeno410Utils.Zeno410Logger;
import java.util.List;
import java.util.logging.Logger;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;

/**
 *
 * @author Zeno410
 */
public class MutableRecipe {
    public static Logger logger = new Zeno410Logger("MutableRecipe").logger();
    private IRecipe current;

    public void set(IRecipe newRecipe) {
        if (current != null) {
            //remove current recipe
            CraftingManager.getInstance().getRecipeList().remove(current);
        }
        current = newRecipe;
        if (current == null) {
            cpw.mods.fml.common.FMLLog.info("null recipe");
        } else {
            cpw.mods.fml.common.FMLLog.info(current.getRecipeOutput().getDisplayName());
        }
        if (current != null) {
            CraftingManager.getInstance().getRecipeList().add(current);
        }
    }

}
