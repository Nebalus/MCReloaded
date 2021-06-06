package de.pixelstudios.mcreloaded.listener.player;


import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import de.pixelstudios.mcreloaded.MCReloaded;
import de.pixelstudios.mcreloaded.manager.UserProfile;


public class PlayerMove implements Listener{

	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {	
		Player p = e.getPlayer();
		
		Location to = e.getTo();
	    Location from = e.getFrom();
		
		Vector vec = to.toVector();
		double i = vec.distance(from.toVector());
		
		UserProfile up = MCReloaded.getPlugin().getPlayerManager().getProfile(p);
		//up.setAfk(System.currentTimeMillis()+up.timeUntilAfk);
		/*
		Player p = e.getPlayer();
		Location loc = p.getLocation();
		loc.setY(loc.getY()-1);
		if(!loc.getBlock().getType().equals(Material.AIR)) {
			p.sendBlockChange(loc, Material.BEACON, (byte)0);
		}
		*/
		
	}
}
