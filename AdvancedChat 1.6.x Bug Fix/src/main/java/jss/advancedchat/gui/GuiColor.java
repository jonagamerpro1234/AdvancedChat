package jss.advancedchat.gui;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.config.ColorFile;
import jss.advancedchat.utils.Utils;
import jss.advancedchat.utils.XMaterial;
import jss.advancedchat.utils.inventory.TSkullUtils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GuiColor {
  private final AdvancedChat plugin;

  private ItemStack item;

  private ItemMeta meta;

  public GuiColor(AdvancedChat plugin) {
    this.plugin = plugin;
  }

  public void open(Player player, String player2) {
    ColorFile colorFile = this.plugin.getColorFile();
    FileConfiguration config = colorFile.getConfig();
    String title = config.getString("Title");
    title = title.replace("<player>", player2);
    Inventory inv = Bukkit.createInventory(null, 45, Utils.color(title));
    String materialglass = config.getString("Decoration.Glass-Color.Item");
    setDecoration(inv, materialglass);
    this.item = Utils.getPlayerHead(player2);
    inv.setItem(36, this.item);
    Iterator<String> section = config.getConfigurationSection("Items").getKeys(false).iterator();
    while (section.hasNext()) {
      String key = section.next();
      String name = config.getString("Items." + key + ".Name");
      String textures = config.getString("Items." + key + ".Texture");
      int slot = config.getInt("Items." + key + ".Slot");
      List<String> lore = key.contains("Lore") ? new ArrayList<>() : config.getStringList("Items." + key + ".Lore");
      textures = TSkullUtils.replace(textures);
      this.item = Utils.createSkull(textures);
      this.meta = this.item.getItemMeta();
      this.meta.setDisplayName(Utils.color(name));
      this.meta.setLore(coloredLore(lore));
      this.item.setItemMeta(this.meta);
      this.item.setAmount(1);
      inv.setItem(slot, this.item);
    }
    this.plugin.addInventoryView(player, "colorGui");
    player.openInventory(inv);
  }

  private List<String> coloredLore(List<String> lore) {
    List<String> coloredlore = new ArrayList<>();
    lore.forEach(line -> {
      String lineColored = Utils.color(line);
      coloredlore.add(lineColored);
    });
    return coloredlore;
  }

  private void setDecoration(Inventory inv, String path) {
    for (int i = 0; i < 45; i++) {
      this.item = XMaterial.valueOf(path).parseItem();
      this.meta = this.item.getItemMeta();
      this.meta.setDisplayName(Utils.color(" "));
      this.item.setItemMeta(this.meta);
      this.item.setAmount(1);
      inv.setItem(i, this.item);
      if (i == 45)
        break;
    }
  }
}
