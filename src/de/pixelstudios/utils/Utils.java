package de.pixelstudios.utils;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import de.pixelstudios.MCReloaded;
import de.pixelstudios.datamanagement.Cache;
import de.pixelstudios.datamanagement.LiteSQL;
import de.pixelstudios.items.ExperienceObelisk;
import de.pixelstudios.items.manager.HeadList;
import net.minecraft.server.v1_16_R3.EntityPlayer;
public class Utils {
	
	public static HashMap<Player, BlockFace> lastblockface = new HashMap<Player, BlockFace>();
	
	public static Boolean isReloading = false;
	public static Boolean isStoping = false;
	
	 private static Enchantment enchant[] = {
	    		Enchantment.SILK_TOUCH,
	    		Enchantment.DURABILITY,
	    		Enchantment.DIG_SPEED,
	    		Enchantment.LOOT_BONUS_BLOCKS,
	    		Enchantment.DAMAGE_ALL,
	    		Enchantment.KNOCKBACK,
	    		Enchantment.FIRE_ASPECT,
	    		Enchantment.DAMAGE_ARTHROPODS,
	    		Enchantment.DAMAGE_UNDEAD,
	    		Enchantment.LOOT_BONUS_MOBS,
	    		Enchantment.ARROW_DAMAGE,
	    		Enchantment.ARROW_FIRE,
	    	    Enchantment.ARROW_INFINITE,
	    	    Enchantment.ARROW_KNOCKBACK,
	    	    Enchantment.PROTECTION_ENVIRONMENTAL,
	    	    Enchantment.PROTECTION_EXPLOSIONS,
	    	    Enchantment.PROTECTION_FALL,
	    	    Enchantment.PROTECTION_FIRE,
	    	    Enchantment.PROTECTION_PROJECTILE,
	    	    Enchantment.LURE,
	    	    Enchantment.LUCK,
	    	    Enchantment.OXYGEN,
	    	    Enchantment.THORNS,
	    	    Enchantment.WATER_WORKER };
	 
	 
	
	
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
	 
	public static String getTimeString(Long from, Long to) {
			
		Long rawData = from - to;
		long sekunden = rawData/1000;
		long minuten = sekunden/60;
		long stunden = minuten/60;
		long tage = stunden/24;
		sekunden %= 60;
		minuten %= 60;
		stunden %= 24;
					
		if(tage >= 1) {		
			if(stunden == 0) {
				return "§b"+tage+" days";
			}else {
				return "§b"+tage+" days, "+stunden+" hours";
			}
			}else if(stunden >= 1){ 
				if(minuten == 0) {
					return "§b"+stunden+" hours";
				}else {
					return "§b"+stunden+" hours, "+stunden+" minutes";			
				}	
			}else if(minuten >= 1){
				if(sekunden == 0) {
					return "§b"+minuten+" minutes";
				}else {
					return "§b"+minuten+" minutes, "+sekunden+" seconds";			
				}	
			}else if(sekunden >= 1){
				if(sekunden == 0) {
					return "§b"+1+"seconds";
				}else {
					return "§b"+sekunden+" seconds";			
				}				
			}
			
			return "§bHmm something went wrong ._.";	
		}
		
		public static int getPing(Player p) {
			CraftPlayer pingc = (CraftPlayer) p;
			EntityPlayer pinge = pingc.getHandle();
			return pinge.ping;
		}
		
		
	public static ItemStack removeEnchants(ItemStack item) {
		ItemMeta itemmeta = item.getItemMeta();
		for(Enchantment en: enchant){
	       if(itemmeta.hasEnchants()){
	    	   itemmeta.removeEnchant(en);
	           item.setItemMeta(itemmeta);
	           }
	       }
		return item;
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
	    public static void checkExperienceObelisk() {
			
			//Fehler muss noch behoben werden:
			//list.add(all); soll für jeder location ausgeführt werden und nicht einmal für all 
			//empfehle eine hashmap
			
			
				List<Player> list = new ArrayList<Player>();
				List<World> worlds = new ArrayList<World>();
				for(Player all : Bukkit.getOnlinePlayers()) {
					if(!worlds.contains(all.getWorld())) {
						worlds.add(all.getWorld());
					}
					for(ExperienceObelisk eo : Cache.experience_obelisk) {
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
												armorstand.setHelmet(HeadList.EXPERIENCE_BOTTLE);
												armorstand.setSilent(true);
												armorstand.setSmall(true);
												armorstand.setBasePlate(false);
												armorstand.setGravity(false);
												armorstand.setMarker(true);
												armorstand.setInvulnerable(true);
												armorstand.setCollidable(false);
												armorstand.setCustomName("byjfbvxyBJIUG892psx-sujxcbPrujsnxiseIJKskls93as2542473697997979792210");
											}
										});
									}
								}
							}
						}
					for(World w : worlds) {
						for(ArmorStand as : w.getEntitiesByClass(ArmorStand.class)) {		
							if(!as.hasGravity()) {
								if(!as.isCollidable()) {
									if(as.getCustomName().equals("byjfbvxyBJIUG892psx-sujxcbPrujsnxiseIJKskls93as2542473697997979792210")) {
										Location locas = as.getEyeLocation();
										locas.setY(locas.getBlockY()+1);
										locas.setX(locas.getBlockX());
										locas.setZ(locas.getBlockZ());
										for(ExperienceObelisk eo : Cache.experience_obelisk) {
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
}
