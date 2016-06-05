/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 */
package exterminatorJeff.undergroundBiomes.constructs.util;

import exterminatorJeff.undergroundBiomes.common.block.BlockMetadataBase;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class UndergroundBiomesBlock {
    public final BlockMetadataBase ubBlock;
    public final int metadata;
    public final int index;

    public UndergroundBiomesBlock(BlockMetadataBase _ubBlock, int _metadata, int _index) {
        this.ubBlock = _ubBlock;
        this.metadata = _metadata;
        this.index = _index;
    }

    public final IIcon icon() {
        return this.ubBlock.func_149691_a(2, this.metadata);
    }

    public final String name() {
        return this.ubBlock.getBlockName(this.metadata);
    }

    public final float hardness() {
        return this.ubBlock.getBlockHardness(this.metadata);
    }

    public final float explosionResistance() {
        return this.ubBlock.getBlockExplosionResistance(this.metadata);
    }

    public final ItemStack stack(int items) {
        return new ItemStack((Block)this.ubBlock, items, this.metadata);
    }

    public final ItemStack one() {
        return this.stack(1);
    }

    public final String getUnlocalizedName() {
        return this.ubBlock.func_149739_a() + "." + this.ubBlock.getBlockTypeName(this.metadata);
    }
}

