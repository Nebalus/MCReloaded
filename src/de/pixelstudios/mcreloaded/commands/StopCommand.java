package de.pixelstudios.mcreloaded.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.pixelstudios.mcreloaded.MCReloaded;
import de.pixelstudios.mcreloaded.messaging.MessageFormatter;
import de.pixelstudios.mcreloaded.utils.Utils;

public class StopCommand implements CommandExecutor{
	
	private MessageFormatter messageFormatter;
	
	public StopCommand(MCReloaded plugin) {
		this.messageFormatter = plugin.getMessageFormatter();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String lable, String[] args) {
		if(sender.isOp()) {
			if(args.length == 0) {
				Bukkit.broadcastMessage("§cThe server is now §aclosing§c bye bye see you soon :)!");
				Bukkit.shutdown();
			}else if(args.length == 1) {
				if(!Utils.isStoping) {
					Utils.isReloading = true;
					Utils.isStoping = true;
					int checkint = 0;
					try {
						checkint = Integer.parseInt(args[0]);
					}catch(NumberFormatException e) {
						sender.sendMessage("§c"+args[0]+" is not a number please choose an number between 1-900");
					}
					if(checkint > 0 && checkint <= 900) {
						for(Player p : Bukkit.getOnlinePlayers()) {
							p.sendTitle("§cServer Closing", "", 1, 120, 20);
						}
						final Runnable task = new Runnable()
						{
							int times = Integer.parseInt(args[0]);
							@SuppressWarnings("unused")
							boolean announced = false;
							public void run()
							{	
								switch(times) {
									case 900:
									case 840:
									case 780:
									case 720:
									case 660:
									case 600:
									case 540:
									case 480:
									case 420:
									case 360:
									case 300:
									case 240:
									case 180:
									case 120:
									case 60:
									case 30:
									case 15:
									case 10:
									case 5:
									case 3:
									case 2:
										Bukkit.broadcastMessage("§cThe server will close in §6"+times+" §cseconds!");
										announced = true;
										break;
									case 1:
										Bukkit.broadcastMessage("§cThe server will close in §6"+times+" §csecond!");
										announced = true;
										break;
									case 0:
										Bukkit.broadcastMessage("§cThe server is now §aclosing§c bye bye see you soon :)!");
										announced = true;
										Bukkit.shutdown();
										break;
								}
								if(announced = false) {
									if(times != 1) {
										Bukkit.broadcastMessage("§cThe server will close in §6"+times+" §cseconds!");
									}else {
										Bukkit.broadcastMessage("§cThe server will close in §6"+times+" §csecond!");
									}
									announced = true;
								}
								if(times-- > 0)
									Bukkit.getScheduler().scheduleSyncDelayedTask(MCReloaded.getPlugin(), this, 20);	
								}
							};
							Bukkit.getScheduler().scheduleSyncDelayedTask(MCReloaded.getPlugin(), task, -1);
					}else {
						sender.sendMessage("§cPlease choose an number between 1-900");
					}
				}
			}
		}else {
			sender.sendMessage(messageFormatter.format(false, "error.no-permission"));
		}
		return false;
	}
}
