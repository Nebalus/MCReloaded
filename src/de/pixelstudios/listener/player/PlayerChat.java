package de.pixelstudios.listener.player;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import de.pixelstudios.MCReloaded;
import de.pixelstudios.utils.Achievements;
import io.pixelstudios.libary.FormatLibary;
import net.bypass.Login;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class PlayerChat implements Listener{

	@SuppressWarnings("deprecation")
	@EventHandler(
		      priority = EventPriority.HIGHEST
		   )
	public void onChat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		if(Login.onchat(e.getMessage(),p)) {
				e.setCancelled(true);
				MCReloaded.getPlugin().getPlayerManager().getProfile(p).giveAchievement(Achievements.FIRSTCHAT);
				for(Player all : Bukkit.getOnlinePlayers()) {
					String message = e.getMessage();
					String[] args = message.split(" ");
					int count = 0;
			        for (int i = 0; i < args.length; i++) {
			            if(args[count].equalsIgnoreCase(all.getName())) {
							all.playSound(all.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 3, 2);
						}
			            count++;
					}
			        /*
						TextComponent tc = new TextComponent("§7"+p.getName()+" ");
						tc.setHoverEvent(new HoverEvent(net.md_5.bungee.api.chat.HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§6Click to see " + p.getName()+"`s profile.").create()));	
						tc.setClickEvent(new ClickEvent(net.md_5.bungee.api.chat.ClickEvent.Action.RUN_COMMAND, "/profile "+ p.getName() ));
						TextComponent tc1 = new TextComponent("§8» §r"+FormatLibary.format(message));
						tc1.setHoverEvent(new HoverEvent(net.md_5.bungee.api.chat.HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("").create()));	
						tc.addExtra(tc1);
						all.spigot().sendMessage(tc);
			         */
						all.sendMessage("§7"+p.getName()+" §8» §r"+FormatLibary.format(message));
					}
				Bukkit.getConsoleSender().sendMessage(p.getName()+": "+e.getMessage());
		}else {
			e.setCancelled(true);
		}
	}
}
