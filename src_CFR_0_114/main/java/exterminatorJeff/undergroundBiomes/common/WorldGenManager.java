/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.ChunkPosition
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 *  net.minecraft.world.chunk.IChunkProvider
 *  net.minecraft.world.gen.ChunkProviderGenerate
 *  net.minecraft.world.gen.ChunkProviderServer
 *  net.minecraft.world.gen.layer.GenLayer
 *  net.minecraft.world.gen.layer.IntCache
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

import Zeno410Utils.Accessor;
import Zeno410Utils.PlaneLocation;
import Zeno410Utils.Zeno410Logger;
import exterminatorJeff.undergroundBiomes.api.BiomeGenUndergroundBase;
import exterminatorJeff.undergroundBiomes.api.UBStoneCodes;
import exterminatorJeff.undergroundBiomes.api.UndergroundBiomeSet;
import exterminatorJeff.undergroundBiomes.common.UBiomeCache;
import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;
import exterminatorJeff.undergroundBiomes.worldGen.BiomeUndergroundCacheBlock;
import exterminatorJeff.undergroundBiomes.worldGen.BiomeUndergroundDecorator;
import exterminatorJeff.undergroundBiomes.worldGen.GenLayerUnderground;
import exterminatorJeff.undergroundBiomes.worldGen.OreUBifier;
import exterminatorJeff.undergroundBiomes.worldGen.UBChunkProvider;
import exterminatorJeff.undergroundBiomes.worldGen.VillageStoneChanger;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderGenerate;
import net.minecraft.world.gen.ChunkProviderServer;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import net.minecraftforge.event.terraingen.BiomeEvent;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;

public class WorldGenManager {
    public static Logger logger = new Zeno410Logger("WorldGenManager").logger();
    public final int dimension;
    private GenLayer genUndergroundBiomes;
    private GenLayer undergroundBiomeIndexLayer;
    private UBiomeCache biomeCache;
    private final VillageStoneChanger villageStoneChanger;
    private final BiomeUndergroundDecorator villageStoneSource;
    private AccessChunkProvider accessChunkProvider = new AccessChunkProvider();
    private AccessChunkProviderServer accessChunkProviderServer = new AccessChunkProviderServer();
    private Accessor<ChunkProviderServer, IChunkProvider> providerFromChunkServer = new Accessor(IChunkProvider.class);
    private long seed;
    private HashSet<PlaneLocation> alreadyGenerated = new HashSet();
    private IChunkProvider chunkProvider;
    private UndergroundBiomeSet biomeSet;
    private final boolean ubOn;

    public boolean ubOn() {
        return this.ubOn;
    }

    public WorldGenManager(long par1, int _dimension, OreUBifier oreUBifier, UndergroundBiomeSet biomeSet, boolean ubOn) {
        this.dimension = _dimension;
        this.seed = par1;
        this.biomeCache = new UBiomeCache(this);
        this.biomeSet = biomeSet;
        GenLayer[] gen = GenLayerUnderground.initializeAllBiomeGenerators(par1, UndergroundBiomes.biomeSize(), biomeSet);
        this.genUndergroundBiomes = gen[0];
        this.undergroundBiomeIndexLayer = gen[1];
        while (!this.arrayHasValues(this.undergroundBiomeIndexLayer.func_75904_a(0, 0, 16, 16))) {
            gen = GenLayerUnderground.initializeAllBiomeGenerators(this.seed++, UndergroundBiomes.biomeSize(), biomeSet);
            this.genUndergroundBiomes = gen[0];
            this.undergroundBiomeIndexLayer = gen[1];
        }
        this.villageStoneSource = new BiomeUndergroundDecorator(this, oreUBifier);
        this.villageStoneChanger = new VillageStoneChanger();
        this.ubOn = ubOn;
    }

    public boolean inChunkGenerationAllowed() {
        return UndergroundBiomes.instance().inChunkGenerationAllowed(this.dimension);
    }

