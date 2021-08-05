package Commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CmdManage implements CommandExecutor{

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!sender.hasPermission("admin") && sender instanceof Player) {
			sender.sendMessage("당신은 이 명령어에 접근할 권한이 없습니다.");
			return false;
		}
		if(args.length < 4) {
			sender.sendMessage("");
			sender.sendMessage("/강화관리 일반주문서 [옵션] [일반확률] [하락확률] §7- 주문서를 만드실 수 있습니다.");
			sender.sendMessage("/강화관리 초월주문서 [옵션] [일반확률] [하락확률] §7- 초월주문서를 만드실수 있습니다.");
			sender.sendMessage("");
			sender.sendMessage("§f옵션은 §7[ 효율,내구성,행운 ] §f중에서 가능합니다.");
			sender.sendMessage("");
			return false;
		}
		if(args.length == 4) {
			if(args[0].equals("일반주문서")||args[0].equals("초월주문서")) {
				if(args[1].equals("효율") || args[1].equals("내구성") || args[1].equals("행운")) {
					Player p = (Player)sender;
					ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
					ItemMeta itemMeta = item.getItemMeta();
					itemMeta.setDisplayName("§6§l["+ args[0] +"] §e" + args[1] +"§f강화 주문서 §c§o( "+ args[2] +"% )");
					List<String> lore = new ArrayList<String>();
					lore.add("§8§l§m───────────────────────────────────────");
					lore.add("§c§l■ §f해당 아이템은 도구의 §e§l"+ args[1] + "§f 인첸트를 조정합니다.");
					lore.add("§6§l■ §f강화 시도시 §a§l" + args[2] + "% §f확률로 레벨이 조정됩니다.");
					lore.add("§e§l■ §f강화 실패시 §c§l" + args[3] + "% §f확률로 옵션이 하락합니다.");
					lore.add("§8§l§m───────────────────────────────────────");
					itemMeta.setLore(lore);
					item.setItemMeta(itemMeta);
					p.getInventory().addItem(item);
					p.sendMessage("성공적으로 " + args[0] + "를 생성하였습니다.");
					return true;
				}else {
					sender.sendMessage("해당 주문서 종류는 존재하지 않습니다.");
					return false;
				}
			}
		}
		return false;
	}

}
