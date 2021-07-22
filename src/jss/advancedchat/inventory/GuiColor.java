package jss.advancedchat.inventory;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import com.cryptomorin.xseries.XMaterial;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.utils.SkullUtils;
import jss.advancedchat.utils.Utils;

public class GuiColor {

    private AdvancedChat plugin;
    
    public GuiColor(AdvancedChat plugin) {
        this.plugin = plugin;
    }

    public void openGuiColor0(Player player, String player2) {
        FileConfiguration config = plugin.getColorFile().getConfig();
        FileConfiguration invData = plugin.getInventoryDataFile().getConfig();
        
        String title = config.getString("Title");
        title = title.replace("<player>", player2);
        Inventory inv = Bukkit.createInventory(null, 45, Utils.color(title));
        ItemStack item = null;
        SkullMeta skullMeta = null;
        ItemMeta meta = null;
        
        boolean useSkull = invData.getBoolean("Use-Custom-Skull-Color");
        int amont = invData.getInt("Amount-Items");
        String colorglass = invData.getString("Color-Glass.Color");
        
        setDecoration(inv, item, meta, colorglass);

        item = Utils.getPlayerHead(player2);
        inv.setItem(36, item);
                
        for (String key : config.getConfigurationSection("Items").getKeys(false)) {
        	String texture = "Items." +key + ".Texture";
            String material = config.getString("Items." + key + ".Item");
            String name = config.getString("Items." + key + ".Name");
            List<String> lore = config.getStringList("Items." + key + ".Lore");
            int slots = config.getInt("Items." + key + ".Slot");
            
            texture = SkullUtils.replace(texture);
            
            item = XMaterial.valueOf(material).parseItem();
            
            if (useSkull) {
            	item = Utils.createSkull(texture);
                skullMeta = (SkullMeta) item.getItemMeta();
                skullMeta.setDisplayName(Utils.color(name));
                if(lore != null) {
					for (int i = 0; i < lore.size(); i++) {

						String text = (String) lore.get(i);
						text = Utils.color(text);
						text = Utils.getVar(player, text);
						lore.add(text);
					}
					skullMeta.setLore(lore);
                }

                item.setItemMeta(skullMeta);
            } else {
            	
                meta = item.getItemMeta();
                meta.setDisplayName(Utils.color(name));
                if(lore != null) {
					for (int i = 0; i < lore.size(); i++) {

						String text = (String) lore.get(i);
						text = Utils.color(text);
						text = Utils.getVar(player, text);
						lore.add(text);
					}
					skullMeta.setLore(lore);
                }
                item.setItemMeta(meta);
            }
            item.setAmount(amont);
            inv.setItem(slots, item);
        }

       /* String nameBack = config.getString("Decoration.Back.Name");
        String materialback = config.getString("Decoration.Back.Item");
        item = XMaterial.valueOf(materialback).parseItem();
        meta = item.getItemMeta();
        meta.setDisplayName(Utils.color(Utils.getVar(player, nameBack)));
        item.setItemMeta(meta);
        item.setAmount(1);
        inv.setItem(40, item);*/

        player.openInventory(inv);
        plugin.addInventoryPlayer(player, "color");
    }

    public void openGuiColor(Player player, String player2) {
        FileConfiguration config = plugin.getColorFile().getConfig();
        FileConfiguration invData = plugin.getInventoryDataFile().getConfig();
        
        String title = config.getString("Title");
        int amont = invData.getInt("Amount-Items");
        String colorglass = invData.getString("Color-Glass.Color");
        
        
        Inventory inv = Bukkit.createInventory(null, 54, Utils.color(title));
        
        ItemStack item = null;
        ItemMeta meta = null;
        
        setDecoration(inv, item, meta, colorglass);
        
        item = Utils.getPlayerHead(player2);
        inv.setItem(4, item);
        
        for(String key : config.getConfigurationSection("Items-Scroll").getKeys(false)) {
        	int slot = config.getInt("Items-Scroll." + key + ".Slot");
        	String textures = config.getString("Items-Scroll." + key + ".Texture");
        	String name = config.getString("Items-Scroll." + key + ".Name");
        	textures = SkullUtils.replace(textures);
        	
        	item = Utils.createSkull(textures);
        	meta = item.getItemMeta();
        	meta.setDisplayName(Utils.color(name));
        	List<String> list = config.getStringList("Items-Scroll." + key + ".Lore");
        	
        	for(int i = 0; i < list.size(); i++) {
        		String l = (String) list.get(i);
        		list.add(Utils.color(l));
        	}
        	
        	if(list != null) {
        		meta.setLore(list);
        	}
        	item.setItemMeta(meta);
        	item.setAmount(amont);
        	inv.setItem(slot, item);
        }
        
        for(String key : config.getConfigurationSection("Items").getKeys(false)) {
        	
        	String name = config.getString("Items." + key + ".Name");
        	String textures = config.getString("Items." + key + ".Texture");
        	int slot = config.getInt("Items." + key + ".Slot");
        	
        	textures = SkullUtils.replace(textures);
        	
        	item = Utils.createSkull(textures);
        	meta = item.getItemMeta();
        	meta.setDisplayName(Utils.color(name));
        	List<String> list = config.getStringList("Items." + key + ".Lore");
        	
        	for(int i = 0; i < list.size(); i++) {
        		String l = (String) list.get(i);
        		list.add(Utils.color(l));
        	}
        	
        	meta.setLore(list);
        	item.setItemMeta(meta);
        	item.setAmount(amont);
        	inv.setItem(slot, item);
        }
        

        
        //open
        player.openInventory(inv);
    }
    
