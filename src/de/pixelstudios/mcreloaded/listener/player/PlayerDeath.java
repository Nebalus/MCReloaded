package de.pixelstudios.mcreloaded.listener.player;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import de.pixelstudios.mcreloaded.MCReloaded;
import de.pixelstudios.mcreloaded.datamanagement.Config;
import de.pixelstudios.mcreloaded.manager.PlayerManager;
import de.pixelstudios.mcreloaded.manager.UserProfile;



public class PlayerDeath implements Listener{
	
	private PlayerManager playermanager;
	private Config config;
	
	public PlayerDeath(MCReloaded plugin) {
		this.playermanager = plugin.getPlayerManager();
		this.config = plugin.getMCReloadedConfig();
	}
		@SuppressWarnings("deprecation")
		@EventHandler(
				  ignoreCancelled = true,
				  priority = EventPriority.HIGHEST
			   )
		public void onDeath(PlayerDeathEvent e) {  
	    	Player p = e.getEntity();
	    	Entity k = p.getKiller();
	    	UserProfile pp = playermanager.getProfile(p);
	    	//spawnDeathNPC(p);
		    if(k != null) { 	
		        for(Player player : Bukkit.getOnlinePlayers()) {
		        	if(p == player) {
		        		player.sendMessage(" §c☠ §7You were killed by " + k.getName());	
		            }else {
		            	player.sendMessage(" §c☠ §7" + p.getName() +" was killed by " + k.getName());
		            }
		      	}
		    	ItemStack skullinfo = new ItemStack(Material.PLAYER_HEAD);
		    	SkullMeta skullinfo1 = (SkullMeta) skullinfo.getItemMeta();
		    	skullinfo1.setOwner(p.getName());
		    	skullinfo.setItemMeta(skullinfo1);
		      	p.getWorld().dropItemNaturally(p.getEyeLocation(), skullinfo);     	
			}else {
				String deathmessage = e.getDeathMessage();
				if(deathmessage == null){
					deathmessage = p.getName()+" died";
				}
				for(Player player : Bukkit.getOnlinePlayers()) {	 
			    	if(p == player) {
			        	player.sendMessage(" §c☠ §7"+deathmessage.replace(p.getName(), "You"));
			    	}else {
			        	player.sendMessage(" §c☠ §7"+deathmessage);
			    	}
			    }
			}
		    e.setDeathMessage(null); 
		    pp.setThirst(pp.getMaxThirst());
		 	pp.setEnergy(config.MECHANICS_ENERGY_RESPAWN_LEVEL);
	}
}
