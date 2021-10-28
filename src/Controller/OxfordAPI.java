package Controller;

import org.json.JSONObject;
import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class OxfordAPI {
    public static String searchWord(String word) {
        String language = "en";
        String toLowerCase = word.trim().toLowerCase();
        return "https://od-api.oxforddictionaries.com/api/v2/entries/" + language + "/" + toLowerCase;
    }

    public static String retrieveData(String param) {
        String appID = "80245a20";
        String appKey = "4fc0f3e5a7995049700cc9fc503d7617";
        try {
            URL url = new URL(param);
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setRequestProperty("app_id", appID);
            urlConnection.setRequestProperty("app_key", appKey);

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder  stringBuilder = new StringBuilder();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line );
            }

            JSONObject json = new JSONObject(stringBuilder.toString());


            // get the first object element in result array.
            JSONObject filter1 = json.getJSONArray("results").getJSONObject(0);
            // get the first object element in lexicalEntries.
            JSONObject filter2 = filter1.getJSONArray("lexicalEntries").getJSONObject(0);
            // get the first object element in entries array.
            JSONObject filter3 = filter2.getJSONArray("entries").getJSONObject(0);
            // get the fiest object element in senses array.
            JSONObject filter4 = filter3.getJSONArray("senses").getJSONObject(0);
            // get the first object element in sense array.
            String filter5 = filter4.getJSONArray("definitions").getString(0);
            return filter5;

        } catch (Exception e) {
            System.out.println("something went wrong when try to retrieve data from oxford dictionay api!!!");
            return null;
        }
    }
}
