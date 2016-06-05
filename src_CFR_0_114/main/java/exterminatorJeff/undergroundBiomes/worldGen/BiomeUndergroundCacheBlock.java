/*
 * Decompiled with CFR 0_114.
 */
package exterminatorJeff.undergroundBiomes.worldGen;

import exterminatorJeff.undergroundBiomes.api.BiomeGenUndergroundBase;
import exterminatorJeff.undergroundBiomes.common.UBiomeCache;
import exterminatorJeff.undergroundBiomes.common.WorldGenManager;

public class BiomeUndergroundCacheBlock {
    public BiomeGenUndergroundBase[] biomes;
    public int xPosition;
    public int zPosition;
    public long lastAccessTime;
    final UBiomeCache theBiomeCache;

    public BiomeUndergroundCacheBlock(UBiomeCache par1BiomeCache, int par2, int par3) {
        this.theBiomeCache = par1BiomeCache;
        this.biomes = new BiomeGenUndergroundBase[256];
        this.xPosition = par2;
        this.zPosition = par3;
        this.theBiomeCache.worldGen.cacheUndergroundBiomeGenAt(this.biomes, par2, par3, 16, 16);
    }

    public BiomeGenUndergroundBase getBiomeGenAt(int par1, int par2) {
        return this.biomes[par1 & 15 | (par2 & 15) << 4];
    }

    public boolean contains(int testedWorldX, int testedWorldZ) {
        if (this.xPosition != testedWorldX >> 4) {
            return false;
        }
        if (this.zPosition != testedWorldZ >> 4) {
            return false;
        }
        return true;
    }
}

