package de.pixelstudios.datamanagement;

public class SQLManager {

public static void onCreate() {
		
		LiteSQL.onUpdate("CREATE TABLE IF NOT EXISTs playerdata(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, uuid VARCHAR, ipadresse VARCHAR, bantime INTEGER, banreason VARCHAR, whobannedhim VARCHAR)");
		LiteSQL.onUpdate("CREATE TABLE IF NOT EXISTs achievements(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, claims INTEGER, achievementname VARCHAR)");
	}
}
