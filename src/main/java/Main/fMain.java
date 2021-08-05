package Main;

import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import Commands.CmdManage;
import Commands.CmdUpgrade;
import Commands.CmdUse;
import Evt.EvtManageClick;
import Evt.EvtUse;

public class fMain extends JavaPlugin{
	
	public static Plugin plugin;
	
	@Override
	public void onEnable() {
		getLogger().info("호출");
		getPlugin();
		getServer().getPluginManager().registerEvents((Listener)new EvtUse(), plugin);
		getServer().getPluginManager().registerEvents((Listener)new EvtManageClick(), plugin);
		getCommand("주문서관리").setExecutor((CommandExecutor)new CmdUse());
		getCommand("upgrade").setExecutor((CommandExecutor)new CmdUpgrade());
		getCommand("강화관리").setExecutor((CommandExecutor)new CmdManage());
	}
	@Override
	public void onDisable() {
		
	}
	public void getPlugin() {
		plugin = (Plugin)this;
	}
}
