/*
 * Decompiled with CFR 0_114.
 */
package exterminatorJeff.undergroundBiomes.worldGen;

import exterminatorJeff.undergroundBiomes.api.NamedBlock;
import exterminatorJeff.undergroundBiomes.api.NamedVanillaBlock;
import exterminatorJeff.undergroundBiomes.api.StrataLayer;
import exterminatorJeff.undergroundBiomes.api.UBIDs;
import exterminatorJeff.undergroundBiomes.api.UndergroundBiomesSettings;
import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;
import java.util.ArrayList;

public class BiomeGenStrataLayers {
    public StrataLayer[][] layers = new StrataLayer[30][];
    private final UndergroundBiomesSettings settings;

    public BiomeGenStrataLayers(UndergroundBiomesSettings settings) {
        this.settings = settings;
        if (UndergroundBiomes.harmoniousStrata()) {
            this.createHarmoniousLayers();
        } else {
            this.createLayers();
        }
    }

    public void createLayers() {
        NamedBlock sedimentaryBlockID = UBIDs.sedimentaryStoneName;
        NamedBlock metamorphicBlockID = UBIDs.metamorphicStoneName;
        NamedBlock igneousBlockID = UBIDs.igneousStoneName;
        this.layers[0] = this.generatable(new StrataLayer[]{new StrataLayer(sedimentaryBlockID, 0, 30, 32), new StrataLayer(sedimentaryBlockID, 1, 38, 41), new StrataLayer(sedimentaryBlockID, 6, 65, 70)});
        this.layers[1] = this.generatable(new StrataLayer[]{new StrataLayer(sedimentaryBlockID, 2, 33, 36), new StrataLayer(sedimentaryBlockID, 1, 38, 41), new StrataLayer(sedimentaryBlockID, 5, 60, 62), new StrataLayer(sedimentaryBlockID, 7, 75, 80)});
        this.layers[2] = this.generatable(new StrataLayer[]{new StrataLayer(sedimentaryBlockID, 3, 30, 35), new StrataLayer(sedimentaryBlockID, 4, 26, 29), new StrataLayer(metamorphicBlockID, 2, 70, 74)});
        this.layers[3] = this.generatable(new StrataLayer[]{new StrataLayer(NamedVanillaBlock.sandstone, 0, 40, 43), new StrataLayer(NamedVanillaBlock.sand, 0, 44, 47), new StrataLayer(sedimentaryBlockID, 1, 55, 57)});
        this.layers[4] = this.generatable(new StrataLayer[]{new StrataLayer(sedimentaryBlockID, 7, 29, 33), new StrataLayer(sedimentaryBlockID, 5, 42, 44), new StrataLayer(sedimentaryBlockID, 4, 90, 105)});
        this.layers[5] = this.generatable(new StrataLayer[]{new StrataLayer(sedimentaryBlockID, 2, 33, 35), new StrataLayer(sedimentaryBlockID, 3, 38, 41), new StrataLayer(sedimentaryBlockID, 6, 72, 79)});
        this.layers[6] = this.generatable(new StrataLayer[]{new StrataLayer(metamorphicBlockID, 2, 30, 35), new StrataLayer(sedimentaryBlockID, 0, 39, 44), new StrataLayer(NamedVanillaBlock.sandstone, 0, 52, 54), new StrataLayer(NamedVanillaBlock.sand, 0, 55, 60)});
        this.layers[7] = this.generatable(new StrataLayer[]{new StrataLayer(sedimentaryBlockID, 0, 33, 35), new StrataLayer(sedimentaryBlockID, 3, 45, 49), new StrataLayer(sedimentaryBlockID, 6, 80, 85)});
        this.layers[8] = this.generatable(new StrataLayer[]{new StrataLayer(sedimentaryBlockID, 1, 30, 32), new StrataLayer(metamorphicBlockID, 2, 70, 74), new StrataLayer(sedimentaryBlockID, 4, 75, 79)});
        this.layers[9] = this.generatable(new StrataLayer[]{new StrataLayer(sedimentaryBlockID, 2, 31, 35), new StrataLayer(sedimentaryBlockID, 5, 39, 42), new StrataLayer(sedimentaryBlockID, 7, 62, 65)});
        this.layers[10] = this.generatable(new StrataLayer[]{new StrataLayer(igneousBlockID, 2, 12, 18), new StrataLayer(igneousBlockID, 6, 24, 29), new StrataLayer(metamorphicBlockID, 2, 80, 85)});
        this.layers[11] = this.generatable(new StrataLayer[]{new StrataLayer(igneousBlockID, 5, 13, 22), new StrataLayer(metamorphicBlockID, 6, 29, 36), new StrataLayer(metamorphicBlockID, 3, 80, 128)});
    }

