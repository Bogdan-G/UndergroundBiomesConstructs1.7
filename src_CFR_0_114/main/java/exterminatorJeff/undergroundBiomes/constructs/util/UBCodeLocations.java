/*
 * Decompiled with CFR 0_114.
 */
package exterminatorJeff.undergroundBiomes.constructs.util;

import Zeno410Utils.BlockLocation;
import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;
import exterminatorJeff.undergroundBiomes.constructs.util.UndergroundBiomesBlock;
import exterminatorJeff.undergroundBiomes.constructs.util.UndergroundBiomesBlockList;
import java.util.HashMap;

public class UBCodeLocations {
    private HashMap<BlockLocation, UndergroundBiomesBlock> stored = new HashMap();

    public void add(int x, int y, int z, UndergroundBiomesBlock ubBlock) {
        BlockLocation location = BlockLocation.fetch(x, y, z);
        if (!this.stored.containsKey(location) || UndergroundBiomes.crashOnProblems()) {
            // empty if block
        }
        this.stored.put(location, ubBlock);
    }

    public UndergroundBiomesBlock get(int x, int y, int z) {
        UndergroundBiomesBlock result = this.stored.get(BlockLocation.fetch(x, y, z));
        if (result == null) {
            if (UndergroundBiomes.crashOnProblems()) {
                throw new RuntimeException();
            }
            return UndergroundBiomesBlockList.indexed(0);
        }
        return result;
    }

    public void remove(int x, int y, int z) {
        this.stored.remove(BlockLocation.fetch(x, y, z));
    }

    public void clear() {
        if (!this.stored.isEmpty() && !UndergroundBiomes.crashOnProblems()) {
            this.stored.clear();
        }
    }
}

