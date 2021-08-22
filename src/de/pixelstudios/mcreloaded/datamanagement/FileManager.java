package de.pixelstudios.mcreloaded.datamanagement;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

import de.pixelstudios.mcreloaded.ConsoleLogger;
import de.pixelstudios.mcreloaded.MCReloaded;

public class FileManager {

	
	public static boolean createServerdir() {
		File pixelstudiosdir = new File(MCReloaded.serverpath + "/PixelStudios/");
		if(!pixelstudiosdir.exists()) {
			ConsoleLogger.debug(ConsoleLogger.DATA_MANAGER, "Creating PixelStudios directory in "+MCReloaded.serverpath);
			pixelstudiosdir.mkdirs();
		}
		File plugindir = new File(MCReloaded.serverpath + "/PixelStudios/MCReloaded/");
		if(!plugindir.exists()) {
			ConsoleLogger.debug(ConsoleLogger.DATA_MANAGER, "Creating MCReloaded directory in "+pixelstudiosdir.getPath());
			plugindir.mkdirs();
		}
		for(
			World w :Bukkit.getWorlds()) {
			File worlddir = new File(w.getWorldFolder()+"/data/mcdata");
			if(!worlddir.exists()) {
				ConsoleLogger.debug(ConsoleLogger.DATA_MANAGER, "Creating MCData directory in "+worlddir.getPath());
				worlddir.mkdirs();
			}
			File experience_obelisk = new File(w.getWorldFolder()+"/data/mcdata/experience_obelisk.yml");
				if(!experience_obelisk.exists()) {
					try {	
						experience_obelisk.createNewFile();
					}catch(IOException e) {
						e.printStackTrace();
					}
				}
			}
		File backupdir = new File(MCReloaded.serverpath + "/PixelStudios/MCReloaded/Backups/");
		if(!backupdir.exists()) {
			ConsoleLogger.debug(ConsoleLogger.DATA_MANAGER, "Creating Backup directory in "+backupdir.getPath());
			backupdir.mkdirs();
		}
		return false;
    }
}
