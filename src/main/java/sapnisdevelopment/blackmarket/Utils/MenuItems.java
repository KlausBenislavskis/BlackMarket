package sapnisdevelopment.blackmarket.Utils;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import sapnisdevelopment.blackmarket.BlackMarket;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static sapnisdevelopment.blackmarket.BlackMarket.economy;

public class MenuItems
{
  private static int rare = 0;
  private static int legendary = 0;
  private static int common = 0;
  private static Map<String, Long> lastBuys = new HashMap<>();
  private static long rareCooldown =
      BlackMarket.getInstance().getConfig().getLong("Cooldown.rare") * 1000;
  private static long commonCooldown =
      BlackMarket.getInstance().getConfig().getLong("Cooldown.common") * 1000;
  private static long legendaryCooldown =
      BlackMarket.getInstance().getConfig().getLong("Cooldown.legendary")
          * 1000;
  public static BlackMarket plugin = BlackMarket.getInstance();
  public static ItemStack lightGrayPane = new ItemStack(
      Material.STAINED_GLASS_PANE, 1, (short) 8);
  public static ItemStack grayPane = new ItemStack(Material.STAINED_GLASS_PANE,
      1, (short) 7);
  public static ItemStack blackPane = new ItemStack(Material.STAINED_GLASS_PANE,
      1, (short) 15);
  public static ItemStack yellowPane = new ItemStack(
      Material.STAINED_GLASS_PANE, 1, (short) 4);
  public static ItemStack orangePane = new ItemStack(
      Material.STAINED_GLASS_PANE, 1, (short) 1);
  public static ItemStack brownPane = new ItemStack(Material.STAINED_GLASS_PANE,
      1, (short) 12);
  public static ItemStack goldBlock = new ItemStack(Material.GOLD_BLOCK);
  public static ItemStack orangeWool = new ItemStack(Material.WOOL, 1,
      (short) 1);
  public static ItemStack brownGlass = new ItemStack(Material.STAINED_GLASS, 1,
      (short) 12);
  public static ItemStack istaisLoreRare = ((ItemStack) BlackMarket
      .getInstance().getConfig().get("RareItems." + rare));
  public static ItemStack istaisLoreCommon = (ItemStack) BlackMarket
      .getInstance().getConfig().get("CommonItems." + common);
  public static ItemStack istaisLoreLegendary = (ItemStack) BlackMarket
      .getInstance().getConfig().get("LegendaryItems." + legendary);
  public static ItemStack rareItem = istaisLoreRare.clone();
  public static ItemStack legendaryItem = istaisLoreLegendary.clone();
  public static ItemStack commonItem = istaisLoreCommon.clone();
  public static ItemStack infoBook = new ItemStack(Material.BOOK, 1);

