/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  com.teammetallurgy.metallurgy.metals.MetalBlock
 *  cpw.mods.fml.common.event.FMLPreInitializationEvent
 *  net.minecraft.block.Block
 *  net.minecraft.world.World
 */
package exterminatorJeff.undergroundBiomes.common;

import Zeno410Utils.MinecraftName;
import Zeno410Utils.Zeno410Logger;
import com.teammetallurgy.metallurgy.metals.MetalBlock;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import exterminatorJeff.undergroundBiomes.api.UBAPIHook;
import exterminatorJeff.undergroundBiomes.api.UBOreTexturizer;
import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;
import exterminatorJeff.undergroundBiomes.worldGen.OreUBifier;
import java.util.HashSet;
import java.util.logging.Logger;
import net.minecraft.block.Block;
import net.minecraft.world.World;

public class OreUBifyRequester
implements UBOreTexturizer {
    private static Logger logger = new Zeno410Logger("OrUBifyRequester").logger();
    private HashSet<UBifyRequest> waitingRequests = new HashSet();
    private boolean alreadyRun = false;

    OreUBifyRequester() {
        UBAPIHook.ubAPIHook.ubOreTexturizer = this;
    }

    @Deprecated
    public void setupUBOre(Block oreBlock, int metadata, String overlayName, FMLPreInitializationEvent event) {
        logger.info("setup attempt");
        assert (oreBlock != null);
        assert (metadata >= 0);
        assert (metadata < 16);
        assert (overlayName != null);
        UndergroundBiomes.instance().oreUBifier().setupUBOre(oreBlock, overlayName, metadata, OreUBifyRequester.minecraftName(oreBlock, metadata), event);
    }

    @Deprecated
    public void requestUBOreSetup(Block oreBlock, int metadata, String overlayName) throws UBOreTexturizer.BlocksAreAlreadySet {
        logger.info("setup request for " + oreBlock.func_149732_F() + " " + overlayName);
        assert (oreBlock != null);
        assert (metadata >= 0);
        assert (metadata < 16);
        assert (overlayName != null);
        logger.info("request OK");
        this.waitingRequests.add(new UBifyRequestWithMetadata(oreBlock, metadata, overlayName));
    }

    public void setupUBOre(Block oreBlock, int metadata, String overlayName, String blockName, FMLPreInitializationEvent event) {
        this.setupUBOre(oreBlock, metadata, overlayName, new MinecraftName(blockName), event);
    }

    private void setupUBOre(Block oreBlock, int metadata, String overlayName, MinecraftName blockName, FMLPreInitializationEvent event) {
        UndergroundBiomes.instance().oreUBifier().setupUBOre(oreBlock, overlayName, metadata, blockName, event);
    }

    public void requestUBOreSetup(Block oreBlock, int metadata, String overlayName, String blockName) throws UBOreTexturizer.BlocksAreAlreadySet {
        logger.info("setup request for " + oreBlock.func_149732_F() + " : " + blockName + " " + overlayName);
        assert (oreBlock != null);
        assert (metadata >= 0);
        assert (metadata < 16);
        assert (overlayName != null);
        MinecraftName properName = new MinecraftName(blockName);
        if (!properName.legit() && !(properName = OreUBifyRequester.minecraftName(oreBlock, metadata)).legit()) {
            new MinecraftName(blockName);
            logger.info(blockName + " not found in the language tables");
        }
        logger.info("request OK");
        this.waitingRequests.add(new UBifyRequestWithMetadata(oreBlock, metadata, overlayName, properName));
    }

    public void setupUBOre(Block oreBlock, String overlayName, FMLPreInitializationEvent event) {
        UndergroundBiomes.instance().oreUBifier().setupUBOre(oreBlock, overlayName, event);
    }

    public void requestUBOreSetup(Block oreBlock, String overlayName) throws UBOreTexturizer.BlocksAreAlreadySet {
        if (this.alreadyRun) {
            UBOreTexturizer.BlocksAreAlreadySet error = new UBOreTexturizer.BlocksAreAlreadySet(oreBlock, overlayName);
            if (UndergroundBiomes.crashOnProblems()) {
                throw error;
            }
            UndergroundBiomes.logger.severe(error.toString());
        } else {
            this.waitingRequests.add(new UBifyRequest(oreBlock, overlayName));
        }
    }

    void fulfillRequests(FMLPreInitializationEvent event) {
        this.alreadyRun = true;
        for (UBifyRequest request : this.waitingRequests) {
            request.fulfill(event);
        }
        this.waitingRequests.clear();
    }

    public void redoOres(int x, int z, World world) {
        UndergroundBiomes.instance().redoOres(x, z, world);
    }

    private static MinecraftName minecraftName(Block block, int meta) {
        if (block instanceof MetalBlock) {
            logger.info(((MetalBlock)block).getUnlocalizedName(meta) + " " + meta);
            logger.info(((MetalBlock)block).getUnlocalizedName(0) + " " + 0);
            logger.info(((MetalBlock)block).getUnlocalizedName(1) + " " + 1);
            logger.info(((MetalBlock)block).getUnlocalizedName(2) + " " + 2);
            return new MinecraftName(((MetalBlock)block).getUnlocalizedName(meta));
        }
        return new MinecraftName(block.func_149739_a());
    }

    private class UBifyRequestWithMetadata
    extends UBifyRequest {
        final int metadata;
        final MinecraftName name;

        UBifyRequestWithMetadata(Block ore, int metadata, String overlayName, MinecraftName name) {
            super(ore, overlayName);
            this.metadata = metadata;
            this.name = name;
        }

        UBifyRequestWithMetadata(Block ore, int metadata, String overlayName) {
            this(ore, metadata, overlayName, OreUBifyRequester.minecraftName(ore, metadata));
        }

        void fulfill(FMLPreInitializationEvent event) {
            OreUBifyRequester.this.setupUBOre(this.ore, this.metadata, this.overlayName, this.name, event);
        }
    }

    private class UBifyRequest {
        final Block ore;
        final String overlayName;

        UBifyRequest(Block ore, String overlayName) {
            this.ore = ore;
            this.overlayName = overlayName;
        }

        void fulfill(FMLPreInitializationEvent event) {
            OreUBifyRequester.this.setupUBOre(this.ore, this.overlayName, event);
        }
    }

}

