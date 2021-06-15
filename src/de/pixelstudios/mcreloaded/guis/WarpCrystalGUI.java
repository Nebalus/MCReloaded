package de.pixelstudios.mcreloaded.guis;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import de.pixelstudios.mcreloaded.MCReloaded;
import de.pixelstudios.mcreloaded.items.WarpCrystal;
import de.pixelstudios.mcreloaded.manager.ItemManager;

public class WarpCrystalGUI {
	
	private WarpCrystal crystal;
	private Player p;
	private final int GUI_Slots = 9*6;
	private final int[] warpstone = new int[] {10,11,12,13,14, 19,20,21,22,23, 28,29,30,31,32, 37,38,39,40,41}; 
	private Inventory inv = Bukkit.createInventory(null, GUI_Slots, "§5Warp Crystal");
	
	public WarpCrystalGUI(WarpCrystal crystal, Player p) {
		this.crystal = crystal;
		this.p = p;
	}
	public void loadGUI() {
		Bukkit.getScheduler().runTaskAsynchronously(MCReloaded.getPlugin(),new Runnable() {
			@Override
			public void run() {	
				for (int i = 0; i < GUI_Slots; ++i ) {
					inv.setItem(i, GUIicons.GRAYFILLER);
				}
				ItemStack Sign = new ItemStack(Material.DARK_OAK_SIGN);
				ItemMeta SignMeta = Sign.getItemMeta();
				SignMeta.setDisplayName("§f"+crystal.getName());
				if(p.getUniqueId().equals(crystal.getOwnerUUID())) {
					ArrayList<String> Sign_Lore = new ArrayList<String>();
					Sign_Lore.add("§eClick to change!");
					SignMeta.setLore(Sign_Lore);
				}
				Sign.setItemMeta(SignMeta);
				inv.setItem(4, Sign);
				
				for(int i : warpstone) {
					inv.setItem(i, GUIicons.LIGHTGRAYFILLER);
				}
				
				setChargeDisplay(crystal.getWarpCharge(),false);
				setVisibility(crystal.getVisibility(),false);
				inv.setItem(48, GUIicons.BACK_ICON);
				inv.setItem(49, GUIicons.CLOSE_ICON);
				inv.setItem(50, GUIicons.NEXT_ICON);
				if(p.getUniqueId().equals(crystal.getOwnerUUID())) {
					inv.setItem(53, GUIicons.WARPCRYSTAL_PICKUP);
				}
				Bukkit.getScheduler().runTask(MCReloaded.getPlugin(),new Runnable() {
					@Override
					public void run() {
						p.openInventory(inv);		
					}
				});
			}
		});
	}
	
	public void setVisibility(int visibility, boolean update) {
		if(update) {
			Inventory pinv = p.getOpenInventory().getTopInventory();
			if(crystal.getVisibility() == 0) {
				pinv.setItem(45, GUIicons.WARPCRYSTAL_VISIBILITY_PRIVATE);
			}else if(crystal.getVisibility() == 2) {
				pinv.setItem(45, GUIicons.WARPCRYSTAL_VISIBILITY_PUBLIC);
			}	
			Bukkit.getScheduler().runTaskLater(MCReloaded.getPlugin(), new Runnable() {
				@Override
				public void run() {
					p.updateInventory();
				}
	    	}, 1);
		}else {
			if(crystal.getVisibility() == 0) {
				inv.setItem(45, GUIicons.WARPCRYSTAL_VISIBILITY_PRIVATE);
			}else if(crystal.getVisibility() == 2) {
				inv.setItem(45, GUIicons.WARPCRYSTAL_VISIBILITY_PUBLIC);
			}	
		}
	}
	
	public Inventory getInventory() {
		return inv;
	}
	
	public WarpCrystal getWarpCrystal() {
		return crystal;
	}
	
	public void setChargeDisplay(int charge, boolean update) {
		if(update) {
			Inventory pinv = p.getOpenInventory().getTopInventory();
			if(charge > 0) {
				pinv.setItem(16, GUIicons.REDFILLER);
				pinv.setItem(25, GUIicons.REDFILLER);
				pinv.setItem(34, GUIicons.REDFILLER);
				
				switch(charge) {
				case 1:
					pinv.setItem(34, GUIicons.LIMEFILLER);
					break;
				case 2:
					pinv.setItem(25, GUIicons.LIMEFILLER);
					pinv.setItem(34, GUIicons.LIMEFILLER);
					break;
				case 3:
					pinv.setItem(16, GUIicons.LIMEFILLER);
					pinv.setItem(25, GUIicons.LIMEFILLER);
					pinv.setItem(34, GUIicons.LIMEFILLER);
					break;
				}
			
			}else {
				pinv.setItem(16, GUIicons.WHITEFILLER);
				pinv.setItem(25, GUIicons.WHITEFILLER);
				pinv.setItem(34, GUIicons.WHITEFILLER);
			}
			crystal.setWarpCharge(charge);
			Bukkit.getScheduler().runTaskLater(MCReloaded.getPlugin(), new Runnable() {
				@Override
				public void run() {
					p.updateInventory();
				}
	    	}, 1);
		}else { 
			if(charge > 0) {
				inv.setItem(16, GUIicons.REDFILLER);
				inv.setItem(25, GUIicons.REDFILLER);
				inv.setItem(34, GUIicons.REDFILLER);
				
				switch(charge) {
				case 1:
					inv.setItem(34, GUIicons.LIMEFILLER);
					break;
				case 2:
					inv.setItem(25, GUIicons.LIMEFILLER);
					inv.setItem(34, GUIicons.LIMEFILLER);
					break;
				case 3:
					inv.setItem(16, GUIicons.LIMEFILLER);
					inv.setItem(25, GUIicons.LIMEFILLER);
					inv.setItem(34, GUIicons.LIMEFILLER);
					break;
				}
				ItemStack WarpFuel = new ItemStack(ItemManager.WARP_FUEL);
				ItemMeta WarpFuelMeta = WarpFuel.getItemMeta();
				WarpFuelMeta.getPersistentDataContainer().set(new NamespacedKey(MCReloaded.getPlugin(), "fuelcharge"), PersistentDataType.INTEGER, charge);
				ArrayList<String> WarpFuel_Lore = new ArrayList<String>();
				WarpFuel_Lore.add("§7Charge §a3§7/§a"+charge);
				WarpFuelMeta.setLore(WarpFuel_Lore);
				WarpFuel.setItemMeta(WarpFuelMeta);
				
				inv.setItem(43, WarpFuel);
			}else {
				inv.setItem(16, GUIicons.WHITEFILLER);
				inv.setItem(25, GUIicons.WHITEFILLER);
				inv.setItem(34, GUIicons.WHITEFILLER);
				inv.setItem(43, new ItemStack(Material.AIR));
			}		
		}
	}
}
