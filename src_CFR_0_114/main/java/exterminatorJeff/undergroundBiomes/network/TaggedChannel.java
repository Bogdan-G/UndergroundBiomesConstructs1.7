/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayerMP
 */
package exterminatorJeff.undergroundBiomes.network;

import Zeno410Utils.Acceptor;
import Zeno410Utils.Maybe;
import Zeno410Utils.Named;
import Zeno410Utils.Streamer;
import exterminatorJeff.undergroundBiomes.network.AbstractChannel;
import exterminatorJeff.undergroundBiomes.network.MessageManager;
import exterminatorJeff.undergroundBiomes.network.PacketPipeline;
import exterminatorJeff.undergroundBiomes.network.PassingChannel;
import java.util.Iterator;
import net.minecraft.entity.player.EntityPlayerMP;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public final class TaggedChannel<Type> {
    private final MessageManager<Type> manager = new MessageManager();
    private final PacketPipeline.Channel<Named<Type>> channel;
    private final PacketPipeline pipeline;

    public PacketPipeline.Channel<Named<Type>> channel() {
        return this.channel;
    }

    public TaggedChannel(PacketPipeline pipeline, Streamer<Type> streamer) {
        this.pipeline = pipeline;
        this.channel = pipeline.registerChannel(new PassingChannel<Type>(this.manager, Named.streamer(streamer))).iterator().next();
    }

    public void setManageAll(String tag, Acceptor<Type> manager) {
        this.manager.setManageAll(tag, manager);
    }

    public void setManageNext(String tag, Acceptor<Type> manager) {
        this.manager.setManageNext(tag, manager);
    }

    public void resetManageNext(String tag, Acceptor<Type> manager) {
        this.manager.resetManageNext(tag, manager);
    }

    public final void sendTo(String tag, Type sent, EntityPlayerMP player) {
        this.channel.sendTo(Named.from(tag, sent), player);
    }

    public final void sendToAll(String tag, Type sent) {
        this.channel.sendToAll(Named.from(tag, sent));
    }

    public final void sendServer(String tag, Type sent) {
        this.channel.sendServer(Named.from(tag, sent));
    }
}

