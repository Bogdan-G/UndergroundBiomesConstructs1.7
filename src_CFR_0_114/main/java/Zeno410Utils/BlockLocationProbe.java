/*
 * Decompiled with CFR 0_114.
 */
package Zeno410Utils;

import Zeno410Utils.BlockLocation;

public class BlockLocationProbe
extends BlockLocation {
    public BlockLocationProbe(int x, int y, int z) {
        super(x, y, z);
    }

    public void setX(int newX) {
        super.setX(newX);
    }

    public void setY(int newY) {
        super.setY(newY);
    }

    public void setZ(int newZ) {
        super.setZ(newZ);
    }

    public BlockLocation forStorage() {
        return new BlockLocation(this.x(), this.y(), this.z());
    }
}

