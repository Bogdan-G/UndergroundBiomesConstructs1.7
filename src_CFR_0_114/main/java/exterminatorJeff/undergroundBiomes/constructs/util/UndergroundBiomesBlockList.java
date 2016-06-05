/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 */
package exterminatorJeff.undergroundBiomes.constructs.util;

import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;
import exterminatorJeff.undergroundBiomes.common.block.BlockMetadataBase;
import exterminatorJeff.undergroundBiomes.constructs.util.UndergroundBiomesBlock;
import net.minecraft.block.Block;

public class UndergroundBiomesBlockList {
    public static final int detailedBlockCount = 56;
    private static UndergroundBiomesBlock[] stored;
    public final BlockMetadataBase igneousStone = UndergroundBiomes.igneousStone;
    public final BlockMetadataBase igneousCobblestone = UndergroundBiomes.igneousCobblestone;
    public final BlockMetadataBase igneousStoneBrick = UndergroundBiomes.igneousStoneBrick;
    public final BlockMetadataBase metamorphicStone = UndergroundBiomes.metamorphicStone;
    public final BlockMetadataBase metamorphicCobblestone = UndergroundBiomes.metamorphicCobblestone;
    public final BlockMetadataBase metamorphicStoneBrick = UndergroundBiomes.metamorphicStoneBrick;
    public final BlockMetadataBase sedimentaryStone = UndergroundBiomes.sedimentaryStone;
    public UndergroundBiomesBlock[] definitionsIndex;

    private UndergroundBiomesBlock[] definitions() {
        int j;
        UndergroundBiomesBlock[] result = new UndergroundBiomesBlock[56];
        int i = 0;
        for (j = 0; j < 8; ++j) {
            result[i] = new UndergroundBiomesBlock(this.metamorphicStone, j, i);
            ++i;
        }
        for (j = 0; j < 8; ++j) {
            result[i] = new UndergroundBiomesBlock(this.metamorphicCobblestone, j, i);
            ++i;
        }
        for (j = 0; j < 8; ++j) {
            result[i] = new UndergroundBiomesBlock(this.metamorphicStoneBrick, j, i);
            ++i;
        }
        for (j = 0; j < 8; ++j) {
            result[i] = new UndergroundBiomesBlock(this.igneousStone, j, i);
            ++i;
        }
        for (j = 0; j < 8; ++j) {
            result[i] = new UndergroundBiomesBlock(this.igneousCobblestone, j, i);
            ++i;
        }
        for (j = 0; j < 8; ++j) {
            result[i] = new UndergroundBiomesBlock(this.igneousStoneBrick, j, i);
            ++i;
        }
        for (j = 0; j < 8; ++j) {
            result[i] = new UndergroundBiomesBlock(this.sedimentaryStone, j, i);
            ++i;
        }
        return result;
    }

    public static BlockMetadataBase blockMetadataBaseNamed(String name) {
        return (BlockMetadataBase)Block.func_149684_b((String)name);
    }

    public static UndergroundBiomesBlock indexed(int index) {
        return stored[index];
    }

    public UndergroundBiomesBlockList() {
        this.definitionsIndex = this.definitions();
        if (stored != null) {
            throw new RuntimeException();
        }
        stored = this.definitionsIndex;
    }
}

