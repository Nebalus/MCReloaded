package io.pixelstudios.libary;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import net.md_5.bungee.api.ChatColor;


public class FormatLibary {
	private static final Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");
	
	public static String format(String message) {
		if(Bukkit.getBukkitVersion().contains("1.16")){
			//Compile Hex colors
			Matcher match = pattern.matcher(message);
			while (match.find()) {
				String color = message.substring(match.start(),match.end());
				message = message.replace(color, ChatColor.of(color)+"");
				match = pattern.matcher(message);
			}
		}
		return ChatColor.translateAlternateColorCodes('&', message);
	}
}
