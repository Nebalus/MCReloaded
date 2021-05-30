package de.pixelstudios.mcreloaded.listener.server;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import de.pixelstudios.mcreloaded.manager.ItemManager;

public class InventoryPickupItem implements Listener{

	@EventHandler(
			  ignoreCancelled = true,
			  priority = EventPriority.HIGHEST
		   )
	public void onPickup(InventoryPickupItemEvent e) {
		Inventory inv = e.getInventory();
		ItemStack item = e.getItem().getItemStack();
		if(inv.getType().equals(InventoryType.BREWING)) {
			if(ItemManager.Tags.BREWINGSTAND_BLOCKED.isTagged(item)) {
				e.setCancelled(true);
			}
		}
	}
	@EventHandler(
			  ignoreCancelled = true,
			  priority = EventPriority.HIGHEST
		   )
	public void onInventoryMoveItemEvent(InventoryMoveItemEvent event) {
		Inventory dest = event.getDestination();
		ItemStack item = event.getItem();
		if(dest.getType().equals(InventoryType.BREWING)) {
			if(ItemManager.Tags.BREWINGSTAND_BLOCKED.isTagged(item)) {
				event.setCancelled(true);
			}
		}
	}
}
