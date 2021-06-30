package de.pixelstudios.mcreloaded.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.pixelstudios.mcreloaded.MCReloaded;
import de.pixelstudios.mcreloaded.messaging.MessageFormatter;
import de.pixelstudios.mcreloaded.utils.Utils;

public class ReloadCommand implements CommandExecutor{
	
	private MessageFormatter messageFormatter;
	
	public ReloadCommand(MCReloaded plugin) {
		this.messageFormatter = plugin.getMessageFormatter();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String lable, String[] args) {
		if(sender.isOp()) {
			if(!Utils.isReloading) {
				Utils.isReloading = true;
				for(Player p : Bukkit.getOnlinePlayers()) {
					p.sendTitle("§cServer Reload", "",1,120,20);
				}
				final Runnable task = new Runnable(){
					int times = 60;
					public void run(){					
						switch(times) {
							case 60:
							case 30:
							case 15:
							case 10:
							case 5:
							case 3:
							case 2:
								Bukkit.broadcastMessage("§cThe server will reload in §6"+times+" §cseconds!");
								break;
							case 1:
								Bukkit.broadcastMessage("§cThe server will reload in §6"+times+" §csecond!");
								break;
							case 0:
								Bukkit.broadcastMessage("§cThe server is now §areloading§c this task may contains lags!");
								Bukkit.reload();
								break;
						}
						if(times-- > 0)
							Bukkit.getScheduler().scheduleSyncDelayedTask(MCReloaded.getPlugin(), this, 20);	
						}
					};
					Bukkit.getScheduler().scheduleSyncDelayedTask(MCReloaded.getPlugin(), task, -1);
			}
		}else {
			sender.sendMessage(messageFormatter.format(false, "error.no-permission"));
		}
		return false;
	}
}
