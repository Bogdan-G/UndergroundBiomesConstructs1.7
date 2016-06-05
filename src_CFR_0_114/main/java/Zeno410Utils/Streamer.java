/*
 * Decompiled with CFR 0_114.
 */
package Zeno410Utils;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class Streamer<Type> {
    public abstract Type readFrom(DataInput var1) throws IOException;

    public abstract void writeTo(Type var1, DataOutput var2) throws IOException;

    public static Streamer<String> ofString() {
        return new Streamer<String>(){

            @Override
            public String readFrom(DataInput input) throws IOException {
                return input.readUTF();
            }

            @Override
            public void writeTo(String written, DataOutput output) throws IOException {
                output.writeUTF(written);
            }
        };
    }

    public static Streamer<Integer> ofInt() {
        return new Streamer<Integer>(){

            @Override
            public Integer readFrom(DataInput input) throws IOException {
                return input.readInt();
            }

            @Override
            public void writeTo(Integer written, DataOutput output) throws IOException {
                output.writeInt(written);
            }
        };
    }

}

