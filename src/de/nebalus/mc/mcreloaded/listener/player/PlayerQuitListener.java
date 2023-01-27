package de.nebalus.mc.mcreloaded.listener.player;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener
{

	@EventHandler()
	private void onQuit(PlayerQuitEvent e) 
	{
		Player p = e.getPlayer();
		
		e.setQuitMessage("§e" + p.getName() + " [§c-§e]");
	}
	
}
