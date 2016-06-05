/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.profiler.Profiler
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldProvider
 *  net.minecraft.world.WorldSettings
 *  net.minecraft.world.chunk.IChunkProvider
 *  net.minecraft.world.storage.ISaveHandler
 */
package exterminatorJeff.undergroundBiomes.constructs.util;

import net.minecraft.entity.Entity;
import net.minecraft.profiler.Profiler;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.storage.ISaveHandler;

public class ShamWorld
extends World {
    private int shamMetadata;
    static String name = "Sham World";

    public ShamWorld(WorldSettings settings) {
        super(null, name, null, settings, null);
    }

    public int func_72805_g(int par1, int par2, int par3) {
        return this.shamMetadata;
    }

    protected int func_152379_p() {
        return 256;
    }

    protected IChunkProvider func_72970_h() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Entity func_73045_a(int arg0) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

