/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  io.netty.channel.ChannelHandlerContext
 *  net.minecraft.entity.player.EntityPlayer
 */
package exterminatorJeff.undergroundBiomes.network;

import exterminatorJeff.undergroundBiomes.network.PacketPipeline;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;

public abstract class AbstractPacket {
    public abstract void encodeInto(ChannelHandlerContext var1, ByteBuf var2);

    public abstract void decodeInto(ChannelHandlerContext var1, ByteBuf var2);

    public abstract void handleClientSide(EntityPlayer var1);

    public abstract void handleServerSide(EntityPlayer var1);

    public abstract PacketPipeline.Channel channel();
}

