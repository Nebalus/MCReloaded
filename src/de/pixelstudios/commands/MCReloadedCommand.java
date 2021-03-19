package de.pixelstudios.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.StringUtil;

import de.pixelstudios.MCReloaded;
import de.pixelstudios.items.manager.HeadList;
import de.pixelstudios.manager.ItemManager;
import de.pixelstudios.manager.PlayerManager;
import de.pixelstudios.messaging.MessageFormatter;

public class MCReloadedCommand implements CommandExecutor, TabCompleter{
	private MessageFormatter messageFormatter;
	
	public MCReloadedCommand(MCReloaded plugin) {
		this.messageFormatter = plugin.getMessageFormatter();
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if(args.length == 1) {
			if(args[0].equalsIgnoreCase("headcache")) {
				Inventory inv = Bukkit.createInventory(null,  9*6, "§2Headcache");
				inv.addItem(HeadList.ENDERCHEST);
				inv.addItem(HeadList.EXPERIENCE_BOTTLE);
				inv.addItem(HeadList.EXPERIENCE_ORB);
				inv.addItem(HeadList.OFFLINE);
				inv.addItem(HeadList.ONLINE);
				inv.addItem(HeadList.PLUS_ACTIVE);
				inv.addItem(HeadList.PLUS);
				inv.addItem(HeadList.TELEPORTATION_CORE);
				inv.addItem(HeadList.WORKBENCH);
				inv.addItem(HeadList.SOUL_CAGE);
				inv.addItem(HeadList.OPAL);
				inv.addItem(HeadList.REVIVE_STONE);
				inv.addItem(HeadList.STONE_TANK);
				p.openInventory(inv);
				
			}
		}else if(args.length == 2) {
			if(args[0].equalsIgnoreCase("reload")) {

			}else if(args[0].equalsIgnoreCase("getitem")) {
				if(p.isOp()) {
					boolean isCustomItem = false;
					String itemString = args[1].toUpperCase();
					for (String item : ItemManager.valuesString()) {
		               if(item.equals(itemString)) {
		            	   isCustomItem = true;
		               }
		            }
					if(isCustomItem) {
						if (p.getInventory().addItem(ItemManager.valueOf(args[1].toUpperCase())).size() != 0) {
		                    p.getWorld().dropItem(p.getEyeLocation(), ItemManager.valueOf(args[1].toUpperCase()));
		                }
					}else {
						p.sendMessage(messageFormatter.format(false, "commands.getitem.allready-requested",args[1]));
					}
				}			
			}
		}
		return false;
	}
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String lable, String[] args) {
		List<String> tabcomplete = new ArrayList<String>();
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(args.length == 1) {
				if(p.isOp()) {
					tabcomplete.add("reload");	 
					tabcomplete.add("getitem");	 
					tabcomplete.add("headcache");	 
				} 
			}
			//*****************************************************		
			if(args.length == 2) {
				if(p.isOp()) {
					if(args[0].equalsIgnoreCase("getitem")) {
						for (String item : ItemManager.valuesString()) {
			                tabcomplete.add(item);
			            }
					}
				}
			}
			//*****************************************************		
		}
		return tabcomplete;
	}
}
