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
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BlockIgneousStone
extends BlockMetadataBase {
    private static final float[] hardness = new float[]{1.7f, 1.6f, 1.3f, 1.4f, 1.0f, 1.4f, 1.5f, 1.2f};
    private static final float[] resistance = new float[]{1.42f, 1.39f, 1.26f, 1.31f, 1.0f, 1.31f, 1.35f, 1.2f};
    public static final String[] blockName = new String[]{"redGranite", "blackGranite", "rhyolite", "andesite", "gabbro", "basalt", "komatiite", "dacite"};

    public BlockIgneousStone() {
        this(UBIDs.igneousStoneName);
    }

    public BlockIgneousStone(NamedBlock namer) {
        super(namer);
        this.func_149711_c(1.5f * UndergroundBiomes.hardnessModifier()).func_149752_b(1.66f * UndergroundBiomes.resistanceModifier());
        this.ubExplosionResistance = this.field_149781_w;
    }

    public float getBlockHardness(int meta) {
        return super.getBlockHardness(meta) * hardness[meta];
    }

    public float getBlockExplosionResistance(int meta) {
        return super.getBlockExplosionResistance(meta) * resistance[meta];
    }

    public ItemStack itemDropped(int metadata, Random random, int fortune, int y) {
        ItemStack stack;
        int num;
        if (metadata < 8 && random.nextInt(1024) <= fortune && (num = UndergroundBiomes.nuggets.size()) > 0 && (NamedVanillaItem.goldNugget.matches((stack = UndergroundBiomes.nuggets.get(random.nextInt(num))).func_77973_b()) || y < 32)) {
            return stack;
        }
        return new ItemStack(UBIDs.igneousCobblestoneName.block(), 1, metadata & 7);
    }

    public Item func_149650_a(int metadata, Random random, int fortune) {
        return Item.func_150899_d((int)UBIDs.igneousCobblestoneName.ID());
    }

    public boolean hasRareDrops() {
        return true;
    }

    public String getBlockTypeName(int index) {
        return blockName[index & 7];
    }
}

