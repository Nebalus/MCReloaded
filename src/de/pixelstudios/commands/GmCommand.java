package de.pixelstudios.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import de.pixelstudios.MCReloaded;
import de.pixelstudios.messaging.MessageFormatter;



public class GmCommand implements CommandExecutor, TabCompleter{
	private MessageFormatter messageFormatter;
	
	public GmCommand(MCReloaded plugin) {
		this.messageFormatter = plugin.getMessageFormatter();
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				if(player.hasPermission("mcreloaded.command.gm") || player.hasPermission("mcreloaded.command.gm.other")) {
					if(args.length == 1) {
						if(player.hasPermission("mcreloaded.command.gm")) {
							if(args[0].equalsIgnoreCase("1") | args[0].equalsIgnoreCase("creative")) {
								player.sendMessage(messageFormatter.format(false, "commands.gm.creative", "Your"));
								player.setGameMode(GameMode.CREATIVE);				
							}else if(args[0].equalsIgnoreCase("2") | args[0].equalsIgnoreCase("adventure")) {
								player.sendMessage(messageFormatter.format(false, "commands.gm.adventure", "Your"));
								player.setGameMode(GameMode.ADVENTURE);	 	 								
							}else if(args[0].equalsIgnoreCase("3") | args[0].equalsIgnoreCase("spectator")) {	
								player.sendMessage(messageFormatter.format(false, "commands.gm.spectator", "Your"));
								player.setGameMode(GameMode.SPECTATOR);	 									
							}else if(args[0].equalsIgnoreCase("0") | args[0].equalsIgnoreCase("survival")) {	
								player.sendMessage(messageFormatter.format(false, "commands.gm.survival", "Your"));
								player.setGameMode(GameMode.SURVIVAL);	 	 							
							}else {
								player.sendMessage(messageFormatter.format(false, "usage.command", "/gm <Gamemode> [Player]"));	
							}
						}else {	
							player.sendMessage(messageFormatter.format(false, "error.no-permission"));
						}
					}else if(args.length == 2){
						if(player.hasPermission("mcreloaded.command.gm.other")) {
							Player target = Bukkit.getPlayer(args[1]);
							if(target != null) {
								if(args[0].equalsIgnoreCase("1") | args[0].equalsIgnoreCase("creative")) {
									if(target != player) {
										player.sendMessage(messageFormatter.format(false, "commands.gm.creative", args[1]));
									}
									target.sendMessage(messageFormatter.format(false, "commands.gm.creative", "Your"));
									target.setGameMode(GameMode.CREATIVE);
								}else if(args[0].equalsIgnoreCase("2") | args[0].equalsIgnoreCase("adventure")) {
									if(target != player) {
										player.sendMessage(messageFormatter.format(false, "commands.gm.adventure", args[1]));	
									}
									target.sendMessage(messageFormatter.format(false, "commands.gm.adventure", "Your"));
									target.setGameMode(GameMode.ADVENTURE);	 	 							
								}else if(args[0].equalsIgnoreCase("3") | args[0].equalsIgnoreCase("spectator")) {	
									if(target != player) {
										player.sendMessage(messageFormatter.format(false, "commands.gm.spectator", args[1]));
									}
									target.sendMessage(messageFormatter.format(false, "commands.gm.spectator", "Your"));
									target.setGameMode(GameMode.SPECTATOR);	 	 							
								}else if(args[0].equalsIgnoreCase("0") | args[0].equalsIgnoreCase("survival")) {	
									if(target != player) {
										player.sendMessage(messageFormatter.format(false, "commands.gm.survival", args[1]));
									}
									target.sendMessage(messageFormatter.format(false, "commands.gm.survival", "Your"));
									target.setGameMode(GameMode.SURVIVAL);	 	 							
								}else {
									player.sendMessage(messageFormatter.format(false, "usage.command", "/gm <Gamemode> [Player]"));	
								}
								}else {
									player.sendMessage(messageFormatter.format(false, "error.player-not-online", args[1]));
								}
							
					}else {
						player.sendMessage(messageFormatter.format(false, "error.no-permission"));
						}
					}else {
						player.sendMessage(messageFormatter.format(false, "usage.command", "/gm <Gamemode> [Player]"));	
					}
				}else {
					player.sendMessage(messageFormatter.format(false, "error.no-permission"));
				}
		}else
			sender.sendMessage(messageFormatter.format(false, "console.only-player"));
			return false;
	}
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String lable, String[] args) {
		List<String> tabcomplete = new ArrayList<String>();
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(args.length == 1) {
				if(p.hasPermission("mcreloaded.command.gm")) {
					tabcomplete.add("survival");	
					tabcomplete.add("creative");
					tabcomplete.add("adventure");
					tabcomplete.add("spectator");
				}
			}
			//*****************************************************
			if(args.length == 2) {
				if(p.hasPermission("mcreloaded.command.gm.other")) {
					for(Player players : Bukkit.getOnlinePlayers() ) {
						tabcomplete.add(players.getName());
					}
				}
			}
			
		}
		return tabcomplete;
	}

}
