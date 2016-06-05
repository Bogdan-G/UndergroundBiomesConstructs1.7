/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockButton
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 */
package exterminatorJeff.undergroundBiomes.constructs.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import exterminatorJeff.undergroundBiomes.common.CreativeTabModBlocks;
import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;
import exterminatorJeff.undergroundBiomes.common.block.BlockMetadataBase;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockButton;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class UBButton
extends BlockButton {
    final BlockMetadataBase baseStone;
    final int lowerMetadata;
    IIcon currentIcon;
    public boolean renderingItem;
    static final int materialFace = 2;

    UBButton(BlockMetadataBase material, int lowerMetadata) {
        super(true);
        this.baseStone = material;
        this.lowerMetadata = lowerMetadata;
        this.func_149647_a((CreativeTabs)UndergroundBiomes.tabModBlocks);
    }

    public final int blockMetadata(int worldMetadata) {
        return this.lowerMetadata;
    }

    public void func_149651_a(IIconRegister arg0) {
    }

    public int func_149741_i(int metadata) {
        this.currentIcon = this.baseStone.func_149691_a(2, this.lowerMetadata());
        return super.func_149741_i(metadata);
    }

    public final int itemMetadata(int itemDamage) {
        return itemDamage;
    }

    public BlockMetadataBase baseStone() {
        return this.baseStone;
    }

    public int lowerMetadata() {
        return this.lowerMetadata;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int side, int metadata) {
        this.currentIcon = this.baseStone.func_149691_a(2, this.lowerMetadata());
        return this.currentIcon;
    }

    public IIcon func_149673_e(IBlockAccess world, int x, int y, int z, int side) {
        return this.func_149691_a(side, world.func_72805_g(x, y, z));
    }

    public IIcon func_149735_b(int side, int metadata) {
        return this.baseStone.func_149691_a(side, this.blockMetadata(metadata));
    }

    public void func_149666_a(Item id, CreativeTabs tabs, List list) {
        list.add(new ItemStack(id, 1, 0));
    }
}

