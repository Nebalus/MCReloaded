package de.pixelstudios.mcreloaded.listener.player;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import de.pixelstudios.mcreloaded.MCReloaded;
import de.pixelstudios.mcreloaded.manager.BlockManager;
import de.pixelstudios.mcreloaded.manager.ItemManager;
import de.pixelstudios.mcreloaded.utils.Achievements;
import de.pixelstudios.mcreloaded.utils.Utils;

public class PlayerBlockBreak implements Listener{
	@SuppressWarnings("deprecation")
	@EventHandler(
			  priority = EventPriority.HIGHEST
		   )
	public void onBreak(BlockBreakEvent e) {
		if(e.isCancelled())return;
		Player p = e.getPlayer();
		Block b = e.getBlock();
		ItemStack item = new ItemStack(p.getItemInHand());
		switch(b.getType()) {
			case SPAWNER:
				MCReloaded.getPlugin().getPlayerManager().getProfile(p).giveAchievement(Achievements.MINESPAWNER);
				break;
			case OBSIDIAN:
				if(item.getType() == Material.AIR) {
					MCReloaded.getPlugin().getPlayerManager().getProfile(p).giveAchievement(Achievements.NEWBEDROCK);
				}
				break;
			default:
				break;		
		}
			try {
				//Boolean telekinesis = item.getItemMeta().getPersistentDataContainer().has(ItemManager.telekinesis_enchantment, PersistentDataType.BYTE);
				Boolean isGamemode = !p.getGameMode().equals(GameMode.CREATIVE);
				if(item.getItemMeta().getPersistentDataContainer().has(ItemManager.super_pickaxe_Key, PersistentDataType.BYTE)) {
					switch(b.getType()) {
					
					case COBBLESTONE:	
					case MOSSY_COBBLESTONE:	
					case NETHER_BRICK:	
					case NETHERRACK:
					case STONE:
					case ANDESITE:
					case DIORITE:
					case GRANITE:
					case COAL_ORE:
					case GOLD_ORE:
					case IRON_ORE:
					case DIAMOND_ORE:
					case LAPIS_ORE:
					case REDSTONE_ORE:
					case NETHER_QUARTZ_ORE:
					case GLOWSTONE:
					case END_STONE:
					case NETHER_GOLD_ORE:
					case BASALT:
					case BLACKSTONE:
					case SANDSTONE:
					case OBSIDIAN:
					case CRYING_OBSIDIAN:
					case COBBLED_DEEPSLATE:
					case DEEPSLATE:
					case CALCITE:
					case TUFF:
					case DRIPSTONE_BLOCK:
					case DEEPSLATE_IRON_ORE:
					case DEEPSLATE_GOLD_ORE:
					case DEEPSLATE_REDSTONE_ORE:
					case DEEPSLATE_EMERALD_ORE:
					case DEEPSLATE_LAPIS_ORE:
					case DEEPSLATE_DIAMOND_ORE:
					case COPPER_ORE:
					case SMOOTH_BASALT:
					case RAW_IRON_BLOCK:
					case RAW_COPPER_BLOCK:
					case RAW_GOLD_BLOCK:
					superTools(b, p, e, isGamemode,item);
					break;		
					
					default:
						
						break;		
					}
				}
				if(item.getItemMeta().getPersistentDataContainer().has(ItemManager.super_shovel_Key, PersistentDataType.BYTE)) {
					switch(b.getType()) {
					case SAND:
					case GRASS_BLOCK:
					case DIRT:
					case GRAVEL:
					case SNOW_BLOCK:
					case SNOW:
					case SOUL_SAND:
					case SOUL_SOIL:
						
					superTools(b, p, e, isGamemode,item);
					break;
					
					default:
						
					break;		
					}
				}
				if(item.getItemMeta().getPersistentDataContainer().has(ItemManager.super_axe_Key, PersistentDataType.BYTE)) {
					switch(b.getType()) {
					case OAK_LOG:
					case OAK_WOOD:
					case STRIPPED_OAK_LOG:
					case STRIPPED_OAK_WOOD:
						
					case SPRUCE_LOG:
					case SPRUCE_WOOD:
					case STRIPPED_SPRUCE_LOG:
					case STRIPPED_SPRUCE_WOOD:
						
					case BIRCH_LOG:
					case BIRCH_WOOD:
					case STRIPPED_BIRCH_LOG:
					case STRIPPED_BIRCH_WOOD:
						
					case JUNGLE_LOG:
					case JUNGLE_WOOD:
					case STRIPPED_JUNGLE_LOG:
					case STRIPPED_JUNGLE_WOOD:
						
					case ACACIA_LOG:
					case ACACIA_WOOD:
					case STRIPPED_ACACIA_LOG:
					case STRIPPED_ACACIA_WOOD:
						
					case DARK_OAK_LOG:
					case DARK_OAK_WOOD:
					case STRIPPED_DARK_OAK_LOG:
					case STRIPPED_DARK_OAK_WOOD:
						
					case CRIMSON_STEM:
					case CRIMSON_HYPHAE:
					case STRIPPED_CRIMSON_HYPHAE:
						
					case WARPED_STEM:
					case WARPED_HYPHAE:
					case STRIPPED_WARPED_HYPHAE:
						
					superTools(b, p, e, isGamemode,item);
					break;
					
					default:
						
					break;		
					}
				}
			}catch(NullPointerException ex) {}
		}
	
