import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

public class RestAPI {

    private static final String BASE_URL = "http://localhost:8181/onos/v1/";
    private static final String AUTH_HEADER = "Basic " + Base64.getEncoder().encodeToString("onos:rocks".getBytes());

    public String fetchNetworkData(String endpoint) throws Exception {
        String urlString = BASE_URL + endpoint;
        URL url = new URL(urlString);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("Authorization", AUTH_HEADER);

        int responseCode = connection.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            throw new RuntimeException("Failed: HTTP error code: " + responseCode);
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            response.append(line);
        }
        br.close();
        connection.disconnect();

        return response.toString();
    }

    public String[][] parseLinksResponse(String jsonResponse) throws Exception {
        JSONObject jsonObject = new JSONObject(jsonResponse);
        JSONArray linksArray = jsonObject.getJSONArray("links");

        String[][] linksData = new String[linksArray.length()][4]; // 4 fields: Source, Destination, Type, State

        for (int i = 0; i < linksArray.length(); i++) {
            JSONObject link = linksArray.getJSONObject(i);
            linksData[i][0] = link.getJSONObject("src").getString("device") + ":" + link.getJSONObject("src").getString("port");
            linksData[i][1] = link.getJSONObject("dst").getString("device") + ":" + link.getJSONObject("dst").getString("port");
            linksData[i][2] = link.optString("type", "N/A");
            linksData[i][3] = link.optString("state", "N/A");
        }

        return linksData;
    }
}
