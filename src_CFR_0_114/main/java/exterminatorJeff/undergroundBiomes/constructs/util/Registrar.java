/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.item.Item
 */
package exterminatorJeff.undergroundBiomes.constructs.util;

import exterminatorJeff.undergroundBiomes.api.NamedBlock;
import exterminatorJeff.undergroundBiomes.api.NamedItem;
import exterminatorJeff.undergroundBiomes.constructs.util.Reregistrable;
import java.util.HashSet;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class Registrar {
    public static final Registrar instance = new Registrar();
    private Set<Reregistrable> items = new HashSet<Reregistrable>();

    public void reRegister() {
        for (Reregistrable item : this.items) {
            item.reRegister();
        }
    }

    public void add(Reregistrable toAdd) {
        this.items.add(toAdd);
    }

    public void add(NamedBlock namer, int ID, Block block) {
        this.add(new Reregistrable.BlockCall(namer, ID, block));
    }

    public void add(NamedItem namer, int ID, Item item) {
        this.add(new Reregistrable.ItemCall(namer, ID, item));
    }
}

