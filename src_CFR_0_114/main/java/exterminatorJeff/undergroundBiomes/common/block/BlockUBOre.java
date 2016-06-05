/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.particle.EffectRenderer
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package exterminatorJeff.undergroundBiomes.common.block;

import Zeno410Utils.MinecraftName;
import Zeno410Utils.Mutable;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import exterminatorJeff.undergroundBiomes.common.CreativeTabModBlocks;
import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;
import exterminatorJeff.undergroundBiomes.common.block.BlockMetadataBase;
import exterminatorJeff.undergroundBiomes.common.block.BlockOverlay;
import exterminatorJeff.undergroundBiomes.common.block.BlockUBReplaceable;
import exterminatorJeff.undergroundBiomes.constructs.util.ShamWorld;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class BlockUBOre
extends Block
implements BlockUBReplaceable {
    protected final BlockMetadataBase stone;
    protected final Block ore;
    private final BlockOverlay overlay;
    private Mutable<Integer> renderIDSource;
    protected ShamWorld shamWorld;
    private final MinecraftName oreName;

    public BlockUBOre(BlockMetadataBase stone, Block ore, BlockOverlay overlay, Mutable<Integer> renderIDSource) {
        this(stone, ore, overlay, renderIDSource, new MinecraftName(ore.func_149739_a()));
    }

    public BlockUBOre(BlockMetadataBase stone, Block ore, BlockOverlay overlay, Mutable<Integer> renderIDSource, MinecraftName oreName) {
        super(Material.field_151576_e);
        this.stone = stone;
        this.ore = ore;
        if (ore instanceof BlockUBOre) {
            throw new RuntimeException();
        }
        this.renderIDSource = renderIDSource;
        if (renderIDSource == null && UndergroundBiomes.crashOnProblems()) {
            throw new RuntimeException();
        }
        this.overlay = overlay;
        this.func_149647_a((CreativeTabs)UndergroundBiomes.tabModBlocks);
        this.oreName = oreName;
    }

    @Override
    public Block block() {
        return this;
    }

    public int func_149645_b() {
        if (this.renderIDSource == null) {
            return 0;
        }
        Integer result = this.renderIDSource.value();
        if (result == null) {
            return 0;
        }
        return result;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister iconRegister) {
        this.overlay.func_149651_a(iconRegister);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int side, int metadata) {
        return this.stone.func_149691_a(side, metadata);
    }

    @SideOnly(value=Side.CLIENT)
    protected String func_149641_N() {
        return this.stone.func_149641_N();
    }

    public void func_149666_a(Item id, CreativeTabs tabs, List list) {
        if (!UndergroundBiomes.ubOres()) {
            return;
        }
        for (int i = 0; i < 8; ++i) {
            list.add(new ItemStack(id, 1, i));
        }
    }

    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        return this.ore.getDrops(world, x, y, z, metadata, fortune);
    }

    public String func_149702_O() {
        return this.stone.func_149702_O() + " " + this.oreName.localized();
    }

    public String getDisplayName(int meta) {
        return StatCollector.func_74838_a((String)new StringBuilder().append(this.stone.func_149739_a()).append(".").append(this.stone.getBlockName(meta)).append(".name").toString()) + " " + this.oreName.localized();
    }

    public String getUnlocalizedName(int meta) {
        return this.stone.getBlockName(meta) + "." + this.oreName.unlocalized();
    }

    public void func_149657_c(World p_149657_1_, int p_149657_2_, int p_149657_3_, int p_149657_4_, int p_149657_5_) {
        this.ore.func_149657_c(p_149657_1_, p_149657_2_, p_149657_3_, p_149657_4_, p_149657_5_);
    }

    public void func_149690_a(World p_149690_1_, int p_149690_2_, int p_149690_3_, int p_149690_4_, int p_149690_5_, float p_149690_6_, int p_149690_7_) {
        this.ore.func_149690_a(p_149690_1_, p_149690_2_, p_149690_3_, p_149690_4_, p_149690_5_, p_149690_6_, p_149690_7_);
    }

    public float func_149712_f(World p_149712_1_, int p_149712_2_, int p_149712_3_, int p_149712_4_) {
        return this.ore.func_149712_f(p_149712_1_, p_149712_2_, p_149712_3_, p_149712_4_);
    }

    public int getExpDrop(IBlockAccess world, int metadata, int fortune) {
        return this.ore.getExpDrop(world, metadata, fortune);
    }

    public float func_149638_a(Entity p_149638_1_) {
        return this.ore.func_149638_a(p_149638_1_);
    }

    public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ) {
        return this.ore.getExplosionResistance(par1Entity, world, x, y, z, explosionX, explosionY, explosionZ);
    }

    public int getLightOpacity(IBlockAccess world, int x, int y, int z) {
        return this.ore.func_149717_k();
    }

    public int func_149750_m() {
        return this.ore.func_149750_m();
    }

    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        return this.ore.func_149750_m();
    }

    public void func_149636_a(World p_149636_1_, EntityPlayer p_149636_2_, int p_149636_3_, int p_149636_4_, int p_149636_5_, int p_149636_6_) {
        this.ore.func_149636_a(p_149636_1_, p_149636_2_, p_149636_3_, p_149636_4_, p_149636_5_, p_149636_6_);
    }

    public boolean canHarvestBlock(EntityPlayer player, int meta) {
        return this.ore.canHarvestBlock(player, 0);
    }

    public void func_149674_a(World p_149674_1_, int p_149674_2_, int p_149674_3_, int p_149674_4_, Random p_149674_5_) {
        this.ore.func_149674_a(p_149674_1_, p_149674_2_, p_149674_3_, p_149674_4_, p_149674_5_);
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        Item item = this.func_149694_d(world, x, y, z);
        if (item == null) {
            return null;
        }
        return new ItemStack(item, 1, this.func_149643_k(world, x, y, z));
    }

    public int func_149692_a(int metadata) {
        return metadata;
    }

    public boolean canEntityDestroy(IBlockAccess world, int x, int y, int z, Entity entity) {
        return this.ore.canEntityDestroy(world, x, y, z, entity);
    }

    public boolean addDestroyEffects(World world, int x, int y, int z, int meta, EffectRenderer effectRenderer) {
        return super.addDestroyEffects(world, x, y, z, meta, effectRenderer);
    }

    public int getHarvestLevel(int metadata) {
        return this.ore.getHarvestLevel(0);
    }

    public String getHarvestTool(int metadata) {
        return this.ore.getHarvestTool(0);
    }

    public float func_149737_a(EntityPlayer player, World world, int x, int y, int z) {
        int metadata = world.func_72805_g(x, y, z);
        float hardness = this.func_149712_f(world, x, y, z);
        if (hardness < 0.0f) {
            return 0.0f;
        }
        if (!this.canHarvestBlock(player, metadata)) {
            return player.getBreakSpeed((Block)this, true, metadata, x, y, z) / hardness / 100.0f;
        }
        return player.getBreakSpeed((Block)this, false, metadata, x, y, z) / hardness / 30.0f;
    }

    public void func_149699_a(World p_149699_1_, int p_149699_2_, int p_149699_3_, int p_149699_4_, EntityPlayer p_149699_5_) {
        this.ore.func_149699_a(p_149699_1_, p_149699_2_, p_149699_3_, p_149699_4_, p_149699_5_);
    }
}

