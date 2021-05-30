package de.pixelstudios.mcreloaded.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import de.pixelstudios.mcreloaded.MCReloaded;
import de.pixelstudios.mcreloaded.friendsystem.FriendAPI;
import de.pixelstudios.mcreloaded.messaging.MessageFormatter;
import de.pixelstudios.mcreloaded.utils.Utils;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class FriendCommand implements CommandExecutor, TabCompleter{
	
	private String FriendSystemPref = "§aFriends §8»§r ";
	
	private MessageFormatter messageFormatter;
	
	public FriendCommand(MCReloaded plugin) {
		this.messageFormatter = plugin.getMessageFormatter();
	}
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Bukkit.getScheduler().runTaskAsynchronously(MCReloaded.getPlugin(),new Runnable() {
			@Override
			public void run() {
				if(sender instanceof Player) {			
					Player p = (Player) sender;			
					if(p.hasPermission("mcreloaded.command.friend")) {
						if(args.length == 0) {
							sendHelp(sender);
						}else if(args.length == 1) {
							if(args[0].equalsIgnoreCase("clear")) {
								
							}else if(args[0].equalsIgnoreCase("toggle")) {
								
							}else if(args[0].equalsIgnoreCase("togglenotify")) {
							
							}else if(args[0].equalsIgnoreCase("togglemessage")) {
								
							}else if(args[0].equalsIgnoreCase("list")) {
								
							}else {
								sendHelp(sender);
							}
						}else if(args.length == 2) {
							OfflinePlayer op = Bukkit.getOfflinePlayer(args[1]);
							if(op.getPlayer() != p) {
								if(Utils.doesPlayerExists(op.getUniqueId()+"")) {
									if(args[0].equalsIgnoreCase("add")) {
										if(!FriendAPI.isFriend(op.getUniqueId()+"", p)) {
											if(!FriendAPI.isRequested(op.getUniqueId()+"", p)) {
												
											}else {
												p.sendMessage(messageFormatter.format(false, "commands.friend.allready-requested",args[1]));
											}
										}else {
											p.sendMessage(messageFormatter.format(false, "commands.friend.allready-friends",args[1]));
										}
									}else if(args[0].equalsIgnoreCase("accept")) {
										if(FriendAPI.isRequested(op.getUniqueId()+"", p)) {
											
										}else {
											p.sendMessage(messageFormatter.format(false, "commands.friend.no-request",args[1]));
										}
									}else if(args[0].equalsIgnoreCase("deny")) {
										if(FriendAPI.isRequested(op.getUniqueId()+"", p)) {
											
										}else {
											p.sendMessage(messageFormatter.format(false, "commands.friend.no-request",args[1]));
										}
									}else if(args[0].equalsIgnoreCase("remove")) {
										if(FriendAPI.isFriend(op.getUniqueId()+"", p)) {
											
										}else {
											p.sendMessage(messageFormatter.format(false, "commands.friend.arent-friends",args[1]));
										}
									}else {
										sendHelp(sender);
									}
								}else {
									p.sendMessage(messageFormatter.format(false, "error.player-was-never-online",args[1]));
								}
							}else {
								p.sendMessage(messageFormatter.format(false, "commands.friend.not-yourself"));
							}
						}else {
							sendHelp(sender);
						}
					}
				}else 	
					Bukkit.getConsoleSender().sendMessage(messageFormatter.format(false, "console.only-player"));
			}
		});
		return false;
	}
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String lable, String[] args) {
		List<String> tabcomplete = new ArrayList<String>();
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(p.hasPermission("mcreloaded.command.friend")) {
				if(args.length == 1) {
					tabcomplete.add("add");	
					tabcomplete.add("accept");
					tabcomplete.add("deny");
					tabcomplete.add("remove");
					tabcomplete.add("clear");	
					tabcomplete.add("toggle");
					tabcomplete.add("togglenotify");
					tabcomplete.add("togglemessage");
					tabcomplete.add("list");
				}else if(args.length == 2) {
					for(Player all : Bukkit.getOnlinePlayers()) {
						if(all != p) {
							tabcomplete.add(all.getName());	
						}
					}
				}
			}
			/*
			if(args.length == 2) {
				if(p.hasPermission("mcreloaded.command.gm.other")) {
					for(Player players : Bukkit.getOnlinePlayers() ) {
						tabcomplete.add(players.getName());
					}
				}
			}
			*/
			
		}
		return tabcomplete;
	}
	@SuppressWarnings("deprecation")
	public boolean sendHelp(CommandSender sender) {
		Player player = (Player) sender;
		player.sendMessage(FriendSystemPref + "§7Command list for the friends-system");
		
		TextComponent tc1_0 = new TextComponent("§a/friend add <Player>");
		tc1_0.setClickEvent(new ClickEvent(net.md_5.bungee.api.chat.ClickEvent.Action.SUGGEST_COMMAND, "/friend add <Player>" ));
		tc1_0.setHoverEvent(new HoverEvent(net.md_5.bungee.api.chat.HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click to show in commandline!").create()));	
		TextComponent tc1_1 = new TextComponent(" §8x §7Send an inquiry");
		tc1_1.setHoverEvent(new HoverEvent(net.md_5.bungee.api.chat.HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("").create()));	
		tc1_0.addExtra(tc1_1);
		sender.spigot().sendMessage(tc1_0);
		
		TextComponent tc2_0 = new TextComponent("§a/friend accept <Player>");
		tc2_0.setClickEvent(new ClickEvent(net.md_5.bungee.api.chat.ClickEvent.Action.SUGGEST_COMMAND, "/friend accept <Player>" ));
		tc2_0.setHoverEvent(new HoverEvent(net.md_5.bungee.api.chat.HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click to show in commandline!").create()));	
		TextComponent tc2_1 = new TextComponent(" §8x §7Accept an inquiry");
		tc2_1.setHoverEvent(new HoverEvent(net.md_5.bungee.api.chat.HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("").create()));
		tc2_0.addExtra(tc2_1);
		sender.spigot().sendMessage(tc2_0);
		
		TextComponent tc3_0 = new TextComponent("§a/freind deny <Player>");
		tc3_0.setClickEvent(new ClickEvent(net.md_5.bungee.api.chat.ClickEvent.Action.SUGGEST_COMMAND, "/friend deny <Player>" ));
		tc3_0.setHoverEvent(new HoverEvent(net.md_5.bungee.api.chat.HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click to show in commandline!").create()));	
		TextComponent tc3_1 = new TextComponent(" §8x §7Decline an inquiry");
		tc3_1.setHoverEvent(new HoverEvent(net.md_5.bungee.api.chat.HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("").create()));
		tc3_0.addExtra(tc3_1);
		sender.spigot().sendMessage(tc3_0);
		
		TextComponent tc4_0 = new TextComponent("§a/friend remove <Player>");
		tc4_0.setClickEvent(new ClickEvent(net.md_5.bungee.api.chat.ClickEvent.Action.SUGGEST_COMMAND, "/friend remove <Player>" ));
		tc4_0.setHoverEvent(new HoverEvent(net.md_5.bungee.api.chat.HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click to show in commandline!").create()));	
		TextComponent tc4_1 = new TextComponent(" §8x §7Break up a friendship");
		tc4_1.setHoverEvent(new HoverEvent(net.md_5.bungee.api.chat.HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("").create()));
		tc4_0.addExtra(tc4_1);
		sender.spigot().sendMessage(tc4_0);
		
		TextComponent tc5_0 = new TextComponent("§a/friend clear");
		tc5_0.setClickEvent(new ClickEvent(net.md_5.bungee.api.chat.ClickEvent.Action.SUGGEST_COMMAND, "/friend clear" ));
		tc5_0.setHoverEvent(new HoverEvent(net.md_5.bungee.api.chat.HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click to show in commandline!").create()));	
		TextComponent tc5_1 = new TextComponent(" §8x §7Dissolve all friendship");
		tc5_1.setHoverEvent(new HoverEvent(net.md_5.bungee.api.chat.HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("").create()));
		tc5_0.addExtra(tc5_1);
		sender.spigot().sendMessage(tc5_0);
		
		TextComponent tc6_0 = new TextComponent("§a/friend toggle");
		tc6_0.setClickEvent(new ClickEvent(net.md_5.bungee.api.chat.ClickEvent.Action.SUGGEST_COMMAND, "/friend toggle" ));
		tc6_0.setHoverEvent(new HoverEvent(net.md_5.bungee.api.chat.HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click to show in commandline!").create()));	
		TextComponent tc6_1 = new TextComponent(" §8x §7Requests Activate/Deactivate");
		tc6_1.setHoverEvent(new HoverEvent(net.md_5.bungee.api.chat.HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("").create()));
		tc6_0.addExtra(tc6_1);
		sender.spigot().sendMessage(tc6_0);
		
		TextComponent tc7_0 = new TextComponent("§a/friend togglenotify");
		tc7_0.setClickEvent(new ClickEvent(net.md_5.bungee.api.chat.ClickEvent.Action.SUGGEST_COMMAND, "/friend togglenotify" ));
		tc7_0.setHoverEvent(new HoverEvent(net.md_5.bungee.api.chat.HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click to show in commandline!").create()));	
		TextComponent tc7_1 = new TextComponent(" §8x §7Online/Offline Messages On/Off");
		tc7_1.setHoverEvent(new HoverEvent(net.md_5.bungee.api.chat.HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("").create()));
		tc7_0.addExtra(tc7_1);
		sender.spigot().sendMessage(tc7_0);
		
		TextComponent tc8_0 = new TextComponent("§a/friend togglemessage");
		tc8_0.setClickEvent(new ClickEvent(net.md_5.bungee.api.chat.ClickEvent.Action.SUGGEST_COMMAND, "/friend togglemessage" ));
		tc8_0.setHoverEvent(new HoverEvent(net.md_5.bungee.api.chat.HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click to show in commandline!").create()));	
		TextComponent tc8_1 = new TextComponent(" §8x §7Private messages On/Off");
		tc8_1.setHoverEvent(new HoverEvent(net.md_5.bungee.api.chat.HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("").create()));
		tc8_0.addExtra(tc8_1);
		sender.spigot().sendMessage(tc8_0);
		
		TextComponent tc9_0 = new TextComponent("§a/friend list");
		tc9_0.setClickEvent(new ClickEvent(net.md_5.bungee.api.chat.ClickEvent.Action.SUGGEST_COMMAND, "/friend list" ));
		tc9_0.setHoverEvent(new HoverEvent(net.md_5.bungee.api.chat.HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click to show in commandline!").create()));	
		TextComponent tc9_1 = new TextComponent(" §8x §7List your friends");
		tc9_1.setHoverEvent(new HoverEvent(net.md_5.bungee.api.chat.HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("").create()));
		tc9_0.addExtra(tc9_1);
		sender.spigot().sendMessage(tc9_0);
		return true;
	}
}
