package exterminatorJeff.undergroundBiomes.common.block;

import Zeno410Utils.Acceptor;
import Zeno410Utils.Mutable;
import exterminatorJeff.undergroundBiomes.api.NamedBlock;
import exterminatorJeff.undergroundBiomes.api.UBIDs;
import exterminatorJeff.undergroundBiomes.api.UndergroundBiomesSettings;
import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;
import exterminatorJeff.undergroundBiomes.common.block.BlockIgneousStone;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BlockIgneousStoneBrick extends BlockIgneousStone
{
    final float baseHardness;
    public BlockIgneousStoneBrick()
    {
        super(UBIDs.igneousStoneBrickName);
        replaceableByOre = false;
        this.baseHardness = this.blockHardness;
        UndergroundBiomes.instance().settings().brickHardnessMultiplier.informOnChange(hardnessUpdater);
        hardnessUpdater.accept(UndergroundBiomes.instance().settings().brickHardnessMultiplier.value());
    }

    private final Acceptor<Double> hardnessUpdater = new Acceptor<Double>() {

        @Override
        public void accept(Double accepted) {
            setHardness(BlockIgneousStoneBrick.this.baseHardness*accepted.floatValue());
        }

    };

    public ItemStack itemDropped(int metadata, Random random, int fortune, int y)
    {
        return new ItemStack(UBIDs.igneousStoneBrickName.block(), 1, metadata & 7);
    }

    public boolean hasRareDrops()
    {
        return false;
    }

    public String getBlockName(int index) {
        return super.getBlockName(index) + "Brick";
    }

    /*@*//*Override*/
    public Item getItemDropped(int metadata, Random random, int fortune) {
        return Item.getItemById(UBIDs.igneousStoneBrickName.ID());
    }
}
