package jss.advancedchat.gui;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import com.cryptomorin.xseries.XMaterial;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.utils.Utils;

@SuppressWarnings({"deprecation"})
public class GuiPlayerList {

    private AdvancedChat plugin;

    public void openPlayerListGui(Player player, int pag) {
        FileConfiguration config = plugin.getPlayerGuiFile().getConfig();

        String title = config.getString("PlayerList-Gui.Title");

        Inventory inv = Bukkit.createInventory(null, 54, Utils.color(title));


        ItemStack item = null;
        ItemMeta meta = null;
        setDecorationPlayerList(inv, item, meta, 45, 54);

        for (int i = 45 * (pag - 1); i < Bukkit.getOnlinePlayers().size(); i++) {

            for (Player p : Bukkit.getOnlinePlayers()) {
                item = XMaterial.PLAYER_HEAD.parseItem();
                SkullMeta smeta = (SkullMeta) item.getItemMeta();
                smeta.setOwner(p.getName());
                item.setItemMeta(smeta);

                inv.setItem(i, item);
            }
        }


        player.openInventory(inv);
    }


    private void setDecorationPlayerList(Inventory inv, ItemStack item, ItemMeta meta, int slot0, int slot1) {

        for (int i = slot0; i < slot1; i++) {
            item = XMaterial.BLACK_STAINED_GLASS_PANE.parseItem();
            item.setAmount(1);
            meta = item.getItemMeta();
            meta.setDisplayName(" ");
            item.setItemMeta(meta);
            inv.setItem(i, item);

            if (i == 9) {
                break;
            }
        }

    }
}
