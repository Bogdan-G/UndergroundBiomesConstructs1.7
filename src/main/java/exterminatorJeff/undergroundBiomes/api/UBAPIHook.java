package exterminatorJeff.undergroundBiomes.api;

import exterminatorJeff.undergroundBiomes.api.UBDimensionalStrataColumnProvider;
import exterminatorJeff.undergroundBiomes.api.UBOreTexturizer;
import exterminatorJeff.undergroundBiomes.api.UBSetProviderRegistry;
/**
 *
 * @author Zeno410
 */
public class UBAPIHook implements java.io.Serializable {
    public static final UBAPIHook ubAPIHook = new UBAPIHook();
    public UBDimensionalStrataColumnProvider dimensionalStrataColumnProvider; // set in the main Underground Biomes
    public UBOreTexturizer ubOreTexturizer;
    public UBSetProviderRegistry ubSetProviderRegistry;
}
