package de.nebalus.mc.mcreloaded.listener.player;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerAsyncChatListener implements Listener
{
	
	public static final HashMap<UUID, Long> PLAYER_CHATCOOLDOWN = new HashMap<>();
	public static final String DEFAULT_CHATCOLOR = "&f";
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e)
	{
		if(e.isCancelled()) return;
		e.setCancelled(true);
		
		final Player p = e.getPlayer();
		final String m = e.getMessage();
		
		for(Player cp : Bukkit.getOnlinePlayers())
		{
			if(m.contains(cp.getName().toLowerCase())) 
			{
				cp.playSound(cp.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 3, 2);
			}		
			
			sendMessage(p, m);
		}
	}
	
	private void sendMessage(Player p, String message)
	{
		p.sendMessage("§7" + p.getName() + " §8» §r" + ChatColor.translateAlternateColorCodes('&', DEFAULT_CHATCOLOR + message));
	}

	
}
