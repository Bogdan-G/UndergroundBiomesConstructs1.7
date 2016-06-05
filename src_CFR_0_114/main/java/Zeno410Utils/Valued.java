/*
 * Decompiled with CFR 0_114.
 */
package Zeno410Utils;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class Valued<Type>
implements Comparable<Valued<Type>> {
    private double value;
    private Type item;

    public Valued(double _count, Type _item) {
        this.value = _count;
        this.item = _item;
    }

    public double vanlue() {
        return this.value;
    }

    public Type item() {
        return this.item;
    }

    @Override
    public int compareTo(Valued<Type> arg0) {
        if (this.value > arg0.value) {
            return 1;
        }
        if (this.value == arg0.value) {
            return 0;
        }
        return -1;
    }
}

