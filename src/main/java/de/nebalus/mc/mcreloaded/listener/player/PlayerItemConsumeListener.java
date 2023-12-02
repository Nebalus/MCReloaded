package de.nebalus.mc.mcreloaded.listener.player;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class PlayerItemConsumeListener implements Listener {
	@EventHandler
	private void onConsume(PlayerItemConsumeEvent e) {
		Player p = e.getPlayer();
	}
}
