/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 */
package exterminatorJeff.undergroundBiomes.api;

import Zeno410Utils.Zeno410Logger;
import exterminatorJeff.undergroundBiomes.api.NamedBlock;
import java.util.logging.Logger;
import net.minecraft.block.Block;

public class NamedSlabPair {
    public static final Logger logger = new Zeno410Logger("NamedSlabPair").logger();
    public final NamedBlock half;
    public final NamedBlock full;

    public NamedSlabPair(NamedBlock material) {
        this.half = new NamedSlab(material.internal() + "HalfSlab");
        this.full = new NamedSlab(material.internal() + "FullSlab");
    }

    public static class NamedSlab
    extends NamedBlock {
        public NamedSlab(String name) {
            super(name);
        }

        public Block block() {
            Block result = Block.func_149684_b((String)this.internal());
            if (result == null) {
                result = Block.func_149684_b((String)this.external());
            }
            return result;
        }
    }

}

