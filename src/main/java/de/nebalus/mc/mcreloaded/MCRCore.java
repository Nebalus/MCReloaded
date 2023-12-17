package de.nebalus.mc.mcreloaded;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import de.nebalus.mc.mcreloaded.data.BukkitRegister;
import de.nebalus.mc.mcreloaded.data.DataManager;

public class MCRCore extends JavaPlugin {
	private static MCRCore instance;

	private static DataManager dataManager;

	private boolean failed;

	@Override
	public void onLoad() {
		failed = false;
		instance = this;

		super.onLoad();
	}

	@Override
	public void onEnable() {
		long timestamp = System.currentTimeMillis();

//		    __  ___  _____   ___         __                __          __
// 		   /  |/  / / ___/  / _ \ ___   / / ___  ___ _ ___/ / ___  ___/ /
//		  / /|_/ / / /__   / , _// -_) / / / _ \/ _ `// _  / / -_)/ _  /
//		 /_/  /_/  \___/  /_/|_| \__/ /_/  \___/\_,_/ \_,_/  \__/ \_,_/

		// NOTE: Dont change ist is formated correctly
		System.out.println("############################################################################");
		System.out.println("#                                                                          #");
		System.out.println("#        §6__  ___  _____   §a___         __                __          __§r     #");
		System.out.println("#       §6/  |/  / / ___/  §a/ _ \\ ___   / / ___  ___ _ ___/ / ___  ___/ /§r     #");
		System.out.println("#      §6/ /|_/ / / /__   §a/ , _// -_) / / / _ \\/ _ `// _  / / -_)/ _  /§r      #");
		System.out.println("#     §6/_/  /_/  \\___/  §a/_/|_| \\__/ /_/  \\___/\\_,_/ \\_,_/  \\__/ \\_,_/§r       #");
		System.out.println("#                               by Nebalus                                 #");
		System.out.println("#                                                                          #");
		System.out.println("############################################################################");

		
		dataManager = new DataManager(instance);
		dataManager.preLoad();
		dataManager.load();

		BukkitRegister.registerListeners();
		BukkitRegister.registerCommands();

		System.out.println("Enabled! (" + (System.currentTimeMillis() - timestamp) + "ms)");

		super.onEnable();
	}

	@Override
	public void onDisable() {
		long timestamp = System.currentTimeMillis();

		if (dataManager != null && !failed) {
			System.out.println("Saving data...");
			dataManager.save();
		}

		// Löscht alle modifizierte Rezepte von dem Server
		getServer().resetRecipes();

		Bukkit.getScheduler().cancelTasks(this);

		System.out.println("Disabled! (" + (System.currentTimeMillis() - timestamp) + "ms)");

		super.onDisable();
	}

	public void fail() {
		failed = true;
		Bukkit.getPluginManager().disablePlugin(this);
	}

	public boolean isFailed() {
		return failed;
	}

	public DataManager getDataManager() {
		return dataManager;
	}

	public static MCRCore getInstance() {
		return instance;
	}
}
