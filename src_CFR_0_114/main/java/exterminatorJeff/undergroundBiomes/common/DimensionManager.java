/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldProvider
 *  net.minecraft.world.WorldServer
 *  net.minecraft.world.biome.WorldChunkManager
 *  net.minecraftforge.event.terraingen.BiomeEvent
 *  net.minecraftforge.event.terraingen.BiomeEvent$GetVillageBlockID
 *  net.minecraftforge.event.terraingen.BiomeEvent$GetVillageBlockMeta
 *  net.minecraftforge.event.terraingen.DecorateBiomeEvent
 *  net.minecraftforge.event.terraingen.DecorateBiomeEvent$Post
 *  net.minecraftforge.event.terraingen.DecorateBiomeEvent$Pre
 *  net.minecraftforge.event.terraingen.PopulateChunkEvent
 *  net.minecraftforge.event.terraingen.PopulateChunkEvent$Post
 *  net.minecraftforge.event.terraingen.PopulateChunkEvent$Pre
 */
package exterminatorJeff.undergroundBiomes.common;

import Zeno410Utils.Acceptor;
import Zeno410Utils.Mutable;
import Zeno410Utils.Zeno410Logger;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import exterminatorJeff.undergroundBiomes.api.UBAPIHook;
import exterminatorJeff.undergroundBiomes.api.UBDimensionalStrataColumnProvider;
import exterminatorJeff.undergroundBiomes.api.UBSetProviderRegistry;
import exterminatorJeff.undergroundBiomes.api.UBStrataColumnProvider;
import exterminatorJeff.undergroundBiomes.api.UndergroundBiomeSet;
import exterminatorJeff.undergroundBiomes.api.UndergroundBiomeSetProvider;
import exterminatorJeff.undergroundBiomes.api.UndergroundBiomesSettings;
import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;
import exterminatorJeff.undergroundBiomes.common.WorldGenManager;
import exterminatorJeff.undergroundBiomes.constructs.util.Consumable;
import exterminatorJeff.undergroundBiomes.constructs.util.DimensionSet;
import exterminatorJeff.undergroundBiomes.worldGen.OreUBifier;
import exterminatorJeff.undergroundBiomes.worldGen.StandardUndergroundBiomeSet;
import exterminatorJeff.undergroundBiomes.worldGen.UBBlockProvider;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraftforge.event.terraingen.BiomeEvent;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;

