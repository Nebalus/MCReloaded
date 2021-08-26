package de.pixelstudios.mcreloaded;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerLoadEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import de.pixelstudios.mcreloaded.commands.CordinatesCommand;
import de.pixelstudios.mcreloaded.commands.FlyCommand;
import de.pixelstudios.mcreloaded.commands.GmCommand;
import de.pixelstudios.mcreloaded.commands.MCReloadedCommand;
import de.pixelstudios.mcreloaded.commands.MenuCommand;
import de.pixelstudios.mcreloaded.commands.PingCommand;
import de.pixelstudios.mcreloaded.commands.ProfileCommand;
import de.pixelstudios.mcreloaded.commands.ReloadCommand;
import de.pixelstudios.mcreloaded.commands.RepairCommand;
import de.pixelstudios.mcreloaded.commands.StopCommand;
import de.pixelstudios.mcreloaded.datamanagement.Cache;
import de.pixelstudios.mcreloaded.datamanagement.Config;
import de.pixelstudios.mcreloaded.datamanagement.LiteSQL;
import de.pixelstudios.mcreloaded.items.GrapplingHook;
import de.pixelstudios.mcreloaded.items.Invisible_Item_Frame;
import de.pixelstudios.mcreloaded.items.WarpCrystal;
import de.pixelstudios.mcreloaded.listener.entity.EntityBlockPlace;
import de.pixelstudios.mcreloaded.listener.entity.EntityDamage;
import de.pixelstudios.mcreloaded.listener.entity.EntityDamageByEntity;
import de.pixelstudios.mcreloaded.listener.entity.EntityDeath;
import de.pixelstudios.mcreloaded.listener.entity.EntityExplode;
import de.pixelstudios.mcreloaded.listener.entity.EntitySpawn;
import de.pixelstudios.mcreloaded.listener.entity.EntityTargetLivingEntity;
import de.pixelstudios.mcreloaded.listener.player.PlayerBlockBreak;
import de.pixelstudios.mcreloaded.listener.player.PlayerChat;
import de.pixelstudios.mcreloaded.listener.player.PlayerDeath;
import de.pixelstudios.mcreloaded.listener.player.PlayerDropItem;
import de.pixelstudios.mcreloaded.listener.player.PlayerInteract;
import de.pixelstudios.mcreloaded.listener.player.PlayerInteractEntity;
import de.pixelstudios.mcreloaded.listener.player.PlayerInventory;
import de.pixelstudios.mcreloaded.listener.player.PlayerItemConsume;
import de.pixelstudios.mcreloaded.listener.player.PlayerJoin;
import de.pixelstudios.mcreloaded.listener.player.PlayerMove;
import de.pixelstudios.mcreloaded.listener.player.PlayerQuit;
import de.pixelstudios.mcreloaded.listener.server.InventoryPickupItem;
import de.pixelstudios.mcreloaded.listener.server.LeavesDecay;
import de.pixelstudios.mcreloaded.listener.server.PrepareCraftEvent;
import de.pixelstudios.mcreloaded.listener.server.ServerListPing;
import de.pixelstudios.mcreloaded.listener.server.SetResourcePack;
import de.pixelstudios.mcreloaded.listener.server.StructureGrowEvent;
import de.pixelstudios.mcreloaded.manager.ItemManager;
import de.pixelstudios.mcreloaded.manager.PlayerManager;
import de.pixelstudios.mcreloaded.manager.UserProfile;
import de.pixelstudios.mcreloaded.manager.VisualsManager;
import de.pixelstudios.mcreloaded.messaging.MessageFormatter;
import de.pixelstudios.mcreloaded.utils.SleepSkipper;
import de.pixelstudios.mcreloaded.utils.Utils;
import fr.mrmicky.fastparticle.FastParticle;
import fr.mrmicky.fastparticle.ParticleType;
import io.pixelstudios.libary.ChunkLibary;
import io.pixelstudios.libary.MathLibary;
import net.minecraft.server.MinecraftServer;

public class MCReloaded extends JavaPlugin implements Listener{
	public static String name = "MCReloaded";
	public static String chatprefix = "§aMCReloaded »§r ";
	public static String infoprefix = "§f§lINFO: §r";
	private static MCReloaded instance;
	public static List<World> Worlds = new ArrayList<World>();
	
