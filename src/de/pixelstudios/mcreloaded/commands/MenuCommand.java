package de.pixelstudios.mcreloaded.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.pixelstudios.mcreloaded.guis.ProfileGUI;

public class MenuCommand implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			ProfileGUI.openProfileGui(p);
		}
		return false;
	}
}
