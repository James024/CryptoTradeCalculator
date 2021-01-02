import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

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
        String[] split = response.split("");

        ArrayList<String> strings = new ArrayList<>();

        Stack<Integer> openers = new Stack<>();

        for (int i = 0, splitLength = split.length; i < splitLength; i++) {
            String current = split[i];

            if(current.equals("{")) {
                strings.add(getContents(response.substring(i), "{"));
            }
        }

        return strings.toArray(new String[0]);

    }

    //returns the string between boundary
    public static String getContents(String sequence, String boundary){
        String foundSequence = "";

        ArrayList<String> strings = new ArrayList<>();

        String[] split = sequence.split("");

        Stack<Integer> openers = new Stack<>();

        String closer;

        switch (boundary){
            case "{":
                closer = "}";
                break;
            case "[":
                closer = "]";
                break;
            case "(":
                closer = ")";
                break;
            default:
                closer = boundary;
        }

        for (int i = 0, splitLength = split.length; i < splitLength; i++) {
            String current = split[i];

            if(current.equals(boundary)) {
                openers.push(i);

            } else if (current.equals(closer)){
                foundSequence = sequence.substring(openers.pop()+1,i);
            }
        }

        return foundSequence;

    }
}
