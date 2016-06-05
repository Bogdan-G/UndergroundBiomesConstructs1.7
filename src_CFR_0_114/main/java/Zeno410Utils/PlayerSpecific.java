/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 */
package Zeno410Utils;

import Zeno410Utils.PlayerID;
import Zeno410Utils.Streamer;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import net.minecraft.entity.player.EntityPlayer;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class PlayerSpecific<Type> {
    private final PlayerID player;
    private final Type item;

    public PlayerSpecific(PlayerID _player, Type _item) {
        this.player = _player;
        this.item = _item;
    }

    public PlayerSpecific(EntityPlayer player, Type item) {
        this(new PlayerID(player), item);
    }

    public final PlayerID player() {
        return this.player;
    }

    public final Type item() {
        return this.item;
    }

    public static <StreamType> Streamer<PlayerSpecific<StreamType>> streamer(final Streamer<StreamType> substreamer) {
        return new Streamer<PlayerSpecific<StreamType>>(){
            PlayerID.PlayerIDStreamer playerStreamer;

            @Override
            public PlayerSpecific<StreamType> readFrom(DataInput input) throws IOException {
                PlayerID player = this.playerStreamer.readFrom(input);
                return new PlayerSpecific(player, substreamer.readFrom(input));
            }

            @Override
            public void writeTo(PlayerSpecific<StreamType> written, DataOutput output) throws IOException {
                this.playerStreamer.writeTo(written.player, output);
                substreamer.writeTo(written.item, output);
            }
        };
    }

    public int hashCode() {
        return this.item.hashCode() + this.player.getName().hashCode();
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        PlayerSpecific other = (PlayerSpecific)obj;
        if (!(this.player == other.player || this.player != null && this.player.equals(other.player))) {
            return false;
        }
        if (!(this.item == other.item || this.item != null && this.item.equals(other.item))) {
            return false;
        }
        return true;
    }

}

