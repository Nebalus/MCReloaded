package de.pixelstudios.mcreloaded.listener.player;

import java.util.Collection;

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
import io.pixelstudios.libary.MathLibary;
import io.pixelstudios.libary.NPCLibary;



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
	    	spawnNPC(p);
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
	@SuppressWarnings("unchecked")
	private void spawnNPC(Player player) {
		NPCLibary npc = new NPCLibary(player.getLocation(), MathLibary.generateShortID());
		npc.setPing(NPCLibary.Ping.NO_CONNECTION);
		npc.spawnNPC((Collection<Player>) Bukkit.getOnlinePlayers());
		npc.setASyncSkinByUUID(MCReloaded.getPlugin(), (Collection<Player>) Bukkit.getOnlinePlayers(), player.getUniqueId());
		npc.setNameTagVisibility((Collection<Player>) Bukkit.getOnlinePlayers(), false);
		NPCLibary.NPCMetaData metaData = npc.getMetadata();
		metaData.setPose(NPCLibary.Pose.SLEEPING);
		npc.updateMetadata((Collection<Player>) Bukkit.getOnlinePlayers());
		Bukkit.getScheduler().runTaskLater(MCReloaded.getPlugin(), new Runnable() {
			@Override
			public void run() {
				npc.destroyNPC((Collection<Player>) Bukkit.getOnlinePlayers());
			}
	    },20*60*5);
		
		/*
		EntityPlayer craftPLayer = ((CraftPlayer)player).getHandle();
		
		Property textures = (Property) craftPLayer.getProfile().getProperties().get("textures").toArray()[0];
		GameProfile gameProfile = new GameProfile(UUID.randomUUID(), player.getName());
		gameProfile.getProperties().put("textures", new Property("textures", textures.getValue(),textures.getSignature()));
		
		EntityPlayer corpse = new EntityPlayer(((CraftServer) Bukkit.getServer()).getServer(), ((CraftWorld)player.getWorld()).getHandle(), gameProfile);
		corpse.setPosition(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ());
		
		Location bed = player.getLocation().add(1,0,0);
		corpse.e(new BlockPosition(bed.getX(),bed.getY(),bed.getZ()));
		
		ScoreboardTeam team = new ScoreboardTeam(((CraftScoreboard) Bukkit.getScoreboardManager().getMainScoreboard()).getHandle(), player.getName());
		team.setNameTagVisibility(ScoreboardTeamBase.EnumNameTagVisibility.b);
		
		PacketPlayOutScoreboardTeam score1 = PacketPlayOutScoreboardTeam.a(team);
		PacketPlayOutScoreboardTeam score2 = PacketPlayOutScoreboardTeam.a(team,true);
		PacketPlayOutScoreboardTeam score3 = PacketPlayOutScoreboardTeam.a(team,corpse.getName(),PacketPlayOutScoreboardTeam.a.a);
		
		corpse.setPose(EntityPose.c);
		
		DataWatcher watcher = corpse.getDataWatcher();
		
		byte b = 0x01 | 0x02 | 0x04 | 0x08 | 0x10 | 0x20 | 0x40;
		watcher.set(DataWatcherRegistry.a.a(17),b);
		
		PacketPlayOutEntity.PacketPlayOutRelEntityMove move = new PacketPlayOutEntity.PacketPlayOutRelEntityMove(corpse.getId(), (byte) 0,(byte)((player.getLocation().getY() -1.7 )*32),(byte)0,false);
		
		for (Player on : Bukkit.getOnlinePlayers()) {
			PlayerConnection connection = ((CraftPlayer)on).getHandle().b;
			
			connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.a,corpse));
			connection.sendPacket(new PacketPlayOutNamedEntitySpawn(corpse));
			
			connection.sendPacket(score1);
			connection.sendPacket(score2);
			connection.sendPacket(score3);
			
			connection.sendPacket(new PacketPlayOutEntityMetadata(corpse.getId(),watcher,true));
			connection.sendPacket(move);
			Bukkit.getScheduler().runTaskLater(MCReloaded.getPlugin(), new Runnable() {
				@Override
				public void run() {
					connection.sendPacket(new PacketPlayOutEntityDestroy(corpse.getId()));	
				}
	    	},20*60);
		}
		*/
	}
}
