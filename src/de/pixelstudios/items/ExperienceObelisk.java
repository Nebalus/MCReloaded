package de.pixelstudios.items;

import org.bukkit.Location;

public class ExperienceObelisk {

	private int level = 1;
	private Location location = null;
	private int xp;
	
	public ExperienceObelisk(int level, Location location, int xp){
		this.level = level;
		this.location = location;
		this.xp = xp;
	}
	public Location getLocation() {
		return location;
	}
	public int getLevel() {
		return level;
	}
	public int getXP() {
		return xp;
	}
}
