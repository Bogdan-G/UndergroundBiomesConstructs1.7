/*
 * Decompiled with CFR 0_114.
 */
package exterminatorJeff.undergroundBiomes.network;

import Zeno410Utils.Acceptor;
import Zeno410Utils.Named;
import java.util.HashMap;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class MessageManager<Type>
extends Acceptor<Named<Type>> {
    private HashMap<String, Type> waitingPackets = new HashMap();
    private HashMap<String, Acceptor<Type>> targets = new HashMap();
    private HashMap<String, Acceptor<Type>> managers = new HashMap();

    @Override
    public void accept(Named<Type> accepted) {
        this.receive(accepted.name, accepted.object);
    }

    public void receive(String tag, Type value) {
        MessageManager messageManager = this;
        synchronized (messageManager) {
            if (this.managers.containsKey(tag)) {
                this.managers.get(tag).accept(value);
                return;
            }
            if (this.targets.containsKey(tag)) {
                this.targets.get(tag).accept(value);
                this.targets.remove(tag);
                return;
            }
            this.waitingPackets.put(tag, value);
        }
    }

    public void setManageAll(String tag, Acceptor<Type> manager) {
        MessageManager messageManager = this;
        synchronized (messageManager) {
            if (this.targets.containsKey(tag)) {
                throw new RuntimeException("Duplicate manager");
            }
            if (this.managers.containsKey(tag)) {
                throw new RuntimeException("Duplicate manager");
            }
            this.managers.put(tag, manager);
            if (this.waitingPackets.containsKey(tag)) {
                manager.accept(this.waitingPackets.remove(tag));
            }
        }
    }

    public void setManageNext(String tag, Acceptor<Type> manager) {
        MessageManager messageManager = this;
        synchronized (messageManager) {
            if (this.targets.containsKey(tag)) {
                throw new RuntimeException("Duplicate manager");
            }
            if (this.managers.containsKey(tag)) {
                throw new RuntimeException("Duplicate manager");
            }
            if (this.waitingPackets.containsKey(tag)) {
                manager.accept(this.waitingPackets.remove(tag));
            } else {
                this.targets.put(tag, manager);
            }
        }
    }

    public void resetManageNext(String tag, Acceptor<Type> manager) {
        MessageManager messageManager = this;
        synchronized (messageManager) {
            if (this.managers.containsKey(tag)) {
                throw new RuntimeException("Duplicate manager");
            }
            if (this.waitingPackets.containsKey(tag)) {
                manager.accept(this.waitingPackets.remove(tag));
            } else {
                this.targets.put(tag, manager);
            }
        }
    }
}

