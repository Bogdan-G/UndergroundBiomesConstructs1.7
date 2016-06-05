/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  highlands.biome.BiomeDecoratorHighlands
 *  highlands.biome.BiomeGenBadlands
 *  highlands.biome.BiomeGenCliffs
 *  highlands.biome.BiomeGenDesertMountains
 *  highlands.biome.BiomeGenRockMountains
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldProvider
 *  net.minecraft.world.biome.BiomeDecorator
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.chunk.Chunk
 *  net.minecraft.world.chunk.IChunkProvider
 *  net.minecraft.world.chunk.NibbleArray
 *  net.minecraft.world.chunk.storage.ExtendedBlockStorage
 */
package exterminatorJeff.undergroundBiomes.worldGen;

import Zeno410Utils.BlockLocation;
import Zeno410Utils.BlockLocationProbe;
import Zeno410Utils.BlockState;
import Zeno410Utils.Mutable;
import Zeno410Utils.Zeno410Logger;
import exterminatorJeff.undergroundBiomes.api.BiomeGenUndergroundBase;
import exterminatorJeff.undergroundBiomes.api.NamedBlock;
import exterminatorJeff.undergroundBiomes.api.NamedVanillaBlock;
import exterminatorJeff.undergroundBiomes.api.PerlinNoiseGenerator;
import exterminatorJeff.undergroundBiomes.api.UBStoneCodes;
import exterminatorJeff.undergroundBiomes.api.UndergroundBiomesSettings;
import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;
import exterminatorJeff.undergroundBiomes.common.WorldGenManager;
import exterminatorJeff.undergroundBiomes.common.block.BlockMetadataBase;
import exterminatorJeff.undergroundBiomes.intermod.BiomeGenUBBadlands;
import exterminatorJeff.undergroundBiomes.intermod.BiomeGenUBCliffs;
import exterminatorJeff.undergroundBiomes.intermod.BiomeGenUBDesertMountains;
import exterminatorJeff.undergroundBiomes.intermod.BiomeGenUBRockMountains;
import exterminatorJeff.undergroundBiomes.worldGen.BiomeDecoratorCorrector;
import exterminatorJeff.undergroundBiomes.worldGen.CorrectedBiomeDecorator;
import exterminatorJeff.undergroundBiomes.worldGen.CorrectedBiomeDecoratorHighlands;
import exterminatorJeff.undergroundBiomes.worldGen.CurrentWorldMemento;
import exterminatorJeff.undergroundBiomes.worldGen.OreUBifier;
import highlands.biome.BiomeDecoratorHighlands;
import highlands.biome.BiomeGenBadlands;
import highlands.biome.BiomeGenCliffs;
import highlands.biome.BiomeGenDesertMountains;
import highlands.biome.BiomeGenRockMountains;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.logging.Logger;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.chunk.NibbleArray;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;

public class BiomeUndergroundDecorator {
    private final WorldGenManager worldGen;
    private final OreUBifier oreUBifier;
    private final CurrentWorldMemento.Manager currentWorldManager = new CurrentWorldMemento.Manager();
    private final ArrayList<BiomeDecoratorCorrector> correctors = new ArrayList();
    public static Logger logger = new Zeno410Logger("BiomeUndergroundDecorator").logger();
    private final HashSet<BlockLocation> beingGenerated = new HashSet();
    private static HashSet<BlockLocation> needsRedo = new HashSet();
    private static BlockLocationProbe probe = new BlockLocationProbe(0, 100, 0);

    public static void noMoreRedos() {
        needsRedo.clear();
    }

    public static void needsRedo(int worldX, int worldZ, World world) {
        probe.setX(worldX >> 4);
        probe.setY(worldZ >> 4);
        probe.setZ(world.field_73011_w.field_76574_g);
        if (needsRedo.contains(probe)) {
            return;
        }
        needsRedo.add(probe.forStorage());
    }

