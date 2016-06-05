/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 */
package exterminatorJeff.undergroundBiomes.constructs.item;

import exterminatorJeff.undergroundBiomes.api.NamedItem;
import exterminatorJeff.undergroundBiomes.api.UBIDs;
import exterminatorJeff.undergroundBiomes.constructs.item.ItemUndergroundBiomesConstruct;
import net.minecraft.block.Block;

public class ItemUBWall
extends ItemUndergroundBiomesConstruct {
    public ItemUBWall(Block block) {
        this(block, UBIDs.UBWallsItemName);
    }

    public ItemUBWall(Block block, NamedItem namer) {
        super(block, namer);
    }

    public String groupName() {
        return "wall";
    }
}

