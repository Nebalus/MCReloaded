package de.pixelstudios.mcreloaded.listener.player;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

import de.pixelstudios.mcreloaded.guis.menu.MainMenuGUI;
import de.pixelstudios.mcreloaded.manager.ItemManager;
import io.pixelstudios.libary.InventoryLibary;

public class PlayerInteractEntity implements Listener{

	 @SuppressWarnings({"deprecation" })
	 @EventHandler(
			  ignoreCancelled = true,
			  priority = EventPriority.HIGHEST
		   )
	 private void onPlayerInteractEntity(PlayerInteractEntityEvent e){
		 Player p = e.getPlayer();
		 ItemStack item = p.getItemInHand();
		 switch(e.getRightClicked().getType()) {
		 	case PLAYER:
				Player op = (Player) e.getRightClicked();
				if(p.isSneaking()) {
					MainMenuGUI.openOtherProfileGui(op.getName(),p);
				}
				break;
		 	case COW:
		 	case GOAT:	 
		 		if(item == null) return;
				if(item.getType().equals(Material.GLASS_BOTTLE)) {
					p.playSound(p.getLocation(), Sound.ENTITY_COW_MILK, 1.0f, 1.0f);
					if (p.getGameMode() == GameMode.SURVIVAL || p.getGameMode() == GameMode.ADVENTURE) item.setAmount(item.getAmount() - 1);
					InventoryLibary.addItemToInventory(p, ItemManager.COLD_MILK);
				}
		 		break;
		 		
		 	default:
		 		break;
		 }
	}
}
