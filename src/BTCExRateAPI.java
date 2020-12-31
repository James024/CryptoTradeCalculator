import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Stack;

public class BTCExRateAPI {

    private static String exchangeRatesLong = "";

    private static final String ExchangeRatesURL =
            "https://api.coindesk.com/v1/bpi/currentprice.json";

    public static String readPage() throws IOException {
        URL url = new URL(ExchangeRatesURL);
        URLConnection urlConnection = url.openConnection();
        BufferedReader scan = new BufferedReader(
                new InputStreamReader(urlConnection.getInputStream()));

        String response;

        response = scan.readLine();

        return response;
    }

    public static String[] interpretResponse(String response) {
        String[] split = response.split("code");
        Stack<Integer> openCurlys = new Stack<>();
        Stack<Integer> closeCurlys = new Stack<>();

        Boolean open = false;

        for (int i = 1, splitLength = split.length; i < splitLength; i++) {
            String current = split[i];

            if(current.equals("{")){
                openCurlys.push(i);
            } else if (current.equals("}")){
                closeCurlys.push(i);
            }

        }


        return response.split(",");
    }
}
