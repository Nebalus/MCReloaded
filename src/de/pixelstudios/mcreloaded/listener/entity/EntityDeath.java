package de.pixelstudios.mcreloaded.listener.entity;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
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
    }
}
