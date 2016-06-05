/*
 * Decompiled with CFR 0_114.
 */
package Zeno410Utils;

import Zeno410Utils.Acceptor;

public interface Trackable<Type> {
    public void informOnChange(Acceptor<Type> var1);

    public void stopInforming(Acceptor<Type> var1);
}

