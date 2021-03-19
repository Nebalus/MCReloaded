package de.pixelstudios.listener.entity;

import org.bukkit.entity.Piglin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class EntityDamage implements Listener{
	
	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		if(e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
		}
		if(e.getEntity() instanceof Piglin) {
			if(e.getCause().equals(DamageCause.ENTITY_ATTACK)) {
				
			}
		}
	}
}
