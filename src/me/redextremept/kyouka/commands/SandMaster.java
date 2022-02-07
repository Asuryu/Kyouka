package me.redextremept.kyouka.commands;

import me.redextremept.kyouka.Main;
import me.redextremept.kyouka.utils.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SandMaster implements CommandExecutor
{
  String Prefix = ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "Kyouka" + ChatColor.DARK_GRAY + "] " + ChatColor.GRAY;

  
  public SandMaster(Main plugin)
  {
    plugin.getCommand("locations").setExecutor(this);
  }
  

  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
	 
	Player player = (Player)sender;
	  
    if (!(sender instanceof Player)) {
      sender.sendMessage(Prefix + ChatColor.RED + "Only players can execute this command!");
      return true;
    } 
    else {
      try {
          player.sendMessage(Utils.chat(Prefix + "&3Showing you the saved locations, " + ChatColor.GOLD + player.getName() + "&8:"));
          File myObj = new File("./plugins/Kyouka/Locations.txt");
          Scanner myReader = new Scanner(myObj);  
          
          while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            player.sendMessage(Utils.chat(data));
          }
          myReader.close();
        }
      catch (FileNotFoundException e) {
          player.sendMessage(Utils.chat(Prefix + "&cAn error has occurred"));
          e.printStackTrace();
        } 
    }
        return true;
  }
}
