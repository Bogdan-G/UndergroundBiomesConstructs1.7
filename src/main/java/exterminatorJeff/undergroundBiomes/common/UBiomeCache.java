package exterminatorJeff.undergroundBiomes.common;

import exterminatorJeff.undergroundBiomes.api.BiomeGenUndergroundBase;
import exterminatorJeff.undergroundBiomes.common.WorldGenManager;
import exterminatorJeff.undergroundBiomes.worldGen.BiomeUndergroundCacheBlock;
//import java.util.ArrayList;
import java.util.List;
//import net.minecraft.util.LongHashMap;
import cern.colt.map.OpenLongObjectHashMap;
import org.eclipse.collections.impl.list.mutable.FastList;

public class UBiomeCache{

    private List cacheUnderground = new FastList();
    private OpenLongObjectHashMap undergroundCacheMap = new OpenLongObjectHashMap();
    public WorldGenManager worldGen;
    
    public UBiomeCache(WorldGenManager gen){
        this.worldGen = gen;
    }
    
    public BiomeUndergroundCacheBlock getUndergroundBiomeCacheBlock(int par1, int par2)
    {

        // this used to downshift to chunk coordinates. But it wasn't always called with chunk coordinates///
        // so I took that out.
        //par1 >>= 4;
        //par2 >>= 4;
        long var3 = (long)par1 & 4294967295L | ((long)par2 & 4294967295L) << 32;
        BiomeUndergroundCacheBlock var5 = (BiomeUndergroundCacheBlock)this.undergroundCacheMap.get(var3);

        if (var5 == null)
        {
            var5 = new BiomeUndergroundCacheBlock(this, par1, par2);
            this.undergroundCacheMap.put(var3, var5);
            this.cacheUnderground.add(var5);
        }

        var5.lastAccessTime = System.currentTimeMillis();
        return var5;
    }
    
    public BiomeGenUndergroundBase getUndergroundBiomeGetAt(int xPos, int yPos){
        return this.getUndergroundBiomeCacheBlock(xPos, yPos).getBiomeGenAt(xPos, yPos);
    }
    
    public BiomeGenUndergroundBase[] getCachedUndergroundBiomes(int xPos, int yPos){
        return this.getUndergroundBiomeCacheBlock(xPos, yPos).biomes;
    }
    
}
