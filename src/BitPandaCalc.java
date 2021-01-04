public class BitPandaCalc extends Calculator {

    private Float btcAmount, finalPrice, realPrice;
    private final Float percentageChange;

    public BitPandaCalc(Float btcAmount, Float exchangeRate, Float finalPrice){
        super(btcAmount, exchangeRate, finalPrice);


        float btcPremium = 0.0149F;

        if ((btcAmount != null) && (finalPrice) == null){

            this.realPrice = btcAmount * exchangeRate;
            this.finalPrice = (1 + btcPremium) * this.realPrice;
            this.btcAmount = btcAmount;

        } else if((btcAmount == null) && (finalPrice) != null){

            this.btcAmount = (finalPrice * (1 - btcPremium)) / exchangeRate;
            this.realPrice = this.btcAmount * exchangeRate;
            this.finalPrice = finalPrice;

        }


        this.percentageChange = btcPremium * 100;

    }

    public Float getRealPrice() {
        return this.realPrice;
    }

    public Float getFinalPrice() {
         return this.finalPrice;
    }

    public Float getBtcAmount(){
        return this.btcAmount;
    }

    public Float getPercentageChange(){
        return this.percentageChange;
    }
}