    public void createHarmoniousLayers() {
        NamedBlock sedimentaryBlockID = UBIDs.sedimentaryStoneName;
        NamedBlock metamorphicBlockID = UBIDs.metamorphicStoneName;
        NamedBlock igneousBlockID = UBIDs.igneousStoneName;
        this.layers[0] = this.generatable(new StrataLayer[]{new StrataLayer(sedimentaryBlockID, 0, 30, 32), new StrataLayer(sedimentaryBlockID, 1, 38, 41), new StrataLayer(sedimentaryBlockID, 6, 65, 70)});
        this.layers[5] = this.generatable(new StrataLayer[]{new StrataLayer(sedimentaryBlockID, 2, 33, 36), new StrataLayer(sedimentaryBlockID, 1, 38, 41), new StrataLayer(sedimentaryBlockID, 5, 60, 62), new StrataLayer(sedimentaryBlockID, 7, 75, 80)});
        this.layers[2] = this.generatable(new StrataLayer[]{new StrataLayer(sedimentaryBlockID, 3, 30, 35), new StrataLayer(sedimentaryBlockID, 4, 26, 29), new StrataLayer(metamorphicBlockID, 2, 70, 74)});
        this.layers[3] = this.generatable(new StrataLayer[]{new StrataLayer(NamedVanillaBlock.sandstone, 0, 40, 43), new StrataLayer(NamedVanillaBlock.sand, 0, 44, 47), new StrataLayer(sedimentaryBlockID, 1, 55, 57)});
        this.layers[4] = this.generatable(new StrataLayer[]{new StrataLayer(sedimentaryBlockID, 7, 29, 33), new StrataLayer(sedimentaryBlockID, 5, 42, 44), new StrataLayer(sedimentaryBlockID, 4, 90, 105)});
        this.layers[1] = this.generatable(new StrataLayer[]{new StrataLayer(sedimentaryBlockID, 2, 33, 35), new StrataLayer(sedimentaryBlockID, 3, 38, 41), new StrataLayer(sedimentaryBlockID, 6, 72, 79)});
        this.layers[6] = this.generatable(new StrataLayer[]{new StrataLayer(metamorphicBlockID, 2, 30, 35), new StrataLayer(sedimentaryBlockID, 0, 39, 44), new StrataLayer(NamedVanillaBlock.sandstone, 0, 52, 54), new StrataLayer(NamedVanillaBlock.sand, 0, 55, 60)});
        this.layers[7] = this.generatable(new StrataLayer[]{new StrataLayer(sedimentaryBlockID, 0, 33, 35), new StrataLayer(sedimentaryBlockID, 3, 45, 49), new StrataLayer(sedimentaryBlockID, 4, 80, 85)});
        this.layers[8] = this.generatable(new StrataLayer[]{new StrataLayer(sedimentaryBlockID, 1, 30, 32), new StrataLayer(metamorphicBlockID, 2, 70, 74), new StrataLayer(sedimentaryBlockID, 6, 75, 79)});
        this.layers[9] = this.generatable(new StrataLayer[]{new StrataLayer(sedimentaryBlockID, 2, 31, 35), new StrataLayer(sedimentaryBlockID, 5, 39, 42), new StrataLayer(sedimentaryBlockID, 7, 62, 65)});
        this.layers[10] = this.generatable(new StrataLayer[]{new StrataLayer(igneousBlockID, 2, 12, 18), new StrataLayer(igneousBlockID, 6, 24, 29), new StrataLayer(metamorphicBlockID, 2, 80, 85)});
        this.layers[11] = this.generatable(new StrataLayer[]{new StrataLayer(igneousBlockID, 5, 13, 22), new StrataLayer(metamorphicBlockID, 6, 29, 36), new StrataLayer(metamorphicBlockID, 3, 80, 128)});
    }

    public StrataLayer[] generatable(StrataLayer[] possible) {
        ArrayList<StrataLayer> accepted = new ArrayList<StrataLayer>();
        for (int i = 0; i < possible.length; ++i) {
            NamedBlock block = possible[i].layerBlock;
            int metadata = possible[i].layerMetadataID;
            if (!this.settings.generationAllowed(block, metadata)) continue;
            accepted.add(possible[i]);
        }
        StrataLayer[] result = new StrataLayer[accepted.size()];
        return accepted.toArray(result);
    }
}

