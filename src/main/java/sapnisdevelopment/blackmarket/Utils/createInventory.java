package sapnisdevelopment.blackmarket.Utils;

import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.InventoryManager;
import fr.minuskube.inv.SmartInventory;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import sapnisdevelopment.blackmarket.BlackMarket;

import java.util.concurrent.ThreadLocalRandom;

public class createInventory implements InventoryProvider
{
  private Player target;
  private SmartInventory inventory;
  private InventoryManager invManager = plugin.getInvManager();
  private static BlackMarket plugin = BlackMarket.getInstance();
  private  ItemStack rare;
  private  ItemStack legendary;
  private ItemStack common;
  private MenuItems menuItems;

  public createInventory()
  {
  }

  private void load()
  {        this.inventory = SmartInventory.builder()
      .manager(invManager)
      .provider(new createInventory())
      .size(5, 9)
      .title("test")
      .build();
  }

  public void open(Player player)
  {
    this.load();
    this.inventory.open(player);
  }

  @Override public void init(Player player, InventoryContents contents)
  {
    menuItems = new MenuItems(player);
    contents.fill(ClickableItem.empty(MenuItems.grayPane));
    int[][] blackPanes = {{0, 0}, {0, 8}, {2, 8}, {4, 0}, {4, 8}};

    for (int[] i : blackPanes)
    {
      contents.set(i[0], i[1], ClickableItem.empty(MenuItems.blackPane));
    }
    //Legendary
    contents.set(0, 2, ClickableItem.empty(MenuItems.yellowPane));
    contents.set(4, 2, ClickableItem.empty(MenuItems.yellowPane));
    contents.set(1, 2, ClickableItem.empty(MenuItems.goldBlock));
    contents.set(3, 2, ClickableItem.empty(MenuItems.goldBlock));
    contents.set(2, 2, ClickableItem.of(MenuItems.legendaryItem,e -> {
      if(e.isLeftClick() || e.isRightClick()) {
        MenuItems.buyItem(player,"legendary");}}));
    //rare
    contents.set(0, 4, ClickableItem.empty(MenuItems.orangePane));
    contents.set(4, 4, ClickableItem.empty(MenuItems.orangePane));
    contents.set(1, 4, ClickableItem.empty(MenuItems.orangeWool));
    contents.set(3, 4, ClickableItem.empty(MenuItems.orangeWool));
    contents.set(2, 4, ClickableItem.of(MenuItems.rareItem,e -> {
      if(e.isLeftClick() || e.isRightClick()) {
        MenuItems.buyItem(player,"rare");}}));

    //Common
    contents.set(0, 6, ClickableItem.empty(MenuItems.brownPane));
    contents.set(4, 6, ClickableItem.empty(MenuItems.brownPane));
    contents.set(1, 6, ClickableItem.empty(MenuItems.brownGlass));
    contents.set(3, 6, ClickableItem.empty(MenuItems.brownGlass));
    contents.set(2, 6, ClickableItem.of(MenuItems.commonItem,e -> {
      if(e.isLeftClick() || e.isRightClick()) {
        MenuItems.buyItem(player,"common");}}));
    //Info Book
    contents.set(2, 0, ClickableItem.empty(MenuItems.infoBook));
  }

  @Override public void update(Player player, InventoryContents contents)
  {
    menuItems = new MenuItems(player);
    contents.set(2, 4, ClickableItem.of(MenuItems.rareItem,e -> {
      if(e.isLeftClick() || e.isRightClick()) {
        MenuItems.buyItem(player,"rare");}}));
    contents.set(2, 6, ClickableItem.of(MenuItems.commonItem,e -> {
      if(e.isLeftClick() || e.isRightClick()) {
        MenuItems.buyItem(player,"common");}}));
    contents.set(2, 2, ClickableItem.of(MenuItems.legendaryItem,e -> {
      if(e.isLeftClick() || e.isRightClick()) {
        MenuItems.buyItem(player,"legendary");}}));
    contents.set(2, 0, ClickableItem.empty(MenuItems.infoBook));
  }

}
