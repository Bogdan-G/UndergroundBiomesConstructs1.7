/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.CraftingManager
 *  net.minecraft.item.crafting.IRecipe
 */
package exterminatorJeff.undergroundBiomes.common.item;

import Zeno410Utils.Zeno410Logger;
import java.util.List;
import java.util.logging.Logger;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;

public class MutableRecipe {
    public static Logger logger = new Zeno410Logger("MutableRecipe").logger();
    private IRecipe current;

    public void set(IRecipe newRecipe) {
        if (this.current != null) {
            CraftingManager.func_77594_a().func_77592_b().remove((Object)this.current);
        }
        this.current = newRecipe;
        if (this.current == null) {
            logger.info("null recipe");
        } else {
            logger.info(this.current.func_77571_b().func_82833_r());
        }
        if (this.current != null) {
            CraftingManager.func_77594_a().func_77592_b().add(this.current);
        }
    }
}

