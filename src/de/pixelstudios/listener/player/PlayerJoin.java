package de.pixelstudios.listener.player;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.EntityEffect;
import org.bukkit.FluidCollisionMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.PistonMoveReaction;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityCategory;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Pose;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Entity.Spigot;
import org.bukkit.entity.memory.MemoryKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.loot.LootTable;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

import com.mysql.jdbc.Util;

import de.pixelstudios.MCReloaded;
import de.pixelstudios.datamanagement.LiteSQL;
import de.pixelstudios.items.manager.GUIicons;
import de.pixelstudios.manager.OfflinePlayerProfile;
import de.pixelstudios.manager.PlayerManager;
import de.pixelstudios.manager.ItemManager.Recipes;
import de.pixelstudios.manager.user.UserProfile;
import de.pixelstudios.messaging.MessageFormatter;
import de.pixelstudios.utils.Achievements;
import de.pixelstudios.utils.Utils;

public class PlayerJoin implements Listener{

	private PlayerManager playermanager;
	private MessageFormatter messageFormatter;
	
	public PlayerJoin(MCReloaded plugin) {
		this.playermanager = plugin.getPlayerManager();
		this.messageFormatter = plugin.getMessageFormatter();
	}

	@SuppressWarnings("deprecation")
	@EventHandler(
			  ignoreCancelled = true,
			  priority = EventPriority.HIGHEST
		   )
	public void onJoin(org.bukkit.event.player.PlayerJoinEvent e) {
		Player p = e.getPlayer();
		UserProfile up = playermanager.getProfile(p);
		p.discoverRecipes(Recipes.COOKIE.getKeys());
		p.discoverRecipes(Recipes.GRAVEL.getKeys());
		p.discoverRecipes(Recipes.STONE.getKeys());
		p.discoverRecipes(Recipes.FLINT.getKeys());
		p.discoverRecipes(Recipes.DIAMOND_HORSE_ARMOR.getKeys());
		p.discoverRecipes(Recipes.IRON_HORSE_ARMOR.getKeys());
		p.discoverRecipes(Recipes.LEATHER_HORSE_ARMOR.getKeys());
		p.discoverRecipes(Recipes.GOLDEN_HORSE_ARMOR.getKeys());
		p.discoverRecipes(Recipes.NAME_TAG.getKeys());
		p.discoverRecipes(Recipes.SADDLE.getKeys());
		p.discoverRecipes(Recipes.SPECTRAL_ARROW.getKeys());
		p.discoverRecipes(Recipes.CHEST.getKeys());
		p.discoverRecipes(Recipes.ICE.getKeys());
		p.discoverRecipes(Recipes.PORTABLE_CRAFTING_TABLE.getKeys());
		p.discoverRecipes(Recipes.SUPER_PICKAXE.getKeys());
		p.discoverRecipes(Recipes.SUPER_SHOVEL.getKeys());
		p.discoverRecipes(Recipes.INVISIBLE_ITEM_FRAME.getKeys());
		p.discoverRecipes(Recipes.GRAPPLING_HOOK.getKeys());
		p.discoverRecipes(Recipes.COFFEE.getKeys());
		p.discoverRecipes(Recipes.COLD_MILK.getKeys());
		p.discoverRecipes(Recipes.HOT_MILK.getKeys());
		p.discoverRecipes(Recipes.HEART_OF_THE_MINE.getKeys());
		p.discoverRecipes(Recipes.PORTABLE_ENDERCHEST.getKeys());
		p.discoverRecipes(Recipes.SUPER_AXE.getKeys());
		p.discoverRecipes(Recipes.EXPERIENCE_OBELISK_I.getKeys());
		p.discoverRecipes(Recipes.EXPERIENCE_OBELISK_II.getKeys());
		p.discoverRecipes(Recipes.EXPERIENCE_OBELISK_III.getKeys());
		p.discoverRecipes(Recipes.EXPERIENCE_OBELISK_IV.getKeys());
		p.discoverRecipes(Recipes.CRYSTAL_ARMOR_BOOTS.getKeys());
		p.discoverRecipes(Recipes.CRYSTAL_ARMOR_CHESTPLATE.getKeys());
		p.discoverRecipes(Recipes.CRYSTAL_ARMOR_HELMET.getKeys());
		p.discoverRecipes(Recipes.CRYSTAL_ARMOR_LEGGINGS.getKeys());
		p.discoverRecipes(Recipes.GRAND_EXPERIENCE_BOTTLE.getKeys());
		p.discoverRecipes(Recipes.EXPERIENCE_BOTTLE.getKeys());
		p.discoverRecipes(Recipes.GRANITE_PICKAXE.getKeys());
		p.discoverRecipes(Recipes.ANDESITE_PICKAXE.getKeys());
		p.discoverRecipes(Recipes.DIORITE_PICKAXE.getKeys());
		p.discoverRecipes(Recipes.WARPED_PEARL.getKeys());
		p.discoverRecipes(Recipes.WARPED_FRUIT.getKeys());
		p.discoverRecipes(Recipes.ENCHANTED_GOLDEN_APPLE_JUICE.getKeys());
		p.discoverRecipes(Recipes.GOLDEN_APPLE_JUICE.getKeys());
		p.discoverRecipes(Recipes.GOLDERITE_INGOT.getKeys());
		
		if (up.isVanished()) {
			for(Player all : Bukkit.getOnlinePlayers()) {
				if(!all.isOp()) {
					if(all.canSee(p)) {
						all.hidePlayer(p);
					}
				}
			}
			p.setSilent(true);
			p.setCollidable(false);
			p.setCanPickupItems(false);
			p.setAllowFlight(true);
		}else {
			Bukkit.broadcastMessage("§e"+p.getName()+" [§a+§e]");
		}
		up.giveAchievement(Achievements.FIRSTJOIN);
		e.setJoinMessage(null);		
	}
	@EventHandler
	public void onLogin(PlayerLoginEvent e) {	
		Player p = e.getPlayer();
		if(!LiteSQL.isConnected()) {
			e.disallow(Result.KICK_OTHER, messageFormatter.format(false, "error.kickmessage","DATABASE_DECODE_ERROR"));
			return;
		}
		OfflinePlayer op = Bukkit.getOfflinePlayer(p.getUniqueId());
		OfflinePlayerProfile opp = playermanager.getOfflineProfile(op);
		if(opp.isBanned()) {
			e.disallow(Result.KICK_BANNED, "§cYou were banned from this server\n"
					+ "§cReason:§4\n"
					+ "\n "
					+ "\n "
					+ "§cTime remaining:\n"
					+ Utils.getTimeString(opp.getBantime(),System.currentTimeMillis())+"\n"
					+ "\n "
					+ "§cID:\n"
					+ "§e#"+op.getUniqueId().toString().replace("-", ""));
		}
		if(Utils.doesPlayerExists(p.getUniqueId()+"")){
			try {
				PreparedStatement ps = LiteSQL.getConnection().prepareStatement("UPDATE `playerdata` SET `ipadresse` = ? WHERE `uuid` = ?");
				ps.setString(1, e.getAddress().getHostAddress());
				ps.setString(2, p.getUniqueId()+"");
				ps.executeUpdate();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}else {
				try {
				PreparedStatement ps = LiteSQL.getConnection().prepareStatement("INSERT INTO playerdata (uuid,ipadresse,bantime,banreason,whobannedhim) VALUES (?,?,?,?,?)");
				ps.setString(1, p.getUniqueId()+"");
				ps.setString(2, e.getAddress().getHostAddress());
				ps.setLong(3, 0l);
				ps.setString(4, null);
				ps.setString(5, null);
				ps.executeUpdate();
				} catch (SQLException ex) {
					ex.printStackTrace();
			}
		}
	}
}

