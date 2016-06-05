/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.ChunkCoordinates
 */
package Zeno410Utils;

import net.minecraft.util.ChunkCoordinates;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class PlaneLocation {
    public final int x;
    public final int z;

    public PlaneLocation(int _x, int _z) {
        this.x = _x;
        this.z = _z;
    }

    public PlaneLocation(ChunkCoordinates coordinates) {
        this(coordinates.field_71574_a, coordinates.field_71573_c);
    }

    public float distance(PlaneLocation location) {
        return (float)(this.x - location.x) * (float)(this.x - location.x) + (float)(this.z - location.z) * (float)(this.z - location.z);
    }

    public <Type extends Provider> Type closest(Iterable<Type> choices) {
        Provider result = null;
        float bestDistance = Float.MAX_VALUE;
        for (Provider tested : choices) {
            float distance = this.distance(tested.planeLocation());
            if (distance >= bestDistance) continue;
            result = tested;
        }
        return (Type)result;
    }

    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.x;
        hash = 71 * hash + this.z;
        return hash;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        PlaneLocation other = (PlaneLocation)obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.z != other.z) {
            return false;
        }
        return true;
    }

    public abstract class Provider {
        public abstract PlaneLocation planeLocation();
    }

}

