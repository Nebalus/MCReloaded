package de.pixelstudios.mcreloaded.listener.entity;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Piglin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageByEntity implements Listener{

	
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e) {
		if(e.getEntity() instanceof Piglin) {
			if(e.getDamager() instanceof Player) {
				Player p = (Player) e.getDamager();
				Entity pn = e.getEntity();
				((Mob) pn).setTarget(p);
			}
		}
	}
}
