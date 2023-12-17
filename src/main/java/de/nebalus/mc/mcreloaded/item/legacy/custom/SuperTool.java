package de.nebalus.mc.mcreloaded.item.legacy.custom;

import java.util.Collection;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import de.nebalus.mc.mcreloaded.item.legacy.CustomItem;
import de.nebalus.mc.mcreloaded.listener.player.PlayerInteractListener;

public class SuperTool extends CustomItem {

	public SuperTool(Material material, String namespace_key) {
		super(material, namespace_key);
	}

	private void breakblock(Location loc, ItemStack item, Boolean isDropable) {
		Block b = loc.getBlock();
		if (!b.getType().equals(Material.AIR)) {
			if (isDropable) {
				if (item != null) {
					Collection<ItemStack> drops = b.getDrops(item);
					if (!drops.isEmpty()) {
						b.getWorld().dropItemNaturally(b.getLocation(), drops.iterator().next());
						b.setType(Material.AIR);
					}
				} else {
					b.breakNaturally();
				}
			} else {
				b.setType(Material.AIR);
			}
		}
	}

	@Override
	public void executeOnBreak(BlockBreakEvent event) {
		Player p = event.getPlayer();
		Block b = event.getBlock();
		ItemStack item = event.getPlayer().getItemInUse();
		boolean isSurvival = p.getGameMode().equals(GameMode.SURVIVAL);
		BlockFace face = PlayerInteractListener.LASTBLOCKFACE.get(p.getUniqueId());
		
		if (face.equals(BlockFace.NORTH) || face.equals(BlockFace.SOUTH)) {
			int[][] field = {
					{-1, -1, 0},
					{0, -1, 0},
					{1, -1, 0},
					{-1, 0, 0},
					{1, 0, 0},
					{-1, 1, 0},
					{0, 1, 0},
					{1, 1, 0}
			};
			
			breakField(field, b.getLocation(), item, isSurvival);
		}
		
		if (face.equals(BlockFace.DOWN) || face.equals(BlockFace.UP)) {
			int[][] field = {
					{-1, 0, 1},
					{0, 0, 1},
					{1, 0, 1},
					{-1, 0, 0},
					{1, 0, 0},
					{-1, 0, -1},
					{0, 0, -1},
					{1, 0, -1}
			};
			
			breakField(field, b.getLocation(), item, isSurvival);
		}
		
		if (face.equals(BlockFace.WEST) || face.equals(BlockFace.EAST)) {
			int[][] field = {
					{0, -1, -1},
					{0, -1, 0},
					{0, -1, 1},
					{0, 0, -1},
					{0, 0, 1},
					{0, 1, -1},
					{0, 1, 0},
					{0, 1, 1}
			};
			
			breakField(field, b.getLocation(), item, isSurvival);
		}
	}
	
	private void breakField(int[][] field, Location loc, ItemStack item, Boolean isSurvival) {
		for(int i = 0; i < field.length; i++) {
			breakblock(loc.add(field[i][0], field[i][1], field[i][2]), item, isSurvival);
		}	
	}
}
