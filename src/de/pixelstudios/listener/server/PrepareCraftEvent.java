package de.pixelstudios.listener.server;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.inventory.PrepareSmithingEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.SmithingInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import de.pixelstudios.manager.ItemManager;

public class PrepareCraftEvent implements Listener{

	
	@EventHandler(
			  ignoreCancelled = true,
			  priority = EventPriority.HIGHEST
		   )
	public void onPrepareCraft(PrepareItemCraftEvent e) {
		CraftingInventory inventory = e.getInventory();
		ItemStack[] matrix = inventory.getMatrix();
		Recipe recipe = e.getRecipe();
		if(recipe != null) {
			for(ItemStack item : matrix) {
				if(item != null) {
					if(ItemManager.isSimilar(item, ItemManager.CRYSTAL_FRAGMENT)) {
						if(recipe.getResult().getItemMeta().getPersistentDataContainer().has(ItemManager.cristal_armor_Key, PersistentDataType.BYTE)) {
							
						}else {
							inventory.setResult(null);
						}
					}else if(ItemManager.isSimilar(item, ItemManager.HEART_OF_THE_MINE)) {
							inventory.setResult(null);
					}
				}
			}
		}
	}
	@EventHandler(
			  ignoreCancelled = true,
			  priority = EventPriority.HIGHEST
		   )
	public void onPrepareSmithing(PrepareSmithingEvent e) {
		SmithingInventory inventory = e.getInventory();
		if(inventory.getItem(0) != null && inventory.getItem(1) != null) {
			if(inventory.getItem(1).getType().equals(Material.NETHERITE_INGOT) && inventory.getItem(1).getItemMeta().hasCustomModelData()) {
				if(inventory.getItem(0).getType().equals(Material.NETHERITE_HELMET)) {
					ItemStack item = new ItemStack(inventory.getItem(0));
					ItemMeta itemMeta = item.getItemMeta();
					itemMeta.setCustomModelData(ItemManager.GOLDERITE_ARMOR_HELMET.getItemMeta().getCustomModelData());
					itemMeta.getPersistentDataContainer().set(ItemManager.golderite_armor_Key, PersistentDataType.BYTE, (byte) 1);
					if(!item.getItemMeta().hasDisplayName()) {
						itemMeta.setDisplayName(ItemManager.GOLDERITE_ARMOR_HELMET.getItemMeta().getDisplayName());
					}
					item.setItemMeta(itemMeta);
					e.setResult(item);
				}else if(inventory.getItem(0).getType().equals(Material.NETHERITE_CHESTPLATE)) {
					
							ItemStack item = new ItemStack(inventory.getItem(0));
							ItemMeta itemMeta = item.getItemMeta();
							itemMeta.setCustomModelData(ItemManager.GOLDERITE_ARMOR_CHESTPLATE.getItemMeta().getCustomModelData());
							itemMeta.getPersistentDataContainer().set(ItemManager.golderite_armor_Key, PersistentDataType.BYTE, (byte) 1);
							if(!item.getItemMeta().hasDisplayName()) {
								itemMeta.setDisplayName(ItemManager.GOLDERITE_ARMOR_CHESTPLATE.getItemMeta().getDisplayName());
							}
							item.setItemMeta(itemMeta);
							e.setResult(item);
					}else if(inventory.getItem(0).getType().equals(Material.NETHERITE_LEGGINGS)) {
					
							ItemStack item = new ItemStack(inventory.getItem(0));
							ItemMeta itemMeta = item.getItemMeta();
							itemMeta.setCustomModelData(ItemManager.GOLDERITE_ARMOR_LEGGINGS.getItemMeta().getCustomModelData());
							itemMeta.getPersistentDataContainer().set(ItemManager.golderite_armor_Key, PersistentDataType.BYTE, (byte) 1);
							if(!item.getItemMeta().hasDisplayName()) {
								itemMeta.setDisplayName(ItemManager.GOLDERITE_ARMOR_LEGGINGS.getItemMeta().getDisplayName());
							}
							item.setItemMeta(itemMeta);
							e.setResult(item);
						}else if(inventory.getItem(0).getType().equals(Material.NETHERITE_BOOTS)) {
					
						ItemStack item = new ItemStack(inventory.getItem(0));
						ItemMeta itemMeta = item.getItemMeta();
						itemMeta.setCustomModelData(ItemManager.GOLDERITE_ARMOR_BOOTS.getItemMeta().getCustomModelData());
						itemMeta.getPersistentDataContainer().set(ItemManager.golderite_armor_Key, PersistentDataType.BYTE, (byte) 1);
						if(!item.getItemMeta().hasDisplayName()) {
							itemMeta.setDisplayName(ItemManager.GOLDERITE_ARMOR_BOOTS.getItemMeta().getDisplayName());
						}
						item.setItemMeta(itemMeta);
						e.setResult(item);
					}else {
						e.setResult(null);
					}
				}
			}else {
				e.setResult(null);
			}
		
	}
}
