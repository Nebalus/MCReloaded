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
						if(recipe.getResult().equals(ItemManager.CRYSTAL_ARMOR_BOOTS)) {
							
						}
						else if(recipe.getResult().equals(ItemManager.CRYSTAL_ARMOR_CHESTPLATE)) {
							
						}
						else if(recipe.getResult().equals(ItemManager.CRYSTAL_ARMOR_HELMET)) {
		
						}
						else if(recipe.getResult().equals(ItemManager.CRYSTAL_ARMOR_LEGGINGS)) {
		
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
			if(inventory.getItem(1).getType().equals(Material.GOLD_BLOCK) || inventory.getItem(1).getType().equals(Material.NETHERITE_INGOT)) {
				if(inventory.getItem(0).getType().equals(Material.NETHERITE_HELMET)) {
					if(!inventory.getItem(0).getItemMeta().hasCustomModelData()) {
						ItemStack item = new ItemStack(inventory.getItem(0));
						ItemMeta itemMeta = item.getItemMeta();
						itemMeta.setCustomModelData(ItemManager.GILDED_NETHERITE_ARMOR_HELMET.getItemMeta().getCustomModelData());
						itemMeta.getPersistentDataContainer().set(ItemManager.gilded_netherite_armor_Key, PersistentDataType.BYTE, (byte) 1);
						if(!item.getItemMeta().hasDisplayName()) {
							itemMeta.setDisplayName(ItemManager.GILDED_NETHERITE_ARMOR_HELMET.getItemMeta().getDisplayName());
						}
						item.setItemMeta(itemMeta);
						e.setResult(item);
					}else {
						e.setResult(null);
					}
				}
				if(inventory.getItem(0).getType().equals(Material.NETHERITE_CHESTPLATE)) {
					if(!inventory.getItem(0).getItemMeta().hasCustomModelData()) {
						ItemStack item = new ItemStack(inventory.getItem(0));
						ItemMeta itemMeta = item.getItemMeta();
						itemMeta.setCustomModelData(ItemManager.GILDED_NETHERITE_ARMOR_CHESTPLATE.getItemMeta().getCustomModelData());
						itemMeta.getPersistentDataContainer().set(ItemManager.gilded_netherite_armor_Key, PersistentDataType.BYTE, (byte) 1);
						if(!item.getItemMeta().hasDisplayName()) {
							itemMeta.setDisplayName(ItemManager.GILDED_NETHERITE_ARMOR_CHESTPLATE.getItemMeta().getDisplayName());
						}
						item.setItemMeta(itemMeta);
						e.setResult(item);
					}else {
						e.setResult(null);
					}
				}
				if(inventory.getItem(0).getType().equals(Material.NETHERITE_LEGGINGS)) {
					if(!inventory.getItem(0).getItemMeta().hasCustomModelData()) {
						ItemStack item = new ItemStack(inventory.getItem(0));
						ItemMeta itemMeta = item.getItemMeta();
						itemMeta.setCustomModelData(ItemManager.GILDED_NETHERITE_ARMOR_LEGGINGS.getItemMeta().getCustomModelData());
						itemMeta.getPersistentDataContainer().set(ItemManager.gilded_netherite_armor_Key, PersistentDataType.BYTE, (byte) 1);
						if(!item.getItemMeta().hasDisplayName()) {
							itemMeta.setDisplayName(ItemManager.GILDED_NETHERITE_ARMOR_LEGGINGS.getItemMeta().getDisplayName());
						}
						item.setItemMeta(itemMeta);
						e.setResult(item);
					}else {
						e.setResult(null);
					}
				}
				if(inventory.getItem(0).getType().equals(Material.NETHERITE_BOOTS)) {
					if(!inventory.getItem(0).getItemMeta().hasCustomModelData()) {
						ItemStack item = new ItemStack(inventory.getItem(0));
						ItemMeta itemMeta = item.getItemMeta();
						itemMeta.setCustomModelData(ItemManager.GILDED_NETHERITE_ARMOR_BOOTS.getItemMeta().getCustomModelData());
						itemMeta.getPersistentDataContainer().set(ItemManager.gilded_netherite_armor_Key, PersistentDataType.BYTE, (byte) 1);
						if(!item.getItemMeta().hasDisplayName()) {
							itemMeta.setDisplayName(ItemManager.GILDED_NETHERITE_ARMOR_BOOTS.getItemMeta().getDisplayName());
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
		}else {
			e.setResult(null);
		}
	}
}