    public static void redoFinished(int worldX, int worldZ, World world) {
        needsRedo.remove(new BlockLocation(worldX >> 4, worldZ >> 4, world.field_73011_w.field_76574_g));
    }

    public void doRedos(World redone) {
        ArrayList<BlockLocation> willRedo = new ArrayList<BlockLocation>();
        for (BlockLocation location22 : needsRedo) {
            if (location22.z() != redone.field_73011_w.field_76574_g) continue;
            willRedo.add(location22);
        }
        for (BlockLocation location22 : willRedo) {
            this.replaceChunkOres(location22.x() << 4, location22.y() << 4, redone);
        }
        for (BlockLocation location22 : willRedo) {
            BiomeUndergroundDecorator.redoFinished(location22.x(), location22.x(), redone);
        }
    }

    public BiomeUndergroundDecorator(WorldGenManager worldGen, OreUBifier oreUBifier) {
        this.worldGen = worldGen;
        this.oreUBifier = oreUBifier;
        this.arrangeHighlandsCompatibility();
        this.setupCorrectors();
        this.correctBiomeDecorators();
    }

    public void decorate(World par1World, Random par2Random, int x, int z) {
        this.replaceBlocksForUndergroundBiome(x, z, par1World);
    }

    public void replaceBlocksForUndergroundBiome(int par_x, int par_z, World currentWorld) {
        if (!this.worldGen.ubOn()) {
            return;
        }
        this.eraseBogusCurrentWorlds();
        BlockLocation chunkLocation = new BlockLocation(par_x, par_z, currentWorld.field_73011_w.field_76574_g);
        if (this.beingGenerated.contains(chunkLocation)) {
            return;
        }
        if (this.beingGenerated.size() == 0) {
            // empty if block
        }
        this.beingGenerated.add(chunkLocation);
        int generationHeight = UndergroundBiomes.generateHeight();
        BiomeGenUndergroundBase[] undergroundBiomesForGeneration = new BiomeGenUndergroundBase[256];
        undergroundBiomesForGeneration = this.worldGen.loadUndergroundBlockGeneratorData(undergroundBiomesForGeneration, par_x, par_z, 16, 16);
        for (int x = par_x; x < par_x + 16; ++x) {
            for (int z = par_z; z < par_z + 16; ++z) {
                BiomeGenUndergroundBase currentBiome = undergroundBiomesForGeneration[x - par_x + (z - par_z) * 16];
                int variation = (int)(currentBiome.strataNoise.noise((double)x / 55.533, (double)z / 55.533, 3, 1.0, 0.5) * 10.0 - 5.0);
                UBStoneCodes defaultColumnStone = currentBiome.fillerBlockCodes;
                Chunk chunk = currentWorld.func_72938_d(x, z);
                ExtendedBlockStorage[] ebsArray = chunk.func_76587_i();
                ExtendedBlockStorage ebs = null;
                for (int y = 1; y < generationHeight; ++y) {
                    Block currentBlock;
                    ebs = ebsArray[y >> 4];
                    if (ebs == null || !NamedVanillaBlock.stone.matches(currentBlock = ebs.func_150819_a(x & 15, y & 15, z & 15))) continue;
                    UBStoneCodes strata = currentBiome.getStrataBlockAtLayer(y + variation);
                    ebs.func_150818_a(x & 15, y & 15, z & 15, strata.block);
                    ebs.func_76654_b(x & 15, y & 15, z & 15, strata.metadata);
                    chunk.field_76643_l = true;
                }
            }
        }
        this.beingGenerated.remove(chunkLocation);
    }

