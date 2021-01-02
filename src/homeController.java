import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.cb_btc_amt.setText("30000");


    }

    public void updateExchangeRates(String[] data){
        this.usd_price.setText(data[0]);
        this.gbp_price.setText(data[1]);
        this.eur_price.setText(data[2]);
        this.update_time.setText(data[3]);
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
