/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 */
package Zeno410Utils;

import Zeno410Utils.PlayerSpecific;
import Zeno410Utils.Streamer;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import net.minecraft.entity.player.EntityPlayer;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class PlayerID {
    private String name;

    private PlayerID(String _name) {
        this.name = _name;
    }

    public PlayerID(EntityPlayer player) {
        this(player.getDisplayName());
    }

    public String getName() {
        return this.name;
    }

    public <Type> PlayerSpecific<Type> specific(Type type) {
        return new PlayerSpecific<Type>(this, type);
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    public boolean equals(Object compared) {
        if (compared == null) {
            return false;
        }
        if (compared instanceof PlayerID && ((PlayerID)compared).name.equals(this.name)) {
            return true;
        }
        return false;
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static class PlayerIDStreamer
    extends Streamer<PlayerID> {
        @Override
        public PlayerID readFrom(DataInput input) throws IOException {
            String result = input.readUTF();
            return new PlayerID(result);
        }

        @Override
        public void writeTo(PlayerID written, DataOutput output) throws IOException {
            output.writeUTF(written.name);
        }
    }

}

