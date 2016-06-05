/*
 * Decompiled with CFR 0_114.
 */
package Zeno410Utils;

import Zeno410Utils.Trackable;

public interface Mutable<Type>
extends Trackable<Type> {
    public void set(Type var1);

    public Type value();
}

