package exterminatorJeff.undergroundBiomes.common.block;

import Zeno410Utils.Acceptor;
import Zeno410Utils.Mutable;
import exterminatorJeff.undergroundBiomes.api.NamedBlock;
import exterminatorJeff.undergroundBiomes.api.UBIDs;
import exterminatorJeff.undergroundBiomes.api.UndergroundBiomesSettings;
import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;
import exterminatorJeff.undergroundBiomes.common.block.BlockMetamorphicStone;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class BlockMetamorphicCobblestone extends BlockMetamorphicStone
{
    final float baseHardness;
    public BlockMetamorphicCobblestone() {
        super(UBIDs.metamorphicCobblestoneName);
        baseHardness = this.blockHardness;
        this.setHardness(this.blockHardness*1.333333f);
        replaceableByOre = false;
        UndergroundBiomes.instance().settings().cobbleHardnessMultiplier.informOnChange(hardnessUpdater);
        hardnessUpdater.accept(UndergroundBiomes.instance().settings().cobbleHardnessMultiplier.value());
    }

    private final Acceptor<Double> hardnessUpdater = new Acceptor<Double>() {

        @Override
        public void accept(Double accepted) {
            setHardness(BlockMetamorphicCobblestone.this.baseHardness*accepted.floatValue());
        }

    };
    /*@*//*Override*/
    public ItemStack itemDropped(int metadata, Random random, int fortune, int y){
        return new ItemStack(UBIDs.metamorphicCobblestoneName.block(), 1, metadata & 7);
    }

    public boolean hasRareDrops(){
        return false;
    }

    public String getBlockName(int index){
        return super.getBlockName(index) + "Cobble";
    }
}
