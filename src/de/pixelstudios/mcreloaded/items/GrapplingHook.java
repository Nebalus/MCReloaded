package de.pixelstudios.mcreloaded.items;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerFishEvent.State;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Vector;

import de.pixelstudios.mcreloaded.manager.ItemManager;

public class GrapplingHook implements Listener{


	@EventHandler(
			  ignoreCancelled = true,
		      priority = EventPriority.MONITOR
		   )
	private void onPlayerFish(PlayerFishEvent event) {
		Player p = event.getPlayer();
		ItemStack mainHand = p.getInventory().getItemInMainHand();
		ItemStack offHand = p.getInventory().getItemInOffHand();
			
		if(mainHand.hasItemMeta()) {
			if (mainHand.getItemMeta().getPersistentDataContainer().has(ItemManager.grappling_hook_Key, PersistentDataType.BYTE)){
				onFish(event,p);
			}	
		}else
		if(offHand.hasItemMeta()) {
			if (offHand.getItemMeta().getPersistentDataContainer().has(ItemManager.grappling_hook_Key, PersistentDataType.BYTE)){
				onFish(event,p);
			}	
		}
		
	}
	private void onFish(PlayerFishEvent event, Player p) {
		if (event.getState() == State.IN_GROUND) {
			List<Entity> nearbyEntities = p.getNearbyEntities(25, 25, 25);

			Entity hook = null;

			for (Entity e : nearbyEntities) // loop through entities
			{
				if (e.getType() == EntityType.FISHING_HOOK) //Hook found
				{
					hook = e;
					break;
				}
			}

			if (hook != null) {
				Location hookLoc = hook.getLocation();
				Location playerLoc = p.getLocation();

				playerLoc.setY(playerLoc.getY() + 0.5);


				Vector vector = hookLoc.toVector().subtract(playerLoc.toVector());
				if (vector.getY() > 0)
					vector.setY(Math.sqrt(vector.getY()));

				p.teleport(playerLoc);
				p.setVelocity(vector.multiply(0.5));
			}
		} else if (event.getState() == State.CAUGHT_ENTITY) {
			if (event.getCaught() != null) {
				Location playerLoc = p.getLocation();
				Location entityLoc = event.getCaught().getLocation();

				playerLoc.setY(playerLoc.getY() + 0.5);
				entityLoc.setY(entityLoc.getY() + 0.5);

				if (event.getCaught().getType() != EntityType.DROPPED_ITEM) {
					Vector vector = entityLoc.toVector().subtract(playerLoc.toVector());
					if (vector.getY() > 0)
						vector.setY(Math.sqrt(vector.getY()) * 4);

					p.teleport(playerLoc);
					p.setVelocity(vector.multiply(0.5).multiply(0.25));
				}

				Vector reverseVector = playerLoc.toVector().subtract(entityLoc.toVector());

				if (reverseVector.getY() > 0)
					reverseVector.setY(Math.sqrt(reverseVector.getY()));

				if (event.getCaught().getType() != EntityType.DROPPED_ITEM) {
					event.getCaught().teleport(entityLoc);
					event.getCaught().setVelocity(reverseVector.multiply(0.5).multiply(0.125));
				} else {
					if (reverseVector.getY() > 0)
						reverseVector.setY(Math.sqrt(reverseVector.getY()) * 0.5);

					event.getCaught().teleport(entityLoc);
					event.getCaught().setVelocity(reverseVector.multiply(0.5).multiply(0.00625));
				}
			}
		} else if (event.getState() == State.BITE || event.getState() == State.CAUGHT_FISH) {
			event.setCancelled(true);
			p.updateInventory();
		}
	}
}
