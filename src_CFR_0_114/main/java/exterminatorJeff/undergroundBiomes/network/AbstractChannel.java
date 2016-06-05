/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 */
package exterminatorJeff.undergroundBiomes.network;

import exterminatorJeff.undergroundBiomes.network.AbstractMessage;
import net.minecraft.entity.player.EntityPlayer;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class AbstractChannel<Type> {
    abstract AbstractMessage<Type> outgoingMessage(Type var1);

    abstract AbstractMessage<Type> incomingMessage();

    abstract void receiveAsClient(Type var1, EntityPlayer var2);

    abstract void receiveAsServer(Type var1, EntityPlayer var2);
}

