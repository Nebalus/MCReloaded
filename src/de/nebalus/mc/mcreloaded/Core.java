package de.nebalus.mc.mcreloaded;

import org.bukkit.plugin.java.JavaPlugin;

import de.nebalus.mc.mcreloaded.customitem.CustomItemHandler;
import de.nebalus.mc.mcreloaded.data.BukkitRegister;

public class Core extends JavaPlugin
{
	private static Core instance;
	
	private CustomItemHandler cih;
	
	@Override
	public void onLoad() 
	{
		instance = this;

		super.onLoad();
	}
	
	@Override
	public void onEnable()
	{
		cih = new CustomItemHandler();
		
		BukkitRegister.registerListeners();
		BukkitRegister.registerCommands();
	}
	
	@Override
	public void onDisable()
	{
		cih = null;
		
		//Löscht alle modifizierte Rezepte von dem Server
		getServer().resetRecipes();
	}
	
	public CustomItemHandler getCustomItemHandler()
	{
		return cih;
	}
	
	public static Core getInstance() 
	{
		return instance;
	}
}
