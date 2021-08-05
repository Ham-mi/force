package Evt;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

public class EvtManageClick implements Listener{
	@EventHandler
	public void OnInventoryClick(InventoryClickEvent c) {
		
		Player pl = (Player)c.getWhoClicked();
		pl.sendMessage("w");
		
		ItemStack tnt = new ItemStack(Material.STAINED_GLASS_PANE);
		ItemMeta itemMeta = tnt.getItemMeta();
		itemMeta.setDisplayName("§f");
		tnt.setItemMeta(itemMeta);
		if(c.getCurrentItem().isSimilar(tnt)) {
			c.setCancelled(true);
		}
		itemMeta.setDisplayName("§7");
		tnt.setItemMeta(itemMeta);
		tnt.setDurability((short) 5);
		if(c.getCurrentItem().isSimilar(tnt)) {
			c.setCancelled(true);
			Inventory inven = c.getInventory();
		}
	}

}
