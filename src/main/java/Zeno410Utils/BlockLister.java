
package Zeno410Utils;

import Zeno410Utils.Zeno410Logger;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Logger;
import net.minecraft.block.Block;
import net.minecraft.util.RegistryNamespaced;

/**
 *
 * @author Zeno410
 */
public class BlockLister {
    public static Logger logger = new Zeno410Logger("BlockLister").logger();

    public void listRegistrations() {
        Set<Object> keys = Block.blockRegistry.getKeys();
        TreeSet<Object> sortedKeys = new TreeSet<Object>();
        for (Object name: Block.blockRegistry.getKeys()) {
            sortedKeys.add(name);
        }
        for (Object name: sortedKeys) {
            Object block = Block.blockRegistry.getObject(name);
            int ID = Block.blockRegistry.getIDForObject(block);
            cpw.mods.fml.common.FMLLog.info(""+ID+","+name+","+block.toString());
        }

    }

}
