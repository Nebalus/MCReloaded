package de.pixelstudios.mcreloaded.datamanagement;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import de.pixelstudios.mcreloaded.ConsoleLogger;
import de.pixelstudios.mcreloaded.MCReloaded;
import de.pixelstudios.mcreloaded.messaging.MessageFormatter;

public class Config {
	private final MCReloaded plugin;
	private File configFile;
	private MessageFormatter messageFormatter;
	private FileConfiguration settings;
	
	public String RESOURCE_PACK_URL;
	public boolean RESOURCE_PACK_ENABLED;
	public boolean RESOURCE_PACK_NOTIFY;
	
	public boolean MECHANICS_ENERGY_ENABLED;
	public boolean MECHANICS_ENERGY_WARNING;
	public double MECHANICS_ENERGY_START_LEVEL;
	public double MECHANICS_ENERGY_RESPAWN_LEVEL;
	public double MECHANICS_ENERGY_DRAIN_RATE;
	public double MECHANICS_ENERGY_COLD_DRAIN_RATE;
	public double MECHANICS_ENERGY_SLEEPING_REFRESH_RATE;
	public double MECHANICS_ENERGY_EXHAUSTION;
	public boolean MECHANICS_ENERGY_COFFEE;
	
	public boolean ACHIEVEMENTS_FIRSTJOIN;
	public boolean ACHIEVEMENTS_STAYHYDRATED;
	public boolean ACHIEVEMENTS_PURIFIED;
	public boolean ACHIEVEMENTS_FIRSTCHAT;
	public boolean ACHIEVEMENTS_GILDEDNETHERITE;
	
	public Config(MCReloaded plugin) {
		this.plugin = plugin;
		this.messageFormatter = plugin.getMessageFormatter();
		loadDefaultSettings();
	}
	private void loadDefaultSettings() {
		if (configFile == null) {
			configFile = new File(plugin.getDataFolder(), "config.yml");
		}
		if (!configFile.exists()) {
			plugin.saveResource("config.yml", false);
			settings = YamlConfiguration.loadConfiguration(configFile);
			ConsoleLogger.info(ConsoleLogger.DATA_MANAGER, messageFormatter.format(false, "console.enable.config-file-created"));
		} else {
			settings = YamlConfiguration.loadConfiguration(configFile);
		}
		matchConfig(settings, configFile);
		loadSettings();
		ConsoleLogger.info(ConsoleLogger.DATA_MANAGER, messageFormatter.format(false, "console.enable.config-file-loaded"));
	}

	
	private void matchConfig(FileConfiguration config, File file) {
		try {
			boolean hasUpdated = false;
			InputStream is = plugin.getResource(file.getName());
			assert is != null;
			InputStreamReader isr = new InputStreamReader(is);
			YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(isr);
			for (String key : defConfig.getConfigurationSection("").getKeys(true)) {
				if (!config.contains(key)) {
					config.set(key, defConfig.get(key));
					hasUpdated = true;
				}
			}
			for (String key : config.getConfigurationSection("").getKeys(true)) {
				if (!defConfig.contains(key) && !key.equalsIgnoreCase("recipe-delay")) {
					config.set(key, null);
					hasUpdated = true;
				}
			}
			if (hasUpdated)
				config.save(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	  public FileConfiguration getSettings() {
			return this.settings;
	}
	private void loadSettings() {
		this.RESOURCE_PACK_URL = settings.getString("ResourcePack.ResourcePackURL");
		this.RESOURCE_PACK_ENABLED = settings.getBoolean("ResourcePack.EnableResourcePack");
		this.RESOURCE_PACK_NOTIFY = settings.getBoolean("ResourcePack.NotifyMessage");
	
		this.MECHANICS_ENERGY_ENABLED = settings.getBoolean("Mechanics.Energy.enabled");
		this.MECHANICS_ENERGY_WARNING = settings.getBoolean("Mechanics.Energy.warning");
		this.MECHANICS_ENERGY_START_LEVEL = settings.getDouble("Mechanics.Energy.start-level");
		this.MECHANICS_ENERGY_RESPAWN_LEVEL = settings.getDouble("Mechanics.Energy.respawn-level");
		this.MECHANICS_ENERGY_DRAIN_RATE = settings.getDouble("Mechanics.Energy.drain-rate");
		this.MECHANICS_ENERGY_COLD_DRAIN_RATE = settings.getDouble("Mechanics.Energy.cold-drain-rate");
		this.MECHANICS_ENERGY_SLEEPING_REFRESH_RATE = settings.getDouble("Mechanics.Energy.sleeping-refresh-rate");
		this.MECHANICS_ENERGY_EXHAUSTION = settings.getDouble("Mechanics.Energy.exhaustion");
		this.MECHANICS_ENERGY_COFFEE = settings.getBoolean("Mechanics.Energy.coffee");
		
		this.ACHIEVEMENTS_FIRSTJOIN = settings.getBoolean("Achievements.FIRSTJOIN");
		this.ACHIEVEMENTS_FIRSTCHAT = settings.getBoolean("Achievements.FIRSTCHAT");
		this.ACHIEVEMENTS_GILDEDNETHERITE = settings.getBoolean("Achievements.GILDEDNETHERITE");
		this.ACHIEVEMENTS_PURIFIED = settings.getBoolean("Achievements.PURIFIED");
		this.ACHIEVEMENTS_STAYHYDRATED = settings.getBoolean("Achievements.STAYHYDRATED");
		
	}
}
