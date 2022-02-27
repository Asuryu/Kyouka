package me.redextremept.kyouka;

import me.redextremept.kyouka.commands.SandMaster;
import me.redextremept.kyouka.listeners.BedListener;
import me.redextremept.kyouka.listeners.ChatListener;
import me.redextremept.kyouka.listeners.JoinListener;
import me.redextremept.kyouka.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.Random;


public class Main
  extends JavaPlugin
{
  String Prefix = ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "Kyouka" + ChatColor.DARK_GRAY + "] " + ChatColor.GRAY;
  public Main() {}
  
  public void onEnable()
  {
    loadConfig();

    new JoinListener(this);
    new BedListener(this);
    new ChatListener(this);
    new SandMaster(this);

    final int[] flag = {0};

    BukkitScheduler scheduler = getServer().getScheduler();
    scheduler.runTaskTimer(this, new Runnable() {
      @Override
      public void run() {
        long time = getServer().getWorld("world").getFullTime() / 24000;
        long phase = time % 8;
        Random rand = new Random();
        int rand_int1 = rand.nextInt(100);
        if (getServer().getWorld("world").getTime() >= 18000 && flag[0] == 0) {
          flag[0] = 1;
          Bukkit.broadcastMessage(Prefix + Utils.chat("&6Comecou uma noite especial!"));
        }
        else if (getServer().getWorld("world").getTime() > 8000 && getServer().getWorld("world").getTime() < 18000) {
          flag[0] = 0;
        }
      }
    }, 0L, 100L);

    saveDefaultConfig();
  }
  
  public void loadConfig() {
	  getConfig().options().copyDefaults(true);
	  saveConfig();
  }
  
}
