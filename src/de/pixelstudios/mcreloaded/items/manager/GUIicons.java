package de.pixelstudios.mcreloaded.items.manager;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GUIicons {

	public static ItemStack CLOSE_ICON;
	public static ItemStack NEXT_ICON;
	public static ItemStack BACK_ICON;
	public static ItemStack SLOTFILLER;
	public static ItemStack MENU;
	public static ItemStack SETTINGS;
	
	
	public static ItemStack EXPIERENCEOBELISK_STORE_1;
	public static ItemStack EXPIERENCEOBELISK_STORE_10;
	public static ItemStack EXPIERENCEOBELISK_STORE_ALL;
	public static ItemStack EXPIERENCEOBELISK_RETRIEVE_1;
	public static ItemStack EXPIERENCEOBELISK_RETRIEVE_10;
	public static ItemStack EXPIERENCEOBELISK_RETRIEVE_ALL;
	public static ItemStack EXPIERENCEOBELISK_PICKUP;
	
	public static void loadGUIIcons() {
		CLOSE_ICON = new ItemStack(Material.BARRIER);
		ItemMeta Close = CLOSE_ICON.getItemMeta();
		Close.setCustomModelData(3000);
		Close.setDisplayName("§cClose");
		CLOSE_ICON.setItemMeta(Close);
		CLOSE_ICON.setAmount(1);
		
		NEXT_ICON = new ItemStack(Material.ARROW);
		ItemMeta Back = NEXT_ICON.getItemMeta();
		Back.setCustomModelData(3000);
		Back.setDisplayName("§aNext");
		NEXT_ICON.setItemMeta(Back);
		NEXT_ICON.setAmount(1);
		
		BACK_ICON = new ItemStack(Material.ARROW);
		ItemMeta Prev = BACK_ICON.getItemMeta();
		Prev.setCustomModelData(3001);
		Prev.setDisplayName("§aBack");
		BACK_ICON.setItemMeta(Prev);
		BACK_ICON.setAmount(1);
		
		SLOTFILLER = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
		ItemMeta Slotfiller1 = SLOTFILLER.getItemMeta();
		Slotfiller1.setDisplayName(" ");
		Slotfiller1.setCustomModelData(3000);
		SLOTFILLER.setItemMeta(Slotfiller1);
		SLOTFILLER.setAmount(1);
		
		MENU = new ItemStack(Material.BOOK);
		ItemMeta menu = MENU.getItemMeta();
		menu.setDisplayName("§aYour Profile");
		ArrayList<String> menu1 = new ArrayList<String>();
		menu1.add("§eClick to open!");
		menu.setLore(menu1);
		menu.setCustomModelData(3000);
		MENU.setItemMeta(menu);
		MENU.setAmount(1);
		
		SETTINGS = new ItemStack(Material.REDSTONE_TORCH);
		ItemMeta settings = SETTINGS.getItemMeta();
		settings.setDisplayName("§aSettings");
		ArrayList<String> settings1 = new ArrayList<String>();
		settings1.add("§8View and edit your settings!");
		settings1.add(" ");
		settings1.add("§eClick to open!");
		settings.setLore(settings1);
		settings.setCustomModelData(3000);
		SETTINGS.setItemMeta(settings);
		SETTINGS.setAmount(1);
		
		loadExpierenceObeliskGUI();
	}
	
	private static void loadExpierenceObeliskGUI() {
		EXPIERENCEOBELISK_STORE_1 = new ItemStack(Material.GLASS_PANE);
		ItemMeta store1_1 = EXPIERENCEOBELISK_STORE_1.getItemMeta();
		store1_1.setDisplayName("§7Store 1");
		ArrayList<String> store2_1 = new ArrayList<String>();
		store2_1.add("§7level of player XP");
		store1_1.setLore(store2_1);
		EXPIERENCEOBELISK_STORE_1.setItemMeta(store1_1);
		EXPIERENCEOBELISK_STORE_1.setAmount(1);
		
		EXPIERENCEOBELISK_STORE_10 = new ItemStack(Material.GLASS_PANE);
		ItemMeta store1_10 = EXPIERENCEOBELISK_STORE_10.getItemMeta();
		store1_10.setDisplayName("§7Store 10");
		ArrayList<String> store2_10 = new ArrayList<String>();
		store2_10.add("§7levels of player XP");
		store1_10.setLore(store2_10);
		EXPIERENCEOBELISK_STORE_10.setItemMeta(store1_10);
		EXPIERENCEOBELISK_STORE_10.setAmount(1);
		
		EXPIERENCEOBELISK_STORE_ALL = new ItemStack(Material.GLASS_PANE);
		ItemMeta store1_all = EXPIERENCEOBELISK_STORE_ALL.getItemMeta();
		store1_all.setDisplayName("§7Store all");
		ArrayList<String> store2_all = new ArrayList<String>();
		store2_all.add("§7levels of player XP");
		store1_all.setLore(store2_all);
		EXPIERENCEOBELISK_STORE_ALL.setItemMeta(store1_all);
		EXPIERENCEOBELISK_STORE_ALL.setAmount(1);
		
		EXPIERENCEOBELISK_RETRIEVE_1 = new ItemStack(Material.GLASS_PANE);
		ItemMeta retrieve1_1 = EXPIERENCEOBELISK_RETRIEVE_1.getItemMeta();
		retrieve1_1.setDisplayName("§7Retrieve 1");
		ArrayList<String> retrieve2_1 = new ArrayList<String>();
		retrieve2_1.add("§7level of player XP");
		retrieve1_1.setLore(retrieve2_1);
		EXPIERENCEOBELISK_RETRIEVE_1.setItemMeta(retrieve1_1);
		EXPIERENCEOBELISK_RETRIEVE_1.setAmount(1);
		
		EXPIERENCEOBELISK_RETRIEVE_10 = new ItemStack(Material.GLASS_PANE);
		ItemMeta retrieve1_10 = EXPIERENCEOBELISK_RETRIEVE_10.getItemMeta();
		retrieve1_10.setDisplayName("§7Retrieve 10");
		ArrayList<String> retrieve2_10 = new ArrayList<String>();
		retrieve2_10.add("§7levels of player XP");
		retrieve1_10.setLore(retrieve2_10);
		EXPIERENCEOBELISK_RETRIEVE_10.setItemMeta(retrieve1_10);
		EXPIERENCEOBELISK_RETRIEVE_10.setAmount(1);
		
		EXPIERENCEOBELISK_RETRIEVE_ALL = new ItemStack(Material.GLASS_PANE);
		ItemMeta retrieve1_all = EXPIERENCEOBELISK_RETRIEVE_ALL.getItemMeta();
		retrieve1_all.setDisplayName("§7Retrieve all");
		ArrayList<String> retrieve2_all = new ArrayList<String>();
		retrieve2_all.add("§7levels of player XP");
		retrieve1_all.setLore(retrieve2_all);
		EXPIERENCEOBELISK_RETRIEVE_ALL.setItemMeta(retrieve1_all);
		EXPIERENCEOBELISK_RETRIEVE_ALL.setAmount(1);
		
		EXPIERENCEOBELISK_PICKUP = new ItemStack(Material.BEDROCK);
		ItemMeta pickup = EXPIERENCEOBELISK_PICKUP.getItemMeta();
		pickup.setDisplayName("§aExpierence Obelisk");
		ArrayList<String> pickup1 = new ArrayList<String>();
		pickup1.add("§eClick to pickup!");
		pickup.setLore(pickup1);
		EXPIERENCEOBELISK_PICKUP.setItemMeta(pickup);
		EXPIERENCEOBELISK_PICKUP.setAmount(1);
		
	}
}
