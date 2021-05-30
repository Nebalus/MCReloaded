package de.pixelstudios.mcreloaded.commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import de.pixelstudios.mcreloaded.MCReloaded;
import de.pixelstudios.mcreloaded.messaging.MessageFormatter;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class MenuCommand implements CommandExecutor, TabCompleter{
	private MessageFormatter messageFormatter;
	
	public MenuCommand(MCReloaded plugin) {
		this.messageFormatter = plugin.getMessageFormatter();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {
		TextComponent tc = new TextComponent("§1Test1 §2Test2 §3Test3 §4Test4 §5Test5 §6Test6 §7Test7 §8Test8 ");
		tc.setClickEvent(new ClickEvent(net.md_5.bungee.api.chat.ClickEvent.Action.RUN_COMMAND, "/ping" ));			
		TextComponent tc1 = new TextComponent("Test");
		tc1.setClickEvent(new ClickEvent(net.md_5.bungee.api.chat.ClickEvent.Action.SUGGEST_COMMAND, "/Test" ));			
		tc.addExtra(tc1);
		sender.spigot().sendMessage(tc);
		return false;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String lable, String[] args) {
		
		return null;
	}

}
