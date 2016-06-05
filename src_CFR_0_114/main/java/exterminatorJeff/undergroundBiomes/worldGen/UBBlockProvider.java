/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.chunk.IChunkProvider
 */
package exterminatorJeff.undergroundBiomes.worldGen;

import exterminatorJeff.undergroundBiomes.api.BiomeGenUndergroundBase;
import exterminatorJeff.undergroundBiomes.api.BlockCodes;
import exterminatorJeff.undergroundBiomes.api.PerlinNoiseGenerator;
import exterminatorJeff.undergroundBiomes.api.StrataLayer;
import exterminatorJeff.undergroundBiomes.api.UBStoneCodes;
import exterminatorJeff.undergroundBiomes.api.UBStrataColumn;
import exterminatorJeff.undergroundBiomes.api.UBStrataColumnProvider;
import exterminatorJeff.undergroundBiomes.common.WorldGenManager;
import exterminatorJeff.undergroundBiomes.worldGen.BiomeUndergroundCacheBlock;
import net.minecraft.world.chunk.IChunkProvider;

public class UBBlockProvider
implements UBStrataColumnProvider {
    private BiomeUndergroundCacheBlock chunkBiomeArray;
    private final WorldGenManager worldGen;

    public UBBlockProvider(WorldGenManager _worldGen) {
        this.worldGen = _worldGen;
        this.chunkBiomeArray = this.worldGen.chunkBiomeCache(0, 0);
    }

    private UBStrataColumn strataColumn(final StrataLayer[] strata, final UBStoneCodes fillerBlockCodes, final int variation) {
        return new UBStrataColumn(){

            public UBStoneCodes stone(int y) {
                for (int i = 0; i < strata.length; ++i) {
                    if (!strata[i].valueIsInLayer(y + variation)) continue;
                    return strata[i].codes;
                }
                return fillerBlockCodes;
            }

            public BlockCodes cobblestone(int height) {
                return this.stone((int)height).onDrop;
            }

            public BlockCodes cobblestone() {
                return this.stone().onDrop;
            }

            public BlockCodes stone() {
                return fillerBlockCodes;
            }
        };
    }

    public UBStrataColumn strataColumn(int x, int z) {
        if (!this.chunkBiomeArray.contains(x, z)) {
            this.chunkBiomeArray = this.worldGen.chunkBiomeCache(x, z);
        }
        BiomeGenUndergroundBase biome = this.chunkBiomeArray.getBiomeGenAt(x, z);
        int variation = (int)(biome.strataNoise.noise((double)x / 55.533, (double)z / 55.533, 3, 1.0, 0.5) * 10.0 - 5.0);
        return this.strataColumn(biome.strata, biome.fillerBlockCodes, variation);
    }

    public boolean inChunkGenerationAllowed() {
        return this.worldGen.inChunkGenerationAllowed();
    }

    public IChunkProvider UBChunkProvider(IChunkProvider wrapped) {
        return this.worldGen.UBChunkProvider(wrapped);
    }

}

