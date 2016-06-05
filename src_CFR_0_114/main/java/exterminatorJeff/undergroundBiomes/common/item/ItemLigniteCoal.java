/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.item.Item
 *  net.minecraft.util.IIcon
 */
package exterminatorJeff.undergroundBiomes.common.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import exterminatorJeff.undergroundBiomes.api.NamedItem;
import exterminatorJeff.undergroundBiomes.api.UBIDs;
import exterminatorJeff.undergroundBiomes.common.CreativeTabModBlocks;
import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

public class ItemLigniteCoal
extends Item {
    IIcon texture;

    public ItemLigniteCoal(int id) {
        this.func_77655_b(UBIDs.ligniteCoalName.internal());
        UBIDs.ligniteCoalName.register(id, this);
        this.func_77637_a((CreativeTabs)UndergroundBiomes.tabModItems);
    }

    public String getTextureFile() {
        return UndergroundBiomes.textures;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister iconRegister) {
        this.texture = UBIDs.ligniteCoalName.registerIcons(iconRegister);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int meta) {
        return this.texture;
    }
}

