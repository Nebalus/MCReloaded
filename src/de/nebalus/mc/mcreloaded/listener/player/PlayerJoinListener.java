package de.nebalus.mc.mcreloaded.listener.player;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import de.nebalus.mc.mcreloaded.MCRCore;
import de.nebalus.mc.mcreloaded.item.CustomItem;

public class PlayerJoinListener implements Listener
{

	@EventHandler
	private void onJoin(PlayerJoinEvent e)
	{
		Player p = e.getPlayer();
		
		e.setJoinMessage("§e" + p.getName() + " [§a+§e]");
		
		for(CustomItem citem : MCRCore.getInstance().getDataManager().getCustomItemHandler().getCustomItemList())
		{
			p.discoverRecipe(citem.getNamespacedKey());
		}
	}
	
}
