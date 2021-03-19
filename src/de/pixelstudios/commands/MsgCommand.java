package de.pixelstudios.commands;

import de.pixelstudios.MCReloaded;
import de.pixelstudios.messaging.MessageFormatter;

public class MsgCommand {
	private MessageFormatter messageFormatter;
	
	public MsgCommand(MCReloaded plugin) {
		this.messageFormatter = plugin.getMessageFormatter();
	}
}
