package de.pixelstudios.mcreloaded.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import de.pixelstudios.mcreloaded.MCReloaded;
import de.pixelstudios.mcreloaded.messaging.MessageFormatter;

public class RepairCommand implements CommandExecutor{ 
	private MessageFormatter messageFormatter;
	
	public RepairCommand(MCReloaded plugin) {
		this.messageFormatter = plugin.getMessageFormatter();
	}
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
	if(sender instanceof Player) {
		Player player = (Player) sender;
		if(player.hasPermission("mcreloaded.command.repair")) {
			ItemStack mainItem = player.getInventory().getItemInMainHand();
			if(!(mainItem.getType() == null)) {
				player.sendMessage(mainItem.getDurability()+ "");
				mainItem.setDurability((short)0);
			
			}
		}else {
			player.sendMessage(messageFormatter.format(false, "error.no-permission"));
		}
	}else {
		sender.sendMessage(messageFormatter.format(false, "console.only-player"));
	}
	
	return false;
 	}
}