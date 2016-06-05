/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.RegistryNamespaced
 */
package exterminatorJeff.undergroundBiomes.network;

import Zeno410Utils.Streamer;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.RegistryNamespaced;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class ItemStackStreamer
extends Streamer<ItemStack> {
    @Override
    public ItemStack readFrom(DataInput input) throws IOException {
        String itemName = input.readUTF();
        int itemCount = input.readInt();
        int itemDamage = input.readInt();
        return new ItemStack((Item)Item.field_150901_e.func_82594_a(itemName), itemCount, itemDamage);
    }

    @Override
    public void writeTo(ItemStack written, DataOutput output) throws IOException {
        output.writeUTF(Item.field_150901_e.func_148750_c((Object)written.func_77973_b()));
        output.writeInt(written.field_77994_a);
        output.writeInt(written.func_77960_j());
    }
}

