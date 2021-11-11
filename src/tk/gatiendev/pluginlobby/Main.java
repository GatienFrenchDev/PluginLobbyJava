package tk.gatiendev.pluginlobby;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	
	public static Main plugin;

	@Override
	public void onEnable() {
		
		Bukkit.getWorld("world").setGameRuleValue("doDaylightCycle", "false");
		Bukkit.getWorld("world").setGameRuleValue("keepInventory", "true");
		
		Bukkit.getWorld("world").setGameRuleValue("keepInventory", "true");
		
		
		plugin = this;
		getServer().getPluginManager().registerEvents(new PluginListener(plugin), this);
		getCommand("join").setExecutor(new CommandJoin());
	}
	
	@Override
	public void onDisable() {
		System.out.println("au revoir");
	}
}
