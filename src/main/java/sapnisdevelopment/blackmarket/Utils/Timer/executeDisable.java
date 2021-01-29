package sapnisdevelopment.blackmarket.Utils.Timer;

import sapnisdevelopment.blackmarket.BlackMarket;

import java.util.TimerTask;

class executeDisable extends TimerTask
{
  public void run()
  {
    BlackMarket.BlackMarketActive = false;
  }
}
