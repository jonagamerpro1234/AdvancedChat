package jss.advancedchat.v2.hooks;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.events.PacketListener;
import jss.advancedchat.AdvancedChat;
import jss.advancedchat.utils.Settings;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

public class ProtocollibHook {

    private AdvancedChat plugin;
    private ProtocolManager protocolManager;
    private boolean isEnable;

    public void onLoad(AdvancedChat plugin){
        this.plugin = plugin;
        if(!Bukkit.getPluginManager().isPluginEnabled("ProtocolLib")){
            isEnable = false;
            return;
        }

        isEnable = true;

        protocolManager = ProtocolLibrary.getProtocolManager();

        if(Settings.tabcomplete){
            onTabComplete();
        }
    }

    public boolean isEnable() {
        return isEnable;
    }

    public ProtocolManager getProtocolManager(){
        return protocolManager;
    }

    private void onTabComplete(){
        protocolManager.addPacketListener(new PacketAdapter(plugin, PacketType.Play.Client.TAB_COMPLETE) {
            @Override
            public void onPacketReceiving(PacketEvent event) {



            }
        });
    }

}
