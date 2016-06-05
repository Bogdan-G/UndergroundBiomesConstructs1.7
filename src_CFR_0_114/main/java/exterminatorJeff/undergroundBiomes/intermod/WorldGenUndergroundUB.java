/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockGrass
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.MathHelper
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
import net.minecraft.block.BlockGrass;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenUndergroundUB
extends WorldGenerator {
    private Block minableBlockId;
    private int minableBlockMeta = 0;
    private int numberOfBlocks;
    private Block IDtoReplace;

    public WorldGenUndergroundUB(Block dirt, int par2, Block sand) {
        this.minableBlockId = dirt;
        this.numberOfBlocks = par2;
        this.IDtoReplace = sand;
    }

    public boolean func_76484_a(World par1World, Random par2Random, int par3, int par4, int par5) {
        float var6 = par2Random.nextFloat() * 3.1415927f;
        double var7 = (float)(par3 + 8) + MathHelper.func_76126_a((float)var6) * (float)this.numberOfBlocks / 8.0f;
        double var9 = (float)(par3 + 8) - MathHelper.func_76126_a((float)var6) * (float)this.numberOfBlocks / 8.0f;
        double var11 = (float)(par5 + 8) + MathHelper.func_76134_b((float)var6) * (float)this.numberOfBlocks / 8.0f;
        double var13 = (float)(par5 + 8) - MathHelper.func_76134_b((float)var6) * (float)this.numberOfBlocks / 8.0f;
        double var15 = par4 + par2Random.nextInt(3) - 2;
        double var17 = par4 + par2Random.nextInt(3) - 2;
        int dimension = par1World.field_73011_w.field_76574_g;
        UBStrataColumnProvider columnProvider = UBAPIHook.ubAPIHook.dimensionalStrataColumnProvider.ubStrataColumnProvider(dimension);
        BlockCodes blockCodes = columnProvider.strataColumn(par3, par5).stone(par4);
        for (int var19 = 0; var19 <= this.numberOfBlocks; ++var19) {
            double var20 = var7 + (var9 - var7) * (double)var19 / (double)this.numberOfBlocks;
            double var22 = var15 + (var17 - var15) * (double)var19 / (double)this.numberOfBlocks;
            double var24 = var11 + (var13 - var11) * (double)var19 / (double)this.numberOfBlocks;
            double var26 = par2Random.nextDouble() * (double)this.numberOfBlocks / 16.0;
            double var28 = (double)(MathHelper.func_76126_a((float)((float)var19 * 3.1415927f / (float)this.numberOfBlocks)) + 1.0f) * var26 + 1.0;
            double var30 = (double)(MathHelper.func_76126_a((float)((float)var19 * 3.1415927f / (float)this.numberOfBlocks)) + 1.0f) * var26 + 1.0;
            int var32 = MathHelper.func_76128_c((double)(var20 - var28 / 2.0));
            int var33 = MathHelper.func_76128_c((double)(var22 - var30 / 2.0));
            int var34 = MathHelper.func_76128_c((double)(var24 - var28 / 2.0));
            int var35 = MathHelper.func_76128_c((double)(var20 + var28 / 2.0));
            int var36 = MathHelper.func_76128_c((double)(var22 + var30 / 2.0));
            int var37 = MathHelper.func_76128_c((double)(var24 + var28 / 2.0));
            for (int var38 = var32; var38 <= var35; ++var38) {
                double var39 = ((double)var38 + 0.5 - var20) / (var28 / 2.0);
                if (var39 * var39 >= 1.0) continue;
                for (int var41 = var33; var41 <= var36; ++var41) {
                    double var42 = ((double)var41 + 0.5 - var22) / (var30 / 2.0);
                    if (var39 * var39 + var42 * var42 >= 1.0) continue;
                    for (int var44 = var34; var44 <= var37; ++var44) {
                        double var45 = ((double)var44 + 0.5 - var24) / (var28 / 2.0);
                        Block block = par1World.func_147439_a(var38, var41, var44);
                        if (var39 * var39 + var42 * var42 + var45 * var45 >= 1.0 || block == null || block != this.IDtoReplace && (this.IDtoReplace != Blocks.field_150346_d || block != Blocks.field_150349_c)) continue;
                        if (par1World.func_147437_c(var38, var41 + 1, var44) && this.minableBlockId == Blocks.field_150346_d) {
                            par1World.func_147465_d(var38, var41, var44, (Block)Blocks.field_150349_c, this.minableBlockMeta, 3);
                            continue;
                        }
                        par1World.func_147465_d(var38, var41, var44, blockCodes.block, blockCodes.metadata, 3);
                    }
                }
            }
        }
        return true;
    }
}

