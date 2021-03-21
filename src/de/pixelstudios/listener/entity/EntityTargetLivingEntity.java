package de.pixelstudios.listener.entity;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.persistence.PersistentDataType;

import de.pixelstudios.MCReloaded;
import de.pixelstudios.manager.ItemManager;
import de.pixelstudios.manager.PlayerManager;
import de.pixelstudios.manager.user.UserProfile;

public class EntityTargetLivingEntity implements Listener{
	private PlayerManager playermanager;
	
	public EntityTargetLivingEntity(MCReloaded plugin) {
		this.playermanager = plugin.getPlayerManager();
		
	}
	
	@EventHandler(
			  priority = EventPriority.HIGHEST
		   )
	public void onTarget(EntityTargetLivingEntityEvent e) {
		if(e.getTarget() instanceof Player) {
			Player p = (Player) e.getTarget();
			UserProfile pp = playermanager.getProfile(p);
			if(!pp.isVanished()) {
				EntityType et = e.getEntityType();
				PlayerInventory pi = p.getInventory();
				ItemStack[] ac = pi.getArmorContents();
				if(et.equals(EntityType.PIGLIN)) {
					int ifGolderiteArmor = 0;
					for(ItemStack is : ac) {
						if(is != null && is.hasItemMeta()) {
							if(is.getItemMeta().getPersistentDataContainer().has(ItemManager.golderite_armor_Key, PersistentDataType.BYTE)) {
								ifGolderiteArmor++;
							}
						}
					}
					//If the player has a full armor set
					if(ifGolderiteArmor >= 4) {
						e.setCancelled(true);
					}
				}
			}else {
				e.setCancelled(true);
			}
		}
	}
}
