package de.nebalus.mc.mcreloaded.item;

import java.util.ArrayList;

import de.nebalus.mc.mcreloaded.item.custom.IronHammer;

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
