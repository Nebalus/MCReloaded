package de.pixelstudios.listener.entity;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class EntitySpawn implements Listener{
	
	@EventHandler(
			  priority = EventPriority.HIGHEST
		   )
	public void onSpawn(EntitySpawnEvent event) {
		Entity e = event.getEntity();
		if(e.getType().equals(EntityType.ARMOR_STAND)) {
			ArmorStand armorstand = (ArmorStand) e;
			armorstand.setArms(true);
		}
		
	}
	
}
