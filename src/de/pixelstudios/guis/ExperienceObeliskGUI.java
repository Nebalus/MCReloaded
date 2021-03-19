package de.pixelstudios.guis;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.pixelstudios.MCReloaded;
import de.pixelstudios.items.ExperienceObelisk;
import de.pixelstudios.items.manager.GUIicons;

public class ExperienceObeliskGUI {
	
	private ExperienceObelisk obelisk;
	private Player p;
	private final int GUI_Slots = 9*6;
	
	public ExperienceObeliskGUI(ExperienceObelisk obelisk, Player p) {
		this.obelisk = obelisk;
		this.p = p;
	}
	public void loadGUI() {
		LoadingGUI.showLoadingScreen(p);
		String Tier = "N";
		if(obelisk.getLevel() == 1) {
			Tier = "I";
		}else if(obelisk.getLevel() == 2) {
			Tier = "II";
		}else if(obelisk.getLevel() == 3) {
			Tier = "III";
		}else if(obelisk.getLevel() == 4) {
			Tier = "IV";
		}
		
		Inventory inv = Bukkit.createInventory(null, GUI_Slots, "§5Experience Obelisk Tier "+Tier);
		Bukkit.getScheduler().runTaskAsynchronously(MCReloaded.getPlugin(),new Runnable() {
			@Override
			public void run() {
				//xp level
				int level = 0;
				//wie wie xp benötigt wird um ein level auf zu steigen
				int xpMax = 6;
				//wie viel xp übrig ist
				int xp = 0;	
				int xpCache = obelisk.getXP();
				int prozentToLevelup = 0;
				
				
				for (int i = 0; i < GUI_Slots; ++i ) {
					inv.setItem(i, GUIicons.SLOTFILLER);
				}
				inv.setItem(11, GUIicons.EXPIERENCEOBELISK_STORE_1);
				inv.setItem(13, GUIicons.EXPIERENCEOBELISK_STORE_10);
				inv.setItem(15, GUIicons.EXPIERENCEOBELISK_STORE_ALL);
				
				inv.setItem(29, GUIicons.EXPIERENCEOBELISK_RETRIEVE_1);
				inv.setItem(31, GUIicons.EXPIERENCEOBELISK_RETRIEVE_10);
				inv.setItem(33, GUIicons.EXPIERENCEOBELISK_RETRIEVE_ALL);
				inv.setItem(49, GUIicons.CLOSE_ICON);
				inv.setItem(53, GUIicons.EXPIERENCEOBELISK_PICKUP);
				for (int i = 0; i < xpCache; ++i ) {
					if(level <= 15 && level >= 0) {
						if(xpMax == xp) {
							level++;
							if(level <= 15) {
								xpMax = xpMax+2;	
							}else {
								xpMax = xpMax+5;	
							}
							xp = 0;
						}else {
							xp++;
						}
					}else if(level <= 30 && level >= 16) {
						if(xpMax == xp) {
							level++;
							if(level <= 30) {
								xpMax = xpMax+5;	
							}else {
								xpMax = xpMax+9;
							}
							xp = 0;
						}else {
							xp++;
						}
					}else if(level >= 31) {
						if(xpMax == xp) {
							level++;
							xpMax = xpMax+9;
							xp = 0;
						}else {
							xp++;
						}
					}
				}
				prozentToLevelup = xp*100/xpMax;
			}
		});
		p.openInventory(inv);
	}
}
