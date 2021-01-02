import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

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
}
