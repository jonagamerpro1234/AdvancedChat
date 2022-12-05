package jss.advancedchat.gui;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.manager.PlayerManager;
import jss.advancedchat.utils.Utils;
import jss.advancedchat.utils.XEnchantment;
import jss.advancedchat.utils.XMaterial;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class GuiPlayer {
  private final AdvancedChat plugin;

  public GuiPlayer(AdvancedChat plugin) {
    this.plugin = plugin;
  }

  public void openPlayerGui(Player player, String player2) {
    FileConfiguration config = this.plugin.getPlayerGuiFile().getConfig();
    String title = config.getString("Player-Gui.Title");
    String pathcolor = config.getString("Player-Gui.Decoration.Glass-Color.Item");
    title = title.replace("<player>", player2);
    Inventory inv = Bukkit.createInventory(null, 36, Utils.color(title));
    ItemStack item = null;
    ItemMeta meta = null;
    SkullMeta skullmeta = null;
    Player p = Bukkit.getPlayer(player2);
    setDecoration(inv, item, meta, pathcolor);
    item = Utils.getPlayerHead(player2);
    inv.setItem(10, item);
    setMuteItem(p, inv, item, meta);
    String material = config.getString("Player-Gui.Items.Color.Item");
    String name = config.getString("Player-Gui.Items.Color.Name");
    String texture = config.getString("Player-Gui.Items.Color.Texture");
    boolean useSkull = config.getString("Player-Gui.Items.Color.Use-Custom-Skull").equals("true");
    item = XMaterial.valueOf(material).parseItem();
    if (useSkull) {
      item = Utils.createSkull(texture);
      skullmeta = (SkullMeta) item.getItemMeta();
      skullmeta.setDisplayName(Utils.color(name));
      item.setItemMeta(skullmeta);
    } else {
      meta = item.getItemMeta();
      meta.setDisplayName(Utils.color(name));
      item.setItemMeta(meta);
    }
    inv.setItem(16, item);
    player.openInventory(inv);
    this.plugin.addInventoryView(player, "player");
  }

  private void setMuteItem(Player player, Inventory inv, ItemStack item, ItemMeta meta) {
    PlayerManager manager = new PlayerManager(this.plugin);
    if (manager.checkPlayerList(player)) {
      if (manager.isMute(player)) {
        item = XMaterial.PAPER.parseItem();
        meta = item.getItemMeta();
        item.setAmount(1);
        meta.addEnchant(XEnchantment.DURABILITY.parseEnchantment(), 1, false);
        meta.setDisplayName(Utils.color("&6&lMute Player"));
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        inv.setItem(13, item);
        item = XMaterial.GREEN_DYE.parseItem();
        meta = item.getItemMeta();
        item.setAmount(1);
        meta.setDisplayName(Utils.color("&a&lTrue"));
        item.setItemMeta(meta);
        inv.setItem(22, item);
      } else if (!manager.isMute(player)) {
        item = XMaterial.PAPER.parseItem();
        meta = item.getItemMeta();
        item.setAmount(1);
        meta.setDisplayName(Utils.color("&6&lMute Player"));
        item.setItemMeta(meta);
        inv.setItem(13, item);
        item = XMaterial.RED_DYE.parseItem();
        meta = item.getItemMeta();
        item.setAmount(1);
        meta.setDisplayName(Utils.color("&c&lFalse"));
        item.setItemMeta(meta);
        inv.setItem(22, item);
      }
    } else {
    }
  }

  private void setDecoration(Inventory inv, ItemStack item, ItemMeta meta, String path) {
    for (int i = 0; i < 36; i++) {
      item = XMaterial.valueOf(path).parseItem();
      meta = item.getItemMeta();
      meta.setDisplayName(Utils.color(" "));
      item.setItemMeta(meta);
      item.setAmount(1);
      inv.setItem(i, item);
      if (i == 36)
        break;
    }
  }
}
