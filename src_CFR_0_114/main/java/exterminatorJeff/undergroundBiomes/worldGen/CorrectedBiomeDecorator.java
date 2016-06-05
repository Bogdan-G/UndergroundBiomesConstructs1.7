/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockFlower
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeDecorator
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.gen.feature.WorldGenFlowers
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package exterminatorJeff.undergroundBiomes.worldGen;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenerator;

public class CorrectedBiomeDecorator
extends BiomeDecorator {
    public CorrectedBiomeDecorator(BiomeDecorator toCorrect) {
        this.field_76826_u = toCorrect.field_76826_u;
        this.field_76807_J = toCorrect.field_76807_J;
        this.field_76800_F = toCorrect.field_76800_F;
        this.field_76824_w = toCorrect.field_76824_w;
        this.field_76809_f = toCorrect.field_76809_f;
        this.field_76806_I = toCorrect.field_76806_I;
        this.field_76821_k = toCorrect.field_76821_k;
        this.field_76804_C = toCorrect.field_76804_C;
        this.field_76817_o = toCorrect.field_76817_o;
        this.field_76823_i = toCorrect.field_76823_i;
        this.field_76802_A = toCorrect.field_76802_A;
        this.field_76808_K = toCorrect.field_76808_K;
        this.field_76819_m = toCorrect.field_76819_m;
        this.field_76803_B = toCorrect.field_76803_B;
        this.field_76822_h = toCorrect.field_76822_h;
        this.field_76820_j = toCorrect.field_76820_j;
        this.field_76818_l = toCorrect.field_76818_l;
        this.field_76831_p = toCorrect.field_76831_p;
        this.field_76828_s = toCorrect.field_76828_s;
        this.field_76827_t = toCorrect.field_76827_t;
        this.field_76798_D = toCorrect.field_76798_D;
        this.field_76813_b = toCorrect.field_76813_b;
        this.field_76816_n = toCorrect.field_76816_n;
        this.field_76825_v = toCorrect.field_76825_v;
        this.field_76799_E = toCorrect.field_76799_E;
        this.field_76810_g = toCorrect.field_76810_g;
        this.field_76801_G = toCorrect.field_76801_G;
        this.field_76805_H = toCorrect.field_76805_H;
        this.field_76832_z = toCorrect.field_76832_z;
        this.field_76834_x = toCorrect.field_76834_x;
        this.field_76833_y = toCorrect.field_76833_y;
        if (toCorrect.field_150514_p != null) {
            this.field_150514_p = toCorrect.field_150514_p;
        }
    }

    public void func_150512_a(World p_150512_1_, Random p_150512_2_, BiomeGenBase p_150512_3_, int p_150512_4_, int p_150512_5_) {
        World wasDecorating = this.field_76815_a;
        Random randomizer = this.field_76813_b;
        this.field_76815_a = null;
        if (this.field_150514_p == null) {
            this.field_150514_p = new WorldGenFlowers((Block)Blocks.field_150327_N);
        }
        if (this.field_150514_p == null) {
            this.field_150514_p = new WorldGenFlowers((Block)Blocks.field_150327_N);
        }
        super.func_150512_a(p_150512_1_, p_150512_2_, p_150512_3_, p_150512_4_, p_150512_5_);
        this.field_76815_a = wasDecorating;
        this.field_76813_b = randomizer;
    }
}

