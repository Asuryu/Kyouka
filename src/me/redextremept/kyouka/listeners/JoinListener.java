package me.redextremept.kyouka.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.redextremept.kyouka.Main;
import me.redextremept.kyouka.utils.Utils;

public class JoinListener implements Listener {
	
	private static Main plugin;
	
	String Green = ChatColor.DARK_GRAY + "[" + ChatColor.GREEN + "*" + ChatColor.DARK_GRAY + "] ";
	String Red = ChatColor.DARK_GRAY + "[" + ChatColor.RED + "*" + ChatColor.DARK_GRAY + "] ";
	String Gold = ChatColor.DARK_GRAY + "[" + ChatColor.MAGIC + ChatColor.GOLD + "*" + ChatColor.DARK_GRAY + "] ";
	
	@SuppressWarnings("static-access")
	public JoinListener(Main plugin) {
		this.plugin = plugin;
		
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		event.setJoinMessage("");
		
		Player player = event.getPlayer();
		
		if(!player.hasPlayedBefore()) {
			Bukkit.broadcastMessage(Gold + Utils.chat(plugin.getConfig().getString("firstJoin_message").replace("<player>", player.getDisplayName())));
			
		} else {
			Bukkit.broadcastMessage(Green + Utils.chat(plugin.getConfig().getString("join_message").replace("<player>", player.getDisplayName())));
		}
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		event.setQuitMessage("");
		
		Player player = event.getPlayer();
		
		Bukkit.broadcastMessage(Red + Utils.chat(plugin.getConfig().getString("leave_message").replace("<player>", player.getDisplayName())));
	}
}