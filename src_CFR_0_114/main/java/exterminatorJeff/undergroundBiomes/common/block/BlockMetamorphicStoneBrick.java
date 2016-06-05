/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.item.Item
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
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BlockMetamorphicStoneBrick
extends BlockMetamorphicStone {
    final float baseHardness;
    private final Acceptor<Double> hardnessUpdater;

    public BlockMetamorphicStoneBrick() {
        super(UBIDs.metamorphicStoneBrickName);
        this.hardnessUpdater = new Acceptor<Double>(){

            @Override
            public void accept(Double accepted) {
                BlockMetamorphicStoneBrick.this.func_149711_c(BlockMetamorphicStoneBrick.this.baseHardness * accepted.floatValue());
            }
        };
        this.replaceableByOre = false;
        this.baseHardness = this.field_149782_v;
        UndergroundBiomes.instance().settings().brickHardnessMultiplier.informOnChange(this.hardnessUpdater);
        this.hardnessUpdater.accept(UndergroundBiomes.instance().settings().brickHardnessMultiplier.value());
    }

    public ItemStack itemDropped(int metadata, Random random, int fortune, int y) {
        return new ItemStack(UBIDs.metamorphicStoneBrickName.block(), 1, metadata & 7);
    }

    public boolean hasRareDrops() {
        return false;
    }

    public String getBlockName(int index) {
        return super.getBlockName(index) + "Brick";
    }

    public Item func_149650_a(int metadata, Random random, int fortune) {
        return Item.func_150899_d((int)UBIDs.metamorphicStoneBrickName.ID());
    }

}

