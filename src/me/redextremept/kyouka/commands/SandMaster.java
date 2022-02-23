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
    else{

        File myObj = new File("./plugins/Kyouka/Locations.txt");
        Scanner myReader = null;

        if (args.length == 0){
            player.sendMessage(Utils.chat(Prefix + "&3Showing you the saved locations, " + ChatColor.GOLD + player.getName() + "&8:"));
            try {
                myReader = new Scanner(myObj);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            int count = 0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                player.sendMessage(Utils.chat(count + "&l&5: &r" + data));
                count++;
            }
            myReader.close();
            return true;
        }
        if (args[0].equalsIgnoreCase("delete")){
            player.sendMessage(Utils.chat(Prefix + "&aDeleting that location from the file"));
//            try {
//                try {
//                    myReader = new Scanner(myObj);
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
//                int index = Integer.parseInt(args[1]);
//                int count = 0;
//                while (myReader.hasNextLine()) {
//                    count++;
//                }
//                myReader.close();
//                if(index <= count && count > 0){
//                    player.sendMessage(Utils.chat(Prefix + "&aDeleting that location from the file"));
//                }
//
//            } catch (NumberFormatException e){
//                player.sendMessage(Utils.chat(Prefix + args[1] + " is not a valid number"));
//                e.printStackTrace();
//            }
        }
        else {
            player.sendMessage(Utils.chat(Prefix + "&cThat command does not exist"));
        }
    }
    return true;
  }
}
