/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.item.Item
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.RegistryNamespaced
 */
package exterminatorJeff.undergroundBiomes.api;

import exterminatorJeff.undergroundBiomes.api.NamedBlock;
import exterminatorJeff.undergroundBiomes.api.Names;
import java.util.Set;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.util.RegistryNamespaced;

public class NamedItem
extends Names {
    protected int id;
    protected Item item;

    public NamedItem(String internalName) {
        super(internalName);
    }

    public NamedItem(NamedBlock block) {
        this(block.internal());
    }

    public void register(int _id, Item _item) {
        this.reRegister(_id, _item);
    }

    public void reRegister(int _id, Item _item) {
        this.id = _id;
        this.item = _item;
        Item current = Item.func_150899_d((int)_id);
        if (current != _item) {
            Item.field_150901_e.func_148756_a(_id, this.internal(), (Object)_item);
        }
    }

    public Item cachedItem() {
        if (this.item == null) {
            this.item = (Item)Item.field_150901_e.func_82594_a(this.external());
            if (this.item == null) {
                throw new RuntimeException(this.internal() + " has no item");
            }
        }
        return this.item;
    }

    public Item registeredItem() {
        Item result = (Item)Item.field_150901_e.func_82594_a(this.internal());
        if (result == null && (result = (Item)Item.field_150901_e.func_82594_a(this.external())) == null) {
            for (Object key : Item.field_150901_e.func_148742_b()) {
            }
            throw new RuntimeException();
        }
        return result;
    }

    public IIcon registerIcons(IIconRegister iconRegister) {
        return iconRegister.func_94245_a(this.external());
    }

    public boolean matches(Item matched) {
        return this.item.equals((Object)matched);
    }
}

