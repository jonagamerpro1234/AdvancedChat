package jss.advancedchat.hooks;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import jss.advancedchat.AdvancedChat;
import jss.advancedchat.files.utils.Settings;
import org.bukkit.entity.Player;

public class ProtocolLibHook {

    private final ProtocolManager manager = ProtocolLibrary.getProtocolManager();

    public void initPacketListening() {

        if (Settings.tabcomplete) {
            manager.addPacketListener(new PacketAdapter(AdvancedChat.get(), PacketType.Play.Client.TAB_COMPLETE) {
                public void onPacketReceiving(PacketEvent e) {
                    Player j = e.getPlayer();

                    if ((j.isOp()) || (j.hasPermission("AdvancedChat.Chat.TabComplete.Bypass"))) return;

                    String msg = e.getPacket().getStrings().read(0).trim();

                    if (Settings.tabcomplete_whitelist) {
                        for (int i = 0; i < Settings.list_tabcomplete_whitelist.size(); i++) {
                            if (msg.contains(Settings.list_tabcomplete_whitelist.get(i))) {
                                return;
                            }
                        }
                    }

                    if (!msg.startsWith("/")) {
                        return;
                    }

                    if (msg.contains(" ")) {
                        return;
                    }

                    e.setCancelled(true);
                }
            });
        }
    }
}
