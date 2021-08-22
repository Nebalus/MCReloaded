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
import de.pixelstudios.mcreloaded.guis.WarpCrystalGUI;
import de.pixelstudios.mcreloaded.items.WarpCrystal;
import de.pixelstudios.mcreloaded.items.manager.HeadList;
import de.pixelstudios.mcreloaded.manager.ItemManager;
import de.pixelstudios.mcreloaded.manager.UserProfile;
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
						if(block.getType().equals(Material.WATER_CAULDRON)) {
							Levelled cauldron = (Levelled) (block.getBlockData());
							e.setCancelled(true);
							Block fire = block.getRelative(BlockFace.DOWN);		
							if(cauldron.getLevel() > 1) {
								block.getState().setData(new Cauldron());
								cauldron.setLevel(cauldron.getLevel() - 1);
								block.setBlockData(cauldron);	
							}else {
								block.setType(Material.CAULDRON);
							}
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
				break;
			case BOWL:
				if(e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) {
					 
					if (isWaterBlock(block)) {
						e.setCancelled(true);
						if (p.getGameMode() == GameMode.SURVIVAL || p.getGameMode() == GameMode.ADVENTURE) item.setAmount(item.getAmount() - 1);
						InventoryLibary.addItemToInventory(p, ItemManager.WATER_BOWL);
					}
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
				if(item.getItemMeta().getPersistentDataContainer().has(ItemManager.warp_crystal_key, PersistentDataType.BYTE)) {
					spawnWarpCrystal(block.getLocation(), item, p);
					if(!p.getGameMode().equals(GameMode.CREATIVE)) {
						item.setAmount(item.getAmount()-1);
					}
				}
			}
		}
		
		
		
		
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(block == null) return;
			if(block.getType().equals(Material.BARRIER)) {
				for(WarpCrystal wc : Cache.warp_crystal) {
					if(wc.getLocation().equals(block.getLocation())) {
						if(p.getUniqueId().equals(wc.getOwnerUUID())) {
							WarpCrystalGUI wcgui = new WarpCrystalGUI(wc,p);
							wcgui.loadGUI();
							Cache.warp_crystal_gui_session.put(p, wcgui);
							return;
						}else {
							if(wc.getVisibility() == 2) {
								WarpCrystalGUI wcgui = new WarpCrystalGUI(wc,p);
								wcgui.loadGUI();
								Cache.warp_crystal_gui_session.put(p, wcgui);
								return;
							}
							p.sendMessage("§cYou do not have permission to access this crystal!!!");
						}
						//}else {
							//p.sendMessage("§cOnly one person can see the GUI from a warp crystal!!!");
						//}
					}
				}
			}
		}
		
		if(e.getAction() == Action.LEFT_CLICK_BLOCK) {
			if(block == null) return;
			up.setLastBlockFace(e.getBlockFace());
		}
		
	}
	private boolean isWaterBlock(Block block) {
	    if (block.getType() == Material.WATER) {
	        return true;
        }
        BlockData data = block.getBlockData();
        return data instanceof Waterlogged && ((Waterlogged) data).isWaterlogged();
    }

	private boolean spawnWarpCrystal(Location loc, ItemStack itemStack, Player p) {
			Location Spawnloc = loc;
			Spawnloc.setY(loc.getY() + 0.355);
			Spawnloc.setX(loc.getX() + 0.5);
			Spawnloc.setZ(loc.getZ() + 0.5);
			

			loc.setY(loc.getY()+1);
			if(loc.getBlock().getType().equals(Material.AIR)) {
				loc.getBlock().breakNaturally();
				loc.getBlock().setType(Material.BARRIER);
				
				loc.setY(loc.getY()-1);
				ArmorStand armorstand = (ArmorStand) loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
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
				
				loc.setY(loc.getY()+1);
				File warp_crystalfile = new File(loc.getWorld().getWorldFolder()+"/data/mcdata/warp_crystal.yml");
				YamlConfiguration warp_crystalyml = YamlConfiguration.loadConfiguration(warp_crystalfile);			
				List<String> uuids = (List<String>) warp_crystalyml.getStringList("UUID");
				UUID random = UUID.randomUUID();
				uuids.add(random+"");
				warp_crystalyml.set("UUID",uuids);
				warp_crystalyml.set(random+".X", loc.getBlockX());
				warp_crystalyml.set(random+".Y", loc.getBlockY());
				warp_crystalyml.set(random+".Z", loc.getBlockZ());
				warp_crystalyml.set(random+".WarpCharge", 0);
				warp_crystalyml.set(random+".Name", p.getName()+"'s Crystal");
				warp_crystalyml.set(random+".OwnerUUID", p.getUniqueId().toString());
				warp_crystalyml.set(random+".Visibility", 0);
				
				Integer X = loc.getBlockX();
				Integer Y = loc.getBlockY();
				Integer Z = loc.getBlockZ();
				Location sloc = new Location(loc.getWorld(), X, Y, Z);
				
				WarpCrystal warp_crystal = new WarpCrystal(sloc, 0, random+"",p.getUniqueId(),p.getName()+"'s Crystal",0);
				Cache.warp_crystal.add(warp_crystal);
				try {
					warp_crystalyml.save(warp_crystalfile);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		return false;
	}
}
