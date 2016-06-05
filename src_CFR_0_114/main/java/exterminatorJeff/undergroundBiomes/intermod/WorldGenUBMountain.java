/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.IBlockAccess
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
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenUBMountain
extends WorldGenerator {
    private int minHeight;
    private int maxHeight;
    private int snowrocksand;
    private boolean notifyFlag;
    private World worldObj;
    private Random random;

    public WorldGenUBMountain(int minH, int maxH, boolean notify, int type) {
        this.minHeight = minH;
        this.maxHeight = maxH;
        this.notifyFlag = notify;
        this.snowrocksand = type;
    }

    public boolean func_76484_a(World world, Random random, int locX, int locY, int locZ) {
        int height;
        this.worldObj = world;
        this.random = random;
        int radius = height = this.minHeight + random.nextInt(this.maxHeight);
        BlockCodes snowBlock = new BlockCodes(Blocks.field_150433_aE, 0);
        BlockCodes sandstoneBlock = new BlockCodes(Blocks.field_150322_A, 0);
        UBStrataColumnProvider columnProvider = UBAPIHook.ubAPIHook.dimensionalStrataColumnProvider.ubStrataColumnProvider(world.field_73011_w.field_76574_g);
        for (int x = (int)Math.ceil((double)((double)(locX - radius))); x <= (int)Math.ceil(locX + radius); ++x) {
            for (int z = (int)Math.ceil((double)((double)(locZ - radius))); z <= (int)Math.ceil(locZ + radius); ++z) {
                Block block;
                double xfr = z - locZ;
                double zfr = x - locX;
                UBStrataColumn column = columnProvider.strataColumn(x, z);
                int dist = (int)Math.sqrt(xfr * xfr + zfr * zfr);
                if (dist > radius) continue;
                for (locY = world.func_72825_h((int)x, (int)z); locY > 0 && ((block = world.func_147439_a(x, locY, z)) == null || !block.func_149662_c() || block.isWood((IBlockAccess)world, x, locY, z) || block.isLeaves((IBlockAccess)world, x, locY, z)); --locY) {
                }
                int h = locY + height - dist;
                for (int i = locY; i < h; ++i) {
                    if (this.snowrocksand == 0 && h - i < 4) {
                        this.setBlockInWorld(x, i, z, snowBlock);
                        continue;
                    }
                    if (this.snowrocksand == 2 && h - i < 4) {
                        this.setBlockInWorld(x, i, z, sandstoneBlock);
                        continue;
                    }
                    if (random.nextInt(3) == 0) {
                        this.setBlockInWorld(x, i, z, column.cobblestone(i));
                        continue;
                    }
                    this.setBlockInWorld(x, i, z, column.stone(i));
                }
                if (this.snowrocksand != 0 || h <= 62) continue;
                this.setBlockInWorld(x, h, z, snowBlock);
            }
        }
        this.worldObj = null;
        return true;
    }

    private void setBlockInWorld(int x, int y, int z, BlockCodes blockCodes) {
        if (this.notifyFlag) {
            this.worldObj.func_147465_d(x, y, z, blockCodes.block, blockCodes.metadata, 3);
        } else {
            this.worldObj.func_147465_d(x, y, z, blockCodes.block, blockCodes.metadata, 2);
        }
    }
}

