package de.pixelstudios.listener.player;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import de.pixelstudios.MCReloaded;
import de.pixelstudios.guis.LoadingGUI;
import de.pixelstudios.guis.ProfileGUI;
import de.pixelstudios.items.manager.GUIicons;
import de.pixelstudios.manager.ItemManager;
import de.pixelstudios.manager.user.UserProfile;

public class PlayerInventory implements Listener{
	@EventHandler(
			  priority = EventPriority.HIGHEST
		   )
	public void onClick(InventoryClickEvent event) {
		if(!(event.getWhoClicked() instanceof Player)) return;
		if(event.getCurrentItem() == null) return;
		Player p = (Player) event.getWhoClicked();
		if(p.getOpenInventory().getType().equals(InventoryType.HOPPER)) {
			if(p.getOpenInventory().getTitle().equals(LoadingGUI.loadingname)) {
				event.setCancelled(true);
			}
		}else if(p.getOpenInventory().getType().equals(InventoryType.GRINDSTONE)) {
			if(ItemManager.Tags.GRINDSTONE_BLOCKED.isTagged(event.getCurrentItem())) {
				event.setCancelled(true);
			}
		}else if(p.getOpenInventory().getType().equals(InventoryType.ANVIL)) {
				if(ItemManager.Tags.ANVIL_BLOCKED.isTagged(event.getCurrentItem())) {
					event.setCancelled(true);
				}
		}else if(p.getOpenInventory().getType().equals(InventoryType.BREWING)) {
			if(ItemManager.Tags.BREWINGSTAND_BLOCKED.isTagged(event.getCurrentItem())) {
				event.setCancelled(true);
			}
		}else if(p.getOpenInventory().getType().equals(InventoryType.SMITHING)) {
			if(event.getClickedInventory() != null && event.getClickedInventory().getType().equals(InventoryType.SMITHING) && event.getSlot() == 2) {
				final Runnable task = new Runnable()
				{
					int ticks = 1;
					public void run()
					{					
						if(ticks-- > 0) {
							Bukkit.getScheduler().scheduleSyncDelayedTask(MCReloaded.getPlugin(), this, 1);	
						}else {
							p.updateInventory();
						}
					}
				};
				Bukkit.getScheduler().scheduleSyncDelayedTask(MCReloaded.getPlugin(), task, -1);
			}
		}
		try {
			if(p.getOpenInventory().getTitle().startsWith("§5")) {
				event.setCancelled(true);
				OfflinePlayer socialmediaOther = ProfileGUI.OtherProfileSave.get(p);
				if(socialmediaOther != null) {
					if(p.getOpenInventory().getTitle().equals("§5"+socialmediaOther.getName()+"`s profile!")) {
						if(event.getCurrentItem() != null) {
							switch(event.getCurrentItem().getType()) {
				
							default:
								break;
				
							}
						}
					}
				}
				if(event.getCurrentItem() != null) {
					switch(event.getCurrentItem().getType()) {
					case BARRIER:
						if(event.getCurrentItem().equals(GUIicons.CLOSE_ICON)) {
							p.closeInventory();
						}
						break;
					default:
						break;
					}
				}
			}
		}catch(NullPointerException e) {}
	}	
	@EventHandler(
			  ignoreCancelled = true,
		      priority = EventPriority.MONITOR
		   )
	public void onClose(InventoryCloseEvent e) {
		Player p = (Player) e.getPlayer();
		UserProfile up = MCReloaded.getPlugin().getPlayerManager().getProfile(p);
		p.updateInventory();
		for(ItemStack item : p.getInventory().getContents()) {
			if(item != null && item.hasItemMeta()) {
				if(item.getItemMeta().getPersistentDataContainer().has(ItemManager.golderite_armor_Key, PersistentDataType.BYTE)) {
					//up.giveAchievement(Achievements.GILDEDNETHERITE);
				}
			}
		}
		if(e.getInventory().equals(p.getEnderChest())) {
			p.playSound(p.getEyeLocation(), Sound.BLOCK_ENDER_CHEST_CLOSE, 1, 1);
		}
	}
}
