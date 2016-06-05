/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Event
 *  cpw.mods.fml.common.eventhandler.Event$Result
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockSlab
 *  net.minecraft.init.Blocks
 *  net.minecraftforge.event.terraingen.BiomeEvent
 *  net.minecraftforge.event.terraingen.BiomeEvent$GetVillageBlockID
 *  net.minecraftforge.event.terraingen.BiomeEvent$GetVillageBlockMeta
 */
package exterminatorJeff.undergroundBiomes.worldGen;

import cpw.mods.fml.common.eventhandler.Event;
import exterminatorJeff.undergroundBiomes.api.BlockCodes;
import exterminatorJeff.undergroundBiomes.api.UBStoneCodes;
import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.init.Blocks;
import net.minecraftforge.event.terraingen.BiomeEvent;

public class VillageStoneChanger {
    private UBStoneCodes preferredStone;
    boolean replacing;

    public void setStoneCode(UBStoneCodes newCode) {
        this.preferredStone = newCode;
    }

    public void onVillageSelectBlock(BiomeEvent.GetVillageBlockID e) {
        if (this.preferredStone == null) {
            return;
        }
        if (e.original == Blocks.field_150364_r) {
            e.replacement = e.original;
        }
        if (e.original == Blocks.field_150347_e) {
            e.replacement = this.preferredStone.onDrop.block;
            this.replacing = true;
            e.setResult(Event.Result.DENY);
        }
        if (e.original == Blocks.field_150344_f) {
            e.replacement = e.original;
        }
        if (e.original == Blocks.field_150476_ad) {
            e.replacement = e.original;
        }
        if (e.original == Blocks.field_150446_ar && UndergroundBiomes.stairsOn()) {
            e.replacement = Blocks.field_150476_ad;
            e.setResult(Event.Result.DENY);
        }
        if (e.original == Blocks.field_150351_n && UndergroundBiomes.replaceVillageGravel()) {
            e.replacement = this.preferredStone.brickVersionEquivalent().block;
            this.replacing = true;
            e.setResult(Event.Result.DENY);
        }
        if (e.original == Blocks.field_150333_U) {
            e.replacement = this.preferredStone.slabVersionEquivalent().block;
            this.replacing = true;
            e.setResult(Event.Result.DENY);
        }
    }

    public void onVillageSelectMeta(BiomeEvent.GetVillageBlockMeta e) {
        if (this.preferredStone == null) {
            return;
        }
        if (this.replacing) {
            e.replacement = this.preferredStone.onDrop.metadata;
            e.setResult(Event.Result.DENY);
            this.replacing = false;
        }
    }
}

