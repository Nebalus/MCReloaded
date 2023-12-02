package de.nebalus.mc.mcreloaded.announcement;

import org.bukkit.Bukkit;
import org.bukkit.Instrument;
import org.bukkit.Note;
import org.bukkit.Note.Tone;
import org.bukkit.entity.Player;

public class Announcement {

	// protected String notifySound;
	protected String prefix;
	protected String messsage;
	protected String suffix;

	protected boolean playNotifySound;

	protected boolean showToConsole;
	protected boolean showToOperator;
	protected boolean showToEveryone;

	public Announcement() {
		// Init der default werte
		// this.setNotificationSound(Sound.BLOCK_NOTE_BLOCK_BIT);
		setPrefix("§7[§aMCR§eAlert§7]: §r");
		setMessage("UNDEFINED");
		setSuffix("");

		playNotifySound(true);

		showToConsole(true);
		showToOperator(true);
		showToEveryone(true);
	}

	/*
	 * public Announcement setNotificationSound(String namespacepath) {
	 * this.notifySound = namespacepath;
	 *
	 * return this; }
	 *
	 * public Announcement setNotificationSound(Sound newNotifySound) {
	 * this.notifySound = newNotifySound.getKey().getKey();
	 *
	 * return this; }
	 **/
	public Announcement setPrefix(String newPrefix) {
		prefix = newPrefix;

		return this;
	}

	public Announcement setMessage(String newMessage) {
		messsage = newMessage;

		return this;
	}

	public Announcement setSuffix(String newSuffix) {
		suffix = newSuffix;

		return this;
	}

	public Announcement playNotifySound(boolean newPlayNotifySound) {
		playNotifySound = newPlayNotifySound;

		return this;
	}

	public Announcement showToConsole(boolean newShowToConsole) {
		showToConsole = newShowToConsole;

		return this;
	}

	public Announcement showToOperator(boolean newShowToOperator) {
		showToOperator = newShowToOperator;

		return this;
	}

	public Announcement showToEveryone(boolean newShowToEveryone) {
		showToEveryone = newShowToEveryone;

		return this;
	}

	public void broadcast() {
		final String finalMessage = prefix + messsage + suffix;

		if (showToConsole) {
			Bukkit.getConsoleSender().sendMessage(finalMessage);
		}

		if (showToOperator || showToEveryone) {
			for (Player p : Bukkit.getOnlinePlayers()) {
				if ((showToOperator && p.isOp()) || showToEveryone) {
					if (playNotifySound) {
						p.playNote(p.getEyeLocation(), Instrument.BELL, Note.natural(1, Tone.C));
					}

					p.sendMessage(finalMessage);
				}
			}
		}
	}
}
