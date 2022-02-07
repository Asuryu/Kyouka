package me.redextremept.kyouka.listeners;

import me.redextremept.kyouka.Main;
import me.redextremept.kyouka.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;


public class DeathListener
  implements Listener
{
  @SuppressWarnings("unused")
  private static Main plugin;
  String Prefix = ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "Kyouka" + ChatColor.DARK_GRAY + "] " + ChatColor.GRAY;
  String Green = ChatColor.DARK_GRAY + "[" + ChatColor.GREEN + "*" + ChatColor.DARK_GRAY + "] ";
  String Red = ChatColor.DARK_GRAY + "[" + ChatColor.RED + "*" + ChatColor.DARK_GRAY + "] ";
  
  public DeathListener(Main plugin)
  {  
    Bukkit.getPluginManager().registerEvents(this, plugin);
  }
  
  @EventHandler
  public void onPlayerDeath(PlayerDeathEvent event)
  {
    if ((event.getEntity().getKiller() instanceof Player))
    {
      Player killer = event.getEntity().getKiller();
      Player player = event.getEntity();
      
      if (player != killer) {
        killer.sendMessage(Utils.chat(Green + "&aYou have killed " + ChatColor.GOLD + player.getDisplayName() + "&a!"));
        player.sendMessage(Utils.chat(Red + "&cYou have been killed by " + ChatColor.GOLD + killer.getDisplayName() + "&c!"));
      }
    } else {
      Player player = event.getEntity();
      player.sendMessage(Utils.chat(Red + "&cYou have commited suicide."));
    }
  }
}
