/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.IFuelHandler
 *  cpw.mods.fml.common.IWorldGenerator
 *  cpw.mods.fml.common.Loader
 *  cpw.mods.fml.common.Mod
 *  cpw.mods.fml.common.Mod$EventHandler
 *  cpw.mods.fml.common.SidedProxy
 *  cpw.mods.fml.common.event.FMLEvent
 *  cpw.mods.fml.common.event.FMLInitializationEvent
 *  cpw.mods.fml.common.event.FMLMissingMappingsEvent
 *  cpw.mods.fml.common.event.FMLModIdMappingEvent
 *  cpw.mods.fml.common.event.FMLPostInitializationEvent
 *  cpw.mods.fml.common.event.FMLPreInitializationEvent
 *  cpw.mods.fml.common.event.FMLServerAboutToStartEvent
 *  cpw.mods.fml.common.event.FMLServerStartedEvent
 *  cpw.mods.fml.common.event.FMLServerStartingEvent
 *  cpw.mods.fml.common.event.FMLServerStoppingEvent
 *  cpw.mods.fml.common.eventhandler.EventBus
 *  cpw.mods.fml.common.eventhandler.EventPriority
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.registry.GameData
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockSlab
 *  net.minecraft.command.ICommand
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.CraftingManager
 *  net.minecraft.item.crafting.FurnaceRecipes
 *  net.minecraft.item.crafting.IRecipe
 *  net.minecraft.item.crafting.ShapedRecipes
 *  net.minecraft.item.crafting.ShapelessRecipes
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.RegistryNamespaced
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldProvider
 *  net.minecraft.world.WorldServer
 *  net.minecraft.world.chunk.IChunkProvider
 *  net.minecraftforge.common.ForgeVersion
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.common.config.Configuration
 *  net.minecraftforge.event.terraingen.DecorateBiomeEvent
 *  net.minecraftforge.event.terraingen.DecorateBiomeEvent$Post
 *  net.minecraftforge.event.terraingen.OreGenEvent
 *  net.minecraftforge.event.terraingen.OreGenEvent$Post
 *  net.minecraftforge.event.terraingen.PopulateChunkEvent
 *  net.minecraftforge.event.terraingen.PopulateChunkEvent$Post
 *  net.minecraftforge.event.world.WorldEvent
 *  net.minecraftforge.event.world.WorldEvent$Load
 *  net.minecraftforge.event.world.WorldEvent$Unload
 *  net.minecraftforge.oredict.OreDictionary
 *  net.minecraftforge.oredict.OreDictionary$OreRegisterEvent
 *  net.minecraftforge.oredict.ShapedOreRecipe
 *  net.minecraftforge.oredict.ShapelessOreRecipe
 */
package exterminatorJeff.undergroundBiomes.common;

