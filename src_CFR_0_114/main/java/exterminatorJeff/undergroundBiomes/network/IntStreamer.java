/*
 * Decompiled with CFR 0_114.
 */
package exterminatorJeff.undergroundBiomes.network;

import Zeno410Utils.Streamer;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class IntStreamer
extends Streamer<Integer> {
    @Override
    public Integer readFrom(DataInput input) throws IOException {
        return input.readInt();
    }

    @Override
    public void writeTo(Integer written, DataOutput output) throws IOException {
        output.writeInt(written);
    }
}

