/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.IRecipe
 */
package exterminatorJeff.undergroundBiomes.constructs.block;

import exterminatorJeff.undergroundBiomes.common.block.BlockMetadataBase;
import exterminatorJeff.undergroundBiomes.constructs.util.UndergroundBiomesBlock;
import exterminatorJeff.undergroundBiomes.constructs.util.UndergroundBiomesBlockList;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class UBConstructGroup {
    public Integer constructID;
    public BlockMetadataBase baseBlock;
    public final String name;
    public Block construct;

    public UBConstructGroup(String _name) {
        this.name = _name;
    }

    public void define(int _constructID) {
        this.constructID = _constructID;
        this.construct = this.definedBlock();
        String test = this.construct.toString();
    }

    public ArrayList<IRecipe> recipes() {
        ArrayList<IRecipe> result = new ArrayList<IRecipe>();
        for (int ubIndex = 0; ubIndex < 56; ++ubIndex) {
            IRecipe added = this.recipe(this.productItemDefiner(ubIndex), new StoneItemDefiner(ubIndex));
            if (added != null) {
                result.add(added);
            }
            if ((added = this.rescueRecipe(this.productItemDefiner(ubIndex), new StoneItemDefiner(ubIndex))) == null) continue;
            result.add(added);
        }
        return result;
    }

    abstract IRecipe recipe(ProductItemDefiner var1, StoneItemDefiner var2);

    abstract IRecipe rescueRecipe(ProductItemDefiner var1, StoneItemDefiner var2);

    abstract Block definedBlock();

    public BlockMetadataBase baseBlock() {
        return this.baseBlock;
    }

    public ProductItemDefiner productItemDefiner(int index) {
        return new ProductItemDefiner(index);
    }

    public class ProductItemDefiner {
        final int stoneIndex;

        ProductItemDefiner(int _stoneIndex) {
            this.stoneIndex = _stoneIndex;
        }

        public ItemStack stackOf(int items) {
            return new ItemStack(UBConstructGroup.this.construct, items, this.stoneIndex);
        }

        public ItemStack one() {
            return this.stackOf(1);
        }
    }

    class StoneItemDefiner {
        final int stoneIndex;

        StoneItemDefiner(int _stoneIndex) {
            this.stoneIndex = _stoneIndex;
        }

        public final ItemStack stackOf(int items) {
            UndergroundBiomesBlock stone = this.ubBlock();
            return new ItemStack((Block)stone.ubBlock, items, stone.metadata);
        }

        public final UndergroundBiomesBlock ubBlock() {
            return UndergroundBiomesBlockList.indexed(this.stoneIndex);
        }

        public final ItemStack one() {
            return this.stackOf(1);
        }
    }

}