	public static String serverpath;
	
	private MessageFormatter messageFormatter;
	private PlayerManager playerManager;
	private VisualsManager visualsManager;
	private ItemManager itemManager;
	private LiteSQL liteSQL;
	
	private boolean loaded = true;
	
	private Config config;
	public void onEnable() {
		long setuptime = System.currentTimeMillis();
		this.messageFormatter = new MessageFormatter();
		instance = this;
		serverpath = MCReloaded.getPlugin().getDataFolder().getAbsolutePath().substring(0, MCReloaded.getPlugin().getDataFolder().getAbsolutePath().length() - (9 + MCReloaded.getPlugin().getName().length()));
			
		ConsoleLogger.info(ConsoleLogger.BOOT_LOADER,"Loading "+ name+"...");
		// VERSION CHECK
		if (!Utils.isRunningMinecraft(1, 17, 1)) {
			String ver = Bukkit.getServer().getBukkitVersion().split("-")[0];
			ConsoleLogger.error(null,"-----------------------------------------------------------");
			ConsoleLogger.error(null,messageFormatter.format(false, "console.enable.error.wrong-version.line1",ver));
		    ConsoleLogger.error(null,messageFormatter.format(false, "console.enable.error.wrong-version.line2"));
		    ConsoleLogger.error(null,"-----------------------------------------------------------");
			loaded = false;
			Bukkit.getPluginManager().disablePlugin(this);
			return;
		}

			// SPIGOT CHECK
		if (!Utils.isRunningSpigot()) {
			ConsoleLogger.error(null,"-----------------------------------------------------------");
			ConsoleLogger.error(null,messageFormatter.format(false, "console.enable.error.not-spigot.line1"));
		    ConsoleLogger.error(null,messageFormatter.format(false, "console.enable.error.not-spigot.line2"));
		    ConsoleLogger.error(null,"-----------------------------------------------------------");
		    loaded = false;
		    Bukkit.getPluginManager().disablePlugin(this);
		    return;
		}
		if (!Bukkit.getOnlineMode()) {
			ConsoleLogger.error(null,"-----------------------------------------------------------");
			ConsoleLogger.error(null,messageFormatter.format(false, "console.enable.error.not-onlinemode.line1"));
		    ConsoleLogger.error(null,"-----------------------------------------------------------");
		    loaded = false;
		    Bukkit.getPluginManager().disablePlugin(this);
		    return;
		}
		liteSQL = new LiteSQL(this);
		liteSQL.connect();
		Worlds.add(Bukkit.getWorld("world"));
		Worlds.add(Bukkit.getWorld("world_nether"));
		Worlds.add(Bukkit.getWorld("world_the_end"));
		config = new Config(this);
		
		boolean resourcePack = config.RESOURCE_PACK_ENABLED;
		if (resourcePack) {
			if (config.RESOURCE_PACK_URL.isEmpty()) {
				ConsoleLogger.error(null,messageFormatter.format(false, "console.enable.resourcepack.no-url"));
				Bukkit.getPluginManager().disablePlugin(this);
				loaded = false;
				return;
			} else {
				ConsoleLogger.info(null,messageFormatter.format(false, "console.enable.resourcepack.enabled"));
			}
		} else ConsoleLogger.info(null,messageFormatter.format(false, "console.enable.resourcepack.disabled"));
		playerManager = new PlayerManager(this);
		visualsManager = new VisualsManager(this);
		itemManager = new ItemManager(this);
		for(Player p :Bukkit.getOnlinePlayers()) {
			playerManager.loadProfile(p);
		}
		loadListeners(this);
		loadCommands();
		loadTimers(this);
		loadCache();
		itemManager.loadItemManager();
		runLoop(this);
		runAnimator(this);
		Invisible_Item_Frame.droppedFrames = new HashSet<>();
		if(LiteSQL.isConnected()) {
			try {	
				PreparedStatement ps = LiteSQL.getConnection().prepareStatement("SELECT seq FROM sqlite_sequence WHERE name = \"playerdata\"");
				ResultSet rs = ps.executeQuery();
				Integer cache = rs.getInt("seq");
				if(cache > 1) {
					ConsoleLogger.info(ConsoleLogger.CACHE, messageFormatter.format(false, "console.enable.cache.few-players-cached", cache));
				}else {
					ConsoleLogger.info(ConsoleLogger.CACHE, messageFormatter.format(false, "console.enable.cache.one-player-cached", cache));
				}
			}catch (SQLException | ArrayIndexOutOfBoundsException e) {
				ConsoleLogger.info(ConsoleLogger.CACHE, messageFormatter.format(false, "console.enable.cache.nobody-cached"));
			}
		}
		ConsoleLogger.info(ConsoleLogger.BOOT_LOADER, messageFormatter.format(false, "console.enable.plugin-loaded", name,System.currentTimeMillis() - setuptime));
		if (this.getDescription().getVersion().contains("Alpha")) {
			ConsoleLogger.warning(null, messageFormatter.format(false, "console.warning.alpha-version"));
		}
	}
	public void onDisable() {
		if (!loaded) return;
		ConsoleLogger.info(ConsoleLogger.FLUSH_MANAGER, "Prepearing for shutdown!");
		playerManager.flushProfiles();
		ConsoleLogger.info(ConsoleLogger.FLUSH_MANAGER, "Flush completed!");
		for(World w: Bukkit.getWorlds()) {
			for(ArmorStand as : w.getEntitiesByClass(ArmorStand.class)) {		
					if(!as.hasGravity()) {
						if(!as.isCollidable()) {
							if(as.getCustomName().equals("byjfbvxyBJIUG892psx-ssfsdjkbfhijkgcvuzg127263489263461924528648")) {
								Location loc = as.getEyeLocation();
								loc.setY(loc.getY()+1);
								if(loc.getBlock().getType().equals(Material.BARRIER)){
									loc.getBlock().setType(Material.AIR);
									
								}
								as.remove();
						}
					}
				}
			}
		}
		liteSQL.disconnect();
		instance = null;
		getServer().resetRecipes();
		getServer().getScheduler().cancelTasks(this);
		messageFormatter = null;
	}
	 
