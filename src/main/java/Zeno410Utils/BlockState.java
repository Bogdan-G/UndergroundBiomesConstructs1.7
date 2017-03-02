
package Zeno410Utils;

import net.minecraft.block.Block;

/**
 *
 * @author Zeno410
 */
public class BlockState implements java.io.Serializable {
    public final Block block;
    public final int metadata;

    public BlockState(Block block, int metadata) {
        this.block = block;
        this.metadata = metadata;
    }

}
