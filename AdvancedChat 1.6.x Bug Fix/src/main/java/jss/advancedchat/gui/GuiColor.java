package jss.advancedchat.gui;

import com.cryptomorin.xseries.XMaterial;
import jss.advancedchat.AdvancedChat;
import jss.advancedchat.manager.ConfigManager;
import jss.advancedchat.utils.Utils;
import jss.advancedchat.utils.inventory.TSkullUtils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GuiColor {
    private final AdvancedChat plugin;

    private ItemStack item;

    private ItemMeta meta;

    public GuiColor(AdvancedChat plugin) {
        this.plugin = plugin;
    }

    public void open(Player player, String player2) {
        FileConfiguration config = ConfigManager.getColorGuiConfig();
        String title = config.getString("Title");
        assert title != null;
        title = title.replace("<player>", player2);
        Inventory inv = Bukkit.createInventory(null, 45, Utils.color(title));
        String materialGlass = config.getString("Decoration.Glass-Color.Item");
        setDecoration(inv, materialGlass);
        this.item = Utils.createSkull(player2);
        inv.setItem(36, this.item);
        for (String key : Objects.requireNonNull(config.getConfigurationSection("Items")).getKeys(false)) {
            String name = config.getString("Items." + key + ".Name");
            String textures = config.getString("Items." + key + ".Texture");
            int slot = config.getInt("Items." + key + ".Slot");
            List<String> lore = key.contains("Lore") ? new ArrayList<>() : config.getStringList("Items." + key + ".Lore");
            textures = TSkullUtils.replace(textures);
            this.item = Utils.createSkull(textures);
            this.meta = this.item.getItemMeta();
            assert this.meta != null;
            this.meta.setDisplayName(Utils.color(name));
            this.meta.setLore(coloredLore(lore));
            this.item.setItemMeta(this.meta);
            this.item.setAmount(1);
            inv.setItem(slot, this.item);
        }
        this.plugin.addInventoryView(player, "colorGui");
        player.openInventory(inv);
    }

    private @NotNull List<String> coloredLore(@NotNull List<String> lore) {
        List<String> coloredLore = new ArrayList<>();
        lore.forEach(line -> {
            String lineColored = Utils.color(line);
            coloredLore.add(lineColored);
        });
        return coloredLore;
    }

    private void setDecoration(Inventory inv, String path) {
        for (int i = 0; i < 45; i++) {
            this.item = XMaterial.valueOf(path).parseItem();
            assert this.item != null;
            this.meta = this.item.getItemMeta();
            assert this.meta != null;
            this.meta.setDisplayName(" ");
            this.item.setItemMeta(this.meta);
            this.item.setAmount(1);
            inv.setItem(i, this.item);
        }
    }
}
