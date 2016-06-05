/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.network.FMLEmbeddedChannel
 *  cpw.mods.fml.common.network.FMLOutboundHandler
 *  cpw.mods.fml.common.network.FMLOutboundHandler$OutboundTarget
 *  cpw.mods.fml.common.network.NetworkRegistry
 *  cpw.mods.fml.common.network.NetworkRegistry$TargetPoint
 *  cpw.mods.fml.common.network.internal.FMLProxyPacket
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.buffer.ByteBuf
 *  io.netty.buffer.Unpooled
 *  io.netty.channel.Channel
 *  io.netty.channel.ChannelFuture
 *  io.netty.channel.ChannelHandler
 *  io.netty.channel.ChannelHandler$Sharable
 *  io.netty.channel.ChannelHandlerContext
 *  io.netty.handler.codec.MessageToMessageCodec
 *  io.netty.util.Attribute
 *  io.netty.util.AttributeKey
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.network.INetHandler
 *  net.minecraft.network.NetHandlerPlayServer
 */
package exterminatorJeff.undergroundBiomes.network;

import Zeno410Utils.Maybe;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.FMLEmbeddedChannel;
import cpw.mods.fml.common.network.FMLOutboundHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import exterminatorJeff.undergroundBiomes.network.AbstractChannel;
import exterminatorJeff.undergroundBiomes.network.AbstractMessage;
import exterminatorJeff.undergroundBiomes.network.AbstractPacket;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.INetHandler;
import net.minecraft.network.NetHandlerPlayServer;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@ChannelHandler.Sharable
public class PacketPipeline
extends MessageToMessageCodec<FMLProxyPacket, AbstractPacket> {
    private EnumMap<Side, FMLEmbeddedChannel> channels;
    private LinkedList<Channel> packetChannels = new LinkedList();
    private boolean isPostInitialised = false;
    private byte nextChannel = 0;
    private Channel[] channelIndex = new Channel[256];

    public <ChannelType> Maybe<Channel<ChannelType>> registerChannel(AbstractChannel<ChannelType> clazzParent) {
        if (this.packetChannels.size() >= 256) {
            return Maybe.unknown();
        }
        if (this.isPostInitialised) {
            return Maybe.unknown();
        }
        byte by = this.nextChannel;
        this.nextChannel = (byte)(by + 1);
        Channel clazz = new Channel(clazzParent, by);
        if (this.packetChannels.contains(clazz)) {
            return Maybe.unknown();
        }
        this.packetChannels.add(clazz);
        this.channelIndex[clazz.discriminator] = clazz;
        return Maybe.definitely(clazz);
    }

    public <ChannelType> byte discriminator(Channel<ChannelType> clazz) {
        byte discriminator = clazz.discriminator;
        return discriminator;
    }

    protected void encode(ChannelHandlerContext ctx, AbstractPacket msg, List<Object> out) throws Exception {
        ByteBuf buffer = Unpooled.buffer();
        Channel clazz = msg.channel();
        if (!this.packetChannels.contains(clazz)) {
            throw new NullPointerException("No Packet Registered for: " + msg.getClass().getCanonicalName());
        }
        byte discriminator = clazz.discriminator;
        buffer.writeByte((int)discriminator);
        msg.encodeInto(ctx, buffer);
        FMLProxyPacket proxyPacket = new FMLProxyPacket(buffer.copy(), (String)ctx.channel().attr(NetworkRegistry.FML_CHANNEL).get());
        out.add((Object)proxyPacket);
    }

    protected void decode(ChannelHandlerContext ctx, FMLProxyPacket msg, List<Object> out) throws Exception {
        ByteBuf payload = msg.payload();
        byte discriminator = payload.readByte();
        Channel clazz = this.channelIndex[discriminator];
        if (clazz == null) {
            throw new NullPointerException("No packet registered for discriminator: " + discriminator);
        }
        AbstractPacket pkt = clazz.incomingPacket();
        pkt.decodeInto(ctx, payload.slice());
        switch (FMLCommonHandler.instance().getEffectiveSide()) {
            case CLIENT: {
                EntityPlayer player = this.getClientPlayer();
                pkt.handleClientSide(player);
                break;
            }
            case SERVER: {
                INetHandler netHandler = (INetHandler)ctx.channel().attr(NetworkRegistry.NET_HANDLER).get();
                EntityPlayerMP player = ((NetHandlerPlayServer)netHandler).field_147369_b;
                pkt.handleServerSide((EntityPlayer)player);
                break;
            }
        }
        out.add(pkt);
    }

    public void initialise() {
        this.channels = NetworkRegistry.INSTANCE.newChannel("UB", new ChannelHandler[]{this});
    }

    public void postInitialise() {
        if (this.isPostInitialised) {
            return;
        }
        this.isPostInitialised = true;
        Collections.sort(this.packetChannels, new Comparator<Channel>(){

            @Override
            public int compare(Channel clazz1, Channel clazz2) {
                int com = clazz1.hashCode() - clazz2.hashCode();
                return com;
            }
        });
    }

    @SideOnly(value=Side.CLIENT)
    private EntityPlayer getClientPlayer() {
        return Minecraft.func_71410_x().field_71439_g;
    }

    public void sendToAll(AbstractPacket message) {
        this.channels.get((Object)Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set((Object)FMLOutboundHandler.OutboundTarget.ALL);
        this.channels.get((Object)Side.SERVER).writeAndFlush((Object)message);
    }

    public void sendTo(AbstractPacket message, EntityPlayerMP player) {
        this.channels.get((Object)Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set((Object)FMLOutboundHandler.OutboundTarget.PLAYER);
        this.channels.get((Object)Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set((Object)player);
        this.channels.get((Object)Side.SERVER).writeAndFlush((Object)message);
    }

    public void sendToAllAround(AbstractPacket message, NetworkRegistry.TargetPoint point) {
        this.channels.get((Object)Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set((Object)FMLOutboundHandler.OutboundTarget.ALLAROUNDPOINT);
        this.channels.get((Object)Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set((Object)point);
        this.channels.get((Object)Side.SERVER).writeAndFlush((Object)message);
    }

    public void sendToDimension(AbstractPacket message, int dimensionId) {
        this.channels.get((Object)Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set((Object)FMLOutboundHandler.OutboundTarget.DIMENSION);
        this.channels.get((Object)Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set((Object)dimensionId);
        this.channels.get((Object)Side.SERVER).writeAndFlush((Object)message);
    }

    public void sendToServer(AbstractPacket message) {
        this.channels.get((Object)Side.CLIENT).attr(FMLOutboundHandler.FML_MESSAGETARGET).set((Object)FMLOutboundHandler.OutboundTarget.TOSERVER);
        this.channels.get((Object)Side.CLIENT).writeAndFlush((Object)message);
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public final class Channel<Type> {
        final AbstractChannel<Type> source;
        final byte discriminator;

        Channel(AbstractChannel source, byte discriminator) {
            this.source = source;
            this.discriminator = discriminator;
        }

        public final AbstractPacket incomingPacket() {
            return new ChannelizedPacket<Type>(this.source.incomingMessage(), this);
        }

        public final void sendTo(Type sent, EntityPlayerMP player) {
            PacketPipeline.this.sendTo(new ChannelizedPacket<Type>(this.source.outgoingMessage(sent), this), player);
        }

        public final void sendToAll(Type sent) {
            PacketPipeline.this.sendToAll(new ChannelizedPacket<Type>(this.source.outgoingMessage(sent), this));
        }

        public final void sendServer(Type sent) {
            PacketPipeline.this.sendToServer(new ChannelizedPacket<Type>(this.source.outgoingMessage(sent), this));
        }

        public final void receiveClient(EntityPlayer player, Type received) {
            this.source.receiveAsClient(received, player);
        }

        public final void receiveServer(EntityPlayer player, Type received) {
            this.source.receiveAsServer(received, player);
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    private class ChannelizedPacket<MessageType>
    extends AbstractPacket {
        private final AbstractMessage<MessageType> message;
        private final Channel<MessageType> channel;

        ChannelizedPacket(AbstractMessage<MessageType> message, Channel channel) {
            this.message = message;
            this.channel = channel;
        }

        @Override
        public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
            this.message.toBytes(buffer);
        }

        @Override
        public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
            this.message.fromBytes(buffer);
        }

        @Override
        public void handleClientSide(EntityPlayer player) {
            this.channel.receiveClient(player, this.message.value);
        }

        @Override
        public void handleServerSide(EntityPlayer player) {
            this.channel.receiveServer(player, this.message.value);
        }

        @Override
        public Channel channel() {
            return this.channel;
        }
    }

}

