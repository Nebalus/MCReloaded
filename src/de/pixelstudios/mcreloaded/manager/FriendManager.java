package de.pixelstudios.mcreloaded.manager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.configuration.file.YamlConfiguration;

import de.pixelstudios.mcreloaded.MCReloaded;
import de.pixelstudios.mcreloaded.datamanagement.FileManager;

public class FriendManager {

	private HashMap<UUID,FriendProfile> friendprofiles = new HashMap<UUID,FriendProfile>();
	
	public FriendManager(MCReloaded plugin) {
		  
	}
	
	private UUID createNewFriendUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid;
	}
	
	public boolean loadFriendProfile(UUID uuid) {
		if(!friendprofiles.containsKey(uuid)) {
			FriendProfile fp = new FriendProfile(uuid);
			friendprofiles.put(uuid, fp);
			return true;
		}
		return false;
	}
	public boolean unloadFriendProfile(UUID uuid) {
		if(friendprofiles.containsKey(uuid)) {
			FriendProfile fp = friendprofiles.get(uuid);
			friendprofiles.remove(uuid, fp);
			return true;
		}
		return false;
	}
	public FriendProfile getFriendProfile(UUID uuid){
		if(friendprofiles.containsKey(uuid)) {
			return friendprofiles.get(uuid);
		}else {
			if(loadFriendProfile(uuid)) {
				return friendprofiles.get(uuid);
			}
		}
		return null;
	}
	public UUID createFriendProfile(UUID player1, UUID player2) {
		UUID uuid = createNewFriendUUID();
		File friendFile = new File(FileManager.serverPath + "/PixelStudios/MCReloaded/FriendSystem/FriendProfiles/"+uuid.toString().substring(0, 2)+"/"+uuid.toString()+".yml");
		if(!friendFile.exists()) {
			FileManager.createFriendsdir(uuid);
			YamlConfiguration yaml = YamlConfiguration.loadConfiguration(friendFile);		
			ArrayList<UUID> participantsList = new ArrayList<UUID>();
			participantsList.add(player1);
			participantsList.add(player2);
			yaml.set("ParticipantsList",participantsList);
			try {
				yaml.save(friendFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return uuid;
		}
		return null;
	}
}
