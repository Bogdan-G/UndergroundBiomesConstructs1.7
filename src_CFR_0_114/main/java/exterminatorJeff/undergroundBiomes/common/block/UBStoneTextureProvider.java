/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.util.IIcon
 */
package exterminatorJeff.undergroundBiomes.common.block;

import exterminatorJeff.undergroundBiomes.constructs.util.UndergroundBiomesBlock;
import exterminatorJeff.undergroundBiomes.constructs.util.UndergroundBiomesBlockList;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class UBStoneTextureProvider
extends Block {
    public UBStoneTextureProvider() {
        super(Material.field_151576_e);
    }

    public void func_149651_a(IIconRegister p_149651_1_) {
    }

    public IIcon func_149691_a(int side, int meta) {
        UndergroundBiomesBlock block = UndergroundBiomesBlockList.indexed(meta);
        return block.icon();
    }

    public String getBlockTypeName(int meta) {
        UndergroundBiomesBlock block = UndergroundBiomesBlockList.indexed(meta);
        return block.name();
    }
}

