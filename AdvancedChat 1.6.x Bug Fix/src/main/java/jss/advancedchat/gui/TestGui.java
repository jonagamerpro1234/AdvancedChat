package jss.advancedchat.gui;

import dev.triumphteam.gui.guis.Gui;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;

public class TestGui {

    public void create(Player player){

        MiniMessage miniMessage = MiniMessage.miniMessage();

        Component parse = miniMessage.deserialize("<gradient:#5e4fa2:#f79459><b>Test Inventory</gradient>");

        Gui gui = Gui.gui()
                .title(parse)
                .rows(6)
                .create();

        gui.open(player);
    }

}
