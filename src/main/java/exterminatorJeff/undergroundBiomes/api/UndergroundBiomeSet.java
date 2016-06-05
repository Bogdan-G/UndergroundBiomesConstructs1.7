
package exterminatorJeff.undergroundBiomes.api;

import exterminatorJeff.undergroundBiomes.api.BiomeGenUndergroundBase;
import exterminatorJeff.undergroundBiomes.api.StrataLayer;
/**
 *
 * @author Zeno410
 */
abstract public class UndergroundBiomeSet {
    public final StrataLayer [] [] strataLayers ;

    public final BiomeGenUndergroundBase[] biomeList = new BiomeGenUndergroundBase[256];

    public UndergroundBiomeSet(StrataLayer [] [] strataLayers) {
        this.strataLayers = strataLayers;
    }

    abstract public BiomeGenUndergroundBase [] allowedBiomes();
}
