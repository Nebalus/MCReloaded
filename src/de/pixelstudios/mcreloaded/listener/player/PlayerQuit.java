package de.pixelstudios.mcreloaded.listener.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import de.pixelstudios.mcreloaded.MCReloaded;
import de.pixelstudios.mcreloaded.manager.PlayerManager;
import de.pixelstudios.mcreloaded.manager.UserProfile;

public class PlayerQuit implements Listener{

	private PlayerManager playermanager;
	
	public PlayerQuit(MCReloaded plugin) {
		this.playermanager = plugin.getPlayerManager();
	}
	
	@EventHandler(
			  priority = EventPriority.HIGHEST
		   )
	public void onLeave(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		UserProfile up = playermanager.getProfile(p);
		Bukkit.broadcastMessage("�e"+p.getName()+" [�c-�e]");
		e.setQuitMessage(null);
		up.setLastTimeOnline(System.currentTimeMillis());
		playermanager.unloadProfile(p);
	}
}