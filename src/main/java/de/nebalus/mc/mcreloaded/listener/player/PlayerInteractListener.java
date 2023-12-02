package de.nebalus.mc.mcreloaded.listener.player;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.FluidCollisionMode;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {
	public static HashMap<UUID, BlockFace> LASTBLOCKFACE = new HashMap<UUID, BlockFace>();

	@EventHandler
	private void onBlockInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		UUID uuid = p.getUniqueId();
		Block block = p.getTargetBlockExact(5, FluidCollisionMode.ALWAYS);

		if (block == null)
			return;

		if (e.getAction() == Action.LEFT_CLICK_BLOCK) {
			if (LASTBLOCKFACE.containsKey(uuid)) {
				LASTBLOCKFACE.replace(uuid, e.getBlockFace());
			} else {
				LASTBLOCKFACE.put(uuid, e.getBlockFace());
			}
		}
	}
}
