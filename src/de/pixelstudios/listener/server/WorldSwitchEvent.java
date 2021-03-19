package de.pixelstudios.listener.server;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

import de.pixelstudios.MCReloaded;
import de.pixelstudios.utils.Utils;

public class WorldSwitchEvent implements Listener{
	@EventHandler(
			  ignoreCancelled = true,
			  priority = EventPriority.HIGHEST
		   )
	public void WorldswitchEvent(PlayerChangedWorldEvent e) {
		Player p = e.getPlayer();
	}
}
