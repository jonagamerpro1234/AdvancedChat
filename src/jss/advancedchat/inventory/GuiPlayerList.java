package jss.advancedchat.inventory;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.utils.Utils;

public class GuiPlayerList {

    private AdvancedChat plugin;

    public void openPlayerListGui(Player player, int pag) {
        FileConfiguration config = plugin.getPlayerGuiFile().getConfig();

        String title = config.getString("PlayerList-Gui.Title");

        Inventory inv = Bukkit.createInventory(null, 54, Utils.color(title));
        ItemStack item = null;
        ItemMeta meta = null;

        
        
        for (int i = 45 * (pag - 1); i < Bukkit.getOnlinePlayers().size(); i++) {

            for (Player p : Bukkit.getOnlinePlayers()) {
            	
            	item = Utils.getPlayerHead(p.getName());
            	meta.setDisplayName(Utils.color("&a&l" + p.getName()));
            	item.setItemMeta(meta);
                inv.setItem(i, item);
            }
        }


        player.openInventory(inv);
    }
}
