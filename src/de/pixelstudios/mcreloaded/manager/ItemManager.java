package de.pixelstudios.mcreloaded.manager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Tag;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.SmithingRecipe;
import org.bukkit.inventory.SmokingRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.inventory.RecipeChoice.ExactChoice;
import org.bukkit.inventory.RecipeChoice.MaterialChoice;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import de.pixelstudios.mcreloaded.ConsoleLogger;
import de.pixelstudios.mcreloaded.MCReloaded;
import de.pixelstudios.mcreloaded.datamanagement.Config;
import de.pixelstudios.mcreloaded.guis.GUIicons;
import de.pixelstudios.mcreloaded.items.manager.HeadList;
import de.pixelstudios.mcreloaded.messaging.MessageFormatter;
import de.pixelstudios.mcreloaded.utils.Utils;

public class ItemManager {
	private MessageFormatter messageFormatter;
	private Config config;
	
	public ItemManager(MCReloaded plugin) {
		this.messageFormatter = plugin.getMessageFormatter();
		this.config = plugin.getMCReloadedConfig();
	}
	
	public static List<Material> blockedMaterial = new ArrayList<Material>();
	
	private static Map<String, ItemStack> ALL_ITEMS = new HashMap<>();
	private final String BONUS_LORE_BEGINNING = "§7● ";
	private final String SET_BONUS_LORE_HEADER = "§a§lSet Bonus";
	private final String BONUS_LORE_HEADER = "§a§lBonus";
	
	
    public final static NamespacedKey invisible_item_frame_Key = new NamespacedKey(MCReloaded.getPlugin(), "invisible_item_frame");
    public final static NamespacedKey stormlander_cooldown_Key = new NamespacedKey(MCReloaded.getPlugin(), "stormlander_cooldown");
    public final static NamespacedKey portable_crafting_table_Key = new NamespacedKey(MCReloaded.getPlugin(), "portable_crafting_table");
    public final static NamespacedKey portable_enderchest_Key = new NamespacedKey(MCReloaded.getPlugin(), "portable_enderchest");
    public final static NamespacedKey grappling_hook_Key = new NamespacedKey(MCReloaded.getPlugin(), "grappling_hook");
    public final static NamespacedKey drinkables_Key = new NamespacedKey(MCReloaded.getPlugin(), "drinks");
    public final static NamespacedKey coffee_bean_Key = new NamespacedKey(MCReloaded.getPlugin(), "coffee_bean");
    public final static NamespacedKey heart_of_the_mine_Key = new NamespacedKey(MCReloaded.getPlugin(), "heart_of_the_mine");
    public final static NamespacedKey grand_experience_bottle_Key = new NamespacedKey(MCReloaded.getPlugin(), "grand_experience_bottle");
    public final static NamespacedKey warp_crystal_key = new NamespacedKey(MCReloaded.getPlugin(), "warp_crystal");
    public final static NamespacedKey warp_fuel_key = new NamespacedKey(MCReloaded.getPlugin(), "warp_fuel");
    
    public final static NamespacedKey golderite_ingot_Key = new NamespacedKey(MCReloaded.getPlugin(), "golderite_ingot");
    public final static NamespacedKey cristal_fragment_Key = new NamespacedKey(MCReloaded.getPlugin(), "crystal_fragment");
    
    //*************************************************************
    public static NamespacedKey telekinesis_enchantment = new NamespacedKey(MCReloaded.getPlugin(), "ENCHANT_TELEKINESIS");
    public static NamespacedKey smelting_touch_enchantment = new NamespacedKey(MCReloaded.getPlugin(), "ENCHANT_SMELTING_TOUCH");
    //*************************************************************
    
	public static ItemStack PORTABLE_CRAFTING_TABLE;
	public static ItemStack PORTABLE_ENDERCHEST;
	public static ItemStack INVISIBLE_ITEM_FRAME;
	public static ItemStack INVISIBLE_GLOW_ITEM_FRAME;
	public static ItemStack GRAPPLING_HOOK;
	public static ItemStack HEART_OF_THE_MINE;
	
	public static ItemStack ANDESITE_PICKAXE;
	public static ItemStack DIORITE_PICKAXE;
	public static ItemStack GRANITE_PICKAXE;
	
	public static ItemStack IRON_HAMMER;
	public static ItemStack STORMLANDER;
	
	public static ItemStack SUPER_AXE;
	public static ItemStack SUPER_AXE_NETHERITE;
	
	public static ItemStack SUPER_SHOVEL;
	public static ItemStack SUPER_SHOVEL_NETHERITE;
	
	public static ItemStack GRAND_EXPERIENCE_BOTTLE;
	
	public static ItemStack WARP_CRYSTAL;
	public static ItemStack WARP_FUEL;
	
	public static ItemStack GOLDERITE_ARMOR_HELMET;
	public static ItemStack GOLDERITE_ARMOR_CHESTPLATE;
	public static ItemStack GOLDERITE_ARMOR_LEGGINGS;
	public static ItemStack GOLDERITE_ARMOR_BOOTS;
	public static ItemStack GOLDERITE_INGOT;
	public static ItemStack GOLDERITE_SWORD;
	
	public static ItemStack COPPER_ARMOR_HELMET;
	public static ItemStack COPPER_ARMOR_CHESTPLATE;
	public static ItemStack COPPER_ARMOR_LEGGINGS;
	public static ItemStack COPPER_ARMOR_BOOTS;

	//*************************************************************
	//Costmetics
	public static ItemStack ELYTRA_RAVEN_WINGS;
	public static ItemStack ELYTRA_DRAGON_WINGS;
	public static ItemStack ELYTRA_MATRIX;
	
	
	//*************************************************************
	public static ItemStack DIRTY_WATER;
	public static ItemStack CLEAN_WATER;
	public static ItemStack PURIFIED_WATER;
	public static ItemStack COLD_MILK;
	public static ItemStack HOT_MILK;
	public static ItemStack COFFEE;
	public static ItemStack WATER_BOWL;
	public static ItemStack COFFEE_BEAN;
	public static ItemStack GOLDEN_APPLE_JUICE;
	public static ItemStack ENCHANTED_GOLDEN_APPLE_JUICE;
	//*************************************************************
	//Entitys
	public static ItemStack BOLT;
	
	public void loadItemManager() {
	    ConsoleLogger.info(null, messageFormatter.format(false, "console.enable.itemmanager.initialize-itemmanager"));
		if(removeCraftingRezept()) {
			 ConsoleLogger.info(ConsoleLogger.ITEM_MANAGER, messageFormatter.format(false, "console.enable.itemmanager.craftingrecipe-remove"));
		}
		loadHeads();
		if(loadCustomitems()) {
			 ConsoleLogger.info(ConsoleLogger.ITEM_MANAGER, messageFormatter.format(false, "console.enable.itemmanager.customitems-loaded"));
			 if(loadRezepte()) {
				 ConsoleLogger.info(ConsoleLogger.ITEM_MANAGER, messageFormatter.format(false, "console.enable.itemmanager.recipes-loaded"));
			}
		}
		GUIicons.loadGUIIcons();
	}
	
	public boolean removeCraftingRezept() {
		blockedMaterial.add(Material.COOKIE);
		blockedMaterial.add(Material.LEATHER_HORSE_ARMOR);
		blockedMaterial.add(Material.SPECTRAL_ARROW);
		
		Iterator<Recipe> iter = Bukkit.getServer().recipeIterator();
		
		while(iter.hasNext()) {
			Recipe r = iter.next();
			Material m = r.getResult().getType();
			
			if(blockedMaterial.contains(m)) {
				iter.remove();
			}
		}
		return true;
	}
	
