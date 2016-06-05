/*
 * Decompiled with CFR 0_114.
 */
package exterminatorJeff.undergroundBiomes.constructs.util;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class Consumable<Type> {
    private final Type item;
    private boolean used = false;

    public Consumable(Type _item) {
        this.item = _item;
    }

    public Type use() {
        this.used = true;
        return this.item;
    }

    public Type getRiskingMultipleUse() {
        return this.item;
    }

    public static <ConsumableType> Consumable<ConsumableType> from(ConsumableType toUseOnce) {
        return new Consumable<ConsumableType>(toUseOnce);
    }
}

