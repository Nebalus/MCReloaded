package de.pixelstudios.mcreloaded.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.pixelstudios.mcreloaded.MCReloaded;
import de.pixelstudios.mcreloaded.messaging.MessageFormatter;

public class CordinatesCommand implements CommandExecutor{

	private MessageFormatter messageFormatter;
	
	public CordinatesCommand(MCReloaded plugin) {
		this.messageFormatter = plugin.getMessageFormatter();
	}
	@Override
	public boolean onCommand(CommandSender sender, Command command, String lable, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(player.hasPermission("mcreloaded.command.cordinates")) {
				if(args.length == 0) {
					player.sendMessage(messageFormatter.format(false, "commands.cordinates.own-cordinates",player.getLocation().getBlockX(),player.getLocation().getBlockY(),player.getLocation().getBlockZ()));
					player.sendMessage(messageFormatter.format(false, "commands.cordinates.send-info"));
				} else if(args.length == 1) {
					Player target = Bukkit.getPlayer(args[0]);
					if(target != null) {
						if(target != player) {
							player.sendMessage(messageFormatter.format(false, "commands.cordinates.send-cordinates-info-self",target.getName()));
							target.sendMessage(messageFormatter.format(false, "commands.cordinates.send-cordinates-info",player.getName(),player.getLocation().getBlockX(),player.getLocation().getBlockY(),player.getLocation().getBlockZ()));
							target.sendMessage(messageFormatter.format(false, "commands.cordinates.send-info-other",player.getName()));
						}else {
							player.sendMessage(messageFormatter.format(false, "commands.cordinates.own-cordinates",player.getLocation().getBlockX(),player.getLocation().getBlockY(),player.getLocation().getBlockZ()));
							player.sendMessage(messageFormatter.format(false, "commands.cordinates.send-info"));
						}
					} else
						player.sendMessage(messageFormatter.format(false, "error.player-not-online"));
				} else
					player.sendMessage(messageFormatter.format(false, "usage.command","/cordinates [Player]"));
			} else
				player.sendMessage(messageFormatter.format(false, "error.no-permission"));
		} else
			sender.sendMessage(messageFormatter.format(false, "console.only-player"));
		return false;
	}
}
