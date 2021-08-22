package de.pixelstudios.mcreloaded.manager;

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
				if(item != null) {
					Collection<ItemStack> drops = b.getDrops(item);
					if(!drops.isEmpty()) {
						b.getWorld().dropItemNaturally(b.getLocation(), drops.iterator().next());
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
