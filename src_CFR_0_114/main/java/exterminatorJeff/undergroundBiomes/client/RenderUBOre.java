/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler
 *  cpw.mods.fml.client.registry.RenderingRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockAnvil
 *  net.minecraft.block.BlockBeacon
 *  net.minecraft.block.BlockGrass
 *  net.minecraft.block.BlockHopper
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.tileentity.TileEntityRendererChestHelper
 *  net.minecraft.init.Blocks
 *  net.minecraft.src.FMLRenderAccessLibrary
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  org.lwjgl.opengl.GL11
 */
package exterminatorJeff.undergroundBiomes.client;

import Zeno410Utils.Accessor;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import exterminatorJeff.undergroundBiomes.common.block.BlockMetadataBase;
import exterminatorJeff.undergroundBiomes.worldGen.OreUBifier;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAnvil;
import net.minecraft.block.BlockBeacon;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockHopper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntityRendererChestHelper;
import net.minecraft.init.Blocks;
import net.minecraft.src.FMLRenderAccessLibrary;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

public class RenderUBOre
implements ISimpleBlockRenderingHandler {
    private int renderType = RenderingRegistry.getNextAvailableRenderId();
    private final OreUBifier ubifier;
    private static Accessor<RenderingRegistry, Map> blockRenderers = new Accessor(RenderingRegistry.class);

    public RenderUBOre(OreUBifier ubifier) {
        ubifier.setRenderer(this);
        this.ubifier = ubifier;
    }

    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
        BlockMetadataBase stoneBlock = this.ubifier.baseStone(block);
        Block overlayBlock = this.ubifier.overlayBlock(block);
        this.renderOreBlockAsItem((Block)stoneBlock, overlayBlock, metadata, 1.0f, renderer);
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        int metadata = world.func_72805_g(x, y, z);
        int i = x;
        int j = y;
        int k = z;
        Block renderedBlock = world.func_147439_a(x, y, z);
        IIcon overlayTexture = renderer.field_147840_d;
        IIcon self = this.ubifier.baseStone(renderedBlock).func_149691_a(0, metadata &= 7);
        renderer.func_147792_a((Block)this.ubifier.baseStone(renderedBlock), x, y, z, self);
        IIcon overlay = this.ubifier.overlayBlock(renderedBlock).func_149691_a(0, 0);
        renderer.func_147792_a(this.ubifier.overlayBlock(renderedBlock), x, y, z, overlay);
        if (overlayTexture != null) {
            renderer.func_147792_a((Block)this.ubifier.baseStone(renderedBlock), x, y, z, overlayTexture);
        }
        return true;
    }

    public boolean shouldRender3DInInventory(int arg0) {
        return true;
    }

    public int getRenderId() {
        return this.renderType;
    }

    public static Object rendered(Integer ID) {
        return blockRenderers.get(RenderingRegistry.instance()).get(ID);
    }

    public void renderOreBlockAsItem(Block stone, Block overlay, int p_147800_2_, float p_147800_3_, RenderBlocks renderer) {
        float f3;
        int j;
        float f2;
        boolean flag;
        Tessellator tessellator = Tessellator.field_78398_a;
        boolean bl = flag = stone == Blocks.field_150349_c;
        if (stone == Blocks.field_150367_z || stone == Blocks.field_150409_cd || stone == Blocks.field_150460_al) {
            p_147800_2_ = 3;
        }
        if (renderer.field_147844_c) {
            j = stone.func_149741_i(p_147800_2_);
            if (flag) {
                j = 16777215;
            }
            float f1 = (float)(j >> 16 & 255) / 255.0f;
            f2 = (float)(j >> 8 & 255) / 255.0f;
            f3 = (float)(j & 255) / 255.0f;
            GL11.glColor4f((float)(f1 * p_147800_3_), (float)(f2 * p_147800_3_), (float)(f3 * p_147800_3_), (float)1.0f);
        }
        j = stone.func_149645_b();
        renderer.func_147775_a(stone);
        if (j == 16) {
            p_147800_2_ = 1;
        }
        stone.func_149683_g();
        renderer.func_147775_a(stone);
        GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)-0.5f);
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, -1.0f, 0.0f);
        renderer.func_147768_a(stone, 0.0, 0.0, 0.0, renderer.func_147787_a(stone, 0, p_147800_2_));
        renderer.func_147768_a(stone, 0.0, 0.0, 0.0, renderer.func_147787_a(overlay, 0, p_147800_2_));
        tessellator.func_78381_a();
        if (flag && renderer.field_147844_c) {
            int k = stone.func_149741_i(p_147800_2_);
            f2 = (float)(k >> 16 & 255) / 255.0f;
            f3 = (float)(k >> 8 & 255) / 255.0f;
            float f4 = (float)(k & 255) / 255.0f;
            GL11.glColor4f((float)(f2 * p_147800_3_), (float)(f3 * p_147800_3_), (float)(f4 * p_147800_3_), (float)1.0f);
        }
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, 1.0f, 0.0f);
        renderer.func_147806_b(stone, 0.0, 0.0, 0.0, renderer.func_147787_a(stone, 1, p_147800_2_));
        renderer.func_147806_b(overlay, 0.0, 0.0, 0.0, renderer.func_147787_a(overlay, 1, p_147800_2_));
        tessellator.func_78381_a();
        if (flag && renderer.field_147844_c) {
            GL11.glColor4f((float)p_147800_3_, (float)p_147800_3_, (float)p_147800_3_, (float)1.0f);
        }
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, 0.0f, -1.0f);
        renderer.func_147761_c(stone, 0.0, 0.0, 0.0, renderer.func_147787_a(stone, 2, p_147800_2_));
        renderer.func_147761_c(stone, 0.0, 0.0, 0.0, renderer.func_147787_a(overlay, 2, p_147800_2_));
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, 0.0f, 1.0f);
        renderer.func_147734_d(stone, 0.0, 0.0, 0.0, renderer.func_147787_a(stone, 3, p_147800_2_));
        renderer.func_147734_d(stone, 0.0, 0.0, 0.0, renderer.func_147787_a(overlay, 3, p_147800_2_));
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(-1.0f, 0.0f, 0.0f);
        renderer.func_147798_e(stone, 0.0, 0.0, 0.0, renderer.func_147787_a(stone, 4, p_147800_2_));
        renderer.func_147798_e(stone, 0.0, 0.0, 0.0, renderer.func_147787_a(overlay, 4, p_147800_2_));
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(1.0f, 0.0f, 0.0f);
        renderer.func_147764_f(stone, 0.0, 0.0, 0.0, renderer.func_147787_a(stone, 5, p_147800_2_));
        renderer.func_147764_f(stone, 0.0, 0.0, 0.0, renderer.func_147787_a(overlay, 5, p_147800_2_));
        tessellator.func_78381_a();
        GL11.glTranslatef((float)0.5f, (float)0.5f, (float)0.5f);
    }

    public void renderBlockAsItem(Block p_147800_1_, int p_147800_2_, float p_147800_3_, RenderBlocks renderer) {
        float f3;
        float f2;
        float f1;
        int j;
        boolean flag;
        Tessellator tessellator = Tessellator.field_78398_a;
        boolean bl = flag = p_147800_1_ == Blocks.field_150349_c;
        if (p_147800_1_ == Blocks.field_150367_z || p_147800_1_ == Blocks.field_150409_cd || p_147800_1_ == Blocks.field_150460_al) {
            p_147800_2_ = 3;
        }
        if (renderer.field_147844_c) {
            j = p_147800_1_.func_149741_i(p_147800_2_);
            if (flag) {
                j = 16777215;
            }
            f1 = (float)(j >> 16 & 255) / 255.0f;
            f2 = (float)(j >> 8 & 255) / 255.0f;
            f3 = (float)(j & 255) / 255.0f;
            GL11.glColor4f((float)(f1 * p_147800_3_), (float)(f2 * p_147800_3_), (float)(f3 * p_147800_3_), (float)1.0f);
        }
        j = p_147800_1_.func_149645_b();
        renderer.func_147775_a(p_147800_1_);
        if (j != 0 && j != 31 && j != 39 && j != 16 && j != 26) {
            if (j == 1) {
                tessellator.func_78382_b();
                tessellator.func_78375_b(0.0f, -1.0f, 0.0f);
                IIcon iicon = renderer.func_147787_a(p_147800_1_, 0, p_147800_2_);
                renderer.func_147765_a(iicon, -0.5, -0.5, -0.5, 1.0f);
                tessellator.func_78381_a();
            } else if (j == 19) {
                tessellator.func_78382_b();
                tessellator.func_78375_b(0.0f, -1.0f, 0.0f);
                p_147800_1_.func_149683_g();
                renderer.func_147730_a(p_147800_1_, p_147800_2_, renderer.field_147857_k, -0.5, -0.5, -0.5);
                tessellator.func_78381_a();
            } else if (j == 23) {
                tessellator.func_78382_b();
                tessellator.func_78375_b(0.0f, -1.0f, 0.0f);
                p_147800_1_.func_149683_g();
                tessellator.func_78381_a();
            } else if (j == 13) {
                p_147800_1_.func_149683_g();
                GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)-0.5f);
                f1 = 0.0625f;
                tessellator.func_78382_b();
                tessellator.func_78375_b(0.0f, -1.0f, 0.0f);
                renderer.func_147768_a(p_147800_1_, 0.0, 0.0, 0.0, renderer.func_147777_a(p_147800_1_, 0));
                tessellator.func_78381_a();
                tessellator.func_78382_b();
                tessellator.func_78375_b(0.0f, 1.0f, 0.0f);
                renderer.func_147806_b(p_147800_1_, 0.0, 0.0, 0.0, renderer.func_147777_a(p_147800_1_, 1));
                tessellator.func_78381_a();
                tessellator.func_78382_b();
                tessellator.func_78375_b(0.0f, 0.0f, -1.0f);
                tessellator.func_78372_c(0.0f, 0.0f, f1);
                renderer.func_147761_c(p_147800_1_, 0.0, 0.0, 0.0, renderer.func_147777_a(p_147800_1_, 2));
                tessellator.func_78372_c(0.0f, 0.0f, - f1);
                tessellator.func_78381_a();
                tessellator.func_78382_b();
                tessellator.func_78375_b(0.0f, 0.0f, 1.0f);
                tessellator.func_78372_c(0.0f, 0.0f, - f1);
                renderer.func_147734_d(p_147800_1_, 0.0, 0.0, 0.0, renderer.func_147777_a(p_147800_1_, 3));
                tessellator.func_78372_c(0.0f, 0.0f, f1);
                tessellator.func_78381_a();
                tessellator.func_78382_b();
                tessellator.func_78375_b(-1.0f, 0.0f, 0.0f);
                tessellator.func_78372_c(f1, 0.0f, 0.0f);
                renderer.func_147798_e(p_147800_1_, 0.0, 0.0, 0.0, renderer.func_147777_a(p_147800_1_, 4));
                tessellator.func_78372_c(- f1, 0.0f, 0.0f);
                tessellator.func_78381_a();
                tessellator.func_78382_b();
                tessellator.func_78375_b(1.0f, 0.0f, 0.0f);
                tessellator.func_78372_c(- f1, 0.0f, 0.0f);
                renderer.func_147764_f(p_147800_1_, 0.0, 0.0, 0.0, renderer.func_147777_a(p_147800_1_, 5));
                tessellator.func_78372_c(f1, 0.0f, 0.0f);
                tessellator.func_78381_a();
                GL11.glTranslatef((float)0.5f, (float)0.5f, (float)0.5f);
            } else if (j == 22) {
                GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)-0.5f);
                TileEntityRendererChestHelper.field_147719_a.func_147715_a(p_147800_1_, p_147800_2_, p_147800_3_);
                GL11.glEnable((int)32826);
            } else if (j == 6) {
                tessellator.func_78382_b();
                tessellator.func_78375_b(0.0f, -1.0f, 0.0f);
                renderer.func_147795_a(p_147800_1_, p_147800_2_, -0.5, -0.5, -0.5);
                tessellator.func_78381_a();
            } else if (j == 2) {
                tessellator.func_78382_b();
                tessellator.func_78375_b(0.0f, -1.0f, 0.0f);
                renderer.func_147747_a(p_147800_1_, -0.5, -0.5, -0.5, 0.0, 0.0, 0);
                tessellator.func_78381_a();
            } else if (j == 10) {
                for (int k = 0; k < 2; ++k) {
                    if (k == 0) {
                        renderer.func_147782_a(0.0, 0.0, 0.0, 1.0, 1.0, 0.5);
                    }
                    if (k == 1) {
                        renderer.func_147782_a(0.0, 0.0, 0.5, 1.0, 0.5, 1.0);
                    }
                    GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)-0.5f);
                    tessellator.func_78382_b();
                    tessellator.func_78375_b(0.0f, -1.0f, 0.0f);
                    renderer.func_147768_a(p_147800_1_, 0.0, 0.0, 0.0, renderer.func_147777_a(p_147800_1_, 0));
                    tessellator.func_78381_a();
                    tessellator.func_78382_b();
                    tessellator.func_78375_b(0.0f, 1.0f, 0.0f);
                    renderer.func_147806_b(p_147800_1_, 0.0, 0.0, 0.0, renderer.func_147777_a(p_147800_1_, 1));
                    tessellator.func_78381_a();
                    tessellator.func_78382_b();
                    tessellator.func_78375_b(0.0f, 0.0f, -1.0f);
                    renderer.func_147761_c(p_147800_1_, 0.0, 0.0, 0.0, renderer.func_147777_a(p_147800_1_, 2));
                    tessellator.func_78381_a();
                    tessellator.func_78382_b();
                    tessellator.func_78375_b(0.0f, 0.0f, 1.0f);
                    renderer.func_147734_d(p_147800_1_, 0.0, 0.0, 0.0, renderer.func_147777_a(p_147800_1_, 3));
                    tessellator.func_78381_a();
                    tessellator.func_78382_b();
                    tessellator.func_78375_b(-1.0f, 0.0f, 0.0f);
                    renderer.func_147798_e(p_147800_1_, 0.0, 0.0, 0.0, renderer.func_147777_a(p_147800_1_, 4));
                    tessellator.func_78381_a();
                    tessellator.func_78382_b();
                    tessellator.func_78375_b(1.0f, 0.0f, 0.0f);
                    renderer.func_147764_f(p_147800_1_, 0.0, 0.0, 0.0, renderer.func_147777_a(p_147800_1_, 5));
                    tessellator.func_78381_a();
                    GL11.glTranslatef((float)0.5f, (float)0.5f, (float)0.5f);
                }
            } else if (j == 27) {
                int k = 0;
                GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)-0.5f);
                tessellator.func_78382_b();
                for (int l = 0; l < 8; ++l) {
                    int b0 = 0;
                    int b1 = 1;
                    if (l == 0) {
                        b0 = 2;
                    }
                    if (l == 1) {
                        b0 = 3;
                    }
                    if (l == 2) {
                        b0 = 4;
                    }
                    if (l == 3) {
                        b0 = 5;
                        b1 = 2;
                    }
                    if (l == 4) {
                        b0 = 6;
                        b1 = 3;
                    }
                    if (l == 5) {
                        b0 = 7;
                        b1 = 5;
                    }
                    if (l == 6) {
                        b0 = 6;
                        b1 = 2;
                    }
                    if (l == 7) {
                        b0 = 3;
                    }
                    float f5 = (float)b0 / 16.0f;
                    float f6 = 1.0f - (float)k / 16.0f;
                    float f7 = 1.0f - (float)(k + b1) / 16.0f;
                    k += b1;
                    renderer.func_147782_a((double)(0.5f - f5), (double)f7, (double)(0.5f - f5), (double)(0.5f + f5), (double)f6, (double)(0.5f + f5));
                    tessellator.func_78375_b(0.0f, -1.0f, 0.0f);
                    renderer.func_147768_a(p_147800_1_, 0.0, 0.0, 0.0, renderer.func_147777_a(p_147800_1_, 0));
                    tessellator.func_78375_b(0.0f, 1.0f, 0.0f);
                    renderer.func_147806_b(p_147800_1_, 0.0, 0.0, 0.0, renderer.func_147777_a(p_147800_1_, 1));
                    tessellator.func_78375_b(0.0f, 0.0f, -1.0f);
                    renderer.func_147761_c(p_147800_1_, 0.0, 0.0, 0.0, renderer.func_147777_a(p_147800_1_, 2));
                    tessellator.func_78375_b(0.0f, 0.0f, 1.0f);
                    renderer.func_147734_d(p_147800_1_, 0.0, 0.0, 0.0, renderer.func_147777_a(p_147800_1_, 3));
                    tessellator.func_78375_b(-1.0f, 0.0f, 0.0f);
                    renderer.func_147798_e(p_147800_1_, 0.0, 0.0, 0.0, renderer.func_147777_a(p_147800_1_, 4));
                    tessellator.func_78375_b(1.0f, 0.0f, 0.0f);
                    renderer.func_147764_f(p_147800_1_, 0.0, 0.0, 0.0, renderer.func_147777_a(p_147800_1_, 5));
                }
                tessellator.func_78381_a();
                GL11.glTranslatef((float)0.5f, (float)0.5f, (float)0.5f);
                renderer.func_147782_a(0.0, 0.0, 0.0, 1.0, 1.0, 1.0);
            } else if (j == 11) {
                for (int k = 0; k < 4; ++k) {
                    f2 = 0.125f;
                    if (k == 0) {
                        renderer.func_147782_a((double)(0.5f - f2), 0.0, 0.0, (double)(0.5f + f2), 1.0, (double)(f2 * 2.0f));
                    }
                    if (k == 1) {
                        renderer.func_147782_a((double)(0.5f - f2), 0.0, (double)(1.0f - f2 * 2.0f), (double)(0.5f + f2), 1.0, 1.0);
                    }
                    f2 = 0.0625f;
                    if (k == 2) {
                        renderer.func_147782_a((double)(0.5f - f2), (double)(1.0f - f2 * 3.0f), (double)((- f2) * 2.0f), (double)(0.5f + f2), (double)(1.0f - f2), (double)(1.0f + f2 * 2.0f));
                    }
                    if (k == 3) {
                        renderer.func_147782_a((double)(0.5f - f2), (double)(0.5f - f2 * 3.0f), (double)((- f2) * 2.0f), (double)(0.5f + f2), (double)(0.5f - f2), (double)(1.0f + f2 * 2.0f));
                    }
                    GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)-0.5f);
                    tessellator.func_78382_b();
                    tessellator.func_78375_b(0.0f, -1.0f, 0.0f);
                    renderer.func_147768_a(p_147800_1_, 0.0, 0.0, 0.0, renderer.func_147777_a(p_147800_1_, 0));
                    tessellator.func_78381_a();
                    tessellator.func_78382_b();
                    tessellator.func_78375_b(0.0f, 1.0f, 0.0f);
                    renderer.func_147806_b(p_147800_1_, 0.0, 0.0, 0.0, renderer.func_147777_a(p_147800_1_, 1));
                    tessellator.func_78381_a();
                    tessellator.func_78382_b();
                    tessellator.func_78375_b(0.0f, 0.0f, -1.0f);
                    renderer.func_147761_c(p_147800_1_, 0.0, 0.0, 0.0, renderer.func_147777_a(p_147800_1_, 2));
                    tessellator.func_78381_a();
                    tessellator.func_78382_b();
                    tessellator.func_78375_b(0.0f, 0.0f, 1.0f);
                    renderer.func_147734_d(p_147800_1_, 0.0, 0.0, 0.0, renderer.func_147777_a(p_147800_1_, 3));
                    tessellator.func_78381_a();
                    tessellator.func_78382_b();
                    tessellator.func_78375_b(-1.0f, 0.0f, 0.0f);
                    renderer.func_147798_e(p_147800_1_, 0.0, 0.0, 0.0, renderer.func_147777_a(p_147800_1_, 4));
                    tessellator.func_78381_a();
                    tessellator.func_78382_b();
                    tessellator.func_78375_b(1.0f, 0.0f, 0.0f);
                    renderer.func_147764_f(p_147800_1_, 0.0, 0.0, 0.0, renderer.func_147777_a(p_147800_1_, 5));
                    tessellator.func_78381_a();
                    GL11.glTranslatef((float)0.5f, (float)0.5f, (float)0.5f);
                }
                renderer.func_147782_a(0.0, 0.0, 0.0, 1.0, 1.0, 1.0);
            } else if (j == 21) {
                for (int k = 0; k < 3; ++k) {
                    f2 = 0.0625f;
                    if (k == 0) {
                        renderer.func_147782_a((double)(0.5f - f2), 0.30000001192092896, 0.0, (double)(0.5f + f2), 1.0, (double)(f2 * 2.0f));
                    }
                    if (k == 1) {
                        renderer.func_147782_a((double)(0.5f - f2), 0.30000001192092896, (double)(1.0f - f2 * 2.0f), (double)(0.5f + f2), 1.0, 1.0);
                    }
                    f2 = 0.0625f;
                    if (k == 2) {
                        renderer.func_147782_a((double)(0.5f - f2), 0.5, 0.0, (double)(0.5f + f2), (double)(1.0f - f2), 1.0);
                    }
                    GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)-0.5f);
                    tessellator.func_78382_b();
                    tessellator.func_78375_b(0.0f, -1.0f, 0.0f);
                    renderer.func_147768_a(p_147800_1_, 0.0, 0.0, 0.0, renderer.func_147777_a(p_147800_1_, 0));
                    tessellator.func_78381_a();
                    tessellator.func_78382_b();
                    tessellator.func_78375_b(0.0f, 1.0f, 0.0f);
                    renderer.func_147806_b(p_147800_1_, 0.0, 0.0, 0.0, renderer.func_147777_a(p_147800_1_, 1));
                    tessellator.func_78381_a();
                    tessellator.func_78382_b();
                    tessellator.func_78375_b(0.0f, 0.0f, -1.0f);
                    renderer.func_147761_c(p_147800_1_, 0.0, 0.0, 0.0, renderer.func_147777_a(p_147800_1_, 2));
                    tessellator.func_78381_a();
                    tessellator.func_78382_b();
                    tessellator.func_78375_b(0.0f, 0.0f, 1.0f);
                    renderer.func_147734_d(p_147800_1_, 0.0, 0.0, 0.0, renderer.func_147777_a(p_147800_1_, 3));
                    tessellator.func_78381_a();
                    tessellator.func_78382_b();
                    tessellator.func_78375_b(-1.0f, 0.0f, 0.0f);
                    renderer.func_147798_e(p_147800_1_, 0.0, 0.0, 0.0, renderer.func_147777_a(p_147800_1_, 4));
                    tessellator.func_78381_a();
                    tessellator.func_78382_b();
                    tessellator.func_78375_b(1.0f, 0.0f, 0.0f);
                    renderer.func_147764_f(p_147800_1_, 0.0, 0.0, 0.0, renderer.func_147777_a(p_147800_1_, 5));
                    tessellator.func_78381_a();
                    GL11.glTranslatef((float)0.5f, (float)0.5f, (float)0.5f);
                }
            } else if (j == 32) {
                for (int k = 0; k < 2; ++k) {
                    if (k == 0) {
                        renderer.func_147782_a(0.0, 0.0, 0.3125, 1.0, 0.8125, 0.6875);
                    }
                    if (k == 1) {
                        renderer.func_147782_a(0.25, 0.0, 0.25, 0.75, 1.0, 0.75);
                    }
                    GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)-0.5f);
                    tessellator.func_78382_b();
                    tessellator.func_78375_b(0.0f, -1.0f, 0.0f);
                    renderer.func_147768_a(p_147800_1_, 0.0, 0.0, 0.0, renderer.func_147787_a(p_147800_1_, 0, p_147800_2_));
                    tessellator.func_78381_a();
                    tessellator.func_78382_b();
                    tessellator.func_78375_b(0.0f, 1.0f, 0.0f);
                    renderer.func_147806_b(p_147800_1_, 0.0, 0.0, 0.0, renderer.func_147787_a(p_147800_1_, 1, p_147800_2_));
                    tessellator.func_78381_a();
                    tessellator.func_78382_b();
                    tessellator.func_78375_b(0.0f, 0.0f, -1.0f);
                    renderer.func_147761_c(p_147800_1_, 0.0, 0.0, 0.0, renderer.func_147787_a(p_147800_1_, 2, p_147800_2_));
                    tessellator.func_78381_a();
                    tessellator.func_78382_b();
                    tessellator.func_78375_b(0.0f, 0.0f, 1.0f);
                    renderer.func_147734_d(p_147800_1_, 0.0, 0.0, 0.0, renderer.func_147787_a(p_147800_1_, 3, p_147800_2_));
                    tessellator.func_78381_a();
                    tessellator.func_78382_b();
                    tessellator.func_78375_b(-1.0f, 0.0f, 0.0f);
                    renderer.func_147798_e(p_147800_1_, 0.0, 0.0, 0.0, renderer.func_147787_a(p_147800_1_, 4, p_147800_2_));
                    tessellator.func_78381_a();
                    tessellator.func_78382_b();
                    tessellator.func_78375_b(1.0f, 0.0f, 0.0f);
                    renderer.func_147764_f(p_147800_1_, 0.0, 0.0, 0.0, renderer.func_147787_a(p_147800_1_, 5, p_147800_2_));
                    tessellator.func_78381_a();
                    GL11.glTranslatef((float)0.5f, (float)0.5f, (float)0.5f);
                }
                renderer.func_147782_a(0.0, 0.0, 0.0, 1.0, 1.0, 1.0);
            } else if (j == 35) {
                GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)-0.5f);
                renderer.func_147728_a((BlockAnvil)p_147800_1_, 0, 0, 0, p_147800_2_ << 2, true);
                GL11.glTranslatef((float)0.5f, (float)0.5f, (float)0.5f);
            } else if (j == 34) {
                for (int k = 0; k < 3; ++k) {
                    if (k == 0) {
                        renderer.func_147782_a(0.125, 0.0, 0.125, 0.875, 0.1875, 0.875);
                        renderer.func_147757_a(renderer.func_147745_b(Blocks.field_150343_Z));
                    } else if (k == 1) {
                        renderer.func_147782_a(0.1875, 0.1875, 0.1875, 0.8125, 0.875, 0.8125);
                        renderer.func_147757_a(renderer.func_147745_b((Block)Blocks.field_150461_bJ));
                    } else if (k == 2) {
                        renderer.func_147782_a(0.0, 0.0, 0.0, 1.0, 1.0, 1.0);
                        renderer.func_147757_a(renderer.func_147745_b(Blocks.field_150359_w));
                    }
                    GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)-0.5f);
                    tessellator.func_78382_b();
                    tessellator.func_78375_b(0.0f, -1.0f, 0.0f);
                    renderer.func_147768_a(p_147800_1_, 0.0, 0.0, 0.0, renderer.func_147787_a(p_147800_1_, 0, p_147800_2_));
                    tessellator.func_78381_a();
                    tessellator.func_78382_b();
                    tessellator.func_78375_b(0.0f, 1.0f, 0.0f);
                    renderer.func_147806_b(p_147800_1_, 0.0, 0.0, 0.0, renderer.func_147787_a(p_147800_1_, 1, p_147800_2_));
                    tessellator.func_78381_a();
                    tessellator.func_78382_b();
                    tessellator.func_78375_b(0.0f, 0.0f, -1.0f);
                    renderer.func_147761_c(p_147800_1_, 0.0, 0.0, 0.0, renderer.func_147787_a(p_147800_1_, 2, p_147800_2_));
                    tessellator.func_78381_a();
                    tessellator.func_78382_b();
                    tessellator.func_78375_b(0.0f, 0.0f, 1.0f);
                    renderer.func_147734_d(p_147800_1_, 0.0, 0.0, 0.0, renderer.func_147787_a(p_147800_1_, 3, p_147800_2_));
                    tessellator.func_78381_a();
                    tessellator.func_78382_b();
                    tessellator.func_78375_b(-1.0f, 0.0f, 0.0f);
                    renderer.func_147798_e(p_147800_1_, 0.0, 0.0, 0.0, renderer.func_147787_a(p_147800_1_, 4, p_147800_2_));
                    tessellator.func_78381_a();
                    tessellator.func_78382_b();
                    tessellator.func_78375_b(1.0f, 0.0f, 0.0f);
                    renderer.func_147764_f(p_147800_1_, 0.0, 0.0, 0.0, renderer.func_147787_a(p_147800_1_, 5, p_147800_2_));
                    tessellator.func_78381_a();
                    GL11.glTranslatef((float)0.5f, (float)0.5f, (float)0.5f);
                }
                renderer.func_147782_a(0.0, 0.0, 0.0, 1.0, 1.0, 1.0);
                renderer.func_147771_a();
            } else if (j == 38) {
                GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)-0.5f);
                renderer.func_147799_a((BlockHopper)p_147800_1_, 0, 0, 0, 0, true);
                GL11.glTranslatef((float)0.5f, (float)0.5f, (float)0.5f);
            } else {
                FMLRenderAccessLibrary.renderInventoryBlock((RenderBlocks)renderer, (Block)p_147800_1_, (int)p_147800_2_, (int)j);
            }
        } else {
            if (j == 16) {
                p_147800_2_ = 1;
            }
            p_147800_1_.func_149683_g();
            renderer.func_147775_a(p_147800_1_);
            GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)-0.5f);
            tessellator.func_78382_b();
            tessellator.func_78375_b(0.0f, -1.0f, 0.0f);
            renderer.func_147768_a(p_147800_1_, 0.0, 0.0, 0.0, renderer.func_147787_a(p_147800_1_, 0, p_147800_2_));
            tessellator.func_78381_a();
            if (flag && renderer.field_147844_c) {
                int k = p_147800_1_.func_149741_i(p_147800_2_);
                f2 = (float)(k >> 16 & 255) / 255.0f;
                f3 = (float)(k >> 8 & 255) / 255.0f;
                float f4 = (float)(k & 255) / 255.0f;
                GL11.glColor4f((float)(f2 * p_147800_3_), (float)(f3 * p_147800_3_), (float)(f4 * p_147800_3_), (float)1.0f);
            }
            tessellator.func_78382_b();
            tessellator.func_78375_b(0.0f, 1.0f, 0.0f);
            renderer.func_147806_b(p_147800_1_, 0.0, 0.0, 0.0, renderer.func_147787_a(p_147800_1_, 1, p_147800_2_));
            tessellator.func_78381_a();
            if (flag && renderer.field_147844_c) {
                GL11.glColor4f((float)p_147800_3_, (float)p_147800_3_, (float)p_147800_3_, (float)1.0f);
            }
            tessellator.func_78382_b();
            tessellator.func_78375_b(0.0f, 0.0f, -1.0f);
            renderer.func_147761_c(p_147800_1_, 0.0, 0.0, 0.0, renderer.func_147787_a(p_147800_1_, 2, p_147800_2_));
            tessellator.func_78381_a();
            tessellator.func_78382_b();
            tessellator.func_78375_b(0.0f, 0.0f, 1.0f);
            renderer.func_147734_d(p_147800_1_, 0.0, 0.0, 0.0, renderer.func_147787_a(p_147800_1_, 3, p_147800_2_));
            tessellator.func_78381_a();
            tessellator.func_78382_b();
            tessellator.func_78375_b(-1.0f, 0.0f, 0.0f);
            renderer.func_147798_e(p_147800_1_, 0.0, 0.0, 0.0, renderer.func_147787_a(p_147800_1_, 4, p_147800_2_));
            tessellator.func_78381_a();
            tessellator.func_78382_b();
            tessellator.func_78375_b(1.0f, 0.0f, 0.0f);
            renderer.func_147764_f(p_147800_1_, 0.0, 0.0, 0.0, renderer.func_147787_a(p_147800_1_, 5, p_147800_2_));
            tessellator.func_78381_a();
            GL11.glTranslatef((float)0.5f, (float)0.5f, (float)0.5f);
        }
    }
}

