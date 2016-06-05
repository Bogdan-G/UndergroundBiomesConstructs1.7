/*
 * Decompiled with CFR 0_114.
 */
package exterminatorJeff.undergroundBiomes.api;

import Zeno410Utils.Mutable;
import Zeno410Utils.Settings;
import Zeno410Utils.Streamer;
import exterminatorJeff.undergroundBiomes.api.NamedBlock;
import exterminatorJeff.undergroundBiomes.api.NamedVanillaBlock;
import exterminatorJeff.undergroundBiomes.api.UBIDs;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.HashMap;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public final class UndergroundBiomesSettings
extends Settings {
    private final Settings.Category blockCategory;
    private final Settings.Category itemCategory;
    private final Settings.Category generationCategory;
    public final Mutable<Boolean> addOreDictRecipes;
    public final Mutable<Boolean> vanillaStoneBiomes;
    public final Mutable<Boolean> buttonsOn;
    public final Mutable<Boolean> stairsOn;
    public final Mutable<Boolean> wallsOn;
    public final Mutable<Boolean> harmoniousStrata;
    public final Mutable<Boolean> replaceCobblestone;
    public final Mutable<Boolean> imposeUBStone;
    public final Mutable<Boolean> replaceVillageGravel;
    public final Mutable<Boolean> crashOnProblems;
    public final Mutable<Boolean> clearVarsForRecursiveGeneration;
    public final Mutable<Boolean> forceConfigIds;
    public final Mutable<Boolean> ubOres;
    public final Mutable<Integer> biomeSize;
    public final Mutable<String> excludeDimensions;
    public final Mutable<String> includeDimensions;
    public final Mutable<Integer> generateHeight;
    public final Mutable<Integer> vanillaStoneCrafting;
    public final Mutable<Double> hardnessModifier;
    public final Mutable<Double> resistanceModifier;
    public final Mutable<Boolean> ubActive;
    public final Mutable<Boolean> dimensionSpecificSeeds;
    public final Mutable<Boolean> inChunkGeneration;
    public final Mutable<Boolean> newGeneration;
    public final Mutable<String> inChunkGenerationExclude;
    public final Mutable<String> inChunkGenerationInclude;
    public final Mutable<Double> brickHardnessMultiplier;
    public final Mutable<Double> cobbleHardnessMultiplier;
    public final Mutable<Integer> ligniteCoalID;
    public final Mutable<Integer> fossilPieceID;
    public final Mutable<Integer> igneousStoneID;
    public final Mutable<Integer> metamorphicStoneID;
    public final Mutable<Integer> sedimentaryStoneID;
    public final Mutable<Integer> igneousCobblestoneID;
    public final Mutable<Integer> metamorphicCobblestoneID;
    public final Mutable<Integer> igneousStoneBrickID;
    public final Mutable<Integer> metamorphicStoneBrickID;
    public final Mutable<Integer> igneousBrickSlabHalfID;
    public final Mutable<Integer> igneousBrickSlabFullID;
    public final Mutable<Integer> metamorphicBrickSlabHalfID;
    public final Mutable<Integer> metamorphicBrickSlabFullID;
    public final Mutable<Integer> igneousStoneSlabHalfID;
    public final Mutable<Integer> igneousStoneSlabFullID;
    public final Mutable<Integer> metamorphicStoneSlabHalfID;
    public final Mutable<Integer> metamorphicStoneSlabFullID;
    public final Mutable<Integer> igneousCobblestoneSlabHalfID;
    public final Mutable<Integer> igneousCobblestoneSlabFullID;
    public final Mutable<Integer> metamorphicCobblestoneSlabHalfID;
    public final Mutable<Integer> metamorphicCobblestoneSlabFullID;
    public final Mutable<Integer> sedimentaryStoneSlabHalfID;
    public final Mutable<Integer> sedimentaryStoneSlabFullID;
    public final Mutable<Integer> stoneStairID;
    public final Mutable<Integer> stoneWallID;
    public final Mutable<Integer> stoneButtonID;
    private final HashMap<NamedBlock, HashMap<Integer, Mutable<Boolean>>> stoneGenerationSettings;

    public static Streamer<UndergroundBiomesSettings> streamer(final UndergroundBiomesSettings setting) {
        return new Streamer<UndergroundBiomesSettings>(){

            @Override
            public UndergroundBiomesSettings readFrom(DataInput input) throws IOException {
                setting.readFrom(input);
                return setting;
            }

            @Override
            public void writeTo(UndergroundBiomesSettings written, DataOutput output) throws IOException {
                written.writeTo(output);
            }
        };
    }

    public UndergroundBiomesSettings(String[] igneousNames, String[] metamorphicNames, String[] sedimentaryNames) {
        this.blockCategory = this.category("block");
        this.itemCategory = this.category("item");
        this.generationCategory = this.category("generation");
        this.addOreDictRecipes = this.general().booleanSetting("oreDictifyStone", true, "Modify all recipes to include Underground Biomes blocks");
        this.vanillaStoneBiomes = this.general().booleanSetting("vanillaStoneBiomes", false, "Will cause sharp biome transitions if changed while playing the same world");
        this.buttonsOn = this.general().booleanSetting("UndergroundBiomesButtons", true, "Provide Buttons for non-brick Underground Biomes blocks");
        this.stairsOn = this.general().booleanSetting("UndergroundBiomesStairs", true, "Provide Stairs for Underground Biomes blocks");
        this.wallsOn = this.general().booleanSetting("UndergroundBiomesWalls", true, "Provide Walls for Underground Biomes blocks");
        this.harmoniousStrata = this.general().booleanSetting("HarmoniousStrata", false, "Avoid jarring strata transitions");
        this.replaceCobblestone = this.general().booleanSetting("replaceCobblestone", true, "Swap vanilla cobble and slabs with Underground Biomes where appropriate, plus some village changes");
        this.imposeUBStone = this.general().booleanSetting("ImposeUBStone", "Impose UB stone on other mods specially programmed for (currently Highlands)", true);
        this.replaceVillageGravel = this.general().booleanSetting("ReplaceVillageGravel", false, "Replace village gravel with brick");
        this.crashOnProblems = this.general().booleanSetting("CrashOnProblems", false, "Crash rather than try to get by when encountering problems");
        this.clearVarsForRecursiveGeneration = this.general().booleanSetting("clearVarsForRecursiveGeneration", false, "Clear the world var in BiomeGenBase for recursive generation");
        this.forceConfigIds = this.general().booleanSetting("ForceConfigIds", false, "(for worlds created pre-1.7) Force IDs to config values");
        this.ubOres = this.general().booleanSetting("UBifyOres", true, "Convert ores to have Underground Biomes stone backgrounds");
        this.biomeSize = this.general().intSetting("biomeSize", 3, "Warning: exponential");
        this.excludeDimensions = this.general().stringSetting("excludeDimensionIDs", "-1,1", "Comma-separated list of dimension IDs, used only if include list is *");
        this.includeDimensions = this.general().stringSetting("includeDimensionIDs", "*", "Comma-separated list of dimension IDs, put * to use exclude list");
        this.generateHeight = this.general().intSetting("generateHeight", 256, "Highest block to generated UB stone for");
        this.vanillaStoneCrafting = this.general().intSetting("vanillaStoneCrafting", 4, "0 = none; 1 = one rock; 2 = with redstone; 3 = 2x2 stone, lose 3; 4 = 2x2 stone");
        this.hardnessModifier = this.general().doubleSetting("hardnessModifier", 1.5, "Increase to make stone longer to mine. Normal is 1.5");
        this.resistanceModifier = this.general().doubleSetting("resistanceModifier", 6.0, "Increase to make stone more resistant to explosions. Normal is 6.0");
        this.ubActive = this.general().booleanSetting("undergroundBiomesActive", "True if Underground Biomes is supposed to replace stones", true);
        this.dimensionSpecificSeeds = this.general().booleanSetting("DimensionSpecificSeeds", false, "Use different seeds in different dimensions");
        this.inChunkGeneration = this.general().booleanSetting("InChunkGeneration", true, "Change stones during chunk generation");
        this.newGeneration = this.general().booleanSetting("newGeneration", false, "Run generation as late as possible (slower but more compatible). Needs inChunk on");
        this.inChunkGenerationExclude = this.general().stringSetting("inChunkDimensionExclusions", "-1,1", "Comma-separated list of dimension to only use old decoration-phase generation, used only if inclusion list is *");
        this.inChunkGenerationInclude = this.general().stringSetting("inChunkDimensionInclusions", "0", "Comma-separated list of dimension IDs to allow new chunk-phase decoration, put * to use exclusion list");
        this.brickHardnessMultiplier = this.general().doubleSetting("brickHardnessMultiplier", 1.0, "UB brick hardness is this multiple of UB stone");
        this.cobbleHardnessMultiplier = this.general().doubleSetting("cobbleHardnessMultiplier", 1.3333333, "UB cobble hardness is this multiple of UB stone");
        this.ligniteCoalID = this.blockCategory.intSetting("Lignite Item ID:", 5500);
        this.fossilPieceID = this.itemCategory.intSetting("fossilPiece", 5501);
        this.igneousStoneID = this.blockCategory.intSetting("Igneous Stone ID:", 209);
        this.metamorphicStoneID = this.blockCategory.intSetting("Metamorphic Stone ID:", 210);
        this.sedimentaryStoneID = this.blockCategory.intSetting("Sedimentary Stone ID:", 211);
        this.igneousCobblestoneID = this.blockCategory.intSetting("Igneous Cobblestone ID:", 200);
        this.metamorphicCobblestoneID = this.blockCategory.intSetting("Metamorphic Cobblestone ID:", 201);
        this.igneousStoneBrickID = this.blockCategory.intSetting("Igneous Brick ID:", 202);
        this.metamorphicStoneBrickID = this.blockCategory.intSetting("Metamorphic Brick ID:", 203);
        this.igneousBrickSlabHalfID = this.blockCategory.intSetting("Igneous Stone Brick Slab ID (half):", 205);
        this.igneousBrickSlabFullID = this.blockCategory.intSetting("Igneous Stone Brick Slab ID (full):", 206);
        this.metamorphicBrickSlabHalfID = this.blockCategory.intSetting("Metamorphic Stone Brick Slab ID (half):", 207);
        this.metamorphicBrickSlabFullID = this.blockCategory.intSetting("Metamorphic Stone Brick Slab ID (full):", 208);
        this.igneousStoneSlabHalfID = this.blockCategory.intSetting("Igneous Stone Slab ID (half):", 215);
        this.igneousStoneSlabFullID = this.blockCategory.intSetting("Igneous Stone Slab ID (full):", 216);
        this.metamorphicStoneSlabHalfID = this.blockCategory.intSetting("Metamorphic Stone Slab ID (half):", 217);
        this.metamorphicStoneSlabFullID = this.blockCategory.intSetting("Metamorphic Stone Slab ID (full):", 218);
        this.igneousCobblestoneSlabHalfID = this.blockCategory.intSetting("Igneous Stone Cobblestone Slab ID (half):", 219);
        this.igneousCobblestoneSlabFullID = this.blockCategory.intSetting("Igneous Stone Cobblestone Slab ID (full):", 220);
        this.metamorphicCobblestoneSlabHalfID = this.blockCategory.intSetting("Metamorphic Stone Cobblestone Slab ID (half):", 221);
        this.metamorphicCobblestoneSlabFullID = this.blockCategory.intSetting("Metamorphic Stone Cobblestone Slab ID (full):", 222);
        this.sedimentaryStoneSlabHalfID = this.blockCategory.intSetting("Sedimentary Stone Slab ID (half):", 223);
        this.sedimentaryStoneSlabFullID = this.blockCategory.intSetting("Sedimentary Stone Slab ID (full):", 224);
        this.stoneStairID = this.blockCategory.intSetting("Universal Biomes Stairs ID:", 212);
        this.stoneWallID = this.blockCategory.intSetting("Universal Biomes Wall ID:", 213);
        this.stoneButtonID = this.blockCategory.intSetting("Universal Biomes Button ID:", 214);
        this.stoneGenerationSettings = new HashMap();
        this.setActivations(UBIDs.igneousStoneName, "igneous stone", igneousNames);
        this.setActivations(UBIDs.metamorphicStoneName, "metamorphic stone", metamorphicNames);
        this.setActivations(UBIDs.sedimentaryStoneName, "sedimentary stone", sedimentaryNames);
        this.setActivation(NamedVanillaBlock.sand, 0);
        this.setActivation(NamedVanillaBlock.sandstone, 0);
        this.setActivation(NamedVanillaBlock.stone, 0);
    }

    private void setActivations(NamedBlock block, String type, String[] names) {
        HashMap<Integer, Mutable<Boolean>> blockActivations = new HashMap<Integer, Mutable<Boolean>>();
        this.stoneGenerationSettings.put(block, blockActivations);
        for (int i = 0; i < names.length; ++i) {
            Mutable<Boolean> generationConfig = this.generationCategory.booleanSetting("Generate " + type + " metadata " + i + ", " + names[i], true, "");
            blockActivations.put(i, generationConfig);
        }
    }

    private void setActivation(NamedBlock block, int metadata) {
        HashMap<Integer, Mutable<Boolean>> blockActivations = new HashMap<Integer, Mutable<Boolean>>();
        this.stoneGenerationSettings.put(block, blockActivations);
        Mutable<Boolean> generationConfig = this.generationCategory.booleanSetting("Generate " + block.internal() + ", metadata " + metadata, true, "");
        blockActivations.put(metadata, generationConfig);
    }

    public final boolean generationAllowed(NamedBlock block, int metadata) {
        HashMap<Integer, Mutable<Boolean>> info = this.stoneGenerationSettings.get(block);
        if (info == null) {
            return true;
        }
        return info.get(metadata).value();
    }

}

