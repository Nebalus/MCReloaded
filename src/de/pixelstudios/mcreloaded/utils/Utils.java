package de.pixelstudios.mcreloaded.utils;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import de.pixelstudios.mcreloaded.MCReloaded;
import de.pixelstudios.mcreloaded.datamanagement.Cache;
import de.pixelstudios.mcreloaded.datamanagement.LiteSQL;
import de.pixelstudios.mcreloaded.items.WarpCrystal;
import de.pixelstudios.mcreloaded.items.manager.HeadList;
public class Utils {

	
	public static Boolean isReloading = false;
	public static Boolean isStoping = false;
	
	
	public static ItemStack getSkullByTextureURL(String textureURL, String Name, ArrayList<String> lore) {
		ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        try {
            ItemMeta headMeta = head.getItemMeta();
            headMeta.setDisplayName(Name);
            headMeta.setLore(lore);
            GameProfile gameProfile = new GameProfile(UUID.randomUUID(), null);
            gameProfile.getProperties().put("textures", new Property("textures", Base64Coder.encodeString("{textures:{SKIN:{url:\"" + textureURL + "\"}}}")));
            Field profileField = headMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(headMeta, gameProfile);
            head.setItemMeta(headMeta);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return head;
    }
	
	public static NamespacedKey getNamespacedKey(String key) {
		return new NamespacedKey(MCReloaded.getPlugin(), key);
	}
	 
	public static Boolean doesPlayerExists(String UUID) {		
		try {	
			PreparedStatement ps = LiteSQL.getConnection().prepareStatement("SELECT uuid FROM playerdata WHERE uuid = ?");
			ps.setString(1, UUID);
			ResultSet rs = ps.executeQuery();
			return rs.next();
		}catch (SQLException e) {
			e.printStackTrace();
		}		
		return false;
	} 
	 

		
		
	
	
	 public static boolean isRunningMinecraft(int major, int minor, int revision) {
	        String[] version = Bukkit.getServer().getBukkitVersion().split("-")[0].split("\\.");
	        int maj = Integer.parseInt(version[0]);
	        int min = Integer.parseInt(version[1]);
	        int rev;
	        try {
	            rev = Integer.parseInt(version[2]);
	        } catch (Exception ignore) {
	            rev = 0;
	        }
	        return maj > major || min > minor || (min == minor && rev >= revision);
	    }


	public static boolean isRunningSpigot() {
	        return classExists("org.spigotmc.CustomTimingsHandler");
	}	    
	public static boolean classExists(final String className) {
		try {
	    	Class.forName(className);
	    	return true;
		} catch (ClassNotFoundException ex) {
	    	return false;
		}
	}
	public static void checkWarpCrystal() {
			
			//Fehler muss noch behoben werden:
			//list.add(all); soll für jeder location ausgeführt werden und nicht einmal für all 
			//empfehle eine hashmap
			
			
				List<Player> list = new ArrayList<Player>();
				List<World> worlds = new ArrayList<World>();
				for(Player all : Bukkit.getOnlinePlayers()) {
					if(!worlds.contains(all.getWorld())) {
						worlds.add(all.getWorld());
					}
					for(WarpCrystal eo : Cache.warp_crystal) {
						Location loc = eo.getLocation();
							if(loc.getWorld().equals(all.getWorld())) {
								if (all.getLocation().distance(loc) <= 20 ) {
									list.add(all);
									if(!loc.getBlock().getType().equals(Material.BARRIER)) {
										Bukkit.getScheduler().runTask(MCReloaded.getPlugin(),new Runnable() {
											@SuppressWarnings("deprecation")
											@Override
											public void run() {
												loc.getBlock().breakNaturally();
												loc.getBlock().setType(Material.BARRIER);
										
												
												Location eloc = new Location(loc.getWorld(), loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
												eloc.setY(eloc.getY() - 0.645);
												eloc.setX(eloc.getX() + 0.5);
												eloc.setZ(eloc.getZ() + 0.5);
												
												ArmorStand armorstand = (ArmorStand) eloc.getWorld().spawnEntity(eloc, EntityType.ARMOR_STAND);
												armorstand.setVisible(false);
												armorstand.setHelmet(HeadList.WARP_CRYSTAL);
												armorstand.setSilent(true);
												armorstand.setSmall(true);
												armorstand.setBasePlate(false);
												armorstand.setGravity(false);
												armorstand.setMarker(true);
												armorstand.setInvulnerable(true);
												armorstand.setCollidable(false);
												armorstand.setCustomName("byjfbvxyBJIUG892psx-ssfsdjkbfhijkgcvuzg127263489263461924528648");
											}
										});
									}
								}
							}
						}
					for(World w : worlds) {
						for(ArmorStand as : w.getEntitiesByClass(ArmorStand.class)) {		
							if(!as.hasGravity() && !as.isCollidable()) {
									if(as.getCustomName().equals("byjfbvxyBJIUG892psx-ssfsdjkbfhijkgcvuzg127263489263461924528648")) {
										Location locas = as.getEyeLocation();
										locas.setY(locas.getBlockY()+1);
										locas.setX(locas.getBlockX());
										locas.setZ(locas.getBlockZ());
										for(WarpCrystal eo : Cache.warp_crystal) {
											Location loc = eo.getLocation();
											if(loc.getBlock().getLocation().equals(locas.getBlock().getLocation())) {
												if(list.size() == 0) {
													as.remove();
													if(loc.getBlock().getType().equals(Material.BARRIER)){
														Bukkit.getScheduler().runTask(MCReloaded.getPlugin(),new Runnable() {
															@Override
															public void run() {
																loc.getBlock().setType(Material.AIR);
														}
													});
												}
											}
										
									}
								}
							}
						}
					}
				}
			}
		}
}
