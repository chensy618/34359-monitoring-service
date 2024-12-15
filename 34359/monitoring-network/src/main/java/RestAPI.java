import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

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

    public List<Link> parseLinksResponse(String jsonResponse) throws Exception {
        JSONObject jsonObject = new JSONObject(jsonResponse);
        JSONArray linksArray = jsonObject.getJSONArray("links");

        List<Link> linksData = new ArrayList<>();

        for (int i = 0; i < linksArray.length(); i++) {
            JSONObject link = linksArray.getJSONObject(i);
            String source = link.getJSONObject("src").getString("device") + ":" + link.getJSONObject("src").getString("port");
            String destination = link.getJSONObject("dst").getString("device") + ":" + link.getJSONObject("dst").getString("port");
            String type = link.optString("type", "N/A");
            String state = link.optString("state", "N/A");

            // Create Link object and add to the list
            linksData.add(new Link(source, destination, type, state));
        }

        return linksData;
    }

    public List<Host> parseHostsResponse(String jsonResponse) throws Exception {
        JSONObject jsonObject = new JSONObject(jsonResponse);
        JSONArray hostsArray = jsonObject.getJSONArray("hosts");

        List<Host> hostsData = new ArrayList<>();

        for (int i = 0; i < hostsArray.length(); i++) {
            JSONObject host = hostsArray.getJSONObject(i);

            String mac = host.optString("mac", "N/A");

            // 提取 IP 地址数组并拼接为字符串
            String ipAddresses = "N/A";
            JSONArray ipAddressesArray = host.optJSONArray("ipAddresses");
            if (ipAddressesArray != null && ipAddressesArray.length() > 0) {
                ipAddresses = "";
                for (int j = 0; j < ipAddressesArray.length(); j++) {
                    ipAddresses += ipAddressesArray.getString(j);
                    if (j < ipAddressesArray.length() - 1) {
                        ipAddresses += ", "; // 用逗号分隔多个 IP 地址
                    }
                }
            }

            // 提取 Locations 数组并拼接为字符串 (elementId:port 格式)
            String locations = "N/A";
            JSONArray locationsArray = host.optJSONArray("locations");
            if (locationsArray != null && locationsArray.length() > 0) {
                locations = "";
                for (int k = 0; k < locationsArray.length(); k++) {
                    JSONObject locationObj = locationsArray.getJSONObject(k);
                    String elementId = locationObj.optString("elementId", "N/A");
                    String port = locationObj.optString("port", "N/A");
                    locations += elementId + ":" + port;
                    if (k < locationsArray.length() - 1) {
                        locations += ", "; // 用逗号分隔多个 Location
                    }
                }
            }

            // 创建 Host 对象并添加到列表
            hostsData.add(new Host(mac, ipAddresses, locations));
        }

        return hostsData;
    }

    public Map<String, Port> parseStatisticsResponse(String jsonResponse) throws Exception {
        JSONObject jsonObject = new JSONObject(jsonResponse);
        JSONArray statisticsArray = jsonObject.getJSONArray("statistics");

        Map<String, Port> portMap = new HashMap<>();
//        List<Statistic> statisticsData = new ArrayList<>();

        for (int i = 0; i < statisticsArray.length(); i++) {
            JSONObject statistic = statisticsArray.getJSONObject(i);
            String deviceId = statistic.getString("device");

            JSONArray portsArray = statistic.getJSONArray("ports");

//            List<Port> portsData = new ArrayList<>();

            for (int j = 0; j < portsArray.length(); j++) {
                JSONObject portObject = portsArray.getJSONObject(j);

                int portNumber = portObject.getInt("port"); // 端口号
                String devicePort = deviceId + ":" + portNumber;
                int packetsReceived = portObject.optInt("packetsReceived", 0); // 接收的包数
                int packetsSent = portObject.optInt("packetsSent", 0); // 发送的包数
                long bytesReceived = portObject.optLong("bytesReceived", 0); // 接收的字节数
                long bytesSent = portObject.optLong("bytesSent", 0); // 发送的字节数
                long durationSec = portObject.optLong("durationSec", 0); // 持续时间

                // 创建 Port 对象并保存到 Map 中
                Port port = new Port(devicePort, packetsReceived, packetsSent, bytesReceived, bytesSent, durationSec);
                portMap.put(devicePort, port); // 以 devicePort 为键保存 Port 对象
            }
        }
        return portMap;
    }

    public List<Flow> parseFlowsResponse(String jsonResponse) throws Exception {
        JSONObject jsonObject = new JSONObject(jsonResponse);
        JSONArray flowsArray = jsonObject.getJSONArray("flows");

        List<Flow> flowsData = new ArrayList<>();

        // 遍历 flows 数组
        for (int i = 0; i < flowsArray.length(); i++) {
            JSONObject flow = flowsArray.getJSONObject(i);

            String deviceId = flow.getString("deviceId");

            List<Instruction> instructionsList = new ArrayList<>();
            JSONObject treatment = flow.getJSONObject("treatment");
            JSONArray instructions = treatment.getJSONArray("instructions");
            for (int j = 0; j < instructions.length(); j++) {
                JSONObject instructionObj = instructions.getJSONObject(j);
                String type = instructionObj.optString("type", "N/A");
                String port = instructionObj.optString("port", "N/A");
                instructionsList.add(new Instruction(type, port));
            }

            // 提取 criteria
            List<Criteria> criteriaList = new ArrayList<>();
            JSONObject selector = flow.getJSONObject("selector");
            JSONArray criteria = selector.getJSONArray("criteria");
            for (int j = 0; j < criteria.length(); j++) {
                JSONObject criterionObj = criteria.getJSONObject(j);
                String type = criterionObj.optString("type", "N/A");
                String ethType = criterionObj.optString("ethType", "N/A");
                String mac = criterionObj.optString("mac", "N/A");
                String port = criterionObj.optString("port", "N/A");
                criteriaList.add(new Criteria(type, ethType, mac, port));
            }

            // 创建 Flow 对象并加入列表
            flowsData.add(new Flow(deviceId, instructionsList, criteriaList));
        }
        return flowsData;
    }

}
