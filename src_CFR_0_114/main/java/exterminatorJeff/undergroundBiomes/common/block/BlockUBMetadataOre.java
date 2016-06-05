/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.Vec3Pool
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraftforge.common.util.ForgeDirection
 */
package exterminatorJeff.undergroundBiomes.common.block;

import Zeno410Utils.BlockState;
import Zeno410Utils.MinecraftName;
import Zeno410Utils.Mutable;
import Zeno410Utils.Zeno410Logger;
import exterminatorJeff.undergroundBiomes.common.block.BlockMetadataBase;
import exterminatorJeff.undergroundBiomes.common.block.BlockOverlay;
import exterminatorJeff.undergroundBiomes.common.block.BlockUBOre;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Logger;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Vec3Pool;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.util.ForgeDirection;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class BlockUBMetadataOre
extends BlockUBOre {
    public static Logger logger = new Zeno410Logger("BlockUBMetadataOre").logger();
    private final int oreMetadata;
    private IBlockAccess currentAccess;
    private IBlockAccess currentShamAccess;

    public BlockUBMetadataOre(BlockMetadataBase stone, BlockState ore, BlockOverlay overlay, Mutable<Integer> renderIDSource, MinecraftName oreName) {
        super(stone, ore.block, overlay, renderIDSource, oreName);
        this.oreMetadata = ore.metadata;
    }

    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        return this.ore.getDrops(world, x, y, z, this.oreMetadata, fortune);
    }

    @Override
    public void func_149651_a(IIconRegister iconRegister) {
        super.func_149651_a(iconRegister);
    }

    private IBlockAccess iBlockAccess(IBlockAccess toWrap) {
        if (toWrap != this.currentAccess) {
            this.currentAccess = toWrap;
            this.currentShamAccess = new ThisBlockAccess(toWrap);
        }
        return this.currentShamAccess;
    }

    @Override
    public void func_149657_c(World world, int x, int y, int z, int p_149657_5_) {
        int stoneMetadata = world.func_72805_g(x, y, z);
        world.func_72921_c(x, y, z, this.oreMetadata, 0);
        this.ore.func_149657_c(world, x, y, z, p_149657_5_);
        world.func_72921_c(x, y, z, stoneMetadata, 0);
    }

    @Override
    public void func_149690_a(World world, int x, int y, int z, int p_149690_5_, float p_149690_6_, int p_149690_7_) {
        super.func_149690_a(world, x, y, z, this.oreMetadata, p_149690_6_, p_149690_7_);
    }

    @Override
    public float func_149712_f(World world, int x, int y, int z) {
        int stoneMetadata = world.func_72805_g(x, y, z);
        world.func_72921_c(x, y, z, this.oreMetadata, 0);
        float result = this.ore.func_149712_f(world, x, y, z);
        world.func_72921_c(x, y, z, stoneMetadata, 0);
        return result;
    }

    @Override
    public int getExpDrop(IBlockAccess world, int metadata, int fortune) {
        return this.ore.getExpDrop(this.iBlockAccess(world), this.oreMetadata, fortune);
    }

    @Override
    public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ) {
        int stoneMetadata = world.func_72805_g(x, y, z);
        world.func_72921_c(x, y, z, this.oreMetadata, 0);
        float result = this.ore.getExplosionResistance(par1Entity, world, x, y, z, explosionX, explosionY, explosionZ);
        world.func_72921_c(x, y, z, stoneMetadata, 0);
        return result;
    }

    @Override
    public int getLightOpacity(IBlockAccess world, int x, int y, int z) {
        return this.ore.func_149717_k();
    }

    @Override
    public int func_149750_m() {
        return this.ore.func_149750_m();
    }

    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        return this.ore.func_149750_m();
    }

    @Override
    public void func_149636_a(World world, EntityPlayer p_149636_2_, int x, int y, int z, int p_149636_6_) {
        super.func_149636_a(world, p_149636_2_, x, y, z, this.oreMetadata);
    }

    @Override
    public int getHarvestLevel(int metadata) {
        return this.ore.getHarvestLevel(this.oreMetadata);
    }

    @Override
    public String getHarvestTool(int metadata) {
        return this.ore.getHarvestTool(this.oreMetadata);
    }

    @Override
    public boolean canHarvestBlock(EntityPlayer player, int meta) {
        return this.ore.canHarvestBlock(player, this.oreMetadata);
    }

    @Override
    public float func_149737_a(EntityPlayer player, World world, int x, int y, int z) {
        int metadata = world.func_72805_g(x, y, z);
        float hardness = this.func_149712_f(world, x, y, z);
        if (hardness < 0.0f) {
            return 0.0f;
        }
        if (!this.canHarvestBlock(player, metadata)) {
            return player.getBreakSpeed((Block)this, true, metadata, x, y, z) / hardness / 100.0f;
        }
        return player.getBreakSpeed((Block)this, false, metadata, x, y, z) / hardness / 30.0f;
    }

    @Override
    public void func_149674_a(World world, int x, int y, int z, Random p_149674_5_) {
        int stoneMetadata = world.func_72805_g(x, y, z);
        world.func_72921_c(x, y, z, this.oreMetadata, 0);
        this.ore.func_149674_a(world, x, y, z, p_149674_5_);
        world.func_72921_c(x, y, z, stoneMetadata, 0);
    }

    public void func_149734_b(World world, int x, int y, int z, Random p_149734_5_) {
        int stoneMetadata = world.func_72805_g(x, y, z);
        world.func_72921_c(x, y, z, this.oreMetadata, 0);
        super.func_149734_b(world, x, y, z, p_149734_5_);
        world.func_72921_c(x, y, z, stoneMetadata, 0);
    }

    private class ThisBlockAccess
    implements IBlockAccess {
        private final IBlockAccess wrapped;
        int x;
        int y;
        int z;

        ThisBlockAccess(IBlockAccess wrapped) {
            this.wrapped = wrapped;
        }

        public Block func_147439_a(int arg0, int arg1, int arg2) {
            if (arg0 == this.x && arg1 == this.y && arg2 == this.z) {
                return BlockUBMetadataOre.this;
            }
            return this.wrapped.func_147439_a(arg0, arg1, arg2);
        }

        public TileEntity func_147438_o(int arg0, int arg1, int arg2) {
            return this.wrapped.func_147438_o(arg0, arg1, arg2);
        }

        public int func_72802_i(int arg0, int arg1, int arg2, int arg3) {
            return this.wrapped.func_72802_i(arg0, arg1, arg2, arg3);
        }

        public int func_72805_g(int arg0, int arg1, int arg2) {
            if (arg0 == this.x && arg1 == this.y && arg2 == this.z) {
                return BlockUBMetadataOre.this.oreMetadata;
            }
            return this.wrapped.func_72805_g(arg0, arg1, arg2);
        }

        public boolean func_147437_c(int arg0, int arg1, int arg2) {
            return this.wrapped.func_147437_c(arg0, arg1, arg2);
        }

        public BiomeGenBase func_72807_a(int arg0, int arg1) {
            return this.wrapped.func_72807_a(arg0, arg1);
        }

        public int func_72800_K() {
            return this.wrapped.func_72800_K();
        }

        public boolean func_72806_N() {
            return this.wrapped.func_72806_N();
        }

        public int func_72879_k(int arg0, int arg1, int arg2, int arg3) {
            return this.wrapped.func_72879_k(arg0, arg1, arg2, arg3);
        }

        public boolean isSideSolid(int arg0, int arg1, int arg2, ForgeDirection arg3, boolean arg4) {
            return this.wrapped.isSideSolid(arg0, arg1, arg2, arg3, arg4);
        }

        public Vec3Pool func_82732_R() {
            return this.wrapped.func_82732_R();
        }
    }

}

