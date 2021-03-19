package de.pixelstudios.messaging;

import java.io.InputStreamReader;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class MessageFile {
	   private FileConfiguration config = null;

	   public MessageFile(String name) {
	      this.config = YamlConfiguration.loadConfiguration(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream(name)));
	   }

	   public FileConfiguration getConfig() {
	      return this.config;
	   }

	   public String get(String key) {
	      return this.config.getString(key);
	   }
	}