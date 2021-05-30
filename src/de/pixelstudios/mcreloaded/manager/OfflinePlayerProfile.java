package de.pixelstudios.mcreloaded.manager;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.OfflinePlayer;

import de.pixelstudios.mcreloaded.datamanagement.LiteSQL;

public class OfflinePlayerProfile {
	
	private OfflinePlayer op;
	
	public OfflinePlayerProfile(OfflinePlayer op) {
		this.op = op;
	}

	public boolean isBanned() {
		ResultSet rs = LiteSQL.onQuery("SELECT bantime FROM playerdata WHERE uuid = '" + op.getUniqueId()+"'");	
		try {
			if(rs.next()) {
				long currenttime = System.currentTimeMillis();
				if(getBantime() > currenttime) {
					return true;
				}	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public Long getBantime() {
		ResultSet rs = LiteSQL.onQuery("SELECT bantime FROM playerdata WHERE uuid = '" + op.getUniqueId()+"'");	
		try {
		while (rs.next()) {
				return rs.getLong("bantime");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0l;
	}
}
