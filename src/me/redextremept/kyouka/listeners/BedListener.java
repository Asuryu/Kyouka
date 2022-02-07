package me.redextremept.kyouka.listeners;

import me.redextremept.kyouka.Main;
import me.redextremept.kyouka.utils.Utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;

public class BedListener implements org.bukkit.event.Listener
{
  int counter = 0;
  boolean trigger = false;
  

  @SuppressWarnings("unused")
  public static Main plugin;
  
  String Prefix = ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "Kyouka" + ChatColor.DARK_GRAY + "] " + ChatColor.GRAY;
  String Green = ChatColor.DARK_GRAY + "[" + ChatColor.GREEN + "*" + ChatColor.DARK_GRAY + "] ";
  String Red = ChatColor.DARK_GRAY + "[" + ChatColor.RED + "*" + ChatColor.DARK_GRAY + "] ";
  
  public BedListener(Main plugin)
  {
    Bukkit.getPluginManager().registerEvents(this, plugin);
  }
  

@EventHandler
  public void onPlayerSleep(PlayerBedEnterEvent event)
  {
    int maxOnline = (int) Math.ceil(Bukkit.getOnlinePlayers().size() / 2.0);
    int countdown = 200;
    Player playerino = event.getPlayer();
    World world = playerino.getWorld();
    
    
    // if(event.getBed().toString() == "OK") {
    if(event.isCancelled()) {
    	playerino.sendMessage(Utils.chat(Red + "&cYou can't sleep right now."));
    } else {
    	if (((event.getPlayer() instanceof Player)) && (maxOnline == 1)) {
    		counter = 0;
            trigger = true;
            //for (Player player : Bukkit.getOnlinePlayers()) {
            	//player.sendTitle(Utils.chat("&7The night has been skipped!"), Utils.chat("&6You are experiencing a new dawn!"));
            //}
            while (countdown != 0) {
            	for (Player player : Bukkit.getOnlinePlayers()) {
                    player.getWorld().playEffect(player.getLocation(), Effect.DRAGON_BREATH, 2004);
                }
            	countdown -= 1;
            }
            Bukkit.broadcastMessage(Utils.chat(Prefix + "&3Skipping the night everyone!"));
            world.playSound(playerino.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 5, 1);
            world.setTime(1000L);
            world.setStorm(false);
            counter = 0;
            trigger = true;
    	}
    	if (((event.getPlayer() instanceof Player)) && (counter < maxOnline)) {
    		counter += 1;
    		//for (Player player : Bukkit.getOnlinePlayers()) {
    			//player.sendTitle(Utils.chat("&6" + (maxOnline - counter) + " &7more players needed to skip the night!"), Utils.chat("&8" + playerino.getName() + " &8has voted to skip!"));
    		//}
    		Bukkit.broadcastMessage(Utils.chat(Prefix + "&7" + playerino.getName() + " &3is ready to sleep " + "&8(" + (counter) + "/" + maxOnline + ")"));
            world.playSound(playerino.getLocation(), Sound.BLOCK_ENDER_CHEST_OPEN, 5, 1);
            trigger = false;

    	}
    	
        if (((event.getPlayer() instanceof Player)) && (counter >= maxOnline)) {
        	counter += 1;
        	Bukkit.broadcastMessage(Utils.chat(Prefix + "&7" + playerino.getName() + " &3is ready to sleep " + "&8(" + (counter - 1) + "/" + maxOnline + ")"));
            // world.playSound(playerino.getLocation(), Sound.BLOCK_GLASS_BREAK, 1.0F, 1.0F);
            counter = 0;
            trigger = true;
            //for (Player player : Bukkit.getOnlinePlayers()) {
            	//player.sendTitle(Utils.chat("&7The night has been skipped!"), Utils.chat("&6You are experiencing a new dawn!"));
            //}
            while (countdown != 0) {
            	for (Player player : Bukkit.getOnlinePlayers()) {
                    player.getWorld().playEffect(player.getLocation(), Effect.DRAGON_BREATH, 2004);
                }
            	countdown -= 1;
            }
            Bukkit.broadcastMessage(Utils.chat(Prefix + "&3Skipping the night everyone!"));
            world.playSound(playerino.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 5, 1);
            world.setTime(1000L);
            world.setStorm(false);
            counter = 0;
            trigger = true;
      }
    }
  }
  
  @EventHandler
  public void onPlayerWake(PlayerBedLeaveEvent e)
  {
	int maxOnline = (int) Math.ceil(Bukkit.getOnlinePlayers().size() / 2);
    
    if (counter != 0) {
      counter -= 1;
      Player playerino = e.getPlayer();
      //for (Player player : Bukkit.getOnlinePlayers()) {
    	  //player.sendTitle(Utils.chat("&6" + (maxOnline - counter) + " &7more players needed to skip the night!"), Utils.chat("&8" + playerino.getName() + " &8doesn't want to sleep!"));
      //}
      World world = playerino.getWorld();
      Bukkit.broadcastMessage(Utils.chat(Prefix + "&7" + playerino.getName() + " &3doesn't want to sleep " + "&8(" + counter + "/" + maxOnline + ")"));
      world.playSound(playerino.getLocation(), Sound.BLOCK_ENDER_CHEST_CLOSE, 5, 1);
    }
  }
}