  public MenuItems(Player player)
  {
    ItemMeta itemMeta;
    long now = System.currentTimeMillis();
    List<String> ja = new ArrayList<>();
    List<String> lore = new ArrayList<>();
    itemMeta = lightGrayPane.getItemMeta();
    itemMeta.setDisplayName(" ");
    lightGrayPane.setItemMeta(itemMeta);

    itemMeta = grayPane.getItemMeta();
    itemMeta.setDisplayName(" ");
    grayPane.setItemMeta(itemMeta);

    itemMeta = blackPane.getItemMeta();
    itemMeta.setDisplayName(" ");
    blackPane.setItemMeta(itemMeta);

    itemMeta = yellowPane.getItemMeta();
    itemMeta.setDisplayName(" ");
    yellowPane.setItemMeta(itemMeta);

    itemMeta = orangePane.getItemMeta();
    itemMeta.setDisplayName(" ");
    orangePane.setItemMeta(itemMeta);

    itemMeta = brownPane.getItemMeta();
    itemMeta.setDisplayName(" ");
    brownPane.setItemMeta(itemMeta);

    itemMeta = orangeWool.getItemMeta();
    itemMeta.setDisplayName(ColorUtils.color(BlackMarket.getInstance().getConfig().getString("names.orangeWool")));
    orangeWool.setItemMeta(itemMeta);

    itemMeta = brownGlass.getItemMeta();
    itemMeta.setDisplayName(ColorUtils.color(BlackMarket.getInstance().getConfig().getString("names.brownGlass")));
    brownGlass.setItemMeta(itemMeta);

    itemMeta = goldBlock.getItemMeta();
    itemMeta.setDisplayName(ColorUtils.color(BlackMarket.getInstance().getConfig().getString("names.goldBlock")));
    goldBlock.setItemMeta(itemMeta);

    itemMeta = infoBook.getItemMeta();
    itemMeta.setDisplayName(" ");
    ja = new ArrayList<>();
    for (String e : BlackMarket.getInstance().getConfig()
        .getStringList("lores.infoBook"))
    {
      ja.add(PlaceholderAPI.setPlaceholders(player, ColorUtils.color(e)));
    }
    itemMeta.setLore(ja);
    infoBook.setItemMeta(itemMeta);

    itemMeta = istaisLoreRare.getItemMeta();
    ja = new ArrayList<>();
    lore = new ArrayList<>();
    if (itemMeta.hasLore())
      for (String e : itemMeta.getLore())
      {
        ja.add(e);
      }

    if (valdiCooldown("rare", now))
    {
      lore.addAll(BlackMarket.getInstance().getConfig()
          .getStringList("lores.rareNoCooldown"));
      for (String e : lore)
      {
        ja.add(PlaceholderAPI.setPlaceholders(player, ColorUtils.color(e)));
      }
    }
    else
    {
      lore.addAll(BlackMarket.getInstance().getConfig()
          .getStringList("lores.rareOnCooldown"));
      for (String e : lore)
      {
        ja.add(PlaceholderAPI.setPlaceholders(player, ColorUtils.color(e)));
      }
    }
    itemMeta.setLore(ja);
    rareItem.setItemMeta(itemMeta);

    itemMeta = istaisLoreLegendary.getItemMeta();
    ja = new ArrayList<>();
    lore = new ArrayList<>();
    if (itemMeta.hasLore())
      for (String e : itemMeta.getLore())
      {
        ja.add(e);
      }

    if (valdiCooldown("legendary", now))
    {
      lore.addAll(BlackMarket.getInstance().getConfig()
          .getStringList("lores.legendaryNoCooldown"));
      for (String e : lore)
      {
        ja.add(PlaceholderAPI.setPlaceholders(player, ColorUtils.color(e)));
      }
    }
    else
    {
      lore.addAll(BlackMarket.getInstance().getConfig()
          .getStringList("lores.legendaryOnCooldown"));
      for (String e : lore)
      {
        ja.add(PlaceholderAPI.setPlaceholders(player, ColorUtils.color(e)));
      }
    }
    itemMeta.setLore(ja);
    legendaryItem.setItemMeta(itemMeta);

    itemMeta = istaisLoreCommon.getItemMeta();
    ja = new ArrayList<>();
    lore = new ArrayList<>();
    if (itemMeta.hasLore())
      for (String e : itemMeta.getLore())
      {
        ja.add(e);
      }

    if (valdiCooldown("common", now))
    {
      lore.addAll(BlackMarket.getInstance().getConfig()
          .getStringList("lores.commonNoCooldown"));
      for (String e : lore)
      {
        ja.add(PlaceholderAPI.setPlaceholders(player, ColorUtils.color(e)));
      }
    }
    else
    {
      lore.addAll(BlackMarket.getInstance().getConfig()
          .getStringList("lores.commonCooldown"));
      for (String e : lore)
      {
        ja.add(PlaceholderAPI.setPlaceholders(player, ColorUtils.color(e)));
      }
    }
    itemMeta.setLore(ja);
    commonItem.setItemMeta(itemMeta);
  }

  public static void updateItems()
  {
    updateRareItems();
    updateCommonItems();
    updateLegendaryItems();
  }

  public static void updateRareItems()
  {
    int newNumber = ThreadLocalRandom.current()
        .nextInt(0, plugin.getConfig().getInt("RareItems.amount"));
    if (rare != newNumber)
    {
      rare = newNumber;
      istaisLoreRare = ((ItemStack) BlackMarket.getInstance().getConfig()
          .get("RareItems." + rare));
      rareItem = istaisLoreRare.clone();
    }
    else
      updateRareItems();
  }

  public static void updateCommonItems()
  {
    int newNumber = ThreadLocalRandom.current()
        .nextInt(0, plugin.getConfig().getInt("CommonItems.amount"));
    if (common != newNumber)
    {
      common = newNumber;
      istaisLoreCommon = (ItemStack) BlackMarket.getInstance().getConfig()
          .get("CommonItems." + common);
      commonItem = istaisLoreCommon.clone();
    }
    else
      updateCommonItems();
  }

