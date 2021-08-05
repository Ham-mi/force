package Evt;


import java.util.Random;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class EvtUse implements Listener{
    @EventHandler
    public void OnInventoryClick2(InventoryClickEvent c) {
        if(c.getClick() == null) return;
        Player pl = (Player)c.getWhoClicked();
        ItemStack plitem = pl.getItemOnCursor();
        if(plitem.toString().contains("주문서")) {
            pl.sendMessage(c.getCurrentItem().toString());
            ItemStack item = c.getCurrentItem();
            if(item.toString().contains("AXE")) {
                c.setCancelled(true);
                
                String level = plitem.getItemMeta().getLore().get(1);
                String enchant = plitem.getItemMeta().getDisplayName();
                String chance = plitem.getItemMeta().getLore().get(2);
                
                level = level.replaceAll("\\s*[\\(\\!\\)]*[가-힣]*[:]*", "");
                chance = chance.replaceAll("\\s*[\\(\\!\\)]*[가-힣]*[:]*[\\%]*", "");
                level = level.replaceAll("[§6lfe]", "");
                chance = chance.replaceAll("[§6lfe]", "");
                pl.sendMessage(level);
                pl.sendMessage(enchant);
                pl.sendMessage(chance);
                
                int ichance = Integer.parseInt(chance);
                String[] split = level.split("~");
                int min = Integer.parseInt(split[0]);
                int max = Integer.parseInt(split[1]);
                //nextint(max - min + 1) + min
                Random rand = new Random();
                int levelrand = rand.nextInt(max - min + 1)+min; //min~max
                int chancerand = rand.nextInt(100)+1; // 1~100
                if(chancerand<ichance) {
                    ItemStack air = new ItemStack(Material.AIR);
                    Enchantment ench = null;
                    if(enchant.contains("내구성")) {
                        ench = Enchantment.DURABILITY;
                    }else if(enchant.contains("행운")) {
                        ench = Enchantment.LOOT_BONUS_BLOCKS;
                    }else if(enchant.contains("효율")) {
                        ench = Enchantment.DIG_SPEED;
                    }
                    item.addUnsafeEnchantment(ench, levelrand);
                    pl.setItemOnCursor(air);
                    pl.closeInventory();
                    pl.sendTitle("Enchant Succeed!", "인첸트를 성공하셨습니다 !",0,100,0);
                    pl.playSound(pl.getLocation(),Sound.ENTITY_PLAYER_LEVELUP, 1, 5);
                }
                
                else {
                    ItemStack air = new ItemStack(Material.AIR);
                    pl.setItemOnCursor(air);
                    pl.closeInventory();
                    pl.playSound(pl.getLocation(),Sound.ENTITY_ITEM_BREAK, 1, 5);
                    pl.sendTitle("Enchant Failed!", "인첸트를 실패하셨습니다 !",0,100,0);
                }    
                
                
            }
        }
    }
}