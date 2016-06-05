/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayerMP
 */
package exterminatorJeff.undergroundBiomes.network;

import Zeno410Utils.Acceptor;
import Zeno410Utils.Maybe;
import Zeno410Utils.PlayerAcceptor;
import Zeno410Utils.Streamer;
import exterminatorJeff.undergroundBiomes.network.AbstractChannel;
import exterminatorJeff.undergroundBiomes.network.PacketPipeline;
import exterminatorJeff.undergroundBiomes.network.PassingChannel;
import java.util.Iterator;
import net.minecraft.entity.player.EntityPlayerMP;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public final class ServerRequestChannel<Type> {
    private final PacketPipeline.Channel<Type> channel;

    public PacketPipeline.Channel<Type> channel() {
        return this.channel;
    }

    public ServerRequestChannel(PacketPipeline pipeline, Streamer<Type> streamer, Acceptor<Type> clientManager, PlayerAcceptor<Type> manager) {
        this.channel = pipeline.registerChannel(new PassingChannel<Type>(clientManager, manager, streamer)).iterator().next();
    }

    public final void sendTo(Type sent, EntityPlayerMP player) {
        this.channel.sendTo(sent, player);
    }

    public final void sendToAll(Type sent) {
        this.channel.sendToAll(sent);
    }

    public final void sendServer(Type sent) {
        this.channel.sendServer(sent);
    }
}

