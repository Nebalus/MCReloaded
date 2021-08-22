package de.pixelstudios.mcreloaded.utils;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class Actionbar {

	private String message;
	
	public Actionbar(String msg){
		this.message = msg;
	}
	public void send(Player p) {
		TextComponent text_component = new TextComponent(message);
		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, text_component);
	}
}