    public void replaceOresForUndergroundBiome(int par_x, int par_z, World currentWorld) {
        if (!this.worldGen.ubOn()) {
            return;
        }
        if (!this.oreUBifier.replacementActive()) {
            return;
        }
        BlockLocation chunkLocation = new BlockLocation(par_x, par_z, currentWorld.field_73011_w.field_76574_g);
        if (this.beingGenerated.contains(chunkLocation)) {
            return;
        }
        this.beingGenerated.add(chunkLocation);
        int generationHeight = UndergroundBiomes.generateHeight();
        BiomeGenUndergroundBase[] undergroundBiomesForGeneration = new BiomeGenUndergroundBase[256];
        undergroundBiomesForGeneration = this.worldGen.loadUndergroundBlockGeneratorData(undergroundBiomesForGeneration, par_x, par_z, 16, 16);
        for (int x = par_x; x < par_x + 16; ++x) {
            for (int z = par_z; z < par_z + 16; ++z) {
                BiomeGenUndergroundBase currentBiome = undergroundBiomesForGeneration[x - par_x + (z - par_z) * 16];
                int variation = (int)(currentBiome.strataNoise.noise((double)x / 55.533, (double)z / 55.533, 3, 1.0, 0.5) * 10.0 - 5.0);
                UBStoneCodes defaultColumnStone = currentBiome.fillerBlockCodes;
                for (int y = 1; y < generationHeight; ++y) {
                    int metadata;
                    Block currentBlock = currentWorld.func_147439_a(x, y, z);
                    if (x != 963 || z == 963) {
                        // empty if block
                    }
                    if (Block.func_149680_a((Block)Blocks.field_150350_a, (Block)currentBlock) || Block.func_149680_a((Block)Blocks.field_150355_j, (Block)currentBlock) || Block.func_149680_a((Block)Blocks.field_150348_b, (Block)currentBlock) || Block.func_149680_a((Block)UndergroundBiomes.igneousStone, (Block)currentBlock) || Block.func_149680_a((Block)UndergroundBiomes.metamorphicStone, (Block)currentBlock) || !this.oreUBifier.replaces(currentBlock, metadata = currentWorld.func_72805_g(x, y, z))) continue;
                    UBStoneCodes baseStrata = currentBiome.getStrataBlockAtLayer(y + variation);
                    BlockState replacement = this.oreUBifier.replacement(currentBlock, metadata, baseStrata, defaultColumnStone);
                    currentWorld.func_147465_d(x, y, z, replacement.block, replacement.metadata, 2);
                }
                if (x != 963 || z != 963) continue;
                BlockMetadataBase.test963 = true;
            }
        }
        this.beingGenerated.remove(chunkLocation);
        BiomeUndergroundDecorator.redoFinished(par_x, par_z, currentWorld);
    }

