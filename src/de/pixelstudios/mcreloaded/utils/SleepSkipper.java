package de.pixelstudios.mcreloaded.utils;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import de.pixelstudios.mcreloaded.MCReloaded;

public class SleepSkipper implements Listener{
private MCReloaded plugin;
	private int overworld = 0;
	private int onlineplayers, sleepingplayers;
	private HashMap<Player, Long> cooldown = new HashMap<Player, Long>();
	private HashMap<Player, Boolean> allreadyMentioned = new HashMap<Player, Boolean>();
	public SleepSkipper(MCReloaded plugin) {
		this.plugin = plugin;
		updater();
	}
	public void updater() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {		
			
			@Override
			public void run() {
				
				try {
					if(isNight()) {
						int onlineusers, sleepingusers;
						onlineusers = 0;
						sleepingusers = 0;
						
						for(Player p : Bukkit.getOnlinePlayers()) {
							if(MCReloaded.Worlds.get(overworld).equals(p.getWorld())) {
								onlineusers++;
								if(p.isSleeping()) {
									if(cooldown.containsKey(p)) {
										if(cooldown.get(p) <= System.currentTimeMillis()) {
											sleepingusers++;
										 }
									}
								}
							}
						}
						onlineplayers = onlineusers;
						sleepingplayers = sleepingusers;
						for(Player p1 : Bukkit.getOnlinePlayers()) {
							if(MCReloaded.Worlds.get(overworld).equals(p1.getWorld())) {
								onlineusers++;
								if(p1.isSleeping()) {
									if(cooldown.containsKey(p1)) {
										if(cooldown.get(p1) <= System.currentTimeMillis()) {
											if(!allreadyMentioned.containsKey(p1)) {
												for(Player ps : Bukkit.getOnlinePlayers()) {
													if(MCReloaded.Worlds.contains(ps.getWorld())) {
														ps.sendMessage("§7"+p1.getName()+" is sleeping... zZz ("+getProzentSleeping()+"%)");
														allreadyMentioned.put(p1, true);
													}
												}
											}
										}
									}else {
										cooldown.put(p1, System.currentTimeMillis() + 4000);
									}
								}else {
									if(cooldown.containsKey(p1)) {
										cooldown.remove(p1);	
									}
									if(allreadyMentioned.containsKey(p1)) {
										allreadyMentioned.remove(p1);	
									}
								}
							}
						}
						
							
						if(isEnough()) {
							for(Player ps : Bukkit.getOnlinePlayers()) {
								if(MCReloaded.Worlds.contains(ps.getWorld())) {
									ps.sendMessage("§aGood morning...");
									if(ps.isSleeping()) {
										if(plugin.getMCReloadedConfig().MECHANICS_ENERGY_ENABLED) {
											plugin.getPlayerManager().getProfile(ps).setEnergy(plugin.getPlayerManager().getProfile(ps).getMaxEnergy());
										}
									}
								}
							}
						MCReloaded.Worlds.get(overworld).setTime(0l);
						MCReloaded.Worlds.get(overworld).setThundering(false);
						MCReloaded.Worlds.get(overworld).setClearWeatherDuration(20000);
					}
				}
						
				}catch(NullPointerException e) {
					
				}
				
			}
		}, 0, 5);
	}
	
	public boolean isEnough() {
		if(isNight()) {
			if(onlineplayers > 0) {
				if((int) getProzentSleeping() >= 50) {
					return true;
				}
				
			}
		}
		return false;
	}
	public int getProzentSleeping() {
		double schlafProzent = sleepingplayers*100/onlineplayers;
		return (int) schlafProzent;
	}
	public boolean isNight() {
		if(MCReloaded.Worlds.get(overworld).getTime() >= 12500 && MCReloaded.Worlds.get(overworld).getTime() <= 23500 || MCReloaded.Worlds.get(overworld).isThundering()) {
			return true;
		}
		return false;
	}
}