public class DimensionManager
implements UBSetProviderRegistry {
    public static Logger logger = new Zeno410Logger("DimensionManager").logger();
    private Map<Integer, WorldGenManager> worldGenManagers = new HashMap<Integer, WorldGenManager>();
    private boolean serverAdjusted = false;
    private boolean dimensionSpecificSeeds = false;
    private boolean inChunkGeneration;
    private Acceptor<Boolean> inChunkGeneratorFollower;
    private final DimensionSet.Include includeDimensionIDs;
    private final DimensionSet.Exclude excludeDimensionIDs;
    private final Consumable<String> includeDimensions;
    private final Consumable<String> excludeDimensions;
    private final DimensionSet.Include inChunkGenerationIncludeIDs;
    private final DimensionSet.Exclude inChunkGenerationExcludeIDs;
    private final Consumable<String> inChunkGenerationInclude;
    private final Consumable<String> inChunkGenerationExclude;
    private List<Integer> inChunkDimensionIDs;
    private UndergroundBiomeSet cachedBiomeSet;
    private HashMap<Integer, UndergroundBiomeSet> dimensionalBiomeSets;
    private final OreUBifier oreUBifier;
    private final UndergroundBiomesSettings settings;
    private WorldGenManager villageWorldGenManager;
    private ArrayList<UndergroundBiomeSetProvider> ubSetProviders;

    public boolean inChunkGeneration() {
        return this.inChunkGeneration;
    }

    private UndergroundBiomeSet biomeSet(int dimension) {
        UndergroundBiomeSet result = this.dimensionalBiomeSets.get(dimension);
        if (result == null) {
            if (this.cachedBiomeSet == null) {
                this.cachedBiomeSet = new StandardUndergroundBiomeSet(this.settings);
            }
            result = this.cachedBiomeSet;
        }
        for (UndergroundBiomeSetProvider provider : this.ubSetProviders) {
            UndergroundBiomeSet changedResult = provider.modifiedBiomeSet(dimension, this.dimensionSeed(dimension), result);
            if (changedResult == null) continue;
            result = changedResult;
        }
        return result;
    }

    public long dimensionSeed(int dimension) {
        if (this.dimensionSpecificSeeds) {
            return UndergroundBiomes.worldSeed + (long)(dimension * 100);
        }
        return UndergroundBiomes.worldSeed;
    }

    public WorldGenManager worldGenManager(int dimension) {
        WorldGenManager result = this.worldGenManagers.get(dimension);
        if (result == null) {
            this.oreUBifier.renewBlockReplacers();
            result = new WorldGenManager(this.dimensionSeed(dimension), dimension, this.oreUBifier, this.biomeSet(dimension), this.ubGenerationAllowed(dimension));
            this.worldGenManagers.put(dimension, result);
        }
        return result;
    }

    public DimensionManager(UndergroundBiomesSettings settings, OreUBifier oreUBifier) {
        this.inChunkGeneratorFollower = new Acceptor<Boolean>(){

            @Override
            public void accept(Boolean accepted) {
                DimensionManager.this.inChunkGeneration = accepted;
                DimensionManager.logger.info("Dimensional in-chunk " + accepted);
            }
        };
        this.inChunkDimensionIDs = new ArrayList<Integer>();
        this.dimensionalBiomeSets = new HashMap();
        this.ubSetProviders = new ArrayList();
        this.settings = settings;
        this.dimensionSpecificSeeds = settings.dimensionSpecificSeeds.value();
        this.inChunkGeneration = settings.inChunkGeneration.value();
        settings.inChunkGeneration.informOnChange(this.inChunkGeneratorFollower);
        this.excludeDimensions = new Consumable<String>(settings.excludeDimensions.value());
        this.includeDimensions = new Consumable<String>(settings.includeDimensions.value());
        this.includeDimensionIDs = new DimensionSet.Include(this.includeDimensions.use());
        this.excludeDimensionIDs = new DimensionSet.Exclude(this.excludeDimensions.use());
        this.inChunkGenerationExclude = new Consumable<String>(settings.inChunkGenerationExclude.value());
        this.inChunkGenerationInclude = new Consumable<String>(settings.inChunkGenerationInclude.value());
        this.inChunkGenerationIncludeIDs = new DimensionSet.Include(this.inChunkGenerationInclude.use());
        this.inChunkGenerationExcludeIDs = new DimensionSet.Exclude(this.inChunkGenerationExclude.use());
        UBAPIHook.ubAPIHook.dimensionalStrataColumnProvider = new StrataColumnProvider();
        this.oreUBifier = oreUBifier;
    }

    public void onBiomeDecorate(DecorateBiomeEvent.Post event) {
        if (!UndergroundBiomes.instance().settings().ubActive.value().booleanValue()) {
            return;
        }
        int id = event.world.field_73011_w.field_76574_g;
        if (!this.includeDimensionIDs.isIncluded(id, this.excludeDimensionIDs)) {
            return;
        }
        WorldGenManager worldGen = this.worldGenManager(id);
        if (worldGen == null) {
            System.out.println("UndergroundBiomes warning: onBiomeDecorate before onWorldLoad! Ignoring.");
            return;
        }
        if (this.inChunkDimensionIDs.contains(id)) {
            worldGen.decorateIfNeeded(event);
        } else {
            worldGen.onBiomeDecorate(event);
        }
    }

    public void onBiomeDecorate(PopulateChunkEvent.Post event) {
        if (!UndergroundBiomes.instance().settings().ubActive.value().booleanValue()) {
            return;
        }
        int id = event.world.field_73011_w.field_76574_g;
        if (!this.includeDimensionIDs.isIncluded(id, this.excludeDimensionIDs)) {
            return;
        }
        WorldGenManager worldGen = this.worldGenManager(id);
        if (worldGen == null) {
            System.out.println("UndergroundBiomes warning: onBiomeDecorate before onWorldLoad! Ignoring.");
            return;
        }
        if (this.inChunkDimensionIDs.contains(id)) {
            worldGen.decorateIfNeeded(event);
        } else {
            worldGen.onBiomeDecorate(event);
        }
    }

    public void setupGenerators() {
        if (!UndergroundBiomes.instance().settings().ubActive.value().booleanValue()) {
            return;
        }
        try {
            MinecraftServer server = MinecraftServer.func_71276_C();
            if (server == null) {
                return;
            }
            if (this.serverAdjusted) {
                return;
            }
            WorldServer[] serverList = server.field_71305_c;
            for (int i = 0; i < serverList.length; ++i) {
                if (serverList[i] == null) continue;
                int id = serverList[i].field_73011_w.field_76574_g;
                WorldGenManager worldGen = this.worldGenManager(id);
                if (!this.includeDimensionIDs.isIncluded(id, this.excludeDimensionIDs)) continue;
                logger.info("UB dimension setup " + this.inChunkGeneration);
                if (!this.inChunkGeneration()) {
                    return;
                }
                if (!this.inChunkGenerationIncludeIDs.isIncluded(id, this.inChunkGenerationExcludeIDs)) continue;
                serverList[i].field_73011_w.field_76578_c.func_76938_b();
                worldGen.setChunkProvider((World)serverList[i]);
                worldGen.setServedChunkProvider(serverList[i]);
                this.inChunkDimensionIDs.add(id);
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        this.serverAdjusted = true;
    }

    public void unload() {
        this.inChunkDimensionIDs = new ArrayList<Integer>();
        this.serverAdjusted = false;
        this.worldGenManagers = new HashMap<Integer, WorldGenManager>();
        this.ubSetProviders = new ArrayList();
        this.cachedBiomeSet = null;
    }

    public boolean inChunkGenerationAllowed(int id) {
        if (!this.inChunkGeneration()) {
            return false;
        }
        if (!this.includeDimensionIDs.isIncluded(id, this.excludeDimensionIDs)) {
            return false;
        }
        if (!this.inChunkGenerationIncludeIDs.isIncluded(id, this.inChunkGenerationExcludeIDs)) {
            return false;
        }
        return true;
    }

    public boolean ubGenerationAllowed(int id) {
        if (!this.includeDimensionIDs.isIncluded(id, this.excludeDimensionIDs)) {
            return false;
        }
        return true;
    }

    @SubscribeEvent
    public void preBiomeDecorate(DecorateBiomeEvent.Pre event) {
        if (!UndergroundBiomes.instance().settings().ubActive.value().booleanValue()) {
            return;
        }
        int id = event.world.field_73011_w.field_76574_g;
        this.villageWorldGenManager = this.worldGenManager(id);
        if (UndergroundBiomes.instance().gotWorldSeed() && UndergroundBiomes.replaceCobblestone()) {
            this.villageWorldGenManager.preBiomeDecorate(event);
        }
    }

    @SubscribeEvent
    public void prePopulateChunk(PopulateChunkEvent.Pre event) {
        if (!UndergroundBiomes.instance().settings().ubActive.value().booleanValue()) {
            return;
        }
        int id = event.world.field_73011_w.field_76574_g;
        this.villageWorldGenManager = this.worldGenManager(id);
        if (UndergroundBiomes.instance().gotWorldSeed() && UndergroundBiomes.replaceCobblestone()) {
            this.villageWorldGenManager.prePopulateChunk(event);
        }
    }

    @SubscribeEvent
    public void onVillageSelectBlock(BiomeEvent.GetVillageBlockID e) {
        if (!UndergroundBiomes.instance().settings().ubActive.value().booleanValue()) {
            return;
        }
        if (this.villageWorldGenManager == null) {
            return;
        }
        if (UndergroundBiomes.instance().gotWorldSeed()) {
            this.villageWorldGenManager.onVillageSelectBlock(e);
        }
    }

    @SubscribeEvent
    public void onVillageSelectMeta(BiomeEvent.GetVillageBlockMeta e) {
        if (!UndergroundBiomes.instance().settings().ubActive.value().booleanValue()) {
            return;
        }
        if (this.villageWorldGenManager == null) {
            return;
        }
        if (UndergroundBiomes.instance().gotWorldSeed()) {
            this.villageWorldGenManager.onVillageSelectMeta(e);
        }
    }

    public void register(UndergroundBiomeSetProvider toRegister) {
        this.ubSetProviders.add(toRegister);
    }

    public void redoOres(int x, int z, World world) {
        if (!UndergroundBiomes.instance().settings().ubActive.value().booleanValue()) {
            return;
        }
        if (!UndergroundBiomes.instance().settings().ubOres.value().booleanValue()) {
            return;
        }
        WorldGenManager worldGenManager = this.worldGenManagers.get(world.field_73011_w.field_76574_g);
        if (worldGenManager != null) {
            if (this.ubGenerationAllowed(worldGenManager.dimension)) {
                worldGenManager.redoOres(x, z, world);
            }
        } else {
            UndergroundBiomes.logger.info("no manager for " + world.toString());
        }
    }

    private class StrataColumnProvider
    implements UBDimensionalStrataColumnProvider {
        private StrataColumnProvider() {
        }

        public UBStrataColumnProvider ubStrataColumnProvider(int dimension) {
            return new UBBlockProvider(DimensionManager.this.worldGenManager(dimension));
        }
    }

}

