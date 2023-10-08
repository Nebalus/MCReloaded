package de.nebalus.mc.mcreloaded.item;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import de.nebalus.mc.mcreloaded.MCRCore;

public class CustomItemReader {

	private final ItemStack itemstack;

	public CustomItemReader(ItemStack itemstack) {
		this.itemstack = itemstack;
	}

	public ItemStack getItemStack() {
		return itemstack;
	}

	public boolean isEmpty() {
		return itemstack == null || itemstack.getType().equals(Material.AIR);
	}

	public boolean isCustomItem() {
		if (!itemstack.hasItemMeta())
			return false;

		final ItemMeta itemmeta = itemstack.getItemMeta();

		if (itemmeta.getPersistentDataContainer().isEmpty())
			return false;

		PersistentDataContainer pdc = itemmeta.getPersistentDataContainer();

		for (NamespacedKey ns : pdc.getKeys()) {
			if (ns.getNamespace().equalsIgnoreCase(MCRCore.getInstance().getName())) {
				return true;
			}
		}
		return false;
	}
}
