package jss.advancedchat.inventory.items;

import com.cryptomorin.xseries.XMaterial;
import jss.advancedchat.manager.PlayerManager;
import jss.advancedchat.utils.Util;
import jss.advancedchat.utils.inventory.TSkullUtils;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ItemColorBase {

    private ItemStack item;
    private ItemMeta meta;
    private FileConfiguration config;
    private PlayerManager playerManager;

    public ItemColorBase(PlayerManager playerManager, FileConfiguration config){
        this.playerManager = playerManager;
        this.config = config;
    }

    public ItemStack getItemColor(@NotNull String color, String key, String value){
        String name = config.getString(color + ".Name");
        List<String> lore = color.contains("Lore") ? new ArrayList<>() : config.getStringList(color + ".Lore");

        if(!playerManager.isLowMode()) {
            String textures = config.getString(color + ".Texture");
            textures = TSkullUtils.replace(textures);
            item = Util.createSkull(textures);
        }else {
            String mat = config.getString(color + ".Item").toUpperCase();
            item = XMaterial.valueOf(mat).parseItem();
        }

        meta = item.getItemMeta();
        meta.setDisplayName(Util.color(name));
        meta.setLore(jss.advancedchat.v2.Util.coloredLore(lore));
        item.setItemMeta(meta);

        jss.advancedchat.v2.Util.setStringItemNbt(item,"","");
        
        return item;
    }
    
}
