package jss.advancedchat.listeners.chat;

import jss.advancedchat.AdvancedChat;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.bukkit.advancement.Advancement;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.jetbrains.annotations.NotNull;

public class AdvancementListener implements Listener {

    private final AdvancedChat plugin = AdvancedChat.get();

    @EventHandler(priority = EventPriority.MONITOR)
    public void onAdvancement(@NotNull PlayerAdvancementDoneEvent e){
        Player player = e.getPlayer();
        Advancement advancement = e.getAdvancement();
        String advancementName = advancement.getKey().toString();

        if(advancementName.contains("root") || advancementName.contains("recipes")) return;;

        advancementName = advancementName.substring(advancementName.indexOf('/') + 1);
        advancementName = StringUtils.replace(advancementName, "_", " ");
        advancementName = WordUtils.capitalize(advancementName);
        advancementName = advancementName.replace(advancementName, "{advancement}");


    }

}
