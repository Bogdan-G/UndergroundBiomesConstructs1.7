/*
 * Decompiled with CFR 0_114.
 */
package exterminatorJeff.undergroundBiomes.constructs.util;

import Zeno410Utils.Zeno410Logger;
import java.util.HashSet;
import java.util.logging.Logger;

public class DimensionSet {
    private final boolean ignore;
    private HashSet<Integer> members = new HashSet();
    private static Logger logger = new Zeno410Logger("DimensionSet").logger();

    public boolean ignore() {
        return this.ignore;
    }

    private DimensionSet(String includeDimensions) {
        if (includeDimensions.equals("*")) {
            this.ignore = true;
        } else {
            this.ignore = false;
            for (String v : includeDimensions.split(",")) {
                this.members.add(Integer.parseInt(v));
            }
        }
    }

    public boolean contains(Integer dimension) {
        return this.members.contains(dimension);
    }

    public static class Exclude
    extends DimensionSet {
        public Exclude(String includeDimensions) {
            super(includeDimensions);
        }
    }

    public static class Include
    extends DimensionSet {
        public Include(String includeDimensions) {
            super(includeDimensions);
        }

        public boolean isIncluded(Integer dimension, Exclude exclusions) {
            if (this.ignore()) {
                return !exclusions.contains(dimension);
            }
            return this.contains(dimension);
        }
    }

}

