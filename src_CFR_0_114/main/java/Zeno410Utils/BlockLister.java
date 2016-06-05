/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.util.RegistryNamespaced
 */
package Zeno410Utils;

import Zeno410Utils.Zeno410Logger;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Logger;
import net.minecraft.block.Block;
import net.minecraft.util.RegistryNamespaced;

public class BlockLister {
    public static Logger logger = new Zeno410Logger("BlockLister").logger();

    public void listRegistrations() {
        Set keys = Block.field_149771_c.func_148742_b();
        TreeSet sortedKeys = new TreeSet();
        for (Object name2 : Block.field_149771_c.func_148742_b()) {
            sortedKeys.add(name2);
        }
        for (Object name2 : sortedKeys) {
            Object block = Block.field_149771_c.func_82594_a(name2);
            int ID = Block.field_149771_c.func_148757_b(block);
            logger.info("" + ID + "," + name2 + "," + block.toString());
        }
    }
}

