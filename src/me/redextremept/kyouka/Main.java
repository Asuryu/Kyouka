package me.redextremept.kyouka;

import me.redextremept.kyouka.commands.SandMaster;
import me.redextremept.kyouka.listeners.BedListener;
import me.redextremept.kyouka.listeners.ChatListener;
import me.redextremept.kyouka.listeners.JoinListener;
import org.bukkit.plugin.java.JavaPlugin;


public class Main
  extends JavaPlugin
{
  public Main() {}
  
  public void onEnable()
  {
    loadConfig();
    new JoinListener(this);
    new BedListener(this);
    new ChatListener(this);
    new SandMaster(this);
    saveDefaultConfig();
  }
  
  public void loadConfig() {
	  getConfig().options().copyDefaults(true);
	  saveConfig();
  }
  
}
