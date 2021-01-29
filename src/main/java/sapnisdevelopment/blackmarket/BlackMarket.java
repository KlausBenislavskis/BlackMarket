package sapnisdevelopment.blackmarket;

import fr.minuskube.inv.InventoryManager;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import sapnisdevelopment.blackmarket.Commands.blackMarketCommands;
import sapnisdevelopment.blackmarket.Utils.Expansions;
import sapnisdevelopment.blackmarket.Utils.MenuItems;
import sapnisdevelopment.blackmarket.Utils.Messages;

public final class BlackMarket extends JavaPlugin
{
  public static Economy economy;
  private InventoryManager invManager;
  private static BlackMarket instance;
  public static boolean BlackMarketActive = false;
  public static Long seconds;
  @Override public void onEnable()
  {
    instance = this;
    this.saveDefaultConfig();
    getCommand("blackmarket").setExecutor(new blackMarketCommands());
    invManager = new InventoryManager(this);
    invManager.init();
    new Messages();
    if (getConfig().isConfigurationSection("messages"))
      Messages.load(getConfig().getConfigurationSection("messages"));
    if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
      new Expansions().register();
    } else {
      getLogger().info("Could not find PlaceholderAPI! This plugin is required for placeholders.");
      Bukkit.getPluginManager().disablePlugin(this);
    }
    PluginManager pm = Bukkit.getPluginManager();
    if (pm.getPlugin("Vault") == null) {
      pm.disablePlugin(this);
    } else {
      RegisteredServiceProvider<Economy> service = Bukkit.getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
      if (service != null)
        economy = service.getProvider();
    }
  }

  @Override public void onDisable()
  {
    // Plugin shutdown logic
  }
  public static BlackMarket getInstance() {
    return instance;
  }
  public InventoryManager getInvManager() {
    return invManager;
  }
}
