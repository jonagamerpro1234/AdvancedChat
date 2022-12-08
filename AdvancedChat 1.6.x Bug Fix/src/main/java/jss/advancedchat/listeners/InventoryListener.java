package jss.advancedchat.listeners;

import com.cryptomorin.xseries.XMaterial;
import jss.advancedchat.AdvancedChat;
import jss.advancedchat.files.utils.Settings;
import jss.advancedchat.gui.GuiColor;
import jss.advancedchat.gui.GuiPlayer;
import jss.advancedchat.listeners.utils.EventUtils;
import jss.advancedchat.manager.PlayerManager;
import jss.advancedchat.utils.Utils;
import jss.advancedchat.utils.inventory.InventoryView;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryListener implements Listener {
  private AdvancedChat plugin;

  private final EventUtils eventUtils = new EventUtils(this.plugin);

  public InventoryListener(AdvancedChat plugin) {
    this.plugin = plugin;
    this.eventUtils.getEventManager().registerEvents(this, plugin);
  }

  @EventHandler
  public void onInventoryPlayer(InventoryClickEvent e) {
    PlayerManager manager = new PlayerManager(this.plugin);
    FileConfiguration config = this.plugin.getConfigFile().getConfig();
    Player p = (Player) e.getWhoClicked();
    InventoryView inventoryPlayer = this.plugin.getInventoryView(p);
    if (inventoryPlayer != null &&
            inventoryPlayer.getInventory().equals("player")) {
      if (e.getCurrentItem() == null)
        return;
      if (e.getClickedInventory() != null && e.getClickedInventory().equals(p.getOpenInventory().getTopInventory())) {
        if (e.getCurrentItem().getType() == Material.AIR || e.getSlotType() == null) return;
        e.setCancelled(true);
        int slot = e.getSlot();
        String namecolor = e.getClickedInventory().getItem(10).getItemMeta().getDisplayName();
        String name = Utils.colorless(namecolor);
        Player pp = Bukkit.getPlayer(name);
        if (slot == 22) {
          e.setCancelled(true);
          if (p.isOp() || p.hasPermission("AdvancedChat.Gui.Player.Mute")) {
            if (!manager.isMute(pp)) {
              manager.setMute(pp, true);
              ItemStack item = XMaterial.GREEN_DYE.parseItem();
              ItemMeta meta = item.getItemMeta();
              item.setAmount(1);
              meta.setDisplayName(Utils.color("&a&lTrue"));
              item.setItemMeta(meta);
              e.getInventory().setItem(22, item);
              item = XMaterial.PAPER.parseItem();
              meta = item.getItemMeta();
              item.setAmount(1);
              meta.addEnchant(Enchantment.DURABILITY, 1, false);
              meta.setDisplayName(Utils.color("&6&lMute Player"));
              meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
              item.setItemMeta(meta);
              e.getInventory().setItem(13, item);
            } else if (manager.isMute(pp)) {
              manager.setMute(pp.getPlayer(), false);
              ItemStack item = XMaterial.RED_DYE.parseItem();
              ItemMeta meta = item.getItemMeta();
              item.setAmount(1);
              meta.setDisplayName(Utils.color("&c&lFalse"));
              item.setItemMeta(meta);
              e.getInventory().setItem(22, item);
              item = XMaterial.PAPER.parseItem();
              meta = item.getItemMeta();
              item.setAmount(1);
              meta.setDisplayName(Utils.color("&6&lMute Player"));
              item.setItemMeta(meta);
              e.getInventory().setItem(13, item);
            }
          } else {
            TextComponent msg = new TextComponent();
            msg.setText(Utils.color(config.getString("AdvancedChat.No-Permission")));
            msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder(config.getString("AdvancedChat.No-Permission-Label"))).color(ChatColor.YELLOW).create()));
            p.spigot().sendMessage(msg);
            return;
          }
        }
        if (slot == 16)
          if (p.isOp() || p.hasPermission("AdvancedChat.Gui.Player.Mute")) {
            e.setCancelled(true);
            p.closeInventory();
            GuiColor guiColor = new GuiColor(this.plugin);
            guiColor.open(p, name);
          } else {
            TextComponent msg = new TextComponent();
            msg.setText(Utils.color(config.getString("AdvancedChat.No-Permission")));
            msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder(config.getString("AdvancedChat.No-Permission-Label"))).color(ChatColor.YELLOW).create()));
            p.spigot().sendMessage(msg);
          }
      }
    }
  }

  @EventHandler
  public void onInventoryColor(InventoryClickEvent e) {
    PlayerManager playerManager = new PlayerManager(this.plugin);
    FileConfiguration c = this.plugin.getColorFile().getConfig();
    Player p = (Player) e.getWhoClicked();
    InventoryView inventoryPlayer = this.plugin.getInventoryView(p);
    if (inventoryPlayer != null)
      if (inventoryPlayer.getInventory().contains("colorGui")) {
        if (e.getCurrentItem() == null)
          return;
        if (e.getClickedInventory() != null && e.getClickedInventory().equals(p.getOpenInventory().getTopInventory())) {
          if (e.getCurrentItem().getType() == Material.AIR || e.getSlotType() == null)
            return;
          e.setCancelled(true);
          int slot = e.getSlot();
          String namecolor = e.getClickedInventory().getItem(36).getItemMeta().getDisplayName();
          String name = Utils.colorless(namecolor);
          Player target = Bukkit.getPlayer(name);
          if (slot == c.getInt("Items.Dark-Red.Slot"))
            if (p.isOp() || p.hasPermission("AdvancedChat.Gui.Color.Dark_Red")) {
              e.setCancelled(true);
              playerManager.setColor(target, "dark_red");
            } else {
              Utils.sendHoverEvent(p, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
              return;
            }
          if (slot == c.getInt("Items.Red.Slot"))
            if (p.isOp() || p.hasPermission("AdvancedChat.Gui.Color.Red")) {
              e.setCancelled(true);
              playerManager.setColor(target, "red");
            } else {
              Utils.sendHoverEvent(p, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
              return;
            }
          if (slot == c.getInt("Items.Dark-Blue.Slot"))
            if (p.isOp() || p.hasPermission("AdvancedChat.Gui.Color.Dark_Blue")) {
              e.setCancelled(true);
              playerManager.setColor(target, "dark_blue");
            } else {
              Utils.sendHoverEvent(p, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
              return;
            }
          if (slot == c.getInt("Items.Blue.Slot"))
            if (p.isOp() || p.hasPermission("AdvancedChat.Gui.Color.Blue")) {
              e.setCancelled(true);
              playerManager.setColor(target, "blue");
            } else {
              Utils.sendHoverEvent(p, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
              return;
            }
          if (slot == c.getInt("Items.Dark-Green.Slot"))
            if (p.isOp() || p.hasPermission("AdvancedChat.Gui.Color.Dark_Green")) {
              e.setCancelled(true);
              playerManager.setColor(target, "dark_green");
            } else {
              Utils.sendHoverEvent(p, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
              return;
            }
          if (slot == c.getInt("Items.Green.Slot"))
            if (p.isOp() || p.hasPermission("AdvancedChat.Gui.Color.Green")) {
              e.setCancelled(true);
              playerManager.setColor(target, "green");
            } else {
              Utils.sendHoverEvent(p, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
              return;
            }
          if (slot == c.getInt("Items.Yellow.Slot"))
            if (p.isOp() || p.hasPermission("AdvancedChat.Gui.Color.Yellow")) {
              e.setCancelled(true);
              playerManager.setColor(target, "yellow");
            } else {
              Utils.sendHoverEvent(p, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
              return;
            }
          if (slot == c.getInt("Items.Gold.Slot"))
            if (p.isOp() || p.hasPermission("AdvancedChat.Gui.Color.Gold")) {
              e.setCancelled(true);
              playerManager.setColor(target, "gold");
            } else {
              Utils.sendHoverEvent(p, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
              return;
            }
          if (slot == c.getInt("Items.Dark-Aqua.Slot"))
            if (p.isOp() || p.hasPermission("AdvancedChat.Gui.Color.Dark_Aqua")) {
              e.setCancelled(true);
              playerManager.setColor(target, "dark_aqua");
            } else {
              Utils.sendHoverEvent(p, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
              return;
            }
          if (slot == c.getInt("Items.Aqua.Slot"))
            if (p.isOp() || p.hasPermission("AdvancedChat.Gui.Color.Aqua")) {
              e.setCancelled(true);
              playerManager.setColor(target, "aqua");
            } else {
              Utils.sendHoverEvent(p, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
              return;
            }
          if (slot == c.getInt("Items.Light-Purple.Slot"))
            if (p.isOp() || p.hasPermission("AdvancedChat.Gui.Color.Light_Purple")) {
              e.setCancelled(true);
              playerManager.setColor(target, "light_purple");
            } else {
              Utils.sendHoverEvent(p, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
              return;
            }
          if (slot == c.getInt("Items.Dark-Purple.Slot"))
            if (p.isOp() || p.hasPermission("AdvancedChat.Gui.Color.Dark_Purple")) {
              e.setCancelled(true);
              playerManager.setColor(target, "dark_purple");
            } else {
              Utils.sendHoverEvent(p, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
              return;
            }
          if (slot == c.getInt("Items.Gray.Slot"))
            if (p.isOp() || p.hasPermission("AdvancedChat.Gui.Color.Gray")) {
              e.setCancelled(true);
              playerManager.setColor(target, "gray");
            } else {
              Utils.sendHoverEvent(p, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
              return;
            }
          if (slot == c.getInt("Items.Dark-Gray.Slot"))
            if (p.isOp() || p.hasPermission("AdvancedChat.Gui.Color.Dark_Gray")) {
              e.setCancelled(true);
              playerManager.setColor(target, "dark_gray");
            } else {
              Utils.sendHoverEvent(p, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
              return;
            }
          if (slot == c.getInt("Items.White.Slot"))
            if (p.isOp() || p.hasPermission("AdvancedChat.Gui.Color.White")) {
              e.setCancelled(true);
              playerManager.setColor(target, "white");
            } else {
              Utils.sendHoverEvent(p, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
              return;
            }
          if (slot == c.getInt("Items.Rainbow.Slot"))
            if (p.isOp() || p.hasPermission("AdvancedChat.Gui.Color.RainBow")) {
              e.setCancelled(true);
              playerManager.setColor(target, "rainbow");
            } else {
              Utils.sendHoverEvent(p, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
              return;
            }
          if (slot == c.getInt("Items.Black.Slot"))
            if (p.isOp() || p.hasPermission("AdvancedChat.Gui.Color.Black")) {
              e.setCancelled(true);
              playerManager.setColor(target, "black");
            } else {
              Utils.sendHoverEvent(p, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
              return;
            }
          if (slot == c.getInt("Items.Exit.Slot")) {
            e.setCancelled(true);
            p.closeInventory();
          }
          if (slot == c.getInt("Items.Last.Slot")) {
            e.setCancelled(true);
            p.closeInventory();
            GuiPlayer guiPlayer = new GuiPlayer(this.plugin);
            guiPlayer.openPlayerGui(p, name);
          }
        }
      }
  }

  @EventHandler
  public void onQuit(PlayerQuitEvent e) {
    Player p = e.getPlayer();
    this.plugin.removeInventoryView(p);
  }

  @EventHandler
  public void onInventoryCloseColor(InventoryCloseEvent e) {
    Player p = (Player) e.getPlayer();
    this.plugin.removeInventoryView(p);
  }
}
