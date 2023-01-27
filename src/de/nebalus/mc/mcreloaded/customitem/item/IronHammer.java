package de.nebalus.mc.mcreloaded.customitem.item;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ShapedRecipe;

public class IronHammer extends SuperTool
{
	public IronHammer() 
	{
		super(Material.IRON_PICKAXE, "iron_hammer");
		setDisplayName("Iron Hammer");
		setCustomModelData(305000);
		setLore("This hammer can break blocks", "in a 3x3 radius");
		
		ShapedRecipe ironhammer = new ShapedRecipe(getNamespacedKey(), getAsItemStack());
		ironhammer.shape("LLL", " N ", " N ");
		ironhammer.setIngredient('N', Material.STICK);
		ironhammer.setIngredient('L', Material.IRON_BLOCK);
		Bukkit.getServer().addRecipe(ironhammer);	
	}
}
