package de.pixelstudios.mcreloaded.commands;

import de.pixelstudios.mcreloaded.MCReloaded;
import de.pixelstudios.mcreloaded.messaging.MessageFormatter;

public class MsgCommand {
	private MessageFormatter messageFormatter;
	
	public MsgCommand(MCReloaded plugin) {
		this.messageFormatter = plugin.getMessageFormatter();
	}
}
