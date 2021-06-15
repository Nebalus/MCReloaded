package de.pixelstudios.mcreloaded.listener.player;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.inventory.ItemStack;

import de.pixelstudios.mcreloaded.MCReloaded;
import de.pixelstudios.mcreloaded.datamanagement.LiteSQL;
import de.pixelstudios.mcreloaded.manager.PlayerManager;
import de.pixelstudios.mcreloaded.manager.UserProfile;
import de.pixelstudios.mcreloaded.manager.ItemManager.Recipes;
import de.pixelstudios.mcreloaded.messaging.MessageFormatter;
import de.pixelstudios.mcreloaded.utils.Achievements;
import de.pixelstudios.mcreloaded.utils.Utils;

public class PlayerJoin implements Listener{

	private PlayerManager playermanager;
	private MessageFormatter messageFormatter;
	
	public PlayerJoin(MCReloaded plugin) {
		this.playermanager = plugin.getPlayerManager();
		this.messageFormatter = plugin.getMessageFormatter();
	}

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
		p.discoverRecipes(Recipes.GRAND_EXPERIENCE_BOTTLE.getKeys());
		p.discoverRecipes(Recipes.EXPERIENCE_BOTTLE.getKeys());
		p.discoverRecipes(Recipes.GRANITE_PICKAXE.getKeys());
		p.discoverRecipes(Recipes.ANDESITE_PICKAXE.getKeys());
		p.discoverRecipes(Recipes.DIORITE_PICKAXE.getKeys());
		p.discoverRecipes(Recipes.ENCHANTED_GOLDEN_APPLE_JUICE.getKeys());
		p.discoverRecipes(Recipes.GOLDEN_APPLE_JUICE.getKeys());
		p.discoverRecipes(Recipes.GOLDERITE_INGOT.getKeys());
		p.discoverRecipes(Recipes.WARP_FUEL.getKeys());
		p.discoverRecipes(Recipes.WARP_CRYSTAL.getKeys());
		
		Bukkit.broadcastMessage("§e"+p.getName()+" [§a+§e]");

		if(up.giveAchievement(Achievements.FIRSTJOIN)) {
			p.getInventory().addItem(new ItemStack(Material.KNOWLEDGE_BOOK));
		}
		e.setJoinMessage(null);		
	}
	@EventHandler
	public void onLogin(PlayerLoginEvent e) {	
		Player p = e.getPlayer();
		if(!LiteSQL.isConnected()) {
			e.disallow(Result.KICK_OTHER, messageFormatter.format(false, "error.kickmessage","DATABASE_DECODE_ERROR"));
			return;
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

