package de.pixelstudios.mcreloaded.listener.player;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.bukkit.FluidCollisionMode;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Levelled;
import org.bukkit.block.data.Waterlogged;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Cauldron;
import org.bukkit.persistence.PersistentDataType;

import de.pixelstudios.mcreloaded.MCReloaded;
import de.pixelstudios.mcreloaded.datamanagement.Cache;
import de.pixelstudios.mcreloaded.guis.ExperienceObeliskGUI;
import de.pixelstudios.mcreloaded.items.ExperienceObelisk;
import de.pixelstudios.mcreloaded.items.manager.HeadList;
import de.pixelstudios.mcreloaded.manager.ItemManager;
import de.pixelstudios.mcreloaded.manager.user.UserProfile;
import de.pixelstudios.mcreloaded.utils.Achievements;
import de.pixelstudios.mcreloaded.utils.Utils;
import io.pixelstudios.libary.InventoryLibary;

@SuppressWarnings("deprecation")
public class PlayerInteract implements Listener{

	
	@EventHandler(
		      priority = EventPriority.HIGHEST
		   )
	public void handleBlockblocker(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		UserProfile up = MCReloaded.getPlugin().getPlayerManager().getProfile(p);
		ItemStack item = e.getItem();
		Block block = p.getTargetBlockExact(5, FluidCollisionMode.ALWAYS);
		if(item != null) {
			switch(item.getType()) {
			case GLASS_BOTTLE:
				if(e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) {
					if (block == null) return;
					if (isWaterBlock(block)) {
						e.setCancelled(true);
						if (p.getGameMode() == GameMode.SURVIVAL || p.getGameMode() == GameMode.ADVENTURE) item.setAmount(item.getAmount() - 1);
						InventoryLibary.addItemToInventory(p, ItemManager.DIRTY_WATER);
					}else {
						if(block.getType().equals(Material.CAULDRON)) {
							Levelled cauldron = (Levelled) (block.getBlockData());
							if(cauldron.getLevel() > 0) {
								e.setCancelled(true);
								Block fire = block.getRelative(BlockFace.DOWN);							
								block.getState().setData(new Cauldron());
								cauldron.setLevel(cauldron.getLevel() - 1);
								block.setBlockData(cauldron);						
								ItemStack waterBottle = ItemManager.DIRTY_WATER;		
								if (fire.getType() == Material.FIRE || fire.getType() == Material.SOUL_FIRE) {
									up.giveAchievement(Achievements.PURIFIED);
									waterBottle = ItemManager.PURIFIED_WATER;
								}
								p.playSound(block.getLocation(), Sound.ITEM_BOTTLE_FILL, 1, 1);
								if (p.getGameMode() == GameMode.SURVIVAL || p.getGameMode() == GameMode.ADVENTURE) item.setAmount(item.getAmount() - 1);
								InventoryLibary.addItemToInventory(p, waterBottle);
							}
						}
					}
				}
				break;
			case BOWL:
				if(e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) {
					if (block == null) return;
					if (isWaterBlock(block)) {
						e.setCancelled(true);
						if (p.getGameMode() == GameMode.SURVIVAL || p.getGameMode() == GameMode.ADVENTURE) item.setAmount(item.getAmount() - 1);
						InventoryLibary.addItemToInventory(p, ItemManager.WATER_BOWL);
					}
				}
				break;
			case LEATHER_BOOTS:
			case LEATHER_LEGGINGS:
			case LEATHER_CHESTPLATE:
			case LEATHER_HELMET:
				if(block == null) return;
				if(block.getType().equals(Material.CAULDRON)) {
					if(item.getItemMeta().getPersistentDataContainer().has(ItemManager.cristal_armor_Key, PersistentDataType.BYTE))
						e.setCancelled(true);		
				}
				break;
			default:
				break;
			}
			
			if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
				if(item.getItemMeta().getPersistentDataContainer().has(ItemManager.portable_crafting_table_Key, PersistentDataType.BYTE)) {
					e.setCancelled(true);
					p.openWorkbench(p.getLocation(), true);
				}else if(item.getItemMeta().getPersistentDataContainer().has(ItemManager.portable_enderchest_Key, PersistentDataType.BYTE)) {
					e.setCancelled(true);
					p.openInventory(p.getEnderChest());
					p.playSound(p.getEyeLocation(), Sound.BLOCK_ENDER_CHEST_OPEN, 1, 1);
				}
			}
			
			if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
				if(ItemManager.Tags.PLACEABLE_BLOCKED.isTagged(item)) {
					e.setCancelled(true);
				}
				if(item.getItemMeta().getPersistentDataContainer().has(ItemManager.experience_obelisk_Key, PersistentDataType.BYTE)) {
					spawnExperienceObelisk(block.getLocation(), item, p.getGameMode());
				}
			}
		}
		
		
		
		
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(block == null) return;
			if(block.getType().equals(Material.BARRIER)) {
				for(ExperienceObelisk eo : Cache.experience_obelisk) {
					if(eo.getLocation().equals(block.getLocation())) {
						ExperienceObeliskGUI eogui = new ExperienceObeliskGUI(eo,p);
						eogui.loadGUI();
						return;
					}
				}
			}
		}
		
		if(e.getAction() == Action.LEFT_CLICK_BLOCK) {
			if(block == null) return;
			Utils.lastblockface.put(p,e.getBlockFace());
		}
		
	}
	private boolean isWaterBlock(Block block) {
	    if (block.getType() == Material.WATER) {
	        return true;
        }
        BlockData data = block.getBlockData();
        return data instanceof Waterlogged && ((Waterlogged) data).isWaterlogged();
    }

	private boolean spawnExperienceObelisk(Location loc, ItemStack itemStack, GameMode gameMode) {
			Location Spawnloc = loc;
			Spawnloc.setY(loc.getY() + 0.355);
			Spawnloc.setX(loc.getX() + 0.5);
			Spawnloc.setZ(loc.getZ() + 0.5);
			

			loc.setY(loc.getY()+1);
			if(loc.getBlock().getType().equals(Material.AIR)) {
				loc.getBlock().breakNaturally();
				loc.getBlock().setType(Material.BARRIER);
				
				ArmorStand armorstand = (ArmorStand) loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
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
				
				
				File experience_obelisk = new File(loc.getWorld().getWorldFolder()+"/data/mcdata/experience_obelisk.yml");
				YamlConfiguration experience_obeliskyml = YamlConfiguration.loadConfiguration(experience_obelisk);			
				List<String> uuids = (List<String>) experience_obeliskyml.getStringList("UUID");
				UUID random = UUID.randomUUID();
				uuids.add(random+"");
				experience_obeliskyml.set("UUID",uuids);
				experience_obeliskyml.set(random+".X", loc.getBlockX());
				experience_obeliskyml.set(random+".Y", loc.getBlockY());
				experience_obeliskyml.set(random+".Z", loc.getBlockZ());
				experience_obeliskyml.set(random+".XP", 0);
				
				Integer X = loc.getBlockX();
				Integer Y = loc.getBlockY()+1;
				Integer Z = loc.getBlockZ();
				Location sloc = new Location(loc.getWorld(), X, Y, Z);
				Integer level = 1;
				if(itemStack.getItemMeta().getPersistentDataContainer().has(ItemManager.experience_obelisk_II_Key, PersistentDataType.BYTE)) {
					level = 2;
				}
				if(itemStack.getItemMeta().getPersistentDataContainer().has(ItemManager.experience_obelisk_III_Key, PersistentDataType.BYTE)) {
					level = 3;
				}
				if(itemStack.getItemMeta().getPersistentDataContainer().has(ItemManager.experience_obelisk_IV_Key, PersistentDataType.BYTE)) {
					level = 4;
				}
				experience_obeliskyml.set(random+".Level", level);
				ExperienceObelisk experienceobelisk = new ExperienceObelisk(level, sloc, 0);
				Cache.experience_obelisk.add(experienceobelisk);
				try {
					experience_obeliskyml.save(experience_obelisk);
				} catch (IOException e) {
					e.printStackTrace();
				}
				if(!gameMode.equals(GameMode.CREATIVE)) {
					itemStack.setAmount(itemStack.getAmount()-1);
				}
			}
		return false;
		
	}
}
