package de.pixelstudios.mcreloaded.listener.entity;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

import io.pixelstudios.libary.ChunkLibary;

public class EntityExplode implements Listener{

	@SuppressWarnings("unlikely-arg-type")
	@EventHandler(
			  ignoreCancelled = true,
			  priority = EventPriority.HIGHEST
		   )
	public void onTnT(EntityExplodeEvent e){
		Integer i = 0;
		for(Block b : e.blockList()) {
			if(!b.getType().equals(Material.TNT)) {
				ChunkLibary.breakblock(b.getLocation(), true, null, null);
				e.blockList().remove(i);
			}
			i++;
		}
	}
}
