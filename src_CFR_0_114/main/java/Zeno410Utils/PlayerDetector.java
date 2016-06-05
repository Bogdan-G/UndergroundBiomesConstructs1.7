/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.eventhandler.EventBus
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.PlayerEvent
 *  cpw.mods.fml.common.gameevent.PlayerEvent$PlayerLoggedInEvent
 *  cpw.mods.fml.common.gameevent.PlayerEvent$PlayerLoggedOutEvent
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraftforge.common.MinecraftForge
 */
package Zeno410Utils;

import Zeno410Utils.Acceptor;
import Zeno410Utils.PlayerID;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.EventBus;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import java.util.HashMap;
import java.util.HashSet;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.common.MinecraftForge;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class PlayerDetector {
    private HashMap<PlayerID, EntityPlayerMP> activePlayers = new HashMap();
    private HashSet<Acceptor<EntityPlayerMP>> onLogin = new HashSet();
    private HashSet<Acceptor<EntityPlayerMP>> onLogout = new HashSet();
    private Dispatcher dispatcher;

    public PlayerDetector() {
        this.dispatcher = new Dispatcher();
        FMLCommonHandler.instance().bus().register((Object)this.dispatcher);
        MinecraftForge.EVENT_BUS.register((Object)this.dispatcher);
    }

    public void addLoginAction(Acceptor<EntityPlayerMP> action) {
        this.onLogin.add(action);
    }

    public void addLogoutAction(Acceptor<EntityPlayerMP> action) {
        this.onLogin.add(action);
    }

    public class Dispatcher {
        private Dispatcher() {
        }

        @SubscribeEvent
        public void onJoinWorlds(PlayerEvent.PlayerLoggedInEvent e) {
            PlayerDetector.this.activePlayers.put(new PlayerID(e.player), (EntityPlayerMP)e.player);
            for (Acceptor action : PlayerDetector.this.onLogin) {
                action.accept((EntityPlayerMP)e.player);
            }
        }

        @SubscribeEvent
        public void onLeaveWorlds(PlayerEvent.PlayerLoggedOutEvent e) {
            PlayerDetector.this.activePlayers.remove(new PlayerID(e.player));
            for (Acceptor action : PlayerDetector.this.onLogout) {
                action.accept((EntityPlayerMP)e.player);
            }
        }
    }

}

