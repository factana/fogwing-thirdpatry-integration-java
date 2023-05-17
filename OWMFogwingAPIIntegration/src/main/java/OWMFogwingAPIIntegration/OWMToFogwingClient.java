package OWMFogwingAPIIntegration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OWMToFogwingClient {
    private static final Logger LOGGER = Logger.getLogger(OWMToFogwingClient.class.getName());

    // Variables to store API credentials and configuration
    private String owpApiUrl;    // OpenWeatherMap API URL
    private String owpApiKey;   // OpenWeatherMap API Key
    private String city;        // City name for weather data
    private String fwApiUrl;    // Fogwing API URL
    private String fwAccId;     // Fogwing Account ID
    private String fwApiKey;    // Fogwing API Key
    private String edgeEui;     // Fogwing Edge EUI

    public OWMToFogwingClient() {
        try {
            // Read the API credentials from the credentials file
            String credContent = new String(Files.readAllBytes(Paths.get("credentials.json")));
            JSONObject cred = new JSONObject(credContent);

            // OpenWeatherMap Credentials
            JSONObject owpCred = cred.getJSONObject("OWP_CRED");
            owpApiUrl = owpCred.getString("API_URL");
            owpApiKey = owpCred.getString("API_KEY");
            city = owpCred.getString("CITY_NAME");

            // Fogwing Credentials
            JSONObject fwCred = cred.getJSONObject("FW_CRED");
            fwApiUrl = fwCred.getString("API_URL");
            fwAccId = fwCred.getString("ACCOUNT_ID");
            fwApiKey = fwCred.getString("API_KEY");
            edgeEui = fwCred.getString("EDGE_EUI");
        } catch (IOException | JSONException ex) {
            LOGGER.log(Level.SEVERE, "Error reading configuration file", ex);
        }
    }

    // Method to get weather data from OWM API
    public JSONObject getOwmData() {
        try {
            String url = owpApiUrl + "?q=" + city + "&appid=" + owpApiKey;
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(url).build();
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                String responseData = response.body().string();
                return new JSONObject(responseData);
            } else {
                LOGGER.log(Level.SEVERE, "Error getting OWM data: " + response.body().string());
            }
        } catch (IOException | JSONException ex) {
            LOGGER.log(Level.SEVERE, "Error getting OWM data", ex);
        }
        return null;
    }

    // Method to send OWM data to Fogwing API
    public JSONObject sendDataToFogwing(JSONObject owmData) {
        try {
            OkHttpClient client = new OkHttpClient();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(owmData.toString(), mediaType);
            Request request = new Request.Builder()
                    .url(fwApiUrl)
                    .post(body)
                    .addHeader("accountID", fwAccId)
                    .addHeader("apiKey", fwApiKey)
                    .addHeader("edgeEUI", edgeEui)
                    .build();
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                String responseData = response.body().string();
                return new JSONObject(responseData);
            } else {
                LOGGER.log(Level.SEVERE, "Error sending data to Fogwing: " + response.body().string());
            }
        } catch (IOException | JSONException ex) {
            LOGGER.log(Level.SEVERE, "Error sending data to Fogwing", ex);
        }
        return null;
    }

    public static void main(String[] args) {
        OWMToFogwingClient client = new OWMToFogwingClient();
        JSONObject owm_data = client.getOwmData();
        if (owm_data != null) {
            System.out.println(client.sendDataToFogwing(owm_data));
        }
    }
}
