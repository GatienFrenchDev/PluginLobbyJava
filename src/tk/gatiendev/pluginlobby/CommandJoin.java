package tk.gatiendev.pluginlobby;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandJoin implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		
		if (args[0] == null){
			return false;
		}
		
		Bukkit.broadcastMessage("§6>> "+args[0]+" a rejoint le lobby <<");
		
		return false;
	}

}
