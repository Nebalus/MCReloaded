package de.nebalus.mc.mcreloaded.command.admin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemConsumeEvent;

import de.nebalus.mc.mcreloaded.command.MCCommand;

public class RepairCommand extends MCCommand
{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) 
	{
		if(!(sender instanceof Player))
		{
			sender.sendMessage("You need to be a player to execute this command!");
			return false;
		}
		
		Player p = (Player) sender;
		
		
		return false;
	}

}
