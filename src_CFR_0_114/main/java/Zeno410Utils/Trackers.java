/*
 * Decompiled with CFR 0_114.
 */
package Zeno410Utils;

import Zeno410Utils.Acceptor;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class Trackers<Type> {
    private HashMap<Trackers<Type>, WeakReference<Acceptor<Type>>> trackers = new HashMap();

    public void informOnChange(Acceptor<Type> tracker) {
        for (Key key : this.trackers.keySet()) {
            Acceptor<Type> existing = this.trackers.get(key).get();
            if (tracker != existing) continue;
            return;
        }
        this.trackers.put((Trackers<Type>)((Object)new Key()), new WeakReference<Acceptor<Type>>(tracker));
    }

    public int size() {
        return this.trackers.size();
    }

    public void update(Type toUpdate) {
        ArrayList<Key> toDelete = new ArrayList<Key>();
        for (Key key2 : this.trackers.keySet()) {
            Acceptor<Type> tracker = this.trackers.get(key2).get();
            if (tracker == null) {
                toDelete.add(key2);
                continue;
            }
            tracker.accept(toUpdate);
        }
        for (Key key : toDelete) {
            this.trackers.remove(key);
        }
    }

    public void stopInforming(Acceptor<Type> tracker) {
        for (Key key : this.trackers.keySet()) {
            Acceptor<Type> existing = this.trackers.get(key).get();
            if (tracker != existing) continue;
            this.trackers.put((Trackers<Type>)((Object)key), (WeakReference<Acceptor<Type>>)new WeakReference<Object>(null));
            return;
        }
    }

    private class Key {
        private Key() {
        }
    }

}

