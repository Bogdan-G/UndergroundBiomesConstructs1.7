/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 */
package exterminatorJeff.undergroundBiomes.network;

import Zeno410Utils.Acceptor;
import Zeno410Utils.PlayerAcceptor;
import Zeno410Utils.Streamer;
import exterminatorJeff.undergroundBiomes.network.AbstractChannel;
import exterminatorJeff.undergroundBiomes.network.AbstractMessage;
import net.minecraft.entity.player.EntityPlayer;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class PassingChannel<Type>
extends AbstractChannel<Type> {
    public final Acceptor<Type> clientManager;
    public PlayerAcceptor<Type> serverManager;
    public final Streamer<Type> streamer;

    protected PassingChannel(Acceptor<Type> manager, Streamer<Type> streamer) {
        this.clientManager = manager;
        this.streamer = streamer;
        this.serverManager = new IgnorePlayer<Type>(manager);
    }

    protected PassingChannel(Acceptor<Type> manager, PlayerAcceptor<Type> serverManager, Streamer<Type> streamer) {
        this.clientManager = manager;
        this.serverManager = serverManager;
        String type = serverManager.toString();
        this.streamer = streamer;
    }

    @Override
    public void receiveAsClient(Type incoming, EntityPlayer player) {
        this.clientManager().accept(incoming);
    }

    @Override
    public void receiveAsServer(Type incoming, EntityPlayer player) {
        this.serverManager().accept(player, incoming);
    }

    public Acceptor<Type> clientManager() {
        return this.clientManager;
    }

    public PlayerAcceptor<Type> serverManager() {
        return this.serverManager;
    }

    @Override
    public AbstractMessage<Type> incomingMessage() {
        return new AbstractMessage<Type>(){

            @Override
            public Streamer<Type> streamer() {
                return PassingChannel.this.streamer;
            }
        };
    }

    @Override
    public AbstractMessage<Type> outgoingMessage(Type sent) {
        return new AbstractMessage<Type>(sent){

            @Override
            public Streamer<Type> streamer() {
                return PassingChannel.this.streamer;
            }
        };
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    private class IgnorePlayer<JustType>
    extends PlayerAcceptor<JustType> {
        private Acceptor<JustType> internal;

        public IgnorePlayer(Acceptor<JustType> internal) {
            this.internal = internal;
        }

        @Override
        public void accept(EntityPlayer player, JustType accepted) {
            this.internal.accept(accepted);
        }
    }

}

