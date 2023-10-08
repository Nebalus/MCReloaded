package de.nebalus.mc.mcreloaded.item.legacy;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import de.nebalus.mc.mcreloaded.MCRCore;

public class CustomItem {
	private final NamespacedKey inamespace;
	private final ItemStack istack;
	private final ItemMeta imeta;
	private final PersistentDataContainer psc;

	public CustomItem(Material material, String namespace_key) {
		inamespace = new NamespacedKey(MCRCore.getInstance(), namespace_key);

		istack = new ItemStack(material);
		imeta = istack.getItemMeta();
		psc = imeta.getPersistentDataContainer();

		psc.set(inamespace, PersistentDataType.BYTE, (byte) 1);
	}

	protected void setDisplayName(String displayname) {
		imeta.setDisplayName(displayname);
	}

	protected void setCustomModelData(int custommodeldata) {
		imeta.setCustomModelData(custommodeldata);
	}

	protected void setLore(String... lore) {
		List<String> finallore = new ArrayList<String>();
		for (String sublore : lore) {
			finallore.add(sublore);
		}
		imeta.setLore(finallore);
	}

	protected void setLore(ArrayList<String> lore) {
		imeta.setLore(lore);
	}

	public NamespacedKey getNamespacedKey() {
		return inamespace;
	}

	public ItemStack getAsItemStack() {
		ItemStack out = new ItemStack(istack);
		out.setItemMeta(imeta);
		return out;
	}

	public void executeOnBreak(BlockBreakEvent event) {
	}
}
