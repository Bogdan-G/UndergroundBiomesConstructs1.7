/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.gen.layer.GenLayer
 *  net.minecraft.world.gen.layer.IntCache
 */
package exterminatorJeff.undergroundBiomes.worldGen;

import exterminatorJeff.undergroundBiomes.api.BiomeGenUndergroundBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerStrataColumn
extends GenLayer {
    public final int biomeCount;

    public GenLayerStrataColumn(long par1, BiomeGenUndergroundBase[] biomeList) {
        super(par1);
        int i = 0;
        for (i = 0; i < biomeList.length && biomeList[i] != null; ++i) {
        }
        this.biomeCount = i;
    }

    public int[] func_75904_a(int par1, int par2, int par3, int par4) {
        int[] aint = IntCache.func_76445_a((int)(par3 * par4));
        for (int i1 = 0; i1 < par4; ++i1) {
            for (int j1 = 0; j1 < par3; ++j1) {
                this.func_75903_a((long)(par1 + j1), (long)(par2 + i1));
                aint[j1 + i1 * par3] = this.func_75902_a(this.biomeCount);
            }
        }
        if (par1 > - par3 && par1 <= 0 && par2 > - par4 && par2 <= 0) {
            aint[- par1 + (- par2) * par3] = 1;
        }
        return aint;
    }
}

