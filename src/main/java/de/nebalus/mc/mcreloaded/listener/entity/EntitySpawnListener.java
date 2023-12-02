package de.nebalus.mc.mcreloaded.listener.entity;

import java.util.Random;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Phantom;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class EntitySpawnListener implements Listener {
	@EventHandler()
	public void onSpawn(EntitySpawnEvent e) {
		Entity entity = e.getEntity();
		EntityType entitytype = entity.getType();

		if (entitytype.equals(EntityType.ARMOR_STAND)) {
			ArmorStand armorstand = (ArmorStand) entity;

			armorstand.setArms(true);
		}

		if (entitytype.equals(EntityType.PHANTOM)) {
			Phantom phantom = (Phantom) entity;
			Random random = new Random();
			int iran = random.nextInt(1, 15);

			phantom.setSize(iran);
		}
	}
}
