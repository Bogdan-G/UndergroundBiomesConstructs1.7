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
import exterminatorJeff.undergroundBiomes.constructs.block.UBWall;
import exterminatorJeff.undergroundBiomes.constructs.block.UBWallGroup;
import exterminatorJeff.undergroundBiomes.constructs.item.ItemUBWallBlock;
import exterminatorJeff.undergroundBiomes.constructs.util.UndergroundBiomesBlock;
import exterminatorJeff.undergroundBiomes.constructs.util.UndergroundBiomesBlockList;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class UBWallBlockGroup
extends UBWallGroup {
    private UBWall igneousStoneWall;
    private UBWall igneousCobblestoneWall;
    private UBWall igneousStoneBrickWall;
    private UBWall metamorphicStoneWall;
    private UBWall metamorphicCobblestoneWall;
    private UBWall metamorphicStoneBrickWall;
    private UBWall sedimentaryStoneWall;

    public void define() {
        this.igneousStoneWall = this.createWall(UndergroundBiomes.igneousStone);
        this.igneousCobblestoneWall = this.createWall(UndergroundBiomes.igneousCobblestone);
        this.igneousStoneBrickWall = this.createWall(UndergroundBiomes.igneousStoneBrick);
        this.metamorphicStoneWall = this.createWall(UndergroundBiomes.metamorphicStone);
        this.metamorphicCobblestoneWall = this.createWall(UndergroundBiomes.metamorphicCobblestone);
        this.metamorphicStoneBrickWall = this.createWall(UndergroundBiomes.metamorphicStoneBrick);
        this.sedimentaryStoneWall = this.createWall(UndergroundBiomes.sedimentaryStone);
    }

    private Block wallFor(UndergroundBiomesBlock base) {
        if (base.ubBlock == UndergroundBiomes.igneousStone) {
            return this.igneousStoneWall;
        }
        if (base.ubBlock == UndergroundBiomes.igneousCobblestone) {
            return this.igneousCobblestoneWall;
        }
        if (base.ubBlock == UndergroundBiomes.igneousStoneBrick) {
            return this.igneousStoneBrickWall;
        }
        if (base.ubBlock == UndergroundBiomes.metamorphicStone) {
            return this.metamorphicStoneWall;
        }
        if (base.ubBlock == UndergroundBiomes.metamorphicCobblestone) {
            return this.metamorphicCobblestoneWall;
        }
        if (base.ubBlock == UndergroundBiomes.metamorphicStoneBrick) {
            return this.metamorphicStoneBrickWall;
        }
        if (base.ubBlock == UndergroundBiomes.sedimentaryStone) {
            return this.sedimentaryStoneWall;
        }
        throw new RuntimeException(base.ubBlock.func_149739_a());
    }

    private UBWall createWall(BlockMetadataBase sourceBlock) {
        NamedBlock createdNamer = new NamedBlock(UBIDs.UBWallsName.internal() + "." + sourceBlock.func_149739_a());
        UBWall created = new UBWall(sourceBlock);
        createdNamer.gameRegister((Block)created, ItemUBWallBlock.class);
        return created;
    }

    public UBConstructGroup.ProductItemDefiner productItemDefiner(int index) {
        return new BlockProductItemDefiner(index);
    }

    class BlockProductItemDefiner
    extends UBConstructGroup.ProductItemDefiner {
        BlockProductItemDefiner(int _stoneIndex) {
            super(_stoneIndex);
        }

        private UndergroundBiomesBlock ubBlock() {
            return UndergroundBiomesBlockList.indexed(this.stoneIndex);
        }

        private Block product() {
            return UBWallBlockGroup.this.wallFor(this.ubBlock());
        }

        private int productMetadata() {
            return this.ubBlock().metadata;
        }

        public final ItemStack stackOf(int items) {
            return new ItemStack(this.product(), items, this.productMetadata());
        }
    }

}

