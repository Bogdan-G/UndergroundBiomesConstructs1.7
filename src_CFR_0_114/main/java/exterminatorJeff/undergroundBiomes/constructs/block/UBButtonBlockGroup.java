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
import exterminatorJeff.undergroundBiomes.constructs.block.UBButton;
import exterminatorJeff.undergroundBiomes.constructs.block.UBButtonGroup;
import exterminatorJeff.undergroundBiomes.constructs.block.UBConstructGroup;
import exterminatorJeff.undergroundBiomes.constructs.item.ItemUBButtonBlock;
import exterminatorJeff.undergroundBiomes.constructs.util.UndergroundBiomesBlock;
import exterminatorJeff.undergroundBiomes.constructs.util.UndergroundBiomesBlockList;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class UBButtonBlockGroup
extends UBButtonGroup {
    private UBButtonSet igneousStoneButtonSet;
    private UBButtonSet igneousCobblestoneButtonSet;
    private UBButtonSet igneousStoneBrickButtonSet;
    private UBButtonSet metamorphicStoneButtonSet;
    private UBButtonSet metamorphicCobblestoneButtonSet;
    private UBButtonSet metamorphicStoneBrickButtonSet;
    private UBButtonSet sedimentaryStoneButtonSet;

    public void define(int _constructID) {
        this.define();
    }

    public void define() {
        this.igneousStoneButtonSet = this.createButtonSet(UndergroundBiomes.igneousStone);
        this.igneousCobblestoneButtonSet = this.createButtonSet(UndergroundBiomes.igneousCobblestone);
        this.igneousStoneBrickButtonSet = this.createButtonSet(UndergroundBiomes.igneousStoneBrick);
        this.metamorphicStoneButtonSet = this.createButtonSet(UndergroundBiomes.metamorphicStone);
        this.metamorphicCobblestoneButtonSet = this.createButtonSet(UndergroundBiomes.metamorphicCobblestone);
        this.metamorphicStoneBrickButtonSet = this.createButtonSet(UndergroundBiomes.metamorphicStoneBrick);
        this.sedimentaryStoneButtonSet = this.createButtonSet(UndergroundBiomes.sedimentaryStone);
    }

    private UBButtonSet createButtonSet(BlockMetadataBase sourceBlock) {
        return new UBButtonSet(sourceBlock);
    }

    private UBButtonSet ButtonSetFor(UndergroundBiomesBlock base) {
        if (base.ubBlock == UndergroundBiomes.igneousStone) {
            return this.igneousStoneButtonSet;
        }
        if (base.ubBlock == UndergroundBiomes.igneousCobblestone) {
            return this.igneousCobblestoneButtonSet;
        }
        if (base.ubBlock == UndergroundBiomes.igneousStoneBrick) {
            return this.igneousStoneBrickButtonSet;
        }
        if (base.ubBlock == UndergroundBiomes.metamorphicStone) {
            return this.metamorphicStoneButtonSet;
        }
        if (base.ubBlock == UndergroundBiomes.metamorphicCobblestone) {
            return this.metamorphicCobblestoneButtonSet;
        }
        if (base.ubBlock == UndergroundBiomes.metamorphicStoneBrick) {
            return this.metamorphicStoneBrickButtonSet;
        }
        if (base.ubBlock == UndergroundBiomes.sedimentaryStone) {
            return this.sedimentaryStoneButtonSet;
        }
        throw new RuntimeException(base.ubBlock.func_149739_a());
    }

    public UBConstructGroup.ProductItemDefiner productItemDefiner(int index) {
        return new BlockProductItemDefiner(index);
    }

    private static class UBButtonSet {
        UBButton[] buttons = new UBButton[8];

        public UBButtonSet(BlockMetadataBase sourceBlock) {
            this.buttons[0] = this.createButton(sourceBlock, 0);
            this.buttons[1] = this.createButton(sourceBlock, 1);
            this.buttons[2] = this.createButton(sourceBlock, 2);
            this.buttons[3] = this.createButton(sourceBlock, 3);
            this.buttons[4] = this.createButton(sourceBlock, 4);
            this.buttons[5] = this.createButton(sourceBlock, 5);
            this.buttons[6] = this.createButton(sourceBlock, 6);
            this.buttons[7] = this.createButton(sourceBlock, 7);
        }

        private UBButton createButton(BlockMetadataBase sourceBlock, int lowerMetadata) {
            NamedBlock createdNamer = new NamedBlock(UBIDs.UBButtonName.internal() + "." + sourceBlock.func_149739_a() + lowerMetadata);
            UBButton created = new UBButton(sourceBlock, lowerMetadata);
            createdNamer.gameRegister((Block)created, ItemUBButtonBlock.class);
            return created;
        }
    }

    class BlockProductItemDefiner
    extends UBConstructGroup.ProductItemDefiner {
        BlockProductItemDefiner(int _stoneIndex) {
            super(UBButtonBlockGroup.this, _stoneIndex);
        }

        private final UndergroundBiomesBlock ubBlock() {
            return UndergroundBiomesBlockList.indexed(this.stoneIndex);
        }

        private Block product() {
            UBButtonSet ButtonSet = UBButtonBlockGroup.this.ButtonSetFor(this.ubBlock());
            if (this.ubBlock().metadata < 8) {
                return ButtonSet.buttons[this.ubBlock().metadata];
            }
            throw new RuntimeException();
        }

        private int productMetadata() {
            return this.ubBlock().metadata;
        }

        public final ItemStack stackOf(int items) {
            return new ItemStack(this.product(), items, this.productMetadata());
        }
    }

}

