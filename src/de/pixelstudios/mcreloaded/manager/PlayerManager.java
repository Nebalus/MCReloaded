package de.pixelstudios.mcreloaded.manager;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import de.pixelstudios.mcreloaded.ConsoleLogger;
import de.pixelstudios.mcreloaded.MCReloaded;
import de.pixelstudios.mcreloaded.datamanagement.Config;

public class PlayerManager {
    private final Config config;
	private final String url;
	private HashMap<Player,UserProfile> playerprofile = new HashMap<Player,UserProfile>();
	
	public PlayerManager(MCReloaded plugin) {
	   this.config = plugin.getMCReloadedConfig();
	   this.url = config.RESOURCE_PACK_URL;
	   
	}
	public void applyResourcePack(Player player, int delay) {
        if (url != null) {
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(MCReloaded.getPlugin(), () -> {
                try {
                    player.setResourcePack(url);
                } catch (Exception e) {
                    Bukkit.getConsoleSender().sendMessage("ResourcePackURL is null or URL is too long! Plugin disabled.");
                    Bukkit.getPluginManager().disablePlugin(MCReloaded.getPlugin());
                    return;
                }
                getProfile(player).setResourcepackAccepted(true);
            }, delay);
        }
    }
	

	public boolean loadProfile(Player p) {	
		if(!playerprofile.containsKey(p)) {
			ConsoleLogger.debug(null,"Loading profile for "+p.getName());
			UserProfile pp = new UserProfile(p);
			playerprofile.put(p, pp);
			return true;
		}
		return false;
	}
	public boolean unloadProfile(Player p) {
		if(playerprofile.containsKey(p)) {
			ConsoleLogger.debug(null,"Unloading profile for "+p.getName());
			UserProfile pp = playerprofile.get(p);
			playerprofile.remove(p, pp);
			return true;
		}
		return false;
	}	
	public UserProfile getProfile(Player p) {
		if(playerprofile.containsKey(p)) {
			return playerprofile.get(p);
		}else {
			if(loadProfile(p)) {
				return playerprofile.get(p);
			}
		}
		return null;
	}	
	public HashMap<Player,UserProfile> getProfiles(){
		return playerprofile;
	}
	public boolean flushProfiles() {
		try {
			ConsoleLogger.debug(ConsoleLogger.FLUSH_MANAGER,"Flushing all cached OnlineProfiles!");
			playerprofile.clear();
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
