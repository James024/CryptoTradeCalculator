import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class homeController implements Initializable {

    //coinbase labels
    @FXML
    private Label cb_btc_amt;
    @FXML
    private Label cb_ex_rate;
    @FXML
    private Label cb_real_price;
    @FXML
    private Label cb_percentage;
    @FXML
    private Label cb_final_price;

    // bitPanda labels
    @FXML
    private Label bp_btc_amt;
    @FXML
    private Label bp_ex_rate;
    @FXML
    private Label bp_real_price;
    @FXML
    private Label bp_percentage;
    @FXML
    private Label bp_final_price;

    // local bitcoin labels
    @FXML
    private Label lb_btc_amt;
    @FXML
    private Label lb_ex_rate;
    @FXML
    private Label lb_real_price;
    @FXML
    private Label lb_percentage;
    @FXML
    private Label lb_final_price;

    // text fields
    @FXML
    private TextField btc_amount_input;
    @FXML
    private TextField exchange_rate_input;
    @FXML
    private TextField final_price_input;

    // current currency label and buttons
    @FXML
    private Label curr_currency;
    @FXML
    private Button usd_button;
    @FXML
    private Button gbp_button;
    @FXML
    private Button eur_button;

    // exchange rates and update time
    @FXML
    private Label usd_price;
    @FXML
    private Label gbp_price;
    @FXML
    private Label eur_price;
    @FXML
    private Label update_time;
    @FXML
    private Button refresh;

    private String[] data;

    private String currCode = "USD";

    private Float exchangeRate;

    private Float btcAmount;

    private Float finalPrice;

    private Float customRate;

    private Float currPrice = 0f;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //run so data is there when loads
        refreshExchangeRates();
        refresh.setOnAction(event -> updateExchangeRates());
        usd_button.setOnAction(event -> calculate());
    }

    public void calculate(){

        interpretCustomExchangeRate();
        interpretFinalPrice();
        Boolean myBool = interpretBitcoinAmount();
        setExchangeRate(selectExchangeRate());
        calculateBitPanda(myBool);
        calculateLocalBitcoins(myBool);
    }


    public float selectExchangeRate(){
        int code;

        switch (currCode) {
            case "GBP":
                code = 1;
                break;
            case "EUR":
                code = 2;
                break;
            default:
                code = 0;
        }
        return Float.parseFloat(data[code]);
    }

    public void setFinalPrice(Float finalPrice) {
        this.finalPrice = finalPrice;
    }

    public void setBtcAmount(Float btcAmount) {
        this.btcAmount = btcAmount;
    }

    public void setExchangeRate(Float exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public void setCustomRate(Float customRate) {
        this.customRate = customRate;
    }

    public Boolean interpretBitcoinAmount(){
        String amount = btc_amount_input.getText();
        if(!amount.isEmpty()){
            setBtcAmount(Float.parseFloat(amount));
            return true;
        } else {
            setBtcAmount(null);
            return false;
        }
    }

    private void interpretCustomExchangeRate(){
        String rate = exchange_rate_input.getText();
        if(!rate.isEmpty()){
            setCustomRate(Float.parseFloat(rate));
        } else {
            setCustomRate(null);
        }
    }

    private Boolean interpretFinalPrice(){
        String price = final_price_input.getText();
        if(!price.isEmpty()){
            setFinalPrice(Float.parseFloat(price));
            return true;
        } else {
            setFinalPrice(null);
            return false;
        }
    }



    public void calculateBitPanda(Boolean amtSet){

        if(amtSet){
            finalPrice = null;
        } else {
            btcAmount = null;
        }

        BitPandaCalc bpCalc = new BitPandaCalc( btcAmount, exchangeRate, finalPrice);
        bp_btc_amt.setText(String.valueOf(bpCalc.getBtcAmount()));
        bp_ex_rate.setText(String.valueOf(bpCalc.getExchangeRate()));
        bp_real_price.setText(String.valueOf(bpCalc.getRealPrice()));
        bp_final_price.setText(String.valueOf(bpCalc.getFinalPrice()));
        bp_percentage.setText(bpCalc.getPercentageChange() + "%");
    }

    public void calculateLocalBitcoins(Boolean amtSet){

        if(amtSet){
            finalPrice = null;
        } else {
            btcAmount = null;
        }

        LocalBitcoinCalc lbCalc = new LocalBitcoinCalc(btcAmount, customRate, exchangeRate, finalPrice);
        lb_btc_amt.setText(String.valueOf(lbCalc.getBtcAmount()));
        lb_ex_rate.setText(String.valueOf(lbCalc.getExchangeRate()));
        lb_real_price.setText(String.valueOf(lbCalc.getRealPrice()));
        lb_final_price.setText(String.valueOf(lbCalc.getFinalPrice()));
        lb_percentage.setText(lbCalc.getPercentageChange() + "%");
    }

    public void checkChange(String lastUpdate, Float lastPrice) {
        if(data[3].equals(lastUpdate)){
            this.update_time.setText(data[3]+" -SAME ");

        } else {

            if(this.currPrice>lastPrice){
                usd_price.setTextFill(Color.GREEN);
                gbp_price.setTextFill(Color.GREEN);
                eur_price.setTextFill(Color.GREEN);
            } else if (this.currPrice<lastPrice){
                usd_price.setTextFill(Color.RED);
                gbp_price.setTextFill(Color.RED);
                eur_price.setTextFill(Color.RED);
            } else {
                usd_price.setTextFill(Color.ORANGE);
                gbp_price.setTextFill(Color.ORANGE);
                eur_price.setTextFill(Color.ORANGE);
            }
        }
    }

    public void updateExchangeRates(){

        String lastUpdate = this.data[3];
        Float lastPrice = this.currPrice;

        refreshExchangeRates();
        checkChange(lastUpdate,lastPrice);
    }

    public void refreshExchangeRates(){

        try {
            new BTCExRateAPI();
            this.data = BTCExRateAPI.getPageInfo();

            this.currPrice = Float.valueOf(data[0]);

            this.usd_price.setText(data[0]);
            this.gbp_price.setText(data[1]);
            this.eur_price.setText(data[2]);
            this.update_time.setText(data[3]);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setUsd_price(String price) {
        this.usd_price.setText("USD: "+price);
    }

    public void setGbp_price(String price) {
        this.gbp_price.setText("GBP: "+price);
    }

    public void setEur_price(String price) {
        this.eur_price.setText("EUR: "+price);
    }

    public void setUpdate_time(String time) {
        this.update_time.setText("Updated: "+time);
    }
}
