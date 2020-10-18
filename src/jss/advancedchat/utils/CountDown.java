package jss.advancedchat.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import jss.advancedchat.AdvancedChat;

public class CountDown {

	public static String getCountDown(AdvancedChat plugin, Player player, FileConfiguration config) {

		for(String key : config.getConfigurationSection("Players-Data").getKeys(false)) {
			String pathtime = "Players-Data."+key+".Mute-Time";
			if (config.contains(pathtime)) {
				String timecooldownString = config.getString(pathtime);
				long timecooldown = Long.valueOf(timecooldownString);
				long millis = System.currentTimeMillis();
				long cooldown = 100; // En Segundos
				long cooldownmil = cooldown * 1000;

				long espera = millis - timecooldown;
				long esperaDiv = espera / 1000;
				long esperatotalseg = cooldown - esperaDiv;
				long esperatotalmin = esperatotalseg / 60;
				long esperatotalhour = esperatotalmin / 60;
				if (((timecooldown + cooldownmil) > millis) && (timecooldown != 0)) {
					if (esperatotalseg > 59) {
						esperatotalseg = esperatotalseg - 60 * esperatotalmin;
					}
					String time = "";
					if (esperatotalseg != 0) {
						time = esperatotalseg + "s";
					}

					if (esperatotalmin > 59) {
						esperatotalmin = esperatotalmin - 60 * esperatotalhour;
					}
					if (esperatotalmin > 0) {
						time = esperatotalmin + "min" + " " + time;
					}

					if (esperatotalhour > 0) {
						time = esperatotalhour + "h" + " " + time;
					}

					// Aun no se termina el cooldown
					//player.sendMessage("Puedes reclamar otra recompensa diaria dentro de " + time);
				} else {
					// Ya se termino el cooldown
					//player.sendMessage("Acabas de recibir la recompensa diaria nuevamente");
					config.set(pathtime, millis);
					plugin.saveConfig();
				}
			} else {
				// Usa el comando por primera vez, ya que no existe el path en la config
				player.sendMessage("Acabas de recibir la recompensa diaria");
				long millis = System.currentTimeMillis();
				config.set(pathtime, millis);
				plugin.saveConfig();
			}

		}
		return "-1";
	}
		


}
