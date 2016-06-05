/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  io.netty.buffer.ByteBufInputStream
 *  io.netty.buffer.ByteBufOutputStream
 */
package exterminatorJeff.undergroundBiomes.network;

import Zeno410Utils.Streamer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.IOException;
import java.io.InputStream;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class AbstractMessage<Type> {
    Type value;

    public AbstractMessage() {
    }

    public AbstractMessage(Type value) {
        this.value = value;
    }

    public abstract Streamer<Type> streamer();

    public void fromBytes(ByteBuf packet) {
        DataInputStream inputStream = new DataInputStream((InputStream)new ByteBufInputStream(packet));
        try {
            this.value = this.streamer().readFrom(inputStream);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void toBytes(ByteBuf buf) {
        ByteBufOutputStream outputStream = new ByteBufOutputStream(buf);
        try {
            this.streamer().writeTo(this.value, (DataOutput)outputStream);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

