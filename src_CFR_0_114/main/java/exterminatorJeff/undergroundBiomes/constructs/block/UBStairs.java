/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockStairs
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package exterminatorJeff.undergroundBiomes.constructs.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import exterminatorJeff.undergroundBiomes.common.CreativeTabModBlocks;
import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;
import exterminatorJeff.undergroundBiomes.common.block.BlockMetadataBase;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class UBStairs
extends BlockStairs {
    final BlockMetadataBase baseStone;
    final int lowerMetadata;
    public IIcon currentIcon;
    public boolean renderingItem;
    static final int materialFace = 2;

    UBStairs(BlockMetadataBase material, int lowerMetadata) {
        super((Block)material, 2);
        this.baseStone = material;
        this.lowerMetadata = lowerMetadata;
        this.func_149647_a((CreativeTabs)UndergroundBiomes.tabModBlocks);
        this.field_149786_r = 1;
    }

    public final int blockMetadata(int worldMetadata) {
        return this.lowerMetadata + ((worldMetadata & 8) >> 3);
    }

    public void func_150147_e(IBlockAccess arg0, int arg1, int arg2, int arg3) {
        super.func_150147_e(arg0, arg1, arg2, arg3);
        this.renderingItem = false;
    }

    public void func_149651_a(IIconRegister arg0) {
    }

    public int func_149741_i(int metadata) {
        this.currentIcon = metadata > 7 ? this.baseStone.func_149691_a(2, this.lowerMetadata() + 1) : this.baseStone.func_149691_a(2, this.lowerMetadata());
        return super.func_149741_i(metadata);
    }

    public BlockMetadataBase baseStone() {
        return this.baseStone;
    }

    public int lowerMetadata() {
        return this.lowerMetadata;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int side, int metadata) {
        if (metadata > 7) {
            if (this.currentIcon != null) {
                // empty if block
            }
            if (side >= 6) {
                this.renderingItem = false;
            }
            this.currentIcon = null;
            return this.baseStone.func_149691_a(2, this.lowerMetadata() + 1);
        }
        if (this.currentIcon != null && this.renderingItem) {
            IIcon result = this.currentIcon;
            if (side >= 6) {
                this.currentIcon = null;
                this.renderingItem = false;
            }
            return result;
        }
        this.currentIcon = this.baseStone.func_149691_a(2, this.lowerMetadata());
        return this.currentIcon;
    }

    public IIcon func_149673_e(IBlockAccess world, int x, int y, int z, int side) {
        return this.func_149691_a(side, world.func_72805_g(x, y, z));
    }

    public void func_149666_a(Item id, CreativeTabs tabs, List list) {
        list.add(new ItemStack(id, 1, 0));
        list.add(new ItemStack(id, 1, 8));
    }

    public IIcon func_149735_b(int side, int metadata) {
        if (metadata > 7) {
            if (this.currentIcon != null) {
                // empty if block
            }
            if (side >= 6) {
                this.renderingItem = false;
            }
            this.currentIcon = null;
            return this.baseStone.func_149691_a(2, this.lowerMetadata() + 1);
        }
        if (this.currentIcon != null && this.renderingItem) {
            IIcon result = this.currentIcon;
            if (side >= 6) {
                this.currentIcon = null;
                this.renderingItem = false;
            }
            return result;
        }
        this.currentIcon = this.baseStone.func_149691_a(2, this.lowerMetadata());
        return this.currentIcon;
    }

    public void func_149664_b(World p_149664_1_, int p_149664_2_, int p_149664_3_, int p_149664_4_, int p_149664_5_) {
        this.baseStone.func_149664_b(p_149664_1_, p_149664_2_, p_149664_3_, p_149664_4_, this.blockMetadata(p_149664_5_));
    }

    public void func_149689_a(World p_149689_1_, int p_149689_2_, int p_149689_3_, int p_149689_4_, EntityLivingBase p_149689_5_, ItemStack placed) {
        int l = MathHelper.func_76128_c((double)((double)(p_149689_5_.field_70177_z * 4.0f / 360.0f) + 0.5)) & 3;
        int i1 = p_149689_1_.func_72805_g(p_149689_2_, p_149689_3_, p_149689_4_) & 4;
        int addToMetadata = 0;
        if (placed.func_77960_j() > 0) {
            addToMetadata = 8;
        }
        if (l == 0) {
            p_149689_1_.func_72921_c(p_149689_2_, p_149689_3_, p_149689_4_, (2 | i1) + addToMetadata, 2);
        }
        if (l == 1) {
            p_149689_1_.func_72921_c(p_149689_2_, p_149689_3_, p_149689_4_, (1 | i1) + addToMetadata, 2);
        }
        if (l == 2) {
            p_149689_1_.func_72921_c(p_149689_2_, p_149689_3_, p_149689_4_, (3 | i1) + addToMetadata, 2);
        }
        if (l == 3) {
            p_149689_1_.func_72921_c(p_149689_2_, p_149689_3_, p_149689_4_, (0 | i1) + addToMetadata, 2);
        }
    }

    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        ArrayList result = super.getDrops(world, x, y, z, metadata & 8, fortune);
        Iterator i$ = result.iterator();
        if (i$.hasNext()) {
            ItemStack itemStack = (ItemStack)i$.next();
            itemStack.func_77964_b(metadata & 8);
        }
        return result;
    }

    public int func_149660_a(World p_149660_1_, int p_149660_2_, int p_149660_3_, int p_149660_4_, int p_149660_5_, float p_149660_6_, float p_149660_7_, float p_149660_8_, int p_149660_9_) {
        int result = p_149660_5_ != 0 && (p_149660_5_ == 1 || (double)p_149660_7_ <= 0.5) ? p_149660_9_ : p_149660_9_ | 4;
        return result;
    }

    boolean func_150146_f(IBlockAccess p_150146_1_, int p_150146_2_, int p_150146_3_, int p_150146_4_, int p_150146_5_) {
        Block block = p_150146_1_.func_147439_a(p_150146_2_, p_150146_3_, p_150146_4_);
        return UBStairs.func_150148_a((Block)block) && (p_150146_1_.func_72805_g(p_150146_2_, p_150146_3_, p_150146_4_) & 7) == p_150146_5_;
    }

    public boolean func_150145_f(IBlockAccess p_150145_1_, int p_150145_2_, int p_150145_3_, int p_150145_4_) {
        int l = p_150145_1_.func_72805_g(p_150145_2_, p_150145_3_, p_150145_4_);
        int i1 = l & 3;
        float f = 0.5f;
        float f1 = 1.0f;
        if ((l & 4) != 0) {
            f = 0.0f;
            f1 = 0.5f;
        }
        float f2 = 0.0f;
        float f3 = 1.0f;
        float f4 = 0.0f;
        float f5 = 0.5f;
        boolean flag = true;
        if (i1 == 0) {
            f2 = 0.5f;
            f5 = 1.0f;
            Block block = p_150145_1_.func_147439_a(p_150145_2_ + 1, p_150145_3_, p_150145_4_);
            int j1 = p_150145_1_.func_72805_g(p_150145_2_ + 1, p_150145_3_, p_150145_4_);
            if (UBStairs.func_150148_a((Block)block) && (l & 4) == (j1 & 4)) {
                int k1 = j1 & 3;
                if (k1 == 3 && !this.func_150146_f(p_150145_1_, p_150145_2_, p_150145_3_, p_150145_4_ + 1, l)) {
                    f5 = 0.5f;
                    flag = false;
                } else if (k1 == 2 && !this.func_150146_f(p_150145_1_, p_150145_2_, p_150145_3_, p_150145_4_ - 1, l)) {
                    f4 = 0.5f;
                    flag = false;
                }
            }
        } else if (i1 == 1) {
            f3 = 0.5f;
            f5 = 1.0f;
            Block block = p_150145_1_.func_147439_a(p_150145_2_ - 1, p_150145_3_, p_150145_4_);
            int j1 = p_150145_1_.func_72805_g(p_150145_2_ - 1, p_150145_3_, p_150145_4_);
            if (UBStairs.func_150148_a((Block)block) && (l & 4) == (j1 & 4)) {
                int k1 = j1 & 3;
                if (k1 == 3 && !this.func_150146_f(p_150145_1_, p_150145_2_, p_150145_3_, p_150145_4_ + 1, l)) {
                    f5 = 0.5f;
                    flag = false;
                } else if (k1 == 2 && !this.func_150146_f(p_150145_1_, p_150145_2_, p_150145_3_, p_150145_4_ - 1, l)) {
                    f4 = 0.5f;
                    flag = false;
                }
            }
        } else if (i1 == 2) {
            f4 = 0.5f;
            f5 = 1.0f;
            Block block = p_150145_1_.func_147439_a(p_150145_2_, p_150145_3_, p_150145_4_ + 1);
            int j1 = p_150145_1_.func_72805_g(p_150145_2_, p_150145_3_, p_150145_4_ + 1);
            if (UBStairs.func_150148_a((Block)block) && (l & 4) == (j1 & 4)) {
                int k1 = j1 & 3;
                if (k1 == 1 && !this.func_150146_f(p_150145_1_, p_150145_2_ + 1, p_150145_3_, p_150145_4_, l)) {
                    f3 = 0.5f;
                    flag = false;
                } else if (k1 == 0 && !this.func_150146_f(p_150145_1_, p_150145_2_ - 1, p_150145_3_, p_150145_4_, l)) {
                    f2 = 0.5f;
                    flag = false;
                }
            }
        } else if (i1 == 3) {
            Block block = p_150145_1_.func_147439_a(p_150145_2_, p_150145_3_, p_150145_4_ - 1);
            int j1 = p_150145_1_.func_72805_g(p_150145_2_, p_150145_3_, p_150145_4_ - 1);
            if (UBStairs.func_150148_a((Block)block) && (l & 4) == (j1 & 4)) {
                int k1 = j1 & 3;
                if (k1 == 1 && !this.func_150146_f(p_150145_1_, p_150145_2_ + 1, p_150145_3_, p_150145_4_, l)) {
                    f3 = 0.5f;
                    flag = false;
                } else if (k1 == 0 && !this.func_150146_f(p_150145_1_, p_150145_2_ - 1, p_150145_3_, p_150145_4_, l)) {
                    f2 = 0.5f;
                    flag = false;
                }
            }
        }
        this.func_149676_a(f2, f, f4, f3, f1, f5);
        return flag;
    }

    public boolean func_150144_g(IBlockAccess p_150144_1_, int p_150144_2_, int p_150144_3_, int p_150144_4_) {
        int l = p_150144_1_.func_72805_g(p_150144_2_, p_150144_3_, p_150144_4_);
        int i1 = l & 3;
        float f = 0.5f;
        float f1 = 1.0f;
        if ((l & 4) != 0) {
            f = 0.0f;
            f1 = 0.5f;
        }
        float f2 = 0.0f;
        float f3 = 0.5f;
        float f4 = 0.5f;
        float f5 = 1.0f;
        boolean flag = false;
        if (i1 == 0) {
            Block block = p_150144_1_.func_147439_a(p_150144_2_ - 1, p_150144_3_, p_150144_4_);
            int j1 = p_150144_1_.func_72805_g(p_150144_2_ - 1, p_150144_3_, p_150144_4_);
            if (UBStairs.func_150148_a((Block)block) && (l & 4) == (j1 & 4)) {
                int k1 = j1 & 3;
                if (k1 == 3 && !this.func_150146_f(p_150144_1_, p_150144_2_, p_150144_3_, p_150144_4_ - 1, l)) {
                    f4 = 0.0f;
                    f5 = 0.5f;
                    flag = true;
                } else if (k1 == 2 && !this.func_150146_f(p_150144_1_, p_150144_2_, p_150144_3_, p_150144_4_ + 1, l)) {
                    f4 = 0.5f;
                    f5 = 1.0f;
                    flag = true;
                }
            }
        } else if (i1 == 1) {
            Block block = p_150144_1_.func_147439_a(p_150144_2_ + 1, p_150144_3_, p_150144_4_);
            int j1 = p_150144_1_.func_72805_g(p_150144_2_ + 1, p_150144_3_, p_150144_4_);
            if (UBStairs.func_150148_a((Block)block) && (l & 4) == (j1 & 4)) {
                f2 = 0.5f;
                f3 = 1.0f;
                int k1 = j1 & 3;
                if (k1 == 3 && !this.func_150146_f(p_150144_1_, p_150144_2_, p_150144_3_, p_150144_4_ - 1, l)) {
                    f4 = 0.0f;
                    f5 = 0.5f;
                    flag = true;
                } else if (k1 == 2 && !this.func_150146_f(p_150144_1_, p_150144_2_, p_150144_3_, p_150144_4_ + 1, l)) {
                    f4 = 0.5f;
                    f5 = 1.0f;
                    flag = true;
                }
            }
        } else if (i1 == 2) {
            Block block = p_150144_1_.func_147439_a(p_150144_2_, p_150144_3_, p_150144_4_ - 1);
            int j1 = p_150144_1_.func_72805_g(p_150144_2_, p_150144_3_, p_150144_4_ - 1);
            if (UBStairs.func_150148_a((Block)block) && (l & 4) == (j1 & 4)) {
                f4 = 0.0f;
                f5 = 0.5f;
                int k1 = j1 & 3;
                if (k1 == 1 && !this.func_150146_f(p_150144_1_, p_150144_2_ - 1, p_150144_3_, p_150144_4_, l)) {
                    flag = true;
                } else if (k1 == 0 && !this.func_150146_f(p_150144_1_, p_150144_2_ + 1, p_150144_3_, p_150144_4_, l)) {
                    f2 = 0.5f;
                    f3 = 1.0f;
                    flag = true;
                }
            }
        } else if (i1 == 3) {
            Block block = p_150144_1_.func_147439_a(p_150144_2_, p_150144_3_, p_150144_4_ + 1);
            int j1 = p_150144_1_.func_72805_g(p_150144_2_, p_150144_3_, p_150144_4_ + 1);
            if (UBStairs.func_150148_a((Block)block) && (l & 4) == (j1 & 4)) {
                int k1 = j1 & 3;
                if (k1 == 1 && !this.func_150146_f(p_150144_1_, p_150144_2_ - 1, p_150144_3_, p_150144_4_, l)) {
                    flag = true;
                } else if (k1 == 0 && !this.func_150146_f(p_150144_1_, p_150144_2_ + 1, p_150144_3_, p_150144_4_, l)) {
                    f2 = 0.5f;
                    f3 = 1.0f;
                    flag = true;
                }
            }
        }
        if (flag) {
            this.func_149676_a(f2, f, f4, f3, f1, f5);
        }
        return flag;
    }
}

