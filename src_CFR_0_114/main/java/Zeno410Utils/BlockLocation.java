/*
 * Decompiled with CFR 0_114.
 */
package Zeno410Utils;

public class BlockLocation {
    private int x;
    private int y;
    private int z;

    public static BlockLocation fetch(int x, int y, int z) {
        return new BlockLocation(x, y, z);
    }

    public BlockLocation(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public final int x() {
        return this.x;
    }

    public final int y() {
        return this.y;
    }

    public final int z() {
        return this.z;
    }

    void setX(int newX) {
        this.x = newX;
    }

    void setY(int newY) {
        this.y = newY;
    }

    void setZ(int newZ) {
        this.z = newZ;
    }

    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.x;
        hash = 71 * hash + this.y;
        hash = 71 * hash + this.z;
        return hash;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof BlockLocation)) {
            return false;
        }
        BlockLocation other = (BlockLocation)obj;
        if (this.x != other.x()) {
            return false;
        }
        if (this.y != other.y()) {
            return false;
        }
        if (this.z != other.z()) {
            return false;
        }
        return true;
    }
}

