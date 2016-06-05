/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.chunk.Chunk
 *  net.minecraft.world.chunk.IChunkProvider
 */
package exterminatorJeff.undergroundBiomes.worldGen;

import Zeno410Utils.Mutable;
import Zeno410Utils.PlaneLocation;
import Zeno410Utils.Zeno410Logger;
import exterminatorJeff.undergroundBiomes.api.UndergroundBiomesSettings;
import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;
import exterminatorJeff.undergroundBiomes.worldGen.BiomeUndergroundDecorator;
import exterminatorJeff.undergroundBiomes.worldGen.ChunkProviderWrapper;
import java.util.WeakHashMap;
import java.util.logging.Logger;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;

public class UBChunkProvider
extends ChunkProviderWrapper {
    BiomeUndergroundDecorator decorator;
    static Logger logger = new Zeno410Logger("UBChunkProvider").logger();
    WeakHashMap<PlaneLocation, Chunk> generatingChunks = new WeakHashMap();
    int level = 0;
    public final int dimension;

    public UBChunkProvider(IChunkProvider toWrap, BiomeUndergroundDecorator _decorator, int dimension) {
        super(toWrap);
        logger.info("UB generation wrapping " + toWrap.toString());
        this.decorator = _decorator;
        this.dimension = dimension;
    }

    public Chunk func_73154_d(int i, int j) {
        Chunk result;
        PlaneLocation place = new PlaneLocation(i, j);
        if (UndergroundBiomes.instance().settings().newGeneration.value().booleanValue() && (result = this.generatingChunks.get(place)) != null) {
            return result;
        }
        result = super.func_73154_d(i, j);
        if (UndergroundBiomes.instance().settings().newGeneration.value().booleanValue()) {
            this.generatingChunks.put(place, result);
        } else {
            this.decorator.replaceChunkBlocks(result, i, j, this.dimension);
        }
        return result;
    }

    public void func_73153_a(IChunkProvider ichunkprovider, int i, int j) {
        super.func_73153_a(ichunkprovider, i, j);
        if (UndergroundBiomes.instance().settings().newGeneration.value().booleanValue()) {
            Chunk target = ichunkprovider.func_73154_d(i, j);
            if (target.field_76646_k) {
                this.decorator.replaceChunkBlocks(target, i, j, this.dimension);
                this.decorator.replaceChunkOres(ichunkprovider, i, j);
            }
        } else {
            this.decorator.replaceChunkOres(ichunkprovider, i, j);
        }
    }
}

