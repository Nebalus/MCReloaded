package de.pixelstudios.manager;

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
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.inventory.RecipeChoice.ExactChoice;
import org.bukkit.inventory.RecipeChoice.MaterialChoice;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import de.pixelstudios.ConsoleLogger;
import de.pixelstudios.MCReloaded;
import de.pixelstudios.datamanagement.Config;
import de.pixelstudios.items.manager.GUIicons;
import de.pixelstudios.items.manager.HeadList;
import de.pixelstudios.messaging.MessageFormatter;
import de.pixelstudios.utils.Utils;

@SuppressWarnings("deprecation")
public class ItemManager {
	private MessageFormatter messageFormatter;
	private Config config;
	
	public ItemManager(MCReloaded plugin) {
		this.messageFormatter = plugin.getMessageFormatter();
		this.config = plugin.getMCReloadedConfig();
	}
	
	public static List<Material> blockedMaterial = new ArrayList<Material>();
	
	private static Map<String, ItemStack> ALL_ITEMS = new HashMap<>();

	
    public static NamespacedKey invisible_item_frame_Key = new NamespacedKey(MCReloaded.getPlugin(), "invisible_item_frame");
    public static NamespacedKey super_pickaxe_Key = new NamespacedKey(MCReloaded.getPlugin(), "super_pickaxe");
    public static NamespacedKey super_shovel_Key = new NamespacedKey(MCReloaded.getPlugin(), "super_shovel");
    public static NamespacedKey super_axe_Key = new NamespacedKey(MCReloaded.getPlugin(), "super_axe");
    public static NamespacedKey portable_crafting_table_Key = new NamespacedKey(MCReloaded.getPlugin(), "portable_crafting_table");
    public static NamespacedKey portable_enderchest_Key = new NamespacedKey(MCReloaded.getPlugin(), "portable_enderchest");
    public static NamespacedKey grappling_hook_Key = new NamespacedKey(MCReloaded.getPlugin(), "grappling_hook");
    public static NamespacedKey drinkables_Key = new NamespacedKey(MCReloaded.getPlugin(), "drinks");
    public static NamespacedKey coffee_bean_Key = new NamespacedKey(MCReloaded.getPlugin(), "coffee_bean");
    public static NamespacedKey heart_of_the_mine_Key = new NamespacedKey(MCReloaded.getPlugin(), "heart_of_the_mine");
    public static NamespacedKey grand_experience_bottle_Key = new NamespacedKey(MCReloaded.getPlugin(), "grand_experience_bottle");
    public static NamespacedKey experience_obelisk_Key = new NamespacedKey(MCReloaded.getPlugin(), "experience_obelisk");
    public static NamespacedKey experience_obelisk_I_Key = new NamespacedKey(MCReloaded.getPlugin(), "experience_obelisk_I");
    public static NamespacedKey experience_obelisk_II_Key = new NamespacedKey(MCReloaded.getPlugin(), "experience_obelisk_II");
    public static NamespacedKey experience_obelisk_III_Key = new NamespacedKey(MCReloaded.getPlugin(), "experience_obelisk_III");
    public static NamespacedKey experience_obelisk_IV_Key = new NamespacedKey(MCReloaded.getPlugin(), "experience_obelisk_IV");
    public static NamespacedKey experience_orb_Key = new NamespacedKey(MCReloaded.getPlugin(), "experience_orb");
    public static NamespacedKey cristal_fragment_Key = new NamespacedKey(MCReloaded.getPlugin(), "crystal_fragment");
 
    public static NamespacedKey cristal_armor_Key = new NamespacedKey(MCReloaded.getPlugin(), "cristal_armor");
    public static NamespacedKey gilded_netherite_armor_Key = new NamespacedKey(MCReloaded.getPlugin(), "gilded_netherite_armor");
	
    //*************************************************************
    public static NamespacedKey telekinesis_enchantment = new NamespacedKey(MCReloaded.getPlugin(), "ENCHANTMENT_TELEKINESIS");
    public static NamespacedKey smelting_touch_enchantment = new NamespacedKey(MCReloaded.getPlugin(), "ENCHANTMENT_SMELTING_TOUCH");
    
    //*************************************************************
	public static ItemStack PORTABLE_CRAFTING_TABLE;
	public static ItemStack PORTABLE_ENDERCHEST;
	public static ItemStack INVISIBLE_ITEM_FRAME;
	public static ItemStack GRAPPLING_HOOK;
	public static ItemStack HEART_OF_THE_MINE;
	
	public static ItemStack ANDESITE_PICKAXE;
	public static ItemStack DIORITE_PICKAXE;
	public static ItemStack GRANITE_PICKAXE;
	
	public static ItemStack SUPER_PICKAXE;
	public static ItemStack SUPER_PICKAXE_NETHERITE;
	
	public static ItemStack SUPER_AXE;
	public static ItemStack SUPER_AXE_NETHERITE;
	
	public static ItemStack SUPER_SHOVEL;
	public static ItemStack SUPER_SHOVEL_NETHERITE;
	
	public static ItemStack EXPERIENCE_OBELISK_I;
	public static ItemStack EXPERIENCE_OBELISK_II;
	public static ItemStack EXPERIENCE_OBELISK_III;
	public static ItemStack EXPERIENCE_OBELISK_IV;
	
	public static ItemStack GRAND_EXPERIENCE_BOTTLE;
	
	public static ItemStack EXPERIENCE_ORB;
	
	public static ItemStack CRYSTAL_ARMOR_HELMET;
	public static ItemStack CRYSTAL_ARMOR_CHESTPLATE;
	public static ItemStack CRYSTAL_ARMOR_LEGGINGS;
	public static ItemStack CRYSTAL_ARMOR_BOOTS;
	public static ItemStack CRYSTAL_FRAGMENT;
	
	public static ItemStack GILDED_NETHERITE_ARMOR_HELMET;
	public static ItemStack GILDED_NETHERITE_ARMOR_CHESTPLATE;
	public static ItemStack GILDED_NETHERITE_ARMOR_LEGGINGS;
	public static ItemStack GILDED_NETHERITE_ARMOR_BOOTS;
	
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
	
	public static ItemStack WARPED_PEARL;
	public static ItemStack WARPED_FRUIT;
	
	
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
        HeadList.SOUL_CAGE = Utils.getSkullByTextureURL(url+"9580e5cd970c1ded973d0c139be418b0755e0713345d716ed594244a53e6e13d", null, null);

        //https://minecraft-heads.com/custom-heads/decoration/32371-opal
        HeadList.OPAL = Utils.getSkullByTextureURL(url+"49ae88a7d03f474558a05692e5f5c3ade312ddf1072166ad0426334ef5174b87", null, null);
        
        //https://minecraft-heads.com/custom-heads/miscellaneous/39654-revive-stone
        HeadList.REVIVE_STONE = Utils.getSkullByTextureURL(url+"b6a76cc22e7c2ab9c540d1244eadba581f5dd9e18f9adacf05280a5b48b8f618", null, null);
        