import Zeno410Utils.Acceptor;
import Zeno410Utils.ConfigManager;
import Zeno410Utils.Mutable;
import Zeno410Utils.PlayerDetector;
import Zeno410Utils.Zeno410Logger;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.IFuelHandler;
import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLEvent;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLMissingMappingsEvent;
import cpw.mods.fml.common.event.FMLModIdMappingEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerAboutToStartEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import cpw.mods.fml.common.eventhandler.EventBus;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameData;
import cpw.mods.fml.common.registry.GameRegistry;
import exterminatorJeff.undergroundBiomes.api.NamedBlock;
import exterminatorJeff.undergroundBiomes.api.NamedItem;
import exterminatorJeff.undergroundBiomes.api.NamedSlabPair;
import exterminatorJeff.undergroundBiomes.api.NamedVanillaBlock;
import exterminatorJeff.undergroundBiomes.api.NamedVanillaItem;
import exterminatorJeff.undergroundBiomes.api.UBAPIHook;
import exterminatorJeff.undergroundBiomes.api.UBIDs;
import exterminatorJeff.undergroundBiomes.api.UBOreTexturizer;
import exterminatorJeff.undergroundBiomes.api.UBSetProviderRegistry;
import exterminatorJeff.undergroundBiomes.api.UndergroundBiomesSettings;
import exterminatorJeff.undergroundBiomes.common.CommonProxy;
import exterminatorJeff.undergroundBiomes.common.CreativeTabModBlocks;
import exterminatorJeff.undergroundBiomes.common.DimensionManager;
import exterminatorJeff.undergroundBiomes.common.FuelManager;
import exterminatorJeff.undergroundBiomes.common.OreUBifyRequester;
import exterminatorJeff.undergroundBiomes.common.UndergroundBiomesNetworking;
import exterminatorJeff.undergroundBiomes.common.block.BlockIgneousCobblestone;
import exterminatorJeff.undergroundBiomes.common.block.BlockIgneousStone;
import exterminatorJeff.undergroundBiomes.common.block.BlockIgneousStoneBrick;
import exterminatorJeff.undergroundBiomes.common.block.BlockMetadataBase;
import exterminatorJeff.undergroundBiomes.common.block.BlockMetamorphicCobblestone;
import exterminatorJeff.undergroundBiomes.common.block.BlockMetamorphicStone;
import exterminatorJeff.undergroundBiomes.common.block.BlockMetamorphicStoneBrick;
import exterminatorJeff.undergroundBiomes.common.block.BlockSedimentaryStone;
import exterminatorJeff.undergroundBiomes.common.block.BlockStoneSlab;
import exterminatorJeff.undergroundBiomes.common.block.StoneSlabPair;
import exterminatorJeff.undergroundBiomes.common.command.CommandOreDictifyStone;
import exterminatorJeff.undergroundBiomes.common.item.ItemFossilPiece;
import exterminatorJeff.undergroundBiomes.common.item.ItemLigniteCoal;
import exterminatorJeff.undergroundBiomes.common.item.ItemMetadataBlock;
import exterminatorJeff.undergroundBiomes.common.item.ItemMetadataSlab;
import exterminatorJeff.undergroundBiomes.common.item.VanillaStoneRecipeManager;
import exterminatorJeff.undergroundBiomes.constructs.UndergroundBiomesConstructs;
import exterminatorJeff.undergroundBiomes.constructs.block.UBButtonBlockGroup;
import exterminatorJeff.undergroundBiomes.constructs.block.UBConstructGroup;
import exterminatorJeff.undergroundBiomes.constructs.block.UBStairsBlockGroup;
import exterminatorJeff.undergroundBiomes.constructs.block.UBTEWallGroup;
import exterminatorJeff.undergroundBiomes.constructs.util.UBCodeLocations;
import exterminatorJeff.undergroundBiomes.constructs.util.WatchList;
import exterminatorJeff.undergroundBiomes.intermod.ModOreManager;
import exterminatorJeff.undergroundBiomes.network.DirectChannel;
import exterminatorJeff.undergroundBiomes.network.PacketPipeline;
import exterminatorJeff.undergroundBiomes.worldGen.BiomeUndergroundDecorator;
import exterminatorJeff.undergroundBiomes.worldGen.OreUBifier;
import java.io.File;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.logging.Logger;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.command.ICommand;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.RegistryNamespaced;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@Mod(modid="UndergroundBiomes", name="Underground Biomes", version="0.8.beta49")
public class UndergroundBiomes
implements IWorldGenerator {
    public static Logger logger = new Zeno410Logger("UndergroundBiomes").logger();
    private static UndergroundBiomes instance;
    public static World world;
    public Configuration config;
    private DimensionManager dimensionManager;
    private boolean runningConfigIDs = false;
    public static String textures;
    public static CreativeTabModBlocks tabModBlocks;
    public static CreativeTabModBlocks tabModItems;
    public static long worldSeed;
    private boolean gotWorldSeed;
    public static BlockMetadataBase igneousStone;
    public static BlockMetadataBase igneousCobblestone;
    public static BlockMetadataBase igneousStoneBrick;
    public static BlockMetadataBase metamorphicStone;
    public static BlockMetadataBase metamorphicCobblestone;
    public static BlockMetadataBase metamorphicStoneBrick;
    public static BlockMetadataBase sedimentaryStone;
    public static Item ligniteCoal;
    public static Item fossilPiece;
    public static StoneSlabPair igneousBrickSlab;
    public static StoneSlabPair metamorphicBrickSlab;
    public static StoneSlabPair igneousStoneSlab;
    public static StoneSlabPair metamorphicStoneSlab;
    public static StoneSlabPair igneousCobblestoneSlab;
    public static StoneSlabPair metamorphicCobblestoneSlab;
    public static StoneSlabPair sedimentaryStoneSlab;
    public static ArrayList<String> fortuneAffected;
    public static ArrayList<ItemStack> nuggets;
    private static String[] nuggetStrings;
    private final UBCodeLocations serverCodeLocations = new UBCodeLocations();
    private final UBCodeLocations clientCodeLocations = new UBCodeLocations();
    private List<Integer> includeDimensionIDs;
    private List<Integer> excludeDimensionIDs;
    public UndergroundBiomesConstructs constructs;
    private UndergroundBiomesSettings settings = new UndergroundBiomesSettings(BlockIgneousStone.blockName, BlockMetamorphicStone.blockName, BlockSedimentaryStone.blockName);
    private ConfigManager<UndergroundBiomesSettings> configManager;
    @SidedProxy(clientSide="exterminatorJeff.undergroundBiomes.client.ClientProxy", serverSide="exterminatorJeff.undergroundBiomes.common.CommonProxy")
    public static CommonProxy proxy;
    public static String blockCategory;
    public static String itemCategory;
    private OreUBifier oreUBifier;
    private OreUBifyRequester oreRequester = new OreUBifyRequester();
    private ModOreManager modOreManager = new ModOreManager();
    private VanillaStoneRecipeManager vanillaStoneRecipeManager = new VanillaStoneRecipeManager(UndergroundBiomes.oreCobblestoneName());
    private PacketPipeline pipeline;
    private PlayerDetector playerDetector;
    private UndergroundBiomesNetworking networking;
    private boolean forceRemap;

    public UndergroundBiomes() {
        instance = this;
    }

    public static UndergroundBiomes instance() {
        return instance;
    }

    public boolean gotWorldSeed() {
        return this.gotWorldSeed;
    }

    private int igneousStoneID() {
        return UndergroundBiomes.instance().settings.igneousStoneID.value();
    }

    private int igneousCobblestoneID() {
        return UndergroundBiomes.instance().settings.igneousCobblestoneID.value();
    }

    private int igneousStoneBrickID() {
        return UndergroundBiomes.instance().settings.igneousStoneBrickID.value();
    }

    private int metamorphicStoneID() {
        return UndergroundBiomes.instance().settings.metamorphicStoneID.value();
    }

    private int metamorphicCobblestoneID() {
        return UndergroundBiomes.instance().settings.metamorphicCobblestoneID.value();
    }

    private int metamorphicStoneBrickID() {
        return UndergroundBiomes.instance().settings.metamorphicStoneBrickID.value();
    }

    private int sedimentaryStoneID() {
        return UndergroundBiomes.instance().settings.sedimentaryStoneID.value();
    }

    private int ligniteCoalID() {
        return UndergroundBiomes.instance().settings.ligniteCoalID.value();
    }

    private int fossilPieceID() {
        return UndergroundBiomes.instance().settings.fossilPieceID.value();
    }

    private int igneousBrickSlabHalfID() {
        return UndergroundBiomes.instance().settings.igneousBrickSlabHalfID.value();
    }

    private int igneousBrickSlabFullID() {
        return UndergroundBiomes.instance().settings.igneousBrickSlabFullID.value();
    }

    private int metamorphicBrickSlabHalfID() {
        return UndergroundBiomes.instance().settings.metamorphicBrickSlabHalfID.value();
    }

    private int metamorphicBrickSlabFullID() {
        return UndergroundBiomes.instance().settings.metamorphicBrickSlabFullID.value();
    }

    private int igneousStoneSlabHalfID() {
        return UndergroundBiomes.instance().settings.igneousStoneSlabHalfID.value();
    }

    private int igneousStoneSlabFullID() {
        return UndergroundBiomes.instance().settings.igneousStoneSlabFullID.value();
    }

    private int metamorphicStoneSlabHalfID() {
        return UndergroundBiomes.instance().settings.metamorphicStoneSlabHalfID.value();
    }

    private int metamorphicStoneSlabFullID() {
        return UndergroundBiomes.instance().settings.metamorphicStoneSlabFullID.value();
    }

    private int igneousCobblestoneSlabHalfID() {
        return UndergroundBiomes.instance().settings.igneousCobblestoneSlabHalfID.value();
    }

    private int igneousCobblestoneSlabFullID() {
        return UndergroundBiomes.instance().settings.igneousCobblestoneSlabFullID.value();
    }

    private int metamorphicCobblestoneSlabHalfID() {
        return UndergroundBiomes.instance().settings.metamorphicCobblestoneSlabHalfID.value();
    }

    private int metamorphicCobblestoneSlabFullID() {
        return UndergroundBiomes.instance().settings.metamorphicCobblestoneSlabFullID.value();
    }

    private int sedimentaryStoneSlabHalfID() {
        return UndergroundBiomes.instance().settings.sedimentaryStoneSlabHalfID.value();
    }

    private int sedimentaryStoneSlabFullID() {
        return UndergroundBiomes.instance().settings.sedimentaryStoneSlabFullID.value();
    }

    public final UBCodeLocations ubCodeLocations(World world) {
        if (world.field_72995_K) {
            return this.clientCodeLocations;
        }
        return this.serverCodeLocations;
    }

    public static final String includeDimensions() {
        return UndergroundBiomes.instance().settings.includeDimensions.value();
    }

    public static final String excludeDimensions() {
        return UndergroundBiomes.instance().settings.excludeDimensions.value();
    }

    public static final int vanillaStoneCrafting() {
        return UndergroundBiomes.instance().settings.vanillaStoneCrafting.value();
    }

    public static final float hardnessModifier() {
        return UndergroundBiomes.instance().settings.hardnessModifier.value().floatValue();
    }

    public static final float resistanceModifier() {
        return UndergroundBiomes.instance().settings.resistanceModifier.value().floatValue() / 3.0f;
    }

    public static final int generateHeight() {
        return UndergroundBiomes.instance().settings.generateHeight.value();
    }

    public UndergroundBiomesSettings settings() {
        return this.settings;
    }

    public static boolean addOreDictRecipes() {
        return UndergroundBiomes.instance().settings.addOreDictRecipes.value();
    }

    public static boolean vanillaStoneBiomes() {
        return UndergroundBiomes.instance().settings.vanillaStoneBiomes.value();
    }

    public static boolean buttonsOn() {
        return UndergroundBiomes.instance().settings.buttonsOn.value();
    }

    public static boolean stairsOn() {
        return UndergroundBiomes.instance().settings.stairsOn.value();
    }

    public static boolean wallsOn() {
        return UndergroundBiomes.instance().settings.wallsOn.value();
    }

    public static boolean harmoniousStrata() {
        return UndergroundBiomes.instance().settings.harmoniousStrata.value();
    }

    public static boolean replaceCobblestone() {
        return UndergroundBiomes.instance().settings.replaceCobblestone.value();
    }

    public static boolean replaceVillageGravel() {
        return UndergroundBiomes.instance().settings.replaceVillageGravel.value();
    }

    public static boolean crashOnProblems() {
        return UndergroundBiomes.instance().settings.crashOnProblems.value();
    }

    public static void throwIfTesting(RuntimeException toThrow) {
        if (UndergroundBiomes.crashOnProblems()) {
            throw toThrow;
        }
    }

    public static void throwIfTesting(String toThrow) {
        if (UndergroundBiomes.crashOnProblems()) {
            throw new RuntimeException(toThrow);
        }
    }

    public static void throwIfTesting(RuntimeException toThrow, String logMessage) {
        logger.info(logMessage);
        if (UndergroundBiomes.crashOnProblems()) {
            throw toThrow;
        }
    }

    public static boolean forceConfigIds() {
        return UndergroundBiomes.instance().settings.forceConfigIds.value();
    }

    public static boolean ubOres() {
        return UndergroundBiomes.instance().settings.ubOres.value();
    }

    public static int biomeSize() {
        return UndergroundBiomes.instance().settings.biomeSize.value();
    }

    OreUBifier oreUBifier() {
        return this.oreUBifier;
    }

    public int ubOreRenderID() {
        return this.oreUBifier.getRenderID();
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        this.config = new Configuration(event.getSuggestedConfigurationFile());
        this.config.load();
        this.settings.readFrom(this.config);
        this.configManager = new ConfigManager<UndergroundBiomesSettings>(this.config, this.settings, event.getSuggestedConfigurationFile());
        if (UndergroundBiomes.includeDimensions().equals("*")) {
            this.excludeDimensionIDs = new ArrayList<Integer>();
            for (String v : UndergroundBiomes.excludeDimensions().split(",")) {
                this.excludeDimensionIDs.add(Integer.parseInt(v));
            }
        } else {
            this.includeDimensionIDs = new ArrayList<Integer>();
            for (String v : UndergroundBiomes.includeDimensions().split(",")) {
                this.includeDimensionIDs.add(Integer.parseInt(v));
            }
        }
        this.oreUBifier = new OreUBifier(this.settings.ubOres);
        UBAPIHook.ubAPIHook.ubSetProviderRegistry = this.dimensionManager = new DimensionManager(this.settings, this.oreUBifier);
        this.config.save();
        fortuneAffected = new ArrayList();
        nuggets = new ArrayList();
        nuggets.add(new ItemStack(NamedVanillaItem.goldNugget.registeredItem(), 1, 0));
        assert (nuggets.get(0) != null);
        tabModBlocks = new CreativeTabModBlocks("undergroundBiomesBlocks");
        tabModItems = new CreativeTabModBlocks("undergroundBiomesItems");
        proxy.registerRenderThings(this.oreUBifier);
        igneousStone = new BlockIgneousStone();
        UBIDs.igneousStoneName.gameRegister((Block)igneousStone, ItemMetadataBlock.class);
        igneousCobblestone = new BlockIgneousCobblestone();
        UBIDs.igneousCobblestoneName.gameRegister((Block)igneousCobblestone, ItemMetadataBlock.class);
        igneousStoneBrick = new BlockIgneousStoneBrick();
        UBIDs.igneousStoneBrickName.gameRegister((Block)igneousStoneBrick, ItemMetadataBlock.class);
        metamorphicStone = new BlockMetamorphicStone();
        UBIDs.metamorphicStoneName.gameRegister((Block)metamorphicStone, ItemMetadataBlock.class);
        metamorphicCobblestone = new BlockMetamorphicCobblestone();
        UBIDs.metamorphicCobblestoneName.gameRegister((Block)metamorphicCobblestone, ItemMetadataBlock.class);
        metamorphicStoneBrick = new BlockMetamorphicStoneBrick();
        UBIDs.metamorphicStoneBrickName.gameRegister((Block)metamorphicStoneBrick, ItemMetadataBlock.class);
        sedimentaryStone = new BlockSedimentaryStone();
        UBIDs.sedimentaryStoneName.gameRegister((Block)sedimentaryStone, ItemMetadataBlock.class);
        igneousBrickSlab = this.stoneSlabPair(igneousStoneBrick, UBIDs.igneousBrickSlabName);
        metamorphicBrickSlab = this.stoneSlabPair(metamorphicStoneBrick, UBIDs.metamorphicBrickSlabName);
        igneousStoneSlab = this.stoneSlabPair(igneousStone, UBIDs.igneousStoneSlabName);
        metamorphicStoneSlab = this.stoneSlabPair(metamorphicStone, UBIDs.metamorphicStoneSlabName);
        igneousCobblestoneSlab = this.stoneSlabPair(igneousCobblestone, UBIDs.igneousCobblestoneSlabName);
        metamorphicCobblestoneSlab = this.stoneSlabPair(metamorphicCobblestone, UBIDs.metamorphicCobblestoneSlabName);
        sedimentaryStoneSlab = this.stoneSlabPair(sedimentaryStone, UBIDs.sedimentaryStoneSlabName);
        ligniteCoal = new ItemLigniteCoal(this.ligniteCoalID());
        fossilPiece = new ItemFossilPiece(this.fossilPieceID());
        fortuneAffected.add(ligniteCoal.func_77658_a());
        UndergroundBiomes.tabModItems.item = ligniteCoal;
        proxy.setUpBlockNames();
        this.constructs = new UndergroundBiomesConstructs();
        this.constructs.preInit(this.config);
        this.config.save();
        UBAPIHook.ubAPIHook.ubOreTexturizer.requestUBOreSetup(Blocks.field_150366_p, "undergroundbiomes:iron_overlay");
        UBAPIHook.ubAPIHook.ubOreTexturizer.setupUBOre(Blocks.field_150450_ax, "undergroundbiomes:redstone_overlay", event);
        this.oreUBifier.setupUBOre(Blocks.field_150365_q, "undergroundbiomes:coal_overlay", event);
        this.oreUBifier.setupUBOre(Blocks.field_150482_ag, "undergroundbiomes:diamond_overlay", event);
        this.oreUBifier.setupUBOre(Blocks.field_150369_x, "undergroundbiomes:lapis_overlay", event);
        this.oreUBifier.setupUBOre(Blocks.field_150412_bA, "undergroundbiomes:emerald_overlay", event);
        this.oreUBifier.setupUBOre(Blocks.field_150352_o, "undergroundbiomes:gold_overlay", event);
        this.oreUBifier.setupUBHidden(Blocks.field_150418_aU, event);
        this.oreRequester.fulfillRequests(event);
        FMLCommonHandler.instance().bus().register((Object)this);
        GameRegistry.registerWorldGenerator((IWorldGenerator)this, (int)10000);
        this.pipeline = new PacketPipeline();
    }

    public StoneSlabPair stoneSlabPair(BlockMetadataBase material, NamedSlabPair slabPairName) {
        BlockStoneSlab half = new BlockStoneSlab(false, (Block)material, slabPairName);
        BlockStoneSlab full = new BlockStoneSlab(true, (Block)material, slabPairName);
        GameRegistry.registerBlock((Block)half, (Class)ItemMetadataSlab.class, (String)slabPairName.half.internal(), (String)UBIDs.ubPrefix(), (Object[])new Object[]{full});
        GameRegistry.registerBlock((Block)full, (Class)ItemMetadataSlab.class, (String)slabPairName.full.internal(), (String)UBIDs.ubPrefix(), (Object[])new Object[]{half});
        return new StoneSlabPair(half, full);
    }

    @Mod.EventHandler
    public void load(FMLInitializationEvent event) {
        this.pipeline.initialise();
        this.playerDetector = new PlayerDetector();
        this.networking = new UndergroundBiomesNetworking(this.pipeline, this.settings);
        this.playerDetector.addLoginAction(new SettingsSender());
        this.addOreDicts();
        this.addRecipes();
        this.constructs.load(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) throws Exception {
        this.pipeline.postInitialise();
        if (UndergroundBiomes.addOreDictRecipes()) {
            UndergroundBiomes.oreDictifyStone();
        }
        this.addRescueRecipes();
        for (String s : nuggetStrings) {
            ArrayList stacks = OreDictionary.getOres((String)s);
            if (stacks.size() <= 0) continue;
            nuggets.add((ItemStack)stacks.get(0));
        }
        if (Loader.isModLoaded((String)"Thaumcraft")) {
            // empty if block
        }
        this.constructs.postInit(event);
        MinecraftForge.EVENT_BUS.register((Object)this);
        MinecraftForge.TERRAIN_GEN_BUS.register((Object)this);
        MinecraftForge.ORE_GEN_BUS.register((Object)this);
        MinecraftForge.EVENT_BUS.register((Object)this.dimensionManager);
        MinecraftForge.TERRAIN_GEN_BUS.register((Object)this.dimensionManager);
    }

    @Mod.EventHandler
    public void serverLoad(FMLServerAboutToStartEvent event) {
    }

    @Mod.EventHandler
    public void serverLoad(FMLServerStartingEvent event) {
        event.registerServerCommand((ICommand)new CommandOreDictifyStone());
        try {
            WorldServer server = event.getServer().func_71218_a(0);
            File worldLocation = server.getChunkSaveLocation();
            this.configManager.setWorldFile(worldLocation);
            this.dimensionManager.setupGenerators();
        }
        catch (NullPointerException e) {
            logger.info(e.toString());
        }
    }

    @Mod.EventHandler
    public void serverLoaded(FMLServerStartedEvent event) {
        if (this.forceRemap) {
            this.forceConfigIDs();
            GameData.freezeData();
            logger.info("forcing on remapping");
            this.runningConfigIDs = true;
        }
    }

    @Mod.EventHandler
    public void serverUnload(FMLServerStoppingEvent event) {
        int id;
        Block named;
        String name;
        for (Object key2 : Block.field_149771_c.func_148742_b()) {
            name = (String)key2;
            named = Block.func_149684_b((String)name);
            id = Block.func_149682_b((Block)named);
        }
        for (Object key2 : Item.field_150901_e.func_148742_b()) {
            name = (String)key2;
            named = (Item)Item.field_150901_e.func_82594_a(name);
            id = Item.func_150891_b((Item)named);
        }
        if (this.runningConfigIDs) {
            this.runningConfigIDs = false;
        }
        BiomeUndergroundDecorator.noMoreRedos();
    }

    @Mod.EventHandler
    public void onMissingMapping(FMLMissingMappingsEvent event) {
    }

    @Mod.EventHandler
    public void adjustMappings(FMLModIdMappingEvent event) {
        boolean oldIDs = false;
    }

    public void addRecipes() {
        int metadata;
        if (!UndergroundBiomes.addOreDictRecipes()) {
            GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(NamedVanillaBlock.furnace.block(), 1), new Object[]{"XXX", "X X", "XXX", Character.valueOf('X'), UndergroundBiomes.oreCobblestoneName()}));
            GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(NamedVanillaBlock.lever.block(), 1), new Object[]{"I", "X", Character.valueOf('X'), UndergroundBiomes.oreCobblestoneName(), Character.valueOf('I'), NamedVanillaItem.stick.cachedItem()}));
            GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(NamedVanillaBlock.piston.block(), 1), new Object[]{"WWW", "CIC", "CRC", Character.valueOf('W'), NamedVanillaBlock.planks.block(), Character.valueOf('C'), UndergroundBiomes.oreCobblestoneName(), Character.valueOf('I'), NamedVanillaItem.ingotIron.cachedItem(), Character.valueOf('R'), NamedVanillaItem.redstone.cachedItem()}));
            if (!UndergroundBiomes.stairsOn()) {
                GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(NamedVanillaBlock.stairsCobblestone.block(), 4), new Object[]{"X  ", "XX ", "XXX", Character.valueOf('X'), UndergroundBiomes.oreCobblestoneName()}));
            }
            if (!UndergroundBiomes.wallsOn()) {
                GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(NamedVanillaBlock.cobblestone_wall.block(), 1), new Object[]{"XXX", "XXX", Character.valueOf('X'), UndergroundBiomes.oreCobblestoneName()}));
            }
            GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(NamedVanillaItem.axeStone.cachedItem(), 1), new Object[]{"XX ", "XW ", " W ", Character.valueOf('X'), UndergroundBiomes.oreCobblestoneName(), Character.valueOf('W'), NamedVanillaItem.stick.cachedItem()}));
            GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(NamedVanillaItem.pickaxeStone.cachedItem(), 1), new Object[]{"XXX", " W ", " W ", Character.valueOf('X'), UndergroundBiomes.oreCobblestoneName(), Character.valueOf('W'), NamedVanillaItem.stick.cachedItem()}));
            GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(NamedVanillaItem.hoeStone.cachedItem(), 1), new Object[]{"XX ", " W ", " W ", Character.valueOf('X'), UndergroundBiomes.oreCobblestoneName(), Character.valueOf('W'), NamedVanillaItem.stick.cachedItem()}));
            GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(NamedVanillaItem.shovelStone.cachedItem(), 1), new Object[]{" X ", " W ", " W ", Character.valueOf('X'), UndergroundBiomes.oreCobblestoneName(), Character.valueOf('W'), NamedVanillaItem.stick.cachedItem()}));
            GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(NamedVanillaItem.swordStone.cachedItem(), 1), new Object[]{"X", "X", "W", Character.valueOf('X'), UndergroundBiomes.oreCobblestoneName(), Character.valueOf('W'), NamedVanillaItem.stick.cachedItem()}));
            GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(NamedVanillaItem.brewingStand.cachedItem(), 1), new Object[]{" B ", "XXX", Character.valueOf('X'), UndergroundBiomes.oreCobblestoneName(), Character.valueOf('B'), NamedVanillaItem.blazeRod.cachedItem()}));
            GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(NamedVanillaBlock.dispenser.block(), 1), new Object[]{"XXX", "XBX", "XRX", Character.valueOf('X'), UndergroundBiomes.oreCobblestoneName(), Character.valueOf('B'), NamedVanillaItem.bow.cachedItem(), Character.valueOf('R'), NamedVanillaItem.redstone.cachedItem()}));
            GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(NamedVanillaBlock.stone_pressure_plate.block(), 1), new Object[]{"XX", Character.valueOf('X'), UndergroundBiomes.oreStoneName()}));
            GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(NamedVanillaItem.redstoneRepeater.cachedItem(), 1), new Object[]{"TRT", "XXX", Character.valueOf('X'), UndergroundBiomes.oreStoneName(), Character.valueOf('T'), NamedVanillaBlock.torchRedstoneActive.block(), Character.valueOf('R'), NamedVanillaItem.redstone.cachedItem()}));
            if (!UndergroundBiomes.stairsOn()) {
                GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(NamedVanillaBlock.stairsStoneBrick.block(), 4), new Object[]{"X  ", "XX ", "XXX", Character.valueOf('X'), "stoneBricks"}));
            }
            if (!UndergroundBiomes.buttonsOn()) {
                GameRegistry.addRecipe((IRecipe)new ShapelessOreRecipe(new ItemStack(NamedVanillaBlock.stoneButton.block(), 1), new Object[]{UndergroundBiomes.oreStoneName()}));
            }
        }
        GameRegistry.addRecipe((ItemStack)new ItemStack(NamedVanillaItem.coal.cachedItem(), 1), (Object[])new Object[]{"XXX", "XXX", "XXX", Character.valueOf('X'), ligniteCoal});
        GameRegistry.addShapelessRecipe((ItemStack)new ItemStack(Items.field_151100_aR, 1, 15), (Object[])new Object[]{new ItemStack(fossilPiece, 1, 32767)});
        this.vanillaStoneRecipeManager.accept(UndergroundBiomes.vanillaStoneCrafting());
        this.settings.vanillaStoneCrafting.informOnChange(this.vanillaStoneRecipeManager);
        for (int i = 0; i < 8; ++i) {
            GameRegistry.addRecipe((ItemStack)new ItemStack((Block)UndergroundBiomes.igneousBrickSlab.half, 6, i), (Object[])new Object[]{"XXX", Character.valueOf('X'), new ItemStack((Block)igneousStoneBrick, 1, i)});
            GameRegistry.addRecipe((ItemStack)new ItemStack((Block)UndergroundBiomes.metamorphicBrickSlab.half, 6, i), (Object[])new Object[]{"XXX", Character.valueOf('X'), new ItemStack((Block)metamorphicStoneBrick, 1, i)});
            GameRegistry.addRecipe((ItemStack)new ItemStack((Block)UndergroundBiomes.igneousStoneSlab.half, 6, i), (Object[])new Object[]{"XXX", Character.valueOf('X'), new ItemStack((Block)igneousStone, 1, i)});
            GameRegistry.addRecipe((ItemStack)new ItemStack((Block)UndergroundBiomes.metamorphicStoneSlab.half, 6, i), (Object[])new Object[]{"XXX", Character.valueOf('X'), new ItemStack((Block)metamorphicStone, 1, i)});
            GameRegistry.addRecipe((ItemStack)new ItemStack((Block)UndergroundBiomes.igneousCobblestoneSlab.half, 6, i), (Object[])new Object[]{"XXX", Character.valueOf('X'), new ItemStack((Block)igneousCobblestone, 1, i)});
            GameRegistry.addRecipe((ItemStack)new ItemStack((Block)UndergroundBiomes.metamorphicCobblestoneSlab.half, 6, i), (Object[])new Object[]{"XXX", Character.valueOf('X'), new ItemStack((Block)metamorphicCobblestone, 1, i)});
            GameRegistry.addRecipe((ItemStack)new ItemStack((Block)UndergroundBiomes.sedimentaryStoneSlab.half, 6, i), (Object[])new Object[]{"XXX", Character.valueOf('X'), new ItemStack((Block)sedimentaryStone, 1, i)});
            FurnaceRecipes.func_77602_a().func_151394_a(new ItemStack((Block)metamorphicCobblestone, 1, i), new ItemStack((Block)metamorphicStone, 1, i), 0.1f);
            FurnaceRecipes.func_77602_a().func_151394_a(new ItemStack((Block)igneousCobblestone, 1, i), new ItemStack((Block)igneousStone, 1, i), 0.1f);
            GameRegistry.addRecipe((ItemStack)new ItemStack((Block)metamorphicStoneBrick, 4, i), (Object[])new Object[]{"xx", "xx", Character.valueOf('x'), new ItemStack((Block)metamorphicStone, 1, i)});
            GameRegistry.addRecipe((ItemStack)new ItemStack((Block)igneousStoneBrick, 4, i), (Object[])new Object[]{"xx", "xx", Character.valueOf('x'), new ItemStack((Block)igneousStone, 1, i)});
        }
        GameRegistry.registerFuelHandler((IFuelHandler)new FuelManager());
        for (metadata = 0; metadata < 8; ++metadata) {
            GameRegistry.addRecipe((IRecipe)new ShapelessOreRecipe(new ItemStack((Block)Blocks.field_150333_U, 1, 0), new Object[]{new ItemStack((Block)UndergroundBiomes.igneousStoneSlab.half, 1, metadata)}));
            GameRegistry.addRecipe((IRecipe)new ShapelessOreRecipe(new ItemStack((Block)Blocks.field_150333_U, 1, 0), new Object[]{new ItemStack((Block)UndergroundBiomes.metamorphicStoneSlab.half, 1, metadata)}));
        }
        GameRegistry.addRecipe((IRecipe)new ShapelessOreRecipe(new ItemStack((Block)Blocks.field_150333_U, 1, 1), new Object[]{new ItemStack((Block)UndergroundBiomes.sedimentaryStoneSlab.half, 1)}));
        for (metadata = 0; metadata < 8; ++metadata) {
            GameRegistry.addRecipe((IRecipe)new ShapelessOreRecipe(new ItemStack((Block)Blocks.field_150333_U, 1, 3), new Object[]{new ItemStack((Block)UndergroundBiomes.igneousCobblestoneSlab.half, 1, metadata)}));
            GameRegistry.addRecipe((IRecipe)new ShapelessOreRecipe(new ItemStack((Block)Blocks.field_150333_U, 1, 3), new Object[]{new ItemStack((Block)UndergroundBiomes.metamorphicCobblestoneSlab.half, 1, metadata)}));
        }
    }

    private void addRescueRecipes() {
        int metadata;
        for (metadata = 0; metadata < 8; ++metadata) {
        }
        for (metadata = 8; metadata < 16; ++metadata) {
            GameRegistry.addRecipe((IRecipe)new ShapelessOreRecipe(new ItemStack(Blocks.field_150446_ar, 1, 0), new Object[]{this.constructs.stoneStair().productItemDefiner(metadata).one()}));
            GameRegistry.addRecipe((IRecipe)new ShapelessOreRecipe(new ItemStack(Blocks.field_150463_bK, 1, 0), new Object[]{this.constructs.stoneWall().productItemDefiner(metadata).one()}));
        }
        for (metadata = 16; metadata < 24; ++metadata) {
            GameRegistry.addRecipe((IRecipe)new ShapelessOreRecipe(new ItemStack(Blocks.field_150390_bg, 1, 0), new Object[]{this.constructs.stoneStair().productItemDefiner(metadata).one()}));
        }
        for (metadata = 24; metadata < 32; ++metadata) {
        }
        for (metadata = 32; metadata < 40; ++metadata) {
            GameRegistry.addRecipe((IRecipe)new ShapelessOreRecipe(new ItemStack(Blocks.field_150446_ar, 1, 0), new Object[]{this.constructs.stoneStair().productItemDefiner(metadata).one()}));
            GameRegistry.addRecipe((IRecipe)new ShapelessOreRecipe(new ItemStack(Blocks.field_150463_bK, 1, 0), new Object[]{this.constructs.stoneWall().productItemDefiner(metadata).one()}));
        }
        for (metadata = 40; metadata < 48; ++metadata) {
            GameRegistry.addRecipe((IRecipe)new ShapelessOreRecipe(new ItemStack(Blocks.field_150390_bg, 1, 0), new Object[]{this.constructs.stoneStair().productItemDefiner(metadata).one()}));
        }
        for (metadata = 48; metadata < 56; ++metadata) {
            GameRegistry.addRecipe((IRecipe)new ShapelessOreRecipe(new ItemStack(Blocks.field_150430_aB, 1, 0), new Object[]{new ItemStack(this.constructs.stoneButton().construct, 1, metadata)}));
        }
    }

    public void addOreDicts() {
        OreDictionary.registerOre((String)UndergroundBiomes.oreStoneName(), (ItemStack)new ItemStack((Block)igneousStone, 1, 32767));
        OreDictionary.registerOre((String)UndergroundBiomes.oreStoneName(), (ItemStack)new ItemStack((Block)metamorphicStone, 1, 32767));
        OreDictionary.registerOre((String)UndergroundBiomes.oreStoneName(), (ItemStack)new ItemStack((Block)sedimentaryStone, 1, 32767));
        OreDictionary.registerOre((String)UndergroundBiomes.oreCobblestoneName(), (ItemStack)new ItemStack((Block)igneousCobblestone, 1, 32767));
        OreDictionary.registerOre((String)UndergroundBiomes.oreCobblestoneName(), (ItemStack)new ItemStack((Block)metamorphicCobblestone, 1, 32767));
        OreDictionary.registerOre((String)"stoneBricks", (ItemStack)new ItemStack((Block)igneousStoneBrick, 1, 32767));
        OreDictionary.registerOre((String)"stoneBricks", (ItemStack)new ItemStack((Block)metamorphicStoneBrick, 1, 32767));
        for (int i = 0; i < 0; ++i) {
            OreDictionary.registerOre((String)"stone", (ItemStack)new ItemStack((Block)igneousStone, 1, i));
            OreDictionary.registerOre((String)"stone", (ItemStack)new ItemStack((Block)metamorphicStone, 1, i));
            OreDictionary.registerOre((String)"stone", (ItemStack)new ItemStack((Block)sedimentaryStone, 1, i));
        }
        this.oreUBifier.registerOres();
    }

    public static int oreDictifyStone() throws Exception {
        ItemStack output;
        int numReplaced = 0;
        HashMap<ItemStack, String> replacements = new HashMap<ItemStack, String>();
        replacements.put(new ItemStack(NamedVanillaBlock.stone.block(), 1, 32767), UndergroundBiomes.oreStoneName());
        replacements.put(new ItemStack(NamedVanillaBlock.cobblestone.block(), 1, 32767), UndergroundBiomes.oreCobblestoneName());
        replacements.put(new ItemStack(NamedVanillaBlock.stoneBrick.block(), 1, 32767), "stoneBricks");
        ItemStack[] replaceStacks = replacements.keySet().toArray((T[])new ItemStack[replacements.keySet().size()]);
        ItemStack[] exclusions = new ItemStack[]{new ItemStack(NamedVanillaBlock.stairsStoneBrick.block()), new ItemStack(NamedVanillaBlock.stoneBrick.block()), new ItemStack(NamedVanillaBlock.stoneSingleSlab.block(), 1, 5)};
        List recipes = CraftingManager.func_77594_a().func_77592_b();
        Constructor shapedConstr = ShapedOreRecipe.class.getDeclaredConstructor(ShapedRecipes.class, Map.class);
        Constructor shapelessConstr = ShapelessOreRecipe.class.getDeclaredConstructor(ShapelessRecipes.class, Map.class);
        shapedConstr.setAccessible(true);
        shapelessConstr.setAccessible(true);
        if (UndergroundBiomes.buttonsOn()) {
            Iterator iterator = CraftingManager.func_77594_a().func_77592_b().iterator();
            while (iterator.hasNext()) {
                IRecipe recipe = (IRecipe)iterator.next();
                if (recipe == null || (output = recipe.func_77571_b()) == null || !NamedVanillaBlock.stoneButton.matches(output.func_77973_b())) continue;
                iterator.remove();
            }
        }
        for (ItemStack stack : replacements.keySet()) {
            OreDictionary.registerOre((String)((String)replacements.get((Object)stack)), (ItemStack)stack);
        }
        for (int i = 0; i < recipes.size(); ++i) {
            ShapedRecipes recipe;
            Object obj = recipes.get(i);
            output = ((IRecipe)obj).func_77571_b();
            if (output != null && UndergroundBiomes.containsMatch(false, exclusions, output) || obj == null || UndergroundBiomesConstructs.overridesRecipe((IRecipe)obj)) continue;
            if (obj instanceof ShapedRecipes) {
                recipe = (ShapedRecipes)obj;
                if (!UndergroundBiomes.containsMatch(true, recipe.field_77574_d, replaceStacks)) continue;
                recipes.set(i, (ShapedOreRecipe)shapedConstr.newInstance(new Object[]{recipe, replacements}));
                ++numReplaced;
                System.out.println("Changed shaped recipe for " + output.func_82833_r());
                continue;
            }
            if (obj instanceof ShapelessRecipes) {
                recipe = (ShapelessRecipes)obj;
                List recipeItems = recipe.field_77579_b;
                if (recipeItems == null || !UndergroundBiomes.containsMatch(true, recipeItems.toArray((T[])new ItemStack[recipeItems.size()]), replaceStacks)) continue;
                recipes.set(i, (ShapelessOreRecipe)shapelessConstr.newInstance(new Object[]{recipe, replacements}));
                ++numReplaced;
                System.out.println("Changed shapeless recipe for " + output.func_82833_r());
                continue;
            }
            if (obj instanceof ShapedOreRecipe) {
                recipe = (ShapedOreRecipe)obj;
                if (!UndergroundBiomes.containsMatchReplaceInplace(true, recipe.getInput(), replaceStacks, replacements)) continue;
                ++numReplaced;
                System.out.println("Changed shaped ore recipe for " + output.func_82833_r());
                continue;
            }
            if (!(obj instanceof ShapelessOreRecipe) || !UndergroundBiomes.containsMatchReplaceInplace(true, (recipe = (ShapelessOreRecipe)obj).getInput(), replaceStacks, replacements)) continue;
            ++numReplaced;
            System.out.println("Changed shapeless ore recipe for " + output.func_82833_r());
        }
        CraftingManager.func_77594_a().func_77596_b(new ItemStack(NamedVanillaBlock.stoneButton.block()), new Object[]{NamedVanillaBlock.stone.block()});
        return numReplaced;
    }

    private static /* varargs */ boolean containsMatch(boolean strict, ItemStack[] inputs, ItemStack ... targets) {
        try {
            for (ItemStack input : inputs) {
                for (ItemStack target : targets) {
                    if (!OreDictionary.itemMatches((ItemStack)target, (ItemStack)input, (boolean)strict)) continue;
                    return true;
                }
            }
        }
        catch (NullPointerException e) {
            return false;
        }
        return false;
    }

    private static boolean containsMatchReplaceInplace(boolean strict, Object inputArrayOrList, ItemStack[] targets, Map<ItemStack, String> replacements) {
        boolean replaced = false;
        if (inputArrayOrList instanceof ArrayList) {
            ArrayList inputList = (ArrayList)inputArrayOrList;
            for (int i = 0; i < inputList.size(); ++i) {
                Object input = inputList.get(i);
                if (!(input instanceof ItemStack)) continue;
                for (ItemStack target : targets) {
                    if (!OreDictionary.itemMatches((ItemStack)target, (ItemStack)((ItemStack)input), (boolean)strict)) continue;
                    inputList.set(i, OreDictionary.getOres((String)replacements.get((Object)target)));
                    replaced = true;
                }
            }
        } else {
            Object[] inputArray = (Object[])inputArrayOrList;
            for (int i = 0; i < inputArray.length; ++i) {
                Object input = inputArray[i];
                if (!(input instanceof ItemStack)) continue;
                for (ItemStack target : targets) {
                    if (!OreDictionary.itemMatches((ItemStack)target, (ItemStack)((ItemStack)input), (boolean)strict)) continue;
                    inputArray[i] = OreDictionary.getOres((String)replacements.get((Object)target));
                    replaced = true;
                }
            }
        }
        return replaced;
    }

    public static long getWorldSeed() {
        return worldSeed;
    }

    public static World getWorld() {
        return world;
    }

    public boolean inChunkGenerationAllowed(int id) {
        return this.dimensionManager.inChunkGenerationAllowed(id);
    }

    public void generate(Random arg0, int x, int z, World world, IChunkProvider arg4, IChunkProvider arg5) {
        this.redoOres(x * 16, z * 16, world);
    }

    @SubscribeEvent
    public void onWorldLoad(WorldEvent.Load event) {
        if (!this.gotWorldSeed) {
            world = event.world;
            if (UndergroundBiomes.world.field_73011_w.field_76574_g == 0) {
                worldSeed = world.func_72905_C();
                this.gotWorldSeed = true;
            }
        }
        UndergroundBiomes.tabModBlocks.item = ItemMetadataBlock.itemFrom(UBIDs.igneousStoneBrickName);
    }

    @SubscribeEvent
    public void onWorldUnload(WorldEvent.Unload event) {
        this.gotWorldSeed = false;
        this.serverCodeLocations.clear();
        this.clientCodeLocations.clear();
        this.dimensionManager.unload();
    }

    @SubscribeEvent(priority=EventPriority.NORMAL)
    public void onBiomeDecorate(DecorateBiomeEvent.Post event) {
    }

    @SubscribeEvent(priority=EventPriority.LOWEST)
    public void onBiomePopulate(PopulateChunkEvent.Post event) {
        this.dimensionManager.onBiomeDecorate(event);
        this.dimensionManager.redoOres(event.chunkX * 16, event.chunkZ * 16, event.world);
    }

    @SubscribeEvent(priority=EventPriority.LOWEST)
    public void onOreGenerate(OreGenEvent.Post event) {
        if (this.oreUBifier.replacementActive()) {
            this.dimensionManager.redoOres(event.worldX, event.worldZ, event.world);
        }
    }

    @SubscribeEvent
    public void registerOre(OreDictionary.OreRegisterEvent event) {
        if (Arrays.asList(nuggets).contains(event.Name)) {
            nuggets.add(event.Ore);
        }
    }

    public void redoOres(int x, int z, World world) {
        this.dimensionManager.redoOres(x, z, world);
    }

    public static String oreStoneName() {
        if (UndergroundBiomes.forgeVersion() < 934) {
            return "stoneSmooth";
        }
        return "stone";
    }

    public static String oreCobblestoneName() {
        if (UndergroundBiomes.forgeVersion() < 934) {
            return "stoneCobble";
        }
        return "cobblestone";
    }

    public static int forgeVersion() {
        return ForgeVersion.getBuildVersion();
    }

    private WatchList configList() {
        WatchList forcing = new WatchList();
        forcing.addChangeWithItem(this.igneousStoneID(), (Block)igneousStone);
        forcing.addChangeWithItem(this.metamorphicStoneID(), (Block)metamorphicStone);
        forcing.addChangeWithItem(this.sedimentaryStoneID(), (Block)sedimentaryStone);
        forcing.addChangeWithItem(this.igneousCobblestoneID(), (Block)igneousCobblestone);
        forcing.addChangeWithItem(this.metamorphicCobblestoneID(), (Block)metamorphicCobblestone);
        forcing.addChangeWithItem(this.igneousStoneBrickID(), (Block)igneousStoneBrick);
        forcing.addChangeWithItem(this.metamorphicStoneBrickID(), (Block)metamorphicStoneBrick);
        forcing.addChangeWithItem(this.igneousBrickSlabHalfID(), (Block)UndergroundBiomes.igneousBrickSlab.half);
        forcing.addChangeWithItem(this.igneousBrickSlabFullID(), (Block)UndergroundBiomes.igneousBrickSlab.full);
        forcing.addChangeWithItem(this.metamorphicBrickSlabHalfID(), (Block)UndergroundBiomes.metamorphicBrickSlab.half);
        forcing.addChangeWithItem(this.metamorphicBrickSlabFullID(), (Block)UndergroundBiomes.metamorphicBrickSlab.full);
        forcing.addChangeWithItem(this.igneousStoneSlabHalfID(), (Block)UndergroundBiomes.igneousStoneSlab.half);
        forcing.addChangeWithItem(this.igneousStoneSlabFullID(), (Block)UndergroundBiomes.igneousStoneSlab.full);
        forcing.addChangeWithItem(this.metamorphicStoneSlabHalfID(), (Block)UndergroundBiomes.metamorphicStoneSlab.half);
        forcing.addChangeWithItem(this.metamorphicStoneSlabFullID(), (Block)UndergroundBiomes.metamorphicStoneSlab.full);
        forcing.addChangeWithItem(this.igneousCobblestoneSlabHalfID(), (Block)UndergroundBiomes.igneousCobblestoneSlab.half);
        forcing.addChangeWithItem(this.igneousCobblestoneSlabFullID(), (Block)UndergroundBiomes.igneousCobblestoneSlab.full);
        forcing.addChangeWithItem(this.metamorphicCobblestoneSlabHalfID(), (Block)UndergroundBiomes.metamorphicCobblestoneSlab.half);
        forcing.addChangeWithItem(this.metamorphicCobblestoneSlabFullID(), (Block)UndergroundBiomes.metamorphicCobblestoneSlab.full);
        forcing.addChangeWithItem(this.sedimentaryStoneSlabHalfID(), (Block)UndergroundBiomes.sedimentaryStoneSlab.half);
        forcing.addChangeWithItem(this.sedimentaryStoneSlabFullID(), (Block)UndergroundBiomes.sedimentaryStoneSlab.full);
        return forcing;
    }

    private WatchList defaultIDs() {
        WatchList forcing = new WatchList();
        forcing.addWithItem((Block)igneousStone);
        forcing.addWithItem((Block)metamorphicStone);
        forcing.addWithItem((Block)sedimentaryStone);
        forcing.addWithItem((Block)igneousCobblestone);
        forcing.addWithItem((Block)metamorphicCobblestone);
        forcing.addWithItem((Block)igneousStoneBrick);
        forcing.addWithItem((Block)metamorphicStoneBrick);
        forcing.addWithItem((Block)UndergroundBiomes.igneousBrickSlab.half);
        forcing.addWithItem((Block)UndergroundBiomes.igneousBrickSlab.full);
        forcing.addWithItem((Block)UndergroundBiomes.metamorphicBrickSlab.half);
        forcing.addWithItem((Block)UndergroundBiomes.metamorphicBrickSlab.full);
        forcing.addWithItem((Block)UndergroundBiomes.igneousStoneSlab.half);
        forcing.addWithItem((Block)UndergroundBiomes.igneousStoneSlab.full);
        forcing.addWithItem((Block)UndergroundBiomes.metamorphicStoneSlab.half);
        forcing.addWithItem((Block)UndergroundBiomes.metamorphicStoneSlab.full);
        forcing.addWithItem((Block)UndergroundBiomes.igneousCobblestoneSlab.half);
        forcing.addWithItem((Block)UndergroundBiomes.igneousCobblestoneSlab.full);
        forcing.addWithItem((Block)UndergroundBiomes.metamorphicCobblestoneSlab.half);
        forcing.addWithItem((Block)UndergroundBiomes.metamorphicCobblestoneSlab.full);
        forcing.addWithItem((Block)UndergroundBiomes.sedimentaryStoneSlab.half);
        forcing.addWithItem((Block)UndergroundBiomes.sedimentaryStoneSlab.full);
        forcing.add(this.constructs.stoneButton().construct);
        forcing.add(this.constructs.stoneStair().construct);
        forcing.add(this.constructs.stoneWall().construct);
        return forcing;
    }

    private void forceConfigIDs() {
        throw new RuntimeException();
    }

    static /* synthetic */ UndergroundBiomesNetworking access$200(UndergroundBiomes x0) {
        return x0.networking;
    }

    static {
        textures = "/exterminatorJeff/undergroundBiomes/textures.png";
        nuggetStrings = new String[]{"nuggetIron", "nuggetCopper", "nuggetTin", "nuggetSilver", "nuggetLead", "nuggetAluminium", "nuggetNaturalAluminium", "nuggetNickel", "nuggetPlatinum", "nuggetElectrum", "nuggetZinc"};
        blockCategory = "block";
        itemCategory = "item";
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    private class SettingsSender
    extends Acceptor<EntityPlayerMP> {
        private SettingsSender() {
        }

        @Override
        public void accept(EntityPlayerMP accepted) {
            UndergroundBiomes.access$200((UndergroundBiomes)UndergroundBiomes.this).settings.sendTo(UndergroundBiomes.this.settings, accepted);
        }
    }

    public class EventWatcher {
        public void processEvent(FMLEvent event) {
        }
    }

}

