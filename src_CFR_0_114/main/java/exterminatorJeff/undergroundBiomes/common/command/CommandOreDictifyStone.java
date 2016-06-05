/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.ICommandSender
 */
package exterminatorJeff.undergroundBiomes.common.command;

import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

public class CommandOreDictifyStone
extends CommandBase {
    public int compareTo(Object compared) {
        return this.toString().compareTo(compared.toString());
    }

    public String func_71517_b() {
        return "oredictifystone";
    }

    public String func_71518_a(ICommandSender par1ICommandSender) {
        return "/" + this.func_71517_b();
    }

    public void func_71515_b(ICommandSender sender, String[] as) {
        try {
            int num = UndergroundBiomes.oreDictifyStone();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

