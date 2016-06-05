/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.gen.layer.GenLayer
 */
package Zeno410Utils;

import Zeno410Utils.Zeno410Logger;
import java.lang.reflect.Field;
import java.util.logging.Logger;
import net.minecraft.world.gen.layer.GenLayer;

public class GenLayerSpy
extends GenLayer {
    private final GenLayer spiedUpon;
    private static Logger logger = new Zeno410Logger("GenLayerSpy").logger();
    private static int lines = 0;

    public GenLayerSpy(GenLayer toSpyOn) {
        super(0);
        this.spiedUpon = toSpyOn;
        Field[] fields = this.getClass().getSuperclass().getDeclaredFields();
        Field parentField = null;
        for (int i = 0; i < fields.length; ++i) {
            if (!fields[i].getName().contains("field_75909_a")) continue;
            parentField = fields[i];
            parentField.setAccessible(true);
        }
        try {
            GenLayer oldParent = (GenLayer)parentField.get((Object)this.spiedUpon);
            if (oldParent != null) {
                GenLayerSpy newParent = new GenLayerSpy(oldParent);
                parentField.set((Object)this.spiedUpon, (Object)newParent);
            }
        }
        catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public int[] func_75904_a(int par1, int par2, int par3, int par4) {
        if (lines < 1000) {
            ++lines;
        }
        return this.spiedUpon.func_75904_a(par1, par2, par3, par4);
    }
}

