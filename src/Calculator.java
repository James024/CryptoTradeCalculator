public abstract class Calculator {
    private Float btcAmount;
    private final Float exchangeRate;
    private Float finalPrice;
    
    public Calculator(Float btcAmount, Float exchangeRate, Float finalPrice){
        this.btcAmount = btcAmount;
        this.exchangeRate = exchangeRate;
        this.finalPrice = finalPrice;


        if ((this.btcAmount != null) && (finalPrice) == null){

            this.finalPrice = this.btcAmount * this.exchangeRate;

        } else if((this.btcAmount == null) && (finalPrice) != null){

            this.btcAmount = finalPrice / exchangeRate;
        }
    }

    public Float getBtcAmount() {
        return btcAmount;
    }

    public Float getExchangeRate() {
        return exchangeRate;
    }

    public Float getFinalPrice(){
        return finalPrice;
    }


}
