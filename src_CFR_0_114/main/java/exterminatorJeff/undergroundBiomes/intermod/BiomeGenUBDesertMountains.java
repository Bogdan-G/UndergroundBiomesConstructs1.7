/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  highlands.biome.BiomeDecoratorHighlands
 *  highlands.biome.BiomeGenDesertMountains
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
import highlands.biome.BiomeGenDesertMountains;
import highlands.worldgen.WorldGenHighlandsShrub;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenUBDesertMountains
extends BiomeGenDesertMountains {
    private static final BiomeGenBase.Height biomeHeight = new BiomeGenBase.Height(1.6f, 0.5f);

    public BiomeGenUBDesertMountains(int par1) {
        super(par1);
        int trees = -999;
        int grass = 0;
        int flowers = 0;
        this.field_76760_I = new BiomeDecoratorHighlands((BiomeGenBase)this, trees, grass, flowers);
        this.field_76752_A = Blocks.field_150354_m;
        this.field_76753_B = Blocks.field_150354_m;
        this.field_76750_F = 0.9f;
        this.field_76751_G = 0.0f;
        this.func_76745_m();
        this.func_76735_a("Desert Mountains");
    }

    public WorldGenAbstractTree func_150567_a(Random par1Random) {
        return new WorldGenHighlandsShrub(1, 1);
    }

    public void func_76728_a(World world, Random random, int x, int z) {
        this.decorate(world, random, x, z);
    }

    public void decorate(World world, Random random, int x, int z) {
        if (random.nextInt(4) == 0) {
            new WorldGenUBMountain(15, 20, false, 2).func_76484_a(world, random, x + random.nextInt(16), 128, z + random.nextInt(16));
        }
        BiomeGenUBDesertMountains biome = this;
        this.field_76760_I.func_150512_a(world, random, (BiomeGenBase)biome, x, z);
        ((BiomeDecoratorHighlands)this.field_76760_I).genOreHighlands(world, random, x, z, 20, this.field_76760_I.field_76818_l, 64, 128);
        ((BiomeDecoratorHighlands)this.field_76760_I).genOreHighlands(world, random, x, z, 8, this.field_76760_I.field_76816_n, 16, 32);
        ((BiomeDecoratorHighlands)this.field_76760_I).genOreHighlands(world, random, x, z, 1, this.field_76760_I.field_76831_p, 32, 64);
        ((BiomeDecoratorHighlands)this.field_76760_I).genOreHighlands(world, random, x, z, 2, this.field_76760_I.field_76819_m, 32, 64);
        ((BiomeDecoratorHighlands)this.field_76760_I).genOreHighlands(world, random, x, z, 4, this.field_76760_I.field_76819_m, 0, 64);
        ((BiomeDecoratorHighlands)this.field_76760_I).genOreHighlands(world, random, x, z, 1, this.field_76760_I.field_76817_o, 16, 32);
    }
}

