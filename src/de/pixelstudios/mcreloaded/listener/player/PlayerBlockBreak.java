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
import de.pixelstudios.mcreloaded.manager.ItemManager;
import de.pixelstudios.mcreloaded.manager.ItemManager.Modifiers;
import de.pixelstudios.mcreloaded.utils.Achievements;
import io.pixelstudios.libary.ChunkLibary;

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
		//p.sendMessage(ChunkLibary.isSlimeChunk(b.getWorld().getSeed(), (int)Math.ceil(b.getX()), (int)Math.ceil(b.getZ()))+"");
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
			if(ItemManager.hasModifier(Modifiers.MINI_EXCAVATOR, item)) {
				superTools(b, p, e, !p.getGameMode().equals(GameMode.CREATIVE),item);
			}
		}catch(NullPointerException ex) {}
	}
	
		public void superTools(Block b, Player p1, BlockBreakEvent e1, Boolean isGamemode, ItemStack item) {
			BlockFace face = MCReloaded.getPlugin().getPlayerManager().getProfile(p1).getLastBlockFace();
			if(face.equals(BlockFace.NORTH)||face.equals(BlockFace.SOUTH)) {
					if(b.getLocation().add(-1, -1, 0).getBlock().getType().equals(b.getType())) {
						ChunkLibary.breakblock(b.getLocation().add(-1, -1, 0), isGamemode, p1, item);
					}
					if(b.getLocation().add(0, -1, 0).getBlock().getType().equals(b.getType())) {
						ChunkLibary.breakblock(b.getLocation().add(0, -1, 0), isGamemode, p1, item);
					}
					if(b.getLocation().add(1, -1, 0).getBlock().getType().equals(b.getType())) {
						ChunkLibary.breakblock(b.getLocation().add(1, -1, 0), isGamemode, p1, item);
					}
					if(b.getLocation().add(-1, 0, 0).getBlock().getType().equals(b.getType())) {
						ChunkLibary.breakblock(b.getLocation().add(-1, 0, 0),isGamemode, p1, item);
					}
					if(b.getLocation().add(1, 0, 0).getBlock().getType().equals(b.getType())) {
						ChunkLibary.breakblock(b.getLocation().add(1, 0, 0), isGamemode, p1, item);
					}
					if(b.getLocation().add(-1, 1, 0).getBlock().getType().equals(b.getType())) {
						ChunkLibary.breakblock(b.getLocation().add(-1, 1, 0), isGamemode, p1, item);
					}
					if(b.getLocation().add(0, 1, 0).getBlock().getType().equals(b.getType())) {
						ChunkLibary.breakblock(b.getLocation().add(0, 1, 0), isGamemode, p1, item);
					}
					if(b.getLocation().add(1, 1, 0).getBlock().getType().equals(b.getType())) {
						ChunkLibary.breakblock(b.getLocation().add(1, 1, 0), isGamemode, p1, item);
					}
				}
			if(face.equals(BlockFace.DOWN)||face.equals(BlockFace.UP)) {
					if(b.getLocation().add(-1, 0, 1).getBlock().getType().equals(b.getType())) {
						ChunkLibary.breakblock(b.getLocation().add(-1, 0, 1), isGamemode, p1, item);
					}
					if(b.getLocation().add(0, 0, 1).getBlock().getType().equals(b.getType())) {
						ChunkLibary.breakblock(b.getLocation().add(0, 0, 1), isGamemode, p1, item);
					}
					if(b.getLocation().add(1, 0, 1).getBlock().getType().equals(b.getType())) {
						ChunkLibary.breakblock(b.getLocation().add(1, 0, 1), isGamemode, p1, item);
					}
					if(b.getLocation().add(-1, 0, 0).getBlock().getType().equals(b.getType())) {
						ChunkLibary.breakblock(b.getLocation().add(-1, 0, 0), isGamemode, p1, item);
					}
					if(b.getLocation().add(1, 0, 0).getBlock().getType().equals(b.getType())) {
						ChunkLibary.breakblock(b.getLocation().add(1, 0, 0), isGamemode, p1, item);
					}
					if(b.getLocation().add(-1, 0, -1).getBlock().getType().equals(b.getType())) {
						ChunkLibary.breakblock(b.getLocation().add(-1, 0, -1), isGamemode, p1, item);
					}
					if(b.getLocation().add(0, 0, -1).getBlock().getType().equals(b.getType())) {
						ChunkLibary.breakblock(b.getLocation().add(0, 0, -1), isGamemode, p1, item);
					}
					if(b.getLocation().add(1, 0, -1).getBlock().getType().equals(b.getType())) {
						ChunkLibary.breakblock(b.getLocation().add(1, 0, -1), isGamemode, p1, item);
					}
			}
			if(face.equals(BlockFace.WEST)||face.equals(BlockFace.EAST)) {
					if(b.getLocation().add(0, -1, -1).getBlock().getType().equals(b.getType())) {
						ChunkLibary.breakblock(b.getLocation().add(0, -1, -1), isGamemode, p1, item);
					}
					if(b.getLocation().add(0, -1, 0).getBlock().getType().equals(b.getType())) {
						ChunkLibary.breakblock(b.getLocation().add(0, -1, 0), isGamemode, p1, item);
					}
					if(b.getLocation().add(0, -1, 1).getBlock().getType().equals(b.getType())) {
						ChunkLibary.breakblock(b.getLocation().add(0, -1, 1), isGamemode, p1, item);
					}
					if(b.getLocation().add(0, 0, -1).getBlock().getType().equals(b.getType())) {
						ChunkLibary.breakblock(b.getLocation().add(0, 0, -1), isGamemode, p1, item);
					}
					if(b.getLocation().add(0, 0, 1).getBlock().getType().equals(b.getType())) {
						ChunkLibary.breakblock(b.getLocation().add(0, 0, 1), isGamemode, p1, item);
					}
					if(b.getLocation().add(0, 1, -1).getBlock().getType().equals(b.getType())) {
						ChunkLibary.breakblock(b.getLocation().add(0, 1, -1), isGamemode, p1, item);
					}
					if(b.getLocation().add(0, 1, 0).getBlock().getType().equals(b.getType())) {
						ChunkLibary.breakblock(b.getLocation().add(0, 1, 0), isGamemode, p1, item);
					}
					if(b.getLocation().add(0, 1, 1).getBlock().getType().equals(b.getType())) {
						ChunkLibary.breakblock(b.getLocation().add(0, 1, 1), isGamemode, p1, item);
				}
			}
		}
}