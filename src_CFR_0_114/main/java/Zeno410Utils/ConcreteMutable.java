/*
 * Decompiled with CFR 0_114.
 */
package Zeno410Utils;

import Zeno410Utils.Acceptor;
import Zeno410Utils.Mutable;
import Zeno410Utils.Trackers;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class ConcreteMutable<Type>
implements Mutable<Type> {
    private Type data;
    private final Trackers<Type> trackers = new Trackers();

    public ConcreteMutable(Type initialValue) {
        this.set(initialValue);
    }

    public ConcreteMutable() {
    }

    @Override
    public void set(Type newValue) {
        if (this.data == null && newValue != null || this.data.equals(this.value())) {
            this.data = newValue;
            this.trackers.update(this.data);
        }
    }

    @Override
    public Type value() {
        return this.data;
    }

    @Override
    public void informOnChange(Acceptor<Type> target) {
        this.trackers.informOnChange(target);
    }

    @Override
    public void stopInforming(Acceptor<Type> target) {
        this.trackers.stopInforming(target);
    }
}

