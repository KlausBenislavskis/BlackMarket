package sapnisdevelopment.blackmarket.Utils.Timer;

import org.bukkit.command.CommandSender;
import sapnisdevelopment.blackmarket.BlackMarket;

import java.util.concurrent.TimeUnit;

public class Timer
{
  public java.util.Timer timer;

  public Timer()
  {
  }

  public void blackMarketTimer(String Minutes)
  {
    int minutes;

    try
    {
      minutes = Integer.parseInt(Minutes);
    }
    catch (NumberFormatException ex)
    {
      return;
    }
    timer = new java.util.Timer();
    BlackMarket.seconds = TimeUnit.MINUTES.toSeconds(minutes);
    timer.schedule(new executeDisable(), TimeUnit.MINUTES.toMillis(minutes));
    katruSekundi();
  }

  public void katruSekundi()
  {
    timer.schedule(new everySecond(this), 1000);
  }

}
