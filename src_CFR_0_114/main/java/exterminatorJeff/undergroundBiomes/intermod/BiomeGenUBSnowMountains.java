/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  highlands.Highlands
 *  highlands.biome.BiomeDecoratorHighlands
 *  highlands.biome.BiomeGenBaseHighlands
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

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import exterminatorJeff.undergroundBiomes.intermod.WorldGenUBMountain;
import highlands.Highlands;
import highlands.biome.BiomeDecoratorHighlands;
import highlands.biome.BiomeGenBaseHighlands;
import highlands.worldgen.WorldGenHighlandsShrub;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenUBSnowMountains
extends BiomeGenBaseHighlands {
    private static final BiomeGenBase.Height biomeHeight = new BiomeGenBase.Height(2.0f, 1.0f);

    public BiomeGenUBSnowMountains(int par1) {
        super(par1);
        int trees = -999;
        int grass = 2;
        int flowers = 0;
        this.theBiomeDecorator = new BiomeDecoratorHighlands((BiomeGenBase)this, trees, grass, flowers);
        this.spawnableCreatureList.clear();
        this.topBlock = Blocks.field_150433_aE;
        this.fillerBlock = Blocks.field_150433_aE;
        this.setHeight(biomeHeight);
        this.temperature = 0.0f;
        this.rainfall = 0.5f;
        this.setEnableSnow();
    }

    public WorldGenAbstractTree func_150567_a(Random par1Random) {
        return new WorldGenHighlandsShrub(1, 1);
    }

    public void decorate(World world, Random random, int x, int z) {
        BiomeGenUBSnowMountains biome = this;
        new WorldGenUBMountain(15, 25, false, 0).func_76484_a(world, random, x + random.nextInt(16), 128, z + random.nextInt(16));
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
        (BiomeDecoratorHighlands)this.theBiomeDecorator;
        highlandsDecorator.genOreHighlands(world, random, x, z, 20, BiomeDecoratorHighlands.HLice, 0, 128);
        highlandsDecorator.genOreHighlands(world, random, x, z, 20, this.field_76760_I.field_76818_l, 64, 128);
        highlandsDecorator.genOreHighlands(world, random, x, z, 8, this.field_76760_I.field_76816_n, 16, 32);
        highlandsDecorator.genOreHighlands(world, random, x, z, 1, this.field_76760_I.field_76831_p, 32, 64);
        highlandsDecorator.genOreHighlands(world, random, x, z, 2, this.field_76760_I.field_76819_m, 32, 64);
        highlandsDecorator.genOreHighlands(world, random, x, z, 1, this.field_76760_I.field_76817_o, 16, 32);
        highlandsDecorator.genOreHighlands(world, random, x, z, 2, this.field_76760_I.field_76817_o, 0, 32);
    }

    @SideOnly(value=Side.CLIENT)
    public int getSkyColorByTemp(float par1) {
        if (Highlands.skyColorFlag) {
            return 13034495;
        }
        return super.getSkyColorByTemp(par1);
    }
}

