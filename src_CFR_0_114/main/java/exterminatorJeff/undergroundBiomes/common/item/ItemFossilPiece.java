/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 */
package exterminatorJeff.undergroundBiomes.common.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import exterminatorJeff.undergroundBiomes.api.NamedItem;
import exterminatorJeff.undergroundBiomes.api.UBIDs;
import exterminatorJeff.undergroundBiomes.common.CreativeTabModBlocks;
import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ItemFossilPiece
extends Item {
    private IIcon[] textures;
    private String[] names = new String[]{"ammonite", "shell", "rib", "bone", "skull", "bone", "shell", "boneshard"};
    public static final int TYPES = 8;

    public ItemFossilPiece(int id) {
        this.func_77656_e(0);
        this.func_77627_a(true);
        this.func_77655_b(UBIDs.fossilPieceName.internal());
        UBIDs.fossilPieceName.register(id, this);
        this.func_77637_a((CreativeTabs)UndergroundBiomes.tabModItems);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister iconRegister) {
        this.textures = new IIcon[8];
        for (int i = 0; i < 8; ++i) {
            this.textures[i] = iconRegister.func_94245_a("undergroundbiomes:fossilPiece_" + i);
        }
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int meta) {
        if (meta > 8) {
            meta = 0;
        }
        return this.textures[meta];
    }

    @SideOnly(value=Side.CLIENT)
    public void func_150895_a(Item id, CreativeTabs tabs, List list) {
        for (int i = 0; i < 8; ++i) {
            list.add(new ItemStack(UBIDs.fossilPieceName.registeredItem(), 1, i));
        }
    }

    public boolean isHasSubtypes() {
        return true;
    }

    public String func_77667_c(ItemStack stack) {
        int damage = stack.func_77960_j();
        if (damage > 8) {
            damage = 0;
        }
        return super.func_77658_a() + "." + this.names[damage];
    }
}

