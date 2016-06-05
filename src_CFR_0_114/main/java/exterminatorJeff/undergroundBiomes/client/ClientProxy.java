/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler
 *  cpw.mods.fml.client.registry.RenderingRegistry
 *  cpw.mods.fml.common.registry.LanguageRegistry
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.resources.Language
 *  net.minecraft.client.resources.LanguageManager
 */
package exterminatorJeff.undergroundBiomes.client;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import exterminatorJeff.undergroundBiomes.client.RenderUBOre;
import exterminatorJeff.undergroundBiomes.common.CommonProxy;
import exterminatorJeff.undergroundBiomes.worldGen.OreUBifier;
import java.net.URL;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.Language;
import net.minecraft.client.resources.LanguageManager;

public class ClientProxy
extends CommonProxy {
    public void registerRenderThings(OreUBifier oreUBifier) {
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new RenderUBOre(oreUBifier));
    }

    public void setUpBlockNames() {
        Language language = Minecraft.func_71410_x().func_135016_M().func_135041_c();
        String lang = language.func_135034_a();
        URL urlResource = this.getClass().getResource("/assets/undergroundbiomes/lang/" + lang + ".lang");
        if (urlResource != null) {
            LanguageRegistry.instance().loadLocalization(urlResource, lang, false);
        }
    }
}

