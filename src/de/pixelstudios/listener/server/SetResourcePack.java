package de.pixelstudios.listener.server;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent.Status;

import de.pixelstudios.MCReloaded;
import de.pixelstudios.datamanagement.Config;
import de.pixelstudios.manager.PlayerManager;
import de.pixelstudios.messaging.MessageFormatter;

public class SetResourcePack implements Listener {

	private MessageFormatter messageFormatter;
	private Config config;
	private PlayerManager playerManager;

	public SetResourcePack(MCReloaded plugin) {
		this.config = plugin.getMCReloadedConfig();
		this.playerManager = plugin.getPlayerManager();
		this.messageFormatter = plugin.getMessageFormatter();
	}



	@EventHandler
	private void onPlayerJoin(PlayerJoinEvent event) {
		if (config.RESOURCE_PACK_ENABLED)
			playerManager.applyResourcePack(event.getPlayer(), 20);
	}

	/* Ich bin mir nicht sicher, warum dies hinzugefügt wurde, und lasse es vorerst für den Fall, dass es tatsächlich benötigt wird
	@EventHandler
	public void onPlayerChangedWorld(PlayerChangedWorldEvent event) {
		if (resourcePack)
			applyResourcePack(event.getPlayer());
	}
	 */

	@EventHandler
	private void resourcePackEvent(PlayerResourcePackStatusEvent e) {
		Player player = e.getPlayer();
		if (config.RESOURCE_PACK_ENABLED && config.RESOURCE_PACK_NOTIFY)
			if (e.getStatus() == Status.DECLINED) {
				player.sendMessage(" ");
				player.sendMessage(messageFormatter.format(true, "resourcepack.resource_pack_declined"));
				player.sendMessage(messageFormatter.format(false, "resourcepack.resource_pack_apply"));
				player.sendMessage(messageFormatter.format(false, "resourcepack.resource_pack_required"));
				playerManager.getProfile(player).setResourcepackAccepted(false);
			} else if (e.getStatus() == Status.ACCEPTED) {
				player.sendMessage(messageFormatter.format(true, "resourcepack.resource_pack_accepted"));
				player.sendMessage(messageFormatter.format(false, "resourcepack.resource_pack_optifine"));
			}
	}

}
