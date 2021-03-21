package de.pixelstudios.utils;

public enum Achievements {
	
	FIRSTJOIN("FIRSTJOIN","First join!","You have joined the server for the first time!",0,0),
	GILDEDNETHERITE("GILDEDNETHERITE","Gilded netherite?","Smithe a piece of gilded netherite armor!",0,1),
	GILDEDJUICE("GILDEDJUICE","Gilded juice!","Drink a golden apple juice!",0,0),
	PURIFIED("PURIFIED","Purified water!","Purify water with a cauldron!",0,0),
	STAYHYDRATED("STAYHYDRATED","Stay hydrated!","Drink a drink and gain water points!",0,0),
	FIRSTCHAT("FIRSTCHAT","Let the world hear your voice!","Use the chat for the first time!",0,0);
	
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
