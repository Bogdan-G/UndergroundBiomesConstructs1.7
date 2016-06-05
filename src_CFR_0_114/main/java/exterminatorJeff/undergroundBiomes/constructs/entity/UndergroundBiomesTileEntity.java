/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.INetHandler
 *  net.minecraft.network.NetworkManager
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.server.S35PacketUpdateTileEntity
 *  net.minecraft.tileentity.TileEntity
 */
package exterminatorJeff.undergroundBiomes.constructs.entity;

import java.util.Set;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetHandler;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class UndergroundBiomesTileEntity
extends TileEntity {
    public static String IndexName = "index";
    private int masterIndex;

    public void setZCoord(int zCoord) {
        this.field_145849_e = zCoord;
    }

    public boolean canUpdate() {
        return false;
    }

    public void func_145839_a(NBTTagCompound nbt) {
        Set tags = nbt.func_150296_c();
        for (Object object : tags) {
        }
        super.func_145839_a(nbt);
        this.masterIndex = nbt.func_74762_e(IndexName);
    }

    public void func_145841_b(NBTTagCompound nbt) {
        super.func_145841_b(nbt);
        nbt.func_74768_a(IndexName, this.masterIndex);
    }

    public Packet func_145844_m() {
        NBTTagCompound tileTag = new NBTTagCompound();
        this.func_145841_b(tileTag);
        return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 0, tileTag);
    }

    public void onDataPacket(INetHandler net, S35PacketUpdateTileEntity packet) {
        this.func_145839_a(packet.func_148857_g());
    }

    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
        this.func_145839_a(packet.func_148857_g());
    }

    public final int masterIndex() {
        return this.masterIndex;
    }

    public final void setMasterIndex(int index) {
        this.masterIndex = index;
        this.func_70296_d();
    }
}

