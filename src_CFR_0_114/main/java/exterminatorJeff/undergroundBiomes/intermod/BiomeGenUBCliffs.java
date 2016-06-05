/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  highlands.biome.BiomeDecoratorHighlands
 *  highlands.biome.BiomeGenCliffs
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockTallGrass
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeDecorator
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.biome.BiomeGenBase$Height
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package exterminatorJeff.undergroundBiomes.intermod;

import exterminatorJeff.undergroundBiomes.intermod.WorldGenBoulders;
import highlands.biome.BiomeDecoratorHighlands;
import highlands.biome.BiomeGenCliffs;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenUBCliffs
extends BiomeGenCliffs {
    public BiomeGenUBCliffs(BiomeGenCliffs model, int biomeID) {
        super(biomeID);
        int trees = 2;
        int grass = 4;
        int flowers = 0;
        boolean plants = true;
        this.field_76760_I = new BiomeDecoratorHighlands((BiomeGenBase)this, trees, grass, flowers);
        this.func_150570_a(new BiomeGenBase.Height(1.0f, 0.5f));
        this.field_76750_F = 0.4f;
        this.field_76751_G = 0.4f;
        this.func_76735_a("Cliffs");
    }

    public WorldGenerator getRandomWorldGenForGrass(Random par1Random) {
        return new WorldGenBoulders(Blocks.field_150329_H, 1);
    }

    public WorldGenerator func_76730_b(Random par1Random) {
        return this.getRandomWorldGenForGrass(par1Random);
    }

    public void decorate(World world, Random random, int x, int z) {
        BiomeGenUBCliffs biome = this;
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
        highlandsDecorator.genOreHighlands(world, random, x, z, 8, this.field_76760_I.field_76816_n, 16, 32);
        highlandsDecorator.genOreHighlands(world, random, x, z, 1, this.field_76760_I.field_76831_p, 32, 64);
        highlandsDecorator.genOreHighlands(world, random, x, z, 2, this.field_76760_I.field_76819_m, 32, 64);
        highlandsDecorator.genOreHighlands(world, random, x, z, 1, this.field_76760_I.field_76817_o, 16, 32);
    }

    public void func_76728_a(World world, Random random, int x, int z) {
        this.decorate(world, random, x, z);
    }
}

