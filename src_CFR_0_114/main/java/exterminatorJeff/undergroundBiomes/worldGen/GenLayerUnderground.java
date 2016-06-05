/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.gen.layer.GenLayer
 *  net.minecraft.world.gen.layer.GenLayerAddIsland
 *  net.minecraft.world.gen.layer.GenLayerFuzzyZoom
 *  net.minecraft.world.gen.layer.GenLayerIsland
 *  net.minecraft.world.gen.layer.GenLayerSmooth
 *  net.minecraft.world.gen.layer.GenLayerVoronoiZoom
 *  net.minecraft.world.gen.layer.GenLayerZoom
 */
package exterminatorJeff.undergroundBiomes.worldGen;

import exterminatorJeff.undergroundBiomes.api.UndergroundBiomeSet;
import exterminatorJeff.undergroundBiomes.common.WorldGenManager;
import exterminatorJeff.undergroundBiomes.worldGen.GenLayerUndergroundBiomes;
import java.util.logging.Logger;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerAddIsland;
import net.minecraft.world.gen.layer.GenLayerFuzzyZoom;
import net.minecraft.world.gen.layer.GenLayerIsland;
import net.minecraft.world.gen.layer.GenLayerSmooth;
import net.minecraft.world.gen.layer.GenLayerVoronoiZoom;
import net.minecraft.world.gen.layer.GenLayerZoom;

public abstract class GenLayerUnderground
extends GenLayer {
    private static int biomeSize = 3;
    public static int biomeSizeOfEntireMinecraftWorld = 22;
    public static int biomeSizeUsedForEarlierVersions = 3;
    public static boolean testing;

    public static GenLayer[] initializeAllBiomeGenerators(long par0, int size, UndergroundBiomeSet biomeSet) {
        GenLayerIsland var3 = new GenLayerIsland(1);
        GenLayerFuzzyZoom var9 = new GenLayerFuzzyZoom(2000, (GenLayer)var3);
        GenLayerAddIsland var10 = new GenLayerAddIsland(1, (GenLayer)var9);
        GenLayerZoom var11 = new GenLayerZoom(2001, (GenLayer)var10);
        var10 = new GenLayerAddIsland(2, (GenLayer)var11);
        var11 = new GenLayerZoom(2002, (GenLayer)var10);
        var10 = new GenLayerAddIsland(3, (GenLayer)var11);
        var11 = new GenLayerZoom(2003, (GenLayer)var10);
        var10 = new GenLayerAddIsland(4, (GenLayer)var11);
        int var4 = biomeSize;
        GenLayer var5 = GenLayerZoom.func_75915_a((long)1000, (GenLayer)var11, (int)0);
        var5 = GenLayerZoom.func_75915_a((long)1000, (GenLayer)var5, (int)(var4 + 2));
        GenLayerUndergroundBiomes var17 = new GenLayerUndergroundBiomes(200, var5, biomeSet);
        if (size >= biomeSizeOfEntireMinecraftWorld) {
            size = biomeSizeUsedForEarlierVersions;
        }
        GenLayerSmooth var15 = new GenLayerSmooth(1000, (GenLayer)var17);
        GenLayer var6 = GenLayerZoom.func_75915_a((long)1000, (GenLayer)var15, (int)size);
        var6 = GenLayerZoom.func_75915_a((long)1000, (GenLayer)var6, (int)2);
        GenLayerSmooth var19 = new GenLayerSmooth(1000, var6);
        GenLayerVoronoiZoom var8 = new GenLayerVoronoiZoom(10, (GenLayer)var19);
        var8.func_75905_a(par0);
        WorldGenManager.logger.info("initializing underground biome generators");
        return new GenLayer[]{var19, var8, var19};
    }

    public GenLayerUnderground(long par1) {
        super(par1);
    }

    public static void testGenerator(GenLayer generator, int biomeSize) {
        int numberBiomes = 40;
        int[] hits = new int[numberBiomes];
        int multiplier = 16 << biomeSize;
        testing = true;
        for (int i = 0; i < 30; ++i) {
            for (int j = 0; j < 30; ++j) {
                int biome = generator.func_75904_a(i * multiplier, j * multiplier, 16, 16)[0];
                WorldGenManager.logger.info("biome " + biome + " from " + i * multiplier + " " + j * multiplier);
                int[] arrn = hits;
                int n = biome;
                arrn[n] = arrn[n] + 1;
            }
        }
        for (int biomeType = 0; biomeType < numberBiomes; ++biomeType) {
            WorldGenManager.logger.info("biome " + biomeType + " hits " + hits[biomeType]);
        }
        testing = false;
    }

    public static void testBiomeSize(GenLayer generator) {
        int length = 10000;
        int currentBiome = generator.func_75904_a(0, 0, 16, 16)[0];
        int currentStart = 0;
        for (int i = 10; i < length; i += 10) {
            int newBiome = generator.func_75904_a(i, 0, 16, 16)[0];
            if (newBiome == currentBiome) continue;
            WorldGenManager.logger.info("biome " + currentBiome + " length " + (i - currentStart));
            currentStart = i;
            currentBiome = newBiome;
        }
    }
}

