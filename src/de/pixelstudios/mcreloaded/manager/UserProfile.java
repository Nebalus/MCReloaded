package de.pixelstudios.mcreloaded.manager;

import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Sound;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import de.pixelstudios.mcreloaded.MCReloaded;
import de.pixelstudios.mcreloaded.datamanagement.Config;
import de.pixelstudios.mcreloaded.datamanagement.FileManager;
import de.pixelstudios.mcreloaded.datamanagement.LiteSQL;
import de.pixelstudios.mcreloaded.utils.Achievements;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class UserProfile {
	private Player player;
	private Config config;
	private UUID uuid;
	private String uuidshort;
	private File playerdataFile;
	private String filePath;

	private ArrayList<Achievements> achievements = new ArrayList<Achievements>();
	private HashMap<Achievements,Long> achievementclaimedtime = new HashMap<Achievements,Long>();
	
	private double thirst;
	private double energy;
	private boolean hasResourcepackAccepted;
	private boolean canFly;
	
	private boolean isAfk;
	private Long isAfkIn;
	public final Long timeUntilAfk = 1000l*5l;
	
	private long lastTimeOnline;
	private boolean isLoading = true;
	
	private final double max_thirst = 40.0;
	private final double max_energy = 20.0;
	
	
	public UserProfile(Player p) {
		this.player = p;
		this.uuid = p.getUniqueId();
		this.uuidshort = uuid.toString().substring(0, 2);
		this.filePath = FileManager.serverPath + "/PixelStudios/MCReloaded/PlayerData/"+uuidshort+"/"+uuid.toString()+"/";
		this.playerdataFile = new File(filePath+"playerdata.yml");
		this.config = MCReloaded.getPlugin().getMCReloadedConfig();
		loadProfile();
	}
	
	private boolean loadProfile() {
	
		FileManager.createPlayerdir(player);
		player.setPlayerListName("Loading...");
		if(playerdataFile.exists()) {
			boolean update = false;
			YamlConfiguration yaml = YamlConfiguration.loadConfiguration(playerdataFile);		
			//********************************************************************
					if(!yaml.contains("thirst")) {
						yaml.set("thirst", max_thirst);
						thirst = max_thirst;
						update = true;
					}else {
						thirst = yaml.getDouble("thirst");
					}
					//********************************************************************
					if(!yaml.contains("energy")) {
						yaml.set("energy", config.MECHANICS_ENERGY_RESPAWN_LEVEL);
						energy = config.MECHANICS_ENERGY_RESPAWN_LEVEL;
						update = true;
					}else {
						energy = yaml.getDouble("energy");
					}
					//********************************************************************
					if(!yaml.contains("canFly")) {
						yaml.set("canFly", false);
						canFly = false;
						update = true;
					}else {
						canFly = yaml.getBoolean("canFly");
						if(!player.getAllowFlight() && canFly && player.isOnline()) {
							if(player.hasPermission("mcreloaded.command.fly")) {
								player.sendMessage(MCReloaded.getPlugin().getMessageFormatter().format(false, "commands.fly.enabled"));
								player.setAllowFlight(true);
								player.setFlying(true);
							}
						}
					}
					//********************************************************************
					if(!yaml.contains("lastTimeOnline")) {
						Long time = System.currentTimeMillis();
						yaml.set("lastTimeOnline", time);
						lastTimeOnline = time;
						update = true;
					}else {
						lastTimeOnline = yaml.getLong("lastTimeOnline");
					}
					//********************************************************************
					if(!yaml.contains("Achievements.list")) {
						ArrayList<Achievements> dummyList = new ArrayList<Achievements>();
						yaml.set("Achievements.list", dummyList);
						achievements = dummyList;
						update = true;
					}else {
						for(String currentAchievements : yaml.getStringList("Achievements.list")){
							try {
								achievements.add(Achievements.valueOf(currentAchievements));
								achievementclaimedtime.put(Achievements.valueOf(currentAchievements), yaml.getLong("Achievements."+currentAchievements+".claimedtime"));
							}catch(IllegalArgumentException e) {
								e.printStackTrace();
							}
						}	
					}
					if(update) {
						saveYaml(yaml, playerdataFile);
					}
				}
				isAfkIn = System.currentTimeMillis()+timeUntilAfk;
				player.setPlayerListName(player.getName());
				isLoading = false;
			
		
		return false;
	}
	
	
	@SuppressWarnings("deprecation")
	public boolean giveAchievement(Achievements achievement) {
		if(!hasAchievement(achievement)) {
			YamlConfiguration yaml = YamlConfiguration.loadConfiguration(playerdataFile);
			List<String> alist = yaml.getStringList("Achievements.list");
			if(!alist.contains(achievement.getSystemName())) {
				alist.add(achievement.getSystemName());
				yaml.set("Achievements.list", alist);
				yaml.set("Achievements."+achievement.getSystemName()+".claimedtime", System.currentTimeMillis());
				achievements.add(achievement);
				try {
					ResultSet rs = LiteSQL.onQuery("SELECT claims FROM achievements WHERE achievementname = '" + achievement.getSystemName()+"'");	
					if(rs.next()) {
						PreparedStatement ps = LiteSQL.getConnection().prepareStatement("UPDATE `achievements` SET `claims` = ? WHERE `achievementname` = ?");
						ps.setInt(1, rs.getInt("claims")+1);
						ps.setString(2, achievement.getSystemName());
						ps.executeUpdate();
						}else {
							PreparedStatement ps = LiteSQL.getConnection().prepareStatement("INSERT INTO achievements (claims,achievementname) VALUES (?,?)");
							ps.setInt(1, 1);
							ps.setString(2, achievement.getSystemName());
							ps.executeUpdate();
						}		
					} catch (SQLException ex) {
					ex.printStackTrace();
				}
				TextComponent tc_achievement = new TextComponent("§a§k0§a>> Achievment Unlocked: §6"+achievement.getName()+" §a<<§k0");
				//tc_achievement.setClickEvent(new ClickEvent(net.md_5.bungee.api.chat.ClickEvent.Action.SUGGEST_COMMAND, "/profile loadgui/achievements/" ));
				tc_achievement.setHoverEvent(new HoverEvent(net.md_5.bungee.api.chat.HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§a"+achievement.getText()).create()));	
				player.spigot().sendMessage(tc_achievement);
				player.playSound(player.getEyeLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
				}
			
			if(saveYaml(yaml, playerdataFile)) {
				return true;
			}
		}
		
		return false;
	}
	public boolean setLastTimeOnline(Long lasttimeOnline) {
		if(lasttimeOnline != lastTimeOnline) {	
			YamlConfiguration yaml = YamlConfiguration.loadConfiguration(playerdataFile);
			lastTimeOnline = lasttimeOnline;
			yaml.set("lastTimeOnline", lasttimeOnline);
			if(saveYaml(yaml, playerdataFile)) {
				return true;
			}
		}
		return false;
	}
	public boolean setThirst(double setthirst) {
		if(setthirst != thirst) {	
			boolean update = false;
			YamlConfiguration yaml = YamlConfiguration.loadConfiguration(playerdataFile);
			if(setthirst <= max_thirst && setthirst > 0) {
				thirst = setthirst;
				yaml.set("thirst", thirst);
				update = true;
			}else {
				thirst = max_thirst;
				yaml.set("thirst", setthirst);
				update = true;
			}
			if(setthirst < 0) {
				thirst = 0;
				yaml.set("thirst", setthirst);
				update = true;
			}
			if(update && saveYaml(yaml, playerdataFile)) {
				return true;
			}
		}
		return false;
	}
	public boolean setEnergy(double setenergy) {
		if(setenergy != energy) {	
			boolean update = false;
			YamlConfiguration yaml = YamlConfiguration.loadConfiguration(playerdataFile);
			if(setenergy <= max_energy && setenergy >= 0) {
				energy = setenergy;
				yaml.set("energy", energy);
				update = true;
			}else {
				energy = max_energy;
				yaml.set("energy", setenergy);
				update = true;
			}
			if(setenergy < 0) {
				energy = 0;
				yaml.set("energy", setenergy);
				update = true;
			}
			if(update && saveYaml(yaml, playerdataFile)) {
				return true;
			}
		}
		return false;
	}	
	public boolean setFly(Boolean setFly) {
		if(setFly != canFly) {	
			YamlConfiguration yaml = YamlConfiguration.loadConfiguration(playerdataFile);
			canFly = setFly;
			yaml.set("canFly", setFly);
			if(saveYaml(yaml, playerdataFile)) {
				return true;
			}
		}
		return false;
	}
	public void setAfk(Long timeUntilAfk) {
		isAfkIn = timeUntilAfk;
	}
	public void setResourcepackAccepted(boolean trueorfalse) {
		hasResourcepackAccepted = trueorfalse;
	}
	
	private boolean saveYaml(YamlConfiguration yaml, File file) {
		try {
			yaml.save(file);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean hasAchievement(Achievements achievement) {		
		return achievements.contains(achievement);
	}
	public boolean hasResourcepackAccepted() {
		return hasResourcepackAccepted;
	}
	public boolean getFly() {
		return canFly;
	}
	public boolean isAfk() {
		if(!isAfk && System.currentTimeMillis() > isAfkIn) {
			isAfk = true;
			return isAfk;
		}
		return false;
	}
	public double getThirst() {
		return thirst;
	}
	public double getEnergy() {
		return energy;
	}
	public double getMaxEnergy() {
		return max_energy;
	}
	public double getMaxThirst() {
		return max_thirst;
	}
	public Long getLastTimeOnline() {
		return lastTimeOnline;
	}
	public boolean isLoading() {
		return isLoading;
	}
}
