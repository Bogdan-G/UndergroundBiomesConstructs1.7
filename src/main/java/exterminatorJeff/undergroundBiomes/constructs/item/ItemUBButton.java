package exterminatorJeff.undergroundBiomes.constructs.item;

/**
 *
 * @author zeno410
 */

import exterminatorJeff.undergroundBiomes.api.NamedItem;
import exterminatorJeff.undergroundBiomes.api.UBIDs;
import exterminatorJeff.undergroundBiomes.constructs.item.ItemUndergroundBiomesConstruct;
import net.minecraft.block.Block;

public class ItemUBButton extends ItemUndergroundBiomesConstruct{

    public ItemUBButton(Block block) {
        this(block,UBIDs.UBButtonItemName);
    }

    public ItemUBButton(Block block,NamedItem namer){
        super(block,namer);
    }

    public String groupName() {return "button";}

    public int metadataForPlacement(int metadata) {return metadata;}
}
