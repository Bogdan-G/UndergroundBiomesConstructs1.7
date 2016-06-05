/*
 * Decompiled with CFR 0_114.
 */
package Zeno410Utils;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public interface Streamable {
    public void readFrom(DataInput var1) throws IOException;

    public void writeTo(DataOutput var1) throws IOException;
}

