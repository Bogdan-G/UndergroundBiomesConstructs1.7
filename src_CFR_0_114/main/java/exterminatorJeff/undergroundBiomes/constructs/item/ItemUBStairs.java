/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package exterminatorJeff.undergroundBiomes.constructs.item;

import exterminatorJeff.undergroundBiomes.api.NamedItem;
import exterminatorJeff.undergroundBiomes.api.UBIDs;
import exterminatorJeff.undergroundBiomes.constructs.item.ItemUndergroundBiomesConstruct;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemUBStairs
extends ItemUndergroundBiomesConstruct {
    public static ArrayList<ItemUBStairs> instances = new ArrayList();

    public ItemUBStairs(Block block) {
        this(block, UBIDs.UBStairsItemName);
    }

    public ItemUBStairs(Block block, NamedItem namer) {
        super(block, namer);
        instances.add(this);
    }

    public String groupName() {
        return "stairs";
    }

    public void onBlockPlacedBy(World p_149689_1_, int p_149689_2_, int p_149689_3_, int p_149689_4_, EntityLivingBase p_149689_5_, ItemStack stairs) {
        int l = MathHelper.func_76128_c((double)((double)(p_149689_5_.field_70177_z * 4.0f / 360.0f) + 0.5)) & 3;
        int i1 = p_149689_1_.func_72805_g(p_149689_2_, p_149689_3_, p_149689_4_);
        int metadata = stairs.func_77960_j();
        if (l == 0) {
            p_149689_1_.func_72921_c(p_149689_2_, p_149689_3_, p_149689_4_, 2 | i1 | metadata, 2);
        }
        if (l == 1) {
            p_149689_1_.func_72921_c(p_149689_2_, p_149689_3_, p_149689_4_, 1 | i1 | metadata, 2);
        }
        if (l == 2) {
            p_149689_1_.func_72921_c(p_149689_2_, p_149689_3_, p_149689_4_, 3 | i1 | metadata, 2);
        }
        if (l == 3) {
            p_149689_1_.func_72921_c(p_149689_2_, p_149689_3_, p_149689_4_, 0 | i1 | metadata, 2);
        }
        throw new RuntimeException("" + metadata + " " + (0 | i1 | metadata));
    }
}

