/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.util.RegistryNamespaced
 */
package exterminatorJeff.undergroundBiomes.intermod;

import Zeno410Utils.Zeno410Logger;
import exterminatorJeff.undergroundBiomes.api.UBAPIHook;
import exterminatorJeff.undergroundBiomes.api.UBOreTexturizer;
import java.util.Set;
import java.util.logging.Logger;
import net.minecraft.block.Block;
import net.minecraft.util.RegistryNamespaced;

public class ModOreManager {
    public static Logger logger = new Zeno410Logger("ModOreManager").logger();

    public void register() {
        Block bopOreBlock;
        Block mekanismBlock;
        Block enviromineBlock;
        Block thaumcraftOreBlock;
        Block projectRedBlock;
        UBOreTexturizer texturizer = UBAPIHook.ubAPIHook.ubOreTexturizer;
        Block thermalFoundationOreBlock = Block.func_149684_b((String)"ThermalFoundation:Ore");
        if (thermalFoundationOreBlock != null) {
            logger.info("Thermal Foundation found");
            texturizer.requestUBOreSetup(thermalFoundationOreBlock, 0, "undergroundbiomes:copper_overlay", "tile.thermalfoundation.ore.copper");
            texturizer.requestUBOreSetup(thermalFoundationOreBlock, 1, "undergroundbiomes:tin_overlay", "tile.thermalfoundation.ore.tin");
            texturizer.requestUBOreSetup(thermalFoundationOreBlock, 2, "undergroundbiomes:silver_overlay", "tile.thermalfoundation.ore.silver");
            texturizer.requestUBOreSetup(thermalFoundationOreBlock, 3, "undergroundbiomes:lead_overlay", "tile.thermalfoundation.ore.lead");
        }
        if ((thaumcraftOreBlock = Block.func_149684_b((String)"Thaumcraft:blockCustomOre")) != null) {
            texturizer.requestUBOreSetup(thaumcraftOreBlock, 0, "undergroundbiomes:cinnabar_overlay", "tile.blockCustomOre.0");
            texturizer.requestUBOreSetup(thaumcraftOreBlock, 7, "undergroundbiomes:amber_overlay", "tile.blockCustomOre.7");
        }
        if ((mekanismBlock = Block.func_149684_b((String)"Mekanism:OreBlock")) != null) {
            texturizer.requestUBOreSetup(mekanismBlock, 1, "undergroundbiomes:copper_overlay", "tile.OreBlock.CopperOre");
            texturizer.requestUBOreSetup(mekanismBlock, 2, "undergroundbiomes:tin_overlay", "tile.OreBlock.TinOre");
        }
        if ((projectRedBlock = Block.func_149684_b((String)"ProjRed|Exploration:projectred.exploration.ore")) == null) {
            for (Object key : Block.field_149771_c.func_148742_b()) {
                String name = (String)key;
                Block named = Block.func_149684_b((String)name);
                int id = Block.func_149682_b((Block)named);
                logger.info(name + " " + id);
                if (!name.contains("projectred.exploration.ore")) continue;
                projectRedBlock = named;
                break;
            }
        }
        if (projectRedBlock != null) {
            texturizer.requestUBOreSetup(projectRedBlock, 0, "undergroundbiomes:ruby_overlay", "tile.projectred.exploration.ore|0");
            texturizer.requestUBOreSetup(projectRedBlock, 1, "undergroundbiomes:sapphire_overlay", "tile.projectred.exploration.ore|1");
            texturizer.requestUBOreSetup(projectRedBlock, 2, "undergroundbiomes:olivine-peridot_overlay", "tile.projectred.exploration.ore|2");
        }
        if ((bopOreBlock = Block.func_149684_b((String)"BiomesOPlenty:gemOre")) != null) {
            texturizer.requestUBOreSetup(bopOreBlock, 2, "undergroundbiomes:ruby_overlay", "tile.gemOre.rubyore");
            texturizer.requestUBOreSetup(bopOreBlock, 4, "undergroundbiomes:olivine-peridot_overlay", "tile.gemOre.peridotore");
            texturizer.requestUBOreSetup(bopOreBlock, 6, "undergroundbiomes:olivine-peridot_overlay", "tile.gemOre.topazore");
            texturizer.requestUBOreSetup(bopOreBlock, 8, "undergroundbiomes:olivine-peridot_overlay", "tile.gemOre.tanzaniteore");
            texturizer.requestUBOreSetup(bopOreBlock, 10, "undergroundbiomes:emerald_overlay", "tile.gemOre.malachiteore");
            texturizer.requestUBOreSetup(bopOreBlock, 12, "undergroundbiomes:sapphire_overlay", "tile.gemOre.sapphireore");
            texturizer.requestUBOreSetup(bopOreBlock, 14, "undergroundbiomes:amber_overlay", "tile.gemOre.amberore");
        }
        if ((enviromineBlock = Block.func_149684_b((String)"enviromine:flammablecoal")) != null) {
            texturizer.requestUBOreSetup(enviromineBlock, "undergroundbiomes:coal_overlay");
        }
    }
}

