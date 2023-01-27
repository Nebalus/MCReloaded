package de.nebalus.mc.mcreloaded.listener.player;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import de.nebalus.mc.mcreloaded.Core;
import de.nebalus.mc.mcreloaded.customitem.CustomItem;

public class PlayerBlockBreakListener implements Listener
{
	
	@EventHandler
	private void onBlockBreak(BlockBreakEvent e)
	{
		if(e.isCancelled()) return;
		if(e.getPlayer() == null) return;
		
		Player p = e.getPlayer();
		ItemStack item = p.getInventory().getItemInMainHand();

		if(item.getType().equals(Material.AIR)) return;
		
		ItemMeta itemmeta = item.getItemMeta();
		
		if(itemmeta.getPersistentDataContainer().isEmpty()) return;
		
		//
		PersistentDataContainer pdc = itemmeta.getPersistentDataContainer();
		
		for(NamespacedKey ns : pdc.getKeys())
		{
			final NamespacedKey convertedNK = new NamespacedKey(Core.getInstance(), ns.getKey());
			
			if(ns.getNamespace().equalsIgnoreCase("hanswurst") && !pdc.has(convertedNK, PersistentDataType.BYTE))
			{
				p.sendMessage("§cERROR: Legacy ItemStack format for Item (§a" + itemmeta.getDisplayName() + "§c) has been found!!");
				p.sendMessage("§cTrying to autocorrect the Namespaces for the ItemStack: " + ns.getNamespace() + " - " + ns.getKey());
				pdc.set(convertedNK, PersistentDataType.BYTE, (byte) 1);
				item.setItemMeta(itemmeta);
			}
		}	
		//
		
		for(CustomItem citem : Core.getInstance().getCustomItemHandler().getCustomItemList())
		{	
			if(pdc.has(citem.getNamespacedKey(), PersistentDataType.BYTE))
			{
				citem.executeOnBreak(e);
			}
		}
	}
}
