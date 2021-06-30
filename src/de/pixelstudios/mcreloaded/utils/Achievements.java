package de.pixelstudios.mcreloaded.utils;

public enum Achievements {
	
	FIRSTJOIN("FIRSTJOIN","First join!","Join the server for the first time!",0,0),
	GOLDERITE("GOLDERITE","Golderite?","Craft a golderite ingot!",0,1),
	PIGLINDISTRACKTOR("PIGLINDISTRACKTOR", "Piglin Distracktor", "Distrack a piglin with golderite armor",0,2),
	GILDEDJUICE("GILDEDJUICE","Gilded juice!","Drink a golden apple juice!",0,1),
	PURIFIED("PURIFIED","Purified water!","Purify water with a cauldron!",0,0),
	STAYHYDRATED("STAYHYDRATED","Stay hydrated!","Drink a drink and gain water points!",0,0),
	FIRSTCHAT("FIRSTCHAT","Let the world hear your voice!","Use the chat for the first time!",0,0),
	ANTIBATMAN("ANTIBATMAN", "Anti-Batman","Kill a bat",0,0),
	MOOTATED("MOOTATED", "Mootated","Murder a mutated cow",0,0),
	COLLECTIVESECURITY("COLLECTIVESECURITY","Collective Security","Witness the collective power of zombified piglins",0,0),
	MINESPAWNER("MINESPAWNER","Unthinkable","Break a spawner",0,0),
	NEWBEDROCK("NEWBEDROCK","Bedrock 2.0","Break a obsidian block with your hand",0,0);
	
	String systemname;
	String name;
	String text;
	int exp;
	int rarity;

	private Achievements(String systemname,String name,String text,int exp,int rarity) {
		this.name = name;
		this.text = text;
		this.exp = exp;
		this.systemname = systemname;
		this.rarity = rarity;
	}
	public String getSystemName() {
		return systemname;
	}
	public String getName() {
		return name;
	}
	public String getText() {
		return text;
	}
	public int getExp(){
		return exp;
	}
	public int getRarity(){
		return rarity;
	}
}
