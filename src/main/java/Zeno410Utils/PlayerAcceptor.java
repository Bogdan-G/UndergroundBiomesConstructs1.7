
package Zeno410Utils;
import net.minecraft.entity.player.EntityPlayer;

/**
 *
 * @author Zeno410
 */
public abstract class PlayerAcceptor<Type> implements java.io.Serializable {
    abstract public void accept(EntityPlayer player,Type data);
}