    public void replaceChunkOres(int par_x, int par_z, World currentWorld) {
        if (!this.worldGen.ubOn()) {
            return;
        }
        if (!this.oreUBifier.replacementActive()) {
            return;
        }
        BlockLocation chunkLocation = new BlockLocation(par_x, par_z, currentWorld.field_73011_w.field_76574_g);
        if (this.beingGenerated.contains(chunkLocation) && UndergroundBiomes.crashOnProblems()) {
            throw new RuntimeException();
        }
        CurrentWorldMemento memento = this.currentWorldManager.memento();
        this.beingGenerated.add(chunkLocation);
        int generationHeight = UndergroundBiomes.generateHeight();
        BiomeGenUndergroundBase[] undergroundBiomesForGeneration = new BiomeGenUndergroundBase[256];
        undergroundBiomesForGeneration = this.worldGen.loadUndergroundBlockGeneratorData(undergroundBiomesForGeneration, par_x, par_z, 16, 16);
        Chunk chunk = currentWorld.func_72938_d(par_x, par_z);
        ExtendedBlockStorage[] storage = chunk.func_76587_i();
        for (int chunkx = 0; chunkx < 16; ++chunkx) {
            for (int chunkz = 0; chunkz < 16; ++chunkz) {
                BiomeGenUndergroundBase currentBiome = undergroundBiomesForGeneration[chunkx + chunkz * 16];
                int x = par_x + chunkx;
                int z = par_z + chunkz;
                int variation = (int)(currentBiome.strataNoise.noise((double)x / 55.533, (double)z / 55.533, 3, 1.0, 0.5) * 10.0 - 5.0);
                UBStoneCodes defaultColumnStone = currentBiome.fillerBlockCodes;
                for (int y = 1; y < generationHeight; ++y) {
                    OreUBifier.BlockReplacer replacer;
                    BlockState replacement;
                    UBStoneCodes baseStrata;
                    int metadata;
                    OreUBifier.BlockStateReplacer blockStateReplacer;
                    ExtendedBlockStorage extendedblockstorage = storage[y >> 4];
                    if (extendedblockstorage == null) continue;
                    int inLevelY = y & 15;
                    int blockID = extendedblockstorage.func_76658_g()[inLevelY << 8 | chunkz << 4 | chunkx] & 255;
                    if (extendedblockstorage.func_76660_i() != null) {
                        blockID |= extendedblockstorage.func_76660_i().func_76582_a(chunkx, inLevelY, chunkz) << 8;
                    }
                    if ((replacer = this.oreUBifier.blockReplacer(blockID)) == null || (blockStateReplacer = replacer.replacer(metadata = extendedblockstorage.func_76665_b(chunkx, inLevelY, chunkz))) == null || (replacement = blockStateReplacer.replacement(baseStrata = currentBiome.getStrataBlockAtLayer(y + variation), defaultColumnStone)) == null) continue;
                    extendedblockstorage.func_150818_a(chunkx, inLevelY, chunkz, replacement.block);
                    extendedblockstorage.func_76654_b(chunkx, inLevelY, chunkz, replacement.metadata);
                }
            }
        }
        this.beingGenerated.remove(chunkLocation);
        memento.restore();
        currentWorld.func_72863_F().func_73156_b();
        BiomeUndergroundDecorator.redoFinished(par_x, par_z, currentWorld);
    }

    public void replaceChunkOres(IChunkProvider provider, int par_x, int par_z) {
        if (!this.worldGen.ubOn()) {
            return;
        }
        if (!this.oreUBifier.replacementActive()) {
            return;
        }
        CurrentWorldMemento memento = this.currentWorldManager.memento();
        int generationHeight = UndergroundBiomes.generateHeight();
        BiomeGenUndergroundBase[] undergroundBiomesForGeneration = new BiomeGenUndergroundBase[256];
        undergroundBiomesForGeneration = this.worldGen.loadUndergroundBlockGeneratorData(undergroundBiomesForGeneration, par_x, par_z, 16, 16);
        Chunk chunk = provider.func_73154_d(par_x, par_z);
        ExtendedBlockStorage[] storage = chunk.func_76587_i();
        for (int chunkx = 0; chunkx < 16; ++chunkx) {
            for (int chunkz = 0; chunkz < 16; ++chunkz) {
                BiomeGenUndergroundBase currentBiome = undergroundBiomesForGeneration[chunkx + chunkz * 16];
                int x = par_x + chunkx;
                int z = par_z + chunkz;
                int variation = (int)(currentBiome.strataNoise.noise((double)x / 55.533, (double)z / 55.533, 3, 1.0, 0.5) * 10.0 - 5.0);
                UBStoneCodes defaultColumnStone = currentBiome.fillerBlockCodes;
                for (int y = 1; y < generationHeight; ++y) {
                    int metadata;
                    OreUBifier.BlockReplacer replacer;
                    BlockState replacement;
                    OreUBifier.BlockStateReplacer blockStateReplacer;
                    UBStoneCodes baseStrata;
                    ExtendedBlockStorage extendedblockstorage = storage[y >> 4];
                    if (extendedblockstorage == null) continue;
                    int inLevelY = y & 15;
                    int blockID = extendedblockstorage.func_76658_g()[inLevelY << 8 | chunkz << 4 | chunkx] & 255;
                    if (extendedblockstorage.func_76660_i() != null) {
                        blockID |= extendedblockstorage.func_76660_i().func_76582_a(chunkx, inLevelY, chunkz) << 8;
                    }
                    if ((replacer = this.oreUBifier.blockReplacer(blockID)) == null || (blockStateReplacer = replacer.replacer(metadata = extendedblockstorage.func_76665_b(chunkx, inLevelY, chunkz))) == null || (replacement = blockStateReplacer.replacement(baseStrata = currentBiome.getStrataBlockAtLayer(y + variation), defaultColumnStone)) == null) continue;
                    extendedblockstorage.func_150818_a(chunkx, inLevelY, chunkz, replacement.block);
                    extendedblockstorage.func_76654_b(chunkx, inLevelY, chunkz, replacement.metadata);
                }
            }
        }
        memento.restore();
    }

