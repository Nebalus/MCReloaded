package de.nebalus.mc.mcreloaded.listener.player;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerLoginListener implements Listener {

	@EventHandler
	private void onLogin(PlayerLoginEvent e) {
		Player p = e.getPlayer();

	}
}
