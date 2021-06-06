package de.pixelstudios.mcreloaded.datamanagement;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

import de.pixelstudios.mcreloaded.ConsoleLogger;
import de.pixelstudios.mcreloaded.MCReloaded;

public class FileManager {
	
	public static String serverPath = MCReloaded.getPlugin().getDataFolder().getAbsolutePath()
			.substring(0, MCReloaded.getPlugin().getDataFolder().getAbsolutePath().length() - (9 + MCReloaded.getPlugin().getName().length()));
	
	public static void createPlayerdir(Player player) {
		String uuid = player.getUniqueId().toString();
		String uuidshort = uuid.substring(0, 2);
		
		File playerdatadir = new File(serverPath + "/PixelStudios/MCReloaded/PlayerData/");
		if(!playerdatadir.exists()) {
			ConsoleLogger.debug(ConsoleLogger.DATA_MANAGER, "Creating PlayerData directory in "+serverPath+"/PixelStudios/MCReloaded/PlayerData/");
			playerdatadir.mkdirs();
		}
		File uuidshortdir = new File(serverPath + "/PixelStudios/MCReloaded/PlayerData/"+uuidshort+"/");
		if(!uuidshortdir.exists()) {
			uuidshortdir.mkdirs();
		}
		File playerdir = new File(serverPath + "/PixelStudios/MCReloaded/PlayerData/"+uuidshort+"/"+uuid+"/");
		if(!playerdir.exists()) {
			ConsoleLogger.debug(ConsoleLogger.DATA_MANAGER, "Creating Player-Files for "+player.getName());
			playerdir.mkdirs();
		}
		File playerdatafile = new File(serverPath + "/PixelStudios/MCReloaded/PlayerData/"+uuidshort+"/"+uuid+"/playerdata.yml");
		if(!playerdatafile.exists()) {
			try {	
				playerdatafile.createNewFile();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		File playerfriendsdir = new File(serverPath + "/PixelStudios/MCReloaded/PlayerData/"+uuidshort+"/"+uuid+"/Friends/");
		if(!playerfriendsdir.exists()) {
			playerfriendsdir.mkdirs();
		}
		File playerfriendsfile = new File(serverPath + "/PixelStudios/MCReloaded/PlayerData/"+uuidshort+"/"+uuid+"/Friends/Friends.yml");
		if(playerfriendsdir.exists()) {
			if(!playerfriendsfile.exists()) {
				try {	
					playerfriendsfile.createNewFile();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public static boolean createServerdir() {
		File pixelstudiosdir = new File(serverPath + "/PixelStudios/");
		if(!pixelstudiosdir.exists()) {
			ConsoleLogger.debug(ConsoleLogger.DATA_MANAGER, "Creating PixelStudios directory in "+serverPath);
			pixelstudiosdir.mkdirs();
		}
		File plugindir = new File(serverPath + "/PixelStudios/MCReloaded/");
		if(!plugindir.exists()) {
			ConsoleLogger.debug(ConsoleLogger.DATA_MANAGER, "Creating MCReloaded directory in "+pixelstudiosdir.getPath());
			plugindir.mkdirs();
		}
		File friendssystemdir = new File(serverPath + "/PixelStudios/MCReloaded/FriendSystem/");
		if(!friendssystemdir.exists()) {
			ConsoleLogger.debug(ConsoleLogger.DATA_MANAGER, "Creating FriendSystem directory in "+plugindir.getPath());
			friendssystemdir.mkdirs();
		}
		File friendsprofiledir = new File(serverPath + "/PixelStudios/MCReloaded/FriendSystem/FriendProfiles/");
		if(!friendsprofiledir.exists()) {
			friendsprofiledir.mkdirs();
		}
		for(World w :Bukkit.getWorlds()) {
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
		File backupdir = new File(serverPath + "/PixelStudios/MCReloaded/Backups/");
		if(!backupdir.exists()) {
			ConsoleLogger.debug(ConsoleLogger.DATA_MANAGER, "Creating Backup directory in "+backupdir.getPath());
			backupdir.mkdirs();
		}
		return false;
    }
}
