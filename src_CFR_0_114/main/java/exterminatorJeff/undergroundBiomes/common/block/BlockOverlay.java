/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.util.IIcon
 */
package exterminatorJeff.undergroundBiomes.common.block;

import Zeno410Utils.Accessor;
import Zeno410Utils.Zeno410Logger;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.IIcon;

public class BlockOverlay
extends Block {
    public static final Logger logger = new Zeno410Logger("BlockOverlay").logger();
    private final String overlayFileName;
    private IIcon overlayTexture;
    private int renderID;
    private static boolean shown = false;

    public BlockOverlay(String overlayName) {
        super(Material.field_151576_e);
        if (overlayName.contains("null")) {
            throw new RuntimeException();
        }
        this.overlayFileName = overlayName;
        this.field_149768_d = overlayName;
        this.renderID = super.func_149645_b();
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister iconRegister) {
        this.overlayTexture = iconRegister.func_94245_a(this.overlayFileName);
        TextureMap textureMap = (TextureMap)iconRegister;
        super.func_149651_a(iconRegister);
    }

    public IIcon func_149691_a(int p_149691_1_, int p_149691_2_) {
        return this.overlayTexture;
    }

    public int func_149645_b() {
        return this.renderID;
    }

    public static void showTextureNames(TextureMap textureMap) {
        shown = true;
        Accessor accessor = new Accessor(TextureMap.class);
        Map sprites = (Map)accessor.get(textureMap);
        Set names = sprites.keySet();
        for (Object name : names) {
            logger.info(name.toString());
        }
    }
}