	private boolean loadListeners(MCReloaded mcreloaded) {
		int counter;
		counter = 0;
		//Customitem events
		Bukkit.getPluginManager() .registerEvents(new Invisible_Item_Frame(), mcreloaded);	
		counter++;
		Bukkit.getPluginManager() .registerEvents(new GrapplingHook(), mcreloaded);	
		counter++;
		
		//Entity events
		Bukkit.getPluginManager() .registerEvents(new EntityDeath(), mcreloaded);	
		counter++;
		Bukkit.getPluginManager() .registerEvents(new EntityDamageByEntity(), mcreloaded);	
		counter++;
		Bukkit.getPluginManager() .registerEvents(new EntitySpawn(), mcreloaded);	
		counter++;
		Bukkit.getPluginManager() .registerEvents(new EntityExplode(), mcreloaded);	
		counter++;
		Bukkit.getPluginManager() .registerEvents(new EntityDamage(), mcreloaded);	
		counter++;
		Bukkit.getPluginManager() .registerEvents(new EntityTargetLivingEntity(this), mcreloaded);	
		counter++;
		Bukkit.getPluginManager() .registerEvents(new EntityBlockPlace(), mcreloaded);	
		counter++;
		
		//Player events
		Bukkit.getPluginManager() .registerEvents(new PlayerQuit(mcreloaded), mcreloaded);	
		counter++;
		Bukkit.getPluginManager() .registerEvents(new PlayerMove(), mcreloaded);	
		counter++;
		Bukkit.getPluginManager() .registerEvents(new PlayerJoin(mcreloaded), mcreloaded);	
		counter++;
		Bukkit.getPluginManager() .registerEvents(new PlayerDeath(mcreloaded), mcreloaded);	
		counter++;
		Bukkit.getPluginManager() .registerEvents(new PlayerChat(), mcreloaded);
		counter++;
		Bukkit.getPluginManager() .registerEvents(new PlayerInventory(), mcreloaded);
		counter++;
		Bukkit.getPluginManager() .registerEvents(new PlayerInteract(), mcreloaded);
		counter++;
		Bukkit.getPluginManager() .registerEvents(new PlayerInteractEntity(), mcreloaded);
		counter++;
		Bukkit.getPluginManager() .registerEvents(new PlayerBlockBreak(), mcreloaded);
		counter++;
		Bukkit.getPluginManager() .registerEvents(new PlayerItemConsume(this), mcreloaded);
		counter++;
		Bukkit.getPluginManager() .registerEvents(new PlayerDropItem(), mcreloaded);
		counter++;
		//Block events
	
		//Server events
		Bukkit.getPluginManager() .registerEvents(mcreloaded, mcreloaded);
		counter++;
		Bukkit.getPluginManager() .registerEvents(new ServerListPing(), mcreloaded);
		counter++;
		Bukkit.getPluginManager() .registerEvents(new StructureGrowEvent(), mcreloaded);
		counter++;
		Bukkit.getPluginManager() .registerEvents(new SetResourcePack(mcreloaded), mcreloaded);
		counter++;
		Bukkit.getPluginManager() .registerEvents(new PrepareCraftEvent(), mcreloaded);
		counter++;
		Bukkit.getPluginManager() .registerEvents(new InventoryPickupItem(), mcreloaded);
		counter++;
		Bukkit.getPluginManager() .registerEvents(new LeavesDecay(this), mcreloaded);
		counter++;
		ConsoleLogger.info(null, messageFormatter.format(false, "console.enable.listeners-loaded",counter));
		return false;
	}	
	private boolean loadCommands() {
		int counter;
		counter = 0;
		
		getCommand("repair").setExecutor(new RepairCommand(this));
		counter++;
		getCommand("menu").setExecutor(new MenuCommand());
		counter++;
		getCommand("gm").setExecutor(new GmCommand(this));
		getCommand("gm").setTabCompleter(new GmCommand(this));
		getCommand("gamemode").setExecutor(new GmCommand(this));
		getCommand("gamemode").setTabCompleter(new GmCommand(this));
		counter++;
		getCommand("ping").setExecutor(new PingCommand(this));
		getCommand("ping").setTabCompleter(new PingCommand(this));
		counter++;
		getCommand("fly").setExecutor(new FlyCommand(this));
		getCommand("fly").setTabCompleter(new FlyCommand(this));
		counter++;
		
		getCommand("mcreloaded").setExecutor(new MCReloadedCommand(this));
		getCommand("mcreloaded").setTabCompleter(new MCReloadedCommand(this));
		
		getCommand("mcr").setExecutor(new MCReloadedCommand(this));
		getCommand("mcr").setTabCompleter(new MCReloadedCommand(this));
		counter++;
		
		getCommand("cordinates").setExecutor(new CordinatesCommand(this));
        getCommand("cords").setExecutor(new CordinatesCommand(this));
        counter++;
		
        getCommand("reload").setExecutor(new ReloadCommand(this));
        getCommand("rl").setExecutor(new ReloadCommand(this));
        counter++;
        
        getCommand("stop").setExecutor(new StopCommand(this));
        counter++;
        
		getCommand("profile").setExecutor(new ProfileCommand(this));
		counter++;
		ConsoleLogger.info(null, messageFormatter.format(false, "console.enable.commands-loaded",counter));
		return false;
	}
	private boolean loadTimers(MCReloaded mcreloaded) {
		int counter;
		counter = 0; 
		new SleepSkipper(mcreloaded).updater();
		counter++;
		ConsoleLogger.info(null, messageFormatter.format(false, "console.enable.timers-loaded",counter));
		return false;
	}
	
