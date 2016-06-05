/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.gen.layer.GenLayer
 *  net.minecraft.world.gen.layer.IntCache
 */
package exterminatorJeff.undergroundBiomes.worldGen;

import exterminatorJeff.undergroundBiomes.api.BiomeGenUndergroundBase;
import exterminatorJeff.undergroundBiomes.api.UndergroundBiomeSet;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerUndergroundBiomes
extends GenLayer {
    private BiomeGenUndergroundBase[] allowedBiomes;

    public GenLayerUndergroundBiomes(long par1, GenLayer par3GenLayer, UndergroundBiomeSet biomeSet) {
        super(par1);
        this.allowedBiomes = biomeSet.allowedBiomes();
        this.field_75909_a = par3GenLayer;
    }

    public int[] func_75904_a(int par1, int par2, int par3, int par4) {
        int[] var6 = IntCache.func_76445_a((int)(par3 * par4));
        for (int var7 = 0; var7 < par4; ++var7) {
            for (int var8 = 0; var8 < par3; ++var8) {
                this.func_75903_a((long)(var8 + par1), (long)(var7 + par2));
                var6[var8 + var7 * par3] = this.allowedBiomes[this.func_75902_a((int)this.allowedBiomes.length)].biomeID;
            }
        }
        return var6;
    }
}

