package de.pixelstudios.listener.server;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.Tag;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.Leaves;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.LeavesDecayEvent;

import de.pixelstudios.MCReloaded;

public class LeavesDecay implements Listener{

	private MCReloaded plugin;
	
	public LeavesDecay(MCReloaded plugin) {
		this.plugin = plugin;
	}
	
	private static final BlockFace[] NEIGHBORS;
	private final Set<Block> scheduledBlocks = new HashSet<Block>();
 	@EventHandler(
	      ignoreCancelled = true,
	      priority = EventPriority.MONITOR
	   )
	   public void onBlockBreak(BlockBreakEvent event) {
	      this.onBlockRemove(event.getBlock(), 5);
	   }

	   @EventHandler(
		  ignoreCancelled = true,
	      priority = EventPriority.MONITOR
	   )
	   public void onLeavesDecay(LeavesDecayEvent event) {
	      this.onBlockRemove(event.getBlock(), 2);
	   }

	   private void onBlockRemove(Block oldBlock, long delay) {
	      if (Tag.LOGS.isTagged(oldBlock.getType()) || Tag.LEAVES.isTagged(oldBlock.getType())) {
	          BlockFace[] var5 = NEIGHBORS;
	           int var6 = var5.length;
	               for(int var7 = 0; var7 < var6; ++var7) {
	                  BlockFace neighborFace = var5[var7];
	                  Block block = oldBlock.getRelative(neighborFace);
	                  if (Tag.LEAVES.isTagged(block.getType())) {
	                     Leaves leaves = (Leaves)block.getBlockData();
	                     if (!leaves.isPersistent() && !this.scheduledBlocks.contains(block)) {
	                        this.scheduledBlocks.add(block);
	                        plugin.getServer().getScheduler().runTaskLater(plugin, () -> {
	                        this.decay(block);
	                        }, delay);
	                     }
	                  }
	               }

	            }
	         }
	      

	   private void decay(Block block) {
	      if (this.scheduledBlocks.remove(block)) {
	         if (Tag.LEAVES.isTagged(block.getType())) {
	            Leaves leaves = (Leaves)block.getBlockData();
	            if (!leaves.isPersistent()) {
	               if (leaves.getDistance() >= 7) {
	                  LeavesDecayEvent event = new LeavesDecayEvent(block);
	                  MCReloaded.getPlugin().getServer().getPluginManager().callEvent(event);
	                  if (!event.isCancelled()) {
	                     block.getWorld().spawnParticle(Particle.BLOCK_DUST, block.getLocation().add(0.5D, 0.5D, 0.5D), 8, 0.2D, 0.2D, 0.2D, 0.0D, block.getType().createBlockData());
	                     block.getWorld().playSound(block.getLocation(), Sound.BLOCK_GRASS_BREAK, SoundCategory.BLOCKS, 0.05F, 1.2F);
	                     block.breakNaturally();
	                  }
	               }
	            }
	         }
	      }
	   }

	   static {
	      NEIGHBORS = new BlockFace[]{BlockFace.UP, BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST, BlockFace.DOWN};
	   }
	   
}
