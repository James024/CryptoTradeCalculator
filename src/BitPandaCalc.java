public class BitPandaCalc extends Calculator {
    final float btcPremium = 0.0149f;

    public float FinalPrice(float btcAmount,float btcRate) {
         float actualPrice  = btcAmount * btcRate;
         return (1 + btcPremium) * actualPrice;
    }

    public float BtcAmount(float finalPrice, float exchangeRate){
        float actualPrice = finalPrice / btcPremium;
        return actualPrice/exchangeRate;
    }

    public float percentageIncrease(){
        return btcPremium;
    }
}
