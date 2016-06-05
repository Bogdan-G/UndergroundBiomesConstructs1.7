
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

/**
 * An abstraction of IMessage to carry an object
 * @author Zeno410
 */
abstract public class AbstractMessage<Type>{
    Type value;
    /**
     * Deconstruct your message into the supplied byte buffer
     * @param buf
     */

    public AbstractMessage() {}
    public AbstractMessage(Type value) {
        this.value = value;
    }
    public abstract Streamer<Type> streamer();


    public void fromBytes(ByteBuf packet) {
                DataInputStream inputStream = new DataInputStream(new ByteBufInputStream(packet));
                try {
                    value = streamer().readFrom(inputStream);
                } catch (IOException e) {
                        throw new RuntimeException(e);
                }
    }

    public void toBytes(ByteBuf buf) {
            DataOutput outputStream = new ByteBufOutputStream(buf);
            try {
                streamer().writeTo(value, outputStream);
                //ExplorerPacketHandler.broadcast(packet);
            } catch (Exception ex) {
                    ex.printStackTrace();
            }


    }

}
