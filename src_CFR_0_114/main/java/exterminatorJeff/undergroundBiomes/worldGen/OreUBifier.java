/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.event.FMLPreInitializationEvent
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.oredict.OreDictionary
 */
package exterminatorJeff.undergroundBiomes.worldGen;

import Zeno410Utils.Acceptor;
import Zeno410Utils.BlockState;
import Zeno410Utils.ConcreteMutable;
import Zeno410Utils.Function;
import Zeno410Utils.KeyedRegistry;
import Zeno410Utils.MinecraftName;
import Zeno410Utils.Mutable;
import Zeno410Utils.Zeno410Logger;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import exterminatorJeff.undergroundBiomes.api.NamedBlock;
import exterminatorJeff.undergroundBiomes.api.UBStoneCodes;
import exterminatorJeff.undergroundBiomes.client.RenderUBOre;
import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;
import exterminatorJeff.undergroundBiomes.common.block.BlockMetadataBase;
import exterminatorJeff.undergroundBiomes.common.block.BlockOverlay;
import exterminatorJeff.undergroundBiomes.common.block.BlockUBHidden;
import exterminatorJeff.undergroundBiomes.common.block.BlockUBMetadataOre;
import exterminatorJeff.undergroundBiomes.common.block.BlockUBOre;
import exterminatorJeff.undergroundBiomes.common.block.BlockUBReplaceable;
import exterminatorJeff.undergroundBiomes.common.item.ItemUBHiddenBlock;
import exterminatorJeff.undergroundBiomes.common.item.ItemUBOreBlock;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public final class OreUBifier {
    public static Logger logger = new Zeno410Logger("OreUBifier").logger();
    private boolean replacementActive;
    private UBVersionsDictionary blockReplacer;
    private ReplacedOres replacedOres;
    private HashMap<Block, ItemStack> oreFor;
    private HashMap<Block, Block> overlayFor;
    private HashMap<Block, Block> stoneFor;
    private HashSet<Class> replacedBlockClasses;
    private BlockReplacer[] blockReplacers;
    private ConcreteMutable<Integer> renderID;
    private Acceptor<Boolean> updateReplacement;

    public OreUBifier(Mutable<Boolean> replacementFlag) {
        this.blockReplacer = new UBVersionsDictionary();
        this.replacedOres = new ReplacedOres();
        this.oreFor = new HashMap();
        this.overlayFor = new HashMap();
        this.stoneFor = new HashMap();
        this.replacedBlockClasses = new HashSet();
        this.renderID = new ConcreteMutable();
        this.updateReplacement = new Acceptor<Boolean>(){

            @Override
            public void accept(Boolean accepted) {
                OreUBifier.this.replacementActive = accepted;
            }
        };
        this.replacementActive = replacementFlag.value();
        replacementFlag.informOnChange(this.updateReplacement);
    }

    public void setupUBHidden(Block oreBlock, FMLPreInitializationEvent event) {
        assert (event != null);
        this.registerHiddenBlock(oreBlock, UndergroundBiomes.igneousStone, "igneous");
        this.registerHiddenBlock(oreBlock, UndergroundBiomes.metamorphicStone, "metamorphic");
        this.registerHiddenBlock(oreBlock, UndergroundBiomes.sedimentaryStone, "sedimentary");
        this.replacedOres.setAll(oreBlock);
        this.replacedBlockClasses.add(oreBlock.getClass());
    }

    public void setupUBOre(Block oreBlock, String overlayName, FMLPreInitializationEvent event) {
        assert (event != null);
        this.registerBlock(oreBlock, UndergroundBiomes.igneousStone, "igneous", overlayName);
        this.registerBlock(oreBlock, UndergroundBiomes.metamorphicStone, "metamorphic", overlayName);
        this.registerBlock(oreBlock, UndergroundBiomes.sedimentaryStone, "sedimentary", overlayName);
        this.replacedOres.setAll(oreBlock);
        this.replacedBlockClasses.add(oreBlock.getClass());
    }

    public void setupUBOre(Block oreBlock, String overlayName, int metadata, MinecraftName blockName, FMLPreInitializationEvent event) {
        assert (event != null);
        this.replacedOres.set(oreBlock, metadata);
        this.replacedBlockClasses.add(oreBlock.getClass());
        this.registerBlockWithMetadata(oreBlock, UndergroundBiomes.igneousStone, "igneous", overlayName, metadata, blockName);
        this.registerBlockWithMetadata(oreBlock, UndergroundBiomes.metamorphicStone, "metamorphic", overlayName, metadata, blockName);
        this.registerBlockWithMetadata(oreBlock, UndergroundBiomes.sedimentaryStone, "sedimentary", overlayName, metadata, blockName);
    }

    private void registerBlock(Block oreBlock, BlockMetadataBase ubStone, String rockName, String overlayName) {
        BlockOverlay overlay = new BlockOverlay(overlayName);
        BlockUBOre ubOre = new BlockUBOre(ubStone, oreBlock, overlay, this.renderID);
        NamedBlock namer = new NamedBlock(rockName + "_" + oreBlock.func_149739_a().substring(5));
        BlockOverlay.logger.info("block " + (Object)oreBlock + " no metadata ");
        GameRegistry.registerBlock((Block)ubOre, (Class)ItemUBOreBlock.class, (String)namer.internal());
        for (int i = 0; i < 16; ++i) {
            ((MetadataUBVersions)this.blockReplacer.item(oreBlock)).ubversions[i].set(ubStone, ubOre);
        }
        this.oreFor.put(ubOre, new ItemStack(oreBlock, 1, 1));
        this.overlayFor.put(ubOre, overlay);
        this.stoneFor.put(ubOre, (Block)ubStone);
        int blockID = Block.func_149682_b((Block)ubOre);
        Item matchedItem = Item.func_150899_d((int)blockID);
        Block matchedBlock = Block.func_149634_a((Item)matchedItem);
    }

    private void registerHiddenBlock(Block oreBlock, BlockMetadataBase ubStone, String rockName) {
        BlockUBHidden ubOre = new BlockUBHidden(ubStone, oreBlock);
        NamedBlock namer = new NamedBlock(rockName + "_" + oreBlock.func_149739_a().substring(5));
        BlockOverlay.logger.info("block " + (Object)oreBlock + " no metadata ");
        GameRegistry.registerBlock((Block)ubOre, (Class)ItemUBHiddenBlock.class, (String)namer.internal());
        for (int i = 0; i < 16; ++i) {
            ((MetadataUBVersions)this.blockReplacer.item(oreBlock)).ubversions[i].set(ubStone, ubOre);
        }
        this.oreFor.put(ubOre, new ItemStack(oreBlock, 1, 1));
        this.stoneFor.put(ubOre, (Block)ubStone);
        int blockID = Block.func_149682_b((Block)ubOre);
        Item matchedItem = Item.func_150899_d((int)blockID);
    }

    private void registerBlockWithMetadata(Block oreBlock, BlockMetadataBase ubStone, String rockName, String overlayName, int metadata, MinecraftName metadataBlockName) {
        BlockOverlay overlay = new BlockOverlay(overlayName);
        logger.info(metadataBlockName.localized() + " " + metadataBlockName.unlocalized());
        BlockState oreBlockState = new BlockState(oreBlock, metadata);
        BlockUBMetadataOre ubOre = new BlockUBMetadataOre(ubStone, oreBlockState, overlay, this.renderID, metadataBlockName);
        NamedBlock namer = null;
        BlockOverlay.logger.info("block " + (Object)oreBlock + " metadata " + metadata + " " + overlayName);
        namer = metadata == 0 ? new NamedBlock(rockName + "_" + oreBlock.func_149739_a().substring(5)) : new NamedBlock(rockName + "_" + oreBlock.func_149739_a().substring(5) + "." + metadata);
        GameRegistry.registerBlock((Block)ubOre, (Class)ItemUBOreBlock.class, (String)namer.internal());
        ((MetadataUBVersions)this.blockReplacer.item(oreBlock)).ubversions[metadata].set(ubStone, ubOre);
        this.oreFor.put(ubOre, new ItemStack(oreBlock, 1, metadata));
        this.overlayFor.put(ubOre, overlay);
        this.stoneFor.put(ubOre, (Block)ubStone);
        int blockID = Block.func_149682_b((Block)ubOre);
        Item matchedItem = Item.func_150899_d((int)blockID);
        Block matchedBlock = Block.func_149634_a((Item)matchedItem);
        UBStoneCodes testUBStone = new UBStoneCodes(ubStone.namer, 4);
        BlockState replacement = this.replacement(oreBlock, metadata, testUBStone, testUBStone);
        if (!this.replaces(oreBlock, metadata) && this.replacementActive && UndergroundBiomes.crashOnProblems()) {
            BlockOverlay.logger.info("blueschist instanceof BlockMetadataBase " + (testUBStone.block instanceof BlockMetadataBase));
            MetadataUBVersions versions = (MetadataUBVersions)this.blockReplacer.item(oreBlock);
            for (int i = 0; i < 16; ++i) {
                Block ore = versions.ubversions[i].ore((BlockMetadataBase)testUBStone.block).block();
                if (ore == null) {
                    BlockOverlay.logger.info("null in " + i);
                    continue;
                }
                BlockOverlay.logger.info(ore.func_149732_F() + " " + ore.toString() + i);
            }
            throw new RuntimeException();
        }
        if (!Block.func_149680_a((Block)replacement.block, (Block)ubOre) && UndergroundBiomes.crashOnProblems()) {
            throw new RuntimeException();
        }
        if (replacement.metadata != 4 && UndergroundBiomes.crashOnProblems()) {
            throw new RuntimeException();
        }
    }

    public int getRenderID() {
        return this.renderID.value();
    }

    public void setRenderer(RenderUBOre renderer) {
        this.renderID.set(renderer.getRenderId());
    }

    public BlockMetadataBase baseStone(Block ubVersion) {
        Block result = this.stoneFor.get((Object)ubVersion);
        if (result == null) {
            if (UndergroundBiomes.crashOnProblems()) {
                throw new RuntimeException("no ore for " + ubVersion.func_149739_a());
            }
            return UndergroundBiomes.igneousStone;
        }
        return (BlockMetadataBase)result;
    }

    public Block overlayBlock(Block ubVersion) {
        Block result = this.overlayFor.get((Object)ubVersion);
        if (result == null) {
            UndergroundBiomes.throwIfTesting("no overlay for " + ubVersion.func_149739_a());
            return UndergroundBiomes.igneousStone;
        }
        return result;
    }

    public void registerOres() {
        for (Block block : this.oreFor.keySet()) {
            ItemStack ore = this.oreFor.get((Object)block);
            try {
                int oreID = OreDictionary.getOreID((ItemStack)ore);
                for (int metadata = 0; metadata < 8; ++metadata) {
                    ItemStack metadataBlock = new ItemStack(block, 1, metadata);
                    OreDictionary.registerOre((int)oreID, (ItemStack)metadataBlock);
                }
                continue;
            }
            catch (NullPointerException e) {
                if (!UndergroundBiomes.crashOnProblems()) continue;
                throw e;
            }
        }
    }

    public boolean replacementActive() {
        return this.replacementActive;
    }

    public boolean replaces(Block possibleOre, int metadata) {
        if (this.replacementActive) {
            boolean result = this.replacedOres.has(possibleOre, metadata);
            return result;
        }
        return false;
    }

    public BlockState replacement(Block ore, int metadata, UBStoneCodes stone, UBStoneCodes defaultStone) {
        BlockMetadataBase baseStone;
        if (stone.block instanceof BlockMetadataBase) {
            baseStone = (BlockMetadataBase)stone.block;
        } else if (defaultStone.block instanceof BlockMetadataBase) {
            baseStone = (BlockMetadataBase)defaultStone.block;
        } else {
            return new BlockState(ore, metadata);
        }
        return ((MetadataUBVersions)this.blockReplacer.item(ore)).ubversions[metadata].convertedore(baseStone, stone.metadata);
    }

    public BlockReplacer blockReplacer(int blockID) {
        return this.blockReplacers[blockID];
    }

    public void renewBlockReplacers() {
        this.blockReplacers = new ConcreteBlockReplacer[4096];
        for (Block block : this.blockReplacer.keys()) {
            this.blockReplacers[Block.func_149682_b((Block)block)] = new ConcreteBlockReplacer((MetadataUBVersions)this.blockReplacer.item(block));
        }
    }

    private class ConcreteBlockReplacer
    implements BlockReplacer {
        ConcreteBlockStateReplacer[] replacers;

        public ConcreteBlockReplacer(MetadataUBVersions metadataVersions) {
            this.replacers = new ConcreteBlockStateReplacer[16];
            for (int i = 0; i < 16; ++i) {
                if (!metadataVersions.ubversions[i].active()) continue;
                this.replacers[i] = new ConcreteBlockStateReplacer(metadataVersions.ubversions[i]);
            }
        }

        public BlockStateReplacer replacer(int metadata) {
            return this.replacers[metadata];
        }
    }

    private class ConcreteBlockStateReplacer
    implements BlockStateReplacer {
        private final UBVersions versions;

        ConcreteBlockStateReplacer(UBVersions versions) {
            this.versions = versions;
        }

        public BlockState replacement(UBStoneCodes stone, UBStoneCodes defaultStone) {
            if (stone.block instanceof BlockMetadataBase) {
                return this.versions.convertedore((BlockMetadataBase)stone.block, stone.metadata);
            }
            if (defaultStone.block instanceof BlockMetadataBase) {
                return this.versions.convertedore((BlockMetadataBase)defaultStone.block, stone.metadata);
            }
            return null;
        }
    }

    public static interface BlockReplacer {
        public BlockStateReplacer replacer(int var1);
    }

    public static interface BlockStateReplacer {
        public BlockState replacement(UBStoneCodes var1, UBStoneCodes var2);
    }

    private class ReplacedOres {
        private HashMap<Block, boolean[]> flags;

        private ReplacedOres() {
            this.flags = new HashMap();
        }

        private boolean[] assuredFlags(Block block) {
            boolean[] result = this.flags.get((Object)block);
            if (result == null) {
                result = new boolean[16];
                this.flags.put(block, result);
            }
            return result;
        }

        void set(Block block, int metadata) {
            this.assuredFlags((Block)block)[metadata] = true;
        }

        boolean has(Block block, int metadata) {
            boolean[] has = this.flags.get((Object)block);
            if (has == null) {
                return false;
            }
            return has[metadata];
        }

        void setAll(Block block) {
            for (int i = 0; i < 16; ++i) {
                this.set(block, i);
            }
        }
    }

    private class MetadataIndexedBlock {
        private Block[] blocks;

        private MetadataIndexedBlock() {
            this.blocks = new Block[16];
        }

        public void set(Block block, int index) {
            this.blocks[index] = block;
        }

        public Block get(int index) {
            return this.blocks[index];
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    private class UBVersionsDictionary
    extends KeyedRegistry<Block, MetadataUBVersions> {
        UBVersionsDictionary() {
            super(new Function<Block, MetadataUBVersions>(){

                @Override
                public MetadataUBVersions result(Block baseOre) {
                    return new MetadataUBVersions();
                }
            });
        }

    }

    private class MetadataUBVersions {
        private UBVersions[] ubversions;

        MetadataUBVersions() {
            this.ubversions = new UBVersions[16];
            for (int i = 0; i < 16; ++i) {
                this.ubversions[i] = new UBVersions();
            }
        }
    }

    private class UBVersions {
        private final HashMap<BlockMetadataBase, BlockUBReplaceable> converter;
        private final HashMap<BlockMetadataBase, ArrayList<BlockState>> convertedBlockStates;

        private UBVersions() {
            this.converter = new HashMap();
            this.convertedBlockStates = new HashMap();
        }

        public void set(BlockMetadataBase ubStone, BlockUBReplaceable ubOre) {
            this.converter.put(ubStone, ubOre);
            ArrayList<BlockState> blockStates = new ArrayList<BlockState>();
            for (int i = 0; i < 8; ++i) {
                blockStates.add(new BlockState(ubOre.block(), i));
            }
            this.convertedBlockStates.put(ubStone, blockStates);
        }

        public boolean active() {
            return this.converter.size() > 0;
        }

        public BlockUBReplaceable ore(BlockMetadataBase stone) {
            return this.converter.get((Object)stone);
        }

        public BlockState convertedore(BlockMetadataBase stone, int metadata) {
            return this.convertedBlockStates.get((Object)stone).get(metadata);
        }
    }

}

