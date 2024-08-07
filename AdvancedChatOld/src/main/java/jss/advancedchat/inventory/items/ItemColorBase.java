package jss.advancedchat.inventory.items;

import com.cryptomorin.xseries.XMaterial;
import jss.advancedchat.manager.PlayerManager;
import jss.advancedchat.utils.Util;
import jss.advancedchat.utils.Utils;
import jss.advancedchat.utils.inventory.TSkullUtils;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ItemColorBase {

    private final FileConfiguration config;
    private final PlayerManager playerManager;

    public ItemColorBase(PlayerManager playerManager, FileConfiguration config) {
        this.playerManager = playerManager;
        this.config = config;
    }

    public ItemStack getItemColor(@NotNull String color, String key, String value) {
        String name = config.getString("Items." + color + ".Name");
        List<String> lore = color.contains("Lore") ? new ArrayList<>() : config.getStringList("Items." + color + ".Lore");

        ItemStack item;
        if (playerManager.isLowMode()) {
            String textures = config.getString("Items." + color + ".Texture");
            textures = TSkullUtils.replace(textures);
            item = Util.createSkull(textures);
        } else {
            String mat = Objects.requireNonNull(config.getString("Items." + color + ".Item")).toUpperCase();
            item = XMaterial.valueOf(mat).parseItem();
        }

        assert item != null;
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName(Util.color(name));
        meta.setLore(Utils.coloredLore(lore));
        item.setItemMeta(meta);

        Utils.setStringItemNbt(item, key, value);

        return item;
    }

}
