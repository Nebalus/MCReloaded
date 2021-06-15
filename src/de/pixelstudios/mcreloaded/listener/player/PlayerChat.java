package de.pixelstudios.mcreloaded.listener.player;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import de.pixelstudios.mcreloaded.MCReloaded;
import de.pixelstudios.mcreloaded.utils.Achievements;
import io.pixelstudios.libary.FormatLibary;

public class PlayerChat implements Listener{

	@EventHandler(
			ignoreCancelled = true,
		    priority = EventPriority.HIGHEST
		   )
	public void onChat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		e.setCancelled(true);
		MCReloaded.getPlugin().getPlayerManager().getProfile(p).giveAchievement(Achievements.FIRSTCHAT);
		for(Player all : Bukkit.getOnlinePlayers()) {
			String[] args = e.getMessage().split(" ");
			int count = 0;
			for (int i = 0; i < args.length; i++) {
				if(args[count].equalsIgnoreCase(all.getName())) {
					all.playSound(all.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 3, 2);
				}
				count++;
			}
			
			String message = e.getMessage();
			for(Emotes emote : Emotes.values()) {
				message = message.replace(emote.getCode(), emote.getEmote()+"§r");
			}
			
			all.sendMessage("§7"+p.getName()+" §8» §r"+FormatLibary.format(message));
		}
		Bukkit.getConsoleSender().sendMessage(p.getName()+": "+e.getMessage());
	}
	public enum Emotes {
		
		HEART("<3", "§c❤"),
		NO(":no:", "§c✖"),
		YES(":yes:", "§a✔"),
		CAT(":cat:", "§e= §b⌃● ⋏ ●⌃ §e="),
		ARROW(":arrow:", "§e➜"),
		STAR(":star:", "§6✰"),
		PVP(":pvp:", "§e⚔");
		
		String code;
		String emote;


		private Emotes(String code,String emote) {
			this.code = code;
			this.emote = emote;
		}
		
		public String getCode() {
			return code;
		}
		
		public String getEmote() {
			return emote;
		}
	}
}
