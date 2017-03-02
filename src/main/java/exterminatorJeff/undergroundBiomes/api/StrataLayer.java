package exterminatorJeff.undergroundBiomes.api;

import exterminatorJeff.undergroundBiomes.api.NamedBlock;
import exterminatorJeff.undergroundBiomes.api.UBStoneCodes;

public class StrataLayer implements java.io.Serializable {
    public final NamedBlock layerBlock;
    public final int layerMetadataID, layerMin, layerMax;
    public final UBStoneCodes codes;
    
    public StrataLayer(NamedBlock layerBlock, int layerMetadataID, int layerMin, int layerMax){
        this.layerBlock = layerBlock;
        this.layerMetadataID = layerMetadataID;
        this.layerMin = layerMin;
        this.layerMax = layerMax;
        this.codes = new UBStoneCodes(layerBlock, layerMetadataID);
    }
    
    public boolean valueIsInLayer(int y){
        if(y >= layerMin && y <= layerMax){
            return true;
        }else{
            return false;
        }
    }
    
}
