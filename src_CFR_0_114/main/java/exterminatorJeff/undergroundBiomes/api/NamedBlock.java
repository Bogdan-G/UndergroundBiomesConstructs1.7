/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.util.RegistryNamespaced
 */
package exterminatorJeff.undergroundBiomes.api;

import cpw.mods.fml.common.registry.GameRegistry;
import exterminatorJeff.undergroundBiomes.api.Names;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.RegistryNamespaced;

public class NamedBlock
extends Names {
    protected int id;
    public Block block;
    public static final String modid = "undergroundBiomes";

    public NamedBlock(String internalName) {
        super(internalName);
    }

    public void gameRegister(Block _block, Class itemClass) {
        this.block = _block;
        GameRegistry.registerBlock((Block)this.block, (Class)itemClass, (String)this.internal());
    }

    public void register(int _id, Block _block) {
        if (this.block != null) {
            throw this.duplicateRegistry();
        }
        this.reRegister(_id, _block);
    }

    public void reRegister(int _id, Block _block) {
        this.id = _id;
        this.block = _block;
        this.block.func_149663_c(this.external());
        Block current = Block.func_149729_e((int)_id);
        if (current != this.block) {
            if (current != null) {
                throw new RuntimeException(this.internal() + " has been replaced by " + current.toString());
            }
            Block.field_149771_c.func_148756_a(this.id, this.internal(), (Object)_block);
        }
    }

    public Block block() {
        return this.block;
    }

    public boolean matches(Item compared) {
        if (compared instanceof ItemBlock) {
            return ((ItemBlock)compared).field_150939_a.equals((Object)this.block);
        }
        return false;
    }

    public boolean matches(Block compared) {
        return compared.equals((Object)this.block());
    }

    public int ID() {
        return Block.func_149682_b((Block)this.block());
    }

    public Item matchingItem(Block block) {
        return Item.func_150899_d((int)Block.func_149682_b((Block)block));
    }
}

