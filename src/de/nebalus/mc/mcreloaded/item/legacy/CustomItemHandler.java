package de.nebalus.mc.mcreloaded.item.legacy;

import java.util.ArrayList;

import de.nebalus.mc.mcreloaded.item.legacy.custom.IronHammer;

public class CustomItemHandler 
{
	private final ArrayList<CustomItem> customitemlist;
	
	public CustomItemHandler()
	{
		customitemlist = new ArrayList<CustomItem>();
		
		customitemlist.add(new IronHammer());
	}
	
	public ArrayList<CustomItem> getCustomItemList()
	{
		return customitemlist;
	}
}
