/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockStairs
 *  net.minecraft.block.ITileEntityProvider
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package exterminatorJeff.undergroundBiomes.constructs.block;

import Zeno410Utils.Zeno410Logger;
import exterminatorJeff.undergroundBiomes.api.NamedBlock;
import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;
import exterminatorJeff.undergroundBiomes.common.block.BlockMetadataBase;
import exterminatorJeff.undergroundBiomes.constructs.entity.UndergroundBiomesTileEntity;
import exterminatorJeff.undergroundBiomes.constructs.util.UBCodeLocations;
import exterminatorJeff.undergroundBiomes.constructs.util.UndergroundBiomesBlock;
import exterminatorJeff.undergroundBiomes.constructs.util.UndergroundBiomesBlockList;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class UBStairsBase
extends BlockStairs
implements ITileEntityProvider {
    private NamedBlock name;
    static Logger logger = new Zeno410Logger("UBStairsBase").logger();

    public UBStairsBase(BlockMetadataBase _baseBlock, NamedBlock namer) {
        super((Block)_baseBlock, 0);
        this.name = namer;
        this.func_149713_g(4);
        this.field_149758_A = false;
        this.func_149647_a(null);
        this.func_149663_c("stairs");
    }

    public void func_149651_a(IIconRegister arg0) {
    }

    public boolean hasTileEntity(int metadata) {
        return true;
    }

    public boolean func_149716_u() {
        return true;
    }

    public TileEntity func_149915_a(World world, int i) {
        throw new RuntimeException();
    }

    public TileEntity createTileEntity(World world, int i) {
        return new UndergroundBiomesTileEntity();
    }

    public void func_149666_a(Item item, CreativeTabs tabs, List list) {
        if (!UndergroundBiomes.stairsOn()) {
            return;
        }
        for (int i = 0; i < 56; ++i) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    public void func_149749_a(World par1World, int par2, int par3, int par4, Block par5, int par6) {
    }

    public final UndergroundBiomesTileEntity ubTileEntity(IBlockAccess world, int x, int y, int z) {
        TileEntity tileEntity = world.func_147438_o(x, y, z);
        if (tileEntity == null) {
            // empty if block
        }
        if (tileEntity instanceof UndergroundBiomesTileEntity) {
            UndergroundBiomesTileEntity result = (UndergroundBiomesTileEntity)tileEntity;
            return result;
        }
        return null;
    }

    public final UndergroundBiomesBlock ubBlock(World world, int x, int y, int z) {
        return UndergroundBiomesBlockList.indexed(this.ubTileEntity((IBlockAccess)world, x, y, z).masterIndex());
    }

    public final UndergroundBiomesBlock safeUBBlock(IBlockAccess world, int x, int y, int z) {
        UndergroundBiomesTileEntity entity = this.ubTileEntity(world, x, y, z);
        if (entity == null) {
            return this.ubBlock(0);
        }
        return UndergroundBiomesBlockList.indexed(this.ubTileEntity(world, x, y, z).masterIndex());
    }

    public final UndergroundBiomesBlock ubBlock(int reference) {
        return UndergroundBiomesBlockList.indexed(reference);
    }

    public int func_149643_k(World world, int x, int y, int z) {
        UndergroundBiomesTileEntity entity = this.ubTileEntity((IBlockAccess)world, x, y, z);
        if (entity == null) {
            return this.func_149692_a(0);
        }
        return this.func_149692_a(this.ubTileEntity((IBlockAccess)world, x, y, z).masterIndex());
    }

    public boolean func_149696_a(World par1World, int par2, int par3, int par4, int par5, int par6) {
        super.func_149696_a(par1World, par2, par3, par4, par5, par6);
        TileEntity tileentity = par1World.func_147438_o(par2, par3, par4);
        return tileentity != null ? tileentity.func_145842_c(par5, par6) : false;
    }

    public float func_149712_f(World world, int x, int y, int z) {
        return this.safeUBBlock((IBlockAccess)world, x, y, z).hardness();
    }

    public IIcon func_149691_a(int side, int metadata) {
        return super.func_149691_a(side, metadata);
    }

    public IIcon func_149673_e(IBlockAccess world, int x, int y, int z, int side) {
        int metadataFromEntity = ((UndergroundBiomesTileEntity)world.func_147438_o(x, y, z)).masterIndex();
        return this.ubBlock(metadataFromEntity).icon();
    }

    public boolean isReplaceableOreGen(World world, int x, int y, int z, Block target) {
        return true;
    }

    public float getBlockExplosionResistance(int meta) {
        return this.ubBlock(meta).explosionResistance();
    }

    public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ) {
        return this.safeUBBlock((IBlockAccess)world, x, y, z).explosionResistance();
    }

    public int func_149692_a(int metadata) {
        return metadata;
    }

    public ItemStack itemDropped(int metadata, Random random, int fortune, int y) {
        return new ItemStack((Block)this, 1, metadata);
    }

    public String getBlockName(int meta) {
        return this.ubBlock(meta).name();
    }

    public void func_149725_f(World world, int x, int y, int z, int p_149725_5_) {
        super.func_149725_f(world, x, y, z, p_149725_5_);
        TileEntity entity = world.func_147438_o(x, y, z);
        if (entity != null && entity instanceof UndergroundBiomesTileEntity) {
            this.cacheCode(x, y, z, this.ubBlock(world, x, y, z), world);
        }
    }

    private void cacheCode(int x, int y, int z, UndergroundBiomesBlock code, World world) {
        UndergroundBiomes.instance().ubCodeLocations(world).add(x, y, z, code);
    }

    private UndergroundBiomesBlock unCacheCode(int x, int y, int z, World world) {
        UndergroundBiomesBlock result = UndergroundBiomes.instance().ubCodeLocations(world).get(x, y, z);
        UndergroundBiomes.instance().ubCodeLocations(world).remove(x, y, z);
        return result;
    }

    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        int count = this.quantityDropped(metadata, fortune, world.field_73012_v);
        for (int i = 0; i < count; ++i) {
            Item item = this.func_149650_a(metadata, world.field_73012_v, fortune);
            if (item == null) continue;
            int index = this.unCacheCode((int)x, (int)y, (int)z, (World)world).index;
            ret.add(new ItemStack(item, 1, index));
        }
        world.func_147475_p(x, y, z);
        return ret;
    }

    public void func_149664_b(World world, int x, int y, int z, int p_149664_5_) {
        super.func_149664_b(world, x, y, z, p_149664_5_);
        TileEntity entity = world.func_147438_o(x, y, z);
        if (entity != null && entity instanceof UndergroundBiomesTileEntity) {
            world.func_147475_p(x, y, z);
        }
    }

    public void func_149689_a(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemStack) {
        super.func_149689_a(world, x, y, z, entity, itemStack);
        int index = itemStack.func_77960_j();
        UndergroundBiomesTileEntity target = (UndergroundBiomesTileEntity)world.func_147438_o(x, y, z);
        if (target == null) {
            target = new UndergroundBiomesTileEntity();
            target.func_145834_a(world);
            world.addTileEntity((TileEntity)target);
            world.func_147455_a(x, y, z, (TileEntity)target);
        }
        target.setMasterIndex(index);
        target = (UndergroundBiomesTileEntity)world.func_147438_o(x, y, z);
        index = target.masterIndex();
    }
}

