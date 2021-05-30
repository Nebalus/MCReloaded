package de.pixelstudios.mcreloaded.friendsystem;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import de.pixelstudios.mcreloaded.datamanagement.FileManager;

public class FriendAPI {

	
	 public static Boolean isFriend(String UUID, Player p) {		
		File PlayerFriendsFile = new File(FileManager.serverPath + "/PixelStudios/MCReloaded/PlayerData/"+p.getUniqueId()+"/Friends/Friends.yml");
		YamlConfiguration PlayerFriendsFileyml = YamlConfiguration.loadConfiguration(PlayerFriendsFile);			
		List<String> friends = (List<String>) PlayerFriendsFileyml.getStringList("UUID");
		if(friends.contains(UUID)) {
			return true;
		}			
		return false;
	} 
	 public static List<String> getFriends(Player p){
		File PlayerFriendsFile = new File(FileManager.serverPath + "/PixelStudios/MCReloaded/PlayerData/"+p.getUniqueId()+"/Friends/Friends.yml");
		YamlConfiguration PlayerFriendsFileyml = YamlConfiguration.loadConfiguration(PlayerFriendsFile);			
		List<String> friends = (List<String>) PlayerFriendsFileyml.getStringList("UUID");
		return friends;
	 }
	 
	 public static List<String> getRequests(Player p){
		File PlayerFriendsFile = new File(FileManager.serverPath + "/PixelStudios/MCReloaded/PlayerData/"+p.getUniqueId()+"/Friends/Friends.yml");
		YamlConfiguration PlayerFriendsFileyml = YamlConfiguration.loadConfiguration(PlayerFriendsFile);			
		List<String> friendrequest = (List<String>) PlayerFriendsFileyml.getStringList("UUID");
		return friendrequest;
	 }
	 public static Boolean sendRequest(String UUID, Player p) {		
		File PlayerFriendsFile = new File(FileManager.serverPath + "/PixelStudios/MCReloaded/PlayerData/"+UUID+"/Friends/Friends.yml");
		YamlConfiguration PlayerFriendsFileyml = YamlConfiguration.loadConfiguration(PlayerFriendsFile);			
		List<String> friendrequest = (List<String>) PlayerFriendsFileyml.getStringList("FriendRequest.UUID");
		friendrequest.add(p.getUniqueId()+"");
		PlayerFriendsFileyml.set("FriendRequest.UUID", friendrequest);
		try {	
			PlayerFriendsFileyml.save(PlayerFriendsFile);
			return true;
		}catch(IOException e) {
			e.printStackTrace();
		}
		return false;
	} 
	 
	 public static Boolean isRequested(String UUID, Player p) {		
		File PlayerFriendsFile = new File(FileManager.serverPath + "/PixelStudios/MCReloaded/PlayerData/"+p.getUniqueId()+"/Friends/Friends.yml");
		YamlConfiguration PlayerFriendsFileyml = YamlConfiguration.loadConfiguration(PlayerFriendsFile);			
		List<String> friendrequest = (List<String>) PlayerFriendsFileyml.getStringList("FriendRequest.UUID");
		if(friendrequest.contains(UUID)) {
			return true;
		}			
		return false;
	} 
	public static Boolean acceptRequest(String UUID, Player p) {		
		File PlayerFriendsFile = new File(FileManager.serverPath + "/PixelStudios/MCReloaded/PlayerData/"+p.getUniqueId()+"/Friends/Friends.yml");
		YamlConfiguration PlayerFriendsFileyml = YamlConfiguration.loadConfiguration(PlayerFriendsFile);			
		List<String> friendrequest = (List<String>) PlayerFriendsFileyml.getStringList("FriendRequest.UUID");
		friendrequest.remove(UUID);
		PlayerFriendsFileyml.set("FriendRequest.UUID", friendrequest);
		
		List<String> friends = (List<String>) PlayerFriendsFileyml.getStringList("Friends.UUID");
		friends.add(UUID);
		PlayerFriendsFileyml.set("Friends.UUID", friends);
		try {	
			PlayerFriendsFileyml.save(PlayerFriendsFile);
			return true;
		}catch(IOException e) {
			e.printStackTrace();
		}
		return false;
	} 
}
