/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.item.ItemStack
 */
package exterminatorJeff.undergroundBiomes.common.block;

import Zeno410Utils.Acceptor;
import Zeno410Utils.Mutable;
import exterminatorJeff.undergroundBiomes.api.NamedBlock;
import exterminatorJeff.undergroundBiomes.api.UBIDs;
import exterminatorJeff.undergroundBiomes.api.UndergroundBiomesSettings;
import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;
import exterminatorJeff.undergroundBiomes.common.block.BlockMetamorphicStone;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class BlockMetamorphicCobblestone
extends BlockMetamorphicStone {
    final float baseHardness;
    private final Acceptor<Double> hardnessUpdater;

    public BlockMetamorphicCobblestone() {
        super(UBIDs.metamorphicCobblestoneName);
        this.hardnessUpdater = new Acceptor<Double>(){

            @Override
            public void accept(Double accepted) {
                BlockMetamorphicCobblestone.this.func_149711_c(BlockMetamorphicCobblestone.this.baseHardness * accepted.floatValue());
            }
        };
        this.baseHardness = this.field_149782_v;
        this.func_149711_c(this.field_149782_v * 1.333333f);
        this.replaceableByOre = false;
        UndergroundBiomes.instance().settings().cobbleHardnessMultiplier.informOnChange(this.hardnessUpdater);
        this.hardnessUpdater.accept(UndergroundBiomes.instance().settings().cobbleHardnessMultiplier.value());
    }

    public ItemStack itemDropped(int metadata, Random random, int fortune, int y) {
        return new ItemStack(UBIDs.metamorphicCobblestoneName.block(), 1, metadata & 7);
    }

    public boolean hasRareDrops() {
        return false;
    }

    public String getBlockName(int index) {
        return super.getBlockName(index) + "Cobble";
    }

}

