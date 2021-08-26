package de.pixelstudios.mcreloaded.listener.entity;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import de.pixelstudios.mcreloaded.MCReloaded;
import de.pixelstudios.mcreloaded.utils.Achievements;
import fr.mrmicky.fastparticle.FastParticle;
import fr.mrmicky.fastparticle.ParticleType;


public class EntityDeath implements Listener{
   
	@EventHandler(
			  ignoreCancelled = true,
			  priority = EventPriority.HIGHEST
		   )
    private void onEntityDeath(EntityDeathEvent e) {
		LivingEntity entity = e.getEntity();
	       switch (entity.getType()) {
		        case ITEM_FRAME:
		        case ARMOR_STAND:
		        break;   
		      
		        default:
		        	/*
		        	Collection<ItemStack> drops = e.getDrops();
					if(!drops.isEmpty()) {
						entity.getWorld().dropItemNaturally(entity.getLocation(), drops.iterator().next());
						e.getDrops().clear();
					}
					*/
		        	FastParticle.spawnParticle(entity.getWorld(), ParticleType.SOUL, entity.getEyeLocation(), 1, 0f, 0f, 0f, 0f);
		        	
		        break;  
		        
	     }
	     if(entity.getKiller() instanceof Player) {
		     switch (entity.getType()) {
		        case BAT:
		        	MCReloaded.getPlugin().getPlayerManager().getProfile(entity.getKiller()).giveAchievement(Achievements.ANTIBATMAN);
		        	break;
		        case MUSHROOM_COW:
		        	MCReloaded.getPlugin().getPlayerManager().getProfile(entity.getKiller()).giveAchievement(Achievements.MOOTATED);
		        	break;
		        case VILLAGER:
		        case WANDERING_TRADER:
		        	MCReloaded.getPlugin().getPlayerManager().getProfile(entity.getKiller()).giveAchievement(Achievements.KILLVILLAGER);
		        	break;
		        
		        default:
		        	break;
		     }
	     }
    }
}
