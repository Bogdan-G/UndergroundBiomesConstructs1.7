/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.event.FMLInitializationEvent
 *  cpw.mods.fml.common.event.FMLPostInitializationEvent
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockButtonStone
 *  net.minecraft.block.BlockSlab
 *  net.minecraft.block.BlockStairs
 *  net.minecraft.block.BlockWall
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.IRecipe
 *  net.minecraftforge.common.config.Configuration
 */
package exterminatorJeff.undergroundBiomes.constructs;

import Zeno410Utils.Mutable;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import exterminatorJeff.undergroundBiomes.api.UndergroundBiomesSettings;
import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;
import exterminatorJeff.undergroundBiomes.common.block.BlockMetadataBase;
import exterminatorJeff.undergroundBiomes.common.item.SwitchableRecipeGroup;
import exterminatorJeff.undergroundBiomes.constructs.IconTrap;
import exterminatorJeff.undergroundBiomes.constructs.block.UBButtonBlockGroup;
import exterminatorJeff.undergroundBiomes.constructs.block.UBStairsBlockGroup;
import exterminatorJeff.undergroundBiomes.constructs.block.UBTEButtonGroup;
import exterminatorJeff.undergroundBiomes.constructs.block.UBTEStairsGroup;
import exterminatorJeff.undergroundBiomes.constructs.block.UBTEWallGroup;
import exterminatorJeff.undergroundBiomes.constructs.block.UBWallBlockGroup;
import exterminatorJeff.undergroundBiomes.constructs.entity.UndergroundBiomesTileEntity;
import exterminatorJeff.undergroundBiomes.constructs.util.UndergroundBiomesBlockList;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import net.minecraft.block.Block;
import net.minecraft.block.BlockButtonStone;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockWall;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.common.config.Configuration;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class UndergroundBiomesConstructs {
    public static int subdivisionBlockCount = 4;
    private UBStairsBlockGroup stoneStair;
    private UBTEWallGroup stoneWall;
    private UBWallBlockGroup blockWall;
    private UBTEButtonGroup oldButton;
    private UBButtonBlockGroup stoneButton;
    private BlockMetadataBase iconTrap;
    public Configuration config;
    private UndergroundBiomesBlockList ubBlockList;
    public static String blockCategory = "block";
    public static String itemCategory = "item";
    private SwitchableRecipeGroup stairRecipes;
    private SwitchableRecipeGroup wallRecipes;
    private SwitchableRecipeGroup buttonRecipes;

    private UndergroundBiomesSettings settings() {
        return UndergroundBiomes.instance().settings();
    }

    public int stoneStairID() {
        return this.settings().stoneStairID.value();
    }

    public UBStairsBlockGroup stoneStair() {
        return this.stoneStair;
    }

    public int stoneWallID() {
        return this.settings().stoneWallID.value();
    }

    public UBTEWallGroup stoneWall() {
        return this.stoneWall;
    }

    public int stoneButtonID() {
        return this.settings().stoneButtonID.value();
    }

    public UBButtonBlockGroup stoneButton() {
        return this.stoneButton;
    }

    public void preInit(Configuration config) {
        this.config = config;
        this.ubBlockList = new UndergroundBiomesBlockList();
        GameRegistry.registerTileEntity((Class)UndergroundBiomesTileEntity.class, (String)"UndergroundBiomesTileEntity");
        this.iconTrap = new IconTrap();
        this.preInitButtons();
        this.preInitStairs();
        this.preInitWalls();
    }

    public void preInitStairs() {
        UBTEStairsGroup oldStairs = new UBTEStairsGroup();
        oldStairs.baseBlock = this.ubBlockList.sedimentaryStone;
        oldStairs.define(this.stoneStairID());
        this.stoneStair = new UBStairsBlockGroup();
        this.stoneStair.baseBlock = this.iconTrap;
        this.stoneStair.define(this.stoneStairID());
    }

    public void preInitWalls() {
        this.stoneWall = new UBTEWallGroup();
        this.stoneWall.baseBlock = this.ubBlockList.sedimentaryStone;
        this.stoneWall.define(this.stoneWallID());
        this.blockWall = new UBWallBlockGroup();
        this.blockWall.define();
    }

    public void preInitButtons() {
        this.oldButton = new UBTEButtonGroup();
        this.oldButton.baseBlock = this.ubBlockList.sedimentaryStone;
        this.oldButton.define(this.stoneButtonID());
        this.stoneButton = new UBButtonBlockGroup();
        this.stoneButton.baseBlock = this.iconTrap;
        this.stoneButton.define(this.stoneStairID());
    }

    public ArrayList<BlockMetadataBase> baseBlocks() {
        ArrayList<BlockMetadataBase> result = new ArrayList<BlockMetadataBase>();
        result.add(UndergroundBiomes.igneousStone);
        System.out.println(UndergroundBiomes.igneousStone.func_149739_a());
        result.add(UndergroundBiomes.igneousCobblestone);
        result.add(UndergroundBiomes.igneousStoneBrick);
        result.add(UndergroundBiomes.metamorphicStone);
        result.add(UndergroundBiomes.metamorphicCobblestone);
        result.add(UndergroundBiomes.metamorphicStoneBrick);
        result.add(UndergroundBiomes.sedimentaryStone);
        return result;
    }

    public void load(FMLInitializationEvent event) {
        this.loadButtons();
        this.loadStairs();
        this.loadWalls();
    }

    private void loadStairs() {
        this.stairRecipes = new SwitchableRecipeGroup(this.stoneStair.recipes(), this.settings().stairsOn);
    }

    private void loadWalls() {
        this.wallRecipes = new SwitchableRecipeGroup(this.blockWall.recipes(), this.settings().wallsOn);
    }

    private void loadButtons() {
        this.buttonRecipes = new SwitchableRecipeGroup(this.stoneButton.recipes(), this.settings().buttonsOn);
    }

    public void postInit(FMLPostInitializationEvent event) throws Exception {
    }

    public static boolean overridesRecipe(IRecipe recipe) {
        if (recipe == null) {
            return false;
        }
        Item output = null;
        try {
            output = recipe.func_77571_b().func_77973_b();
        }
        catch (Exception e) {
            return false;
        }
        if (output == null) {
            return false;
        }
        if (!(output instanceof ItemBlock)) {
            return false;
        }
        Block blockMade = ((ItemBlock)output).field_150939_a;
        if (UndergroundBiomes.buttonsOn() && blockMade instanceof BlockButtonStone) {
            return true;
        }
        if (UndergroundBiomes.stairsOn() && blockMade instanceof BlockStairs) {
            return true;
        }
        if (UndergroundBiomes.wallsOn() && blockMade instanceof BlockWall) {
            return true;
        }
        if (blockMade instanceof BlockSlab) {
            return !blockMade.func_149662_c();
        }
        return false;
    }
}

