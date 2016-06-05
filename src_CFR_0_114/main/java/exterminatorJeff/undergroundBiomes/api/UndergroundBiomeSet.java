/*
 * Decompiled with CFR 0_114.
 */
package exterminatorJeff.undergroundBiomes.api;

import exterminatorJeff.undergroundBiomes.api.BiomeGenUndergroundBase;
import exterminatorJeff.undergroundBiomes.api.StrataLayer;

public abstract class UndergroundBiomeSet {
    public final StrataLayer[][] strataLayers;
    public final BiomeGenUndergroundBase[] biomeList = new BiomeGenUndergroundBase[256];

    public UndergroundBiomeSet(StrataLayer[][] strataLayers) {
        this.strataLayers = strataLayers;
    }

    public abstract BiomeGenUndergroundBase[] allowedBiomes();
}

