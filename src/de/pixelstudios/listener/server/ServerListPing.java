package de.pixelstudios.listener.server;

import java.awt.image.BufferedImage;
import java.net.InetAddress;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.util.CachedServerIcon;

import de.pixelstudios.datamanagement.LiteSQL;

public class ServerListPing implements Listener{

	private HashMap<InetAddress, ArrayList<OfflinePlayer>> ipCache = new HashMap<InetAddress, ArrayList<OfflinePlayer>>();
	
	@EventHandler
	public void onPing(ServerListPingEvent e) {
		int maxPlayers = e.getNumPlayers();
		InetAddress address = e.getAddress();
		OfflinePlayer masterOp = null;
		Long lastTimeOnline = (long) 0;
		
		if(maxPlayers+1 < Bukkit.getMaxPlayers()) {
			maxPlayers += 1;
		}
		e.setMaxPlayers(maxPlayers);
		if(!ipCache.containsKey(address)) {
			loadIP(address);
		}
		
		if(ipCache.containsKey(address)) {
			ArrayList<OfflinePlayer> op = ipCache.get(address);
			int cachePlayers = 0;
			
			for(OfflinePlayer offlineP : op) {
				if(lastTimeOnline < offlineP.getLastPlayed()) {
					lastTimeOnline = offlineP.getLastPlayed();
					masterOp = offlineP;
				}
				cachePlayers++;
			}
			if(cachePlayers > 0) {
				try {
		        	BufferedImage image = null;
		            URL url = new URL("https://crafatar.com/avatars/"+masterOp.getUniqueId()+"?size=64&default=MHF_Steve&overlay");
		            image = ImageIO.read(url);
		            CachedServerIcon icon = Bukkit.loadServerIcon(image);
					e.setServerIcon(icon);
		        } catch (Exception e1) {
		        }
				
				e.setMotd("§aWelcome "+ masterOp.getName());
				
			}		
		}
	}
	private void loadIP(InetAddress address) {
		ArrayList<OfflinePlayer> op = new ArrayList<OfflinePlayer>();
		try {
			try {
			ResultSet rs = LiteSQL.onQuery("SELECT uuid FROM playerdata WHERE ipadresse = '" + address.getHostAddress()+"'");	
			while (rs.next()) {
				UUID uuid = UUID.fromString(rs.getString("uuid"));
				OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(uuid);
				op.add(offlinePlayer);
			}
			rs.close();	
			}catch(NullPointerException ne) {}
			}catch (SQLException ex) {
				ex.printStackTrace();
		}
		ipCache.put(address, op);
	}
}
