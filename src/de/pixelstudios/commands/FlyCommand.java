package de.pixelstudios.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import de.pixelstudios.MCReloaded;
import de.pixelstudios.manager.PlayerManager;
import de.pixelstudios.messaging.MessageFormatter;

public class FlyCommand implements CommandExecutor, TabCompleter {
	private PlayerManager playerManager;
	private MessageFormatter messageFormatter;
	
	public FlyCommand(MCReloaded plugin) {
		this.playerManager = plugin.getPlayerManager();
		this.messageFormatter = plugin.getMessageFormatter();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String lable, String[] args) {
		if(sender instanceof Player){
			Player player = (Player) sender;
					if(args.length == 0) {
						if(player.hasPermission("mcreloaded.command.fly")) {
							if(player.getAllowFlight()) {
								player.setAllowFlight(false);
								playerManager.getProfile(player).setFly(false);
								player.sendMessage(messageFormatter.format(false, "commands.fly.disabled"));
							}else { 
								player.setAllowFlight(true);
								playerManager.getProfile(player).setFly(true);
								player.sendMessage(messageFormatter.format(false, "commands.fly.enabled"));
							}
						}else {
							player.sendMessage(messageFormatter.format(false, "error.no-permission"));
						}
					}else if(args.length == 1){
						if(player.hasPermission("mcreloaded.command.fly.give")) {
							Player target = Bukkit.getPlayer(args[0]);
							if(target != null){
								if(target.getAllowFlight()) {
									target.setAllowFlight(false);
									target.sendMessage(messageFormatter.format(false, "commands.fly.disabled"));
									if(target != player){
										player.sendMessage(messageFormatter.format(false, "commands.fly.disabled-other",target.getName()));
									}
								}else if(target.getAllowFlight() == false) {
									target.setAllowFlight(true);
									target.sendMessage(messageFormatter.format(false, "commands.fly.enabled"));
									if(target != player){
										player.sendMessage(messageFormatter.format(false, "commands.fly.enabled-other",target.getName()));
									}
								}
							}else {
								player.sendMessage(messageFormatter.format(false, "error.player-not-online", args[0]));
							}
						}else { 
							player.sendMessage(messageFormatter.format(false, "error.no-permission"));
						}
					}else {
						player.sendMessage(messageFormatter.format(false, "usage.command","/fly [Player]"));	
					}
		}else {
			if(args.length == 1){
				Player target = Bukkit.getPlayer(args[0]);
				if(target != null){
					if(target.getAllowFlight()) {
						
						target.setAllowFlight(false);
						target.sendMessage(messageFormatter.format(false, "commands.fly.disabled"));
						sender.sendMessage(messageFormatter.format(false, "commands.fly.disabled-other",target.getName()));
					}else if(target.getAllowFlight() == false) {
						target.setAllowFlight(true);
						target.sendMessage(messageFormatter.format(false, "commands.fly.enabled"));
						sender.sendMessage(messageFormatter.format(false, "commands.fly.enabled-other",target.getName()));
					}
				}else {
					sender.sendMessage(messageFormatter.format(false, "error.player-not-online", args[0]));
				}
		}else {
			sender.sendMessage(messageFormatter.format(false, "usage.command","/fly [Player]"));	
			}
		}
		return false;
	}
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String lable, String[] args) {
		List<String> tabcomplete = new ArrayList<String>();
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(args.length == 1) {
				if(p.hasPermission("mcreloaded.command.fly.give")) {
					for(Player all : Bukkit.getOnlinePlayers()) {
						tabcomplete.add(all.getName());
					}
				}
			}
		}
		return tabcomplete;
	}
}