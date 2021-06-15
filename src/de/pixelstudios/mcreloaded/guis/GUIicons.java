package de.pixelstudios.mcreloaded.guis;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GUIicons {

	public static ItemStack CLOSE_ICON;
	public static ItemStack NEXT_ICON;
	public static ItemStack BACK_ICON;
	public static ItemStack MENU;
	public static ItemStack SETTINGS;
	
	public static ItemStack WARPCRYSTAL_PICKUP;
	public static ItemStack WARPCRYSTAL_VISIBILITY_PUBLIC;
	public static ItemStack WARPCRYSTAL_VISIBILITY_PRIVATE;
	
	public static ItemStack WHITEFILLER;
	public static ItemStack GRAYFILLER;
	public static ItemStack LIGHTGRAYFILLER;
	public static ItemStack REDFILLER;
	public static ItemStack LIMEFILLER;
	
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
		
		WHITEFILLER = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
		ItemMeta whitefiller = WHITEFILLER.getItemMeta();
		whitefiller.setDisplayName(" ");
		WHITEFILLER.setItemMeta(whitefiller);
		WHITEFILLER.setAmount(1);
		
		GRAYFILLER = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
		ItemMeta grayfiller = GRAYFILLER.getItemMeta();
		grayfiller.setDisplayName(" ");
		GRAYFILLER.setItemMeta(grayfiller);
		GRAYFILLER.setAmount(1);
		
		LIGHTGRAYFILLER = new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE);
		ItemMeta lightgrayfiller = LIGHTGRAYFILLER.getItemMeta();
		lightgrayfiller.setDisplayName(" ");
		LIGHTGRAYFILLER.setItemMeta(lightgrayfiller);
		LIGHTGRAYFILLER.setAmount(1);
		
		REDFILLER = new ItemStack(Material.RED_STAINED_GLASS_PANE);
		ItemMeta redfiller = REDFILLER.getItemMeta();
		redfiller.setDisplayName(" ");
		REDFILLER.setItemMeta(redfiller);
		REDFILLER.setAmount(1);
		
		LIMEFILLER = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
		ItemMeta limefiller = LIMEFILLER.getItemMeta();
		limefiller.setDisplayName(" ");
		LIMEFILLER.setItemMeta(limefiller);
		LIMEFILLER.setAmount(1);
		
		WARPCRYSTAL_PICKUP = new ItemStack(Material.BEDROCK);
		ItemMeta wc_pickup = WARPCRYSTAL_PICKUP.getItemMeta();
		wc_pickup.setDisplayName("§aWarp Crystal");
		ArrayList<String> wc_pickup1 = new ArrayList<String>();
		wc_pickup1.add("§eClick to pickup!");
		wc_pickup.setLore(wc_pickup1);
		WARPCRYSTAL_PICKUP.setItemMeta(wc_pickup);
		WARPCRYSTAL_PICKUP.setAmount(1);
		
		WARPCRYSTAL_VISIBILITY_PUBLIC = new ItemStack(Material.LIME_DYE);
		ItemMeta wc_visibilitypublic = WARPCRYSTAL_VISIBILITY_PUBLIC.getItemMeta();
		wc_visibilitypublic.setDisplayName("§aVisibility");
		ArrayList<String> wc_visibilitypublic1 = new ArrayList<String>();
		wc_visibilitypublic1.add("§7Current visibility: §aPublic");
		wc_visibilitypublic1.add(" ");
		wc_visibilitypublic1.add("§eClick to change to §6Private§e!");
		wc_visibilitypublic.setLore(wc_visibilitypublic1);
		WARPCRYSTAL_VISIBILITY_PUBLIC.setItemMeta(wc_visibilitypublic);
		WARPCRYSTAL_VISIBILITY_PUBLIC.setAmount(1);
		
		WARPCRYSTAL_VISIBILITY_PRIVATE = new ItemStack(Material.ORANGE_DYE);
		ItemMeta wc_visibilityprivate = WARPCRYSTAL_VISIBILITY_PRIVATE.getItemMeta();
		wc_visibilityprivate.setDisplayName("§6Visibility");
		ArrayList<String> wc_visibilityprivate1 = new ArrayList<String>();
		wc_visibilityprivate1.add("§7Current visibility: §6Private");
		wc_visibilityprivate1.add(" ");
		wc_visibilityprivate1.add("§eClick to change to §aPublic§e!");
		wc_visibilityprivate.setLore(wc_visibilityprivate1);
		WARPCRYSTAL_VISIBILITY_PRIVATE.setItemMeta(wc_visibilityprivate);
		WARPCRYSTAL_VISIBILITY_PRIVATE.setAmount(1);
	}
}
