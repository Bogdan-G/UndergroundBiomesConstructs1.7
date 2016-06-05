/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package exterminatorJeff.undergroundBiomes.common.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import exterminatorJeff.undergroundBiomes.api.NamedBlock;
import exterminatorJeff.undergroundBiomes.api.NamedItem;
import exterminatorJeff.undergroundBiomes.common.block.BlockUBHidden;
import exterminatorJeff.undergroundBiomes.common.item.ItemMetadataBlock;
import java.util.HashMap;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemUBHiddenBlock
extends ItemBlock {
    private static HashMap<String, ItemMetadataBlock> namedBlocks = new HashMap();
    private BlockUBHidden theBlock;

    public static ItemMetadataBlock itemFrom(NamedBlock namer) {
        return namedBlocks.get(new NamedItem(namer).internal());
    }

    public ItemUBHiddenBlock(Block block) {
        BlockUBHidden baseBlock;
        super(block);
        this.theBlock = baseBlock = (BlockUBHidden)block;
        this.func_77656_e(0);
        this.func_77627_a(true);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int meta) {
        return this.theBlock.func_149691_a(2, meta);
    }

    public String func_77653_i(ItemStack stack) {
        return this.theBlock.getDisplayName(stack.func_77960_j());
    }

    public String func_77667_c(ItemStack stack) {
        return this.theBlock.getUnlocalizedName(stack.func_77960_j());
    }

    public boolean func_77662_d() {
        return true;
    }

    public int func_94901_k() {
        return 0;
    }

    public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
        metadata = stack.func_77960_j();
        if (!world.func_147465_d(x, y, z, (Block)this.theBlock, metadata, 3)) {
            return false;
        }
        if (world.func_147439_a(x, y, z) == this.theBlock) {
            this.theBlock.func_149689_a(world, x, y, z, (EntityLivingBase)player, stack);
            this.theBlock.func_149714_e(world, x, y, z, metadata);
        }
        return true;
    }
}

