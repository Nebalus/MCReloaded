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
		    if(k !=null) { 	
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
			}
		    e.setDeathMessage(null); 
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
		/*
	private boolean spawnDeathNPC(Player p) {
		Location loc = p.getLocation();
		MinecraftServer server = ((CraftServer)Bukkit.getServer()).getServer();
		WorldServer world = ((CraftWorld) Bukkit.getWorld(loc.getWorld().getName())).getHandle();
		GameProfile gameProfile = new GameProfile(UUID.randomUUID(), "");
		EntityPlayer npc = new EntityPlayer(server, world, gameProfile);
		npc.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
		
		String[] name = Utils.getSkin(p, p.getName());
		gameProfile.getProperties().put("textures", new Property("textures", name[0], name[1]));
	
		DataWatcher dw = new DataWatcher(null);
	    dw.register(new DataWatcherObject<>(6, DataWatcherRegistry.s), EntityPose.a);
	    
		for(Player player : Bukkit.getOnlinePlayers()) {
			PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;	
			connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, npc));
			connection.sendPacket(new PacketPlayOutNamedEntitySpawn(npc));
			connection.sendPacket(new PacketPlayOutEntityHeadRotation(npc, (byte) (npc.yaw * 256 / 360)));
			connection.sendPacket(new PacketPlayOutEntityMetadata(npc.getId(), dw, true));
			Bukkit.getScheduler().runTaskLater(MCReloaded.getPlugin(), new Runnable() {
				@Override
				public void run() {
					connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, npc));	
				}
	    	},10);
			Bukkit.getScheduler().runTaskLater(MCReloaded.getPlugin(), new Runnable() {
				@Override
				public void run() {
					connection.sendPacket(new PacketPlayOutEntityDestroy(npc.getId()));	
				}
	    	},20*60);
		}
		return false;
	}
	*/
}
