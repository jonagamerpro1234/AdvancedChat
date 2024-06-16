package jss.advancedchat.inventory;

import com.cryptomorin.xseries.XMaterial;
import jss.advancedchat.AdvancedChat;
import jss.advancedchat.manager.PlayerManager;
import jss.advancedchat.utils.Util;
import jss.advancedchat.utils.inventory.InventoryUtils;
import jss.advancedchat.utils.inventory.TSkullUtils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class GuiGradient {

    private final AdvancedChat plugin = AdvancedChat.get();
    private ItemStack item;
    private ItemMeta meta;
    private Inventory inv;

    public void open(Player player, String target) {
        plugin.addInventoryView(player, "gradientGui");
        this.create();
        this.addItems(target);
        player.openInventory(inv);
    }

    private void create() {
        FileConfiguration config = plugin.getGradientColorFile().getConfig();
        String title = config.getString("Title");

        inv = Bukkit.createInventory(null, 54, Util.color(title));
    }

    private void addItems(String target) {
        FileConfiguration config = plugin.getGradientColorFile().getConfig();

        int amount = 1;

        item = Util.getPlayerHead(target);
        inv.setItem(4, item);

        PlayerManager playerManager = new PlayerManager(Objects.requireNonNull(Bukkit.getPlayer(target)));

        Set<String> section = Objects.requireNonNull(config.getConfigurationSection("Items")).getKeys(false);

        for (String key : section) {
            String name = config.getString("Items." + key + ".Name");
            int slot = config.getInt("Items." + key + ".Slot");
            List<String> lore = key.contains("Lore") ? new ArrayList<>() : config.getStringList("Items." + key + ".Lore");

            if (!playerManager.isLowMode()) {
                String textures = config.getString("Items." + key + ".Texture");
                textures = TSkullUtils.replace(textures);
                item = Util.createSkull(textures);
            } else {
                String mat = Objects.requireNonNull(config.getString("Items." + key + ".Item")).toUpperCase();
                item = XMaterial.valueOf(mat).parseItem();
            }

            assert item != null;
            meta = item.getItemMeta();
            assert meta != null;
            meta.setDisplayName(Util.color(name));
            meta.setLore(coloredLore(lore));
            item.setItemMeta(meta);
            item.setAmount(amount);
            inv.setItem(slot, item);
        }

        InventoryUtils.setItemChecker(inv, 45, playerManager.isGradient());
    }

    @SuppressWarnings("unused")
    private void setCustomItem(@NotNull PlayerManager playerManager) {
        if (playerManager.isGradient()) {
            item = XMaterial.LIME_DYE.parseItem();
            meta = item.getItemMeta();
            meta.setDisplayName(Util.color("&aEnable"));
            List<String> lore = Arrays.asList("&7Click to &cdisable");
            meta.setLore(coloredLore(lore));
        } else {
            item = XMaterial.GRAY_DYE.parseItem();
            meta = item.getItemMeta();
            meta.setDisplayName(Util.color("&cDisable"));
            List<String> lore = Arrays.asList("&7Click to &aenable");
            meta.setLore(coloredLore(lore));
        }
        item.setItemMeta(meta);
        inv.setItem(45, item);
    }

    private @NotNull List<String> coloredLore(@NotNull List<String> lore) {
        List<String> coloredLore = new ArrayList<>();
        lore.forEach((line) -> {
            String lineColored = Util.color(line);
            coloredLore.add(lineColored);
        });
        return coloredLore;
    }

    private void setDecoration() {
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