    public void preBiomeDecorate(DecorateBiomeEvent.Pre event) {
        if (!this.ubOn()) {
            return;
        }
        BiomeGenUndergroundBase[] undergroundBiomesForGeneration = null;
        undergroundBiomesForGeneration = this.loadUndergroundBlockGeneratorData(undergroundBiomesForGeneration, event.chunkX, event.chunkZ, 16, 16);
        this.villageStoneChanger.setStoneCode(this.getUndergroundBiomeGenAt((int)event.chunkX, (int)event.chunkZ).fillerBlockCodes);
    }

    public void prePopulateChunk(PopulateChunkEvent.Pre event) {
        if (!this.ubOn()) {
            return;
        }
        BiomeGenUndergroundBase[] undergroundBiomesForGeneration = null;
        undergroundBiomesForGeneration = this.loadUndergroundBlockGeneratorData(undergroundBiomesForGeneration, event.chunkX, event.chunkZ, 16, 16);
        this.villageStoneChanger.setStoneCode(this.getUndergroundBiomeGenAt((int)event.chunkX, (int)event.chunkZ).fillerBlockCodes);
    }

    public void onVillageSelectBlock(BiomeEvent.GetVillageBlockID e) {
        this.villageStoneChanger.onVillageSelectBlock(e);
    }

    public void onVillageSelectMeta(BiomeEvent.GetVillageBlockMeta e) {
        this.villageStoneChanger.onVillageSelectMeta(e);
    }

    public void onBiomeDecorate(DecorateBiomeEvent.Post event) {
        this.villageStoneSource.decorate(event.world, event.rand, event.chunkX, event.chunkZ);
    }

    public void onBiomeDecorate(PopulateChunkEvent.Post event) {
        this.villageStoneSource.decorate(event.world, event.rand, event.chunkX * 16, event.chunkZ * 16);
    }

    public void onBiomeReplaceOres(DecorateBiomeEvent.Post event) {
        this.villageStoneSource.replaceChunkOres(event.chunkX, event.chunkZ, event.world);
        this.villageStoneSource.doRedos(event.world);
    }

    public void redoOres(int x, int z, World world) {
        this.villageStoneSource.replaceChunkOres(x, z, world);
        this.villageStoneSource.doRedos(world);
    }

    public BiomeGenUndergroundBase getUndergroundBiomeGenAt(int par1, int par2) {
        return this.biomeCache.getUndergroundBiomeGetAt(par1, par2);
    }

    public BiomeUndergroundCacheBlock chunkBiomeCache(int xPosition, int zPosition) {
        return this.biomeCache.getUndergroundBiomeCacheBlock(xPosition, zPosition);
    }

    public BiomeGenUndergroundBase[] loadUndergroundBlockGeneratorData(BiomeGenUndergroundBase[] par1ArrayOfBiomeGenBase, int par2, int par3, int par4, int par5) {
        return this.getUndergroundBiomeGenAt(par1ArrayOfBiomeGenBase, par2, par3, par4, par5);
    }

    public BiomeGenUndergroundBase[] getUndergroundBiomeGenAt(BiomeGenUndergroundBase[] biomesArrayPar, int par2, int par3, int par4, int par5) {
        IntCache.func_76446_a();
        BiomeGenUndergroundBase[] biomesArray = biomesArrayPar;
        if (biomesArray == null || biomesArray.length < par4 * par5) {
            biomesArray = new BiomeGenUndergroundBase[par4 * par5];
        }
        if (par4 == 16 && par5 == 16) {
            BiomeGenUndergroundBase[] var9 = this.biomeCache.getCachedUndergroundBiomes(par2, par3);
            System.arraycopy(var9, 0, biomesArray, 0, par4 * par5);
            return biomesArray;
        }
        throw new RuntimeException();
    }

