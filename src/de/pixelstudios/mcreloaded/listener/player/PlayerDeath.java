package de.pixelstudios.mcreloaded.listener.player;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
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
import de.pixelstudios.mcreloaded.manager.user.UserProfile;
import net.minecraft.server.v1_16_R3.PacketPlayInClientCommand;
import net.minecraft.server.v1_16_R3.PacketPlayInClientCommand.EnumClientCommand;


public class PlayerDeath implements Listener{
	
	private PlayerManager playermanager;
	private Config config;
	
	public PlayerDeath(MCReloaded plugin) {
		this.playermanager = plugin.getPlayerManager();
		this.config = plugin.getMCReloadedConfig();
	}
	
	
	public void respawn(final Player player, int time) {
	    	Bukkit.getScheduler().runTaskLater(MCReloaded.getPlugin(), new Runnable() {

				@Override
				public void run() {
					((CraftPlayer) player).getHandle().playerConnection.a(new PacketPlayInClientCommand(EnumClientCommand.PERFORM_RESPAWN));			
				}
	    	}, time);
	    }
	    

		@SuppressWarnings("deprecation")
		@EventHandler(
				  ignoreCancelled = true,
				  priority = EventPriority.HIGHEST
			   )
		public void onDeath(PlayerDeathEvent e) {
	  
	    	Player p = e.getEntity();
	    	Player k = p.getKiller();
	    	UserProfile pp = playermanager.getProfile(p);
		    if(k !=null) {
		         e.setDeathMessage(null);
		         for(Player player : Bukkit.getOnlinePlayers()) {
		        	 if(p == player) {
		            	player.sendMessage(" §c☠ §7You were killed by " + k.getName());	
		            }else
		            player.sendMessage(" §c☠ §7" + p.getName() +" was killed by " + k.getName());
		           }
		            ItemStack skullinfo = new ItemStack(Material.PLAYER_HEAD);
		    		SkullMeta skullinfo1 = (SkullMeta) skullinfo.getItemMeta();
		    		skullinfo1.setOwner(p.getName());
		    		skullinfo.setItemMeta(skullinfo1);
		            p.getWorld().dropItemNaturally(p.getEyeLocation(), skullinfo);
		            respawn(p, 10);
		            	
					}else {
						String deathmessage = e.getDeathMessage();
						if(deathmessage == null){
							deathmessage = p.getName()+" died";
						}
						for(Player player : Bukkit.getOnlinePlayers()) {	 
			            	if(p == player) {
			           			player.sendMessage(" §c☠ §7"+deathmessage.replace(p.getName(), "You"));
			           		}else
			           		player.sendMessage(" §c☠ §7"+deathmessage);
			            }
						e.setDeathMessage(null); 
			            respawn(p, 10);       	
					}
		    	pp.setThirst(pp.getMaxThirst());
		    	pp.setEnergy(config.MECHANICS_ENERGY_RESPAWN_LEVEL);
	    	
		}
		/*
		@EventHandler
		public void onDrop(PlayerDropItemEvent e) {
			e.getItemDrop().setCustomName(e.getItemDrop().getItemStack().getItemMeta().getDisplayName());
			e.getItemDrop().setCustomNameVisible(true);
			e.getItemDrop().setGravity(false);
			org.bukkit.util.Vector v = e.getItemDrop().getVelocity().multiply(2.0D);
				
			final Runnable task = new Runnable()
			{
				int times = 15;
				public void run()
				{								
						
					e.getItemDrop().setVelocity(v);
						if(times-- > 0)
							Bukkit.getScheduler().scheduleSyncDelayedTask(MCReloaded.getPlugin(), this, 20);
						
					}
				};
				
				Bukkit.getScheduler().scheduleSyncDelayedTask(MCReloaded.getPlugin(), task, -1);
				e.getItemDrop().setGlowing(true);
				
			}
			*/
}
