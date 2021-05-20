package de.pixelstudios.manager;

import java.util.Collection;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
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
					Collection<ItemStack> drops = b.getDrops(item);
					if(!drops.isEmpty()) {
						b.getWorld().dropItemNaturally(itemLoc, drops.iterator().next());
						b.setType(Material.AIR);
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