	private void loadCache() {
		for(World w : Bukkit.getWorlds()) {
			File warp_crystal = new File(w.getWorldFolder()+"/data/mcdata/warp_crystal.yml");
			YamlConfiguration warp_crystalyml = YamlConfiguration.loadConfiguration(warp_crystal);			
			List<String> uuids = (List<String>) warp_crystalyml.getStringList("UUID");
			for(String s : uuids) {
				Integer X = warp_crystalyml.getInt(s+".X");
				Integer Y = warp_crystalyml.getInt(s+".Y");
				Integer Z = warp_crystalyml.getInt(s+".Z");
				Location loc = new Location(w, X, Y, Z);
				Integer WarpCharge = warp_crystalyml.getInt(s+".WarpCharge");
				UUID owneruuid = UUID.fromString(warp_crystalyml.getString(s+".OwnerUUID"));
				String name = warp_crystalyml.getString(s+".Name");
				int visibility = warp_crystalyml.getInt(s+".Visibility");
				WarpCrystal warpcrystal = new WarpCrystal(loc,WarpCharge,s,owneruuid,name,visibility);
				Cache.warp_crystal.add(warpcrystal);
			}
		}
		
		ConsoleLogger.info(ConsoleLogger.DATA_MANAGER, messageFormatter.format(false, "console.enable.cache-loaded"));
	}
	
