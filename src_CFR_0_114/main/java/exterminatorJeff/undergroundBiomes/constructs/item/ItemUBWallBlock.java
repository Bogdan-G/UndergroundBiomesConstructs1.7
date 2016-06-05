/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 */
package exterminatorJeff.undergroundBiomes.constructs.item;

import exterminatorJeff.undergroundBiomes.api.NamedItem;
import exterminatorJeff.undergroundBiomes.api.UBIDs;
import exterminatorJeff.undergroundBiomes.common.block.BlockMetadataBase;
import exterminatorJeff.undergroundBiomes.constructs.block.UBWall;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemUBWallBlock
extends ItemBlock {
    private Block ubWall;
    private NamedItem name;
    protected String[] names;

    private static String[] names(Block appearance, NamedItem name) {
        String[] result = new String[8];
        for (int i = 0; i < 8; ++i) {
            BlockMetadataBase sourceBlock = ((UBWall)appearance).baseBlock();
            result[i] = sourceBlock.getBlockTypeName(i) + "." + name.internal();
        }
        return result;
    }

    public ItemUBWallBlock(Block ubBlock) {
        this(ubBlock, UBIDs.UBWallsItemName);
    }

    public ItemUBWallBlock(Block ubBlock, NamedItem name) {
        super(ubBlock);
        this.names = ItemUBWallBlock.names(ubBlock, name);
        this.ubWall = ubBlock;
        this.name = name;
        this.func_77655_b(name.internal());
        this.func_77656_e(0);
        this.func_77627_a(true);
    }

    public int func_77647_b(int damage) {
        return damage;
    }

    public String func_77667_c(ItemStack stack) {
        return ((UBWall)this.ubWall).baseBlock().func_149739_a() + "." + this.names[stack.func_77960_j()];
    }
}

