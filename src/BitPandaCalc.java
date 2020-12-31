public class BitPandaCalc extends Calculator {
    final double btcPremium = 0.0149;

    public double calcCost(double btcAmount,double btcRate) {
         double actualPrice  = btcAmount * btcRate;
         return btcPremium * actualPrice;
    }
}
