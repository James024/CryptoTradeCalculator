import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        HomeScene scene = new HomeScene(primaryStage);
    }

        public static void main (String[]args) throws IOException {
            new BTCExRateAPI();
            System.out.println(BTCExRateAPI.getPriceGBP());
            System.out.println(BTCExRateAPI.getPriceUSD());
            System.out.println(BTCExRateAPI.getPriceEUR());
            System.out.println(BTCExRateAPI.getDateTime());

            launch(args);

        }
    }