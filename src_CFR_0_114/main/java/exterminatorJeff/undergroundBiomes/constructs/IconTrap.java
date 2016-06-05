/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.IIcon
 */
package exterminatorJeff.undergroundBiomes.constructs;

import exterminatorJeff.undergroundBiomes.api.NamedBlock;
import exterminatorJeff.undergroundBiomes.api.UBIDs;
import exterminatorJeff.undergroundBiomes.common.block.BlockSedimentaryStone;
import exterminatorJeff.undergroundBiomes.constructs.item.ItemUndergroundBiomesConstruct;
import net.minecraft.util.IIcon;

class IconTrap
extends BlockSedimentaryStone {
    IconTrap() {
        super(UBIDs.IconTrap);
    }

    public IIcon func_149691_a(int side, int metadata) {
        return ItemUndergroundBiomesConstruct.currentColor;
    }
}

