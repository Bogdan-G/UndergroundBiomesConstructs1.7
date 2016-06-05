/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.item.Item
 *  net.minecraft.util.RegistryNamespaced
 */
package exterminatorJeff.undergroundBiomes.api;

import exterminatorJeff.undergroundBiomes.api.NamedBlock;
import exterminatorJeff.undergroundBiomes.api.NamedItem;
import exterminatorJeff.undergroundBiomes.api.NamedSlabPair;
import exterminatorJeff.undergroundBiomes.api.NamedVanillaBlock;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.RegistryNamespaced;

public class UBIDs {
    public static final int version = 3;
    public static final NamedBlock igneousStoneName = new NamedBlock("igneousStone");
    public static final NamedBlock igneousCobblestoneName = new NamedBlock("igneousCobblestone");
    public static final NamedBlock igneousStoneBrickName = new NamedBlock("igneousStoneBrick");
    public static final NamedBlock metamorphicStoneName = new NamedBlock("metamorphicStone");
    public static final NamedBlock metamorphicCobblestoneName = new NamedBlock("metamorphicCobblestone");
    public static final NamedBlock metamorphicStoneBrickName = new NamedBlock("metamorphicStoneBrick");
    public static final NamedBlock sedimentaryStoneName = new NamedBlock("sedimentaryStone");
    public static final NamedItem ligniteCoalName = new NamedItem("ligniteCoal");
    public static final NamedItem fossilPieceName = new NamedItem("fossilPiece");
    public static final NamedSlabPair igneousBrickSlabName = new NamedSlabPair(igneousStoneBrickName);
    public static final NamedSlabPair metamorphicBrickSlabName = new NamedSlabPair(metamorphicStoneBrickName);
    public static final NamedSlabPair igneousStoneSlabName = new NamedSlabPair(igneousStoneName);
    public static final NamedSlabPair metamorphicStoneSlabName = new NamedSlabPair(metamorphicStoneName);
    public static final NamedSlabPair igneousCobblestoneSlabName = new NamedSlabPair(igneousCobblestoneName);
    public static final NamedSlabPair metamorphicCobblestoneSlabName = new NamedSlabPair(metamorphicCobblestoneName);
    public static final NamedSlabPair sedimentaryStoneSlabName = new NamedSlabPair(sedimentaryStoneName);
    public static final NamedBlock UBButtonName = new NamedBlock("button");
    public static final NamedBlock UBStairsName = new NamedBlock("stairs");
    public static final NamedBlock UBWallsName = new NamedBlock("wall");
    public static final NamedBlock UBWallsBlockName = new NamedBlock("wallBlock");
    public static final NamedItem UBButtonItemName = new NamedItem(UBButtonName);
    public static final NamedItem UBStairsItemName = new NamedItem(UBStairsName);
    public static final NamedItem UBWallsItemName = new NamedItem(UBWallsName);
    public static final NamedBlock IconTrap = new NamedBlock("iconTrap");

    public static final String ubPrefix() {
        return "UndergroundBiomes:";
    }

    public static final String ubIconPrefix() {
        return "undergroundbiomes:";
    }

    public static String publicName(String inModName) {
        if (inModName.contains(UBIDs.ubPrefix())) {
            return inModName;
        }
        return UBIDs.ubPrefix() + inModName;
    }

    public static String iconName(String inModName) {
        if (inModName.contains(UBIDs.ubIconPrefix())) {
            return inModName;
        }
        return UBIDs.ubIconPrefix() + inModName;
    }

    public static Item itemNamed(String name) {
        return (Item)Item.field_150901_e.func_82594_a(name);
    }

    public static Block blockNamed(String name) {
        return Block.func_149684_b((String)name);
    }

    public static int itemID(String name) {
        return Item.func_150891_b((Item)UBIDs.itemNamed(name));
    }

    public static int blockID(String name) {
        return Block.func_149682_b((Block)Block.func_149684_b((String)name));
    }

    public static NamedBlock slabVersionID(NamedBlock ubStone) {
        if (ubStone == igneousStoneName) {
            return UBIDs.igneousStoneSlabName.half;
        }
        if (ubStone == igneousCobblestoneName) {
            return UBIDs.igneousCobblestoneSlabName.half;
        }
        if (ubStone == igneousStoneBrickName) {
            return UBIDs.igneousBrickSlabName.half;
        }
        if (ubStone == metamorphicStoneName) {
            return UBIDs.metamorphicStoneSlabName.half;
        }
        if (ubStone == metamorphicCobblestoneName) {
            return UBIDs.metamorphicCobblestoneSlabName.half;
        }
        if (ubStone == metamorphicStoneBrickName) {
            return UBIDs.metamorphicBrickSlabName.half;
        }
        if (ubStone == sedimentaryStoneName) {
            return UBIDs.sedimentaryStoneSlabName.half;
        }
        if (ubStone == NamedVanillaBlock.sandstone) {
            return NamedVanillaBlock.stoneSingleSlab;
        }
        if (ubStone == NamedVanillaBlock.stone) {
            return NamedVanillaBlock.stoneSingleSlab;
        }
        if (ubStone == NamedVanillaBlock.cobblestone) {
            return NamedVanillaBlock.stoneSingleSlab;
        }
        if (ubStone == NamedVanillaBlock.sand) {
            return NamedVanillaBlock.stoneSingleSlab;
        }
        throw new RuntimeException("" + ubStone + " is not not usable as an Underground Biomes stone code");
    }

    public static NamedBlock brickVersionID(NamedBlock ubStone) {
        if (ubStone == igneousStoneName) {
            return igneousStoneBrickName;
        }
        if (ubStone == igneousCobblestoneName) {
            return igneousStoneBrickName;
        }
        if (ubStone == igneousStoneBrickName) {
            return igneousStoneBrickName;
        }
        if (ubStone == metamorphicStoneName) {
            return metamorphicStoneBrickName;
        }
        if (ubStone == metamorphicCobblestoneName) {
            return metamorphicStoneBrickName;
        }
        if (ubStone == metamorphicStoneBrickName) {
            return metamorphicStoneBrickName;
        }
        if (ubStone == sedimentaryStoneName) {
            return sedimentaryStoneName;
        }
        if (ubStone == NamedVanillaBlock.sandstone) {
            return NamedVanillaBlock.smoothSandstone;
        }
        if (ubStone == NamedVanillaBlock.stone) {
            return NamedVanillaBlock.stoneBrick;
        }
        if (ubStone == NamedVanillaBlock.cobblestone) {
            return NamedVanillaBlock.stoneBrick;
        }
        if (ubStone == NamedVanillaBlock.sand) {
            return NamedVanillaBlock.sandstone;
        }
        throw new RuntimeException("" + ubStone + " is not usable as an Underground Biomes stone code");
    }
}

