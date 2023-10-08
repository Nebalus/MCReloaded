package de.nebalus.mc.mcreloaded.listener.player;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class PlayerDeathListener implements Listener {

	@SuppressWarnings("deprecation")
	@EventHandler()
	private void onDeath(PlayerDeathEvent e) {
		Player p = e.getEntity();
		String deathmessage = e.getDeathMessage();

		e.setDeathMessage(null);

		if (deathmessage == null) {
			deathmessage = p.getName() + " just died... so sad";
		}

		for (Player player : Bukkit.getOnlinePlayers()) {
			if (p == player) {
				player.sendMessage(" §c☠ §7" + deathmessage.replace(p.getName(), "You"));
			} else {
				player.sendMessage(" §c☠ §7" + deathmessage);
			}
		}

		ItemStack skullstack = new ItemStack(Material.PLAYER_HEAD);
		SkullMeta skullmeta = (SkullMeta) skullstack.getItemMeta();
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7☠ Cause: " + deathmessage.replace(p.getName(), "This person"));
		lore.add("§7☠ Count: " + (p.getStatistic(Statistic.DEATHS) + 1));
		lore.add(" ");
		lore.add("§4WARNING: The stats are gone if placed");
		skullmeta.setLore(lore);
		skullmeta.setOwner(p.getName());

		skullstack.setItemMeta(skullmeta);

		p.getWorld().dropItemNaturally(p.getEyeLocation(), skullstack);
	}
}