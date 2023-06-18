package de.nebalus.mc.mcreloaded;

import javax.swing.JOptionPane;

public class MCRCoreLauncher 
{
	public static void main(String[] args) 
	{
		final String errorMessage = "This plugin is not a executable programm!\nPlease drag this filt into the plugins folder on a Bukkit/Spigot server!\n\nMCReloaded Plugin by Nebalus";
		System.out.println(errorMessage);
		JOptionPane.showMessageDialog(null, errorMessage);
	}
}
