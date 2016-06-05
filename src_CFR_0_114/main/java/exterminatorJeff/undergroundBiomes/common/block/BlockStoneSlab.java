/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockSlab
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package exterminatorJeff.undergroundBiomes.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import exterminatorJeff.undergroundBiomes.api.NamedBlock;
import exterminatorJeff.undergroundBiomes.api.NamedItem;
import exterminatorJeff.undergroundBiomes.api.NamedSlabPair;
import exterminatorJeff.undergroundBiomes.common.CreativeTabModBlocks;
import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;
import exterminatorJeff.undergroundBiomes.common.block.BlockMetadataBase;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class BlockStoneSlab
extends BlockSlab {
    protected final boolean isDoubleSlab;
    public final BlockMetadataBase referenceBlock;
    private final NamedItem halfSlabNamer;

    public BlockStoneSlab(boolean isDouble, Block refBlock, NamedSlabPair pairName) {
        super(isDouble, refBlock.func_149688_o());
        this.func_149647_a((CreativeTabs)(isDouble ? null : UndergroundBiomes.tabModBlocks));
        this.isDoubleSlab = isDouble;
        this.referenceBlock = (BlockMetadataBase)refBlock;
        this.field_149783_u = true;
        this.halfSlabNamer = new NamedItem(pairName.half);
    }

    public void func_149651_a(IIconRegister p_149651_1_) {
    }

    public float func_149712_f(World par1World, int x, int y, int z) {
        float result = this.referenceBlock.func_149712_f(par1World, x, y, z);
        if (this.isDoubleSlab()) {
            return result;
        }
        return result / 2.0f;
    }

    public Item func_149694_d(World world, int x, int y, int z) {
        return this.getHalfSlab();
    }

    public String func_150002_b(int meta) {
        return this.referenceBlock.func_149739_a() + "Slab." + this.referenceBlock.getBlockTypeName(meta);
    }

    public float getExplosionResistance(Entity entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ) {
        return this.referenceBlock.getExplosionResistance(entity, world, x, y, z, explosionX, explosionY, explosionZ);
    }

    protected ItemStack func_149644_j(int metadata) {
        return new ItemStack(this.halfSlabNamer.cachedItem(), 2, metadata & 7);
    }

    public boolean isDoubleSlab() {
        return this.isDoubleSlab;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int par1, int meta) {
        return this.referenceBlock.textures[meta & 7];
    }

    public void func_149666_a(Item item, CreativeTabs tabs, List list) {
        if (this.isDoubleSlab) {
            return;
        }
        for (int i = 0; i < 8; ++i) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    public int func_149643_k(World world, int x, int y, int z) {
        return world.func_72805_g(x, y, z) & 7;
    }

    @SideOnly(value=Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        for (int i = 0; i < 8; ++i) {
        }
    }

    public String getFullSlabName(int index) {
        return super.func_149739_a() + "." + this.referenceBlock.getBlockTypeName(index);
    }

    public Item getHalfSlab() {
        return this.halfSlabNamer.registeredItem();
    }

    public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return this.getHalfSlab();
    }

    public int slabsDropped() {
        if (this.isDoubleSlab) {
            return 2;
        }
        return 1;
    }

    public boolean isReplaceableOreGen(World world, int x, int y, int z, Block target) {
        return false;
    }

    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        Item item = this.func_149650_a(metadata, world.field_73012_v, fortune);
        if (item != null) {
            ret.add(new ItemStack(item, this.slabsDropped(), metadata & 7));
        }
        return ret;
    }
}

