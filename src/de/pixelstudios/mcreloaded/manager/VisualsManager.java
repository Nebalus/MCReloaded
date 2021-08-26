package de.pixelstudios.mcreloaded.manager;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import de.pixelstudios.mcreloaded.MCReloaded;
import io.pixelstudios.libary.MathLibary;

public class VisualsManager {

	private MCReloaded plugin;
	private PlayerManager playerManager;
	public VisualsManager(MCReloaded plugin) {
		this.playerManager = plugin.getPlayerManager();
		this.plugin = plugin;
		updaterActionBar();
	}
	@SuppressWarnings("deprecation")
	private void updaterActionBar() {
		Bukkit.getScheduler().scheduleAsyncRepeatingTask(plugin, new Runnable() {		
			@Override
			public void run() {
				for(Player p : Bukkit.getOnlinePlayers()) {
					int triggerprozent = 20;
					int healthProzent = (int) p.getHealth()*100/(int) p.getMaxHealth();
					if((int) healthProzent > triggerprozent) {
						if(p.getGameMode().equals(GameMode.ADVENTURE) || p.getGameMode().equals(GameMode.SURVIVAL)) {
							UserProfile up = playerManager.getProfile(p);
							double thirst = MathLibary.roundUp(MathLibary.getPercent(up.getThirst(),up.getMaxThirst()));
							double energy = MathLibary.roundUp(MathLibary.getPercent(up.getEnergy(),up.getMaxEnergy()));
							up.sendActionBar("§b"+thirst+"≈         §6"+energy+"⚡");
						}
					}
				}
			}
		}, 0, 20);
	}
	public void triggerHeartAnimation(Player p) {
		UserProfile up = playerManager.getProfile(p);
		double thirst = MathLibary.roundUp(MathLibary.getPercent(up.getThirst(),up.getMaxThirst()));
		double energy = MathLibary.roundUp(MathLibary.getPercent(up.getEnergy(),up.getMaxEnergy()));

		if(p.getGameMode().equals(GameMode.ADVENTURE) || p.getGameMode().equals(GameMode.SURVIVAL)) {
			up.sendActionBar("§b"+thirst+"≈       §c❤       §6"+energy+"⚡");
			Bukkit.getScheduler().scheduleSyncDelayedTask(plugin,new Runnable() {
				@Override
				public void run() {
					up.sendActionBar("§b"+thirst+"≈      §c(§f❤§c)      §6"+energy+"⚡");
				}
			}, 3);
			Bukkit.getScheduler().scheduleSyncDelayedTask(plugin,new Runnable() {
				@Override
				public void run() {
					up.sendActionBar("§b"+thirst+"≈     §c( ❤ )     §6"+energy+"⚡");
				}
			}, 6);
			Bukkit.getScheduler().scheduleSyncDelayedTask(plugin,new Runnable() {
				@Override
				public void run() {
					up.sendActionBar("§b"+thirst+"≈    §c(  ❤  )    §6"+energy+"⚡");	
				}
			}, 9);
			Bukkit.getScheduler().scheduleSyncDelayedTask(plugin,new Runnable() {
				@Override
				public void run() {			
					up.sendActionBar("§b"+thirst+"≈       §c❤       §6"+energy+"⚡");	
				}
			}, 12);
		}
	}
}
