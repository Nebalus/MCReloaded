package de.pixelstudios.utils;

public enum Achievements {
	
	FIRSTJOIN("FIRSTJOIN","First join!","You have joined the server for the first time!",0),
	GILDEDNETHERITE("GILDEDNETHERITE","Gilded netherite?","Smithe a piece of gilded netherite armor!",0),
	GILDEDJUICE("GILDEDJUICE","Gilded juice!","Drink a golden apple juice!",0),
	PURIFIED("PURIFIED","Purified water!","Purify water with a cauldron!",0),
	STAYHYDRATED("STAYHYDRATED","Stay hydrated!","Drink a drink and gain water points!",0),
	FIRSTCHAT("FIRSTCHAT","Let the world hear your voice!","Use the chat for the first time!",0);
	
	String systemname;
	String name;
	String text;
	int exp;

	private Achievements(String systemname,String name,String text,int exp) {
		this.name = name;
		this.text = text;
		this.exp = exp;
		this.systemname = systemname;
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
	
}
