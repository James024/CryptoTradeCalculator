public class LocalBitcoinCalc extends Calculator {
    public float FinalPrice(Float btcAmount, Float customRate){
        return btcAmount * customRate;
    }
    public float PercentageChange(Float exchangeRate, Float customRate){
        return Math.round(((customRate-exchangeRate)/exchangeRate)*100);
    }

    public float BtcAmount(Float finalPrice, Float customRate){
        return finalPrice/customRate;
    }

}