    public void replaceChunkBlocks(Chunk chunk, int par_x, int par_z, int dimension) {
        if (!this.worldGen.ubOn()) {
            return;
        }
        this.worldGen.setGenerated(par_x, par_z);
        BlockLocation chunkLocation = new BlockLocation(par_x *= 16, par_z *= 16, dimension);
        if (this.beingGenerated.contains(chunkLocation)) {
            return;
        }
        this.beingGenerated.add(chunkLocation);
        int generationHeight = UndergroundBiomes.generateHeight();
        BiomeGenUndergroundBase[] undergroundBiomesForGeneration = new BiomeGenUndergroundBase[256];
        undergroundBiomesForGeneration = this.worldGen.loadUndergroundBlockGeneratorData(undergroundBiomesForGeneration, par_x, par_z, 16, 16);
        for (int inChunkX = 0; inChunkX < 16; ++inChunkX) {
            for (int inChunkZ = 0; inChunkZ < 16; ++inChunkZ) {
                BiomeGenUndergroundBase currentBiome = undergroundBiomesForGeneration[inChunkX + inChunkZ * 16];
                int variation = (int)(currentBiome.strataNoise.noise((double)(inChunkX + par_x) / 55.533, (double)(inChunkZ + par_z) / 55.533, 3, 1.0, 0.5) * 10.0 - 5.0);
                for (int y = 1; y < generationHeight; ++y) {
                    TileEntity entity;
                    Block currentBlock = chunk.func_150810_a(inChunkX, y, inChunkZ);
                    if (!NamedVanillaBlock.stone.matches(currentBlock) || (entity = chunk.getTileEntityUnsafe(inChunkX, y, inChunkZ)) != null) continue;
                    UBStoneCodes strata = currentBiome.getStrataBlockAtLayer(y + variation);
                    chunk.func_150807_a(inChunkX, y, inChunkZ, strata.block, strata.metadata);
                }
            }
        }
        this.beingGenerated.remove(chunkLocation);
    }

    public UBStoneCodes fillerBlock(int par_x, int par_z) {
        BiomeGenUndergroundBase[] undergroundBiomesForGeneration = new BiomeGenUndergroundBase[256];
        undergroundBiomesForGeneration = this.worldGen.loadUndergroundBlockGeneratorData(undergroundBiomesForGeneration, par_x, par_z, 16, 16);
        BiomeGenUndergroundBase currentBiome = undergroundBiomesForGeneration[136];
        return currentBiome.fillerBlockCodes;
    }

    public void eraseBogusCurrentWorlds() {
        if (!UndergroundBiomes.instance().settings().clearVarsForRecursiveGeneration.value().booleanValue()) {
            return;
        }
        try {
            for (BiomeGenBase biomeGenBase : BiomeGenBase.func_150565_n()) {
                biomeGenBase.field_76760_I.field_76815_a = null;
            }
        }
        catch (NullPointerException e) {
            // empty catch block
        }
    }

