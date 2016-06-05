/*
 * Decompiled with CFR 0_114.
 */
package Zeno410Utils;

import Zeno410Utils.Streamer;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class Named<Type> {
    public String name;
    public Type object;

    public Named(String theName, Type theObject) {
        this.name = theName;
        this.object = theObject;
    }

    public static <T> Named<T> from(String name, T object) {
        return new Named<T>(name, object);
    }

    public static <StreamerType> NamedStreamer<StreamerType> streamer(Streamer<StreamerType> streamer) {
        return new NamedStreamer<StreamerType>(streamer);
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static class NamedStreamer<Type>
    extends Streamer<Named<Type>> {
        private final Streamer<Type> streamer;

        public NamedStreamer(Streamer<Type> streamer) {
            this.streamer = streamer;
        }

        @Override
        public Named<Type> readFrom(DataInput input) throws IOException {
            String name = input.readUTF();
            return new Named<Type>(name, this.streamer.readFrom(input));
        }

        @Override
        public void writeTo(Named<Type> written, DataOutput output) throws IOException {
            output.writeUTF(written.name);
            this.streamer.writeTo(written.object, output);
        }
    }

}

