package de.nebalus.mc.mcreloaded.data;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import de.nebalus.mc.mcreloaded.Core;
import de.nebalus.mc.mcreloaded.command.MCCommand;
import de.nebalus.mc.mcreloaded.command.admin.ReloadCommand;
import de.nebalus.mc.mcreloaded.command.admin.RepairCommand;
import de.nebalus.mc.mcreloaded.listener.entity.EntityDeathListener;
import de.nebalus.mc.mcreloaded.listener.entity.EntitySpawnListener;
import de.nebalus.mc.mcreloaded.listener.player.PlayerBlockBreakListener;
import de.nebalus.mc.mcreloaded.listener.player.PlayerAsyncChatListener;
import de.nebalus.mc.mcreloaded.listener.player.PlayerDeathListener;
import de.nebalus.mc.mcreloaded.listener.player.PlayerInteractListener;
import de.nebalus.mc.mcreloaded.listener.player.PlayerItemConsumeListener;
import de.nebalus.mc.mcreloaded.listener.player.PlayerJoinListener;
import de.nebalus.mc.mcreloaded.listener.player.PlayerLoginListener;
import de.nebalus.mc.mcreloaded.listener.player.PlayerQuitListener;
import de.nebalus.mc.mcreloaded.listener.server.ServerLoadListener;

public final class BukkitRegister 
{
	
	public static void registerCommand(String name, MCCommand command, boolean enableExecutor, boolean enableTabCompleter)
	{
		if(enableExecutor)
		{
			Core.getInstance().getCommand(name).setExecutor(command);
		}
		
		if(enableTabCompleter)
		{
			Core.getInstance().getCommand(name).setTabCompleter(command);
		}
	}
	
	public static void registerCommands() 
	{
		registerCommand("reload", new ReloadCommand(), true, false);
		registerCommand("repair", new RepairCommand(), true, false);
	}
	
	public static void registerListener(Listener listener)
	{
		Bukkit.getPluginManager().registerEvents(listener, Core.getInstance());
	}
	
	public static void registerListeners() 
	{
		registerListener(new EntityDeathListener());
		registerListener(new EntitySpawnListener());
		
		registerListener(new PlayerAsyncChatListener());
		registerListener(new PlayerBlockBreakListener());
		registerListener(new PlayerDeathListener());
		registerListener(new PlayerInteractListener());
		registerListener(new PlayerItemConsumeListener());
		registerListener(new PlayerJoinListener());
		registerListener(new PlayerLoginListener());
		registerListener(new PlayerQuitListener());
		
		registerListener(new ServerLoadListener());	
	}
}