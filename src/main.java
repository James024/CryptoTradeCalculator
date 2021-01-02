import java.io.IOException;
import java.util.Arrays;

public class main {
        public static void main(String[] args) throws IOException {
                new BTCExRateAPI();
                System.out.println(BTCExRateAPI.getPriceGBP());
                System.out.println(BTCExRateAPI.getPriceUSD());
                System.out.println(BTCExRateAPI.getPriceEUR());
                System.out.println(BTCExRateAPI.getDateTime());
        }
}