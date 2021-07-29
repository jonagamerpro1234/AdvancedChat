package jss.advancedchat.inventory;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.cryptomorin.xseries.XMaterial;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.manager.ChatManager;
import jss.advancedchat.utils.Utils;

public class GuiLog {

    private AdvancedChat plugin;

    public GuiLog(AdvancedChat plugin) {
        this.plugin = plugin;
    }
    
    @SuppressWarnings("unused")
    public void openGuiLog(Player player, String name, int page) {
    	
    	
    	//new InventoryUtils(player, 1, "s").create();
    	
        FileConfiguration config = plugin.getChatDataFile().getConfig();

        Inventory inv = Bukkit.createInventory(null, 54, Utils.color("&6Log: &9" + name));

        ArrayList<ChatManager> list = plugin.getChatManagers();
        int totalpage = plugin.getTotalPage();
        int slot = 0;
        ItemStack item = null;
        ItemMeta meta = null;


        for (int i = 45 * (page - 1); i < list.size(); i++) {
            ChatManager manager = list.get(i);


            item = XMaterial.BOOK.parseItem();
            meta = item.getItemMeta();


            slot++;
            if (slot == 44) {
                break;
            }
        }


        player.openInventory(inv);
    }

    
}
