package sapnisdevelopment.blackmarket.Utils.Timer;

import sapnisdevelopment.blackmarket.BlackMarket;

import java.util.TimerTask;

class everySecond extends TimerTask
{
  private final Timer timer;

  public everySecond(Timer timer)
  {
    this.timer = timer;
  }

  public void run()
  {
    if (BlackMarket.seconds > 0)
    {
      BlackMarket.seconds = BlackMarket.seconds - 1;
      timer.katruSekundi();
    }
  }
}
