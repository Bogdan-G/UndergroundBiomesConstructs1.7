/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockLeaves
 *  net.minecraft.block.BlockTallGrass
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldProvider
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package exterminatorJeff.undergroundBiomes.intermod;

import exterminatorJeff.undergroundBiomes.api.BlockCodes;
import exterminatorJeff.undergroundBiomes.api.UBAPIHook;
import exterminatorJeff.undergroundBiomes.api.UBDimensionalStrataColumnProvider;
import exterminatorJeff.undergroundBiomes.api.UBStrataColumn;
import exterminatorJeff.undergroundBiomes.api.UBStrataColumnProvider;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenBoulders
extends WorldGenerator {
    private BlockTallGrass tallGrass;
    private int tallGrassMetadata;

    public WorldGenBoulders(BlockTallGrass tallgrass, int par2) {
        this.tallGrass = tallgrass;
        this.tallGrassMetadata = par2;
    }

    public boolean func_76484_a(World par1World, Random par2Random, int par3, int par4, int par5) {
        boolean var6 = false;
        while ((par1World.func_147439_a(par3, par4, par5) == Blocks.field_150362_t || par1World.func_147437_c(par3, par4, par5)) && par4 > 0) {
            --par4;
        }
        int dimension = par1World.field_73011_w.field_76574_g;
        UBStrataColumnProvider columnProvider = UBAPIHook.ubAPIHook.dimensionalStrataColumnProvider.ubStrataColumnProvider(dimension);
        BlockCodes cobble = columnProvider.strataColumn(par3, par5).cobblestone(par4);
        for (int var7 = 0; var7 < 128; ++var7) {
            int var8 = par3 + par2Random.nextInt(8) - par2Random.nextInt(8);
            int var9 = par4 + par2Random.nextInt(4) - par2Random.nextInt(4);
            int var10 = par5 + par2Random.nextInt(8) - par2Random.nextInt(8);
            int columnX = Integer.MAX_VALUE;
            int columnZ = Integer.MAX_VALUE;
            BlockCodes mossyCobblestone = new BlockCodes(Blocks.field_150341_Y, 0);
            if (!par1World.func_147437_c(var8, var9, var10) || !this.tallGrass.func_149718_j(par1World, var8, var9, var10)) continue;
            if (par2Random.nextInt(2) == 0) {
                par1World.func_147465_d(var8, var9, var10, (Block)this.tallGrass, this.tallGrassMetadata, 2);
                continue;
            }
            this.setBlockCodes(par1World, var8, var9, var10, par2Random.nextInt(8) == 0 ? mossyCobblestone : cobble);
        }
        return true;
    }

    private void setBlockCodes(World world, int x, int y, int z, BlockCodes blockCodes) {
        world.func_147465_d(x, y, z, blockCodes.block, blockCodes.metadata, 2);
    }
}

