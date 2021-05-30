package de.pixelstudios.mcreloaded.guis;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class LoadingGUI {
	
	public static String loadingname = "§0§lLoading...";
	public static HashMap<Player, Integer> guianimator = new HashMap<Player, Integer>();
	
	public static boolean showLoadingScreen(Player p) {
		guianimator.remove(p);
		Inventory inventory = Bukkit.createInventory(null, InventoryType.HOPPER, loadingname);
		p.openInventory(inventory);
		return false;
	}
	public static void animate() {
		ItemStack loading = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
		ItemMeta itemMetablue1 = loading.getItemMeta();
		itemMetablue1.setDisplayName("§aLoading... Please be patient.");	
		loading.setItemMeta(itemMetablue1);
		ItemStack air = new ItemStack(Material.AIR);
		for(Player p : Bukkit.getOnlinePlayers()) {
			if(p.getOpenInventory() != null) {
				if(p.getOpenInventory().getType().equals(InventoryType.HOPPER)) {
					if(p.getOpenInventory().getTitle().equals(loadingname)) {
						if(guianimator.containsKey(p)) {
							Integer slot = guianimator.get(p);		
							if(slot == 1 || slot <= 0) {
								guianimator.replace(p, 2);
								p.getOpenInventory().setItem(0, loading);
							}
							if(slot == 2) {
								guianimator.replace(p, 3);
								p.getOpenInventory().setItem(0, loading);
								p.getOpenInventory().setItem(1, loading);
							}
							if(slot == 3) {
								guianimator.replace(p, 4);
								p.getOpenInventory().setItem(0, loading);
								p.getOpenInventory().setItem(1, loading);
								p.getOpenInventory().setItem(2, loading);
							}
							if(slot == 4) {
								guianimator.replace(p, 5);
								p.getOpenInventory().setItem(0, loading);
								p.getOpenInventory().setItem(1, loading);
								p.getOpenInventory().setItem(2, loading);
								p.getOpenInventory().setItem(3, loading);
							}
							if(slot == 5) {
								guianimator.replace(p, 6);
								p.getOpenInventory().setItem(0, loading);
								p.getOpenInventory().setItem(1, loading);
								p.getOpenInventory().setItem(2, loading);
								p.getOpenInventory().setItem(3, loading);
								p.getOpenInventory().setItem(4, loading);
							}
							if(slot >= 6) {
								guianimator.replace(p, 1);
								p.getOpenInventory().setItem(4, air);
								p.getOpenInventory().setItem(3, air);
								p.getOpenInventory().setItem(2, air);
								p.getOpenInventory().setItem(1, air);
								p.getOpenInventory().setItem(0, air);
							}
							p.updateInventory();
						}else {
							guianimator.put(p, 0);
						}
					}
				}
			}
		}
	}
	public static boolean resetLoadingScreen(Player p) {
		
		return false;
	}
}
