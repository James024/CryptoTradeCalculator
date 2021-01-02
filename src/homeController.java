import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class homeController {

    //coinbase labels
    private Label cb_btc_amt;
    private Label cb_ex_rate;
    private Label cb_real_price;
    private Label cb_percentage;
    private Label cb_final_price;

    // bitPanda labels
    private Label bp_btc_amt;
    private Label bp_ex_rate;
    private Label bp_real_price;
    private Label bp_percentage;
    private Label bp_final_price;

    // local bitcoin labels
    private Label lb_btc_amt;
    private Label lb_ex_rate;
    private Label lb_real_price;
    private Label lb_percentage;
    private Label lb_final_price;

    // text fields
    private TextField btc_amount_input;
    private TextField exchange_rate_input;
    private TextField final_price_input;

    // current currency label and buttons
    private Label curr_currency;
    private Button usd_button;
    private Button gbp_button;
    private Button eur_button;

    // exchange rates and update time
    private Label usd_price;
    private Label gbp_price;
    private Label eur_price;

    private Label update_time;



    public homeController(Stage stage){

    }
}
