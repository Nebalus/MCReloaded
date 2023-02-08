package de.nebalus.mc.mcreloaded.announcement;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;

public class Announcement 
{

	protected String notifySound;
	protected String prefix;
	protected String messsage;
	protected String suffix;
	
	protected boolean playNotifySound = false;
	
	protected boolean showToConsole = false;
	protected boolean showToOperator = false;	
	protected boolean showToEveryone = false;
	
	public Announcement()
	{
		//Init der default werte
		this.setNotificationSound(Sound.BLOCK_NOTE_BLOCK_BIT);
		this.setPrefix("§7[§aMCR§eAlert§7]: §r");
		this.setMessage("UNDEFINED");
		this.setSuffix("");
		
		this.playNotifySound(true);
		
		this.showToConsole(true);
		this.showToOperator(true);
		this.showToEveryone(true);
	}
	
	public Announcement setNotificationSound(String namespacepath)
	{
		this.notifySound = namespacepath;
		
		return this;
	}
	
	public Announcement setNotificationSound(Sound newNotifySound)
	{
		final NamespacedKey key = newNotifySound.getKey();
		
		this.notifySound = key.getNamespace() + key.getKey();
		
		return this;
	}
	
	public Announcement setPrefix(String newPrefix)
	{
		this.prefix = newPrefix;
		
		return this;
	}
	
	public Announcement setMessage(String newMessage)
	{
		this.messsage = newMessage;
		
		return this;
	}
	
	public Announcement setSuffix(String newSuffix)
	{
		this.suffix = newSuffix;
		
		return this;
	}
	
	public Announcement playNotifySound(boolean newPlayNotifySound)
	{
		this.playNotifySound = newPlayNotifySound;
		
		return this;
	}
	
	public Announcement showToConsole(boolean newShowToConsole)
	{
		this.showToConsole = newShowToConsole;
		
		return this;
	}
	
	public Announcement showToOperator(boolean newShowToOperator)
	{
		this.showToOperator = newShowToOperator;
		
		return this;
	}
	
	public Announcement showToEveryone(boolean newShowToEveryone)
	{
		this.showToEveryone = newShowToEveryone;
		
		return this;
	}
	
	public void broadcast()
	{
		final String finalMessage = prefix + messsage + suffix;
		
		if(showToConsole)
		{
			Bukkit.getConsoleSender().sendMessage(finalMessage);
		}
		
		if(showToOperator || showToEveryone)
		{
			for(Player p : Bukkit.getOnlinePlayers())
			{
				if(playNotifySound)
				{
					p.playSound(p.getEyeLocation(), notifySound, SoundCategory.MASTER, 1, 1);
				}
				
				if((showToOperator && p.isOp()) || showToEveryone)
				{
					p.sendMessage(finalMessage);
				}
			}
		}
	}
	
}
