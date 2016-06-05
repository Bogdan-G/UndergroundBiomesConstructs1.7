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
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public interface Reregistrable {
    public void reRegister();

    public static class ItemCall
    implements Reregistrable {
        private NamedItem namer;
        private int ID;
        private Item item;

        public ItemCall(NamedItem _namer, int _ID, Item _item) {
            this.namer = _namer;
            this.ID = _ID;
            this.item = _item;
        }

        public void reRegister() {
            this.namer.reRegister(this.ID, this.item);
        }
    }

    public static class BlockCall
    implements Reregistrable {
        private NamedBlock namer;
        private int ID;
        private Block block;

        public BlockCall(NamedBlock _namer, int _ID, Block _block) {
            this.namer = _namer;
            this.ID = _ID;
            this.block = _block;
        }

        public void reRegister() {
            this.namer.reRegister(this.ID, this.block);
        }
    }

}

