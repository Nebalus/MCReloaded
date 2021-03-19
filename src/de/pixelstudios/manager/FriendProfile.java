package de.pixelstudios.manager;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;

import de.pixelstudios.datamanagement.FileManager;

public class FriendProfile {
	private UUID uuid;
	private String uuidshort;
	private File friendProfileFile;
	
	private ArrayList<OfflinePlayer> friends = new ArrayList<OfflinePlayer>();
	
	public FriendProfile(UUID uuid) {
		this.uuid = uuid;
		this.uuidshort = uuid.toString().substring(0, 2);
		this.friendProfileFile = new File(FileManager.serverPath + "/PixelStudios/MCReloaded/FriendSystem/FriendProfiles/"+uuidshort+"/"+uuid.toString()+".yml");
		loadProfile();
	}
	@SuppressWarnings("deprecation")
	private boolean loadProfile() {
		if(friendProfileFile.exists()) {
			YamlConfiguration yaml = YamlConfiguration.loadConfiguration(friendProfileFile);		
			for(String allop : yaml.getStringList("ParticipantsList")) {
				OfflinePlayer op = Bukkit.getOfflinePlayer(allop);
				friends.add(op);
			}
		}
		return false;
	}
	public OfflinePlayer getOtherParticipant(OfflinePlayer op) {
		ArrayList<OfflinePlayer> otherplayer = friends;
		otherplayer.remove(op);
		return otherplayer.get(0);
	}
}