    public BiomeGenUndergroundBase[] cacheUndergroundBiomeGenAt(BiomeGenUndergroundBase[] biomesArrayPar, int par2, int par3, int par4, int par5) {
        BiomeGenUndergroundBase[] biomesArray = biomesArrayPar;
        if (biomesArray == null || biomesArray.length < par4 * par5) {
            IntCache.func_76446_a();
            biomesArray = new BiomeGenUndergroundBase[par4 * par5];
        }
        int[] var7 = this.undergroundBiomeIndexLayer.func_75904_a(par2, par3, par4, par5);
        for (int var8 = 0; var8 < par4 * par5; ++var8) {
            biomesArray[var8] = this.biomeSet.biomeList[var7[var8]];
        }
        return biomesArray;
    }

    public void setGenerated(int x, int z) {
        this.alreadyGenerated.add(new PlaneLocation(x, z));
    }

    public void decorateIfNeeded(DecorateBiomeEvent.Post event) {
        PlaneLocation target = new PlaneLocation(event.chunkX / 16, event.chunkZ / 16);
        if (this.alreadyGenerated.contains(target)) {
            this.alreadyGenerated.remove(target);
        } else {
            this.onBiomeDecorate(event);
        }
    }

    public void decorateIfNeeded(PopulateChunkEvent.Post event) {
        PlaneLocation target = new PlaneLocation(event.chunkX, event.chunkZ);
        if (this.alreadyGenerated.contains(target)) {
            this.alreadyGenerated.remove(target);
        } else {
            this.onBiomeDecorate(event);
        }
    }

    private boolean arrayHasValues(int[] ints) {
        for (int i : ints) {
            if (i == 0) continue;
            return true;
        }
        return false;
    }

    public ChunkPosition findUndergroundBiomePosition(int par1, int par2, int par3, List par4List, Random par5Random) {
        IntCache.func_76446_a();
        int var6 = par1 - par3 >> 2;
        int var7 = par2 - par3 >> 2;
        int var8 = par1 + par3 >> 2;
        int var9 = par2 + par3 >> 2;
        int var10 = var8 - var6 + 1;
        int var11 = var9 - var7 + 1;
        int[] var12 = this.genUndergroundBiomes.func_75904_a(var6, var7, var10, var11);
        ChunkPosition var13 = null;
        int var14 = 0;
        for (int var15 = 0; var15 < var12.length; ++var15) {
            int var16 = var6 + var15 % var10 << 2;
            int var17 = var7 + var15 / var10 << 2;
            BiomeGenUndergroundBase var18 = this.biomeSet.biomeList[var12[var15]];
            if (!par4List.contains(var18) || var13 != null && par5Random.nextInt(var14 + 1) != 0) continue;
            var13 = new ChunkPosition(var16, 0, var17);
            ++var14;
        }
        return var13;
    }

    public IChunkProvider UBChunkProvider(IChunkProvider currentProvider) {
        return new UBChunkProvider(currentProvider, this.villageStoneSource, this.dimension);
    }

    public boolean chunkExists(int x, int z) {
        return this.chunkProvider.func_73149_a(x, z);
    }

    public IChunkProvider servedChunkProvider(WorldServer world) {
        return this.providerFromChunkServer.get(this.accessChunkProviderServer.chunkProviderServer(world));
    }

    public void setChunkProvider(World world) {
        if (!this.ubOn()) {
            return;
        }
        IChunkProvider currentProvider = this.accessChunkProvider.iChunkProvider(world);
        if (!(currentProvider instanceof UBChunkProvider) && currentProvider instanceof ChunkProviderGenerate) {
            this.accessChunkProvider.setIChunkProvider(world, new UBChunkProvider(currentProvider, this.villageStoneSource, this.dimension));
        }
    }