	public void loadHeads() {		
		String url = "https://textures.minecraft.net/texture/";
	
		//https://minecraft-heads.com/custom-heads/miscellaneous/26412-plus
		HeadList.PLUS = Utils.getSkullByTextureURL(url+"58cf2c2b75b97343901f67ec0efbcfc0f49335ebac4404e27cf25a79dbd21561", null, null);
		
		//https://minecraft-heads.com/custom-heads/miscellaneous/24889-offline
		HeadList.OFFLINE = Utils.getSkullByTextureURL(url+"50df56437005795679499548496838dbacceaf705cfcad994ab294bff84e299f", null, null);	
	
		//https://minecraft-heads.com/custom-heads/miscellaneous/24886-online
		HeadList.ONLINE = Utils.getSkullByTextureURL(url+"f5be49bbdd1db35def04ad11f06deaaf45c9666c05bc02bc8bf1444e99c7e", null, null);		
       
        //https://minecraft-heads.com/custom-heads/decoration/24180-crafting-table
        HeadList.WORKBENCH = Utils.getSkullByTextureURL(url+"2cdc0feb7001e2c10fd5066e501b87e3d64793092b85a50c856d962f8be92c78", null, null);            
     
        //https://minecraft-heads.com/custom-heads/decoration/35428-experience-bottle
        HeadList.EXPERIENCE_BOTTLE = Utils.getSkullByTextureURL(url+"7054c29c78090471c1ea058bd641897391c9e4694aa9d140afbba8d0cc43637", null, null);        	
                
        //https://minecraft-heads.com/custom-heads/miscellaneous/26413-plus-active
        HeadList.PLUS_ACTIVE = Utils.getSkullByTextureURL(url+"9aca891a7015cbba06e61c600861069fa7870dcf9b35b4fe15850f4b25b3ce0", null, null);
        	    	       
        //https://minecraft-heads.com/custom-heads/decoration/228-ender-chest
        HeadList.ENDERCHEST = Utils.getSkullByTextureURL(url+"a6cc486c2be1cb9dfcb2e53dd9a3e9a883bfadb27cb956f1896d602b4067", null, null);
        	
        //https://minecraft-heads.com/custom-heads/decoration/6640-huge-exp-orb
        HeadList.EXPERIENCE_ORB = Utils.getSkullByTextureURL(url+"31d568e16be6c79d674f97ac1e949f8a8f03e3837b6f0b56a539bfc337f1ebd", null, null);
        
        //https://minecraft-heads.com/custom-heads/decoration/38198-teleportation-core
        HeadList.TELEPORTATION_CORE = Utils.getSkullByTextureURL(url+"4ec1c3f7d09ce6c0cb48ed30b4596a5c14fae79def8bfd14a59fc1935600bc7b", null, null);
        
        //https://minecraft-heads.com/custom-heads/decoration/36293-soul-lantern
        HeadList.SOUL_LANTERN = Utils.getSkullByTextureURL(url+"9580e5cd970c1ded973d0c139be418b0755e0713345d716ed594244a53e6e13d", null, null);

        //https://minecraft-heads.com/custom-heads/decoration/32371-opal
        HeadList.OPAL = Utils.getSkullByTextureURL(url+"49ae88a7d03f474558a05692e5f5c3ade312ddf1072166ad0426334ef5174b87", null, null);
        
        //https://minecraft-heads.com/custom-heads/miscellaneous/39654-revive-stone
        HeadList.REVIVE_STONE = Utils.getSkullByTextureURL(url+"b6a76cc22e7c2ab9c540d1244eadba581f5dd9e18f9adacf05280a5b48b8f618", null, null);
        
        //https://minecraft-heads.com/custom-heads/decoration/36763-stone-tank-empty
        HeadList.STONE_TANK = Utils.getSkullByTextureURL(url+"b91b7b21725f146d29c192b745d79d22603267c7ad893badeb6546e746600060", null, null);
        
        //https://minecraft-heads.com/custom-heads/decoration/44796-energy-core
        HeadList.WARP_CRYSTAL = Utils.getSkullByTextureURL(url+"77400ea19dbd84f75c39ad6823ac4ef786f39f48fc6f84602366ac29b837422", null, null);
        
	}

	
	
    public class CustomItem{
    	
    	private ItemStack itemstack;
    	private ItemMeta itemmeta;
    	private PersistentDataContainer psc;
    	private Modifiers[] modifiers;
        	
    	public CustomItem(ItemStack itemstack) {
    		this.itemstack = new ItemStack(itemstack);
    		this.itemmeta = itemstack.getItemMeta();
    		this.psc = itemmeta.getPersistentDataContainer();		
    	}
    	public CustomItem(Material material) {
    		this.itemstack = new ItemStack(material);
    		this.itemmeta = itemstack.getItemMeta();
    		this.psc = itemmeta.getPersistentDataContainer();		
    	}
    	
    	public CustomItem setDisplayName(String displayname) {
    		itemmeta.setDisplayName(displayname);
    		return this;
    	}
    	
    	public CustomItem setCustomModelData(int custommodeldata) {
    		itemmeta.setCustomModelData(custommodeldata);
    		return this;
    	}
    	
    	public CustomItem setLore(String... lore) {
    		List<String> finalLore = new ArrayList<String>();
    		for(String subLore : lore) {
    			finalLore.add(subLore);
    		}
    		itemmeta.setLore(finalLore);
    		return this;
    	}
    	
    	public CustomItem setLore(ArrayList<String> lore) {
    		itemmeta.setLore(lore);
    		return this;
    	}
    	
    	public CustomItem addItemFlags(ItemFlag... itemflag) {
    		itemmeta.addItemFlags(itemflag);
    		return this;
    	}
    	
    	public CustomItem setIdentifierKeys(NamespacedKey... identifierkeys) {
    		for(NamespacedKey identifierkey : identifierkeys) {
				psc.set(identifierkey, PersistentDataType.BYTE, (byte) 1);
			}
    		return this;
    	}

    	public CustomItem setModifiers(Modifiers... modifiers) {
    		this.modifiers = modifiers;
    		for(Modifiers forModifiers : modifiers) {
				psc.set(forModifiers.getKey(), PersistentDataType.BYTE, (byte) 1);
			}
    		return this;
    	}
    	
    	public CustomItem addEnchant(Enchantment Enchantment, int Level) {
    		itemmeta.addEnchant(Enchantment, Level, true);
    		return this;
    	}
    	
    	public ItemStack build() {
    		List<String> finalLore = new ArrayList<String>();
    		if(itemmeta.getLore() != null) {
    			finalLore = itemmeta.getLore();	
    		}
    		if(finalLore != null) {
	    		int line = 0;
	    		for(String subLore : finalLore) {
	    			if(subLore.startsWith(BONUS_LORE_BEGINNING)) {
	    				finalLore.remove(line);
	    			}else if(subLore.equalsIgnoreCase(BONUS_LORE_HEADER) || subLore.equalsIgnoreCase(SET_BONUS_LORE_HEADER)) {
	    				finalLore.remove(line-1);
	    				finalLore.remove(line);
	    			}
	    			line++;
	    		}
    		}
    		if(modifiers != null) {
    			List<String> setbonus = new ArrayList<String>();
    			List<String> bonus = new ArrayList<String>();
    			for(Modifiers modify : modifiers) {
    				if(modify.isSetBonus()) {
    					if(setbonus.size() == 0) {
    						setbonus.add(" ");
    						setbonus.add(SET_BONUS_LORE_HEADER);
    					}
    					setbonus.add(BONUS_LORE_BEGINNING+modify.getDescription());
    				}else {
    					if(bonus.size() == 0) {
    						bonus.add(" ");
    						bonus.add(BONUS_LORE_HEADER);
    					}
    					bonus.add(BONUS_LORE_BEGINNING+modify.getDescription());
    				}
    			}
    			if(setbonus != null) {
    				finalLore.addAll(setbonus);
    			}
    			if(bonus != null) {
    				finalLore.addAll(bonus);
    			}
    		}
    		if(finalLore != null) {
    			itemmeta.setLore(finalLore);
    		}
    		itemstack.setItemMeta(itemmeta);
    		
    		return itemstack;
    	}
    	
    }
	public boolean loadCustomitems() {
		//Spieler köpfe laden
		PORTABLE_CRAFTING_TABLE = new CustomItem(HeadList.WORKBENCH)
				.setDisplayName("§fPortable Crafting Table")
				.setCustomModelData(1)
				.setIdentifierKeys(portable_crafting_table_Key)
				.build();
		ALL_ITEMS.put("PORTABLE_CRAFTING_TABLE", PORTABLE_CRAFTING_TABLE);
		
		PORTABLE_ENDERCHEST = new CustomItem(HeadList.ENDERCHEST)
				.setDisplayName("§fPortable Enderchest")
				.setCustomModelData(2)
				.setIdentifierKeys(portable_enderchest_Key)
				.build();
		ALL_ITEMS.put("PORTABLE_ENDERCHEST", PORTABLE_ENDERCHEST);
		
		WARP_CRYSTAL = new CustomItem(HeadList.WARP_CRYSTAL)
				.setDisplayName("§fWarp Crystal")
				.setCustomModelData(3)
				.setIdentifierKeys(warp_crystal_key)
				.build();
		ALL_ITEMS.put("WARP_CRYSTAL", WARP_CRYSTAL);	
		
		//******************************************************************
		//Costmetics
		ELYTRA_RAVEN_WINGS = new CustomItem(Material.ELYTRA)
				.setDisplayName("§eRaven Wing Elytra")
				.setCustomModelData(1)
				.build();
	    ALL_ITEMS.put("ELYTRA_RAVEN_WINGS", ELYTRA_RAVEN_WINGS);
	    
	    ELYTRA_DRAGON_WINGS = new CustomItem(Material.ELYTRA)
	    		.setDisplayName("§eDragon Wing Elytra")
	    		.setCustomModelData(2)
	    		.build();
	    ALL_ITEMS.put("ELYTRA_DRAGON_WINGS", ELYTRA_DRAGON_WINGS);
	    
	    ELYTRA_MATRIX = new CustomItem(Material.ELYTRA)
	    		.setDisplayName("§eMatrix Elytra")
	    		.setCustomModelData(3)
	    		.build();
	    ALL_ITEMS.put("ELYTRA_MATRIX", ELYTRA_MATRIX);
		
		//******************************************************************
	    ANDESITE_PICKAXE = new CustomItem(Material.STONE_PICKAXE)
	    		.setCustomModelData(1)
	    		.setDisplayName("§fAndesite Pickaxe")
	    		.build();
	    ALL_ITEMS.put("ANDESITE_PICKAXE", ANDESITE_PICKAXE);
	    
	    DIORITE_PICKAXE = new CustomItem(Material.STONE_PICKAXE)
	    		.setDisplayName("§fDiorite Pickaxe")
	    		.setCustomModelData(2)
	    		.build();
	    ALL_ITEMS.put("DIORITE_PICKAXE", DIORITE_PICKAXE);
	    
	    GRANITE_PICKAXE = new CustomItem(Material.STONE_PICKAXE)
	    		.setCustomModelData(3)
	    		.setDisplayName("§fGranite Pickaxe")
	    		.build();
	    ALL_ITEMS.put("GRANITE_PICKAXE", GRANITE_PICKAXE);
	    
	    WARP_FUEL = new CustomItem(Material.QUARTZ)
	    		.setDisplayName("§fWarp Fuel")
	    		.setCustomModelData(1)
	    		.setIdentifierKeys(warp_fuel_key)
	    		.setLore("§7Charge §a3§7/§a3")
	    		.addItemFlags(ItemFlag.HIDE_ENCHANTS)
	    		.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0)
	    		.build();
		ItemMeta WarpFuel = WARP_FUEL.getItemMeta();
		WarpFuel.getPersistentDataContainer().set(new NamespacedKey(MCReloaded.getPlugin(), "fuelcharge"), PersistentDataType.INTEGER, 3);
		WARP_FUEL.setItemMeta(WarpFuel);
		ALL_ITEMS.put("WARP_FUEL", WARP_FUEL);	
	    
