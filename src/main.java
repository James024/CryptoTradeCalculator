import java.io.IOException;
import java.util.Arrays;

public class main {
        public static void main(String[] args) throws IOException {
                String response = BTCExRateAPI.readPage();
                String myString = "check (this is the string) this (shouldn't be contained)";
                System.out.println(BTCExRateAPI.getContents(myString, "("));
                String[] interpreted = BTCExRateAPI.interpretResponse(response);
                System.out.println(Arrays.toString(interpreted));
        }
}