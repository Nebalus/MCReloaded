package de.pixelstudios.mcreloaded.commands;

import java.io.File;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.pixelstudios.mcreloaded.MCReloaded;
import de.pixelstudios.mcreloaded.datamanagement.FileManager;
import de.pixelstudios.mcreloaded.guis.ProfileGUI;
import de.pixelstudios.mcreloaded.messaging.MessageFormatter;

public class ProfileCommand implements CommandExecutor{
	private MessageFormatter messageFormatter;
	
	public ProfileCommand(MCReloaded plugin) {
		this.messageFormatter = plugin.getMessageFormatter();
	}
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(args.length == 0) {		
				ProfileGUI.openProfileGui(p);
			}else if(args.length == 1) {		
				OfflinePlayer op = Bukkit.getOfflinePlayer(args[0]);
				String uuid = op.getUniqueId().toString();
				String uuidshort = uuid.substring(0, 2);
						
				File playerdir = new File(MCReloaded.serverpath + "/PixelStudios/MCReloaded/PlayerData/"+uuidshort+"/"+uuid+"/");
				if(playerdir.exists()) {		
					ProfileGUI.openOtherProfileGui(op.getName(),p);				
				}else {					
					p.sendMessage(messageFormatter.format(false, "error.player-was-never-online",args[0]));	
				}
			}else {
				sender.sendMessage(messageFormatter.format(false, "usage.command","/profile <playername/uuid>"));	
			}
		}else {
			Bukkit.getConsoleSender().sendMessage(messageFormatter.format(false, "console.only-player"));
		}
		return false;
	}

}
