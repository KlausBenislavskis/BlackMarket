package sapnisdevelopment.blackmarket.Utils;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;

import static org.bukkit.Statistic.*;

public class Expansions extends PlaceholderExpansion {
  private final DecimalFormat timeFormat = new DecimalFormat("#0.0");

  @Override
  public boolean persist(){
    return true;
  }

  @Override
  public boolean canRegister(){
    return true;
  }

  @Override
  public String getAuthor(){
    return "Latvish";
  }

  @Override
  public String getIdentifier(){
    return "blackmarket";
  }

  @Override
  public String getVersion(){
    return "1.0.0";
  }

  @Override
  public String onRequest(OfflinePlayer player, String identifier){

    if (identifier.equals("cooldown_rare")) {
      long now = System.currentTimeMillis();
      return String.valueOf( MenuItems.getRemainingCooldown("rare", now));
    }
    if (identifier.equals("cooldown_common")){
      long now = System.currentTimeMillis();
      return String.valueOf( MenuItems.getRemainingCooldown("common", now));
    }
    if (identifier.equals("cooldown_legendary")){
      long now = System.currentTimeMillis();
      return String.valueOf( MenuItems.getRemainingCooldown("legendary", now));
    }
    if (identifier.equals("price_rare")){
      return MenuItems.getPrices("rare");
    }
    if (identifier.equals("price_common")){
      return MenuItems.getPrices("common");
    }
    if (identifier.equals("price_legendary")){
      return MenuItems.getPrices("legendary");
    }
    return null;
  }

}

