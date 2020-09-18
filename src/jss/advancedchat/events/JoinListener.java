package jss.advancedchat.events;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.utils.UpdateChecker;
import jss.advancedchat.utils.UtilsEvents;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener
  implements Listener
{
  private AdvancedChat plugin;
  
  public JoinListener(AdvancedChat plugin)
  {
    this.plugin = plugin;
    UtilsEvents.getManager().registerEvents(this, plugin);
  }
  
  @EventHandler
  public void sendUpdatePlayer(PlayerJoinEvent e)
  {
    Player j = e.getPlayer();
    UpdateChecker update = new UpdateChecker(this.plugin);
    if ((!j.hasPermission("AdvancedChat.Update.Notify")) || (!j.isOp())) {
      return;
    }
    update.Update(j);
  }
}
