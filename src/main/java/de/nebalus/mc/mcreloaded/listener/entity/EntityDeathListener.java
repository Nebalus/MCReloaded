package de.nebalus.mc.mcreloaded.listener.entity;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class EntityDeathListener implements Listener {

	@EventHandler()
	private void onEntityDeath(EntityDeathEvent e) {
		final LivingEntity entity = e.getEntity();
		final Location entityLoc = entity.getEyeLocation();
		final World entityWorld = entityLoc.getWorld();

		switch (entity.getType()) {
		case ITEM_FRAME:
		case ARMOR_STAND:
			break;

		default:
			entityWorld.spawnParticle(Particle.SCULK_SOUL, entityLoc, 0);
			break;
		}
	}
}
