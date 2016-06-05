/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockWall
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package exterminatorJeff.undergroundBiomes.constructs.block;

import Zeno410Utils.Zeno410Logger;
import exterminatorJeff.undergroundBiomes.common.CreativeTabModBlocks;
import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;
import exterminatorJeff.undergroundBiomes.common.block.BlockMetadataBase;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
import net.minecraft.block.Block;
import net.minecraft.block.BlockWall;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class UBWall
extends BlockWall {
    private int storedID;
    public static Logger logger = new Zeno410Logger("UBWallBase").logger();
    private final BlockMetadataBase baseBlock;

    public UBWall(BlockMetadataBase _baseBlock) {
        super((Block)_baseBlock);
        this.baseBlock = _baseBlock;
        this.field_149758_A = false;
        this.func_149647_a((CreativeTabs)UndergroundBiomes.tabModBlocks);
        this.func_149663_c("wall");
    }

    public void func_149651_a(IIconRegister arg0) {
    }

    public BlockMetadataBase baseBlock() {
        return this.baseBlock;
    }

    public void func_149666_a(Item item, CreativeTabs tabs, List list) {
        if (!UndergroundBiomes.wallsOn()) {
            return;
        }
        for (int i = 0; i < 8; ++i) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    public int func_149643_k(World world, int x, int y, int z) {
        return world.func_72805_g(x, y, z);
    }

    public float func_149712_f(World world, int x, int y, int z) {
        return this.baseBlock.func_149712_f(world, x, y, z);
    }

    public IIcon func_149691_a(int side, int metadata) {
        return this.baseBlock.func_149691_a(side, metadata);
    }

    public IIcon func_149673_e(IBlockAccess world, int x, int y, int z, int side) {
        return this.baseBlock.func_149673_e(world, x, y, z, side);
    }

    public boolean isReplaceableOreGen(World world, int x, int y, int z, Block target) {
        return false;
    }

    public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ) {
        return this.baseBlock.getExplosionResistance(par1Entity, world, x, y, z, explosionX, explosionY, explosionZ);
    }

    public int func_149645_b() {
        return 32;
    }

    public int func_149692_a(int metadata) {
        return metadata;
    }

    public ItemStack itemDropped(int metadata, Random random, int fortune, int y) {
        return new ItemStack((Block)this, 1, metadata);
    }

    public String getBlockName(int meta) {
        return this.baseBlock.getBlockName(meta);
    }

    public boolean canPlaceTorchOnTop(World world, int x, int y, int z) {
        return true;
    }
}