    private void correctBiomeDecorators() {
        if (!UndergroundBiomes.instance().settings().imposeUBStone.value().booleanValue()) {
            return;
        }
        BiomeGenBase[] biomes = BiomeGenBase.func_150565_n();
        block0 : for (int i = 0; i < biomes.length; ++i) {
            BiomeGenBase biome = biomes[i];
            if (biome == null) continue;
            logger.info(biome.field_76791_y + " " + biome.toString());
            BiomeDecorator currentDecorator = biome.field_76760_I;
            for (BiomeDecoratorCorrector corrector : this.correctors) {
                BiomeDecorator newDecorator = corrector.corrected(biome, currentDecorator);
                if (newDecorator == currentDecorator) continue;
                logger.info("changing");
                biome.field_76760_I = newDecorator;
                continue block0;
            }
        }
    }

    private void setupCorrectors() {
        this.correctors.add(new VanillaDecoratorCorrector());
        try {
            this.correctors.add(new HighlandsDecoratorCorrector());
        }
        catch (NoClassDefFoundError e) {
            // empty catch block
        }
        try {
            this.correctors.add(new BoPDecoratorCorrector());
        }
        catch (NoClassDefFoundError e) {
            // empty catch block
        }
    }

    private void arrangeHighlandsCompatibility() {
        try {
            BiomeGenBase[] biomes = BiomeGenBase.func_150565_n();
            for (int i = 0; i < biomes.length; ++i) {
                BiomeGenBase biome = biomes[i];
                if (biome == null) continue;
                if (biome instanceof BiomeGenCliffs) {
                    biomes[i] = new BiomeGenUBCliffs((BiomeGenCliffs)biome, i);
                }
                if (biome instanceof BiomeGenDesertMountains) {
                    biomes[i] = new BiomeGenUBDesertMountains(i);
                }
                if (biome instanceof BiomeGenRockMountains) {
                    biomes[i] = new BiomeGenUBRockMountains(i);
                }
                if (!(biome instanceof BiomeGenBadlands)) continue;
                biomes[i] = new BiomeGenUBBadlands(i);
            }
        }
        catch (NoClassDefFoundError e) {
            // empty catch block
        }
    }

    private class HighlandsDecoratorCorrector
    implements BiomeDecoratorCorrector {
        Class standardDecoratorClass;

        private HighlandsDecoratorCorrector() {
            this.standardDecoratorClass = BiomeDecoratorHighlands.class;
        }

        public BiomeDecorator corrected(BiomeGenBase biome, BiomeDecorator currentDecorator) {
            if (currentDecorator instanceof BiomeDecoratorHighlands && !(currentDecorator instanceof CorrectedBiomeDecorator)) {
                BiomeUndergroundDecorator.logger.info("corrected decorator for " + biome.field_76791_y);
                return new CorrectedBiomeDecoratorHighlands(biome, (BiomeDecoratorHighlands)currentDecorator);
            }
            return currentDecorator;
        }
    }

    private class BoPDecoratorCorrector
    implements BiomeDecoratorCorrector {
        Class standardDecoratorClass;

        private BoPDecoratorCorrector() {
            this.standardDecoratorClass = BiomeDecorator.class;
        }

        public BiomeDecorator corrected(BiomeGenBase biome, BiomeDecorator currentDecorator) {
            if (currentDecorator.getClass().getName().contains("BoP") && !(currentDecorator instanceof CorrectedBiomeDecorator)) {
                return new CorrectedBiomeDecorator(currentDecorator);
            }
            return currentDecorator;
        }
    }

    private class VanillaDecoratorCorrector
    implements BiomeDecoratorCorrector {
        Class standardDecoratorClass;

        private VanillaDecoratorCorrector() {
            this.standardDecoratorClass = BiomeDecorator.class;
        }

        public BiomeDecorator corrected(BiomeGenBase biome, BiomeDecorator currentDecorator) {
            if (currentDecorator.getClass().equals(this.standardDecoratorClass)) {
                return new CorrectedBiomeDecorator(currentDecorator);
            }
            return currentDecorator;
        }
    }

    public static class Request {
        public final int x;
        public final int z;
        public final World world;

        public Request(int x, int z, World world) {
            this.x = x;
            this.z = z;
            this.world = world;
        }
    }

}

