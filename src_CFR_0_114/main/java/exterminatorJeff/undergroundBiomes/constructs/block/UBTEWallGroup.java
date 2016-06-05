/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 */
package exterminatorJeff.undergroundBiomes.constructs.block;

import exterminatorJeff.undergroundBiomes.api.NamedBlock;
import exterminatorJeff.undergroundBiomes.api.UBIDs;
import exterminatorJeff.undergroundBiomes.common.block.BlockMetadataBase;
import exterminatorJeff.undergroundBiomes.constructs.block.UBWallBase;
import exterminatorJeff.undergroundBiomes.constructs.block.UBWallGroup;
import exterminatorJeff.undergroundBiomes.constructs.item.ItemUBWall;
import net.minecraft.block.Block;

public class UBTEWallGroup
extends UBWallGroup {
    private static UBWallBase constructBlock;

    Block definedBlock() {
        if (constructBlock == null) {
            constructBlock = new UBWallBase(this.baseBlock(), UBIDs.UBWallsName);
            UBIDs.UBWallsName.gameRegister((Block)constructBlock, ItemUBWall.class);
        }
        return constructBlock;
    }
}

