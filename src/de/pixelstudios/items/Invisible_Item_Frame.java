package de.pixelstudios.items;

import java.util.Iterator;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.hanging.HangingBreakEvent;
import org.bukkit.event.hanging.HangingPlaceEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

import de.pixelstudios.MCReloaded;
import de.pixelstudios.manager.ItemManager;
import de.pixelstudios.utils.DroppedFrameLocation;

public class Invisible_Item_Frame implements Listener{

    public static Set<DroppedFrameLocation> droppedFrames;
    
    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    private void onHangingPlace(HangingPlaceEvent event)
    {
        if(event.getEntity().getType() != EntityType.ITEM_FRAME || event.getPlayer() == null)
        {
            return;
        }
       
        ItemStack frame;
        Player p = event.getPlayer();
        if(p.getInventory().getItemInMainHand().getType() == Material.ITEM_FRAME)
        {
            frame = p.getInventory().getItemInMainHand();
        }
        else if(p.getInventory().getItemInOffHand().getType() == Material.ITEM_FRAME)
        {
            frame = p.getInventory().getItemInOffHand();
        }
        else
        {
            return;
        }
       
        try {
	        if(frame.getItemMeta().getPersistentDataContainer().has(ItemManager.invisible_item_frame_Key, PersistentDataType.BYTE))
	        {
	            ItemFrame itemFrame = (ItemFrame) event.getEntity();
	            itemFrame.setVisible(true);
	            event.getEntity().getPersistentDataContainer().set(ItemManager.invisible_item_frame_Key, PersistentDataType.BYTE, (byte) 1);
	        }
        }catch(NullPointerException ex) {
			
		}
    }
    
    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    private void onHangingBreak(HangingBreakEvent event)
    {	
    	try {
	        if(event.getEntity().getType() != EntityType.ITEM_FRAME ||
	                !event.getEntity().getPersistentDataContainer().has(ItemManager.invisible_item_frame_Key, PersistentDataType.BYTE))
	        {
	            return;
	        }
	       
	        DroppedFrameLocation droppedFrameLocation = new DroppedFrameLocation(event.getEntity().getLocation());
	        droppedFrames.add(droppedFrameLocation);
	        droppedFrameLocation.setRemoval((new BukkitRunnable()
	        {
	            @Override
	            public void run()
	            {
	                droppedFrames.remove(droppedFrameLocation);
	            }
	        }).runTaskLater(MCReloaded.getPlugin(), 20L));
    	}catch(NullPointerException ex) {
 			
 		}
    }
    
    @EventHandler
    private void onItemSpawn(ItemSpawnEvent event)
    {
        Item item = event.getEntity();
        if(item.getItemStack().getType() != Material.ITEM_FRAME)
        {
            return;
        }
        
        Iterator<DroppedFrameLocation> iter = droppedFrames.iterator();
        while(iter.hasNext())
        {
            DroppedFrameLocation droppedFrameLocation = iter.next();
            if(droppedFrameLocation.isFrame(item))
            {
                event.getEntity().setItemStack(ItemManager.INVISIBLE_ITEM_FRAME);
                
                droppedFrameLocation.getRemoval().cancel();
                iter.remove();
                break;
            }
        }
    }
    
    @EventHandler(ignoreCancelled = true)
    private void onPlayerInteractEntity(PlayerInteractEntityEvent event)
    {
    	try {
	        if(event.getRightClicked().getType() == EntityType.ITEM_FRAME &&
	                event.getRightClicked().getPersistentDataContainer().has(ItemManager.invisible_item_frame_Key, PersistentDataType.BYTE))
	        {
	            ItemFrame frame = (ItemFrame) event.getRightClicked();
	            Bukkit.getScheduler().runTaskLater(MCReloaded.getPlugin(), () ->
	            {
	                if(frame.getItem().getType() != Material.AIR)
	                {
	                    frame.setVisible(false);
	                }
	            }, 1L);
	        }
    	}catch(NullPointerException ex) {
 			
 		}
    }
    
    @EventHandler(ignoreCancelled = true)
    private void onEntityDamageByEntity(EntityDamageByEntityEvent event)
    {
    	try {
        if(event.getEntityType() == EntityType.ITEM_FRAME &&
                event.getEntity().getPersistentDataContainer().has(ItemManager.invisible_item_frame_Key, PersistentDataType.BYTE))
        {
            ItemFrame frame = (ItemFrame) event.getEntity();
            Bukkit.getScheduler().runTaskLater(MCReloaded.getPlugin(), () ->
            {
                if(frame.getItem().getType() == Material.AIR)
                {
                    frame.setVisible(true);
                    }
                
            }, 1L);
        }
    	 }catch(NullPointerException ex) {
 			
 		}
    }
}