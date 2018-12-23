package httpRespons;

import bot.Bot;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpResponser {

    private static final Logger log = (Logger) LogManager.getLogger(HttpResponser.class);

    public static JSONObject getResponse(String url) {
        StringBuffer response = new StringBuffer();
        JSONObject jsonObject = new JSONObject();
        try {
            URL _url = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) _url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            int responseCode = connection.getResponseCode();
            log.trace("Response Code : " + responseCode);
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String inputLine;

            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }

            jsonObject = new JSONObject(response.toString());

            br.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
