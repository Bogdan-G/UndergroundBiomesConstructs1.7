/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.item.Item
 *  net.minecraft.util.ObjectIntIdentityMap
 *  net.minecraft.util.RegistryNamespaced
 */
package exterminatorJeff.undergroundBiomes.constructs.util;

import Zeno410Utils.Accessor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ObjectIntIdentityMap;
import net.minecraft.util.RegistryNamespaced;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class WatchList {
    private HashMap<Object, Watchable> items = new HashMap();
    private static Accessor<RegistryNamespaced, ObjectIntIdentityMap> intRegistryAccess = new Accessor(RegistryNamespaced.class);

    public ArrayList<String> problems() {
        ArrayList<String> result = new ArrayList<String>();
        for (Watchable item : this.items.values()) {
            ProblemReport report = item.problemReport();
            result.add(report.description);
        }
        return result;
    }

    public void redoAsNeeded() {
        for (Watchable item : this.items.values()) {
            item.redoIfNeeded();
        }
    }

    public void add(Block added) {
        if (this.items.containsKey((Object)added)) {
            return;
        }
        this.items.put((Object)added, new WatchableBlock(added));
    }

    public void addWithItem(Block added) {
        if (this.items.containsKey((Object)added)) {
            return;
        }
        this.items.put((Object)added, new WatchableBlock(added));
        int blockID = Block.func_149682_b((Block)added);
        WatchableItem watchableItem = new WatchableItem(Item.func_150899_d((int)blockID));
        this.items.put((Object)watchableItem.item, watchableItem);
    }

    public void addChangeWithItem(int newID, Block added) {
        if (this.items.containsKey((Object)added)) {
            return;
        }
        this.items.put((Object)added, new WatchableBlock(newID, added));
        WatchableItem watchableItem = new WatchableItem(newID, added);
        this.items.put((Object)watchableItem.item, watchableItem);
    }

    public void addChange(int newID, Block added) {
        if (this.items.containsKey((Object)added)) {
            return;
        }
        this.items.put((Object)added, new WatchableBlock(newID, added));
    }

    public void addChange(int newID, Item added) {
        if (this.items.containsKey((Object)added)) {
            return;
        }
        this.items.put((Object)added, new WatchableItem(newID, added));
    }

    public void add(Item added) {
        if (this.items.containsKey((Object)added)) {
            return;
        }
        this.items.put((Object)added, new WatchableItem(added));
    }

    public void clear() {
        this.items = new HashMap();
    }

    public static class WatchableItem
    implements Watchable {
        public final Item item;
        public final int itemID;
        private final String itemName;

        public WatchableItem(Item paired) {
            this.item = paired;
            this.itemID = Item.func_150891_b((Item)this.item);
            if (this.itemID == -1) {
                throw new RuntimeException(this.item.toString() + " currently unregistered");
            }
            this.itemName = Item.field_150901_e.func_148750_c((Object)this.item);
            if (this.itemName == null) {
                throw new RuntimeException(this.item.toString() + " currently unregistered");
            }
        }

        public WatchableItem(int newID, Item paired) {
            this.item = paired;
            this.itemID = newID;
            this.itemName = Item.field_150901_e.func_148750_c((Object)this.item);
            if (this.itemName == null) {
                throw new RuntimeException(this.item.toString() + " currently unregistered");
            }
        }

        public WatchableItem(int newID, Block blockPairing) {
            int oldID = Block.func_149682_b((Block)blockPairing);
            this.item = Item.func_150899_d((int)oldID);
            if (this.item == null) {
                throw new RuntimeException("No current item for " + blockPairing.toString());
            }
            this.itemID = newID;
            this.itemName = Item.field_150901_e.func_148750_c((Object)this.item);
            if (this.itemName == null) {
                throw new RuntimeException(this.item.toString() + " currently unregistered");
            }
        }

        public ProblemReport problemReport() {
            Item identified;
            String result = "";
            boolean problem = false;
            int newID = Item.func_150891_b((Item)this.item);
            if (newID != this.itemID) {
                problem = true;
                if (newID == -1) {
                    result = result + this.item.toString() + " lacks ID";
                }
                if (newID != -1) {
                    result = result + this.item.toString() + " moved from " + this.itemID + " to " + newID;
                }
            }
            if ((identified = Item.func_150899_d((int)this.itemID)) != this.item) {
                problem = true;
                result = identified == null ? result + this.item.toString() + " not retrievable by number" : result + this.item.toString() + " number replaced by " + identified.toString();
            } else {
                result = result + "" + this.itemID + " correctly IDs " + this.item.toString();
            }
            Item named = (Item)Item.field_150901_e.func_82594_a(this.itemName);
            if (named != this.item) {
                problem = true;
                result = named == null ? result + this.item.toString() + " not named" : result + this.item.toString() + " replaced by " + named.toString();
            } else {
                result = result + this.item.toString() + " correctly named " + this.itemName;
            }
            if (problem) {
                return new ProblemReport(true, result);
            }
            return new ProblemReport(false, this.item.toString() + " registered to " + newID);
        }

        public void redoIfNeeded() {
            ProblemReport report = this.problemReport();
            if (report.hasProblem) {
                ObjectIntIdentityMap intRegistry = (ObjectIntIdentityMap)intRegistryAccess.get(Item.field_150901_e);
                intRegistry.func_148746_a((Object)this.item, this.itemID);
            }
        }
    }

    public static class WatchableBlock
    implements Watchable {
        private final Block block;
        private final int blockID;
        private final String blockName;

        public WatchableBlock(Block toWatch) {
            this.block = toWatch;
            this.blockID = Block.func_149682_b((Block)toWatch);
            if (this.blockID == -1) {
                throw new RuntimeException(toWatch.toString() + " currently unregistered");
            }
            this.blockName = Block.field_149771_c.func_148750_c((Object)toWatch);
            if (this.blockName == null) {
                throw new RuntimeException(toWatch.toString() + " currently unregistered");
            }
        }

        public WatchableBlock(int newID, Block toWatch) {
            this.block = toWatch;
            this.blockID = newID;
            this.blockName = Block.field_149771_c.func_148750_c((Object)toWatch);
            if (this.blockName == null) {
                throw new RuntimeException(toWatch.toString() + " currently unregistered");
            }
        }

        public ProblemReport problemReport() {
            Block identified;
            String result = "";
            boolean problem = false;
            int newID = Block.func_149682_b((Block)this.block);
            if (newID != this.blockID) {
                problem = true;
                if (newID == -1) {
                    result = result + this.block.toString() + " lacks ID";
                }
                if (newID != -1) {
                    result = result + this.block.toString() + " moved from " + this.blockID + " to " + newID;
                }
            }
            if ((identified = Block.func_149729_e((int)this.blockID)) != this.block) {
                problem = true;
                result = identified == null ? result + this.block.toString() + " not retrievable by number" : result + this.block.toString() + " number replaced by " + identified.toString();
            } else {
                result = result + "" + this.blockID + " correctly IDs " + this.block.toString();
            }
            Block named = Block.func_149684_b((String)this.blockName);
            if (named != this.block) {
                problem = true;
                result = named == null ? result + this.block.toString() + " not named" : result + this.block.toString() + " replaced by " + named.toString();
            } else {
                result = result + this.block.toString() + " correctly named " + this.blockName;
            }
            if (problem) {
                return new ProblemReport(true, result);
            }
            return new ProblemReport(false, this.block.toString() + " registered to " + newID);
        }

        public void redoIfNeeded() {
            ProblemReport report = this.problemReport();
            if (report.hasProblem) {
                ObjectIntIdentityMap intRegistry = (ObjectIntIdentityMap)intRegistryAccess.get(Block.field_149771_c);
                intRegistry.func_148746_a((Object)this.block, this.blockID);
            }
        }
    }

    public static class ProblemReport {
        public final boolean hasProblem;
        public final String description;

        public ProblemReport(boolean hasProblem, String text) {
            this.hasProblem = hasProblem;
            this.description = text;
        }
    }

    public static interface Watchable {
        public ProblemReport problemReport();

        public void redoIfNeeded();
    }

}

