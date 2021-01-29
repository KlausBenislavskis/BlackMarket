package sapnisdevelopment.blackmarket.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import sapnisdevelopment.blackmarket.BlackMarket;
import sapnisdevelopment.blackmarket.Utils.MenuItems;
import sapnisdevelopment.blackmarket.Utils.Messages;
import sapnisdevelopment.blackmarket.Utils.Timer.Timer;
import sapnisdevelopment.blackmarket.Utils.createInventory;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class blackMarketCommands implements CommandExecutor
{
  @Override public boolean onCommand(CommandSender sender, Command command,
      String label, String[] args)
  {
    if (sender.isOp())
    {
      if (args.length == 3)
      {
        if (args[0].equals("add"))
        {
          Player player = (Player) sender;
          ItemStack itemInHand = player.getItemInHand();
          if (args[1].equals("rare"))
          {
            int index = BlackMarket.getInstance().getConfig()
                .getInt("RareItems.amount");
            BlackMarket.getInstance().getConfig()
                .set("RareItems." + index, itemInHand);
            BlackMarket.getInstance().getConfig()
                .set("prices.rare." + index, args[2]);
            BlackMarket.getInstance().getConfig()
                .set("RareItems.amount", index + 1);
            BlackMarket.getInstance().saveConfig();
            BlackMarket.getInstance().reloadConfig();
            sender.sendMessage(Messages.get("added"));
            return true;

          }
          else if (args[1].equals("common"))
          {

            int index = BlackMarket.getInstance().getConfig()
                .getInt("CommonItems.amount");
            BlackMarket.getInstance().getConfig()
                .set("CommonItems." + index, itemInHand);
            BlackMarket.getInstance().getConfig()
                .set("prices.common." + index, args[2]);
            BlackMarket.getInstance().getConfig()
                .set("CommonItems.amount", index + 1);
            BlackMarket.getInstance().saveConfig();
            BlackMarket.getInstance().reloadConfig();
            sender.sendMessage(Messages.get("added"));
            return true;
          }
          else if (args[1].equals("legendary"))
          {
            int index = BlackMarket.getInstance().getConfig()
                .getInt("LegendaryItems.amount");
            BlackMarket.getInstance().getConfig()
                .set("LegendaryItems." + index, itemInHand);
            BlackMarket.getInstance().getConfig()
                .set("prices.legendary." + index, args[2]);
            BlackMarket.getInstance().getConfig()
                .set("LegendaryItems.amount", index + 1);
            BlackMarket.getInstance().saveConfig();
            BlackMarket.getInstance().reloadConfig();
            sender.sendMessage(Messages.get("added"));
            return true;
          }
          sender.sendMessage(Messages.get("usage"));
        }
      }
      if (args.length == 1)
      {
        if (args[0].equals("enable"))
        {
          if (BlackMarket.getInstance().getConfig().getInt("RareItems.amount")
              > 1 &&
              BlackMarket.getInstance().getConfig().getInt("CommonItems.amount")
                  > 1 && BlackMarket.getInstance().getConfig()
              .getInt("LegendaryItems.amount") > 1)
          {
            sender.sendMessage(Messages.get("enabled"));
            BlackMarket.BlackMarketActive = true;
            BlackMarket.getInstance().reloadConfig();
            MenuItems.updateItems();
            Timer ja = new Timer();
            ja.blackMarketTimer(BlackMarket.getInstance().getConfig().getString("activeForMinutes"));
            return true;
          }
          else sender.sendMessage(Messages.get("no-items"));
          return true;
        }
        else if (args[0].equals("disable"))
        {
          sender.sendMessage(Messages.get("disabled"));
          BlackMarket.BlackMarketActive = false;
          return true;
        }
        else if (args[0].equals("help")){
          List<String> message = Messages.getList("help-command");

          for (int i = 0; i < message.size(); i++) {
            sender.sendMessage(message.get(i));
          }
          return true;
        }
        if (BlackMarket.BlackMarketActive)
        {
          createInventory ja = new createInventory();
          ja.open(Bukkit.getPlayer(args[0]));
          return false;
        }
        else{
          Bukkit.getPlayer(args[0]).sendMessage(Messages.get("currentlyDisabled"));
          return true;
        }
      }
      List<String> message = Messages.getList("help-command");

      for (int i = 0; i < message.size(); i++) {
        sender.sendMessage(message.get(i));
      }
      return true;
    }
    sender.sendMessage("no-permission");
    return false;
  }
}
