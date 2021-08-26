package de.pixelstudios.mcreloaded.guis.menu;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import de.pixelstudios.mcreloaded.MCReloaded;
import de.pixelstudios.mcreloaded.guis.GUIicons;
import de.pixelstudios.mcreloaded.items.manager.HeadList;
import de.pixelstudios.mcreloaded.utils.Utils;
import io.pixelstudios.libary.MathLibary;

public class MainMenuGUI {

	public static HashMap<Player, OfflinePlayer> OtherProfileSave = new HashMap<>();
	
	public static void openProfileGui(Player p) {		
		int GUI_Slots = 9*6;
		Inventory inventory = Bukkit.createInventory(null, GUI_Slots, "§5My profile!");	

		Bukkit.getScheduler().runTaskAsynchronously(MCReloaded.getPlugin(),new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < GUI_Slots; ++i ) {
					inventory.setItem(i, GUIicons.GRAYFILLER);
				}
				inventory.setItem(22, GUIicons.ACHIEVEMENTS);
				inventory.setItem(49, GUIicons.CLOSE_ICON);
				inventory.setItem(50, GUIicons.SETTINGS);
				Bukkit.getScheduler().runTask(MCReloaded.getPlugin(),new Runnable() {
					@Override
					public void run() {
						p.openInventory(inventory);			
					}
				});
			}
		});	
		p.openInventory(inventory);		
	}
	@SuppressWarnings("deprecation")
	public static void openOtherProfileGui(String name, Player sender) {
	
		int GUI_Slots = 9*6;
		
		Inventory inventory = Bukkit.createInventory(null, GUI_Slots, "§5"+name+"`s profile!");
		
		Bukkit.getScheduler().runTaskAsynchronously(MCReloaded.getPlugin(),new Runnable() {
			@Override
			public void run() {
				OfflinePlayer op = Bukkit.getOfflinePlayer(name);
				ItemStack skullinfo = new ItemStack(Material.LEGACY_SKULL_ITEM, 1 ,(short) 3);
				SkullMeta skullinfo1 = (SkullMeta) skullinfo.getItemMeta();
				skullinfo1.setOwner(op.getName());
				skullinfo.setItemMeta(skullinfo1);
				
				ItemStack SlotfillerClickSlot = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
				ItemMeta SlotfillerClickSlot1 = SlotfillerClickSlot.getItemMeta();
				SlotfillerClickSlot1.setDisplayName(" ");
				SlotfillerClickSlot.setItemMeta(SlotfillerClickSlot1);
				SlotfillerClickSlot.setAmount(1);
				
				ItemStack SlotfillerEmptyInvSlot = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
				ItemMeta SlotfillerEmptyInvSlot1 = SlotfillerEmptyInvSlot.getItemMeta();
		
				for (int i = 0; i < GUI_Slots; ++i ) {
					inventory.setItem(i, GUIicons.GRAYFILLER);
				}
				
				SlotfillerEmptyInvSlot1.setDisplayName("§c§lHelmet Slot");
				SlotfillerEmptyInvSlot.setItemMeta(SlotfillerEmptyInvSlot1);
				inventory.setItem(10, SlotfillerEmptyInvSlot);
				SlotfillerEmptyInvSlot1.setDisplayName("§c§lOffhand Slot");
				SlotfillerEmptyInvSlot.setItemMeta(SlotfillerEmptyInvSlot1);
				inventory.setItem(18, SlotfillerEmptyInvSlot);	
				SlotfillerEmptyInvSlot1.setDisplayName("§c§lChestplate Slot");
				SlotfillerEmptyInvSlot.setItemMeta(SlotfillerEmptyInvSlot1);
				inventory.setItem(19, SlotfillerEmptyInvSlot);
				SlotfillerEmptyInvSlot1.setDisplayName("§c§lMainhand Slot");
				SlotfillerEmptyInvSlot.setItemMeta(SlotfillerEmptyInvSlot1);
				inventory.setItem(20, SlotfillerEmptyInvSlot);
				SlotfillerEmptyInvSlot1.setDisplayName("§c§lLeggings Slot");
				SlotfillerEmptyInvSlot.setItemMeta(SlotfillerEmptyInvSlot1);
				inventory.setItem(28, SlotfillerEmptyInvSlot);
				SlotfillerEmptyInvSlot1.setDisplayName("§c§lBoots Slot");
				SlotfillerEmptyInvSlot.setItemMeta(SlotfillerEmptyInvSlot1);
				inventory.setItem(37, SlotfillerEmptyInvSlot);
				
				if(op.isOnline()) {
					Player onlinePlayer = Bukkit.getPlayer(op.getName());
					if(onlinePlayer.getInventory().getHelmet() != null) {
						inventory.setItem(10, onlinePlayer.getInventory().getHelmet());
					}
					if(!onlinePlayer.getInventory().getItemInOffHand().getType().equals(Material.AIR)) {
						inventory.setItem(18, onlinePlayer.getInventory().getItemInOffHand());	
					}
					if(onlinePlayer.getInventory().getChestplate() != null) {
						inventory.setItem(19, onlinePlayer.getInventory().getChestplate());
					}
					if(!onlinePlayer.getInventory().getItemInMainHand().getType().equals(Material.AIR)) {
						inventory.setItem(20, onlinePlayer.getInventory().getItemInMainHand());
					}
					if(onlinePlayer.getInventory().getLeggings() != null) {
						inventory.setItem(28, onlinePlayer.getInventory().getLeggings());
					}
					if(onlinePlayer.getInventory().getBoots() != null) {
						inventory.setItem(37, onlinePlayer.getInventory().getBoots());
					}
					
					ItemStack Online = new ItemStack(HeadList.ONLINE);
					ItemMeta Online1 = Online.getItemMeta();
					Online1.setDisplayName("§8➤ §a§lOnline");
					ArrayList<String> Online2 = new ArrayList<String>();
					Online2.add("§7§l§m======================");
					Online2.add("§8➥ §fPing: §a"+onlinePlayer.getPing()+"ms");
					Online1.setLore(Online2);
					Online.setItemMeta(Online1);
					Online.setAmount(1);	
					inventory.setItem(15, Online);
				}else {
					ItemStack Offline = new ItemStack(HeadList.OFFLINE);
					ItemMeta Offline1 = Offline.getItemMeta();
					Offline1.setDisplayName("§8➤ §c§lOffline");
					ArrayList<String> Offline2 = new ArrayList<String>();
					Offline2.add("§7§l§m======================");
					Offline2.add("§8➥ §fLast online: §a"+MathLibary.getTimeRange(System.currentTimeMillis(), op.getLastPlayed())+" ago");
					Offline1.setLore(Offline2);
					Offline.setItemMeta(Offline1);
					Offline.setAmount(1);
					
					inventory.setItem(15, Offline);
				}
					
				inventory.setItem(16, SlotfillerClickSlot);
				inventory.setItem(22, skullinfo);
				inventory.setItem(24, SlotfillerClickSlot);
				inventory.setItem(25, SlotfillerClickSlot);
				inventory.setItem(33, SlotfillerClickSlot);
				inventory.setItem(34, SlotfillerClickSlot);
				inventory.setItem(42, SlotfillerClickSlot);
				inventory.setItem(43, SlotfillerClickSlot);
				inventory.setItem(49, GUIicons.CLOSE_ICON);
				Bukkit.getScheduler().runTask(MCReloaded.getPlugin(),new Runnable() {
					@Override
					public void run() {
						OtherProfileSave.put(sender, op);
						sender.openInventory(inventory);			
					}
				});
			}
		});	
	}
}
