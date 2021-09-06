package jss.advancedchat.manager;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import jss.advancedchat.AdvancedChat;

public class CoolDownManager {

	//comming soon
	
	private Map<UUID,Integer> cooldownMap = new HashMap<>();
	
	public CoolDownManager(AdvancedChat plugin) {
		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				for(UUID uuid: cooldownMap.keySet()) {
					if(cooldownMap.get(uuid) == 1) {
						cooldownMap.remove(uuid);
						continue;
					}
					cooldownMap.put(uuid,cooldownMap.get(uuid)-1);
				}
			}
		}.runTaskTimer(plugin, 0, 20);
	}
	
	public void addPlayerToMap(Player player, Integer time) {
		cooldownMap.put(player.getUniqueId(), time);
	}
	
	public boolean isPlayerToMap(Player player) {
		return cooldownMap.containsKey(player.getUniqueId());
	}
	
	public Integer getTimingRemaining(Player player) {
		if(!isPlayerToMap(player)) {
			return 0;	
		}else {
			return cooldownMap.get(player.getUniqueId());
		}
	}
}
