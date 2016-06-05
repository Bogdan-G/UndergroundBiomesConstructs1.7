/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  highlands.biome.BiomeDecoratorHighlands
 *  highlands.biome.BiomeGenBadlands
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeDecorator
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.biome.BiomeGenBase$Height
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package exterminatorJeff.undergroundBiomes.intermod;

import exterminatorJeff.undergroundBiomes.intermod.WorldGenUndergroundUB;
import highlands.biome.BiomeDecoratorHighlands;
import highlands.biome.BiomeGenBadlands;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenUBBadlands
extends BiomeGenBadlands {
    private WorldGenerator ubStoneGenerator = new WorldGenUndergroundUB(Blocks.field_150348_b, 72, Blocks.field_150346_d);
    private static final BiomeGenBase.Height biomeHeight = new BiomeGenBase.Height(0.8f, 0.45f);

    public BiomeGenUBBadlands(int biomeID) {
        super(biomeID);
        int trees = 1;
        int grass = 6;
        int flowers = 0;
        int plants = 1;
        this.field_76760_I = new BiomeDecoratorHighlands((BiomeGenBase)this, trees, grass, flowers, plants);
        this.func_150570_a(biomeHeight);
        this.field_76750_F = 0.6f;
        this.field_76751_G = 0.1f;
        this.func_76735_a("Badlands");
    }

    public void func_76728_a(World world, Random random, int x, int z) {
        this.decorate(world, random, x, z);
    }

    public void decorate(World world, Random random, int x, int z) {
        BiomeGenUBBadlands biome = this;
        this.field_76760_I.func_150512_a(world, random, (BiomeGenBase)biome, x, z);
        int var5 = 3 + random.nextInt(6);
        for (int var6 = 0; var6 < var5; ++var6) {
            int var8;
            int var9;
            int var7 = x + random.nextInt(16);
            Block var10 = world.func_147439_a(var7, var8 = random.nextInt(28) + 4, var9 = z + random.nextInt(16));
            if (var10 != Blocks.field_150348_b) continue;
            world.func_147465_d(var7, var8, var9, Blocks.field_150412_bA, 0, 2);
        }
        this.genUBStoneHighlandsNoCheck(world, random, x, z, 6, this.ubStoneGenerator, 62, 120);
        BiomeDecoratorHighlands decorator = (BiomeDecoratorHighlands)this.field_76760_I;
        decorator.genOreHighlands(world, random, x, z, 2, this.field_76760_I.field_76819_m, 0, 32);
    }

    public void genUBStoneHighlandsNoCheck(World world, Random random, int locX, int locZ, int timesPerChunk, WorldGenerator oreGen, int minH, int maxH) {
        for (int var5 = 0; var5 < timesPerChunk; ++var5) {
            int var6 = locX + random.nextInt(16);
            int var7 = random.nextInt(maxH - minH) + minH;
            int var8 = locZ + random.nextInt(16);
            oreGen.func_76484_a(world, random, var6, var7, var8);
        }
    }
}

