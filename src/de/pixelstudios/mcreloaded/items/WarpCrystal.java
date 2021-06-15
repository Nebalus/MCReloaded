package de.pixelstudios.mcreloaded.items;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import de.pixelstudios.mcreloaded.MCReloaded;
import de.pixelstudios.mcreloaded.datamanagement.Cache;
import de.pixelstudios.mcreloaded.manager.ItemManager;
import io.pixelstudios.libary.InventoryLibary;

public class WarpCrystal {

	private Location location;
	private int warpcharge;
	private String uuid;
	private UUID owneruuid;
	private String name;
	
	//0 = Privat
	//1 = Nur Freunde
	//2 = Öffentlich
	private int visibility;
	
	public WarpCrystal(Location location, int warpcharge, String uuid, UUID owneruuid, String name, int visibility){
		this.location = location;
		this.warpcharge = warpcharge;
		this.uuid = uuid;
		this.owneruuid = owneruuid;
		this.name = name;
		this.visibility = visibility;
	}
	public Location getLocation() {
		return location;
	}

	public int getWarpCharge() {
		return warpcharge;
	}
	
	public UUID getOwnerUUID() {
		return owneruuid;
	}
	
	public String getName() {
		return name;
	}
	
	public int getVisibility() {
		return visibility;
	}
	
	public void setVisibility(int visibility) {
		File warp_crystalfile = new File(location.getWorld().getWorldFolder()+"/data/mcdata/warp_crystal.yml");
		YamlConfiguration warp_crystalyml = YamlConfiguration.loadConfiguration(warp_crystalfile);		
		warp_crystalyml.set(uuid+".Visibility", visibility);
		try {
			warp_crystalyml.save(warp_crystalfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.visibility = visibility;
	}
	
	public void setWarpCharge(int charge) {
		File warp_crystalfile = new File(location.getWorld().getWorldFolder()+"/data/mcdata/warp_crystal.yml");
		YamlConfiguration warp_crystalyml = YamlConfiguration.loadConfiguration(warp_crystalfile);		
		warp_crystalyml.set(uuid+".WarpCharge", charge);
		try {
			warp_crystalyml.save(warp_crystalfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.warpcharge = charge;
	}
	
	
	
	public void despawn(Player p) {
		InventoryLibary.addItemToInventory(p, ItemManager.WARP_CRYSTAL);
		if(warpcharge > 0) {
			ItemStack WarpFuel = new ItemStack(ItemManager.WARP_FUEL);
			ItemMeta WarpFuelMeta = WarpFuel.getItemMeta();
			WarpFuelMeta.getPersistentDataContainer().set(new NamespacedKey(MCReloaded.getPlugin(), "fuelcharge"), PersistentDataType.INTEGER, warpcharge);
			ArrayList<String> WarpFuel_Lore = new ArrayList<String>();
			WarpFuel_Lore.add("§7Charge §a3§7/§a"+warpcharge);
			WarpFuelMeta.setLore(WarpFuel_Lore);
			WarpFuel.setItemMeta(WarpFuelMeta);
			InventoryLibary.addItemToInventory(p, WarpFuel);
		}

		File warp_crystalfile = new File(location.getWorld().getWorldFolder()+"/data/mcdata/warp_crystal.yml");
		YamlConfiguration warp_crystalyml = YamlConfiguration.loadConfiguration(warp_crystalfile);		
		List<String> uuids = (List<String>) warp_crystalyml.getStringList("UUID");
		uuids.remove(uuid);
		
		warp_crystalyml.set(uuid, null);
		warp_crystalyml.set("UUID",uuids);
		
		for(Entity entity : location.getChunk().getEntities()) {
			if(entity.getType().equals(EntityType.ARMOR_STAND)) {
				Location eloc = entity.getLocation().getBlock().getLocation();
				eloc.setY(eloc.getY()+1);
				if(eloc.equals(location.getBlock().getLocation())) {
					entity.remove();
				}
			}
		}
		location.getBlock().setType(Material.AIR);
		try {
			warp_crystalyml.save(warp_crystalfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Cache.warp_crystal.remove(this);
		p.closeInventory();
		Cache.warp_crystal_gui_session.remove(p);
	}
}
