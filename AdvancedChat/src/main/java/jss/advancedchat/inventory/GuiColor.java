package jss.advancedchat.inventory;

import com.cryptomorin.xseries.XMaterial;
import jss.advancedchat.AdvancedChat;
import jss.advancedchat.inventory.items.ItemColorBase;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class GuiColor {

	private final AdvancedChat plugin = AdvancedChat.get();
	private Inventory inv;
	private ItemStack item;
	private ItemMeta meta;
	
	public void open(Player player, String target) {
		plugin.addInventoryView(player, "colorGui");
		create();
		setItems(target);
		player.openInventory(inv);
	}
	
	private void create() {
		FileConfiguration config = plugin.getColorFile().getConfig();

		String title = config.getString("Title");
		inv = Bukkit.createInventory(null, 54, Util.color(title));
	}
	
	private void setItems(String target) {
		FileConfiguration config = plugin.getColorFile().getConfig();

		setDecoration(inv, XMaterial.BLACK_STAINED_GLASS_PANE.toString());

		item = Util.getPlayerHead(target);
		inv.setItem(4, item);
		
		PlayerManager playerManager = new PlayerManager(Bukkit.getPlayer(target));

		ItemColorBase itemColorBase = new ItemColorBase(playerManager,config);

		item = itemColorBase.getItemColor("Dark-Red","color_gui_dark_red","dark_red");
		inv.setItem(19, item);

		item = itemColorBase.getItemColor("Red","color_gui_red","red");
		inv.setItem(20, item);

		item = itemColorBase.getItemColor("Dark-Blue","color_gui_dark_blue","dark_blue");
		inv.setItem(21, item);

		item = itemColorBase.getItemColor("Blue","color_gui_blue","blue");
		inv.setItem(22, item);

		item = itemColorBase.getItemColor("Dark-Green","color_gui_dark_green","dark_green");
		inv.setItem(23, item);

		item = itemColorBase.getItemColor("Green","color_gui_green","green");
		inv.setItem(24, item);

		item = itemColorBase.getItemColor("Dark-","color_gui_dark_","dark_");
		inv.setItem(20, item);

		item = itemColorBase.getItemColor("Yellow","color_gui_yellow","yellow");
		inv.setItem(20, item);

		item = itemColorBase.getItemColor("Gold","color_gui_gold","gold");
		inv.setItem(20, item);

		item = itemColorBase.getItemColor("","color_gui_","");
		inv.setItem(20, item);

		item = itemColorBase.getItemColor("Dark-","color_gui_dark_","dark_");
		inv.setItem(20, item);

		item = itemColorBase.getItemColor("","color_gui_","");
		inv.setItem(20, item);

		item = itemColorBase.getItemColor("Dark-","color_gui_dark_","dark_");
		inv.setItem(20, item);

		item = itemColorBase.getItemColor("","color_gui_","");
		inv.setItem(20, item);

		
		InventoryUtils.setItemChecker(inv, 45, playerManager.isColor());
	}
	
	private @NotNull List<String> coloredLore(@NotNull List<String> lore) {
		List<String> coloredlore = new ArrayList<>();
		lore.forEach((line) -> {
			String lineColored = Util.color(line);
			coloredlore.add(lineColored);
		});
		return coloredlore;
	}

	private void setDecoration(Inventory inv, String path) {
		for (int i = 0; i < 54; i++) {
			item = XMaterial.valueOf(path).parseItem();
			meta = item.getItemMeta();
			meta.setDisplayName(Util.color(" "));
			item.setItemMeta(meta);
			item.setAmount(1);
			inv.setItem(i, item);

		}
	}
	

} 