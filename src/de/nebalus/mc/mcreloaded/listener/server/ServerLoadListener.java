package de.nebalus.mc.mcreloaded.listener.server;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerLoadEvent;

public class ServerLoadListener implements Listener
{
	@EventHandler
	public void onServerReload(ServerLoadEvent e) 
	{
		if (e.getType() == ServerLoadEvent.LoadType.RELOAD) 
		{
			for (Player p : Bukkit.getOnlinePlayers()) 
			{
				p.sendMessage("§cDETECTED SERVER RELOAD");
				p.sendMessage("    §6Recipes may have been impacted");
				p.sendMessage("    §6Relog to update your recipes");
			}
		}
	}
}
