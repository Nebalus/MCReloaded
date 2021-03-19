package de.pixelstudios.messaging;

import org.bukkit.ChatColor;

public class MessageFormatter {
	   private final MessageFile messageFile = new MessageFile("messages.yml");

	   public String format(String key, Object... args) {
	      return this.format(true, key, args);
	   }

	   public String format(boolean prefix, String key, Object... args) {
	      String message = prefix ? this.messageFile.get("prefix") + this.messageFile.get(key) : this.messageFile.get(key);

	      for(int i = 0; i < args.length; ++i) {
	         message = message.replace("{" + i + "}", String.valueOf(args[i]));
	      }

	      return ChatColor.translateAlternateColorCodes('&', message);
	   }

	   public String prefix(String msg) {
	      return ChatColor.translateAlternateColorCodes('&', this.messageFile.get("prefix") + msg);
	   }

	   public MessageFile getMessageFile() {
	      return this.messageFile;
	   }
	}