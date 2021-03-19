package io.pixelstudios.libary;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class InventoryLibary {

	public static void addItemToInventory(Player player, ItemStack item) {
		 if (item != null && player != null) {
			 if (player.getInventory().addItem(item).size() > 0) {
				 player.getWorld().dropItem(player.getEyeLocation(), item);
	         }
		 }
	 }
}
