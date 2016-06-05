/*
 * Decompiled with CFR 0_114.
 */
package exterminatorJeff.undergroundBiomes.api;

import exterminatorJeff.undergroundBiomes.api.NamedBlock;
import exterminatorJeff.undergroundBiomes.api.PerlinNoiseGenerator;
import exterminatorJeff.undergroundBiomes.api.StrataLayer;
import exterminatorJeff.undergroundBiomes.api.UBStoneCodes;

public class BiomeGenUndergroundBase {
    public String biomeName = "";
    public final int biomeID;
    public boolean hasStrata = false;
    public StrataLayer[] strata;
    public final PerlinNoiseGenerator strataNoise;
    public final UBStoneCodes fillerBlockCodes;

    public BiomeGenUndergroundBase(int ID, NamedBlock filler, int metadataValue, BiomeGenUndergroundBase[] biomeList) {
        this.biomeID = ID;
        this.fillerBlockCodes = new UBStoneCodes(filler, metadataValue);
        this.strataNoise = new PerlinNoiseGenerator(1);
        biomeList[ID] = this;
    }

    public BiomeGenUndergroundBase(int ID, NamedBlock filler, int metadataValue, BiomeGenUndergroundBase[] biomeList, StrataLayer[] strataLayers) {
        this.biomeID = ID;
        this.fillerBlockCodes = new UBStoneCodes(filler, metadataValue);
        this.strataNoise = new PerlinNoiseGenerator(1);
        biomeList[ID] = this;
        this.AddStrataLayers(strataLayers);
    }

    public BiomeGenUndergroundBase AddStrataLayers(StrataLayer[] strata) {
        this.hasStrata = true;
        this.strata = strata;
        return this;
    }

    public UBStoneCodes getStrataBlockAtLayer(int y) {
        for (int i = 0; i < this.strata.length; ++i) {
            if (!this.strata[i].valueIsInLayer(y)) continue;
            return this.strata[i].codes;
        }
        return this.fillerBlockCodes;
    }

    public BiomeGenUndergroundBase setName(String name) {
        this.biomeName = name;
        return this;
    }
}

