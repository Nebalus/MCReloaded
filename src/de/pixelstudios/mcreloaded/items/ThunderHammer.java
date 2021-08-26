package de.pixelstudios.mcreloaded.items;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import de.pixelstudios.mcreloaded.manager.ItemManager;
import fr.mrmicky.fastparticle.FastParticle;
import fr.mrmicky.fastparticle.ParticleType;
import de.pixelstudios.mcreloaded.MCReloaded;
public class ThunderHammer {

	@SuppressWarnings("deprecation")
	public static void attack(Player p, ItemStack item) {
		ItemMeta itemmeta = item.getItemMeta();
		PersistentDataContainer pdc = itemmeta.getPersistentDataContainer();
		if(pdc.get(ItemManager.stormlander_cooldown_Key, PersistentDataType.LONG) == null || pdc.get(ItemManager.stormlander_cooldown_Key, PersistentDataType.LONG).longValue() < System.currentTimeMillis()) {
			pdc.set(ItemManager.stormlander_cooldown_Key, PersistentDataType.LONG,System.currentTimeMillis()+20000);
			
			item.setItemMeta(itemmeta);
			final Location playerLoc = p.getLocation();
			ArmorStand bolt = (ArmorStand) playerLoc.getWorld().spawnEntity(playerLoc, EntityType.ARMOR_STAND);
			final Vector velocity = p.getLocation().getDirection().add(new Vector(0, 0.025, 0)).multiply(3);	
			bolt.setVelocity(velocity);
			bolt.setInvisible(true);
			bolt.setArms(false);
			bolt.setCustomName("Bolt");
			bolt.setHelmet(ItemManager.BOLT);
			new BukkitRunnable() {	
				@Override
				public void run() {
					if(bolt.isOnGround()) {
						final Location spawnloc = bolt.getLocation();
						bolt.remove();
						spawnloc.getWorld().strikeLightning(spawnloc);
						spawnloc.getWorld().strikeLightning(spawnloc.add(1,0,0));
						spawnloc.getWorld().strikeLightning(spawnloc.add(-1,0,0));
						spawnloc.getWorld().strikeLightning(spawnloc.add(0,0,1));
						spawnloc.getWorld().strikeLightning(spawnloc.add(0,0,-1));
						FastParticle.spawnParticle(spawnloc.getWorld(), ParticleType.EXPLOSION_HUGE, spawnloc, 20, 2f, 2f, 2f, 0f);
						cancel();
					}
				}
			}.runTaskTimer(MCReloaded.getPlugin(), 0, 10l);
		}
	}
}
