/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.LongHashMap
 */
package exterminatorJeff.undergroundBiomes.common;

import exterminatorJeff.undergroundBiomes.api.BiomeGenUndergroundBase;
import exterminatorJeff.undergroundBiomes.common.WorldGenManager;
import exterminatorJeff.undergroundBiomes.worldGen.BiomeUndergroundCacheBlock;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.util.LongHashMap;

public class UBiomeCache {
    private List cacheUnderground = new ArrayList();
    private LongHashMap undergroundCacheMap = new LongHashMap();
    public WorldGenManager worldGen;

    public UBiomeCache(WorldGenManager gen) {
        this.worldGen = gen;
    }

    public BiomeUndergroundCacheBlock getUndergroundBiomeCacheBlock(int par1, int par2) {
        long var3 = (long)par1 & 0xFFFFFFFFL | ((long)par2 & 0xFFFFFFFFL) << 32;
        BiomeUndergroundCacheBlock var5 = (BiomeUndergroundCacheBlock)this.undergroundCacheMap.func_76164_a(var3);
        if (var5 == null) {
            var5 = new BiomeUndergroundCacheBlock(this, par1, par2);
            this.undergroundCacheMap.func_76163_a(var3, (Object)var5);
            this.cacheUnderground.add(var5);
        }
        var5.lastAccessTime = System.currentTimeMillis();
        return var5;
    }

    public BiomeGenUndergroundBase getUndergroundBiomeGetAt(int xPos, int yPos) {
        return this.getUndergroundBiomeCacheBlock(xPos, yPos).getBiomeGenAt(xPos, yPos);
    }

    public BiomeGenUndergroundBase[] getCachedUndergroundBiomes(int xPos, int yPos) {
        return this.getUndergroundBiomeCacheBlock((int)xPos, (int)yPos).biomes;
    }
}