    public void setServedChunkProvider(WorldServer world) {
        if (!this.ubOn()) {
            return;
        }
        ChunkProviderServer currentServer = this.accessChunkProviderServer.chunkProviderServer(world);
        IChunkProvider currentProvider = this.providerFromChunkServer.get(currentServer);
        if (!(currentProvider instanceof UBChunkProvider) && (currentProvider instanceof ChunkProviderGenerate || currentProvider.getClass().getName().contains("ChunkProviderBOP") || currentProvider.getClass().getName().contains("ChunkProviderTwilightForest") || currentProvider.getClass().getName().contains("BWG4ChunkProvider") || currentProvider.getClass().getName().contains("ChunkProviderRTG"))) {
            this.providerFromChunkServer.setField(currentServer, new UBChunkProvider(currentProvider, this.villageStoneSource, this.dimension));
        }
    }

    public IChunkProvider chunkProvider(World world) {
        IChunkProvider currentProvider = this.accessChunkProvider.iChunkProvider(world);
        return currentProvider;
    }

    static class AccessGenLayer
    extends GenLayer {
        AccessGenLayer() {
            super(0);
        }

        public int[] func_75904_a(int i, int j, int k, int l) {
            return null;
        }

        public void report(GenLayer examinedLayer) throws IllegalAccessException {
            Field[] fields = this.getClass().getSuperclass().getDeclaredFields();
            Field parentField = null;
            for (int i = 0; i < fields.length; ++i) {
                if (!GenLayer.class.isAssignableFrom(fields[i].getClass())) continue;
                parentField.setAccessible(true);
            }
            int level = 0;
            while (examinedLayer != null) {
                examinedLayer = (GenLayer)parentField.get((Object)examinedLayer);
                if (++level <= 100) continue;
                break;
            }
        }
    }

    static class AccessChunkProviderServer {
        Field iChunkProviderServerField;

        AccessChunkProviderServer() {
            try {
                this.setIChunkProviderField();
            }
            catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        private void setIChunkProviderField() throws IllegalAccessException {
            Field[] fields = WorldServer.class.getDeclaredFields();
            for (int i = 0; i < fields.length; ++i) {
                if (fields[i].getType() != ChunkProviderServer.class) continue;
                this.iChunkProviderServerField = fields[i];
                this.iChunkProviderServerField.setAccessible(true);
            }
        }

        public ChunkProviderServer chunkProviderServer(WorldServer world) {
            try {
                return (ChunkProviderServer)this.iChunkProviderServerField.get((Object)world);
            }
            catch (IllegalArgumentException ex) {
                throw new RuntimeException(ex);
            }
            catch (IllegalAccessException ex) {
                throw new RuntimeException(ex);
            }
        }

        public void setIChunkProvider(WorldServer world, ChunkProviderServer chunkProvider) {
            try {
                this.iChunkProviderServerField.set((Object)world, (Object)chunkProvider);
            }
            catch (IllegalArgumentException ex) {
                throw new RuntimeException(ex);
            }
            catch (IllegalAccessException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    static class AccessChunkProvider {
        Field iChunkProviderField;

        AccessChunkProvider() {
            try {
                this.setIChunkProviderField();
            }
            catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        private void setIChunkProviderField() throws IllegalAccessException {
            Field[] fields = World.class.getDeclaredFields();
            for (int i = 0; i < fields.length; ++i) {
                if (!IChunkProvider.class.isAssignableFrom(fields[i].getType())) continue;
                this.iChunkProviderField = fields[i];
                this.iChunkProviderField.setAccessible(true);
                return;
            }
            throw new RuntimeException();
        }

        public IChunkProvider iChunkProvider(World world) {
            try {
                Object result = this.iChunkProviderField.get((Object)world);
                if (result == null) {
                    // empty if block
                }
                return (IChunkProvider)result;
            }
            catch (IllegalArgumentException ex) {
                throw new RuntimeException(ex);
            }
            catch (IllegalAccessException ex) {
                throw new RuntimeException(ex);
            }
        }

        public void setIChunkProvider(World world, IChunkProvider chunkProvider) {
            try {
                this.iChunkProviderField.set((Object)world, (Object)chunkProvider);
            }
            catch (IllegalArgumentException ex) {
                throw new RuntimeException(ex);
            }
            catch (IllegalAccessException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

}

