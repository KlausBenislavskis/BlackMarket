package sapnisdevelopment.blackmarket.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import sapnisdevelopment.blackmarket.BlackMarket;
import sapnisdevelopment.blackmarket.Utils.MenuItems;
import sapnisdevelopment.blackmarket.Utils.createInventory;

public class blackMarketCommands implements CommandExecutor
{
  @Override public boolean onCommand(CommandSender sender, Command command,
      String label, String[] args)
  {
    if (sender.isOp()){
      if (args.length == 3){
        if (args[0].equals("add")){
          Player player = (Player) sender;
          ItemStack itemInHand = player.getItemInHand();
          if (args[1].equals("rare")){
            int index = BlackMarket.getInstance().getConfig().getInt("RareItems.amount");
            BlackMarket.getInstance().getConfig().set("RareItems." + index, itemInHand);
            BlackMarket.getInstance().getConfig().set("prices.rare." + index, args[2]);
            BlackMarket.getInstance().getConfig().set("RareItems.amount", index + 1);
            BlackMarket.getInstance().saveConfig();
            BlackMarket.getInstance().reloadConfig();
            return true;

          }else if (args[1].equals("common")){

            int index = BlackMarket.getInstance().getConfig().getInt("CommonItems.amount");
            BlackMarket.getInstance().getConfig().set("CommonItems." + index, itemInHand);
            BlackMarket.getInstance().getConfig().set("prices.common." + index, args[2]);
            BlackMarket.getInstance().getConfig().set("CommonItems.amount", index + 1);
            BlackMarket.getInstance().saveConfig();
            BlackMarket.getInstance().reloadConfig();
            return true;
          }
          else if (args[1].equals("legendary")){
            int index = BlackMarket.getInstance().getConfig().getInt("LegendaryItems.amount");
            BlackMarket.getInstance().getConfig().set("LegendaryItems." + index, itemInHand);
            BlackMarket.getInstance().getConfig().set("prices.legendary." + index, args[2]);
            BlackMarket.getInstance().getConfig().set("LegendaryItems.amount", index + 1);
            BlackMarket.getInstance().saveConfig();
            BlackMarket.getInstance().reloadConfig();
            return true;
          }
      }
      }
      if (args.length == 1){
        if (args[0].equals("enable")){
          BlackMarket.BlackMarketActive = true;
          BlackMarket.getInstance().reloadConfig();
          MenuItems.updateItems();
          return true;
        }
        else if (args[0].equals("disable")){
          BlackMarket.BlackMarketActive = false;
          return true;
        }
        if (BlackMarket.BlackMarketActive){
          createInventory ja = new createInventory();
          ja.open(Bukkit.getPlayer(sender.getName()));
          return false;
        }
      }
    }
    createInventory ja = new createInventory();
    ja.open((Player) sender);
    return false;
  }
}