    private void setDecoration(Inventory inv, ItemStack item, ItemMeta meta, String path) {
        for(int i = 0 ; i < 54; i++) {
            item = XMaterial.valueOf(path).parseItem();
            meta = item.getItemMeta();
            meta.setDisplayName(Utils.color(" "));
            item.setItemMeta(meta);
            item.setAmount(1);
            inv.setItem(i, item);

            if (i == 54) {
                break;
            }
        }
    }
    
    private void setDecoration2(Inventory inv, ItemStack item, ItemMeta meta, String path) {
    	
        for (int i = 0; i < 9; i++) {
            item = XMaterial.valueOf(path).parseItem();
            meta = item.getItemMeta();
            meta.setDisplayName(Utils.color(" "));
            item.setItemMeta(meta);
            item.setAmount(1);
            inv.setItem(i, item);

            if (i == 9) {
                break;
            }
        }

        for (int i = 36; i < 40; i++) {
            item = XMaterial.valueOf(path).parseItem();
            meta = item.getItemMeta();
            meta.setDisplayName(Utils.color(" "));
            item.setItemMeta(meta);
            item.setAmount(1);
            inv.setItem(i, item);

            if (i == 40) {
                break;
            }

        }
        item = XMaterial.valueOf(path).parseItem();
        meta = item.getItemMeta();
        meta.setDisplayName(Utils.color(" "));
        item.setItemMeta(meta);
        item.setAmount(1);
        inv.setItem(9, item);

        item = XMaterial.valueOf(path).parseItem();
        meta = item.getItemMeta();
        meta.setDisplayName(Utils.color(" "));
        item.setItemMeta(meta);
        item.setAmount(1);
        inv.setItem(18, item);

        item = XMaterial.valueOf(path).parseItem();
        inv.setItem(17, item);

        item = XMaterial.valueOf(path).parseItem();
        meta = item.getItemMeta();
        meta.setDisplayName(Utils.color(" "));
        item.setItemMeta(meta);
        item.setAmount(1);
        inv.setItem(26, item);

        item = XMaterial.valueOf(path).parseItem();
        meta = item.getItemMeta();
        meta.setDisplayName(Utils.color(" "));
        item.setItemMeta(meta);
        item.setAmount(1);
        inv.setItem(27, item);

        item = XMaterial.valueOf(path).parseItem();
        meta = item.getItemMeta();
        meta.setDisplayName(Utils.color(" "));
        item.setItemMeta(meta);
        item.setAmount(1);
        inv.setItem(35, item);

        for (int i = 28; i < 30; i++) {
            item = XMaterial.valueOf(path).parseItem();
            meta = item.getItemMeta();
            meta.setDisplayName(Utils.color(" "));
            item.setItemMeta(meta);
            item.setAmount(1);
            inv.setItem(i, item);
            
            if (i == 30) {
                break;
            }
        }
        for (int i = 33; i < 35; i++) {
            item = XMaterial.valueOf(path).parseItem();
            meta = item.getItemMeta();
            meta.setDisplayName(Utils.color(" "));
            item.setItemMeta(meta);
            item.setAmount(1);
            inv.setItem(i, item);

            if (i == 35) {
                break;
            }
        }

        for (int i = 41; i < 45; i++) {
            item = XMaterial.valueOf(path).parseItem();
            meta = item.getItemMeta();
            meta.setDisplayName(Utils.color(" "));
            item.setItemMeta(meta);
            item.setAmount(1);
            inv.setItem(i, item);

            if (i == 45) {
                break;
            }
        }
    }

}
