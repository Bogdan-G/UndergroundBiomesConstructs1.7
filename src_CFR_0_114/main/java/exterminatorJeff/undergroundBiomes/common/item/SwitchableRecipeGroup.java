/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.crafting.CraftingManager
 *  net.minecraft.item.crafting.IRecipe
 */
package exterminatorJeff.undergroundBiomes.common.item;

import Zeno410Utils.Acceptor;
import Zeno410Utils.Mutable;
import java.util.Collection;
import java.util.List;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class SwitchableRecipeGroup
extends Acceptor<Boolean> {
    private Collection<IRecipe> recipes;

    public SwitchableRecipeGroup(Collection<IRecipe> recipes, Mutable<Boolean> flag) {
        this.recipes = recipes;
        if (flag.value().booleanValue()) {
            this.addRecipes();
        }
        flag.informOnChange(this);
    }

    private void addRecipes() {
        for (IRecipe recipe : this.recipes) {
            CraftingManager.func_77594_a().func_77592_b().add(recipe);
        }
    }

    private void removeRecipes() {
        for (IRecipe recipe : this.recipes) {
            CraftingManager.func_77594_a().func_77592_b().remove((Object)recipe);
        }
    }

    @Override
    public void accept(Boolean accepted) {
        if (accepted.booleanValue()) {
            this.addRecipes();
        } else {
            this.removeRecipes();
        }
    }
}

