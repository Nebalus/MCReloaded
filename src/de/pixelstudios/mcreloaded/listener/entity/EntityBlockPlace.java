package de.pixelstudios.mcreloaded.listener.entity;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import de.pixelstudios.mcreloaded.MCReloaded;
import de.pixelstudios.mcreloaded.utils.Achievements;

public class EntityBlockPlace implements Listener{

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		if(e.getPlayer() != null) {
			Player p = e.getPlayer();
			MCReloaded.getPlugin().getPlayerManager().getProfile(p).giveAchievement(Achievements.BUILDING);
			switch(e.getBlockPlaced().getType()) {
		
				default:
					break;
			}
		}
	}
}
