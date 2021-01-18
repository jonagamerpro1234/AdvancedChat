package jss.advancedchat.test;

import java.util.Random;
import java.util.UUID;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.utils.EventUtils;

public class PlayerManager {

	private AdvancedChat plugin;
	@SuppressWarnings("unused")
	private EventUtils eventUtils = new EventUtils(plugin);
	public UUID uuid;
	public String name;
	private float range;
	private int spam;
	private String[] ColorCodes = { "§0", "§1", "§2", "§3", "§4", "§5", "§6", "§7", "§8", "§9", "§b", "§c", "§a" };
	private boolean badword;
	
	public PlayerManager(AdvancedChat plugin) {
		super();
		this.plugin = plugin;
		this.uuid = null;
		this.name = null;
		this.range = 0;
		this.spam = 0;
		this.badword = false;
	}

	public boolean isBadword() {
		return badword;
	}

	public void setBadword(boolean badword) {
		this.badword = badword;
	}

	public int getSpam() {
		return spam;
	}

	public void setSpam(int spam) {
		this.spam = spam;
	}

	public float getRange() {
		return range;
	}

	public void setRange(float range) {
		this.range = range;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isMute(Player player) {
		FileConfiguration config = plugin.getPlayerDataFile().getConfig();
		for (String key : config.getConfigurationSection("Players").getKeys(false)) {
			if (key.contains(player.getName())) {
				return config.getBoolean("Players." + key + ".Mute");
			}
		}
		return false;
	}

	public String getStateMute(Player player) {
		FileConfiguration config = plugin.getPlayerDataFile().getConfig();
		for (String key : config.getConfigurationSection("Players").getKeys(false)) {
			if (key.contains(player.getName())) {
				String w = config.getString("Players." + key + ".Mute");
				return w;
			}
		}
		return null;
	}

	public void setMute(Player player, boolean mute) {
		FileConfiguration config = plugin.getPlayerDataFile().getConfig();
		if (player == null) {
			//Utils.sendColorMessage(eventUtils.getConsoleSender(), "No se pudo encontrar el jugador");
			return;
		}
		if (config.contains("Players." + player.getName() + ".Mute")) {
			config.set("Players." + player.getName() + ".Mute", mute);
			plugin.getPlayerDataFile().saveConfig();
		}
	}

	public String getColor(Player player, String text) {
		FileConfiguration config = plugin.getPlayerDataFile().getConfig();

		for (String key : config.getConfigurationSection("Players").getKeys(false)) {
			if (key.contains(player.getName())) {
				String color = config.getString("Players." + key + ".Color");
				return convertColor(color, text);
			}
		}
		return null;
	}

	public String getColorPlayer(Player player, FileConfiguration config) {
		for (String key : config.getConfigurationSection("Players").getKeys(false)) {
			if (key.contains(player.getName())) {
				String color = config.getString("Players." + key + ".Color");
				return color;
			}
		}
		return null;
	}

	public void setColor(Player player, String color) {
		FileConfiguration config = plugin.getPlayerDataFile().getConfig();
		if (config.contains("Players." + player.getName() + ".Color")) {
			config.set("Players." + player.getName() + ".Color", color);
			plugin.getPlayerDataFile().saveConfig();
		} else {
			//Utils.sendColorMessage(eventUtils.getConsoleSender(), "No se pudo encontrar el jugador");
		}
	}

	public boolean checkPlayerList(Player player) {
		FileConfiguration config = plugin.getPlayerDataFile().getConfig();
		for (String key : config.getConfigurationSection("Players").getKeys(false)) {
			if (key.contains(player.getName())) {
				//Utils.sendColorMessage(player, "&aExiste " + player.getName());
				return true;
			}
		}
		//Utils.sendColorMessage(player, "&cNo Existe " + player.getName());
		return false;
	}

	public boolean removePlayerlist(Player player) {
		FileConfiguration config = plugin.getPlayerDataFile().getConfig();
		for (String key : config.getConfigurationSection("Players").getKeys(false)) {
			if (key.contains(player.getName())) {
				config.set("Players." + key, null);
				plugin.getPlayerDataFile().saveConfig();
				//Utils.sendColorMessage(player, "&a" + player.getName() + " Ha sido removido de la lista!");
				return true;
			}
		}
		//Utils.sendColorMessage(player, "&cNo se pudo encontrar a " + player.getName());
		return false;
	}

	private String convertColor(String color, String text) {
		String temp = color;
		if (temp.equalsIgnoreCase("Dark_Red")) {
			return "&4" + text;
		}
		if (temp.equalsIgnoreCase("Red")) {
			return "&c" + text;
		}
		if (temp.equalsIgnoreCase("Dark_Blue")) {
			return "&1" + text;
		}
		if (temp.equalsIgnoreCase("Blue")) {
			return "&9" + text;
		}
		if (temp.equalsIgnoreCase("Dark_Green")) {
			return "&2" + text;
		}
		if (temp.equalsIgnoreCase("Green")) {
			return "&a" + text;
		}
		if (temp.equalsIgnoreCase("Yellow")) {
			return "&e" + text;
		}
		if (temp.equalsIgnoreCase("Gold")) {
			return "&6" + text;
		}
		if (temp.equalsIgnoreCase("Dark_Aqua")) {
			return "&3" + text;
		}
		if (temp.equalsIgnoreCase("Aqua")) {
			return "&b" + text;
		}
		if (temp.equalsIgnoreCase("Light_Purple")) {
			return "&d" + text;
		}
		if (temp.equalsIgnoreCase("Dark_Purple")) {
			return "&5" + text;
		}
		if (temp.equalsIgnoreCase("Gray")) {
			return "&7" + text;
		}
		if (temp.equalsIgnoreCase("Dark_Gray")) {
			return "&8" + text;
		}
		if (temp.equalsIgnoreCase("White")) {
			return "&f" + text;
		}
		if (temp.equalsIgnoreCase("Black")) {
			return "&0" + text;
		}
		if (temp.equalsIgnoreCase("RainBow")) {
			StringBuffer stringBuffer = new StringBuffer();

			for (char c : text.toCharArray()) {
				stringBuffer.append(setRandomColor() + c);
			}
			return stringBuffer.toString();
		}
		return null;
	}

	public String setRandomColor() {
		Random r = new Random();
		return ColorCodes[r.nextInt((this.ColorCodes.length))];
	}
}
