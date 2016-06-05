/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.StatCollector
 */
package exterminatorJeff.undergroundBiomes.common.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import exterminatorJeff.undergroundBiomes.api.NamedBlock;
import exterminatorJeff.undergroundBiomes.api.NamedItem;
import exterminatorJeff.undergroundBiomes.common.block.BlockMetadataBase;
import java.util.HashMap;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;

public class ItemMetadataBlock
extends ItemBlock {
    private static HashMap<String, ItemMetadataBlock> namedBlocks = new HashMap();
    private BlockMetadataBase theBlock;

    public static ItemMetadataBlock itemFrom(NamedBlock namer) {
        return namedBlocks.get(new NamedItem(namer).internal());
    }

    public ItemMetadataBlock(Block block) {
        BlockMetadataBase baseBlock;
        super(block);
        this.theBlock = baseBlock = (BlockMetadataBase)block;
        this.func_77655_b(new NamedItem(baseBlock.namer).internal());
        this.func_77656_e(0);
        this.func_77627_a(true);
        namedBlocks.put(new NamedItem(baseBlock.namer).internal(), this);
    }

    public int func_77647_b(int meta) {
        return this.theBlock.hasRareDrops() ? meta | 8 : meta;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int meta) {
        return this.theBlock.func_149691_a(2, meta);
    }

    public String func_77653_i(ItemStack par1ItemStack) {
        String result;
        String lookup = result = super.func_77653_i(par1ItemStack);
        return ("" + StatCollector.func_74838_a((String)lookup)).trim();
    }

    public String func_77667_c(ItemStack stack) {
        String name = this.theBlock.getBlockTypeName(stack.func_77960_j());
        return super.func_77658_a() + "." + name;
    }
}

