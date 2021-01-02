import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.Stack;


public class BTCExRateAPI {

    private static String dateTime;

    private static String priceUSD;
    private static String priceGBP;
    private static String priceEUR;


    private static final String ExchangeRatesURL =
            "https://api.coindesk.com/v1/bpi/currentprice.json";

    public BTCExRateAPI() throws IOException {
        interpretResponse(readPage());
    }

    private static String readPage() throws IOException {
        URL url = new URL(ExchangeRatesURL);
        URLConnection urlConnection = url.openConnection();
        BufferedReader scan = new BufferedReader(
                new InputStreamReader(urlConnection.getInputStream()));

        String response;

        response = scan.readLine();

        return response;
    }

    private static void interpretResponse(String response) {
        String[] split = response.split("");
        Stack<Integer> openCurlys = new Stack<>();

        ArrayList<String> strings = new ArrayList<>();

        for (int i = 0, splitLength = split.length; i < splitLength; i++) {
            String current = split[i];

            if(current.equals("{")){
                openCurlys.push(i + 1);
            } else if (current.equals("}") && strings.size() < 4){
                strings.add(response.substring(openCurlys.pop(), i));
            }
        }

        String[] dataArray = strings.toArray(new String[0]);


        InterpretUpdateTime(dataArray[0]);

        InterpretPrice(dataArray[1]);
        InterpretPrice(dataArray[2]);
        InterpretPrice(dataArray[3]);
    }

    private static void InterpretUpdateTime(String data){
        String[] dataArray = data.split("updateduk");
        dateTime = dataArray[1].substring(3, dataArray[1].length()-1);
    }

    private static void InterpretPrice(String data){
        String[] dataArray = data.split(",");
        String code = dataArray[0].substring(8, 11);
        String rateFloat = dataArray[5].substring(13,dataArray[5].length());

        switch(code){
            case "USD":
                priceUSD = rateFloat;
                break;

            case "GBP":
                priceGBP = rateFloat;
                break;

            case "EUR":
                priceEUR = rateFloat;
                break;
        }
    }

    public static String getDateTime() {
        return dateTime;
    }

    public static float getFloatUSD() {
        return Float.parseFloat(priceUSD);
    }

    public static float getFloatGBP() {
        return Float.parseFloat(priceGBP);
    }

    public static float getFloatEUR() {
        return Float.parseFloat(priceEUR);
    }

    public static String getPriceUSD() {
        return priceUSD;
    }

    public static String getPriceGBP() {
        return priceGBP;
    }

    public static String getPriceEUR() {
        return priceEUR;
    }

    public static String[] getPageInfo() {
        return new String[]{priceUSD,priceGBP,priceEUR,dateTime};
    }
}
