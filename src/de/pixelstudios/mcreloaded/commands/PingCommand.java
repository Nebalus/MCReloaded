package de.pixelstudios.mcreloaded.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import de.pixelstudios.mcreloaded.MCReloaded;
import de.pixelstudios.mcreloaded.messaging.MessageFormatter;
import de.pixelstudios.mcreloaded.utils.Utils;


public class PingCommand  implements CommandExecutor, TabCompleter {
	private MessageFormatter messageFormatter;
	
	public PingCommand(MCReloaded plugin) {
		this.messageFormatter = plugin.getMessageFormatter();
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(args.length == 0) {
				p.sendMessage(messageFormatter.format(false, "commands.ping.self", Utils.getPing(p)));
			}else if(args.length == 1) {
				Player target = Bukkit.getPlayer(args[0]);
				if(target != null) {
					if(target != p) {
						p.sendMessage(messageFormatter.format(false, "commands.ping.other",target.getName() , Utils.getPing(target)));
					}else {
						p.sendMessage(messageFormatter.format(false, "commands.ping.self", Utils.getPing(p)));	
					}
				}else {
					p.sendMessage(messageFormatter.format(false, "error.player-not-online", args[0]));
				}
			}else {
				p.sendMessage(messageFormatter.format(false, "usage.command","/ping [Player]"));	
			}
		}else {
			sender.sendMessage(messageFormatter.format(false, "console.only-player"));
		}
		return true;
	}
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String lable, String[] args) {
		List<String> tabcomplete = new ArrayList<String>();
		if(sender instanceof Player) {
			if(args.length == 1) {
				for(Player all : Bukkit.getOnlinePlayers()) {
					tabcomplete.add(all.getName());
				}
			}
		}
		return tabcomplete;
	}

}
