public class LocalBitcoinCalc extends Calculator {
    private Float btcAmount;
    private Float finalPrice;
    private Float realPrice;
    private final Float customRate;
    private final Float percentageChange;

    public LocalBitcoinCalc(Float btcAmount, Float customRate, Float exchangeRate, Float finalPrice) {
        super(btcAmount, exchangeRate, finalPrice);


        if ((btcAmount != null) && (finalPrice) == null){

            this.btcAmount = btcAmount;

            this.finalPrice = btcAmount * customRate;

            this.realPrice = btcAmount * exchangeRate;

        } else if((btcAmount == null) && (finalPrice) != null){

            this.finalPrice = finalPrice;

            this.btcAmount = finalPrice / customRate;

            this.realPrice = this.btcAmount * exchangeRate;

        }


        this.customRate = customRate;
        this.percentageChange = ((customRate-exchangeRate)/exchangeRate)*100;
    }

    public Float getBtcAmount(){return btcAmount;}

    public Float getRealPrice(){return realPrice;}

    public Float getFinalPrice() {
        return finalPrice;
    }

    public Float getPercentageChange(){
        return percentageChange;
    }

}
