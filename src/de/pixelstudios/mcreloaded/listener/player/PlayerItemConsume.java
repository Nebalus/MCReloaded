package de.pixelstudios.mcreloaded.listener.player;

import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import de.pixelstudios.mcreloaded.MCReloaded;
import de.pixelstudios.mcreloaded.manager.ItemManager;
import de.pixelstudios.mcreloaded.manager.PlayerManager;
import de.pixelstudios.mcreloaded.manager.UserProfile;
import de.pixelstudios.mcreloaded.manager.ItemManager.Tags;
import de.pixelstudios.mcreloaded.utils.Achievements;

public class PlayerItemConsume implements Listener{
	PlayerManager playerManager;
	
	public PlayerItemConsume(MCReloaded plugin) {
		this.playerManager = plugin.getPlayerManager();
	}
	@EventHandler(
			  priority = EventPriority.HIGHEST
		   )
	public void onConsume(PlayerItemConsumeEvent e){
		if(e.isCancelled()) return;
		Player player = e.getPlayer();
		switch(e.getItem().getType()){
		
			case BEEF:
			case PORKCHOP:
			case MUTTON:
			case CHICKEN:
			case ROTTEN_FLESH:
			case RABBIT:
				RawMeatHunger(player);
				break;
			case BEETROOT:
				BeetrootStrength(player);
				break;
				
			case COOKIE:
				CookieHealthBoost(player);
				break;
				
			case POISONOUS_POTATO:
				PoisonousPotato(player);
				break;
				
			case HONEY_BOTTLE:
				HoneyBoost(player);
			case POTION:
			case MILK_BUCKET:
			case MELON_SLICE:
			case MUSHROOM_STEW:
			case SUSPICIOUS_STEW:
				DrinkManager(player,e.getItem());
				break;
			default:
				break;
		}
	}
	private void DrinkManager(Player p, ItemStack item) {
		UserProfile up = playerManager.getProfile(p);
		double thirst = up.getThirst();
		double energy = up.getEnergy();
		double health = p.getHealth();
		
		switch(item.getType()){
		case POTION:
			if(Tags.DRINKABLE.isTagged(item)) {
				up.giveAchievement(Achievements.STAYHYDRATED);
				if(item.isSimilar(ItemManager.DIRTY_WATER)) {
					thirst += 13;
				}
				if(item.isSimilar(ItemManager.CLEAN_WATER)) {
					thirst += 18;
				}
				if(item.isSimilar(ItemManager.PURIFIED_WATER)) {
					thirst += 23;
				}
				if(item.isSimilar(ItemManager.COFFEE)) {
					energy += 10;
					thirst += 23;
				}
				if(item.isSimilar(ItemManager.COLD_MILK)) {
					thirst += 15;
				}
				if(item.isSimilar(ItemManager.HOT_MILK)) {
					health -= 1;
					thirst += 10;
				}
				if(item.isSimilar(ItemManager.WATER_BOWL)) {
					thirst += 10;
				}
				if(item.isSimilar(ItemManager.GOLDEN_APPLE_JUICE)) {
					thirst += 20;
					p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 1));
					p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 2400, 0));
					up.giveAchievement(Achievements.GILDEDJUICE);
				}
				if(item.isSimilar(ItemManager.ENCHANTED_GOLDEN_APPLE_JUICE)) {
					thirst += 40;
					p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 6000, 0));
					p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 6000, 0));
					p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 2400, 3));
					up.giveAchievement(Achievements.GILDEDJUICE);
				}
			}
			break;
		case MELON_SLICE:
			thirst += 6;
			break;
		case MILK_BUCKET:
			thirst += 30;
			break;
		case MUSHROOM_STEW:
		case SUSPICIOUS_STEW:
			thirst += 12;
			break;
		case HONEY_BOTTLE:
			thirst += 9;
			break;
		default:
			break;
		}
		
		if(health < 0) {
			health = 0;
		}
		p.setHealth(health);
		up.setEnergy(energy);
		up.setThirst(thirst);
	}
	private HashMap<UUID, Long> messagecooldown = new HashMap<>();
	private void RawMeatHunger(Player p) { 
		final String message = MCReloaded.infoprefix+"§aIn order not to get hungry, you need to fry the meat beforehand.";
		Random rand = new Random();
		int hungerChance = rand.nextInt(10) + 1;
		if(hungerChance >= 1 && hungerChance <= 8){
			int dur = 200;
			for (PotionEffect effect : p.getActivePotionEffects()){
				if(effect.getType().equals(PotionEffectType.HUNGER)){
					dur += effect.getDuration();
			        p.removePotionEffect(effect.getType());
				}
			}
			if(messagecooldown.containsKey(p.getUniqueId())) {
				if(messagecooldown.get(p.getUniqueId()) <= System.currentTimeMillis()) {
					messagecooldown.put(p.getUniqueId(), System.currentTimeMillis()+60000);
					p.sendMessage(message);
				}
			}else {
				messagecooldown.put(p.getUniqueId(), System.currentTimeMillis()+60000);
				p.sendMessage(message);
			}
			p.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, dur, 2, false));
		}
	}
	private void BeetrootStrength(Player p) {
		int amp = 0;
		int dur = 200;
		for (PotionEffect effect : p.getActivePotionEffects()){
			if(effect.getType().equals(PotionEffectType.INCREASE_DAMAGE)){
				dur += effect.getDuration();
				if(dur > 600) dur = 600;
				p.removePotionEffect(effect.getType());
			}
		}
		p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, dur, amp));
	}
	private void CookieHealthBoost(Player p) {
		int amp = -1;
		int dur = 600;
		double health;
		health = p.getHealth();
		for (PotionEffect effect : p.getActivePotionEffects()){
			if(effect.getType().equals(PotionEffectType.HEALTH_BOOST)){
				dur += effect.getDuration();
				if(effect.getDuration() >= 300)
					amp++;
				if(effect.getDuration() >= 1200)
					amp++;
				if(effect.getDuration() >= 3600)
					amp++;
		        p.removePotionEffect(effect.getType());
			}
		}
		p.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, dur, amp));
		p.setHealth(health);
		p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 60, 1));
		p.setSaturation(p.getSaturation() + 4.6f);
	}
	private void HoneyBoost(Player p) {
		int amp = -1;
		int dur = 200;
		for (PotionEffect effect : p.getActivePotionEffects()){
			if(effect.getType().equals(PotionEffectType.SPEED)){
				dur += effect.getDuration();
				if(effect.getDuration() >= 200)
					amp++;
				if(effect.getDuration() >= 400)
					amp++;
				if(effect.getDuration() >= 600)
					amp++;
		        p.removePotionEffect(effect.getType());
			}
		}
		p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, dur, amp));
	}
	@SuppressWarnings("deprecation")
	private void PoisonousPotato(Player p) {
		for (PotionEffect effect : p.getActivePotionEffects())
	        p.removePotionEffect(effect.getType());

		Random rand = new Random();
		if(rand.nextInt(10) + 1 <= 4){
			p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 200, 0), true);
			p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 400, 0), true);
			p.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 350, 0, false));
			p.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 300, 0, false));
		}
	}
}
