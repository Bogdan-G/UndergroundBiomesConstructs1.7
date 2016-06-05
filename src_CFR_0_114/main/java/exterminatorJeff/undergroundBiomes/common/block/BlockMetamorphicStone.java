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

public class BlockMetamorphicStone
extends BlockMetadataBase {
    private static final float[] hardness = new float[]{1.1f, 1.0f, 1.1f, 1.3f, 0.7f, 0.7f, 0.4f, 0.9f};
    private static final float[] resistance = new float[]{1.11f, 1.0f, 1.11f, 1.26f, 0.54f, 0.54f, 0.2f, 0.86f};
    public static final String[] blockName = new String[]{"gneiss", "eclogite", "marble", "quartzite", "blueschist", "greenschist", "soapstone", "migmatite"};

    public BlockMetamorphicStone() {
        this(UBIDs.metamorphicStoneName);
    }

    public BlockMetamorphicStone(NamedBlock namer) {
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
        if (metadata < 8 && random.nextInt(1024) <= fortune) {
            if (y < 31 && random.nextInt(3) == 0) {
                return new ItemStack(NamedVanillaItem.dyePowder.cachedItem(), 1, 4);
            }
            if (y < 16 && random.nextInt(3) == 0) {
                return new ItemStack(NamedVanillaItem.redstone.cachedItem(), 1, 0);
            }
        }
        return new ItemStack(UBIDs.metamorphicCobblestoneName.block(), 1, metadata & 7);
    }

    public boolean hasRareDrops() {
        return true;
    }

    public String getBlockTypeName(int index) {
        return blockName[index & 7];
    }

    public String getBlockName(int index) {
        return this.getBlockTypeName(index);
    }

    public Item func_149650_a(int metadata, Random random, int fortune) {
        return Item.func_150899_d((int)UBIDs.metamorphicCobblestoneName.ID());
    }
}

