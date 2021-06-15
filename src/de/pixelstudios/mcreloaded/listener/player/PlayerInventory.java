package de.pixelstudios.mcreloaded.listener.player;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import de.pixelstudios.mcreloaded.MCReloaded;
import de.pixelstudios.mcreloaded.datamanagement.Cache;
import de.pixelstudios.mcreloaded.guis.GUIicons;
import de.pixelstudios.mcreloaded.guis.ProfileGUI;
import de.pixelstudios.mcreloaded.manager.ItemManager;
import de.pixelstudios.mcreloaded.manager.UserProfile;
import de.pixelstudios.mcreloaded.utils.Achievements;

public class PlayerInventory implements Listener{
	@EventHandler(
			  priority = EventPriority.HIGHEST
		   )
	public void onClick(InventoryClickEvent event) {
		if(!(event.getWhoClicked() instanceof Player)) return;
		
		Player p = (Player) event.getWhoClicked();
		if(p.getOpenInventory().getType().equals(InventoryType.GRINDSTONE)) {
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
				if(event.getClick().isShiftClick() || event.getClick() == ClickType.DOUBLE_CLICK || event.getClick() == ClickType.MIDDLE) 
					return;
				if(p.getOpenInventory().getTitle().startsWith("§5Warp Crystal")) {
					if(event.getClickedInventory().equals(p.getOpenInventory().getTopInventory())) {
						if(event.getSlot() == 43) {
	
							if(event.getCurrentItem() == null && event.getCursor().getItemMeta().getPersistentDataContainer().has(ItemManager.warp_fuel_key,  PersistentDataType.BYTE)) {
								event.setCancelled(false);
								Cache.warp_crystal_gui_session.get(p).setChargeDisplay(event.getCursor().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(MCReloaded.getPlugin(), "fuelcharge"), PersistentDataType.INTEGER), true);
				
							}else if(event.getCursor().getType().equals(Material.AIR)){
								event.setCancelled(false);
								Cache.warp_crystal_gui_session.get(p).setChargeDisplay(0, true);
							
							}else if(event.getCursor().getItemMeta().getPersistentDataContainer().has(ItemManager.warp_fuel_key,  PersistentDataType.BYTE)){
								event.setCancelled(false);
								Cache.warp_crystal_gui_session.get(p).setChargeDisplay(event.getCursor().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(MCReloaded.getPlugin(), "fuelcharge"), PersistentDataType.INTEGER), true);
								
							}
						}else {
							event.setCancelled(true);
						}
						if(event.getCurrentItem().equals(GUIicons.WARPCRYSTAL_PICKUP)) {
							Cache.warp_crystal_gui_session.get(p).getWarpCrystal().despawn(p);
						}
						if(event.getCurrentItem().equals(GUIicons.WARPCRYSTAL_VISIBILITY_PUBLIC)) {
							Cache.warp_crystal_gui_session.get(p).setVisibility(0,true);
						}
						if(event.getCurrentItem().equals(GUIicons.WARPCRYSTAL_VISIBILITY_PRIVATE)) {
							Cache.warp_crystal_gui_session.get(p).setVisibility(2,true);
						}
					}else {
						event.setCancelled(false);
					}
				}
				OfflinePlayer socialmediaOther = ProfileGUI.OtherProfileSave.get(p);
				if(socialmediaOther != null) {
					if(p.getOpenInventory().getTitle().equals("§5"+socialmediaOther.getName()+"`s profile!")) {
						switch(event.getCurrentItem().getType()) {
							default:
								break;
						}
					}
				}
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
				if(item.getItemMeta().getPersistentDataContainer().has(ItemManager.golderite_ingot_Key, PersistentDataType.BYTE)) {
					up.giveAchievement(Achievements.GOLDERITE);
				}
			}
		}
		if(e.getInventory().equals(p.getEnderChest())) {
			p.playSound(p.getEyeLocation(), Sound.BLOCK_ENDER_CHEST_CLOSE, 1, 1);
		}
		/*
		p.sendMessage("test1");
		if(Cache.warp_crystal_gui_session.containsKey(p)) {
			p.sendMessage("test");
			Cache.warp_crystal_gui_session.remove(p);
		}
		*/
	}
}
