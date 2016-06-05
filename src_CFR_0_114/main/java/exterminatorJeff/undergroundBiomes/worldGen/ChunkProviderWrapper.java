/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EnumCreatureType
 *  net.minecraft.util.IProgressUpdate
 *  net.minecraft.world.ChunkPosition
 *  net.minecraft.world.World
 *  net.minecraft.world.chunk.Chunk
 *  net.minecraft.world.chunk.IChunkProvider
 */
package exterminatorJeff.undergroundBiomes.worldGen;

import Zeno410Utils.PlaneLocation;
import Zeno410Utils.Zeno410Logger;
import exterminatorJeff.undergroundBiomes.worldGen.CurrentWorldMemento;
import java.util.List;
import java.util.WeakHashMap;
import java.util.logging.Logger;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;

public class ChunkProviderWrapper
implements IChunkProvider {
    private IChunkProvider wrappee;
    private final CurrentWorldMemento.Manager currentWorldManager = new CurrentWorldMemento.Manager();
    private WeakHashMap<PlaneLocation, Chunk> providedChunks = new WeakHashMap();
    public static Logger logger = new Zeno410Logger("ChunkProviderWrapper").logger();

    public ChunkProviderWrapper(IChunkProvider toWrap) {
        this.wrappee = toWrap;
    }

    public IChunkProvider wrappee() {
        return this.wrappee;
    }

    public boolean func_73149_a(int i, int j) {
        return this.wrappee.func_73149_a(i, j);
    }

    public Chunk func_73154_d(int i, int j) {
        Chunk result = this.wrappee.func_73154_d(i, j);
        return result;
    }

    public Chunk func_73158_c(int i, int j) {
        return this.wrappee.func_73158_c(i, j);
    }

    public void func_73153_a(IChunkProvider ichunkprovider, int i, int j) {
        try {
            this.wrappee.func_73153_a(ichunkprovider, i, j);
        }
        catch (RuntimeException e) {
            CurrentWorldMemento memento = this.currentWorldManager.memento();
            try {
                this.wrappee.func_73153_a(ichunkprovider, i, j);
            }
            catch (Exception e2) {
                throw e;
            }
            memento.restore();
        }
    }

    public boolean func_73151_a(boolean flag, IProgressUpdate iprogressupdate) {
        return this.wrappee.func_73151_a(flag, iprogressupdate);
    }

    public boolean func_73156_b() {
        return this.wrappee.func_73156_b();
    }

    public boolean func_73157_c() {
        return this.wrappee.func_73157_c();
    }

    public String func_73148_d() {
        return this.wrappee.func_73148_d();
    }

    public List func_73155_a(EnumCreatureType enumcreaturetype, int i, int j, int k) {
        return this.wrappee.func_73155_a(enumcreaturetype, i, j, k);
    }

    public ChunkPosition func_147416_a(World world, String s, int i, int j, int k) {
        return this.wrappee.func_147416_a(world, s, i, j, k);
    }

    public int func_73152_e() {
        return this.wrappee.func_73152_e();
    }

    public void func_82695_e(int i, int j) {
        this.wrappee.func_82695_e(i, j);
    }

    public void func_104112_b() {
        this.wrappee.func_104112_b();
    }
}

