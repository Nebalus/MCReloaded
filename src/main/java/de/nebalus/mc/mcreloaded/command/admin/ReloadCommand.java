package de.nebalus.mc.mcreloaded.command.admin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.nebalus.mc.mcreloaded.MCRCore;
import de.nebalus.mc.mcreloaded.announcement.Announcement;
import de.nebalus.mc.mcreloaded.command.CommandAdapter;

public class ReloadCommand extends CommandAdapter {

	private boolean isReloading = false;

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (isReloading) {
			sender.sendMessage("§cThe server is allready reloading");
			return false;
		}

		isReloading = true;
		for (Player allp : Bukkit.getOnlinePlayers()) {
			allp.sendTitle("§cServer Reload", "", 1, 120, 20);
		}

		final Runnable task = new Runnable() {
			int times = 60;

			@Override
			public void run() {
				switch (times) {
				case 60:
				case 30:
				case 15:
				case 10:
				case 5:
				case 3:
				case 2:
				case 1:
					new Announcement().setMessage(
							"§cThe server will reload in §6" + times + " §csecond" + (times == 1 ? "" : "s") + "!")
							.broadcast();
					break;
				case 0:
					new Announcement().setMessage("§cThe server is now §areloading§c this task may contains lags!")
							.broadcast();
					Bukkit.reload();
					break;
				}

				if (times-- > 0) {
					Bukkit.getScheduler().scheduleSyncDelayedTask(MCRCore.getInstance(), this, 20);
				}
			}
		};

		Bukkit.getScheduler().scheduleSyncDelayedTask(MCRCore.getInstance(), task, -1);

		return false;
	}

}
