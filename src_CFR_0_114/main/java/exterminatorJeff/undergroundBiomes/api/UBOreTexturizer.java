/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.event.FMLPreInitializationEvent
 *  net.minecraft.block.Block
 *  net.minecraft.world.World
 */
package exterminatorJeff.undergroundBiomes.api;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.block.Block;
import net.minecraft.world.World;

public interface UBOreTexturizer {
    public static final String amber_overlay = "undergroundbiomes:amber_overlay";
    public static final String cinnabar_overlay = "undergroundbiomes:cinnabar_overlay";
    public static final String coal_overlay = "undergroundbiomes:coal_overlay";
    public static final String copper_overlay = "undergroundbiomes:copper_overlay";
    public static final String diamond_overlay = "undergroundbiomes:diamond_overlay";
    public static final String emerald_overlay = "undergroundbiomes:emerald_overlay";
    public static final String gold_overlay = "undergroundbiomes:gold_overlay";
    public static final String iron_overlay = "undergroundbiomes:iron_overlay";
    public static final String lapis_overlay = "undergroundbiomes:lapis_overlay";
    public static final String lead_overlay = "undergroundbiomes:lead_overlay";
    public static final String olivine_peridot_overlay = "undergroundbiomes:olivine-peridot_overlay";
    public static final String redstone_overlay = "undergroundbiomes:redstone_overlay";
    public static final String ruby_overlay = "undergroundbiomes:ruby_overlay";
    public static final String sapphire_overlay = "undergroundbiomes:sapphire_overlay";
    public static final String silver_overlay = "undergroundbiomes:silver_overlay";
    public static final String tin_overlay = "undergroundbiomes:tin_overlay";
    public static final String uranium_overlay = "undergroundbiomes:uranium_overlay";

    public void setupUBOre(Block var1, String var2, FMLPreInitializationEvent var3);

    @Deprecated
    public void setupUBOre(Block var1, int var2, String var3, FMLPreInitializationEvent var4);

    public void setupUBOre(Block var1, int var2, String var3, String var4, FMLPreInitializationEvent var5);

    public void requestUBOreSetup(Block var1, String var2) throws BlocksAreAlreadySet;

    @Deprecated
    public void requestUBOreSetup(Block var1, int var2, String var3) throws BlocksAreAlreadySet;

    public void requestUBOreSetup(Block var1, int var2, String var3, String var4) throws BlocksAreAlreadySet;

    public void redoOres(int var1, int var2, World var3);

    public static class BlocksAreAlreadySet
    extends RuntimeException {
        public final Block oreBlock;
        public final String overlayName;

        public BlocksAreAlreadySet(Block oreBlock, String overlayName) {
            this.oreBlock = oreBlock;
            this.overlayName = overlayName;
        }

        public String toString() {
            String blockDescription = "undefined block";
            String overlayDescription = "undefined overlay";
            if (this.oreBlock != null) {
                blockDescription = this.oreBlock.func_149739_a();
            }
            if (this.overlayName != null) {
                overlayDescription = this.overlayName;
            }
            return "Attempt to create Underground Biomes ore for " + blockDescription + " with " + overlayDescription + " after blocks have already been defined";
        }
    }

}

