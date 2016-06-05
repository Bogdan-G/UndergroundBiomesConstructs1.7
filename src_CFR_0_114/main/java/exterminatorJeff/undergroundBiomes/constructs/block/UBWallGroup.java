/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.IRecipe
 *  net.minecraftforge.oredict.ShapedOreRecipe
 *  net.minecraftforge.oredict.ShapelessOreRecipe
 */
package exterminatorJeff.undergroundBiomes.constructs.block;

import cpw.mods.fml.common.registry.GameRegistry;
import exterminatorJeff.undergroundBiomes.api.NamedBlock;
import exterminatorJeff.undergroundBiomes.api.UBIDs;
import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;
import exterminatorJeff.undergroundBiomes.common.block.BlockMetadataBase;
import exterminatorJeff.undergroundBiomes.constructs.block.UBConstructGroup;
import exterminatorJeff.undergroundBiomes.constructs.block.UBWallBase;
import exterminatorJeff.undergroundBiomes.constructs.item.ItemUBWall;
import exterminatorJeff.undergroundBiomes.constructs.util.UndergroundBiomesBlock;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class UBWallGroup
extends UBConstructGroup {
    private static UBWallBase constructBlock;

    public UBWallGroup() {
        super("wall");
    }

    void addRecipe(UBConstructGroup.ProductItemDefiner product, UBConstructGroup.StoneItemDefiner stone) {
        ShapedOreRecipe wallRecipe = this.wallRecipe(product, stone);
        GameRegistry.addRecipe((IRecipe)wallRecipe);
        ItemStack stack = product.one();
        ItemUBWall item = (ItemUBWall)stack.func_77973_b();
        String test = item.toString();
        Item toGet = product.stackOf(6).func_77973_b();
        Item toGetProduct = wallRecipe.func_77571_b().func_77946_l().func_77973_b();
        GameRegistry.addShapelessRecipe((ItemStack)new ItemStack(Blocks.field_150463_bK, 1), (Object[])new Object[]{product.stackOf(1)});
    }

    IRecipe recipe(UBConstructGroup.ProductItemDefiner product, UBConstructGroup.StoneItemDefiner stone) {
        return this.wallRecipe(product, stone);
    }

    IRecipe rescueRecipe(UBConstructGroup.ProductItemDefiner product, UBConstructGroup.StoneItemDefiner stone) {
        if (stone.ubBlock().ubBlock == UndergroundBiomes.igneousCobblestone) {
            return new ShapelessOreRecipe(new ItemStack(Blocks.field_150463_bK, 1), new Object[]{product.stackOf(1)});
        }
        if (stone.ubBlock().ubBlock == UndergroundBiomes.metamorphicCobblestone) {
            return new ShapelessOreRecipe(new ItemStack(Blocks.field_150463_bK, 1), new Object[]{product.stackOf(1)});
        }
        return null;
    }

    ShapedOreRecipe wallRecipe(UBConstructGroup.ProductItemDefiner product, UBConstructGroup.StoneItemDefiner stone) {
        return new ShapedOreRecipe(product.stackOf(6), new Object[]{"   ", "XXX", "XXX", Character.valueOf('X'), stone.one()});
    }

    Block definedBlock() {
        if (constructBlock == null) {
            constructBlock = new UBWallBase(this.baseBlock(), UBIDs.UBWallsName);
            UBIDs.UBWallsName.gameRegister((Block)constructBlock, ItemUBWall.class);
        }
        return constructBlock;
    }
}

