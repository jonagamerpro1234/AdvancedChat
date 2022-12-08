package jss.advancedchat.gui;

import com.cryptomorin.xseries.XMaterial;
import jss.advancedchat.AdvancedChat;
import jss.advancedchat.manager.PlayerManager;
import jss.advancedchat.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class GuiPlayer {
  private final AdvancedChat plugin;
  private ItemStack item;
  private ItemMeta meta;

  public GuiPlayer(AdvancedChat plugin) {
    this.plugin = plugin;
  }

  public void openPlayerGui(Player player, String player2) {
    FileConfiguration config = this.plugin.getPlayerGuiFile().getConfig();
    String title = config.getString("Player-Gui.Title");
    String pathColor = config.getString("Player-Gui.Decoration.Glass-Color.Item");
    assert title != null;
    title = title.replace("<player>", player2);
    Inventory inv = Bukkit.createInventory(null, 36, Utils.color(title));

    Player p = Bukkit.getPlayer(player2);
    setDecoration(inv, pathColor);
    item = Utils.getPlayerHead(player2);
    inv.setItem(10, item);
    setMuteItem(p, inv, item, meta);
    String material = config.getString("Player-Gui.Items.Color.Item");
    String name = config.getString("Player-Gui.Items.Color.Name");
    String texture = config.getString("Player-Gui.Items.Color.Texture");
    boolean useSkull = config.getBoolean("Player-Gui.Items.Color.Use-Custom-Skull");
    item = XMaterial.valueOf(material).parseItem();
    if (useSkull) {
      assert texture != null;
      item = Utils.createSkull(texture);
      SkullMeta skullmeta = (SkullMeta) item.getItemMeta();
      assert skullmeta != null;
      skullmeta.setDisplayName(Utils.color(name));
      item.setItemMeta(skullmeta);
    } else {
      assert item != null;
      meta = item.getItemMeta();
      assert meta != null;
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
        assert item != null;
        meta = item.getItemMeta();
        item.setAmount(1);
        assert meta != null;
        meta.addEnchant(Enchantment.DURABILITY, 1, false);
        meta.setDisplayName(Utils.color("&6&lMute Player"));
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        inv.setItem(13, item);
        item = XMaterial.GREEN_DYE.parseItem();
        assert item != null;
        meta = item.getItemMeta();
        item.setAmount(1);
        assert meta != null;
        meta.setDisplayName(Utils.color("&a&lTrue"));
        item.setItemMeta(meta);
        inv.setItem(22, item);
      } else if (!manager.isMute(player)) {
        item = XMaterial.PAPER.parseItem();
        assert item != null;
        meta = item.getItemMeta();
        item.setAmount(1);
        assert meta != null;
        meta.setDisplayName(Utils.color("&6&lMute Player"));
        item.setItemMeta(meta);
        inv.setItem(13, item);
        item = XMaterial.RED_DYE.parseItem();
        assert item != null;
        meta = item.getItemMeta();
        item.setAmount(1);
        assert meta != null;
        meta.setDisplayName(Utils.color("&c&lFalse"));
        item.setItemMeta(meta);
        inv.setItem(22, item);
      }
    }
  }

  private void setDecoration(Inventory inv, String path) {
    for (int i = 0; i < 36; i++) {
      item = XMaterial.valueOf(path).parseItem();
      assert item != null;
      meta = item.getItemMeta();
      assert meta != null;
      meta.setDisplayName(" ");
      item.setItemMeta(meta);
      item.setAmount(1);
      inv.setItem(i, item);
    }
  }
}
