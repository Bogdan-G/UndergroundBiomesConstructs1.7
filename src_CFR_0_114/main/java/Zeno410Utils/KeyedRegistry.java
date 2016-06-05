/*
 * Decompiled with CFR 0_114.
 */
package Zeno410Utils;

import Zeno410Utils.Function;
import java.util.HashMap;
import java.util.Set;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class KeyedRegistry<Key, Type> {
    private HashMap<Key, Type> items = new HashMap();
    private final Function<Key, Type> maker;

    public KeyedRegistry(Function<Key, Type> maker) {
        this.maker = maker;
    }

    public Type item(Key key) {
        Type result = this.items.get(key);
        if (result != null) {
            return result;
        }
        result = this.maker.result(key);
        this.items.put(key, result);
        return result;
    }

    public Iterable<Key> keys() {
        return this.items.keySet();
    }

    public void clear() {
        this.items = new HashMap();
    }

    public void remove(Key toRemove) {
        this.items.remove(toRemove);
    }

    public int size() {
        return this.items.size();
    }
}

