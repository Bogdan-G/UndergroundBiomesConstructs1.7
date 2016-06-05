package exterminatorJeff.undergroundBiomes.common;

import cpw.mods.fml.common.IFuelHandler;
import exterminatorJeff.undergroundBiomes.api.NamedItem;
import exterminatorJeff.undergroundBiomes.api.UBIDs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class FuelManager implements IFuelHandler
{
    public int getBurnTime(ItemStack fuel)
    {
        if(UBIDs.ligniteCoalName.matches(fuel.getItem()))
        {
            return 200;
        } else {
            return 0;
        }
    }
}
