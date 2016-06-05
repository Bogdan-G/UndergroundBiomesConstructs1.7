/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 */
package exterminatorJeff.undergroundBiomes.common.block;

import exterminatorJeff.undergroundBiomes.api.NamedBlock;
import exterminatorJeff.undergroundBiomes.api.NamedItem;
import exterminatorJeff.undergroundBiomes.api.NamedVanillaItem;
import exterminatorJeff.undergroundBiomes.api.UBIDs;
import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;
import exterminatorJeff.undergroundBiomes.common.block.BlockMetadataBase;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BlockSedimentaryStone
extends BlockMetadataBase {
    private static final float[] hardness = new float[]{0.5f, 0.5f, 0.5f, 0.6f, 0.5f, 0.5f, 1.0f, 0.9f};
    private static final float[] resistance = new float[]{0.29f, 0.29f, 0.29f, 0.4f, 0.29f, 0.29f, 1.0f, 0.86f};
    public static final String[] blockName = new String[]{"limestone", "chalk", "shale", "siltstone", "ligniteBlock", "dolomite", "greywacke", "chert"};

    public BlockSedimentaryStone() {
        this(UBIDs.sedimentaryStoneName);
    }

    public BlockSedimentaryStone(NamedBlock namer) {
        super(namer);
        this.func_149711_c(1.5f * UndergroundBiomes.hardnessModifier()).func_149752_b(1.66f * UndergroundBiomes.resistanceModifier());
        this.ubExplosionResistance = this.field_149781_w;
    }

    public float getBlockHardness(int meta) {
        return super.getBlockHardness(meta) * hardness[meta];
    }

    public float getBlockExplosionResistance(int meta) {
        float result = super.getBlockExplosionResistance(meta) * resistance[meta];
        return result;
    }

    public ItemStack itemDropped(int metadata, Random random, int fortune, int y) {
        if (metadata < 8 && random.nextInt(64) <= fortune) {
            if (metadata == 2) {
                return new ItemStack(NamedVanillaItem.clay.cachedItem(), 1, 0);
            }
            if (metadata == 0 || metadata == 1 || metadata == 3 || metadata == 4 || metadata == 5) {
                return new ItemStack(UBIDs.fossilPieceName.cachedItem(), 1, random.nextInt(8));
            }
            if (metadata == 7) {
                return new ItemStack(NamedVanillaItem.flint.cachedItem(), 1, 0);
            }
        }
        if ((metadata & 7) == 4) {
            return new ItemStack(UBIDs.ligniteCoalName.cachedItem(), 1, 0);
        }
        return new ItemStack(UBIDs.sedimentaryStoneName.block(), 1, metadata & 7);
    }

    public boolean hasRareDrops() {
        return true;
    }

    public String getBlockTypeName(int index) {
        return blockName[index & 7];
    }
}

