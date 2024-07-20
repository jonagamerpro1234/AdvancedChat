package jss.advancedchat.inventory;

import com.cryptomorin.xseries.XMaterial;
import jss.advancedchat.AdvancedChat;
import jss.advancedchat.manager.PlayerManager;
import jss.advancedchat.files.utils.Settings;
import jss.advancedchat.utils.Util;
import jss.advancedchat.utils.inventory.TSkullUtils;
import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class GuiSettings {

    private final AdvancedChat plugin = AdvancedChat.get();
    private Inventory inv;
    private ItemStack item;
    private ItemMeta meta;

    public void open(Player player, String target) {
        plugin.addInventoryView(player, "settingsGui");
        create();
        addItems(player, target);
        player.openInventory(inv);
    }

    private void create() {
        inv = Bukkit.createInventory(null, 54, Util.color("&b&lMenu &8&l> &e&lSettings"));
    }

    private void addItems(Player player, String target) {
        this.setDecoration();

        item = Util.getPlayerHead(target);
        inv.setItem(4, item);

        PlayerManager playerManager = new PlayerManager(Objects.requireNonNull(Bukkit.getPlayer(target)));

        if (!playerManager.isLowMode()) {
            item = Util.createSkull(TSkullUtils.replace("[exit]"));
        } else {
            item = XMaterial.RED_STAINED_GLASS_PANE.parseItem();
        }

        assert item != null;
        meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName(Util.color("&c&lExit"));
        item.setItemMeta(meta);
        item.setAmount(1);
        inv.setItem(0, item);

        if (!playerManager.isLowMode()) {
            item = Util.createSkull(TSkullUtils.replace("[last]"));
        } else {
            item = XMaterial.YELLOW_STAINED_GLASS_PANE.parseItem();
        }

        assert item != null;
        meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName(Util.color("&e&lLast Menu"));
        item.setItemMeta(meta);
        item.setAmount(1);
        inv.setItem(3, item);

        isLowModeItem(playerManager, player);
        isChat(playerManager, player);
        isMention(playerManager, player);
        isMsg(playerManager, player);

        item = XMaterial.BEDROCK.parseItem();
        meta.setDisplayName(Util.color("&e?"));
        item.setItemMeta(meta);
        item.setAmount(1);
        inv.setItem(37, item);

        item = XMaterial.BEDROCK.parseItem();
        meta.setDisplayName(Util.color("&e?"));
        item.setItemMeta(meta);
        item.setAmount(1);
        inv.setItem(39, item);

        item = XMaterial.BEDROCK.parseItem();
        meta.setDisplayName(Util.color("&e?"));
        item.setItemMeta(meta);
        item.setAmount(1);
        inv.setItem(41, item);

        item = XMaterial.BEDROCK.parseItem();
        meta.setDisplayName(Util.color("&e?"));
        item.setItemMeta(meta);
        item.setAmount(1);
        inv.setItem(43, item);

        item = XMaterial.EMERALD.parseItem();
        meta.setDisplayName(Util.color("&eUpdate Inventories"));
        item.setItemMeta(meta);
        inv.setItem(45, item);
    }


    public void isLowModeItem(@NotNull PlayerManager playerManager, Player player) {
        ItemStack item = XMaterial.REPEATER.parseItem();
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Util.color("&eLow Mode"));

        if (playerManager.isLowMode()) {
            meta.addEnchant(Enchantment.DURABILITY, 1, false);
        }

        List<String> lore = Collections.singletonList(Settings.message_NoPermission);
        if (!player.isOp() || !player.hasPermission("AdvancedChat.Gui.Settings.LowMode")) {
            meta.setLore(coloredLore(lore));
        }

        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        inv.setItem(19, item);
    }

    public void isChat(@NotNull PlayerManager playerManager, Player player) {
        ItemStack item = XMaterial.REPEATER.parseItem();
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Util.color("&eChat"));

        if (playerManager.isChat()) {
            meta.addEnchant(Enchantment.DURABILITY, 1, false);
        }

        List<String> lore = Collections.singletonList(Settings.message_NoPermission);
        if (!player.isOp() || !player.hasPermission("AdvancedChat.Gui.Settings.Chat")) {
            meta.setLore(coloredLore(lore));
        }

        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        inv.setItem(21, item);
    }

    public void isMention(@NotNull PlayerManager playerManager, Player player) {
        ItemStack item = XMaterial.REPEATER.parseItem();
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Util.color("&eMention"));

        if (playerManager.isMention()) {
            meta.addEnchant(Enchantment.DURABILITY, 1, false);
        }

        List<String> lore = Collections.singletonList(Settings.message_NoPermission);
        if (!player.isOp() || !player.hasPermission("AdvancedChat.Gui.Settings.Mention")) {
            meta.setLore(coloredLore(lore));
        }

        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        inv.setItem(23, item);
    }

    public void isMsg(@NotNull PlayerManager playerManager, Player player) {
        ItemStack item = XMaterial.REPEATER.parseItem();
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Util.color("&eMsg"));

        if (playerManager.isMention()) {
            meta.addEnchant(Enchantment.DURABILITY, 1, false);
        }

        List<String> lore = Collections.singletonList(Settings.message_NoPermission);
        if (!player.isOp() || !player.hasPermission("AdvancedChat.Gui.Settings.Msg")) {
            meta.setLore(coloredLore(lore));
        }

        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        inv.setItem(25, item);
    }

    public List<String> coloredLore(@NotNull List<String> lore) {
        List<String> colored = new ArrayList<>();
        lore.forEach((line) -> {
            String lineColored = Util.color(line);
            colored.add(lineColored);
        });
        return colored;
    }

    public void setDecoration() {
        for (int i = 0; i < 54; i++) {
            item = XMaterial.BLACK_STAINED_GLASS_PANE.parseItem();
            meta = item.getItemMeta();
            meta.setDisplayName(Util.color(" "));
            item.setItemMeta(meta);
            item.setAmount(1);
            inv.setItem(i, item);

        }
    }

}
