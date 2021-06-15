package de.pixelstudios.mcreloaded.datamanagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.entity.Player;

import de.pixelstudios.mcreloaded.guis.WarpCrystalGUI;
import de.pixelstudios.mcreloaded.items.WarpCrystal;

public class Cache {

	
	public static List<WarpCrystal> warp_crystal = new ArrayList<WarpCrystal>();
	
	public static HashMap<Player, WarpCrystalGUI> warp_crystal_gui_session = new HashMap<Player, WarpCrystalGUI>();
}
