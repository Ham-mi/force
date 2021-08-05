package Commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CmdUpgrade implements CommandExecutor{

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player p = (Player)sender;
		if(p.hasMetadata("강화")==true) {
			p.sendMessage("강화가 진행중일때는 강화창을 오픈하실 수 없습니다.");
			return false;
		}
		p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_USE, 1, 0);
		
		Inventory inven = Bukkit.createInventory((InventoryHolder)p, 9*5, "강화");
		ItemStack item = new ItemStack(Material.STAINED_GLASS_PANE);
		ItemMeta data = item.getItemMeta();
		data.setDisplayName("§f");
		item.setItemMeta(data);
		for(int i=0; i<45;i++) {
			if(i==10 || i==13 || i==16 || i==28 || i==30 || i==31 || i==32) continue;
			inven.setItem(i, item);
		}
		data.setDisplayName("§7");
		item.setItemMeta(data);
		item.setDurability((short) 5);
		inven.setItem(30, item);
		inven.setItem(31, item);
		inven.setItem(32, item);
		p.openInventory(inven);
		return true;
	}

}
