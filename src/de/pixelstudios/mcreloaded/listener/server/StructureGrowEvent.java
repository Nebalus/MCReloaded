package de.pixelstudios.mcreloaded.listener.server;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.inventory.Inventory;

public class StructureGrowEvent implements Listener{

	@EventHandler(
			  ignoreCancelled = true,
			  priority = EventPriority.HIGHEST
		   )
	public void onGrow(org.bukkit.event.world.StructureGrowEvent event) {
		Random ran = new Random();
		int chance = ran.nextInt(100);
		if(chance >= 98) {
			event.setCancelled(true);
			event.getLocation().getBlock().setType(Material.DEAD_BUSH);
		}
	}
}