        //https://minecraft-heads.com/custom-heads/decoration/36763-stone-tank-empty
        HeadList.STONE_TANK = Utils.getSkullByTextureURL(url+"b91b7b21725f146d29c192b745d79d22603267c7ad893badeb6546e746600060", null, null);
        
	}
	
	
	public boolean loadCustomitems() {
		//Spieler köpfe laden
		PORTABLE_CRAFTING_TABLE = new ItemStack(HeadList.WORKBENCH);
		ItemMeta Workbench1 = PORTABLE_CRAFTING_TABLE.getItemMeta();
		Workbench1.setDisplayName("§fPortable Crafting Table");
		Workbench1.setCustomModelData(1);
		Workbench1.getPersistentDataContainer().set(portable_crafting_table_Key, PersistentDataType.BYTE, (byte) 1);
		PORTABLE_CRAFTING_TABLE.setItemMeta(Workbench1);
		ALL_ITEMS.put("PORTABLE_CRAFTING_TABLE", PORTABLE_CRAFTING_TABLE);
		
		PORTABLE_ENDERCHEST = new ItemStack(HeadList.ENDERCHEST);
		ItemMeta Portable_enderchest1 = PORTABLE_ENDERCHEST.getItemMeta();
		Portable_enderchest1.setDisplayName("§fPortable Enderchest");
		Portable_enderchest1.setCustomModelData(2);
		Portable_enderchest1.getPersistentDataContainer().set(portable_enderchest_Key, PersistentDataType.BYTE, (byte) 1);
		PORTABLE_ENDERCHEST.setItemMeta(Portable_enderchest1);
		ALL_ITEMS.put("PORTABLE_ENDERCHEST", PORTABLE_ENDERCHEST);
		
		EXPERIENCE_OBELISK_I = new ItemStack(HeadList.EXPERIENCE_BOTTLE);
		ItemMeta ExperienceObelisk1_I = EXPERIENCE_OBELISK_I.getItemMeta();
		ExperienceObelisk1_I.setDisplayName("§fExperience Obelisk I");
		ExperienceObelisk1_I.setCustomModelData(3);
		ExperienceObelisk1_I.getPersistentDataContainer().set(experience_obelisk_Key, PersistentDataType.BYTE, (byte) 1);
		ExperienceObelisk1_I.getPersistentDataContainer().set(experience_obelisk_I_Key, PersistentDataType.BYTE, (byte) 1);
		EXPERIENCE_OBELISK_I.setItemMeta(ExperienceObelisk1_I);
		ALL_ITEMS.put("EXPERIENCE_OBELISK_I", EXPERIENCE_OBELISK_I);
		
		EXPERIENCE_OBELISK_II = new ItemStack(HeadList.EXPERIENCE_BOTTLE);
		ItemMeta ExperienceObelisk1_II = EXPERIENCE_OBELISK_II.getItemMeta();
		ExperienceObelisk1_II.setDisplayName("§fExperience Obelisk II");
		ExperienceObelisk1_II.setCustomModelData(4);
		ExperienceObelisk1_II.getPersistentDataContainer().set(experience_obelisk_Key, PersistentDataType.BYTE, (byte) 1);
		ExperienceObelisk1_II.getPersistentDataContainer().set(experience_obelisk_II_Key, PersistentDataType.BYTE, (byte) 1);
		EXPERIENCE_OBELISK_II.setItemMeta(ExperienceObelisk1_II);
		ALL_ITEMS.put("EXPERIENCE_OBELISK_II", EXPERIENCE_OBELISK_II);
		
		EXPERIENCE_OBELISK_III = new ItemStack(HeadList.EXPERIENCE_BOTTLE);
		ItemMeta ExperienceObelisk1_III = EXPERIENCE_OBELISK_III.getItemMeta();
		ExperienceObelisk1_III.setDisplayName("§fExperience Obelisk III");
		ExperienceObelisk1_III.setCustomModelData(5);
		ExperienceObelisk1_III.getPersistentDataContainer().set(experience_obelisk_Key, PersistentDataType.BYTE, (byte) 1);
		ExperienceObelisk1_III.getPersistentDataContainer().set(experience_obelisk_III_Key, PersistentDataType.BYTE, (byte) 1);
		EXPERIENCE_OBELISK_III.setItemMeta(ExperienceObelisk1_III);
		ALL_ITEMS.put("EXPERIENCE_OBELISK_III", EXPERIENCE_OBELISK_III);
		
		EXPERIENCE_OBELISK_IV = new ItemStack(HeadList.EXPERIENCE_BOTTLE);
		ItemMeta ExperienceObelisk1_IV = EXPERIENCE_OBELISK_IV.getItemMeta();
		ExperienceObelisk1_IV.setDisplayName("§fExperience Obelisk IV");
		ExperienceObelisk1_IV.setCustomModelData(6);
		ExperienceObelisk1_IV.getPersistentDataContainer().set(experience_obelisk_Key, PersistentDataType.BYTE, (byte) 1);
		ExperienceObelisk1_IV.getPersistentDataContainer().set(experience_obelisk_IV_Key, PersistentDataType.BYTE, (byte) 1);
		EXPERIENCE_OBELISK_IV.setItemMeta(ExperienceObelisk1_IV);
		ALL_ITEMS.put("EXPERIENCE_OBELISK_IV", EXPERIENCE_OBELISK_IV);
		
		EXPERIENCE_ORB = new ItemStack(HeadList.EXPERIENCE_ORB);
		ItemMeta ExperienceObelisk1Orb = EXPERIENCE_ORB.getItemMeta();
		ExperienceObelisk1Orb.setDisplayName("§fExperience Orb");
		ExperienceObelisk1Orb.setCustomModelData(7);
		ExperienceObelisk1Orb.getPersistentDataContainer().set(experience_orb_Key, PersistentDataType.BYTE, (byte) 1);
		EXPERIENCE_ORB.setItemMeta(ExperienceObelisk1Orb);
		ALL_ITEMS.put("EXPERIENCE_ORB", EXPERIENCE_ORB);	
		//******************************************************************
		//Costmetics
		ELYTRA_RAVEN_WINGS = new ItemStack(Material.ELYTRA);
	    ItemMeta elytra_raven_wings_meta = ELYTRA_RAVEN_WINGS.getItemMeta();
	    elytra_raven_wings_meta.setCustomModelData(1);
	    elytra_raven_wings_meta.setDisplayName("§eRaven Wing Elytra");
	    ELYTRA_RAVEN_WINGS.setItemMeta(elytra_raven_wings_meta);
	    ALL_ITEMS.put("ELYTRA_RAVEN_WINGS", ELYTRA_RAVEN_WINGS);
	    
	    ELYTRA_DRAGON_WINGS = new ItemStack(Material.ELYTRA);
	    ItemMeta elytra_dragon_wings_meta = ELYTRA_DRAGON_WINGS.getItemMeta();
	    elytra_dragon_wings_meta.setCustomModelData(2);
	    elytra_dragon_wings_meta.setDisplayName("§eDragon Wing Elytra");
	    ELYTRA_DRAGON_WINGS.setItemMeta(elytra_dragon_wings_meta);
	    ALL_ITEMS.put("ELYTRA_DRAGON_WINGS", ELYTRA_DRAGON_WINGS);
	    
	    ELYTRA_MATRIX = new ItemStack(Material.ELYTRA);
	    ItemMeta elytra_matrix_meta = ELYTRA_MATRIX.getItemMeta();
	    elytra_matrix_meta.setCustomModelData(3);
	    elytra_matrix_meta.setDisplayName("§eMatrix Elytra");
	    ELYTRA_MATRIX.setItemMeta(elytra_matrix_meta);
	    ALL_ITEMS.put("ELYTRA_MATRIX", ELYTRA_MATRIX);
		
		//******************************************************************
	    WARPED_PEARL = new ItemStack(Material.ENDER_PEARL);
	    ItemMeta warped_pearl_meta = WARPED_PEARL.getItemMeta();
	    warped_pearl_meta.setCustomModelData(1);
	    warped_pearl_meta.setDisplayName("§fWarped Pearl");
	    WARPED_PEARL.setItemMeta(warped_pearl_meta);
	    ALL_ITEMS.put("WARPED_PEARL", WARPED_PEARL);
	    
	    WARPED_FRUIT = new ItemStack(Material.APPLE);
	    ItemMeta warped_fruit_meta = WARPED_FRUIT.getItemMeta();
	    warped_fruit_meta.setCustomModelData(1);
	    warped_fruit_meta.setDisplayName("§fWarped Fruit");
	    WARPED_FRUIT.setItemMeta(warped_fruit_meta);
	    ALL_ITEMS.put("WARPED_FRUIT", WARPED_FRUIT);
	    
	    ANDESITE_PICKAXE = new ItemStack(Material.STONE_PICKAXE);
	    ItemMeta andesite_pickaxe_meta = ANDESITE_PICKAXE.getItemMeta();
	    andesite_pickaxe_meta.setCustomModelData(1);
	    andesite_pickaxe_meta.setDisplayName("§fAndesite Pickaxe");
	    ANDESITE_PICKAXE.setItemMeta(andesite_pickaxe_meta);
	    ALL_ITEMS.put("ANDESITE_PICKAXE", ANDESITE_PICKAXE);
	    
	    DIORITE_PICKAXE = new ItemStack(Material.STONE_PICKAXE);
	    ItemMeta diorite_pickaxe_meta = DIORITE_PICKAXE.getItemMeta();
	    diorite_pickaxe_meta.setCustomModelData(2);
	    diorite_pickaxe_meta.setDisplayName("§fDiorite Pickaxe");
	    DIORITE_PICKAXE.setItemMeta(diorite_pickaxe_meta);
	    ALL_ITEMS.put("DIORITE_PICKAXE", DIORITE_PICKAXE);
	    
	    GRANITE_PICKAXE = new ItemStack(Material.STONE_PICKAXE);
	    ItemMeta granite_pickaxe_meta = GRANITE_PICKAXE.getItemMeta();
	    granite_pickaxe_meta.setCustomModelData(3);
	    granite_pickaxe_meta.setDisplayName("§fGranite Pickaxe");
	    GRANITE_PICKAXE.setItemMeta(granite_pickaxe_meta);
	    ALL_ITEMS.put("GRANITE_PICKAXE", GRANITE_PICKAXE);
		
	    GILDED_NETHERITE_ARMOR_HELMET = new ItemStack(Material.NETHERITE_HELMET);
		ItemMeta Gilded_Netherite_Armor_Helmet1 = GILDED_NETHERITE_ARMOR_HELMET.getItemMeta();
		Gilded_Netherite_Armor_Helmet1.setDisplayName("§fGilded Netherite Helmet");
		Gilded_Netherite_Armor_Helmet1.setCustomModelData(1);
		Gilded_Netherite_Armor_Helmet1.getPersistentDataContainer().set(gilded_netherite_armor_Key, PersistentDataType.BYTE, (byte) 1);
		GILDED_NETHERITE_ARMOR_HELMET.setItemMeta(Gilded_Netherite_Armor_Helmet1);
		ALL_ITEMS.put("GILDED_NETHERITE_ARMOR_HELMET", GILDED_NETHERITE_ARMOR_HELMET);
		
		GILDED_NETHERITE_ARMOR_CHESTPLATE = new ItemStack(Material.NETHERITE_CHESTPLATE);
		ItemMeta Gilded_Netherite_Armor_Chestplate1 = GILDED_NETHERITE_ARMOR_CHESTPLATE.getItemMeta();
		Gilded_Netherite_Armor_Chestplate1.setDisplayName("§fGilded Netherite Chestplate");
		Gilded_Netherite_Armor_Chestplate1.setCustomModelData(1);
		Gilded_Netherite_Armor_Chestplate1.getPersistentDataContainer().set(gilded_netherite_armor_Key, PersistentDataType.BYTE, (byte) 1);
		GILDED_NETHERITE_ARMOR_CHESTPLATE.setItemMeta(Gilded_Netherite_Armor_Chestplate1);
		ALL_ITEMS.put("GILDED_NETHERITE_ARMOR_CHESTPLATE", GILDED_NETHERITE_ARMOR_CHESTPLATE);
		
		GILDED_NETHERITE_ARMOR_LEGGINGS = new ItemStack(Material.NETHERITE_LEGGINGS);
		ItemMeta Gilded_Netherite_Armor_Leggings1 = GILDED_NETHERITE_ARMOR_LEGGINGS.getItemMeta();
		Gilded_Netherite_Armor_Leggings1.setDisplayName("§fGilded Netherite Leggings");
		Gilded_Netherite_Armor_Leggings1.setCustomModelData(1);
		Gilded_Netherite_Armor_Leggings1.getPersistentDataContainer().set(gilded_netherite_armor_Key, PersistentDataType.BYTE, (byte) 1);
		GILDED_NETHERITE_ARMOR_LEGGINGS.setItemMeta(Gilded_Netherite_Armor_Leggings1);
		ALL_ITEMS.put("GILDED_NETHERITE_ARMOR_LEGGINGS", GILDED_NETHERITE_ARMOR_LEGGINGS);
		
		GILDED_NETHERITE_ARMOR_BOOTS = new ItemStack(Material.NETHERITE_BOOTS);
		ItemMeta Gilded_Netherite_Armor_Boots1 = GILDED_NETHERITE_ARMOR_BOOTS.getItemMeta();
		Gilded_Netherite_Armor_Boots1.setDisplayName("§fGilded Netherite Boots");
		Gilded_Netherite_Armor_Boots1.setCustomModelData(1);
		Gilded_Netherite_Armor_Boots1.getPersistentDataContainer().set(gilded_netherite_armor_Key, PersistentDataType.BYTE, (byte) 1);
		GILDED_NETHERITE_ARMOR_BOOTS.setItemMeta(Gilded_Netherite_Armor_Boots1);
		ALL_ITEMS.put("GILDED_NETHERITE_ARMOR_BOOTS", GILDED_NETHERITE_ARMOR_BOOTS);
	    
		COFFEE_BEAN = new ItemStack(Material.COCOA_BEANS);
	    ItemMeta coffee_bean_meta = COFFEE_BEAN.getItemMeta();
	    coffee_bean_meta.setCustomModelData(1);
	    coffee_bean_meta.setDisplayName("§fCoffee Bean");
	    coffee_bean_meta.getPersistentDataContainer().set(coffee_bean_Key, PersistentDataType.BYTE, (byte)1);
	    COFFEE_BEAN.setItemMeta(coffee_bean_meta);
	    ALL_ITEMS.put("COFFEE_BEAN", COFFEE_BEAN);
		
	    GRAND_EXPERIENCE_BOTTLE = new ItemStack(Material.EXPERIENCE_BOTTLE);
		ItemMeta Grand_xp_bottle1 = GRAND_EXPERIENCE_BOTTLE.getItemMeta();
		Grand_xp_bottle1.setDisplayName("§fGrand Experience Bottle");
		Grand_xp_bottle1.setCustomModelData(1);
		Grand_xp_bottle1.getPersistentDataContainer().set(grand_experience_bottle_Key, PersistentDataType.BYTE, (byte) 1);
		GRAND_EXPERIENCE_BOTTLE.setItemMeta(Grand_xp_bottle1);
		ALL_ITEMS.put("GRAND_EXPERIENCE_BOTTLE", GRAND_EXPERIENCE_BOTTLE);
		
		CRYSTAL_FRAGMENT = new ItemStack(Material.QUARTZ);
		ItemMeta Crystal_fragment1 = CRYSTAL_FRAGMENT.getItemMeta();
		Crystal_fragment1.setDisplayName("§5Crystal Fragment");
		Crystal_fragment1.setCustomModelData(1);
		Crystal_fragment1.getPersistentDataContainer().set(cristal_fragment_Key, PersistentDataType.BYTE, (byte) 1);
		CRYSTAL_FRAGMENT.setItemMeta(Crystal_fragment1);
		ALL_ITEMS.put("CRYSTAL_FRAGMENT", CRYSTAL_FRAGMENT);
		
		CRYSTAL_ARMOR_HELMET = new ItemStack(Material.LEATHER_HELMET);
		LeatherArmorMeta Crystal_Armor_Helmet1 = (LeatherArmorMeta) CRYSTAL_ARMOR_HELMET.getItemMeta();
		Crystal_Armor_Helmet1.setColor(Color.fromBGR(255, 192, 203));
		Crystal_Armor_Helmet1.setDisplayName("§5Crystal Helmet");
		Crystal_Armor_Helmet1.setCustomModelData(1);
		Crystal_Armor_Helmet1.getPersistentDataContainer().set(cristal_armor_Key, PersistentDataType.BYTE, (byte) 1);
		CRYSTAL_ARMOR_HELMET.setItemMeta(Crystal_Armor_Helmet1);
		ALL_ITEMS.put("CRYSTAL_ARMOR_HELMET", CRYSTAL_ARMOR_HELMET);
		
		CRYSTAL_ARMOR_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE);
		LeatherArmorMeta Crystal_Armor_Chestplate1 = (LeatherArmorMeta) CRYSTAL_ARMOR_CHESTPLATE.getItemMeta();
		Crystal_Armor_Chestplate1.setColor(Color.fromBGR(255, 192, 203));
		Crystal_Armor_Chestplate1.setDisplayName("§5Crystal Chestplate");
		Crystal_Armor_Chestplate1.setCustomModelData(1);
		Crystal_Armor_Chestplate1.getPersistentDataContainer().set(cristal_armor_Key, PersistentDataType.BYTE, (byte) 1);
		CRYSTAL_ARMOR_CHESTPLATE.setItemMeta(Crystal_Armor_Chestplate1);
		ALL_ITEMS.put("CRYSTAL_ARMOR_CHESTPLATE", CRYSTAL_ARMOR_CHESTPLATE);
		
		CRYSTAL_ARMOR_LEGGINGS = new ItemStack(Material.LEATHER_LEGGINGS);
		LeatherArmorMeta Crystal_Armor_Leggings1 = (LeatherArmorMeta) CRYSTAL_ARMOR_LEGGINGS.getItemMeta();
		Crystal_Armor_Leggings1.setColor(Color.fromBGR(255, 192, 203));
		Crystal_Armor_Leggings1.setDisplayName("§5Crystal Leggings");
		Crystal_Armor_Leggings1.setCustomModelData(1);
		Crystal_Armor_Leggings1.getPersistentDataContainer().set(cristal_armor_Key, PersistentDataType.BYTE, (byte) 1);
		CRYSTAL_ARMOR_LEGGINGS.setItemMeta(Crystal_Armor_Leggings1);
		ALL_ITEMS.put("CRYSTAL_ARMOR_LEGGINGS", CRYSTAL_ARMOR_LEGGINGS);
		
		CRYSTAL_ARMOR_BOOTS = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta Crystal_Armor_Boots1 = (LeatherArmorMeta) CRYSTAL_ARMOR_BOOTS.getItemMeta();
		Crystal_Armor_Boots1.setColor(Color.fromBGR(255, 192, 203));
		Crystal_Armor_Boots1.setDisplayName("§5Crystal Boots");
		Crystal_Armor_Boots1.setCustomModelData(1);
		Crystal_Armor_Boots1.getPersistentDataContainer().set(cristal_armor_Key, PersistentDataType.BYTE, (byte) 1);
		CRYSTAL_ARMOR_BOOTS.setItemMeta(Crystal_Armor_Boots1);
		ALL_ITEMS.put("CRYSTAL_ARMOR_BOOTS", CRYSTAL_ARMOR_BOOTS);
		
		HEART_OF_THE_MINE = new ItemStack(Material.HEART_OF_THE_SEA);
		ItemMeta Heart_of_the_mine1 = HEART_OF_THE_MINE.getItemMeta();
		Heart_of_the_mine1.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		Heart_of_the_mine1.setDisplayName("§bHeart Of The Mine");
		Heart_of_the_mine1.setCustomModelData(1);
		Heart_of_the_mine1.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		Heart_of_the_mine1.getPersistentDataContainer().set(heart_of_the_mine_Key, PersistentDataType.BYTE, (byte) 1);
		HEART_OF_THE_MINE.setItemMeta(Heart_of_the_mine1);
		ALL_ITEMS.put("HEART_OF_THE_MINE", HEART_OF_THE_MINE);
		
		INVISIBLE_ITEM_FRAME = new ItemStack(Material.ITEM_FRAME);
		ItemMeta Invisible_Itemframe1 = INVISIBLE_ITEM_FRAME.getItemMeta();
		Invisible_Itemframe1.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		Invisible_Itemframe1.setDisplayName("§fInvisible Item Frame");
		Invisible_Itemframe1.setCustomModelData(1);
		Invisible_Itemframe1.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		Invisible_Itemframe1.getPersistentDataContainer().set(invisible_item_frame_Key, PersistentDataType.BYTE, (byte) 1);
		INVISIBLE_ITEM_FRAME.setItemMeta(Invisible_Itemframe1);
		ALL_ITEMS.put("INVISIBLE_ITEM_FRAME", INVISIBLE_ITEM_FRAME);
		
		GRAPPLING_HOOK = new ItemStack(Material.FISHING_ROD);
		ItemMeta GrapplingHook1 = GRAPPLING_HOOK.getItemMeta();
		GrapplingHook1.setDisplayName("§fGrappling Hook");
		GrapplingHook1.setCustomModelData(1);
		GrapplingHook1.getPersistentDataContainer().set(grappling_hook_Key, PersistentDataType.BYTE, (byte) 1);
		GRAPPLING_HOOK.setItemMeta(GrapplingHook1);
		ALL_ITEMS.put("GRAPPLING_HOOK", GRAPPLING_HOOK);
		
		DIRTY_WATER = new ItemStack(Material.POTION);
        ItemMeta dirtyMeta = DIRTY_WATER.getItemMeta();
        dirtyMeta.setCustomModelData(1);
        ((PotionMeta) dirtyMeta).setBasePotionData(new PotionData(PotionType.WATER));
        ((PotionMeta) dirtyMeta).setColor(Color.fromRGB(12769874));
        dirtyMeta.setDisplayName("§fWater Bottle");
        dirtyMeta.setLore(Collections.singletonList("§7Dirty"));
        dirtyMeta.getPersistentDataContainer().set(drinkables_Key, PersistentDataType.BYTE, (byte) 1);
        dirtyMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        DIRTY_WATER.setItemMeta(dirtyMeta);
        ALL_ITEMS.put("DIRTY_WATER", DIRTY_WATER);
        
        CLEAN_WATER = new ItemStack(Material.POTION);
        ItemMeta cleanMeta = CLEAN_WATER.getItemMeta();
        cleanMeta.setCustomModelData(2);
        ((PotionMeta) cleanMeta).setBasePotionData(new PotionData(PotionType.WATER));
        ((PotionMeta) cleanMeta).setColor(Color.fromRGB(1213666));
        cleanMeta.setDisplayName("§fWater Bottle");
        cleanMeta.setLore(Collections.singletonList("§7Clean"));
        cleanMeta.getPersistentDataContainer().set(drinkables_Key, PersistentDataType.BYTE, (byte) 1);
        cleanMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        CLEAN_WATER.setItemMeta(cleanMeta);
        ALL_ITEMS.put("CLEAN_WATER", CLEAN_WATER);
        
        PURIFIED_WATER = new ItemStack(Material.POTION);
        ItemMeta purifiedMeta = PURIFIED_WATER.getItemMeta();
        purifiedMeta.setCustomModelData(3);
        ((PotionMeta) purifiedMeta).setBasePotionData(new PotionData(PotionType.WATER));
        ((PotionMeta) purifiedMeta).setColor(Color.AQUA);
        purifiedMeta.setDisplayName("§fWater Bottle");
        purifiedMeta.setLore(Collections.singletonList("§7Purified"));
        purifiedMeta.getPersistentDataContainer().set(drinkables_Key, PersistentDataType.BYTE, (byte) 1);
        purifiedMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        PURIFIED_WATER.setItemMeta(purifiedMeta);
        ALL_ITEMS.put("PURIFIED_WATER", PURIFIED_WATER);
        
        COLD_MILK = new ItemStack(Material.POTION);
        ItemMeta cold_milk_Meta = COLD_MILK.getItemMeta();
        cold_milk_Meta.setCustomModelData(4);
        ((PotionMeta) cold_milk_Meta).setBasePotionData(new PotionData(PotionType.WATER));
        ((PotionMeta) cold_milk_Meta).setColor(Color.fromBGR(16250871));
        cold_milk_Meta.setDisplayName("§fCold Milk");
        cold_milk_Meta.getPersistentDataContainer().set(drinkables_Key, PersistentDataType.BYTE, (byte) 1);
        cold_milk_Meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        COLD_MILK.setItemMeta(cold_milk_Meta);
        ALL_ITEMS.put("COLD_MILK", COLD_MILK);
        
        HOT_MILK = new ItemStack(Material.POTION);
        ItemMeta hot_milk_Meta = HOT_MILK.getItemMeta();
        hot_milk_Meta.setCustomModelData(5);
        ((PotionMeta) hot_milk_Meta).setBasePotionData(new PotionData(PotionType.WATER));
        ((PotionMeta) hot_milk_Meta).setColor(Color.fromBGR(15456977));
        hot_milk_Meta.setDisplayName("§fHot Milk");
        hot_milk_Meta.getPersistentDataContainer().set(drinkables_Key, PersistentDataType.BYTE, (byte) 1);
        hot_milk_Meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        HOT_MILK.setItemMeta(hot_milk_Meta);
        ALL_ITEMS.put("HOT_MILK", HOT_MILK);
        
        COFFEE = new ItemStack(Material.POTION);
        ItemMeta coffee_meta = COFFEE.getItemMeta();
        coffee_meta.setCustomModelData(6);
        ((PotionMeta) coffee_meta).setBasePotionData(new PotionData(PotionType.WATER));
        ((PotionMeta) coffee_meta).setColor(Color.OLIVE);
        coffee_meta.setDisplayName("§fCoffee");
        coffee_meta.getPersistentDataContainer().set(drinkables_Key, PersistentDataType.BYTE, (byte) 1);
        coffee_meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        COFFEE.setItemMeta(coffee_meta);
        ALL_ITEMS.put("COFFEE", COFFEE);
        
        WATER_BOWL = new ItemStack(Material.POTION);
        ItemMeta water_bowl_meta = WATER_BOWL.getItemMeta();
        water_bowl_meta.setCustomModelData(7);
        ((PotionMeta) water_bowl_meta).setBasePotionData(new PotionData(PotionType.WATER));
        water_bowl_meta.setDisplayName("§fWater Bowl");
        water_bowl_meta.getPersistentDataContainer().set(drinkables_Key, PersistentDataType.BYTE, (byte) 1);
        water_bowl_meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        WATER_BOWL.setItemMeta(water_bowl_meta);
        ALL_ITEMS.put("WATER_BOWL", WATER_BOWL);
        
        GOLDEN_APPLE_JUICE = new ItemStack(Material.POTION);
        ItemMeta golden_apple_juice_meta = GOLDEN_APPLE_JUICE.getItemMeta();
        golden_apple_juice_meta.setCustomModelData(8);
        ((PotionMeta) golden_apple_juice_meta).setBasePotionData(new PotionData(PotionType.WATER));
        ((PotionMeta) golden_apple_juice_meta).setColor(Color.YELLOW);
        golden_apple_juice_meta.setDisplayName("§bGolden Apple Juice");
        golden_apple_juice_meta.getPersistentDataContainer().set(drinkables_Key, PersistentDataType.BYTE, (byte) 1);
        golden_apple_juice_meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        GOLDEN_APPLE_JUICE.setItemMeta(golden_apple_juice_meta);
        ALL_ITEMS.put("GOLDEN_APPLE_JUICE", GOLDEN_APPLE_JUICE);
        
        ENCHANTED_GOLDEN_APPLE_JUICE = new ItemStack(Material.POTION);
        ItemMeta enchanted_golden_apple_juice_meta = ENCHANTED_GOLDEN_APPLE_JUICE.getItemMeta();
        enchanted_golden_apple_juice_meta.setCustomModelData(9);
        ((PotionMeta) enchanted_golden_apple_juice_meta).setBasePotionData(new PotionData(PotionType.WATER));
        ((PotionMeta) enchanted_golden_apple_juice_meta).setColor(Color.YELLOW);
        ((PotionMeta) enchanted_golden_apple_juice_meta).addCustomEffect(new PotionEffect(PotionEffectType.REGENERATION, 400, 1), false);
        enchanted_golden_apple_juice_meta.setDisplayName("§dEnchanted Golden Apple Juice");
        enchanted_golden_apple_juice_meta.getPersistentDataContainer().set(drinkables_Key, PersistentDataType.BYTE, (byte) 1);
        enchanted_golden_apple_juice_meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        ENCHANTED_GOLDEN_APPLE_JUICE.setItemMeta(enchanted_golden_apple_juice_meta);
        ALL_ITEMS.put("ENCHANTED_GOLDEN_APPLE_JUICE", ENCHANTED_GOLDEN_APPLE_JUICE);
        
        SUPER_PICKAXE = new ItemStack(Material.DIAMOND_PICKAXE);
		ItemMeta Superpickaxe1 = SUPER_PICKAXE.getItemMeta();
		Superpickaxe1.setDisplayName("§bSuper Pickaxe");
		Superpickaxe1.getPersistentDataContainer().set(super_pickaxe_Key, PersistentDataType.BYTE, (byte) 1);
		//Fügt denn telekinesis hinzu
		//Superpickaxe1.getPersistentDataContainer().set(telekinesis_enchantment, PersistentDataType.BYTE, (byte) 1);
		Superpickaxe1.setCustomModelData(1);
		SUPER_PICKAXE.setItemMeta(Superpickaxe1);
		ALL_ITEMS.put("SUPER_PICKAXE", SUPER_PICKAXE);
		
		SUPER_PICKAXE_NETHERITE = new ItemStack(Material.NETHERITE_PICKAXE);
		ItemMeta Superpickaxe_typ_netherite1 = SUPER_PICKAXE_NETHERITE.getItemMeta();
		Superpickaxe_typ_netherite1.setDisplayName("§bSuper Pickaxe");
		Superpickaxe_typ_netherite1.getPersistentDataContainer().set(super_pickaxe_Key, PersistentDataType.BYTE, (byte) 1);
		Superpickaxe_typ_netherite1.setCustomModelData(1); 
		SUPER_PICKAXE_NETHERITE.setItemMeta(Superpickaxe_typ_netherite1);
		ALL_ITEMS.put("SUPER_PICKAXE_NETHERITE", SUPER_PICKAXE_NETHERITE);
		
		SUPER_AXE = new ItemStack(Material.DIAMOND_AXE);
		ItemMeta Superaxe1 = SUPER_AXE.getItemMeta();
		Superaxe1.setDisplayName("§bSuper Axe");
		Superaxe1.getPersistentDataContainer().set(super_axe_Key, PersistentDataType.BYTE, (byte) 1);
		Superaxe1.setCustomModelData(1);
		SUPER_AXE.setItemMeta(Superaxe1);
		ALL_ITEMS.put("SUPER_AXE", SUPER_AXE);
		
		SUPER_AXE_NETHERITE = new ItemStack(Material.NETHERITE_AXE);
		ItemMeta Superaxe_typ_netherite1 = SUPER_AXE_NETHERITE.getItemMeta();
		Superaxe_typ_netherite1.setDisplayName("§bSuper Axe");
		Superaxe_typ_netherite1.getPersistentDataContainer().set(super_axe_Key, PersistentDataType.BYTE, (byte) 1);
		Superaxe_typ_netherite1.setCustomModelData(1); 
		SUPER_AXE_NETHERITE.setItemMeta(Superaxe_typ_netherite1);
		ALL_ITEMS.put("SUPER_AXE_NETHERITE", SUPER_AXE_NETHERITE);
		
		SUPER_SHOVEL = new ItemStack(Material.DIAMOND_SHOVEL);
		ItemMeta Supershovel1 = SUPER_SHOVEL.getItemMeta();
		Supershovel1.setDisplayName("§bSuper Shovel");
		Supershovel1.getPersistentDataContainer().set(super_shovel_Key, PersistentDataType.BYTE, (byte) 1);
		Supershovel1.setCustomModelData(1);
		SUPER_SHOVEL.setItemMeta(Supershovel1);
		ALL_ITEMS.put("SUPER_SHOVEL", SUPER_SHOVEL);
		
		SUPER_SHOVEL_NETHERITE = new ItemStack(Material.NETHERITE_SHOVEL);
		ItemMeta Supershovel_typ_netherite1 = SUPER_SHOVEL_NETHERITE.getItemMeta();
		Supershovel_typ_netherite1.setDisplayName("§bSuper Shovel");
		Supershovel_typ_netherite1.getPersistentDataContainer().set(super_shovel_Key, PersistentDataType.BYTE, (byte) 1);
		Supershovel_typ_netherite1.setCustomModelData(1); 
		SUPER_SHOVEL_NETHERITE.setItemMeta(Supershovel_typ_netherite1);
		ALL_ITEMS.put("SUPER_SHOVEL_NETHERITE", SUPER_SHOVEL_NETHERITE);
		
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
	
		SmithingRecipe gilded_netherite_helmet = new SmithingRecipe(new NamespacedKey(MCReloaded.getPlugin(), "gilded_netherite_helmet"), GILDED_NETHERITE_ARMOR_HELMET, new MaterialChoice(Material.NETHERITE_HELMET), new MaterialChoice(Material.GOLD_BLOCK));
		Bukkit.getServer().addRecipe(gilded_netherite_helmet);	
		//*******************************************************************
		
		SmithingRecipe gilded_netherite_chestplate = new SmithingRecipe(new NamespacedKey(MCReloaded.getPlugin(), "gilded_netherite_chestplate"), GILDED_NETHERITE_ARMOR_CHESTPLATE, new MaterialChoice(Material.NETHERITE_CHESTPLATE), new MaterialChoice(Material.GOLD_BLOCK));
		Bukkit.getServer().addRecipe(gilded_netherite_chestplate);	
		//*******************************************************************
		
		SmithingRecipe gilded_netherite_leggings = new SmithingRecipe(new NamespacedKey(MCReloaded.getPlugin(), "gilded_netherite_leggings"), GILDED_NETHERITE_ARMOR_LEGGINGS, new MaterialChoice(Material.NETHERITE_LEGGINGS), new MaterialChoice(Material.GOLD_BLOCK));
		Bukkit.getServer().addRecipe(gilded_netherite_leggings);	
		//*******************************************************************
		
		SmithingRecipe gilded_netherite_boots = new SmithingRecipe(new NamespacedKey(MCReloaded.getPlugin(), "gilded_netherite_boots"), GILDED_NETHERITE_ARMOR_BOOTS, new MaterialChoice(Material.NETHERITE_BOOTS), new MaterialChoice(Material.GOLD_BLOCK));
		Bukkit.getServer().addRecipe(gilded_netherite_boots);	
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
		ShapelessRecipe Warped_pearl = new ShapelessRecipe(new NamespacedKey(MCReloaded.getPlugin(), "warped_pearl"), WARPED_PEARL);
		
		Warped_pearl.addIngredient(Material.ENDER_PEARL);
		Warped_pearl.addIngredient(Material.WARPED_FUNGUS);
        Bukkit.getServer().addRecipe(Warped_pearl);		
        
        //*******************************************************************		
      		ShapelessRecipe Warped_fruit = new ShapelessRecipe(new NamespacedKey(MCReloaded.getPlugin(), "warped_fruit"), WARPED_FRUIT);
      		
      		Warped_fruit.addIngredient(Material.CHORUS_FRUIT);
      		Warped_fruit.addIngredient(Material.WARPED_FUNGUS);
      		Warped_fruit.addIngredient(Material.SUGAR);
            Bukkit.getServer().addRecipe(Warped_fruit);		
		//*******************************************************************
		
		ShapedRecipe grappling_hook = new ShapedRecipe(new NamespacedKey(MCReloaded.getPlugin(), "grappling_hook"), GRAPPLING_HOOK);

        grappling_hook.shape(" 3 ", "121", " 3 ");
        grappling_hook.setIngredient('1', Material.FISHING_ROD);
        grappling_hook.setIngredient('2', Material.STRING);
        grappling_hook.setIngredient('3', Material.IRON_INGOT);
        Bukkit.getServer().addRecipe(grappling_hook);		
        
        
		//*******************************************************************	
		ShapedRecipe Crystal_Armor_Helmet = new ShapedRecipe(new NamespacedKey(MCReloaded.getPlugin(), "crystal_helmet"), CRYSTAL_ARMOR_HELMET);

		Crystal_Armor_Helmet.shape("111", "1 1");
		Crystal_Armor_Helmet.setIngredient('1', new ExactChoice(CRYSTAL_FRAGMENT));
        Bukkit.getServer().addRecipe(Crystal_Armor_Helmet);		
        
        //*******************************************************************	
        ShapedRecipe Crystal_Armor_Chestplate = new ShapedRecipe(new NamespacedKey(MCReloaded.getPlugin(), "crystal_chestplate"), CRYSTAL_ARMOR_CHESTPLATE);

        Crystal_Armor_Chestplate.shape("1 1", "111", "111");
      	Crystal_Armor_Chestplate.setIngredient('1', new ExactChoice(CRYSTAL_FRAGMENT));
        Bukkit.getServer().addRecipe(Crystal_Armor_Chestplate);		        
              
        //*******************************************************************	
        ShapedRecipe Crystal_Armor_Leggings = new ShapedRecipe(new NamespacedKey(MCReloaded.getPlugin(), "crystal_leggings"), CRYSTAL_ARMOR_LEGGINGS);

        Crystal_Armor_Leggings.shape("111", "1 1", "1 1");
        Crystal_Armor_Leggings.setIngredient('1', new ExactChoice(CRYSTAL_FRAGMENT));
        Bukkit.getServer().addRecipe(Crystal_Armor_Leggings);		        
              
        //*******************************************************************	
        ShapedRecipe Crystal_Armor_Boots = new ShapedRecipe(new NamespacedKey(MCReloaded.getPlugin(), "crystal_boots"), CRYSTAL_ARMOR_BOOTS);

        Crystal_Armor_Boots.shape("1 1", "1 1");
        Crystal_Armor_Boots.setIngredient('1', new ExactChoice(CRYSTAL_FRAGMENT));
        Bukkit.getServer().addRecipe(Crystal_Armor_Boots);		        
              
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
        
        ShapedRecipe ExperienceObelisk_I = new ShapedRecipe(new NamespacedKey(MCReloaded.getPlugin(), "experience_obelisk_I"), EXPERIENCE_OBELISK_I);

        ExperienceObelisk_I.shape("121", "343", "353");
        ExperienceObelisk_I.setIngredient('1', Material.IRON_INGOT);
        ExperienceObelisk_I.setIngredient('2', new RecipeChoice.MaterialChoice(Tag.WOODEN_SLABS));
        ExperienceObelisk_I.setIngredient('3', Material.GLASS);
        ExperienceObelisk_I.setIngredient('4', new ExactChoice(EXPERIENCE_ORB));
        ExperienceObelisk_I.setIngredient('5', Material.EXPERIENCE_BOTTLE);
        Bukkit.getServer().addRecipe(ExperienceObelisk_I);		
	
		//*******************************************************************
        ShapedRecipe ExperienceObelisk_II = new ShapedRecipe(new NamespacedKey(MCReloaded.getPlugin(), "experience_obelisk_II"), EXPERIENCE_OBELISK_II);

        ExperienceObelisk_II.shape("121", "545", "333");
        ExperienceObelisk_II.setIngredient('1', Material.DIAMOND);
        ExperienceObelisk_II.setIngredient('2', new ExactChoice(EXPERIENCE_ORB));
        ExperienceObelisk_II.setIngredient('3', Material.GLASS);
        ExperienceObelisk_II.setIngredient('4', new ExactChoice(EXPERIENCE_OBELISK_I));
        ExperienceObelisk_II.setIngredient('5', new ExactChoice(GRAND_EXPERIENCE_BOTTLE));
        Bukkit.getServer().addRecipe(ExperienceObelisk_II);		
	
		//*******************************************************************
        ShapedRecipe ExperienceObelisk_III = new ShapedRecipe(new NamespacedKey(MCReloaded.getPlugin(), "experience_obelisk_III"), EXPERIENCE_OBELISK_III);

        ExperienceObelisk_III.shape("121", "343", "565");
        ExperienceObelisk_III.setIngredient('1', Material.DIAMOND);
        ExperienceObelisk_III.setIngredient('2', new ExactChoice(EXPERIENCE_ORB));
        ExperienceObelisk_III.setIngredient('3', Material.GLASS);
        ExperienceObelisk_III.setIngredient('4', new ExactChoice(EXPERIENCE_OBELISK_II));
        ExperienceObelisk_III.setIngredient('5', Material.OBSIDIAN);
        ExperienceObelisk_III.setIngredient('6', Material.NETHERITE_INGOT);
        Bukkit.getServer().addRecipe(ExperienceObelisk_III);		
        //*******************************************************************
        ShapedRecipe ExperienceObelisk_IV = new ShapedRecipe(new NamespacedKey(MCReloaded.getPlugin(), "experience_obelisk_IV"), EXPERIENCE_OBELISK_IV);

        ExperienceObelisk_IV.shape("212", "343", "565");
        ExperienceObelisk_IV.setIngredient('1', Material.NETHER_STAR);
        ExperienceObelisk_IV.setIngredient('2', new ExactChoice(EXPERIENCE_ORB));
        ExperienceObelisk_IV.setIngredient('3', Material.GLASS);
        ExperienceObelisk_IV.setIngredient('4', new ExactChoice(EXPERIENCE_OBELISK_III));
        ExperienceObelisk_IV.setIngredient('5', Material.OBSIDIAN);
        ExperienceObelisk_IV.setIngredient('6', Material.NETHERITE_INGOT);
        Bukkit.getServer().addRecipe(ExperienceObelisk_IV);		
	
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
		
		ShapedRecipe Super_Pickaxe = new ShapedRecipe(new NamespacedKey(MCReloaded.getPlugin(), "super_pickaxe"), SUPER_PICKAXE);
		Super_Pickaxe.shape("LLL", " N ", " N ");
		Super_Pickaxe.setIngredient('N', Material.STICK);
		Super_Pickaxe.setIngredient('L', Material.DIAMOND_BLOCK);
		Bukkit.getServer().addRecipe(Super_Pickaxe);		
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
     
       
        /**
         * Any water bottle
         */
    	DRINKABLE(DIRTY_WATER, CLEAN_WATER, PURIFIED_WATER, WATER_BOWL, COLD_MILK, HOT_MILK, COFFEE,
    			ENCHANTED_GOLDEN_APPLE_JUICE, GOLDEN_APPLE_JUICE),
       
    	WATER_BOTTLE(DIRTY_WATER, CLEAN_WATER, PURIFIED_WATER),
    	
    	BREWINGSTAND_BLOCKED(COFFEE, COLD_MILK, HOT_MILK, WATER_BOWL, ENCHANTED_GOLDEN_APPLE_JUICE, GOLDEN_APPLE_JUICE),
    	
    	GRINDSTONE_BLOCKED(DIRTY_WATER, CLEAN_WATER, PURIFIED_WATER, WATER_BOWL, COLD_MILK, HOT_MILK,
    			COFFEE, GRAPPLING_HOOK, HEART_OF_THE_MINE, PORTABLE_CRAFTING_TABLE, INVISIBLE_ITEM_FRAME,
    			EXPERIENCE_OBELISK_I, EXPERIENCE_OBELISK_II, EXPERIENCE_OBELISK_III, EXPERIENCE_OBELISK_IV,
    			EXPERIENCE_ORB, CRYSTAL_FRAGMENT, ENCHANTED_GOLDEN_APPLE_JUICE, GOLDEN_APPLE_JUICE),
    	
    	ANVIL_BLOCKED(HEART_OF_THE_MINE, DIRTY_WATER, CLEAN_WATER, PURIFIED_WATER, WATER_BOWL,
    			COLD_MILK, HOT_MILK, COFFEE, COFFEE_BEAN, EXPERIENCE_OBELISK_I, EXPERIENCE_OBELISK_II, 
    			EXPERIENCE_OBELISK_III, EXPERIENCE_OBELISK_IV, EXPERIENCE_ORB, CRYSTAL_FRAGMENT,
    			ENCHANTED_GOLDEN_APPLE_JUICE, GOLDEN_APPLE_JUICE),
    	
    	PLAYER_HEAD(PORTABLE_CRAFTING_TABLE, PORTABLE_ENDERCHEST, EXPERIENCE_OBELISK_I, EXPERIENCE_OBELISK_II, 
    			EXPERIENCE_OBELISK_III, EXPERIENCE_OBELISK_IV, EXPERIENCE_ORB),
    	
    	PLACEABLE_BLOCKED(PORTABLE_CRAFTING_TABLE, PORTABLE_ENDERCHEST, EXPERIENCE_OBELISK_I, EXPERIENCE_OBELISK_II, 
    			EXPERIENCE_OBELISK_III, EXPERIENCE_OBELISK_IV, EXPERIENCE_ORB, COFFEE_BEAN);
    	
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
    	SUPER_PICKAXE("super_pickaxe"),
    	SUPER_AXE("super_axe"),
    	SUPER_SHOVEL("super_shovel"),
    	INVISIBLE_ITEM_FRAME("invisible_item_frame"),
    	GRAPPLING_HOOK("grappling_hook"),
    	COFFEE("coffee"),
    	COLD_MILK("cold_milk"),
    	HOT_MILK("hot_milk"),
    	HEART_OF_THE_MINE("heart_of_the_mine"),
    	EXPERIENCE_OBELISK_I("experience_obelisk_I"),
    	EXPERIENCE_OBELISK_II("experience_obelisk_II"),
    	EXPERIENCE_OBELISK_III("experience_obelisk_III"),
    	EXPERIENCE_OBELISK_IV("experience_obelisk_IV"),
    	GRAND_EXPERIENCE_BOTTLE("grand_experience_bottle"),
    	GRANITE_PICKAXE("granite_pickaxe"),
    	ANDESITE_PICKAXE("andesite_pickaxe"),
    	DIORITE_PICKAXE("diorite_pickaxe"),
    	WARPED_PEARL("warped_pearl"),
    	WARPED_FRUIT("warped_fruit"),
    	GOLDEN_APPLE_JUICE("golden_apple_juice"),
    	ENCHANTED_GOLDEN_APPLE_JUICE("enchanted_golden_apple_juice"),
    	
    	// CUSTOM ARMOR
    	CRYSTAL_ARMOR_HELMET("crystal_helmet"),
    	CRYSTAL_ARMOR_CHESTPLATE("crystal_chestplate"),
    	CRYSTAL_ARMOR_LEGGINGS("crystal_leggings"),
    	CRYSTAL_ARMOR_BOOTS("crystal_boots"),
    	
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

        private static Collection<NamespacedKey> getAllKeys() {
            return allKeys;
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
}
