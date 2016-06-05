/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockPistonBase
 *  net.minecraft.block.BlockSlab
 *  net.minecraft.init.Blocks
 */
package exterminatorJeff.undergroundBiomes.api;

import exterminatorJeff.undergroundBiomes.api.NamedBlock;
import exterminatorJeff.undergroundBiomes.api.UBIDs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.BlockSlab;
import net.minecraft.init.Blocks;

public class NamedVanillaBlock
extends NamedBlock {
    public static final NamedBlock cobblestone = new NamedVanillaBlock(Blocks.field_150347_e);
    public static final NamedBlock cobblestone_wall = new NamedVanillaBlock(Blocks.field_150463_bK);
    public static final NamedBlock dispenser = new NamedVanillaBlock(Blocks.field_150367_z);
    public static final NamedBlock furnace = new NamedVanillaBlock(Blocks.field_150460_al);
    public static final NamedBlock lever = new NamedVanillaBlock(Blocks.field_150442_at);
    public static final NamedBlock piston = new NamedVanillaBlock((Block)Blocks.field_150331_J);
    public static final NamedBlock planks = new NamedVanillaBlock(Blocks.field_150344_f);
    public static final NamedBlock stone_pressure_plate = new NamedVanillaBlock(Blocks.field_150456_au);
    public static final NamedBlock sand = new NamedVanillaBlock(Blocks.field_150354_m);
    public static final NamedBlock sandstone = new NamedVanillaBlock(Blocks.field_150322_A);
    public static final NamedBlock smoothSandstone = new NamedVanillaBlock(Blocks.field_150322_A);
    public static final NamedBlock stairsCobblestone = new NamedVanillaBlock(Blocks.field_150446_ar);
    public static final NamedBlock stairsStoneBrick = new NamedVanillaBlock(Blocks.field_150390_bg);
    public static final NamedBlock stone = new NamedVanillaBlock(Blocks.field_150348_b);
    public static final NamedBlock stoneBrick = new NamedVanillaBlock(Blocks.field_150417_aV);
    public static final NamedBlock stoneButton = new NamedVanillaBlock(Blocks.field_150430_aB);
    public static final NamedBlock stoneSingleSlab = new NamedVanillaBlock((Block)Blocks.field_150333_U);
    public static final NamedBlock torchRedstoneActive = new NamedVanillaBlock(Blocks.field_150374_bv);

    public NamedVanillaBlock(String name) {
        super(name);
        this.id = UBIDs.blockID(name);
        this.block = UBIDs.blockNamed(name);
    }

    public NamedVanillaBlock(Block _block) {
        super(_block.func_149739_a());
        this.id = Block.func_149682_b((Block)_block);
        this.block = _block;
    }

    public Block block() {
        if (this.block == null) {
            this.block = UBIDs.blockNamed(this.internal());
        }
        return this.block;
    }
}

