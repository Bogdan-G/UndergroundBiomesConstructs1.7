/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemMultiTexture
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 */
package exterminatorJeff.undergroundBiomes.constructs.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import exterminatorJeff.undergroundBiomes.api.NamedItem;
import exterminatorJeff.undergroundBiomes.api.UBIDs;
import exterminatorJeff.undergroundBiomes.common.block.BlockMetadataBase;
import exterminatorJeff.undergroundBiomes.constructs.block.UBStairs;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemMultiTexture;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ItemUBStairsBlock
extends ItemMultiTexture {
    private UBStairs ubStairs;
    private NamedItem name;
    protected String[] names;
    public static int top = 2;
    public static IIcon currentColor;

    private static String[] names(Block appearance, NamedItem name, int metadata) {
        String[] result = new String[2];
        for (int i = metadata; i < metadata + 2; ++i) {
            BlockMetadataBase sourceBlock = ((UBStairs)appearance).baseStone();
            result[i - metadata] = sourceBlock.getBlockTypeName(i) + "." + name.internal();
        }
        return result;
    }

    public ItemUBStairsBlock(Block ubBlock) {
        this(ubBlock, UBIDs.UBStairsItemName);
    }

    public int getMetatata(int metadata) {
        if (metadata == 0) {
            throw new RuntimeException();
        }
        return metadata;
    }

    public ItemUBStairsBlock(Block ubBlock, NamedItem name) {
        super(ubBlock, null, null);
        this.ubStairs = (UBStairs)ubBlock;
        this.names = ItemUBStairsBlock.names(ubBlock, name, this.ubStairs.lowerMetadata());
        this.name = name;
        this.func_77655_b(name.internal());
        this.func_77656_e(0);
        this.func_77627_a(true);
    }

    public int func_82790_a(ItemStack itemStack, int par2) {
        currentColor = this.func_77617_a(itemStack.func_77960_j());
        return super.func_82790_a(itemStack, par2);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(ItemStack stack, int pass) {
        return currentColor;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77618_c(int par1, int par2) {
        return currentColor;
    }

    public int getDamage(ItemStack stack) {
        return super.getDamage(stack);
    }

    public int getDisplayDamage(ItemStack stack) {
        return super.getDisplayDamage(stack);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77650_f(ItemStack itemStack) {
        return this.func_77617_a(itemStack.func_77960_j());
    }

    public boolean func_77623_v() {
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int par1) {
        IIcon result;
        currentColor = result = this.ubStairs.baseStone().func_149691_a(top, this.ubStairs.blockMetadata(par1));
        this.ubStairs.currentIcon = result;
        this.ubStairs.renderingItem = true;
        return result;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining) {
        return this.func_77650_f(usingItem);
    }

    int nameReference(int metadata) {
        return metadata >> 3;
    }

    public String func_77667_c(ItemStack stack) {
        return this.ubStairs.baseStone().func_149739_a() + "." + this.names[this.nameReference(stack.func_77960_j())];
    }

    public int func_94901_k() {
        return 0;
    }
}

