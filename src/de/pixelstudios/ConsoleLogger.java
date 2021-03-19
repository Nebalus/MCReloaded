package de.pixelstudios;

import org.bukkit.Bukkit;

public class ConsoleLogger {

	public static String DATA_MANAGER = "DataManager";
	public static String LITESQL = "LiteSQL";
	public static String BOOT_LOADER = "BootLoader";
	public static String WORLD_GENERATOR = "WorldGenerator";
	public static String ITEM_MANAGER = "ItemManager";
	public static String CACHE = "Cache";
	public static String FLUSH_MANAGER = "FlushManager";
	
    //info
    public static void info(String className, String message) {
    	if(className == null) {
    		Bukkit.getConsoleSender().sendMessage("§a[Info] §a" + message);
    	}else {
    		Bukkit.getConsoleSender().sendMessage("§a[Info] " + className + ": " + message);
    	}
    }

    //error
    public static void error(String className, String message) {
    	if(className == null) {
    		Bukkit.getConsoleSender().sendMessage("§4[Error] §4" + message);	
    	}else {
    		Bukkit.getConsoleSender().sendMessage("§4[Error] " + className + ": " + message);
    	}
    }

    //debug
    public static void debug(String className, String message) {
    	if(className == null) {
    		Bukkit.getConsoleSender().sendMessage("§e[Debug] " + message);
    	}else {
    		Bukkit.getConsoleSender().sendMessage("§e[Debug] " + className + ": " + message);
    	}
    }

    //warning
    public static void warning(String className, String message) {
    	if(className == null) {
    		Bukkit.getConsoleSender().sendMessage("§c[Warning] §c" + message);
    	}else {
    		Bukkit.getConsoleSender().sendMessage("§c[Warning] " + className + ": " + message);
    	}
    }

}