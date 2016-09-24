
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

public class WorldGenUndergroundUB extends WorldGenerator
{
    /** The block ID of the ore to be placed using this generator. */
    private Block minableBlockId;
    private int minableBlockMeta = 0;

    /** The number of blocks to generate. */
    private int numberOfBlocks;

    private Block IDtoReplace;

    public WorldGenUndergroundUB(Block dirt, int par2, Block sand)
    {
        this.minableBlockId = dirt;
        this.numberOfBlocks = par2;
        this.IDtoReplace = sand;
    }

    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
    {
        float var6 = par2Random.nextFloat() * (float)Math.PI;
        float msin = MathHelper.sin(var6);
        float msqrt = (float)Math.sqrt(1 - msin*msin);
        float nob8 = (float)this.numberOfBlocks / 8.0f;
        float var7 = ((float)(par3 + 8) + msin * nob8);
        float var9 = ((float)(par3 + 8) - msin * nob8);
        float var11 = ((float)(par5 + 8) + msqrt * nob8);
        float var13 = ((float)(par5 + 8) - msqrt * nob8);
        float var15 = (par4 + par2Random.nextInt(3) - 2);
        float var17 = (par4 + par2Random.nextInt(3) - 2);

        int dimension = par1World.provider.dimensionId;
        UBStrataColumnProvider columnProvider =
                UBAPIHook.ubAPIHook.dimensionalStrataColumnProvider.ubStrataColumnProvider(dimension);
                //BlockCodes blockCodes = columnProvider.strataColumn(var38, var44).stone(var41);
                BlockCodes blockCodes = columnProvider.strataColumn(par3, par5).stone(par4);
        for (int var19 = 0; var19 <= this.numberOfBlocks; ++var19)
        {
            float vnob = (float)var19 / (float)this.numberOfBlocks;
            float var20 = var7 + (var9 - var7) * vnob;
            float var22 = var15 + (var17 - var15) * vnob;
            float var24 = var11 + (var13 - var11) * vnob;
            float var26 = par2Random.nextFloat() * nob8 / 2.0f;
            float var28 = ((MathHelper.sin((float)var19 * (float)Math.PI / (float)this.numberOfBlocks) + 1.0f) * var26 + 1.0f) / 2.0f;
            //float var30 = (float)(MathHelper.sin((float)var19 * (float)Math.PI / (float)this.numberOfBlocks) + 1.0f) * var26 + 1.0f;
            int var32 = MathHelper.floor_float(var20 - var28);
            int var33 = MathHelper.floor_float(var22 - var28);
            int var34 = MathHelper.floor_float(var24 - var28);
            int var35 = MathHelper.floor_float(var20 + var28);
            int var36 = MathHelper.floor_float(var22 + var28);
            int var37 = MathHelper.floor_float(var24 + var28);

            for (int var38 = var32; var38 <= var35; ++var38)
            {
                float var39 = (var38 + 0.5f - var20) / (var28);
                float var39n2 = var39 * var39;

                if (var39n2 < 1.0f)
                {
                    for (int var41 = var33; var41 <= var36; ++var41)
                    {
                        float var42 = (var41 + 0.5f - var22) / (var28);
                        float var42n2 = var42 * var42;

                        if (var39n2 + var42n2 < 1.0f)
                        {
                            for (int var44 = var34; var44 <= var37; ++var44)
                            {
                                float var45 = (var44 + 0.5f - var24) / (var28);

                                Block block = par1World.getBlock(var38, var41, var44);
                                if (var39n2 + var42n2 + var45 * var45 < 1.0f && (block != null && (block == IDtoReplace ||
                                		(IDtoReplace == Blocks.dirt && block == Blocks.grass))))
                                {
                                	if(par1World.isAirBlock(var38, var41+1, var44) && minableBlockId == Blocks.dirt)
                                		par1World.setBlock(var38, var41, var44, Blocks.grass, minableBlockMeta, 3);
                                	else {
                                        par1World.setBlock(var38, var41, var44, blockCodes.block, blockCodes.metadata, 3);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return true;
    }
}