package de.pixelstudios.manager;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class BlockManager {

	public static void breakblock(Location loc, Boolean dropable, Player player, ItemStack item) {
		Block b = loc.getBlock();
		if(!b.getType().equals(Material.AIR)) {
			if(dropable) {
				Location itemLoc = b.getLocation();
				itemLoc.setY(b.getY() + 0.5);
				itemLoc.setX(b.getX() + 0.5);
				itemLoc.setZ(b.getZ() + 0.5);
				if(item != null) {
					if(item.getEnchantments().containsKey(Enchantment.SILK_TOUCH)) {
						b.setType(Material.AIR);
						itemLoc.getWorld().dropItem(itemLoc, new ItemStack(b.getType()));
					}else if(item.getEnchantments().containsKey(Enchantment.LUCK)) {
							
					}else {
						b.breakNaturally();
						}
				}else {
					b.breakNaturally();
					}
				}else {
				b.setType(Material.AIR);
			}
		}
	}
}
