package exterminatorJeff.undergroundBiomes.constructs.item;
import exterminatorJeff.undergroundBiomes.api.NamedItem;
import exterminatorJeff.undergroundBiomes.api.UBIDs;
import exterminatorJeff.undergroundBiomes.constructs.item.ItemUndergroundBiomesConstruct;
import net.minecraft.block.Block;

/**
 *
 * @author Zeno410
 */
public class ItemUBWall extends ItemUndergroundBiomesConstruct {

    public ItemUBWall(Block block) {
        this(block,UBIDs.UBWallsItemName);
    }
    public ItemUBWall(Block block,NamedItem namer){
        super(block,namer);
    }
    
    public String groupName() {return "wall";}

}
