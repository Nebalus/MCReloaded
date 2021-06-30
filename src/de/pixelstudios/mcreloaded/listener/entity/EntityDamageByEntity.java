package de.pixelstudios.mcreloaded.listener.entity;

import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import de.pixelstudios.mcreloaded.MCReloaded;
import de.pixelstudios.mcreloaded.utils.Achievements;

public class EntityDamageByEntity implements Listener{

	
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e) {
		if(e.getEntity() instanceof PigZombie) {
			if(e.getDamager() instanceof Player) {
				Player p = (Player) e.getDamager();
				MCReloaded.getPlugin().getPlayerManager().getProfile(p).giveAchievement(Achievements.COLLECTIVESECURITY);
			}
		}
	}
}
