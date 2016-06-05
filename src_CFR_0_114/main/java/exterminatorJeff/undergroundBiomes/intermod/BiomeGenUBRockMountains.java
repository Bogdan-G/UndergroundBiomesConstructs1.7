/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  highlands.biome.BiomeDecoratorHighlands
 *  highlands.biome.BiomeGenRockMountains
 *  highlands.worldgen.WorldGenHighlandsShrub
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeDecorator
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.biome.BiomeGenBase$Height
 *  net.minecraft.world.gen.feature.WorldGenAbstractTree
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package exterminatorJeff.undergroundBiomes.intermod;

import exterminatorJeff.undergroundBiomes.intermod.WorldGenUBMountain;
import highlands.biome.BiomeDecoratorHighlands;
import highlands.biome.BiomeGenRockMountains;
import highlands.worldgen.WorldGenHighlandsShrub;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenUBRockMountains
extends BiomeGenRockMountains {
    private static final BiomeGenBase.Height biomeHeight = new BiomeGenBase.Height(1.8f, 0.5f);

    public BiomeGenUBRockMountains(int par1) {
        super(par1);
        int trees = -999;
        int grass = 2;
        int flowers = 0;
        this.field_76760_I = new BiomeDecoratorHighlands((BiomeGenBase)this, trees, grass, flowers);
        this.field_76753_B = Blocks.field_150348_b;
        this.func_150570_a(biomeHeight);
        this.field_76750_F = 0.6f;
        this.field_76751_G = 0.5f;
        this.func_76735_a("Rock Mountains");
    }

    public WorldGenAbstractTree func_150567_a(Random par1Random) {
        return new WorldGenHighlandsShrub(1, 1);
    }

    public void func_76728_a(World world, Random random, int x, int z) {
        this.decorate(world, random, x, z);
    }

    public void decorate(World world, Random random, int x, int z) {
        BiomeGenUBRockMountains biome = this;
        if (random.nextInt(2) == 0) {
            new WorldGenUBMountain(15, 15, false, 1).func_76484_a(world, random, x + random.nextInt(16), 128, z + random.nextInt(16));
        }
        this.field_76760_I.func_150512_a(world, random, (BiomeGenBase)biome, x, z);
        int var5 = 3 + random.nextInt(6);
        for (int var6 = 0; var6 < var5; ++var6) {
            int var8;
            int var9;
            int var7 = x + random.nextInt(16);
            Block var10 = world.func_147439_a(var7, var8 = random.nextInt(28) + 4, var9 = z + random.nextInt(16));
            if (!var10.isReplaceableOreGen(world, x, z, z, Blocks.field_150348_b)) continue;
            world.func_147465_d(var7, var8, var9, Blocks.field_150412_bA, 0, 2);
        }
        BiomeDecorator theDecorator = this.field_76760_I;
        BiomeDecoratorHighlands highlandsDecorator = (BiomeDecoratorHighlands)theDecorator;
        highlandsDecorator.genOreHighlands(world, random, x, z, 20, this.field_76760_I.field_76818_l, 64, 128);
        highlandsDecorator.genOreHighlands(world, random, x, z, 40, this.field_76760_I.field_76818_l, 0, 128);
        highlandsDecorator.genOreHighlands(world, random, x, z, 8, this.field_76760_I.field_76816_n, 16, 32);
        highlandsDecorator.genOreHighlands(world, random, x, z, 1, this.field_76760_I.field_76831_p, 32, 64);
        highlandsDecorator.genOreHighlands(world, random, x, z, 2, this.field_76760_I.field_76819_m, 32, 64);
        highlandsDecorator.genOreHighlands(world, random, x, z, 1, this.field_76760_I.field_76817_o, 16, 32);
    }
}

