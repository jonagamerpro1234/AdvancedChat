package jss.advancedchat.utils;

import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import jss.advancedchat.AdvancedChat;

public abstract class CountDown {

    private int time;

    protected BukkitTask task;
    protected final AdvancedChat plugin;


    public CountDown(int time, AdvancedChat plugin) {
        this.time = time;
        this.plugin = plugin;
    }
    
    public abstract void count(int current);

    public final void start() {
        task = new BukkitRunnable() {

            @Override
            public void run() {
                count(time);
                if (time-- <= 0) cancel();
            }

        }.runTaskTimer(plugin, 0L, 20L);
    }
    
}
