package de.pixelstudios.mcreloaded.utils;


import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_16_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_16_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_16_R3.PacketPlayOutTitle.EnumTitleAction;


public class Actionbar {

	private String message;
	
	public Actionbar(String msg){
		this.message = msg;
	}
	public void send(Player p) {
		PacketPlayOutTitle chat = new PacketPlayOutTitle(EnumTitleAction.ACTIONBAR, ChatSerializer.a("{\"text\":\""+ message +"\"}"));
		
		CraftPlayer cp = (CraftPlayer)p;
		cp.getHandle().playerConnection.sendPacket(chat);
	}
}
