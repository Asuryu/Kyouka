package me.redextremept.kyouka.listeners;

import me.redextremept.kyouka.Main;
import me.redextremept.kyouka.utils.Utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;


@SuppressWarnings("deprecation")
public class ChatListener
  implements Listener
{
  @SuppressWarnings("unused")
  private static Main plugin;
  String Prefix = ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "Kyouka" + ChatColor.DARK_GRAY + "] " + ChatColor.GRAY;
  String Green = ChatColor.DARK_GRAY + "[" + ChatColor.GREEN + "*" + ChatColor.DARK_GRAY + "] ";
  String Red = ChatColor.DARK_GRAY + "[" + ChatColor.RED + "*" + ChatColor.DARK_GRAY + "] ";
  
  public ChatListener(Main plugin)
  {
	  Bukkit.getPluginManager().registerEvents(this, plugin);
  }
  
  @EventHandler
  public void onMessageDot(AsyncPlayerChatEvent e) {
    if (e.getMessage().equalsIgnoreCase(".")) {
    	e.setCancelled(true);
    }
    else if (e.getMessage().startsWith("..")){
    	e.setCancelled(true);
    }
  }
  
  @EventHandler
  public void onPlayerChat (PlayerChatEvent event) throws IOException {
    if (((event.getPlayer() instanceof Player)) && (event.getMessage().equalsIgnoreCase("."))) {
      Player player = event.getPlayer();
      Location location = player.getLocation();
      int x = location.getBlockX();
      int y = location.getBlockY();
      int z = location.getBlockZ();
      Bukkit.broadcastMessage(Prefix + Utils.chat(new StringBuilder("&3").append(player.getName()).append(" &8@&6 ").append(x).append(" ").append(y).append(" ").append(z).append(" &8@&5 ").append(player.getWorld().getName()).toString()));
    } else if (((event.getPlayer() instanceof Player)) && (event.getMessage().startsWith(".."))) {
    	Player player = event.getPlayer();
        Location location = player.getLocation();
        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();
    	String locationName = event.getMessage();
    	String newLocationName = locationName.substring(locationName.indexOf(".") + 2);
    	Bukkit.broadcastMessage(Prefix + Utils.chat("&3" + newLocationName + " &8@&7 " + x + " " + y + " " + z + " &5(" + player.getWorld().getName() + ")"));
    	
    	FileWriter fileWriter = new FileWriter("./plugins/Kyouka/Locations.txt", true);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.println("&3" + newLocationName + "&8: " + x + " " + y + " " + z + " &5(" + player.getWorld().getName() + ") &8by&6 " + player.getDisplayName());
        printWriter.close();
    }
  }
}