	    GOLDERITE_INGOT = new CustomItem(Material.NETHERITE_INGOT)
	    		.setCustomModelData(1)
	    		.setDisplayName("§bGolderite Ingot")
	    		.setIdentifierKeys(golderite_ingot_Key)
	    		.build();
		ALL_ITEMS.put("GOLDERITE_INGOT", GOLDERITE_INGOT);
	    
		GOLDERITE_SWORD = new CustomItem(Material.NETHERITE_SWORD)
				.setDisplayName("§bGolderite Sword")
				.setCustomModelData(1)
				.setIdentifierKeys(golderite_ingot_Key)
				.build();
		ItemMeta Golderite_Sword = GOLDERITE_SWORD.getItemMeta();
		ArrayList<String> Golderite_Sword_Lore = new ArrayList<String>();
		Golderite_Sword_Lore.add(" ");
		Golderite_Sword_Lore.add("§a§lBonus");
		Golderite_Sword_Lore.add("§7● Increase §6Damage§7 dealt to §6Piglin Brute§7 by §62§7.");
		Golderite_Sword_Lore.add("§7● Increase §6Looting Level§7 by §61§7.");
		GOLDERITE_SWORD.setItemMeta(Golderite_Sword);
		ALL_ITEMS.put("GOLDERITE_SWORD", GOLDERITE_SWORD);
		
		GOLDERITE_ARMOR_HELMET = new CustomItem(Material.NETHERITE_HELMET)
				.setDisplayName("§bGolderite Helmet")
				.setCustomModelData(1)
				.setModifiers(Modifiers.PIGLIN_PASSIVE)
				.build();
		ALL_ITEMS.put("GOLDERITE_ARMOR_HELMET", GOLDERITE_ARMOR_HELMET);
		
		GOLDERITE_ARMOR_CHESTPLATE = new CustomItem(Material.NETHERITE_CHESTPLATE)
				.setDisplayName("§bGolderite Chestplate")
				.setCustomModelData(1)
				.setModifiers(Modifiers.PIGLIN_PASSIVE)
				.build();
		ALL_ITEMS.put("GOLDERITE_ARMOR_CHESTPLATE", GOLDERITE_ARMOR_CHESTPLATE);
		
		GOLDERITE_ARMOR_LEGGINGS = new CustomItem(Material.NETHERITE_LEGGINGS)
				.setDisplayName("§bGolderite Leggings")
				.setCustomModelData(1)
				.setModifiers(Modifiers.PIGLIN_PASSIVE)
				.build();
		ALL_ITEMS.put("GOLDERITE_ARMOR_LEGGINGS", GOLDERITE_ARMOR_LEGGINGS);
		
		GOLDERITE_ARMOR_BOOTS = new CustomItem(Material.NETHERITE_BOOTS)
				.setDisplayName("§bGolderite Boots")
				.setCustomModelData(1)
				.setModifiers(Modifiers.PIGLIN_PASSIVE)
				.build();
		ALL_ITEMS.put("GOLDERITE_ARMOR_BOOTS", GOLDERITE_ARMOR_BOOTS);
	    
		COFFEE_BEAN = new CustomItem(Material.COCOA_BEANS)
				.setCustomModelData(1)
				.setDisplayName("§fCoffee Bean")
				.setIdentifierKeys(coffee_bean_Key)
				.build();
	    ALL_ITEMS.put("COFFEE_BEAN", COFFEE_BEAN);
		
	    GRAND_EXPERIENCE_BOTTLE = new CustomItem(Material.EXPERIENCE_BOTTLE)
	    		.setDisplayName("§fGrand Experience Bottle")
	    		.setCustomModelData(1)
	    		.setIdentifierKeys(grand_experience_bottle_Key)
	    		.build();
		ALL_ITEMS.put("GRAND_EXPERIENCE_BOTTLE", GRAND_EXPERIENCE_BOTTLE);
		
