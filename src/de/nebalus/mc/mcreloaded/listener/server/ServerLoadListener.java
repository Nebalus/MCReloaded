package de.nebalus.mc.mcreloaded.listener.server;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerLoadEvent;

import de.nebalus.mc.mcreloaded.announcement.Announcement;

public class ServerLoadListener implements Listener
{
	@EventHandler
	public void onServerReload(ServerLoadEvent e) 
	{
		if (e.getType() == ServerLoadEvent.LoadType.RELOAD) 
		{
			new Announcement()
				.setMessage("§cDETECTED SERVER RELOAD"
						+ "\n    §6Recipes may have been impacted"
						+ "\n    §6Relog to update your recipes")
				.showToConsole(false)
				.broadcast();
		}
	}
}
