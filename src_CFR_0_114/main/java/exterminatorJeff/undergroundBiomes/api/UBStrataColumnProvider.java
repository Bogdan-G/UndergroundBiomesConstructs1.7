/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.chunk.IChunkProvider
 */
package exterminatorJeff.undergroundBiomes.api;

import exterminatorJeff.undergroundBiomes.api.UBStrataColumn;
import net.minecraft.world.chunk.IChunkProvider;

public interface UBStrataColumnProvider {
    public UBStrataColumn strataColumn(int var1, int var2);

    public IChunkProvider UBChunkProvider(IChunkProvider var1);

    public boolean inChunkGenerationAllowed();
}

