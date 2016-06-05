/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package exterminatorJeff.undergroundBiomes.common.block;

import Zeno410Utils.MinecraftName;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import exterminatorJeff.undergroundBiomes.common.CreativeTabModBlocks;
import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;
import exterminatorJeff.undergroundBiomes.common.block.BlockMetadataBase;
import exterminatorJeff.undergroundBiomes.common.block.BlockUBOre;
import exterminatorJeff.undergroundBiomes.common.block.BlockUBReplaceable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class BlockUBHidden
extends Block
implements BlockUBReplaceable {
    protected final BlockMetadataBase stone;
    protected final Block ore;
    private final MinecraftName oreName;

    public BlockUBHidden(BlockMetadataBase stone, Block ore) {
        this(stone, ore, new MinecraftName(ore.func_149739_a()));
    }

    public BlockUBHidden(BlockMetadataBase stone, Block ore, MinecraftName oreName) {
        super(Material.field_151576_e);
        this.stone = stone;
        this.ore = ore;
        if (ore instanceof BlockUBOre) {
            throw new RuntimeException();
        }
        this.func_149647_a((CreativeTabs)UndergroundBiomes.tabModBlocks);
        this.oreName = oreName;
    }

    @Override
    public Block block() {
        return this;
    }

    public void func_149651_a(IIconRegister p_149651_1_) {
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int side, int metadata) {
        return this.stone.func_149691_a(side, metadata);
    }

    @SideOnly(value=Side.CLIENT)
    protected String func_149641_N() {
        return this.stone.func_149641_N();
    }

    public void func_149666_a(Item id, CreativeTabs tabs, List list) {
        if (!UndergroundBiomes.ubOres()) {
            return;
        }
        for (int i = 0; i < 8; ++i) {
            list.add(new ItemStack(id, 1, i));
        }
    }

    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        return this.ore.getDrops(world, x, y, z, metadata, fortune);
    }

    public String func_149702_O() {
        return this.stone.func_149702_O() + " " + this.oreName.localized();
    }

    public String getDisplayName(int meta) {
        return this.stone.getBlockName(meta);
    }

    public String getUnlocalizedName(int meta) {
        return this.stone.getBlockName(meta);
    }

    public void func_149657_c(World p_149657_1_, int p_149657_2_, int p_149657_3_, int p_149657_4_, int p_149657_5_) {
        this.ore.func_149657_c(p_149657_1_, p_149657_2_, p_149657_3_, p_149657_4_, p_149657_5_);
    }

    public void func_149690_a(World p_149690_1_, int p_149690_2_, int p_149690_3_, int p_149690_4_, int p_149690_5_, float p_149690_6_, int p_149690_7_) {
        this.ore.func_149690_a(p_149690_1_, p_149690_2_, p_149690_3_, p_149690_4_, p_149690_5_, p_149690_6_, p_149690_7_);
    }

    public float func_149712_f(World p_149712_1_, int p_149712_2_, int p_149712_3_, int p_149712_4_) {
        return this.ore.func_149712_f(p_149712_1_, p_149712_2_, p_149712_3_, p_149712_4_);
    }

    public int getExpDrop(IBlockAccess world, int metadata, int fortune) {
        return this.ore.getExpDrop(world, metadata, fortune);
    }

    public float func_149638_a(Entity p_149638_1_) {
        return this.ore.func_149638_a(p_149638_1_);
    }

    public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ) {
        return this.ore.getExplosionResistance(par1Entity, world, x, y, z, explosionX, explosionY, explosionZ);
    }

    public int getLightOpacity(IBlockAccess world, int x, int y, int z) {
        return this.ore.func_149717_k();
    }

    public int func_149750_m() {
        return this.ore.func_149750_m();
    }

    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        return this.ore.func_149750_m();
    }

    public void func_149636_a(World p_149636_1_, EntityPlayer p_149636_2_, int p_149636_3_, int p_149636_4_, int p_149636_5_, int p_149636_6_) {
        this.ore.func_149636_a(p_149636_1_, p_149636_2_, p_149636_3_, p_149636_4_, p_149636_5_, p_149636_6_);
    }

    public boolean canHarvestBlock(EntityPlayer player, int meta) {
        return this.ore.canHarvestBlock(player, 0);
    }

    public void func_149674_a(World p_149674_1_, int p_149674_2_, int p_149674_3_, int p_149674_4_, Random p_149674_5_) {
        this.ore.func_149674_a(p_149674_1_, p_149674_2_, p_149674_3_, p_149674_4_, p_149674_5_);
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        Item item = this.func_149694_d(world, x, y, z);
        if (item == null) {
            return null;
        }
        return new ItemStack(item, 1, this.func_149643_k(world, x, y, z));
    }

    public int func_149692_a(int metadata) {
        return metadata;
    }

    public boolean canEntityDestroy(IBlockAccess world, int x, int y, int z, Entity entity) {
        return this.ore.canEntityDestroy(world, x, y, z, entity);
    }

    public void func_149664_b(World p_149664_1_, int p_149664_2_, int p_149664_3_, int p_149664_4_, int p_149664_5_) {
        this.ore.func_149664_b(p_149664_1_, p_149664_2_, p_149664_3_, p_149664_4_, p_149664_5_);
    }

    public boolean func_149696_a(World p_149696_1_, int p_149696_2_, int p_149696_3_, int p_149696_4_, int p_149696_5_, int p_149696_6_) {
        return this.ore.func_149696_a(p_149696_1_, p_149696_2_, p_149696_3_, p_149696_4_, p_149696_5_, p_149696_6_);
    }
}

