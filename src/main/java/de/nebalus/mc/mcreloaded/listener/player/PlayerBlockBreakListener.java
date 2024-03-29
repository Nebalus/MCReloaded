package de.nebalus.mc.mcreloaded.listener.player;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import de.nebalus.mc.mcreloaded.MCRCore;
import de.nebalus.mc.mcreloaded.item.CustomItemReader;
import de.nebalus.mc.mcreloaded.item.legacy.CustomItem;

public class PlayerBlockBreakListener implements Listener {

	@EventHandler
	private void onBlockBreak(BlockBreakEvent e) {
		if (e.isCancelled() || (e.getPlayer() == null)) {
			return;
		}

		Player p = e.getPlayer();
		ItemStack heldItem = p.getInventory().getItemInMainHand();
		CustomItemReader cir = new CustomItemReader(heldItem);

		if (cir.isEmpty() && !cir.isCustomItem()) {
			return;
		}

		ItemMeta itemMeta = heldItem.getItemMeta();
		PersistentDataContainer pdc = itemMeta.getPersistentDataContainer();

		for (CustomItem citem : MCRCore.getInstance().getDataManager().getCustomItemHandler().getCustomItemList()) {
			if (pdc.has(citem.getNamespacedKey(), PersistentDataType.BYTE)) {
				citem.executeOnBreak(e);
			}
		}
	}
}
//PersistentDataContainer pdc = itemMeta.getPersistentDataContainer();
//
//for(NamespacedKey ns : pdc.getKeys())
//{
//	final NamespacedKey convertedNK = new NamespacedKey(MCRCore.getInstance(), ns.getKey());
//
//	if(ns.getNamespace().equalsIgnoreCase("hanswurst") && !pdc.has(convertedNK, PersistentDataType.BYTE))
//	{
//		p.sendMessage("§cERROR: Legacy ItemStack format for Item (§a" + itemMeta.getDisplayName() + "§c) has been found!!");
//		p.sendMessage("§cTrying to autocorrect the Namespaces for the ItemStack: " + ns.getNamespace() + " - " + ns.getKey());
//		pdc.set(convertedNK, PersistentDataType.BYTE, (byte) 1);
//		heldItem.setItemMeta(itemMeta);
//	}
//}
