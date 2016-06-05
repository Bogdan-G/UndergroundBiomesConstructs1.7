/*
 * Decompiled with CFR 0_114.
 */
package exterminatorJeff.undergroundBiomes.worldGen;

import exterminatorJeff.undergroundBiomes.api.BiomeGenUndergroundBase;
import exterminatorJeff.undergroundBiomes.api.NamedBlock;
import exterminatorJeff.undergroundBiomes.api.NamedVanillaBlock;
import exterminatorJeff.undergroundBiomes.api.StrataLayer;
import exterminatorJeff.undergroundBiomes.api.UBIDs;
import exterminatorJeff.undergroundBiomes.api.UBStoneCodes;
import exterminatorJeff.undergroundBiomes.api.UndergroundBiomeSet;
import exterminatorJeff.undergroundBiomes.api.UndergroundBiomesSettings;
import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;
import exterminatorJeff.undergroundBiomes.worldGen.BiomeGenStrataLayers;
import java.util.ArrayList;

public class StandardUndergroundBiomeSet
extends UndergroundBiomeSet {
    private final UndergroundBiomesSettings settings;
    NamedBlock igneousID = UBIDs.igneousStoneName;
    public final BiomeGenUndergroundBase igneous1;
    public final BiomeGenUndergroundBase igneous2;
    public final BiomeGenUndergroundBase igneous3;
    public final BiomeGenUndergroundBase igneous4;
    public final BiomeGenUndergroundBase igneous5;
    public final BiomeGenUndergroundBase igneous6;
    public final BiomeGenUndergroundBase igneous7;
    public final BiomeGenUndergroundBase igneous8;
    public final BiomeGenUndergroundBase igneous9;
    public final BiomeGenUndergroundBase igneous10;
    public final BiomeGenUndergroundBase igneous11;
    public final BiomeGenUndergroundBase igneous12;
    public final BiomeGenUndergroundBase igneous13;
    public final BiomeGenUndergroundBase igneous14;
    public final BiomeGenUndergroundBase igneous15;
    public final BiomeGenUndergroundBase igneous16;
    static NamedBlock metamorphicID = UBIDs.metamorphicStoneName;
    public final BiomeGenUndergroundBase metamorphic1;
    public final BiomeGenUndergroundBase metamorphic2;
    public final BiomeGenUndergroundBase metamorphic3;
    public final BiomeGenUndergroundBase metamorphic4;
    public final BiomeGenUndergroundBase metamorphic5;
    public final BiomeGenUndergroundBase metamorphic6;
    public final BiomeGenUndergroundBase metamorphic7;
    public final BiomeGenUndergroundBase metamorphic8;
    public final BiomeGenUndergroundBase metamorphic9;
    public final BiomeGenUndergroundBase metamorphic10;
    public final BiomeGenUndergroundBase metamorphic11;
    public final BiomeGenUndergroundBase metamorphic12;
    public final BiomeGenUndergroundBase metamorphic13;
    public final BiomeGenUndergroundBase metamorphic14;
    public final BiomeGenUndergroundBase metamorphic15;
    public final BiomeGenUndergroundBase metamorphic16;
    public final BiomeGenUndergroundBase vanillaStone1;
    public final BiomeGenUndergroundBase vanillaStone2;
    public final BiomeGenUndergroundBase vanillaStone3;
    public final BiomeGenUndergroundBase vanillaStone4;

    public StandardUndergroundBiomeSet(UndergroundBiomesSettings settings) {
        super(new BiomeGenStrataLayers((UndergroundBiomesSettings)settings).layers);
        this.igneous1 = new BiomeGenUndergroundBase(0, this.igneousID, 0, this.biomeList).setName("Igneous").AddStrataLayers(this.strataLayers[0]);
        this.igneous2 = new BiomeGenUndergroundBase(1, this.igneousID, 1, this.biomeList).setName("Igneous").AddStrataLayers(this.strataLayers[1]);
        this.igneous3 = new BiomeGenUndergroundBase(2, this.igneousID, 2, this.biomeList).setName("Igneous").AddStrataLayers(this.strataLayers[2]);
        this.igneous4 = new BiomeGenUndergroundBase(3, this.igneousID, 3, this.biomeList).setName("Igneous").AddStrataLayers(this.strataLayers[3]);
        this.igneous5 = new BiomeGenUndergroundBase(4, this.igneousID, 4, this.biomeList).setName("Igneous").AddStrataLayers(this.strataLayers[4]);
        this.igneous6 = new BiomeGenUndergroundBase(5, this.igneousID, 5, this.biomeList).setName("Igneous").AddStrataLayers(this.strataLayers[5]);
        this.igneous7 = new BiomeGenUndergroundBase(6, this.igneousID, 6, this.biomeList).setName("Igneous").AddStrataLayers(this.strataLayers[6]);
        this.igneous8 = new BiomeGenUndergroundBase(7, this.igneousID, 7, this.biomeList).setName("Igneous").AddStrataLayers(this.strataLayers[7]);
        this.igneous9 = new BiomeGenUndergroundBase(8, this.igneousID, 0, this.biomeList).setName("Igneous").AddStrataLayers(this.strataLayers[8]);
        this.igneous10 = new BiomeGenUndergroundBase(9, this.igneousID, 1, this.biomeList).setName("Igneous").AddStrataLayers(this.strataLayers[9]);
        this.igneous11 = new BiomeGenUndergroundBase(10, this.igneousID, 2, this.biomeList).setName("Igneous").AddStrataLayers(this.strataLayers[0]);
        this.igneous12 = new BiomeGenUndergroundBase(11, this.igneousID, 3, this.biomeList).setName("Igneous").AddStrataLayers(this.strataLayers[1]);
        this.igneous13 = new BiomeGenUndergroundBase(12, this.igneousID, 4, this.biomeList).setName("Igneous").AddStrataLayers(this.strataLayers[2]);
        this.igneous14 = new BiomeGenUndergroundBase(13, this.igneousID, 5, this.biomeList).setName("Igneous").AddStrataLayers(this.strataLayers[3]);
        this.igneous15 = new BiomeGenUndergroundBase(14, this.igneousID, 6, this.biomeList).setName("Igneous").AddStrataLayers(this.strataLayers[4]);
        this.igneous16 = new BiomeGenUndergroundBase(15, this.igneousID, 7, this.biomeList).setName("Igneous").AddStrataLayers(this.strataLayers[5]);
        this.metamorphic1 = new BiomeGenUndergroundBase(16, metamorphicID, 0, this.biomeList).setName("Metamorphic").AddStrataLayers(this.strataLayers[6]);
        this.metamorphic2 = new BiomeGenUndergroundBase(17, metamorphicID, 1, this.biomeList).setName("Metamorphic").AddStrataLayers(this.strataLayers[7]);
        this.metamorphic3 = new BiomeGenUndergroundBase(18, metamorphicID, 1, this.biomeList).setName("Metamorphic").AddStrataLayers(this.strataLayers[8]);
        this.metamorphic4 = new BiomeGenUndergroundBase(19, metamorphicID, 3, this.biomeList).setName("Metamorphic").AddStrataLayers(this.strataLayers[9]);
        this.metamorphic5 = new BiomeGenUndergroundBase(20, metamorphicID, 4, this.biomeList).setName("Metamorphic").AddStrataLayers(this.strataLayers[0]);
        this.metamorphic6 = new BiomeGenUndergroundBase(21, metamorphicID, 5, this.biomeList).setName("Metamorphic").AddStrataLayers(this.strataLayers[1]);
        this.metamorphic7 = new BiomeGenUndergroundBase(22, metamorphicID, 6, this.biomeList).setName("Metamorphic").AddStrataLayers(this.strataLayers[2]);
        this.metamorphic8 = new BiomeGenUndergroundBase(23, metamorphicID, 7, this.biomeList).setName("Metamorphic").AddStrataLayers(this.strataLayers[3]);
        this.metamorphic9 = new BiomeGenUndergroundBase(24, metamorphicID, 0, this.biomeList).setName("Metamorphic").AddStrataLayers(this.strataLayers[4]);
        this.metamorphic10 = new BiomeGenUndergroundBase(25, metamorphicID, 1, this.biomeList).setName("Metamorphic").AddStrataLayers(this.strataLayers[5]);
        this.metamorphic11 = new BiomeGenUndergroundBase(26, metamorphicID, 1, this.biomeList).setName("Metamorphic").AddStrataLayers(this.strataLayers[6]);
        this.metamorphic12 = new BiomeGenUndergroundBase(27, metamorphicID, 3, this.biomeList).setName("Metamorphic").AddStrataLayers(this.strataLayers[7]);
        this.metamorphic13 = new BiomeGenUndergroundBase(28, metamorphicID, 4, this.biomeList).setName("Metamorphic").AddStrataLayers(this.strataLayers[8]);
        this.metamorphic14 = new BiomeGenUndergroundBase(29, metamorphicID, 5, this.biomeList).setName("Metamorphic").AddStrataLayers(this.strataLayers[9]);
        this.metamorphic15 = new BiomeGenUndergroundBase(30, metamorphicID, 6, this.biomeList).setName("Metamorphic").AddStrataLayers(this.strataLayers[0]);
        this.metamorphic16 = new BiomeGenUndergroundBase(31, metamorphicID, 7, this.biomeList).setName("Metamorphic").AddStrataLayers(this.strataLayers[1]);
        this.vanillaStone1 = new BiomeGenUndergroundBase(32, NamedVanillaBlock.stone, 0, this.biomeList).setName("Metamorphic").AddStrataLayers(this.strataLayers[0]);
        this.vanillaStone2 = new BiomeGenUndergroundBase(33, NamedVanillaBlock.stone, 0, this.biomeList).setName("Metamorphic").AddStrataLayers(this.strataLayers[1]);
        this.vanillaStone3 = new BiomeGenUndergroundBase(34, NamedVanillaBlock.stone, 0, this.biomeList).setName("Metamorphic").AddStrataLayers(this.strataLayers[2]);
        this.vanillaStone4 = new BiomeGenUndergroundBase(35, NamedVanillaBlock.stone, 0, this.biomeList).setName("Metamorphic").AddStrataLayers(this.strataLayers[3]);
        this.settings = settings;
    }

    public BiomeGenUndergroundBase[] allowedBiomes() {
        BiomeGenUndergroundBase[] allowedBiomes = null;
        allowedBiomes = !UndergroundBiomes.vanillaStoneBiomes() ? new BiomeGenUndergroundBase[]{this.igneous1, this.igneous2, this.igneous3, this.igneous4, this.igneous5, this.igneous6, this.igneous7, this.igneous8, this.igneous9, this.igneous10, this.igneous11, this.igneous12, this.igneous13, this.igneous14, this.igneous15, this.igneous16, this.metamorphic1, this.metamorphic2, this.metamorphic3, this.metamorphic4, this.metamorphic5, this.metamorphic6, this.metamorphic7, this.metamorphic8, this.metamorphic9, this.metamorphic10, this.metamorphic11, this.metamorphic12, this.metamorphic13, this.metamorphic14, this.metamorphic15, this.metamorphic16} : new BiomeGenUndergroundBase[]{this.igneous1, this.igneous2, this.igneous3, this.igneous4, this.igneous5, this.igneous6, this.igneous7, this.igneous8, this.igneous9, this.igneous10, this.igneous11, this.igneous12, this.igneous13, this.igneous14, this.igneous15, this.igneous16, this.metamorphic1, this.metamorphic2, this.metamorphic3, this.metamorphic4, this.metamorphic5, this.metamorphic6, this.metamorphic7, this.metamorphic8, this.metamorphic9, this.metamorphic10, this.metamorphic11, this.metamorphic12, this.metamorphic13, this.metamorphic14, this.metamorphic15, this.metamorphic16, this.vanillaStone1, this.vanillaStone2, this.vanillaStone3, this.vanillaStone4};
        return this.generatable(allowedBiomes);
    }

    public BiomeGenUndergroundBase[] generatable(BiomeGenUndergroundBase[] possible) {
        ArrayList<BiomeGenUndergroundBase> accepted = new ArrayList<BiomeGenUndergroundBase>();
        for (int i = 0; i < possible.length; ++i) {
            NamedBlock block = possible[i].fillerBlockCodes.name;
            int metadata = possible[i].fillerBlockCodes.metadata;
            if (!this.settings.generationAllowed(block, metadata)) continue;
            accepted.add(possible[i]);
        }
        BiomeGenUndergroundBase[] result = new BiomeGenUndergroundBase[accepted.size()];
        return accepted.toArray(result);
    }
}