		public void superTools(Block b, Player p1, BlockBreakEvent e1, Boolean isGamemode, ItemStack item) {
			BlockFace face = Utils.lastblockface.get(p1);
			if(face.equals(BlockFace.NORTH)||face.equals(BlockFace.SOUTH)) {
					if(b.getLocation().add(-1, -1, 0).getBlock().getType().equals(b.getType())) {
						BlockManager.breakblock(b.getLocation().add(-1, -1, 0), isGamemode, p1, item);
					}
					if(b.getLocation().add(0, -1, 0).getBlock().getType().equals(b.getType())) {
						BlockManager.breakblock(b.getLocation().add(0, -1, 0), isGamemode, p1, item);
					}
					if(b.getLocation().add(1, -1, 0).getBlock().getType().equals(b.getType())) {
						BlockManager.breakblock(b.getLocation().add(1, -1, 0), isGamemode, p1, item);
					}
					if(b.getLocation().add(-1, 0, 0).getBlock().getType().equals(b.getType())) {
						BlockManager.breakblock(b.getLocation().add(-1, 0, 0),isGamemode, p1, item);
					}
					if(b.getLocation().add(1, 0, 0).getBlock().getType().equals(b.getType())) {
						BlockManager.breakblock(b.getLocation().add(1, 0, 0), isGamemode, p1, item);
					}
					if(b.getLocation().add(-1, 1, 0).getBlock().getType().equals(b.getType())) {
						BlockManager.breakblock(b.getLocation().add(-1, 1, 0), isGamemode, p1, item);
					}
					if(b.getLocation().add(0, 1, 0).getBlock().getType().equals(b.getType())) {
						BlockManager.breakblock(b.getLocation().add(0, 1, 0), isGamemode, p1, item);
					}
					if(b.getLocation().add(1, 1, 0).getBlock().getType().equals(b.getType())) {
						BlockManager.breakblock(b.getLocation().add(1, 1, 0), isGamemode, p1, item);
					}
				}
			if(face.equals(BlockFace.DOWN)||face.equals(BlockFace.UP)) {
					if(b.getLocation().add(-1, 0, 1).getBlock().getType().equals(b.getType())) {
						BlockManager.breakblock(b.getLocation().add(-1, 0, 1), isGamemode, p1, item);
					}
					if(b.getLocation().add(0, 0, 1).getBlock().getType().equals(b.getType())) {
						BlockManager.breakblock(b.getLocation().add(0, 0, 1), isGamemode, p1, item);
					}
					if(b.getLocation().add(1, 0, 1).getBlock().getType().equals(b.getType())) {
						BlockManager.breakblock(b.getLocation().add(1, 0, 1), isGamemode, p1, item);
					}
					if(b.getLocation().add(-1, 0, 0).getBlock().getType().equals(b.getType())) {
						BlockManager.breakblock(b.getLocation().add(-1, 0, 0), isGamemode, p1, item);
					}
					if(b.getLocation().add(1, 0, 0).getBlock().getType().equals(b.getType())) {
						BlockManager.breakblock(b.getLocation().add(1, 0, 0), isGamemode, p1, item);
					}
					if(b.getLocation().add(-1, 0, -1).getBlock().getType().equals(b.getType())) {
						BlockManager.breakblock(b.getLocation().add(-1, 0, -1), isGamemode, p1, item);
					}
					if(b.getLocation().add(0, 0, -1).getBlock().getType().equals(b.getType())) {
						BlockManager.breakblock(b.getLocation().add(0, 0, -1), isGamemode, p1, item);
					}
					if(b.getLocation().add(1, 0, -1).getBlock().getType().equals(b.getType())) {
						BlockManager.breakblock(b.getLocation().add(1, 0, -1), isGamemode, p1, item);
					}
			}
			if(face.equals(BlockFace.WEST)||face.equals(BlockFace.EAST)) {
					if(b.getLocation().add(0, -1, -1).getBlock().getType().equals(b.getType())) {
						BlockManager.breakblock(b.getLocation().add(0, -1, -1), isGamemode, p1, item);
					}
					if(b.getLocation().add(0, -1, 0).getBlock().getType().equals(b.getType())) {
						BlockManager.breakblock(b.getLocation().add(0, -1, 0), isGamemode, p1, item);
					}
					if(b.getLocation().add(0, -1, 1).getBlock().getType().equals(b.getType())) {
						BlockManager.breakblock(b.getLocation().add(0, -1, 1), isGamemode, p1, item);
					}
					if(b.getLocation().add(0, 0, -1).getBlock().getType().equals(b.getType())) {
						BlockManager.breakblock(b.getLocation().add(0, 0, -1), isGamemode, p1, item);
					}
					if(b.getLocation().add(0, 0, 1).getBlock().getType().equals(b.getType())) {
						BlockManager.breakblock(b.getLocation().add(0, 0, 1), isGamemode, p1, item);
					}
					if(b.getLocation().add(0, 1, -1).getBlock().getType().equals(b.getType())) {
						BlockManager.breakblock(b.getLocation().add(0, 1, -1), isGamemode, p1, item);
					}
					if(b.getLocation().add(0, 1, 0).getBlock().getType().equals(b.getType())) {
						BlockManager.breakblock(b.getLocation().add(0, 1, 0), isGamemode, p1, item);
					}
					if(b.getLocation().add(0, 1, 1).getBlock().getType().equals(b.getType())) {
						BlockManager.breakblock(b.getLocation().add(0, 1, 1), isGamemode, p1, item);
				}
			}
		}
}