		HEART_OF_THE_MINE = new CustomItem(Material.HEART_OF_THE_SEA)
				.setDisplayName("§bHeart Of The Mine")
				.setCustomModelData(1)
				.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0)
				.addItemFlags(ItemFlag.HIDE_ENCHANTS)
				.setIdentifierKeys(heart_of_the_mine_Key)
				.build();
		ALL_ITEMS.put("HEART_OF_THE_MINE", HEART_OF_THE_MINE);
		
		INVISIBLE_ITEM_FRAME = new CustomItem(Material.ITEM_FRAME)
				.addItemFlags(ItemFlag.HIDE_ENCHANTS)
				.setDisplayName("§fInvisible Item Frame")
				.setCustomModelData(1)
				.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1)
				.setIdentifierKeys(invisible_item_frame_Key)
				.build();
		ALL_ITEMS.put("INVISIBLE_ITEM_FRAME", INVISIBLE_ITEM_FRAME);
		
		INVISIBLE_GLOW_ITEM_FRAME = new CustomItem(Material.GLOW_ITEM_FRAME)
				.setCustomModelData(1)
				.addItemFlags(ItemFlag.HIDE_ENCHANTS)
				.setDisplayName("§fInvisible Glow Item Frame")
				.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0)
				.setIdentifierKeys(invisible_item_frame_Key)
				.build();
		ALL_ITEMS.put("INVISIBLE_GLOW_ITEM_FRAME", INVISIBLE_GLOW_ITEM_FRAME);
		
		GRAPPLING_HOOK = new CustomItem(Material.FISHING_ROD)
				.setDisplayName("§fGrappling Hook")
				.setCustomModelData(1)
				.setIdentifierKeys(grappling_hook_Key)
				.build();
		ALL_ITEMS.put("GRAPPLING_HOOK", GRAPPLING_HOOK);
		
		DIRTY_WATER = new CustomItem(Material.POTION)
        		.setCustomModelData(1)
        		.setDisplayName("§fWater Bottle")
        		.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS)
        		.setIdentifierKeys(drinkables_Key)
        		.setLore("§7Dirty")
        		.build();
        ItemMeta dirtyMeta = DIRTY_WATER.getItemMeta();
        ((PotionMeta) dirtyMeta).setBasePotionData(new PotionData(PotionType.WATER));
        ((PotionMeta) dirtyMeta).setColor(Color.fromRGB(12769874));
        DIRTY_WATER.setItemMeta(dirtyMeta);
        ALL_ITEMS.put("DIRTY_WATER", DIRTY_WATER);
        
        CLEAN_WATER = new CustomItem(Material.POTION)
        		.setCustomModelData(2)
        		.setDisplayName("§fWater Bottle")
        		.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS)
        		.setIdentifierKeys(drinkables_Key)
        		.setLore("§7Clean")
        		.build();
        ItemMeta cleanMeta = CLEAN_WATER.getItemMeta();
        ((PotionMeta) cleanMeta).setBasePotionData(new PotionData(PotionType.WATER));
        ((PotionMeta) cleanMeta).setColor(Color.fromRGB(1213666));
        CLEAN_WATER.setItemMeta(cleanMeta);
        ALL_ITEMS.put("CLEAN_WATER", CLEAN_WATER);
        
        PURIFIED_WATER = new CustomItem(Material.POTION)
        		.setCustomModelData(3)
        		.setDisplayName("§fWater Bottle")
        		.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS)
        		.setIdentifierKeys(drinkables_Key)
        		.setLore("§7Purified")
        		.build();
        ItemMeta purifiedMeta = PURIFIED_WATER.getItemMeta();
        ((PotionMeta) purifiedMeta).setBasePotionData(new PotionData(PotionType.WATER));
        ((PotionMeta) purifiedMeta).setColor(Color.AQUA);
        PURIFIED_WATER.setItemMeta(purifiedMeta);
        ALL_ITEMS.put("PURIFIED_WATER", PURIFIED_WATER);
        
        COLD_MILK = new CustomItem(Material.POTION)
        		.setCustomModelData(4)
        		.setDisplayName("§fCold Milk")
        		.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS)
        		.setIdentifierKeys(drinkables_Key)
        		.build();
        ItemMeta cold_milk_Meta = COLD_MILK.getItemMeta();
        ((PotionMeta) cold_milk_Meta).setBasePotionData(new PotionData(PotionType.WATER));
        ((PotionMeta) cold_milk_Meta).setColor(Color.fromBGR(16250871));
        COLD_MILK.setItemMeta(cold_milk_Meta);
        ALL_ITEMS.put("COLD_MILK", COLD_MILK);
        
        HOT_MILK = new CustomItem(Material.POTION)
        		.setCustomModelData(5)
        		.setDisplayName("§fHot Milk")
        		.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS)
        		.setIdentifierKeys(drinkables_Key)
        		.build();
        ItemMeta hot_milk_Meta = HOT_MILK.getItemMeta();
        ((PotionMeta) hot_milk_Meta).setBasePotionData(new PotionData(PotionType.WATER));
        ((PotionMeta) hot_milk_Meta).setColor(Color.fromBGR(15456977));
        HOT_MILK.setItemMeta(hot_milk_Meta);
        ALL_ITEMS.put("HOT_MILK", HOT_MILK);
        
        COFFEE = new CustomItem(Material.POTION)
        		.setCustomModelData(6)
        		.setDisplayName("§fCoffee")
        		.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS)
        		.setIdentifierKeys(drinkables_Key)
        		.build();
        ItemMeta coffee_meta = COFFEE.getItemMeta();
        ((PotionMeta) coffee_meta).setBasePotionData(new PotionData(PotionType.WATER));
        ((PotionMeta) coffee_meta).setColor(Color.OLIVE);
        COFFEE.setItemMeta(coffee_meta);
        ALL_ITEMS.put("COFFEE", COFFEE);
        
        GOLDEN_APPLE_JUICE = new CustomItem(Material.POTION)
        		.setCustomModelData(7)
        		.setDisplayName("§bGolden Apple Juice")
        		.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS)
        		.setIdentifierKeys(drinkables_Key)
        		.build();
        ItemMeta golden_apple_juice_meta = GOLDEN_APPLE_JUICE.getItemMeta();
        ((PotionMeta) golden_apple_juice_meta).setBasePotionData(new PotionData(PotionType.WATER));
        ((PotionMeta) golden_apple_juice_meta).setColor(Color.YELLOW);
        GOLDEN_APPLE_JUICE.setItemMeta(golden_apple_juice_meta);
        ALL_ITEMS.put("GOLDEN_APPLE_JUICE", GOLDEN_APPLE_JUICE);
        
        ENCHANTED_GOLDEN_APPLE_JUICE = new CustomItem(Material.POTION)
        		.setCustomModelData(8)
        		.setDisplayName("§dEnchanted Golden Apple Juice")
        		.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS)
        		.setIdentifierKeys(drinkables_Key)
        		.build();
        ItemMeta enchanted_golden_apple_juice_meta = ENCHANTED_GOLDEN_APPLE_JUICE.getItemMeta();
        ((PotionMeta) enchanted_golden_apple_juice_meta).setBasePotionData(new PotionData(PotionType.WATER));
        ((PotionMeta) enchanted_golden_apple_juice_meta).setColor(Color.YELLOW);
        ((PotionMeta) enchanted_golden_apple_juice_meta).addCustomEffect(new PotionEffect(PotionEffectType.REGENERATION, 400, 1), false);
        ENCHANTED_GOLDEN_APPLE_JUICE.setItemMeta(enchanted_golden_apple_juice_meta);
        ALL_ITEMS.put("ENCHANTED_GOLDEN_APPLE_JUICE", ENCHANTED_GOLDEN_APPLE_JUICE);
        
        //Water Bowl Immer als letztes einfügen
        WATER_BOWL = new CustomItem(Material.POTION)
        		.setCustomModelData(20)
        		.setDisplayName("§fWater Bowl")
        		.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS)
        		.setIdentifierKeys(drinkables_Key)
        		.build();
        ItemMeta water_bowl_meta = WATER_BOWL.getItemMeta();
        ((PotionMeta) water_bowl_meta).setBasePotionData(new PotionData(PotionType.WATER));
        WATER_BOWL.setItemMeta(water_bowl_meta);
        ALL_ITEMS.put("WATER_BOWL", WATER_BOWL);
        
        IRON_HAMMER = new CustomItem(Material.IRON_PICKAXE)
        		.setDisplayName("§fIron Hammer")
        		.setCustomModelData(1)
        		.setModifiers(Modifiers.MINI_EXCAVATOR)
        		.build();
		ALL_ITEMS.put("IRON_HAMMER", IRON_HAMMER);
		
		STORMLANDER = new CustomItem(Material.IRON_PICKAXE)
				.setDisplayName("§fStormlander")
        		.setCustomModelData(2)
        		.setModifiers(Modifiers.THUNDER_SHOT,Modifiers.MINI_EXCAVATOR)
        		.build();
		ALL_ITEMS.put("STORMLANDER", STORMLANDER);
		
		SUPER_AXE = new CustomItem(Material.DIAMOND_AXE)
				.setDisplayName("§bSuper Axe")
        		.setCustomModelData(1)
        		.setModifiers(Modifiers.MINI_EXCAVATOR)
        		.build();
		ALL_ITEMS.put("SUPER_AXE", SUPER_AXE);
		
		SUPER_AXE_NETHERITE = new CustomItem(Material.NETHERITE_AXE)
				.setDisplayName("§bSuper Axe")
        		.setCustomModelData(1)
        		.setModifiers(Modifiers.MINI_EXCAVATOR)
        		.build();;
		ALL_ITEMS.put("SUPER_AXE_NETHERITE", SUPER_AXE_NETHERITE);
		
		SUPER_SHOVEL = new CustomItem(Material.DIAMOND_SHOVEL)
				.setDisplayName("§bSuper Shovel")
				.setCustomModelData(1)
				.setModifiers(Modifiers.MINI_EXCAVATOR)
				.build();
		ALL_ITEMS.put("SUPER_SHOVEL", SUPER_SHOVEL);
		
		SUPER_SHOVEL_NETHERITE = new CustomItem(Material.NETHERITE_SHOVEL)
				.setDisplayName("§bSuper Shovel")
				.setCustomModelData(1)
				.setModifiers(Modifiers.MINI_EXCAVATOR)
				.build();
		ALL_ITEMS.put("SUPER_SHOVEL_NETHERITE", SUPER_SHOVEL_NETHERITE);
		
		BOLT = new CustomItem(Material.PAPER)
				.setCustomModelData(1)
				.build();
		
		return true;
		
	}
	public boolean loadRezepte() {
		//SmokingFurnace Rezepte
		SmokingRecipe smoking_clean_water = new SmokingRecipe(new NamespacedKey(MCReloaded.getPlugin(), "clean_water"), CLEAN_WATER,
				 new ExactChoice(DIRTY_WATER), 1, 100);
		Bukkit.getServer().addRecipe(smoking_clean_water);
	
		SmokingRecipe smoking_hot_milk = new SmokingRecipe(new NamespacedKey(MCReloaded.getPlugin(), "hot_milk"), HOT_MILK,
	                new ExactChoice(COLD_MILK), 1, 100);
		Bukkit.getServer().addRecipe(smoking_hot_milk);		
		
		//Furnace Rezepte
		
		FurnaceRecipe furnace_clean_water = new FurnaceRecipe(new NamespacedKey(MCReloaded.getPlugin(), "clean_water"), CLEAN_WATER,
				new ExactChoice(DIRTY_WATER), 2, 200);
		Bukkit.getServer().addRecipe(furnace_clean_water);	
		
		FurnaceRecipe furnace_hot_milk = new FurnaceRecipe(new NamespacedKey(MCReloaded.getPlugin(), "hot_milk"), HOT_MILK,
				new ExactChoice(COLD_MILK), 2, 200);
		Bukkit.getServer().addRecipe(furnace_hot_milk);	
		//}
		//Smithing Rezepte
		//*******************************************************************
		
		SmithingRecipe guilded_netherite_helmet = new SmithingRecipe(new NamespacedKey(MCReloaded.getPlugin(), "guilded_netherite_helmet"), new ItemStack(Material.NETHERITE_HELMET), new MaterialChoice(Material.NETHERITE_HELMET), new MaterialChoice(Material.NETHERITE_INGOT));
		Bukkit.getServer().addRecipe(guilded_netherite_helmet);	
		//*******************************************************************
		
		SmithingRecipe guilded_netherite_chestplate = new SmithingRecipe(new NamespacedKey(MCReloaded.getPlugin(), "guilded_netherite_chestplate"), new ItemStack(Material.NETHERITE_CHESTPLATE), new MaterialChoice(Material.NETHERITE_CHESTPLATE), new MaterialChoice(Material.NETHERITE_INGOT));
		Bukkit.getServer().addRecipe(guilded_netherite_chestplate);	
		//*******************************************************************
		
		SmithingRecipe guilded_netherite_leggings = new SmithingRecipe(new NamespacedKey(MCReloaded.getPlugin(), "guilded_netherite_leggings"), new ItemStack(Material.NETHERITE_LEGGINGS), new MaterialChoice(Material.NETHERITE_LEGGINGS), new MaterialChoice(Material.NETHERITE_INGOT));
		Bukkit.getServer().addRecipe(guilded_netherite_leggings);	
		//*******************************************************************
		
		SmithingRecipe guilded_netherite_boots = new SmithingRecipe(new NamespacedKey(MCReloaded.getPlugin(), "guilded_netherite_boots"), new ItemStack(Material.NETHERITE_BOOTS), new MaterialChoice(Material.NETHERITE_BOOTS), new MaterialChoice(Material.NETHERITE_INGOT));
		Bukkit.getServer().addRecipe(guilded_netherite_boots);	
		//*******************************************************************
		
		if(config.MECHANICS_ENERGY_COFFEE) {
			ItemStack coffee_1 = new ItemStack(COFFEE);
			coffee_1.setAmount(2);
			
			ShapelessRecipe coffee = new ShapelessRecipe(new NamespacedKey(MCReloaded.getPlugin(), "coffee"), coffee_1);
			
			coffee.addIngredient(new ExactChoice(COFFEE_BEAN));
			coffee.addIngredient(new ExactChoice(PURIFIED_WATER));
			coffee.addIngredient(new ExactChoice(HOT_MILK));
	        Bukkit.getServer().addRecipe(coffee);		
		}
		//*******************************************************************
		
		ShapelessRecipe golden_apple_juice = new ShapelessRecipe(new NamespacedKey(MCReloaded.getPlugin(), "golden_apple_juice"), GOLDEN_APPLE_JUICE);
		
		golden_apple_juice.addIngredient(Material.GLASS_BOTTLE);
		golden_apple_juice.addIngredient(Material.GOLDEN_APPLE);
        Bukkit.getServer().addRecipe(golden_apple_juice);		
        //*******************************************************************
        
        ShapelessRecipe enchanted_golden_apple_juice = new ShapelessRecipe(new NamespacedKey(MCReloaded.getPlugin(), "enchanted_golden_apple_juice"), ENCHANTED_GOLDEN_APPLE_JUICE);
		
        enchanted_golden_apple_juice.addIngredient(Material.GLASS_BOTTLE);
        enchanted_golden_apple_juice.addIngredient(Material.ENCHANTED_GOLDEN_APPLE);
        Bukkit.getServer().addRecipe(enchanted_golden_apple_juice);		
        //*******************************************************************
        
        ShapelessRecipe golderite_ingot = new ShapelessRecipe(new NamespacedKey(MCReloaded.getPlugin(), "golderite_ingot"), GOLDERITE_INGOT);
		
        golderite_ingot.addIngredient(Material.NETHERITE_INGOT);
        golderite_ingot.addIngredient(Material.GOLD_INGOT);
        golderite_ingot.addIngredient(Material.GOLD_INGOT);
        golderite_ingot.addIngredient(Material.GOLD_INGOT);
        golderite_ingot.addIngredient(Material.GOLD_INGOT);
        Bukkit.getServer().addRecipe(golderite_ingot);		
        
        //*******************************************************************
        ShapedRecipe Super_Axe = new ShapedRecipe(new NamespacedKey(MCReloaded.getPlugin(), "super_axe"),SUPER_AXE);
        Super_Axe.shape("LL", "LN", " N");
		Super_Axe.setIngredient('N', Material.STICK);
		Super_Axe.setIngredient('L', Material.DIAMOND_BLOCK);
		Bukkit.getServer().addRecipe(Super_Axe);		
		
		//*******************************************************************
        ItemStack Heart_of_the_mine1 = new ItemStack(HEART_OF_THE_MINE);
        Heart_of_the_mine1.setAmount(1);
		
		ShapelessRecipe Heart_of_the_mine = new ShapelessRecipe(new NamespacedKey(MCReloaded.getPlugin(), "heart_of_the_mine"), Heart_of_the_mine1);
		
		Heart_of_the_mine.addIngredient(Material.EMERALD_BLOCK);
		Heart_of_the_mine.addIngredient(Material.DIAMOND_BLOCK);
		Heart_of_the_mine.addIngredient(Material.LAPIS_BLOCK);
		Heart_of_the_mine.addIngredient(Material.QUARTZ_BLOCK);
		Heart_of_the_mine.addIngredient(Material.ANCIENT_DEBRIS);
		Heart_of_the_mine.addIngredient(Material.IRON_BLOCK);
		Heart_of_the_mine.addIngredient(Material.GOLD_BLOCK);
		Heart_of_the_mine.addIngredient(Material.COAL_BLOCK);
		Heart_of_the_mine.addIngredient(Material.REDSTONE_BLOCK);
        Bukkit.getServer().addRecipe(Heart_of_the_mine);		
        
		//*******************************************************************		
		ShapedRecipe grappling_hook = new ShapedRecipe(new NamespacedKey(MCReloaded.getPlugin(), "grappling_hook"), GRAPPLING_HOOK);

        grappling_hook.shape(" 3 ", "121", " 3 ");
        grappling_hook.setIngredient('1', Material.FISHING_ROD);
        grappling_hook.setIngredient('2', Material.STRING);
        grappling_hook.setIngredient('3', Material.IRON_INGOT);
        Bukkit.getServer().addRecipe(grappling_hook);		
        
        //*******************************************************************
        ShapedRecipe warp_fuel = new ShapedRecipe(new NamespacedKey(MCReloaded.getPlugin(), "warp_fuel"), WARP_FUEL);

        warp_fuel.shape("121", "232", "121");
        warp_fuel.setIngredient('1', Material.BLAZE_POWDER);
        warp_fuel.setIngredient('2', Material.REDSTONE);
        warp_fuel.setIngredient('3', Material.QUARTZ);
        Bukkit.getServer().addRecipe(warp_fuel);		
        
        //*******************************************************************
        ShapedRecipe warp_crystal = new ShapedRecipe(new NamespacedKey(MCReloaded.getPlugin(), "warp_crystal"), WARP_CRYSTAL);

        warp_crystal.shape("121", "343", "565");
        warp_crystal.setIngredient('1', Material.MAGMA_CREAM);
        warp_crystal.setIngredient('2', Material.MAP);
        warp_crystal.setIngredient('3', Material.END_CRYSTAL);
        warp_crystal.setIngredient('4', Material.ENDER_PEARL);
        warp_crystal.setIngredient('5', Material.OBSIDIAN);
        warp_crystal.setIngredient('6', Material.DIAMOND);
        Bukkit.getServer().addRecipe(warp_crystal);		
        
        //*******************************************************************
        ShapedRecipe XP_Bottle = new ShapedRecipe(new NamespacedKey(MCReloaded.getPlugin(), "experience_bottle"), new ItemStack(Material.EXPERIENCE_BOTTLE, 1));

        XP_Bottle.shape(" 2 ", "212","222");
        XP_Bottle.setIngredient('1', Material.GLASS_BOTTLE);
        XP_Bottle.setIngredient('2', Material.LAPIS_LAZULI);
        Bukkit.getServer().addRecipe(XP_Bottle);		        
        
        //*******************************************************************
        ShapedRecipe Grand_XP_Bottle = new ShapedRecipe(new NamespacedKey(MCReloaded.getPlugin(), "grand_experience_bottle"), GRAND_EXPERIENCE_BOTTLE);

        Grand_XP_Bottle.shape(" 2 ", "212","222");
        Grand_XP_Bottle.setIngredient('1', Material.GLASS_BOTTLE);
        Grand_XP_Bottle.setIngredient('2', Material.LAPIS_BLOCK);
        Bukkit.getServer().addRecipe(Grand_XP_Bottle);		        
              
		//*******************************************************************
        ShapedRecipe portable_enderchest = new ShapedRecipe(new NamespacedKey(MCReloaded.getPlugin(), "portable_enderchest"), PORTABLE_ENDERCHEST);

        portable_enderchest.shape("131", "121", "131");
        portable_enderchest.setIngredient('1', Material.LEATHER);
        portable_enderchest.setIngredient('2', Material.ENDER_CHEST);
        portable_enderchest.setIngredient('3', Material.STICK);
        Bukkit.getServer().addRecipe(portable_enderchest);		
	
		//*******************************************************************
		
		ShapedRecipe Invisible_Itemframe = new ShapedRecipe(new NamespacedKey(MCReloaded.getPlugin(), "invisible_item_frame"), INVISIBLE_ITEM_FRAME);
		Invisible_Itemframe.shape("NNN", "NLN", "NNN");
		Invisible_Itemframe.setIngredient('N', Material.STICK);
		Invisible_Itemframe.setIngredient('L', Material.GLASS_PANE);
		Bukkit.getServer().addRecipe(Invisible_Itemframe);		
		
		//*******************************************************************
		ShapelessRecipe Invisible_GlowItemframe = new ShapelessRecipe(new NamespacedKey(MCReloaded.getPlugin(), "invisible_glow_item_frame"), INVISIBLE_GLOW_ITEM_FRAME);
		
		Invisible_GlowItemframe.addIngredient(new ExactChoice(INVISIBLE_ITEM_FRAME));
		Invisible_GlowItemframe.addIngredient(Material.GLOW_INK_SAC);
        Bukkit.getServer().addRecipe(Invisible_GlowItemframe);		
        
		//*******************************************************************
		
		ShapedRecipe ironhammer = new ShapedRecipe(new NamespacedKey(MCReloaded.getPlugin(), "iron_hammer"), IRON_HAMMER);
		ironhammer.shape("LLL", " N ", " N ");
		ironhammer.setIngredient('N', Material.STICK);
		ironhammer.setIngredient('L', Material.IRON_BLOCK);
		Bukkit.getServer().addRecipe(ironhammer);		
		//*******************************************************************
		
		ShapedRecipe Granite_Pickaxe = new ShapedRecipe(new NamespacedKey(MCReloaded.getPlugin(), "granite_pickaxe"), GRANITE_PICKAXE);
		Granite_Pickaxe.shape("LLL", " N ", " N ");
		Granite_Pickaxe.setIngredient('N', Material.STICK);
		Granite_Pickaxe.setIngredient('L', Material.GRANITE);
		Bukkit.getServer().addRecipe(Granite_Pickaxe);		
		//*******************************************************************
		
		ShapedRecipe Diorite_Pickaxe = new ShapedRecipe(new NamespacedKey(MCReloaded.getPlugin(), "diorite_pickaxe"), DIORITE_PICKAXE);
		Diorite_Pickaxe.shape("LLL", " N ", " N ");
		Diorite_Pickaxe.setIngredient('N', Material.STICK);
		Diorite_Pickaxe.setIngredient('L', Material.DIORITE);
		Bukkit.getServer().addRecipe(Diorite_Pickaxe);		
		//*******************************************************************
		
		ShapedRecipe Andesite_Pickaxe = new ShapedRecipe(new NamespacedKey(MCReloaded.getPlugin(), "andesite_pickaxe"), ANDESITE_PICKAXE);
		Andesite_Pickaxe.shape("LLL", " N ", " N ");
		Andesite_Pickaxe.setIngredient('N', Material.STICK);
		Andesite_Pickaxe.setIngredient('L', Material.ANDESITE);
		Bukkit.getServer().addRecipe(Andesite_Pickaxe);		
		//*******************************************************************
		
		ShapedRecipe Super_Shovel = new ShapedRecipe(new NamespacedKey(MCReloaded.getPlugin(), "super_shovel"), SUPER_SHOVEL);
		Super_Shovel.shape("L", "N", "N");
		Super_Shovel.setIngredient('N', Material.STICK);
		Super_Shovel.setIngredient('L', Material.DIAMOND_BLOCK);
		Bukkit.getServer().addRecipe(Super_Shovel);		
		//*******************************************************************
		
		ShapedRecipe Portable_Workbench = new ShapedRecipe(new NamespacedKey(MCReloaded.getPlugin(), "portable_crafting_table"), PORTABLE_CRAFTING_TABLE);
		Portable_Workbench.shape("131", "121", "131");
		Portable_Workbench.setIngredient('1', Material.LEATHER);
		Portable_Workbench.setIngredient('2', Material.CRAFTING_TABLE);
		Portable_Workbench.setIngredient('3', Material.STICK);
		Bukkit.getServer().addRecipe(Portable_Workbench);
		//*******************************************************************
		ItemStack SpectralArrow = new ItemStack(Material.SPECTRAL_ARROW);
		SpectralArrow.setAmount(1);
		
		ShapedRecipe SpectralArrow1 = new ShapedRecipe(new NamespacedKey(MCReloaded.getPlugin(), "spectral_arrow"), SpectralArrow);
		SpectralArrow1.shape(" B ", "BNB", " B ");
		SpectralArrow1.setIngredient('B', Material.GLOWSTONE_DUST);
		SpectralArrow1.setIngredient('N', Material.ARROW);
		Bukkit.getServer().addRecipe(SpectralArrow1);
		//*******************************************************************
		ItemStack Saddle = new ItemStack(Material.SADDLE);
		Saddle.setAmount(1);
		
		ShapedRecipe Saddle1 = new ShapedRecipe(new NamespacedKey(MCReloaded.getPlugin(), "saddle"), Saddle);
		Saddle1.shape("FFF", "BNB", "H H");
		Saddle1.setIngredient('F', Material.LEATHER);
		Saddle1.setIngredient('B', Material.LEAD);
		Saddle1.setIngredient('N', Material.IRON_INGOT);
		Saddle1.setIngredient('H', Material.IRON_NUGGET);
		Bukkit.getServer().addRecipe(Saddle1);
		//*******************************************************************
		ItemStack LeatherHorseArmor = new ItemStack(Material.LEATHER_HORSE_ARMOR);
		LeatherHorseArmor.setAmount(1);
		
		ShapedRecipe LeatherHorseArmor1 = new ShapedRecipe(new NamespacedKey(MCReloaded.getPlugin(), "leather_horse_armor"),LeatherHorseArmor);
		LeatherHorseArmor1.shape("  F", "BNB", "B B");
		LeatherHorseArmor1.setIngredient('F', Material.LEATHER_HELMET);
		LeatherHorseArmor1.setIngredient('B', Material.LEATHER);
		LeatherHorseArmor1.setIngredient('N', Material.SADDLE);
		Bukkit.getServer().addRecipe(LeatherHorseArmor1);
		//*******************************************************************
		ItemStack IronHorseArmor = new ItemStack(Material.IRON_HORSE_ARMOR);
		IronHorseArmor.setAmount(1);
		
		ShapedRecipe IronHorseArmor1 = new ShapedRecipe(new NamespacedKey(MCReloaded.getPlugin(), "iron_horse_armor"),IronHorseArmor);
		IronHorseArmor1.shape("  F", "NBN", "H H");
		IronHorseArmor1.setIngredient('F', Material.IRON_INGOT);
		IronHorseArmor1.setIngredient('B', Material.LEATHER_HORSE_ARMOR);
		IronHorseArmor1.setIngredient('N', Material.IRON_BLOCK);
		IronHorseArmor1.setIngredient('H', Material.IRON_NUGGET);
		Bukkit.getServer().addRecipe(IronHorseArmor1);
		//*******************************************************************
		ItemStack DiamondHorseArmor = new ItemStack(Material.DIAMOND_HORSE_ARMOR);
		DiamondHorseArmor.setAmount(1);
		
		ShapedRecipe DiamondHorseArmor1 = new ShapedRecipe(new NamespacedKey(MCReloaded.getPlugin(), "diamond_horse_armor"),DiamondHorseArmor);
		DiamondHorseArmor1.shape("  F", "NBN", "H H");
		DiamondHorseArmor1.setIngredient('F', Material.DIAMOND_HELMET);
		DiamondHorseArmor1.setIngredient('B', Material.IRON_HORSE_ARMOR);
		DiamondHorseArmor1.setIngredient('N', Material.DIAMOND);
		DiamondHorseArmor1.setIngredient('H', Material.DIAMOND_BOOTS);
		Bukkit.getServer().addRecipe(DiamondHorseArmor1);
		//*******************************************************************
		ItemStack GoldenHorseArmor = new ItemStack(Material.GOLDEN_HORSE_ARMOR);
		GoldenHorseArmor.setAmount(1);
		
		ShapedRecipe GoldenHorseArmor1 = new ShapedRecipe(new NamespacedKey(MCReloaded.getPlugin(), "golden_horse_armor"),GoldenHorseArmor);
		GoldenHorseArmor1.shape("  F", "NBN", "H H");
		GoldenHorseArmor1.setIngredient('F', Material.GOLD_INGOT);
		GoldenHorseArmor1.setIngredient('B', Material.LEATHER_HORSE_ARMOR);
		GoldenHorseArmor1.setIngredient('N', Material.GOLD_BLOCK);
		GoldenHorseArmor1.setIngredient('H', Material.GOLD_NUGGET);
		Bukkit.getServer().addRecipe(GoldenHorseArmor1);
		//*******************************************************************
		ShapelessRecipe gravel = new ShapelessRecipe(new NamespacedKey(MCReloaded.getPlugin(), "gravel"),new ItemStack(Material.GRAVEL,4));
		
		gravel.addIngredient(new RecipeChoice.MaterialChoice(Tag.SAND));
		gravel.addIngredient(Material.COBBLESTONE);
		gravel.addIngredient(new RecipeChoice.MaterialChoice(Tag.SAND));
		gravel.addIngredient(Material.COBBLESTONE);
		Bukkit.getServer().addRecipe(gravel);
		//*******************************************************************
		ShapedRecipe cookie = new ShapedRecipe(new NamespacedKey(MCReloaded.getPlugin(), "cookie"),new ItemStack(Material.COOKIE,8));
		
		cookie.shape(" F ", "BCB", " S ");
		cookie.setIngredient('F', Material.EGG);
		cookie.setIngredient('B', Material.WHEAT);
		cookie.setIngredient('C', Material.COCOA_BEANS);
		cookie.setIngredient('S', Material.SUGAR);
		Bukkit.getServer().addRecipe(cookie);
		//*******************************************************************	
		ShapedRecipe stone = new ShapedRecipe(new NamespacedKey(MCReloaded.getPlugin(), "stone"),new ItemStack(Material.STONE, 8));
		
		stone.shape("SSS", "SCS", "SSS");
		stone.setIngredient('C', Material.COAL);
		stone.setIngredient('S', Material.COBBLESTONE);
		Bukkit.getServer().addRecipe(stone);
		//*******************************************************************		
		ShapelessRecipe flint = new ShapelessRecipe(new NamespacedKey(MCReloaded.getPlugin(), "flint"), new ItemStack(Material.FLINT, 1));
		
		flint.addIngredient(Material.GRAVEL);
		Bukkit.getServer().addRecipe(flint);
		//*******************************************************************
		ShapedRecipe nametag = new ShapedRecipe(new NamespacedKey(MCReloaded.getPlugin(), "nametag"), new ItemStack(Material.NAME_TAG, 1));

	    nametag.shape(" -@", " *-", "*  ");
	    nametag.setIngredient('@', Material.STRING);
	    nametag.setIngredient('-', Material.IRON_INGOT);
	    nametag.setIngredient('*', Material.PAPER);
	    Bukkit.getServer().addRecipe(nametag);
        //*******************************************************************
        ShapedRecipe ice = new ShapedRecipe(new NamespacedKey(MCReloaded.getPlugin(), "ice1"), new ItemStack(Material.ICE, 1));
        ShapelessRecipe ice2 = new ShapelessRecipe(new NamespacedKey(MCReloaded.getPlugin(), "ice2"), new ItemStack(Material.ICE, 4));

        ice.shape("@@@", "@*@", "@@@");

        ice.setIngredient('@', Material.SNOWBALL);
        ice.setIngredient('*', Material.WATER_BUCKET);

        ice2.addIngredient(Material.PACKED_ICE);
        Bukkit.getServer().addRecipe(ice);
      
        return true;
	}
	
    public enum Tags {
    	DRINKABLE(DIRTY_WATER, CLEAN_WATER, PURIFIED_WATER, WATER_BOWL, COLD_MILK, HOT_MILK, COFFEE,
    			ENCHANTED_GOLDEN_APPLE_JUICE, GOLDEN_APPLE_JUICE),
       
    	WATER_BOTTLE(DIRTY_WATER, CLEAN_WATER, PURIFIED_WATER),
    	
    	BREWINGSTAND_BLOCKED(COFFEE, COLD_MILK, HOT_MILK, WATER_BOWL, ENCHANTED_GOLDEN_APPLE_JUICE, GOLDEN_APPLE_JUICE),
    	
    	GRINDSTONE_BLOCKED(DIRTY_WATER, CLEAN_WATER, PURIFIED_WATER, WATER_BOWL, COLD_MILK, HOT_MILK,
    			COFFEE, GRAPPLING_HOOK, HEART_OF_THE_MINE, PORTABLE_CRAFTING_TABLE, INVISIBLE_ITEM_FRAME, INVISIBLE_GLOW_ITEM_FRAME,
    			ENCHANTED_GOLDEN_APPLE_JUICE, GOLDEN_APPLE_JUICE, GOLDERITE_INGOT, WARP_FUEL),
    	
    	ANVIL_BLOCKED(HEART_OF_THE_MINE, DIRTY_WATER, CLEAN_WATER, PURIFIED_WATER, WATER_BOWL,
    			COLD_MILK, HOT_MILK, COFFEE, COFFEE_BEAN, WARP_CRYSTAL, INVISIBLE_GLOW_ITEM_FRAME,
    			ENCHANTED_GOLDEN_APPLE_JUICE, GOLDEN_APPLE_JUICE, GOLDERITE_INGOT, INVISIBLE_ITEM_FRAME),
    	
    	PLAYER_HEAD(PORTABLE_CRAFTING_TABLE, PORTABLE_ENDERCHEST, WARP_CRYSTAL),
    	
    	PLACEABLE_BLOCKED(PORTABLE_CRAFTING_TABLE, PORTABLE_ENDERCHEST, WARP_CRYSTAL, COFFEE_BEAN),
    	
    	NETHERITE_INGOT(GOLDERITE_INGOT);
    	
        private final ItemStack[] items;

        Tags(ItemStack... items) {
            this.items = items;
        }

        /**
         * Get all items tagged in this group
         *
         * @return All items tagged in this group
         */
        public ItemStack[] getItems() {
            return items;
        }

        /**
         * Check if an ItemStack is tagged in a group of custom {@link Item}
         *
         * @param item ItemStack to check
         * @return True if item matches tag
         */
        public boolean isTagged(ItemStack item) {
        	if(item != null) {
	        	for(ItemStack itemstack : items) {
	        			if(item.getType().equals(itemstack.getType())) {
			        		if(isSimilar(itemstack, item)) {
			        			return true;
			        		}else if((itemstack.getItemMeta().getPersistentDataContainer() != null) ? itemstack.getItemMeta().getPersistentDataContainer() == item.getItemMeta().getPersistentDataContainer() : false) {
			        			return true;
			        		}
	        			}
	        	}
        	}
            return false;
        }

    }

	 /**
     * Enums of all custom recipes
     */
    public enum Recipes {
        // CUSTOM TOOLS/ITEMS
    	PORTABLE_CRAFTING_TABLE("portable_crafting_table"),
    	PORTABLE_ENDERCHEST("portable_enderchest"),
    	IRON_HAMMER("iron_hammer"),
    	SUPER_AXE("super_axe"),
    	SUPER_SHOVEL("super_shovel"),
    	INVISIBLE_ITEM_FRAME("invisible_item_frame"),
    	GRAPPLING_HOOK("grappling_hook"),
    	COFFEE("coffee"),
    	COLD_MILK("cold_milk"),
    	HOT_MILK("hot_milk"),
    	HEART_OF_THE_MINE("heart_of_the_mine"),
    	GRAND_EXPERIENCE_BOTTLE("grand_experience_bottle"),
    	GRANITE_PICKAXE("granite_pickaxe"),
    	ANDESITE_PICKAXE("andesite_pickaxe"),
    	DIORITE_PICKAXE("diorite_pickaxe"),
    	GOLDEN_APPLE_JUICE("golden_apple_juice"),
    	ENCHANTED_GOLDEN_APPLE_JUICE("enchanted_golden_apple_juice"),
    	GOLDERITE_INGOT("golderite_ingot"),
    	WARP_FUEL("warp_fuel"),
    	WARP_CRYSTAL("warp_crystal"),
    	INVISIBLE_GLOW_ITEM_FRAME("invisible_glow_item_frame"),
    	
    	// CUSTOM ARMOR
    	
    	
        // VANILLA ITEMS
    	EXPERIENCE_BOTTLE("experience_bottle"),
    	COOKIE("cookie"),
    	SPECTRAL_ARROW("spectral_arrow"),
    	SADDLE("saddle"),
    	LEATHER_HORSE_ARMOR("leather_horse_armor"),
    	GOLDEN_HORSE_ARMOR("golden_horse_armor"),
    	IRON_HORSE_ARMOR("iron_horse_armor"),
    	DIAMOND_HORSE_ARMOR("diamond_horse_armor"),
    	FLINT("flint"),
    	NAME_TAG("nametag"),
    	BREAD("bread"),
    	
        // VANILLA BLOCKS
    	STONE("stone"),
    	GRAVEL("gravel"),
    	CHEST("chest"),
    	ICE("ice1","ice2");
    	
        // REPAIR RECIPES


        // SMELTING RECIPES


        private final Collection<NamespacedKey> keys;
        private static final Collection<NamespacedKey> allKeys;

        static {
            allKeys = new ArrayList<>();
            for (Recipes recipes : values()) {
                allKeys.addAll(recipes.keys);
            }
        }

        Recipes(String... keys) {
            ArrayList<NamespacedKey> list = new ArrayList<>();
            for (String key : keys) {
                assert false;
                list.add(Utils.getNamespacedKey(key));
            }
            this.keys = list;
        }

        /**
         * Get the {@link NamespacedKey}s for this recipe
         *
         * @return NamespacedKeys for this recipe
         */
        public Collection<NamespacedKey> getKeys() {
            return this.keys;
        }

        @SuppressWarnings("unused")
		private static Collection<NamespacedKey> getAllKeys() {
            return allKeys;
        }
    }
	
    public enum Modifiers {
        PIGLIN_PASSIVE("golderite_armor","§6Piglin §7become §6Passive§7.",true),
        THUNDER_SHOT("stormlander","§7You can shoot §6Thunderbolts§7.",false),
        MINI_EXCAVATOR("mini_excavator","§7You can mine §63x3§7 blocks at one time.",false);
    	NamespacedKey key;
    	Boolean isSetBonus;
    	String description;
    	Modifiers(String key,String description,Boolean isSetBonus){
    		this.key = new NamespacedKey(MCReloaded.getPlugin(), key);
    		this.isSetBonus = isSetBonus;
    		this.description = description;
    	}
    	public NamespacedKey getKey() {
    		return key;
    	}
    	public boolean isSetBonus() {
    		return isSetBonus;
    	}
    	public String getDescription() {
    		return description;
    	}
    }
	/*
	public static boolean compare(ItemStack itemStack, Item type) {
        if (itemStack.getType() == type.getMaterialType()) {
            if (itemStack.getItemMeta() != null && itemStack.getItemMeta().hasCustomModelData()) {
                return itemStack.getItemMeta().getCustomModelData() == type.getModelData();
            } else {
                return type.getModelData() == 0;
            }
        }
        return false;
    }
	 public static boolean compare(ItemStack itemStack, ItemStack... type) {
	        for (ItemStack item : type) {
	            if (compare(itemStack, item)) {
	                return true;
	            }
	        }
	        return true;
	    }
	 
	 */
	public static ItemStack valueOf(String value) {
		if (ALL_ITEMS.containsKey(value.toUpperCase())) {
	    	return ALL_ITEMS.get(value.toUpperCase());
		}
		return null;
	}
	 
	public static Collection<ItemStack> values() {
		return ALL_ITEMS.values();
	}
	
	public static Collection<String> valuesString() {
		return ALL_ITEMS.keySet();
	}
	
	public static boolean isSimilar(ItemStack item1, ItemStack item2) {
		if (item2.getType() == item1.getType()) {
			ItemMeta item1Meta = item1.getItemMeta();
			ItemMeta item2Meta = item2.getItemMeta();
			if (item1Meta.hasDisplayName() != item2Meta.hasDisplayName()) {
	        	return false;
	    	}      
	    	if (item1Meta.hasDisplayName()) {
	        	if (!item1Meta.getDisplayName().equals(item2Meta.getDisplayName())) {
	            	return false;
	        	}
	    	}     
	        if (item1Meta.hasLore() != item2Meta.hasLore()) {
	            return false;
	        }
	        if (item1Meta.hasLore()) {
	            if (item1Meta.getLore().size() != item2Meta.getLore().size()) {
	            	return false;
	            }
	        	for (int index = 0; index < item1Meta.getLore().size(); index++) {
	            	if (item1Meta.getLore().get(index).equals(item2Meta.getLore().get(index))) {
	                	return false;
	            	}
	        	}
	        }
	        if (item1Meta.hasEnchants() != item2Meta.hasEnchants()) {
	        	return false;
	        }
	            
	        if (item1Meta.hasEnchants()) {
	        	if (item1Meta.getEnchants().size() != item2Meta.getEnchants().size()) {
	            	return false;
	        	}
	        	for (Entry<Enchantment, Integer> enchantInfo : item1Meta.getEnchants().entrySet()) {
	            	if (item1Meta.getEnchantLevel(enchantInfo.getKey()) != item2Meta.getEnchantLevel(enchantInfo.getKey())) {
	                	return false;
	            	}
	            }
	        }
	            
	            if (item1Meta.getItemFlags().size() != item2Meta.getItemFlags().size()) {
	                return false;
	            }
      
	            for (ItemFlag flag : item1Meta.getItemFlags()) {
	                if (!item2Meta.hasItemFlag(flag)) {
	                    return false;
	                }
	            }
	            return true;
	        }
	        return false;
	    }
	
	
	public static void debugInventory(Player p) {
		for(ItemStack item : p.getInventory().getContents()) {
			if(item != null) {
				if(item.getType().equals(Material.PLAYER_HEAD)) {
					for(ItemStack checkItem : Tags.PLAYER_HEAD.items) {
						if(isSimilar(checkItem, item)) {
							if(!item.getItemMeta().equals(checkItem.getItemMeta())) {
								item.setItemMeta(checkItem.getItemMeta());
							}
						} 
					}
				}
			}
		} 
	}
	public static ItemStack compareEnchantsToOtherItem(ItemStack item1, ItemStack item2) {
		Map<Enchantment, Integer> e1 = item1.getItemMeta().getEnchants();
		ItemMeta item2meta = item2.getItemMeta();
		for(Map.Entry<Enchantment, Integer> entry : e1.entrySet()) {
			item2meta.addEnchant(entry.getKey(), entry.getValue(), true);
		}
		item2.setItemMeta(item2meta);
		return item2;	 
	}
	
	public static boolean hasModifier(Modifiers modifier, ItemStack itemstack) {
		if(itemstack.getItemMeta().getPersistentDataContainer().has(modifier.getKey(), PersistentDataType.BYTE)) {
			return true;
		}
		return false;
	}
}
