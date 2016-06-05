/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  biomesoplenty.api.biome.BOPBiome
 *  biomesoplenty.api.biome.BOPInheritedBiome
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeDecorator
 *  net.minecraft.world.biome.BiomeGenBase
 */
package exterminatorJeff.undergroundBiomes.worldGen;

import Zeno410Utils.Accessor;
import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.api.biome.BOPInheritedBiome;
import java.util.HashSet;
import java.util.Iterator;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;

public class CurrentWorldMemento {
    private int remembered;
    private int[] indices = new int[256];
    private World[] worlds = new World[256];
    private Manager manager;

    private CurrentWorldMemento(Manager manager) {
        this.manager = manager;
    }

    private void save() {
        BiomeGenBase[] biomes = BiomeGenBase.func_150565_n();
        for (int i = 0; i < biomes.length; ++i) {
            World currentWorld;
            BiomeGenBase biome = biomes[i];
            if (biome == null) continue;
            if (this.manager.bopHot()) {
                biome = this.manager.bopAdjustedBiome(biome);
            }
            if ((currentWorld = biome.field_76760_I.field_76815_a) == null) continue;
            this.worlds[this.remembered] = currentWorld;
            this.indices[this.remembered++] = i;
            biome.field_76760_I.field_76815_a = null;
        }
    }

    void restore() {
        BiomeGenBase[] biomes = BiomeGenBase.func_150565_n();
        for (int i = 0; i < this.remembered; ++i) {
            BiomeGenBase biome = biomes[this.indices[i]];
            if (this.manager.bopHot()) {
                biome = this.manager.bopAdjustedBiome(biome);
            }
            biome.field_76760_I.field_76815_a = this.worlds[i];
        }
        this.remembered = 0;
        this.manager.release(this);
    }

    static class Manager {
        private HashSet<CurrentWorldMemento> available = new HashSet();
        private boolean bopHot;
        private Accessor<BOPInheritedBiome, BiomeGenBase> inheritedBiomeAccess;

        public Manager() {
            try {
                reference bopBiomeclass = BOPBiome.class;
                this.bopHot = true;
                this.inheritedBiomeAccess = new Accessor(BiomeGenBase.class);
            }
            catch (NoClassDefFoundError e) {
                this.bopHot = false;
            }
        }

        public BiomeGenBase bopAdjustedBiome(BiomeGenBase source) {
            if (source == null) {
                return source;
            }
            if (source instanceof BOPInheritedBiome) {
                return this.inheritedBiomeAccess.get((BOPInheritedBiome)source);
            }
            return source;
        }

        public boolean bopHot() {
            return this.bopHot;
        }

        private void release(CurrentWorldMemento freed) {
            this.available.add(freed);
        }

        CurrentWorldMemento memento() {
            CurrentWorldMemento result;
            if (this.available.size() > 0) {
                result = this.available.iterator().next();
                this.available.remove(result);
            } else {
                result = new CurrentWorldMemento(this);
            }
            result.save();
            return result;
        }
    }

}