  public static void updateLegendaryItems()
  {
    int newNumber = ThreadLocalRandom.current()
        .nextInt(0, plugin.getConfig().getInt("LegendaryItems.amount"));
    if (legendary != newNumber)
    {
      legendary = newNumber;
      istaisLoreLegendary = (ItemStack) BlackMarket.getInstance().getConfig()
          .get("LegendaryItems." + legendary);
      legendaryItem = istaisLoreRare.clone();

    }
    else
      updateLegendaryItems();
  }

  public static void buyItem(Player player, String type)
  {
    if (BlackMarket.BlackMarketActive)
    {
      long now = System.currentTimeMillis();
      switch (type)
      {
        case "rare":
          if (valdiCooldown(type, now))
          {
            if (economy.getBalance(player) >= Integer.parseInt(
                (String) BlackMarket.getInstance().getConfig()
                    .get("prices.rare." + rare)))
            {
              economy.withdrawPlayer(player.getName(), Integer.parseInt(
                  (String) BlackMarket.getInstance().getConfig()
                      .get("prices.rare." + rare)));
              lastBuys.put(type, now);
              player.getInventory().addItem(istaisLoreRare);
              break;
            }
            player.closeInventory();
            player.sendMessage(Messages.get("no-money"));
          }
          break;
        case "legendary":
          if (valdiCooldown(type, now))
          {
            if (economy.getBalance(player) >= Integer.parseInt(
                (String) BlackMarket.getInstance().getConfig()
                    .get("prices.legendary." + legendary)))
            {
              economy.withdrawPlayer(player.getName(), Integer.parseInt(
                  (String) BlackMarket.getInstance().getConfig()
                      .get("prices.legendary." + legendary)));
              lastBuys.put(type, now);
              player.getInventory().addItem(istaisLoreLegendary);
              break;
            }
            player.closeInventory();
            player.sendMessage(Messages.get("no-money"));
          }
          break;
        case "common":
          if (valdiCooldown(type, now))
          {
            if (economy.getBalance(player) >= Integer.parseInt(
                (String) BlackMarket.getInstance().getConfig()
                    .get("prices.common." + common)))
            {
              economy.withdrawPlayer(player.getName(), Integer.parseInt(
                  (String) BlackMarket.getInstance().getConfig()
                      .get("prices.common." + common)));
              lastBuys.put(type, now);
              player.getInventory().addItem(istaisLoreCommon);
              break;
            }
            player.closeInventory();
            player.sendMessage(Messages.get("no-money"));
            break;
          }
      }

    }
    else
    {
      player.sendMessage(Messages.get("currentlyDisabled"));
      player.closeInventory();
    }
  }

  public static String getPrices(String type)
  {
    switch (type)
    {
      case "rare":
        return String.valueOf(BlackMarket.getInstance().getConfig()
            .get("prices." + type + "." + rare));
      case "common":
        return String.valueOf(BlackMarket.getInstance().getConfig()
            .get("prices." + type + "." + common));
      case "legendary":
        return String.valueOf(BlackMarket.getInstance().getConfig()
            .get("prices." + type + "." + legendary));
    }
    return "-1";
  }

  private static boolean valdiCooldown(String type, long time)
  {
    Long lol = 0l;
    switch (type)
    {
      case "rare":
        lol = rareCooldown;
        break;
      case "common":
        lol = commonCooldown;
        break;
      case "legendary":
        lol = legendaryCooldown;
    }
    Long lastbuy = lastBuys.get(type);
    if (lastbuy == null || time - lastbuy.longValue() >= lol)
      return true;
    return false;
  }

  public static int getRemainingCooldown(String type, long throwTime)
  {
    Long lol = 0l;
    switch (type)
    {
      case "rare":
        lol = rareCooldown;
        break;
      case "common":
        lol = commonCooldown;
        break;
      case "legendary":
        lol = legendaryCooldown;
    }
    Long lastPlayerPearl = lastBuys.get(type);
    if (lastPlayerPearl == null
        || throwTime - lastPlayerPearl.longValue() >= lol)
      return 0;
    return (int) Math
        .round(((throwTime - lastPlayerPearl.longValue()) - lol) / -1000.0D);
  }
}