	@EventHandler
	private void onServerReload(ServerLoadEvent e) {
		if (e.getType() == ServerLoadEvent.LoadType.RELOAD) {
			for (Player p : getServer().getOnlinePlayers()) {
				p.sendMessage("§cDETECTED SERVER RELOAD");
				p.sendMessage("    §6Recipes may have been impacted");
				p.sendMessage("    §6Relog to update your recipes");
			}
			ConsoleLogger.warning(null,"DETECTED SERVER RELOAD");
			ConsoleLogger.info(null,"Server reloads will impact recipes");
			ConsoleLogger.info(null,"Players will need to relog to re-enable custom recipes");
			ConsoleLogger.info(null,"A warning has been sent to each player that is online right now");
		}
	}
	
	@SuppressWarnings("deprecation")
	private void runLoop(MCReloaded mcreloaded) {
		Bukkit.getScheduler().scheduleAsyncRepeatingTask(mcreloaded, new Runnable() {		
			@SuppressWarnings("resource")
			@Override
			public void run() {
				Runtime r = Runtime.getRuntime();
				long memUsed = (r.totalMemory() - r.freeMemory()) / 1048576;
				double tps = 0d;
				String tpsstring = null;
				for ( double firsttps : MinecraftServer.getServer().recentTps ){
					if(tps <= 0d) {
						tps = MathLibary.roundUp(firsttps);
						tpsstring = tps > 15d ? "§a"+tps : tps > 10d ? "§e"+tps : "§c"+tps;
					}
		        }
				for(Player p : Bukkit.getOnlinePlayers()) {	
					final int ping = p.getPing();
					p.setPlayerListHeader(" \n \n");
					p.setPlayerListFooter(" \n"
							+ " \n"
							+ "§7┃§r §8Ping: "+(ping < 100 ? "§a"+ping : ping < 300 ? "§e"+ping : "§c"+ping) +" §7┃§8 Ram: §7"+memUsed+"§8/§7"+(r.totalMemory()/ 1048576)+"§6mb§7 ┃§8 Tps: "+tpsstring +" §7┃");
				}
			}
		}, 0, 1);
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(mcreloaded, new Runnable() {		
			@Override
			public void run() {
				try {
					Utils.checkWarpCrystal();
				}catch(ConcurrentModificationException ex) {}
				for(Player p : Bukkit.getOnlinePlayers()) {
					ItemManager.debugInventory(p);
				}
				
			}
		}, 0, 20);
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(mcreloaded, new Runnable() {		
			@Override
			public void run() {
				int triggerprozent = 20;
				for(Player p : Bukkit.getOnlinePlayers()) {
					
					
			        int healthProzent = (int) p.getHealth()*100/(int) p.getMaxHealth();
			        
					if((int) healthProzent <= triggerprozent) {
						if(p.getGameMode().equals(GameMode.ADVENTURE) || p.getGameMode().equals(GameMode.SURVIVAL)) {
							p.playSound(p.getEyeLocation(), "mcreloaded:heartbeat",1.0f ,1.0f);
							Bukkit.getScheduler().runTask(getPlugin(),new Runnable() {
								@Override
								public void run() {
									p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60, 1));
									p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 60, 0));
								}
							});		
							visualsManager.triggerHeartAnimation(p);
			            }
					}
				}
			}
		}, 0, 25);
		Bukkit.getScheduler().scheduleAsyncRepeatingTask(mcreloaded, new Runnable() {		
			@Override
			public void run() {
				for(Player p : Bukkit.getOnlinePlayers()) {
					if(p.getGameMode().equals(GameMode.ADVENTURE) || p.getGameMode().equals(GameMode.SURVIVAL)) {
						UserProfile pp = playerManager.getProfile(p);
						if(!pp.isLoading()) {
							if(config.MECHANICS_ENERGY_ENABLED) {
								double energy = pp.getEnergy();
								if(p.isSleeping()) {
									energy += config.MECHANICS_ENERGY_SLEEPING_REFRESH_RATE;
								}else {
									energy -= config.MECHANICS_ENERGY_DRAIN_RATE;
									if(ChunkLibary.isColdBiom(p.getLocation().getBlock().getBiome()) && p.getWorld().getHighestBlockAt(p.getEyeLocation()).getLocation().getBlockY() <= p.getEyeLocation().getY()) {
										energy -= config.MECHANICS_ENERGY_COLD_DRAIN_RATE;
									}
								}
								pp.setEnergy(energy);
							}
							double thirst = pp.getThirst();
							thirst -= 0.0245;
							pp.setThirst(thirst);
						}
					}
				}
			}
		}, 0, 100);
	}
	private HashMap<UUID, Boolean> tpupordown = new HashMap<>();
	private HashMap<UUID, Integer> tpup_down = new HashMap<>();
	private void runAnimator(MCReloaded mcreloaded) {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(mcreloaded, new Runnable() {		
			@Override
			public void run() {
				for(World w: Bukkit.getWorlds()) {
					for(ArmorStand as : w.getEntitiesByClass(ArmorStand.class)) {		
						if(!as.hasGravity()) {
							if(!as.isCollidable()) {
								if(as.getCustomName().equals("byjfbvxyBJIUG892psx-ssfsdjkbfhijkgcvuzg127263489263461924528648")) {
									if(tpupordown.get(as.getUniqueId()) == null) {
										tpupordown.put(as.getUniqueId(), true);
										tpup_down.put(as.getUniqueId(), 1);
									}
									if(tpupordown.get(as.getUniqueId()) == true) {			
										if(tpup_down.get(as.getUniqueId()) >= 50) {
											tpupordown.put(as.getUniqueId(), false);
										}else {
											int amount = tpup_down.get(as.getUniqueId());
											amount++;
											tpup_down.put(as.getUniqueId(), amount);
											Location loc = as.getLocation();
											loc.setY(as.getLocation().getY() + 0.01f);
											loc.setYaw(as.getLocation().getYaw()+5f);
											as.teleport(loc);
										}
									}else {
										if(tpup_down.get(as.getUniqueId()) <= 0) {
											tpupordown.put(as.getUniqueId(), true);
										}else {
											int amount = tpup_down.get(as.getUniqueId());
											amount--;
											tpup_down.put(as.getUniqueId(), amount);
											Location loc = as.getLocation();
											loc.setY(as.getLocation().getY() - 0.01f);
											loc.setYaw(as.getLocation().getYaw()+5f);
											as.teleport(loc);
											}
										}
									Location loc = as.getEyeLocation();
									loc.setY(loc.getY()+1);
									FastParticle.spawnParticle(w, ParticleType.END_ROD, loc, 1, 0.1f, 0f, 0.1f, 0.01f);
									loc.setY(loc.getY()+1);
									FastParticle.spawnParticle(w, ParticleType.ENCHANTMENT_TABLE, loc, 1, 0f, 0f, 0f, 1f);
									loc.setY(loc.getY()-1);
									for (int i = 0; i < 12; ++i ) {
										loc.setY(loc.getY()+0.3);
										FastParticle.spawnParticle(w, ParticleType.REDSTONE, loc, 1, 0f, 0f, 0f, 0f, new Particle.DustOptions(Color.AQUA,1));
									}
									
									FastParticle.spawnParticle(w, ParticleType.REDSTONE, loc, 10, 0.4f, 0f, 0.4f, 0f, new Particle.DustOptions(Color.BLACK,3));
									
									
								}
							}
						}
					}
				}
			}
		}, 0, 2);
	}
	
	public Config getMCReloadedConfig() {
		return this.config;
	}
	public PlayerManager getPlayerManager() {
		return this.playerManager;
	}
	public MessageFormatter getMessageFormatter() {
		return this.messageFormatter;
	}
	public static MCReloaded getPlugin() {
		return instance;
	}
}

