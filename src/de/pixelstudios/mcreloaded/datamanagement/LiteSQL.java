package de.pixelstudios.mcreloaded.datamanagement;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import de.pixelstudios.mcreloaded.ConsoleLogger;
import de.pixelstudios.mcreloaded.MCReloaded;
import de.pixelstudios.mcreloaded.messaging.MessageFormatter;

public class LiteSQL {
	private MessageFormatter messageFormater;
	
	public LiteSQL(MCReloaded plugin) {
		this.messageFormater = plugin.getMessageFormatter();
	}
	
	private static String serverPath = MCReloaded.getPlugin().getDataFolder().getAbsolutePath()
			.substring(0, MCReloaded.getPlugin().getDataFolder().getAbsolutePath().length() - (9 + MCReloaded.getPlugin().getName().length()));
	
	private static Connection conn;
	private static Statement stmt;
	public void connect() {
		conn = null; 
		try {		
			FileManager.createServerdir();	
			File datenbank = new File(serverPath+"/PixelStudios/MCReloaded/datenbank.db");
			if(!datenbank.exists()) {
				ConsoleLogger.debug(ConsoleLogger.DATA_MANAGER, "Creating MCReloaded database in "+serverPath+"/PixelStudios/MCReloaded/");
				datenbank.createNewFile();
			}
			
			String url = "jdbc:sqlite:"+ datenbank.getPath();
			conn = DriverManager.getConnection(url);
			
			stmt = conn.createStatement();
			onUpdate("CREATE TABLE IF NOT EXISTs playerdata(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, uuid VARCHAR, ipadresse VARCHAR)");
			onUpdate("CREATE TABLE IF NOT EXISTs achievements(claims INTEGER, achievementname VARCHAR)");
			ConsoleLogger.info(ConsoleLogger.LITESQL,messageFormater.format(false, "console.enable.litesql-loaded"));
		}catch(SQLException | IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void disconnect() {
		try {
			if(isConnected()) {
				conn.close();
				ConsoleLogger.info(ConsoleLogger.LITESQL,messageFormater.format(false, "console.disable.litesql-disabled"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	 public static boolean isConnected() {
	    	return (conn == null ? false : true);
	    }
	 
	 
	 public static Connection getConnection() {
	    	return conn;
	    }
	
	 
	public static void onUpdate(String sql) {
		try {
			stmt.execute(sql);
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
		public static ResultSet onQuery(String sql) {
		try {
			return stmt.executeQuery(sql);
		}catch (SQLException e) {
			e.printStackTrace();
		}
			return null;
		}
	
}
