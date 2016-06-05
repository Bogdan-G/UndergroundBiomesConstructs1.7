/*
 * Decompiled with CFR 0_114.
 */
package exterminatorJeff.undergroundBiomes.common.block;

import exterminatorJeff.undergroundBiomes.common.block.BlockStoneSlab;

public class StoneSlabPair {
    public BlockStoneSlab half;
    public BlockStoneSlab full;

    public StoneSlabPair(BlockStoneSlab half, BlockStoneSlab full) {
        this.half = half;
        this.full = full;
        if (half.isDoubleSlab) {
            throw new RuntimeException();
        }
        if (!full.isDoubleSlab) {
            throw new RuntimeException();
        }
    }
}

