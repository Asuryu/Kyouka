package me.redextremept.kyouka.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import me.redextremept.kyouka.Main;
import me.redextremept.kyouka.utils.Utils;

public class Tasklist implements CommandExecutor {
	
	// private Plugin plugin = new Main();
	
	String Prefix = ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "Kyouka" + ChatColor.DARK_GRAY + "] " + ChatColor.GRAY;

	public Tasklist(Main plugin){
	    plugin.getCommand("task").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (!(sender instanceof Player)) {
			sender.sendMessage(Prefix + ChatColor.RED + "Only players can execute this command!");
			return true;
		}
		
		Player player = (Player)sender;
		
		if (player.hasPermission("task.use")) {
			if(args.length != 0) {
				if((args[0].toString().equalsIgnoreCase("add"))) {
					if (args.length == 1){
						player.sendMessage(Utils.chat(Prefix + "&aSuccessfully added " + args[1] + " to the Tasklist!"));
	                    return true;
	                }
					else if(args.length >= 2) {
						//player.sendMessage(Utils.chat(Prefix + "&aSuccessfully added " + args[1] + " to the Tasklist!"));
						
						String message = "";
	                    for (int i = 0; i < args.length; i++) {
	                        message = message + args[i] + " ";
	                    }
	                    if (message.length() == 0){
	                        player.sendMessage(ChatColor.RED + "Correct usage: /task add <task>");
	                        return true;
	                    }
	                    
	                    message = message.substring(4);
	                    player.sendMessage(Utils.chat("&bTask: " + message));
						
						ScoreboardManager manager = Bukkit.getScoreboardManager();
						Scoreboard board = manager.getNewScoreboard();
						@SuppressWarnings("deprecation")
						Objective objective1 = board.registerNewObjective(Utils.chat("&cTasklist"), "dummy");
						objective1.setDisplaySlot(DisplaySlot.SIDEBAR);
						
						Score separator = objective1.getScore(ChatColor.LIGHT_PURPLE + ""); //Get a fake offline player
						separator.setScore(2);
						
						
						Score score = objective1.getScore(ChatColor.LIGHT_PURPLE + message); //Get a fake offline player
						score.setScore(1);
						for(Player online : Bukkit.getOnlinePlayers()){
							online.setScoreboard(board);
						}
						return true;
					} else {
						player.sendMessage(Utils.chat(Prefix + "&c/task add <task>]"));
						return true;
					}
					
				}
			} else {
				player.sendMessage(Utils.chat(Prefix + "&cNot enough arguments!"));
				return true;
			}
			
			return true;
		}
		
		player.sendMessage(Prefix + ChatColor.RED + "You don't have permission to execute this command!");
		
		return true;
	}
	
}
