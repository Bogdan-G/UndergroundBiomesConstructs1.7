/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.IRecipe
 *  net.minecraftforge.oredict.ShapelessOreRecipe
 */
package exterminatorJeff.undergroundBiomes.constructs.block;

import exterminatorJeff.undergroundBiomes.api.NamedBlock;
import exterminatorJeff.undergroundBiomes.api.UBIDs;
import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;
import exterminatorJeff.undergroundBiomes.common.block.BlockMetadataBase;
import exterminatorJeff.undergroundBiomes.constructs.block.UBButtonBase;
import exterminatorJeff.undergroundBiomes.constructs.block.UBConstructGroup;
import exterminatorJeff.undergroundBiomes.constructs.item.ItemUBButton;
import exterminatorJeff.undergroundBiomes.constructs.util.UndergroundBiomesBlock;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class UBButtonGroup
extends UBConstructGroup {
    private static UBButtonBase constructBlock;

    public UBButtonGroup() {
        super("Button");
    }

    public static boolean suppress(UndergroundBiomesBlock block) {
        if (block.ubBlock == UndergroundBiomes.igneousStoneBrick) {
            return true;
        }
        return block.ubBlock == UndergroundBiomes.metamorphicStoneBrick;
    }

    IRecipe recipe(UBConstructGroup.ProductItemDefiner product, UBConstructGroup.StoneItemDefiner stone) {
        if (UBButtonGroup.suppress(stone.ubBlock())) {
            return null;
        }
        return new ShapelessOreRecipe(product.stackOf(1), new Object[]{stone.one()});
    }

    IRecipe rescueRecipe(UBConstructGroup.ProductItemDefiner product, UBConstructGroup.StoneItemDefiner stone) {
        if (stone.ubBlock().ubBlock == UndergroundBiomes.igneousCobblestone) {
            return null;
        }
        if (stone.ubBlock().ubBlock == UndergroundBiomes.metamorphicCobblestone) {
            return null;
        }
        if (stone.ubBlock().ubBlock == UndergroundBiomes.igneousStoneBrick) {
            return new ShapelessOreRecipe(new ItemStack(Blocks.field_150430_aB, 1), new Object[]{product.stackOf(1)});
        }
        if (stone.ubBlock().ubBlock == UndergroundBiomes.metamorphicStoneBrick) {
            return new ShapelessOreRecipe(new ItemStack(Blocks.field_150430_aB, 1), new Object[]{product.stackOf(1)});
        }
        if (stone.ubBlock().ubBlock == UndergroundBiomes.sedimentaryStone) {
            return new ShapelessOreRecipe(new ItemStack(Blocks.field_150430_aB, 1), new Object[]{product.stackOf(1)});
        }
        return new ShapelessOreRecipe(new ItemStack(Blocks.field_150430_aB, 1), new Object[]{product.stackOf(1)});
    }

    Block definedBlock() {
        if (constructBlock == null) {
            constructBlock = new UBButtonBase(UBIDs.UBButtonName);
            UBIDs.UBButtonName.gameRegister((Block)constructBlock, ItemUBButton.class);
        }
        return constructBlock;
    }
}

