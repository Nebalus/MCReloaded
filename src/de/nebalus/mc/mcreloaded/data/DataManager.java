package de.nebalus.mc.mcreloaded.data;

import org.bukkit.scheduler.BukkitRunnable;

import de.nebalus.mc.mcreloaded.MCRCore;
import de.nebalus.mc.mcreloaded.item.legacy.CustomItemHandler;

public class DataManager 
{
	
	private static final int SAVE_DELAY = 12000;
	
	private MCRCore ownerInstance;
	private boolean doSave;
	
	private CustomItemHandler customItemHandler;
	
	public DataManager(MCRCore ownerInstance)
	{
		this.ownerInstance = ownerInstance;
	}
	
	public void preLoad()
	{
		
	}
	
	public void load()
	{
		this.customItemHandler = new CustomItemHandler();
		
		this.startAutoSaveThread();
		
		this.doSave = true;
	}
	
	private void startAutoSaveThread() 
	{
		new BukkitRunnable() 
		{
			@Override
			public void run()
			{
				DataManager.this.reloadConfig();
				DataManager.this.save();

			}
		}.runTaskTimerAsynchronously(MCRCore.getInstance(), SAVE_DELAY, SAVE_DELAY);
	}
	
	public void reloadConfig() 
	{
		
	}
	
	public void save()
	{
		if (!this.doSave)
			return;
	}
	
	public void setDoSave(boolean doSave) 
	{
		this.doSave = doSave;
	}


	public CustomItemHandler getCustomItemHandler()
	{
		return customItemHandler;
	}
}
