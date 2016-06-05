/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockSlab
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemSlab
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 */
package exterminatorJeff.undergroundBiomes.common.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import exterminatorJeff.undergroundBiomes.common.block.BlockMetadataBase;
import exterminatorJeff.undergroundBiomes.common.block.BlockStoneSlab;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ItemMetadataSlab
extends ItemSlab {
    private final BlockMetadataBase referenceBlock;
    private final boolean isDouble;

    public static BlockStoneSlab slab(Block block) {
        return (BlockStoneSlab)block;
    }

    public static BlockSlab singleSlab(Block one, Block two) {
        if (ItemMetadataSlab.slab(one).isDoubleSlab()) {
            return (BlockSlab)two;
        }
        return (BlockSlab)one;
    }

    public static BlockSlab doubleSlab(Block one, Block two) {
        if (ItemMetadataSlab.slab(one).isDoubleSlab()) {
            return (BlockSlab)one;
        }
        return (BlockSlab)two;
    }

    public static BlockMetadataBase stone(Block block) {
        return ItemMetadataSlab.slab((Block)block).referenceBlock;
    }

    public ItemMetadataSlab(Block slab, BlockStoneSlab otherSlab) {
        super(slab, ItemMetadataSlab.singleSlab(slab, (Block)otherSlab), ItemMetadataSlab.doubleSlab(slab, (Block)otherSlab), ItemMetadataSlab.slab(slab).isDoubleSlab());
        this.referenceBlock = ItemMetadataSlab.stone(slab);
        this.isDouble = ItemMetadataSlab.slab(slab).isDoubleSlab();
    }

    public void func_150895_a(Item p_150895_1_, CreativeTabs p_150895_2_, List p_150895_3_) {
        if (!this.isDouble) {
            super.func_150895_a(p_150895_1_, p_150895_2_, p_150895_3_);
        }
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int meta) {
        return this.referenceBlock.func_149691_a(2, meta);
    }

    public String func_77667_c(ItemStack arg0) {
        return super.func_77667_c(arg0);
    }
}

