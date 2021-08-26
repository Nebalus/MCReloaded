package io.pixelstudios.libary;

import java.util.Collection;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Waterlogged;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ChunkLibary {

	@SuppressWarnings("incomplete-switch")
	public static boolean isColdBiom(Biome biom) {
		switch(biom) {
			case COLD_OCEAN:
			case DEEP_COLD_OCEAN:
			case DEEP_FROZEN_OCEAN:
			case FROZEN_OCEAN:
			case FROZEN_RIVER:
			case SNOWY_TAIGA_HILLS:
			case SNOWY_BEACH:
			case SNOWY_MOUNTAINS:
			case SNOWY_TAIGA:
			case SNOWY_TAIGA_MOUNTAINS:
			case SNOWY_TUNDRA:
			case ICE_SPIKES:
			case DEEP_OCEAN:
				return true;
		}
		return false;
	}
	@SuppressWarnings("incomplete-switch")
	public static boolean isHotBiom(Biome biom) {
		switch(biom) {
			case DESERT:
			case DESERT_HILLS:
			case DESERT_LAKES:
			case BASALT_DELTAS:
			case SOUL_SAND_VALLEY:
			case NETHER_WASTES:
			case BADLANDS:
			case BADLANDS_PLATEAU:
			case ERODED_BADLANDS:
			case SAVANNA:
			case SAVANNA_PLATEAU:
			case SHATTERED_SAVANNA:
			case SHATTERED_SAVANNA_PLATEAU:
				return true;
		}		
		return false;
	}
	
	public static boolean isSlimeChunk(long seed, int xPos, int zPos) {
		int xPosition = xPos >> 4;
		int zPosition = zPos >> 4;
		Random rnd = new Random(seed + (long)(xPosition * xPosition * 4987142) + (long)(xPosition * 5947611) + (long)(zPosition * zPosition) * 4392871L + (long)(zPosition * 389711) ^ 987234911L);
		return rnd.nextInt(10) == 0;
	}
	public static boolean isWaterBlock(Block block) {
	    if (block.getType() == Material.WATER) {
	        return true;
        }
        BlockData data = block.getBlockData();
        return data instanceof Waterlogged && ((Waterlogged) data).isWaterlogged();
    }
	
	public static void breakblock(Location loc, Boolean dropable, Player player, ItemStack item) {
		Block b = loc.getBlock();
		if(!b.getType().equals(Material.AIR)) {
			if(dropable) {
				if(item != null) {
					Collection<ItemStack> drops = b.getDrops(item);
					if(!drops.isEmpty()) {
						b.getWorld().dropItemNaturally(b.getLocation(), drops.iterator().next());
						b.setType(Material.AIR);
					}
				}else {
					b.breakNaturally();
				}
			}else {
				b.setType(Material.AIR);
			}
		}
	}
}
