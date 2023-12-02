package de.nebalus.mc.mcreloaded.command.admin;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import de.nebalus.mc.mcreloaded.command.CommandAdapter;

public class RepairCommand extends CommandAdapter {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("You need to be a player to execute this command!");
			return false;
		}

		Player p = (Player) sender;
		ItemStack mainItem = p.getInventory().getItemInMainHand();

		if ((mainItem = p.getInventory().getItemInMainHand()).getType().equals(Material.AIR)
				|| mainItem.getDurability() <= 0) {
			sender.sendMessage("Please hold a damaged item/tool your main hand!");
			return false;
		}

		p.sendMessage(
				mainItem.getDurability() + " durability points has been added to [" + mainItem.getType().name() + "]");

		mainItem.setDurability((short) 0);

		return false;
	}

}
