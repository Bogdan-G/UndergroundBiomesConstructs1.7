/*
 * Decompiled with CFR 0_114.
 */
package Zeno410Utils;

import Zeno410Utils.KeyedRegistry;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class Function<Source, Product> {
    public abstract Product result(Source var1);

    public KeyedRegistry<Source, Product> registry() {
        return new KeyedRegistry<Key, Type>(this);
    }
}

