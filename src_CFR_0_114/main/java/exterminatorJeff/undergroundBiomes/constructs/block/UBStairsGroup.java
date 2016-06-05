/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.IRecipe
 *  net.minecraftforge.oredict.ShapedOreRecipe
 *  net.minecraftforge.oredict.ShapelessOreRecipe
 */
package exterminatorJeff.undergroundBiomes.constructs.block;

import exterminatorJeff.undergroundBiomes.api.NamedBlock;
import exterminatorJeff.undergroundBiomes.api.UBIDs;
import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;
import exterminatorJeff.undergroundBiomes.common.block.BlockMetadataBase;
import exterminatorJeff.undergroundBiomes.constructs.block.UBConstructGroup;
import exterminatorJeff.undergroundBiomes.constructs.block.UBStairsBase;
import exterminatorJeff.undergroundBiomes.constructs.item.ItemUBStairs;
import exterminatorJeff.undergroundBiomes.constructs.util.UndergroundBiomesBlock;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class UBStairsGroup
extends UBConstructGroup {
    private static UBStairsBase constructBlock;

    public UBStairsGroup() {
        super("stairs");
    }

    IRecipe recipe(UBConstructGroup.ProductItemDefiner product, UBConstructGroup.StoneItemDefiner stone) {
        return new ShapedOreRecipe(product.stackOf(4), new Object[]{"  X", " XX", "XXX", Character.valueOf('X'), stone.one()});
    }

    IRecipe rescueRecipe(UBConstructGroup.ProductItemDefiner product, UBConstructGroup.StoneItemDefiner stone) {
        if (stone.ubBlock().ubBlock == UndergroundBiomes.igneousCobblestone) {
            return null;
        }
        if (stone.ubBlock().ubBlock == UndergroundBiomes.metamorphicCobblestone) {
            return null;
        }
        if (stone.ubBlock().ubBlock == UndergroundBiomes.igneousStoneBrick) {
            return new ShapelessOreRecipe(new ItemStack(Blocks.field_150390_bg, 1), new Object[]{product.stackOf(1)});
        }
        if (stone.ubBlock().ubBlock == UndergroundBiomes.metamorphicStoneBrick) {
            return new ShapelessOreRecipe(new ItemStack(Blocks.field_150390_bg, 1), new Object[]{product.stackOf(1)});
        }
        if (stone.ubBlock().ubBlock == UndergroundBiomes.sedimentaryStone) {
            return new ShapelessOreRecipe(new ItemStack(Blocks.field_150372_bz, 1), new Object[]{product.stackOf(1)});
        }
        return new ShapelessOreRecipe(new ItemStack(Blocks.field_150446_ar, 1), new Object[]{product.stackOf(1)});
    }

    Block definedBlock() {
        if (constructBlock == null) {
            constructBlock = new UBStairsBase(this.baseBlock(), UBIDs.UBStairsName);
            UBIDs.UBStairsName.gameRegister((Block)constructBlock, ItemUBStairs.class);
        }
        return constructBlock;
    }
}

