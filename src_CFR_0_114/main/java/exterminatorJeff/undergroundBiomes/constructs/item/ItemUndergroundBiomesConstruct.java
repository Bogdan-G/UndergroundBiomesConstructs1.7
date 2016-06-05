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
 *  net.minecraft.item.ItemMultiTexture
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 */
package exterminatorJeff.undergroundBiomes.constructs.item;

import Zeno410Utils.Zeno410Logger;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import exterminatorJeff.undergroundBiomes.api.NamedItem;
import exterminatorJeff.undergroundBiomes.common.block.UBStoneTextureProvider;
import exterminatorJeff.undergroundBiomes.constructs.util.Reregistrable;
import exterminatorJeff.undergroundBiomes.constructs.util.UndergroundBiomesBlock;
import exterminatorJeff.undergroundBiomes.constructs.util.UndergroundBiomesBlockList;
import java.util.logging.Logger;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemMultiTexture;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public abstract class ItemUndergroundBiomesConstruct
extends ItemMultiTexture {
    public static int NO_PRIOR_METADATA = 0;
    public static final int subBlocksPerBlock = 8;
    public static Logger logger = new Zeno410Logger("ConstructItems").logger();
    public static final UBStoneTextureProvider appearance = new UBStoneTextureProvider();
    private Block structure;
    private NamedItem name;
    public static IIcon currentColor;

    private static String[] names(UBStoneTextureProvider appearance, NamedItem name) {
        String[] result = new String[8];
        for (int i = 0; i < 8; ++i) {
            result[i] = appearance.getBlockTypeName(i) + "." + name.internal();
        }
        return result;
    }

    public ItemUndergroundBiomesConstruct(Block _structure, NamedItem _name) {
        super(_structure, (Block)appearance, ItemUndergroundBiomesConstruct.names(appearance, _name));
        this.structure = _structure;
        this.name = _name;
        this.func_77655_b(_name.internal());
        this.func_77656_e(0);
        this.func_77627_a(true);
    }

    public final UndergroundBiomesBlock ubBlock(int reference) {
        return UndergroundBiomesBlockList.indexed(reference);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int par1) {
        IIcon field_150938_b = this.ubBlock(par1).icon();
        return field_150938_b != null ? field_150938_b : this.field_150939_a.func_149733_h(1);
    }

    public int func_82790_a(ItemStack par1ItemStack, int par2) {
        currentColor = this.func_77617_a(par1ItemStack.func_77960_j());
        return super.func_82790_a(par1ItemStack, par2);
    }

    public IIcon getIcon(ItemStack stack, int pass) {
        return currentColor;
    }

    public abstract String groupName();

    public String func_77653_i(ItemStack par1ItemStack) {
        String result;
        String lookup = result = super.func_77653_i(par1ItemStack);
        return ("" + StatCollector.func_74838_a((String)lookup)).trim();
    }

    public String func_77667_c(ItemStack stack) {
        String unlocalizedname = this.ubBlock(stack.func_77960_j()).getUnlocalizedName();
        return unlocalizedname + "." + this.groupName();
    }

    public Block structure() {
        return this.structure;
    }

    public void testRegistration() {
        logger.info(this.structure().toString());
        logger.info(Block.func_149729_e((int)Block.func_149682_b((Block)this.structure())).toString());
        logger.info("" + Item.func_150891_b((Item)this));
        logger.info("" + Block.func_149682_b((Block)this.structure()));
        logger.info("" + Block.func_149682_b((Block)this.structure()));
        logger.info(Item.func_150899_d((int)Block.func_149682_b((Block)this.structure())).toString());
    }

    public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
        int blockID = Block.func_149682_b((Block)this.structure);
        if (blockID == -1) {
            ((Reregistrable)this.structure).reRegister();
        }
        int newMetadata = 0;
        if (!world.func_147465_d(x, y, z, this.structure, newMetadata = this.structure.func_149660_a(world, x, y, z, side, hitX, hitY, hitZ, newMetadata), 3)) {
            return false;
        }
        if (world.func_147439_a(x, y, z) == this.structure) {
            this.structure.func_149689_a(world, x, y, z, (EntityLivingBase)player, stack);
            this.structure.func_149714_e(world, x, y, z, newMetadata);
        }
        return true;
    }
}

