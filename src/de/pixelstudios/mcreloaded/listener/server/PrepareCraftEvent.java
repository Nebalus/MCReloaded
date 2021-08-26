package de.pixelstudios.mcreloaded.listener.server;

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
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import de.pixelstudios.mcreloaded.manager.ItemManager;

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
					if(ItemManager.isSimilar(item, ItemManager.HEART_OF_THE_MINE)) {
						inventory.setResult(null);
					}else if(ItemManager.isSimilar(item, ItemManager.GOLDERITE_INGOT)) {
						inventory.setResult(null);
					}else if(ItemManager.isSimilar(item, ItemManager.WARP_FUEL)){
						inventory.setResult(null);
					}
				}
			}
		}
	}
	@EventHandler(
			  priority = EventPriority.HIGHEST
		   )
	public void onPrepareSmithing(PrepareSmithingEvent e) {
		SmithingInventory inventory = e.getInventory();
		if(inventory.getItem(0) != null && inventory.getItem(1) != null) {
			ItemStack itemSlot1 = inventory.getItem(0);
			ItemStack itemSlot2 = inventory.getItem(1);
			ItemStack newItem = new ItemStack(itemSlot1);
			ItemMeta newItemMeta = newItem.getItemMeta();
			
			PersistentDataContainer pdc = itemSlot2.getItemMeta().getPersistentDataContainer();
			
			if(itemSlot2.getType().equals(Material.NETHERITE_INGOT)) {
				switch(itemSlot1.getType()) {
					case DIAMOND_HELMET:
					case DIAMOND_CHESTPLATE:
					case DIAMOND_LEGGINGS:
					case DIAMOND_BOOTS:
					case DIAMOND_AXE:
					case DIAMOND_HOE:
					case DIAMOND_PICKAXE:
					case DIAMOND_SHOVEL:
					case DIAMOND_SWORD:
						if (ItemManager.Tags.NETHERITE_INGOT.isTagged(itemSlot2))
							e.setResult(null);
						break;
						
						
					case NETHERITE_HELMET:
						if(!itemSlot1.getItemMeta().hasCustomModelData()) {
							if(pdc.has(ItemManager.golderite_ingot_Key, PersistentDataType.BYTE)) {
								
								newItemMeta.setCustomModelData(ItemManager.GOLDERITE_ARMOR_HELMET.getItemMeta().getCustomModelData());
								newItemMeta.getPersistentDataContainer().set(ItemManager.Modifiers.PIGLIN_PASSIVE.getKey(), PersistentDataType.BYTE, (byte) 1);
								if(!newItem.getItemMeta().hasDisplayName()) {
									newItemMeta.setDisplayName(ItemManager.GOLDERITE_ARMOR_HELMET.getItemMeta().getDisplayName());
								}
								newItemMeta.setLore(ItemManager.GOLDERITE_ARMOR_HELMET.getItemMeta().getLore());
								newItem.setItemMeta(newItemMeta);
								e.setResult(newItem);
							}
						}else {
							e.setResult(null);
						}
						break;
					case NETHERITE_CHESTPLATE:
						if(!itemSlot1.getItemMeta().hasCustomModelData()) {
							if(pdc.has(ItemManager.golderite_ingot_Key, PersistentDataType.BYTE)) {
								newItemMeta.setCustomModelData(ItemManager.GOLDERITE_ARMOR_CHESTPLATE.getItemMeta().getCustomModelData());
								newItemMeta.getPersistentDataContainer().set(ItemManager.Modifiers.PIGLIN_PASSIVE.getKey(), PersistentDataType.BYTE, (byte) 1);
								if(!newItem.getItemMeta().hasDisplayName()) {
									newItemMeta.setDisplayName(ItemManager.GOLDERITE_ARMOR_CHESTPLATE.getItemMeta().getDisplayName());
								}
								newItemMeta.setLore(ItemManager.GOLDERITE_ARMOR_HELMET.getItemMeta().getLore());
								newItem.setItemMeta(newItemMeta);
								e.setResult(newItem);
							}
						}else {
							e.setResult(null);
						}
						break;
					case NETHERITE_LEGGINGS:
						if(!itemSlot1.getItemMeta().hasCustomModelData()) {
							if(pdc.has(ItemManager.golderite_ingot_Key, PersistentDataType.BYTE)) {
								newItemMeta.setCustomModelData(ItemManager.GOLDERITE_ARMOR_LEGGINGS.getItemMeta().getCustomModelData());
								newItemMeta.getPersistentDataContainer().set(ItemManager.Modifiers.PIGLIN_PASSIVE.getKey(), PersistentDataType.BYTE, (byte) 1);
								if(!newItem.getItemMeta().hasDisplayName()) {
									newItemMeta.setDisplayName(ItemManager.GOLDERITE_ARMOR_LEGGINGS.getItemMeta().getDisplayName());
								}
								newItemMeta.setLore(ItemManager.GOLDERITE_ARMOR_HELMET.getItemMeta().getLore());
								newItem.setItemMeta(newItemMeta);
								e.setResult(newItem);						
							}
						}else {
							e.setResult(null);
						}
						break;
					case NETHERITE_BOOTS:
						if(!itemSlot1.getItemMeta().hasCustomModelData()) {
							if(pdc.has(ItemManager.golderite_ingot_Key, PersistentDataType.BYTE)) {
								newItemMeta.setCustomModelData(ItemManager.GOLDERITE_ARMOR_BOOTS.getItemMeta().getCustomModelData());
								newItemMeta.getPersistentDataContainer().set(ItemManager.Modifiers.PIGLIN_PASSIVE.getKey(), PersistentDataType.BYTE, (byte) 1);
								if(!newItem.getItemMeta().hasDisplayName()) {
									newItemMeta.setDisplayName(ItemManager.GOLDERITE_ARMOR_BOOTS.getItemMeta().getDisplayName());
								}
								newItemMeta.setLore(ItemManager.GOLDERITE_ARMOR_HELMET.getItemMeta().getLore());
								newItem.setItemMeta(newItemMeta);
								e.setResult(newItem);							
							}
						}else {
							e.setResult(null);
						}
						break;
					default:
						e.setResult(null);
						break;
				}
				
			}else {
				e.setResult(null);
			}
			}else {
				e.setResult(null);
		}
	}
}
/*
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
*/