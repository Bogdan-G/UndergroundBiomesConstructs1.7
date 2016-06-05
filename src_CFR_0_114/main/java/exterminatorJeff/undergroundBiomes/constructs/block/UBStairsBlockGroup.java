/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.item.ItemStack
 */
package exterminatorJeff.undergroundBiomes.constructs.block;

import exterminatorJeff.undergroundBiomes.api.NamedBlock;
import exterminatorJeff.undergroundBiomes.api.UBIDs;
import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;
import exterminatorJeff.undergroundBiomes.common.block.BlockMetadataBase;
import exterminatorJeff.undergroundBiomes.constructs.block.UBConstructGroup;
import exterminatorJeff.undergroundBiomes.constructs.block.UBStairs;
import exterminatorJeff.undergroundBiomes.constructs.block.UBStairsGroup;
import exterminatorJeff.undergroundBiomes.constructs.item.ItemUBStairsBlock;
import exterminatorJeff.undergroundBiomes.constructs.util.UndergroundBiomesBlock;
import exterminatorJeff.undergroundBiomes.constructs.util.UndergroundBiomesBlockList;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class UBStairsBlockGroup
extends UBStairsGroup {
    private UBStairsSet igneousStoneStairsSet;
    private UBStairsSet igneousCobblestoneStairsSet;
    private UBStairsSet igneousStoneBrickStairsSet;
    private UBStairsSet metamorphicStoneStairsSet;
    private UBStairsSet metamorphicCobblestoneStairsSet;
    private UBStairsSet metamorphicStoneBrickStairsSet;
    private UBStairsSet sedimentaryStoneStairsSet;

    public void define(int _constructID) {
        this.define();
    }

    public void define() {
        this.igneousStoneStairsSet = this.createStairsSet(UndergroundBiomes.igneousStone);
        this.igneousCobblestoneStairsSet = this.createStairsSet(UndergroundBiomes.igneousCobblestone);
        this.igneousStoneBrickStairsSet = this.createStairsSet(UndergroundBiomes.igneousStoneBrick);
        this.metamorphicStoneStairsSet = this.createStairsSet(UndergroundBiomes.metamorphicStone);
        this.metamorphicCobblestoneStairsSet = this.createStairsSet(UndergroundBiomes.metamorphicCobblestone);
        this.metamorphicStoneBrickStairsSet = this.createStairsSet(UndergroundBiomes.metamorphicStoneBrick);
        this.sedimentaryStoneStairsSet = this.createStairsSet(UndergroundBiomes.sedimentaryStone);
    }

    private UBStairsSet createStairsSet(BlockMetadataBase sourceBlock) {
        return new UBStairsSet(sourceBlock);
    }

    private UBStairsSet stairsSetFor(UndergroundBiomesBlock base) {
        if (base.ubBlock == UndergroundBiomes.igneousStone) {
            return this.igneousStoneStairsSet;
        }
        if (base.ubBlock == UndergroundBiomes.igneousCobblestone) {
            return this.igneousCobblestoneStairsSet;
        }
        if (base.ubBlock == UndergroundBiomes.igneousStoneBrick) {
            return this.igneousStoneBrickStairsSet;
        }
        if (base.ubBlock == UndergroundBiomes.metamorphicStone) {
            return this.metamorphicStoneStairsSet;
        }
        if (base.ubBlock == UndergroundBiomes.metamorphicCobblestone) {
            return this.metamorphicCobblestoneStairsSet;
        }
        if (base.ubBlock == UndergroundBiomes.metamorphicStoneBrick) {
            return this.metamorphicStoneBrickStairsSet;
        }
        if (base.ubBlock == UndergroundBiomes.sedimentaryStone) {
            return this.sedimentaryStoneStairsSet;
        }
        throw new RuntimeException(base.ubBlock.func_149739_a());
    }

    public UBConstructGroup.ProductItemDefiner productItemDefiner(int index) {
        return new BlockProductItemDefiner(index);
    }

    private static class UBStairsSet {
        UBStairs zeroOne;
        UBStairs twoThree;
        UBStairs fourFive;
        UBStairs sixSeven;

        public UBStairsSet(BlockMetadataBase sourceBlock) {
            this.zeroOne = this.createStairs(sourceBlock, 0);
            this.twoThree = this.createStairs(sourceBlock, 2);
            this.fourFive = this.createStairs(sourceBlock, 4);
            this.sixSeven = this.createStairs(sourceBlock, 6);
        }

        private UBStairs createStairs(BlockMetadataBase sourceBlock, int lowerMetadata) {
            NamedBlock createdNamer = new NamedBlock(UBIDs.UBStairsName.internal() + "." + sourceBlock.func_149739_a() + lowerMetadata / 2);
            UBStairs created = new UBStairs(sourceBlock, lowerMetadata);
            createdNamer.gameRegister((Block)created, ItemUBStairsBlock.class);
            return created;
        }
    }

    class BlockProductItemDefiner
    extends UBConstructGroup.ProductItemDefiner {
        BlockProductItemDefiner(int _stoneIndex) {
            super(_stoneIndex);
        }

        private final UndergroundBiomesBlock ubBlock() {
            return UndergroundBiomesBlockList.indexed(this.stoneIndex);
        }

        private Block product() {
            UBStairsSet stairsSet = UBStairsBlockGroup.this.stairsSetFor(this.ubBlock());
            if (this.ubBlock().metadata < 2) {
                return stairsSet.zeroOne;
            }
            if (this.ubBlock().metadata < 4) {
                return stairsSet.twoThree;
            }
            if (this.ubBlock().metadata < 6) {
                return stairsSet.fourFive;
            }
            if (this.ubBlock().metadata < 8) {
                return stairsSet.sixSeven;
            }
            throw new RuntimeException();
        }

        private int productMetadata() {
            int result = this.ubBlock().metadata % 2 * 8;
            if (result != 0 && result != 8) {
                throw new RuntimeException();
            }
            return this.ubBlock().metadata % 2 * 8;
        }

        public final ItemStack stackOf(int items) {
            return new ItemStack(this.product(), items, this.productMetadata());
        }
    }

}

