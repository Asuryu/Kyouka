package me.redextremept.kyouka.commands;

import me.redextremept.kyouka.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HelloCommand implements CommandExecutor
{
  String Prefix = ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "Kyouka" + ChatColor.DARK_GRAY + "] " + ChatColor.GRAY;
  
  @SuppressWarnings("unused")
  private Main plugin;
  
  public HelloCommand(Main plugin)
  {
    this.plugin = plugin;
    plugin.getCommand("hello").setExecutor(this);
  }
  

  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    if (!(sender instanceof Player)) {
      sender.sendMessage(Prefix + ChatColor.RED + "Only players can execute this command!");
      return true;
    }
    Player player = (Player)sender;
    
    if (player.hasPermission("hello.use")) {
      player.sendMessage(Prefix + "Hey there, " + ChatColor.GOLD + player.getName() + "!");
      return true;
    }
    player.sendMessage(Prefix + ChatColor.RED + "You don't have permission to execute this command!");
    

    return false;
  }
}
