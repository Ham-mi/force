package Commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

public class CmdUse implements CommandExecutor{

	String option = "§6§l(!)§f";
	String option2 = "§c§l(!)§f";
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!sender.hasPermission("admin") && sender instanceof Player) {
			sender.sendMessage("a");
			return true;
		}
		if(args.length < 3 ) {
			sender.sendMessage(option+"/주문서 관리 [효율,내구성,행운] [최소레벨~최대레벨] [확률]");
			return true;
		}
		if(args.length == 3) {
			if(!args[1].contains("~")) {
				sender.sendMessage(option+"/주문서 관리 [효율,내구성,행운] [최소레벨~최대레벨] [확률]");
				return true;
			}
			Player player = (Player)sender;
			String enchant = "";
			if(args[0].equals("효율")) {
				enchant = "효율";
			}else if(args[0].equals("내구성")) {
				enchant = "내구성";
			}else if(args[0].equals("행운")) {
				enchant = "행운";
			}else {
				sender.sendMessage("잘못된 인수 입니다.");
				return true;
			}
			ItemStack item = new ItemStack( Material.ENCHANTED_BOOK );
			List<String> lore = new ArrayList<String>();
			lore.add("");
			lore.add( option+ " 기대 레벨 : §e"+ args[1]);
			lore.add( option+ " 강화 확률 : §e"+ args[2]+"%");
			lore.add("");
			lore.add( option2+ " 해당 아이템을 드래그하여 도구에 올려주세요!");
			lore.add( option2+ " §c§l※ §f인첸트가 붙어있어도 주문서 강화 진행이 가능합니다.");
			lore.add("");
			ItemMeta data2 = item.getItemMeta();
			data2.setLore(lore);
			data2.setDisplayName(enchant+" 강화 주문서");
			item.setItemMeta(data2);
			player.getInventory().addItem(item);
			sender.sendMessage(option + " 성공적으로 "+ enchant +" 강화 주문서를 생성하였습니다.");
		}
		return false;
	}

}
