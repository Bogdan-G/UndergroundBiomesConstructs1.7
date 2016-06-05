/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockStone
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package exterminatorJeff.undergroundBiomes.common.block;

import Zeno410Utils.Acceptor;
import Zeno410Utils.Mutable;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import exterminatorJeff.undergroundBiomes.api.NamedBlock;
import exterminatorJeff.undergroundBiomes.api.UndergroundBiomesSettings;
import exterminatorJeff.undergroundBiomes.common.CreativeTabModBlocks;
import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;
import exterminatorJeff.undergroundBiomes.worldGen.BiomeUndergroundDecorator;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStone;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class BlockMetadataBase
extends BlockStone {
    public static boolean test963 = false;
    protected IIcon[] textures = new IIcon[]{null, null, null, null, null, null, null, null};
    protected boolean replaceableByOre = true;
    public final NamedBlock namer;
    public static final int metadatas = 8;
    private int renderID;
    protected float ubExplosionResistance;
    private final Acceptor<Double> hardnessUpdater;
    private final Acceptor<Double> resistanceUpdater;

    public BlockMetadataBase(NamedBlock block) {
        this.hardnessUpdater = new Acceptor<Double>(){

            @Override
            public void accept(Double newHardness) {
                BlockMetadataBase.this.func_149711_c(1.5f * UndergroundBiomes.hardnessModifier());
            }
        };
        this.resistanceUpdater = new Acceptor<Double>(){

            @Override
            public void accept(Double newResistance) {
                BlockMetadataBase.this.func_149752_b(1.66f * UndergroundBiomes.resistanceModifier());
                BlockMetadataBase.this.ubExplosionResistance = BlockMetadataBase.this.field_149781_w;
            }
        };
        this.func_149663_c(block.internal());
        this.func_149647_a((CreativeTabs)UndergroundBiomes.tabModBlocks);
        this.namer = block;
        try {
            this.renderID = super.func_149645_b();
        }
        catch (NoSuchFieldError e) {
            this.renderID = super.func_149645_b();
        }
        catch (Error e) {
            this.renderID = super.func_149645_b();
        }
        UndergroundBiomes.instance().settings().hardnessModifier.informOnChange(this.hardnessUpdater);
        this.hardnessUpdater.accept(UndergroundBiomes.instance().settings().hardnessModifier.value());
        UndergroundBiomes.instance().settings().resistanceModifier.informOnChange(this.resistanceUpdater);
        this.resistanceUpdater.accept(UndergroundBiomes.instance().settings().resistanceModifier.value());
    }

    public int func_149645_b() {
        return this.renderID;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister iconRegister) {
        for (int i = 0; i < 8; ++i) {
            this.textures[i] = iconRegister.func_94245_a("undergroundbiomes:" + this.getBlockName(i));
        }
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int side, int metadata) {
        IIcon result = this.textures[metadata & 7];
        if (result == null) {
            throw new RuntimeException("" + metadata);
        }
        return this.textures[metadata & 7];
    }

    @SideOnly(value=Side.CLIENT)
    protected String func_149641_N() {
        return this.field_149768_d == null ? "MISSING_ICON_BLOCK_" + BlockMetadataBase.func_149682_b((Block)this) + "_" + this.func_149739_a() : this.field_149768_d;
    }

    public void func_149666_a(Item id, CreativeTabs tabs, List list) {
        for (int i = 0; i < 8; ++i) {
            list.add(new ItemStack(id, 1, i));
        }
    }

    public int func_149692_a(int metadata) {
        return metadata & 7;
    }

    public boolean isReplaceableOreGen(World world, int x, int y, int z, Block target) {
        if (target == null) {
            return this.replaceableByOre;
        }
        if (!UndergroundBiomes.instance().settings().newGeneration.value().booleanValue()) {
            BiomeUndergroundDecorator.needsRedo(x, z, world);
        }
        return this.replaceableByOre && target.func_149739_a().equals(Blocks.field_150348_b.func_149739_a());
    }

    public float getBlockHardness(int meta) {
        float result = this.field_149782_v;
        return result;
    }

    public float getBlockExplosionResistance(int meta) {
        float result = this.ubExplosionResistance;
        return result;
    }

    public float getExplosionResistance(Entity entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ) {
        return this.getBlockExplosionResistance(this.func_149643_k(world, x, y, z) & 7);
    }

    public float func_149712_f(World world, int x, int y, int z) {
        return this.getBlockHardness(this.func_149643_k(world, x, y, z) & 7);
    }

    protected ItemStack func_149644_j(int metadata) {
        return new ItemStack((Block)this, 1, metadata & 7);
    }

    public ItemStack itemDropped(int metadata, Random random, int fortune, int y) {
        return new ItemStack((Block)this, 1, metadata & 7);
    }

    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        int count = 1;
        ItemStack stack = this.itemDropped(metadata, world.field_73012_v, fortune, y);
        if (fortune != 0 && UndergroundBiomes.fortuneAffected.contains(stack.func_77973_b().func_77658_a())) {
            int j = world.field_73012_v.nextInt(fortune + 2);
            count = j < 1 ? 1 : j;
        }
        for (int i = 0; i < count; ++i) {
            ret.add(stack);
        }
        return ret;
    }

    public abstract String getBlockTypeName(int var1);

    public abstract boolean hasRareDrops();

    public String getBlockName(int index) {
        return this.getBlockTypeName(index);
    }

}

